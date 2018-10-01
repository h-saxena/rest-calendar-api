package com.hemant.cal.security;

import java.io.IOException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Priority;
import javax.security.sasl.AuthenticationException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.hemant.cal.model.AuthClient;
import com.hemant.cal.repo.AuthClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

@Component
@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    @Autowired
    AuthClientRepo authClientRepo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // Extract Authorization header details
        String authorizationHeader
                = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }
        // Extract the token
        String token = authorizationHeader.substring("Bearer".length()).trim();
        // Extract user id
        String clientId = requestContext.getUriInfo().getPathParameters().getFirst("clientId");
        try {
            // Validate the token
            validateToken(token, clientId);
        } catch (AuthenticationServiceException ex) {
            Logger.getLogger(AuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
    private void validateToken(String token, String clientId) throws AuthenticationServiceException {
        byte[] encryptedAccessToken = null;
        String completeToken = "auth_key" + token;
        try {
            Optional<AuthClient> res = authClientRepo.findById(clientId);
            if(!res.isPresent()) {
                throw new InvalidKeySpecException("Invalid Client Id");
            }
            String salt = res.get().getSecret();
            //String accessTokenMaterial = clientId + salt;
            //encryptedAccessToken = authenticationUtil.encrypt(securePassword, accessTokenMaterial);
        } catch (InvalidKeySpecException ex) {
            throw new AuthenticationServiceException("Failed to create secure access token. " + ex.getMessage());
        }
        String encryptedAccessTokenBase64Encoded = Base64.getEncoder().encodeToString(encryptedAccessToken);
        if (!encryptedAccessTokenBase64Encoded.equalsIgnoreCase(completeToken)) {
            throw new AuthenticationServiceException("Authorization token did not match");
        }
    }
}
