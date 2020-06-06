package offer.authorization;

import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static offer.authorization.ServicesConstants.HEADER_AUTHORIZATION;

public class Utils {

    public static ErrorResponse getAuthenticationError() {
        ErrorResponse ret = new ErrorResponse();
        ret.setCode(ServicesConstants.ERROR_ID_AUTHENTICATION_ERROR);
        ret.setName("AuthenticationError");
        ret.setDescription("Authentication failed");
        return ret;
    }

    public static void writeJSONResponse(Object object, HttpServletResponse httpResponse, int httpStatus)
            throws IOException {
        String json = new ObjectMapper().writeValueAsString(object);
        httpResponse.addHeader("Content-Type", "application/json;charset=UTF-8");
        httpResponse.setStatus(httpStatus);
        httpResponse.getWriter().print(json);
    }

    public static boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }

    public static UserToken getServiceUser() {
        return isAuthenticated() && SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserToken ?
                (UserToken) SecurityContextHolder.getContext().getAuthentication().getPrincipal() : null;
    }

    public static HttpHeaders getAuthorizationHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null)
        httpHeaders.add(HEADER_AUTHORIZATION, "Bearer " + authentication.getDetails());
        return httpHeaders;
    }
}
