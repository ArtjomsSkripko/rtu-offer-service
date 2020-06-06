package offer.authorization;

import java.text.ParseException;
import java.util.Collections;

import com.google.common.collect.Sets;
import com.nimbusds.jose.*;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyOperation;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.proc.BadJWTException;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Component;

@Component
public class JWTokenHelper {

    private static final String JWT_CLAIMS_USER_ID = "user_id";
    private static final String JWT_CLAIMS_USERNAME = "username";
    private static final String JWT_CLAIMS_CONTRACT_NUMBER = "contract_number";
    private static final String JWT_CLAIMS_TOUCHPOINT = "touchpoint";
    private static final String JWT_CLAIMS_CONTRACT_MODEL = "contract_model";
    private static final String JWT_CLAIMS_USER_ROLE = "role";
    private static final String JWT_CLAIMS_APP_TYPE = "app_type";
    private static final String JWT_CLAIMS_APP_NAME = "app_name";

    public UserToken parseAccessToken(String token) throws ParseException, BadJOSEException, JOSEException {
        String keySecret = "SK4kPfAVBHbmEsG25FrhSyGhFGYREW7BH7BSgeafhRo";
        JWKSet jwkSet = new JWKSet();
        JWK hmacJWK = new OctetSequenceKey(Base64URL.encode(keySecret.getBytes()), KeyUse.SIGNATURE,
                Sets.newHashSet(KeyOperation.SIGN, KeyOperation.VERIFY), JWSAlgorithm.HS256,
                null, null, null, null, null, null);
        jwkSet = new JWKSet(ListUtils.union(jwkSet.getKeys(), Collections.singletonList(hmacJWK)));
        UserToken authContext = new UserToken();
        Exception returnException = null;
        try {
            JWKSource keySource = new ImmutableJWKSet<>(jwkSet);
            ConfigurableJWTProcessor jwtProcessor = new DefaultJWTProcessor();
            SignedJWT signedJWT = SignedJWT.parse(token);
            
            JWSKeySelector keySelector = new JWSVerificationKeySelector(signedJWT.getHeader().getAlgorithm(), keySource);
            jwtProcessor.setJWSKeySelector(keySelector);
            SecurityContext ctx = null;
            JWTClaimsSet claimsSet = jwtProcessor.process(token, ctx);
            authContext.setTokenId(claimsSet.getJWTID());
            authContext.setUserId(claimsSet.getStringClaim(JWT_CLAIMS_USER_ID));
            authContext.setUsername(claimsSet.getStringClaim(JWT_CLAIMS_USERNAME));
            authContext.setUserRole(claimsSet.getStringClaim(JWT_CLAIMS_USER_ROLE));
            authContext.setContractNumber(claimsSet.getStringClaim(JWT_CLAIMS_CONTRACT_NUMBER));
            authContext.setTouchpoint(claimsSet.getStringClaim(JWT_CLAIMS_TOUCHPOINT));
            authContext.setContractModel(claimsSet.getStringClaim(JWT_CLAIMS_CONTRACT_MODEL));
            authContext.setApplicationType(claimsSet.getStringClaim(JWT_CLAIMS_APP_TYPE));
            authContext.setApplicationName(claimsSet.getStringClaim(JWT_CLAIMS_APP_NAME));

        } catch (BadJWTException e) {
            returnException = new Exception(e.getMessage());
        }
        authContext.setValidationException(returnException);
        return authContext;
    }
}
