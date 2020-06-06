package offer.authorization;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.MessageFormat;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;

public class BaseAuthenticationFilter extends GenericFilterBean {

	private final static Logger LOGGER = LoggerFactory.getLogger(BaseAuthenticationFilter.class);

	protected AuthenticationManager authenticationManager;

	public BaseAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		Optional<String> token = Optional.ofNullable(httpRequest.getHeader(ServicesConstants.HEADER_AUTHORIZATION));
		try {
			if (token.isPresent()) {
				if (token.get().indexOf('%') > -1) {
					token = Optional.of(URLDecoder.decode(token.get(), "UTF-8"));
				}
				if (token.get().startsWith("Bearer ")) {
					token = Optional.ofNullable(token.get().substring(7));
					processTokenAuthentication(token);
				}
			}
			chain.doFilter(request, response);
		} catch (Exception ex) {
			SecurityContextHolder.clearContext();
			LOGGER.error(MessageFormat.format("Response {0}", httpResponse));
			throw ex;
		}
	}

	protected void processTokenAuthentication(Optional<String> token) {
		Authentication resultOfAuthentication = tryToAuthenticateWithToken(token);
		SecurityContextHolder.getContext().setAuthentication(resultOfAuthentication);
	}

	protected Authentication tryToAuthenticateWithToken(Optional<String> token) {
		PreAuthenticatedAuthenticationToken requestAuthentication = new PreAuthenticatedAuthenticationToken(token,
				null);
		return tryToAuthenticate(requestAuthentication);
	}

	protected Authentication tryToAuthenticate(Authentication requestAuthentication) {
		Authentication responseAuthentication = authenticationManager.authenticate(requestAuthentication);
		if (responseAuthentication == null || !responseAuthentication.isAuthenticated()) {
			throw new InternalAuthenticationServiceException("Unable to authenticate caller with provided credentials");
		}
		return responseAuthentication;
	}

}
