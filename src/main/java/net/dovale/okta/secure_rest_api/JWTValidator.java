package net.dovale.okta.secure_rest_api;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTValidator {
    public static void main(String args[]) throws Exception
    {
        /*
        com.okta.jwt.AccessTokenVerifier jwtVerifier = JwtVerifiers.accessTokenVerifierBuilder()
                .setIssuer("https://dev-955771.okta.com/oauth2/default")
                .setAudience("api://default")                // defaults to 'api://default'
                .setConnectionTimeout(Duration.ofSeconds(1)) // defaults to 1s
                .setReadTimeout(Duration.ofSeconds(1))       // defaults to 1s
                .build();


        Jwt jwt = jwtVerifier.decode("eyJraWQiOiJaWHNvUUxpVmtPOVZqT2xCZUZ3b3FubWdUQmtaUTFQcTRfcTA4U2p0STl3IiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULnFNdVJEV2FVMUt3TFdsX0REY01DazVKOENFTHRMV2phYVFJWDBxU0ZYcHMiLCJpc3MiOiJodHRwczovL2Rldi05NTU3NzEub2t0YS5jb20vb2F1dGgyL2RlZmF1bHQiLCJhdWQiOiJhcGk6Ly9kZWZhdWx0IiwiaWF0IjoxNTgxMDg0OTI0LCJleHAiOjE1ODEwODg1MjQsImNpZCI6IjBvYTIzaDBsOVJFc3JSSVVuNHg2IiwidWlkIjoiMDB1MjM5OWg0bVFlWDY0VTU0eDYiLCJzY3AiOlsib3BlbmlkIl0sInN1YiI6Im1mZXN0ZWJhQHlhaG9vLmNvLnVrIn0.IU2fjDrKooajjY0EMmEjMj0QIiiHAQ-ZYDpQKF93nKBCywuUOFMRwHTRDkqK5NoVTcL8LcE6WdjB05euYULhiweDj663srtuLRLvk2Q5T-i02eeS1NoqGBKfMJvzbVz1QIA_I0ahCHn-GSy4uO-SdpIsFqMMkYQFcwFtUOrEJULWvqWeAU7Biz1SPm6kxjOmY8vY_gr8w1NV1husjWPLMAnw0gr0rxl6KFnS4WA_fumDWY2T1VO5--dkONGAiOe9QaFRl9kmk-K9kTLlNozSKG4gH3w708dSX1XVudlVi-uRSQiiwjuuhr9SmRY5nZ-rtBt4S-Cgc0DJb9R69MNf6g");
        System.out.println(jwt.getExpiresAt());
*/
        String str = createJWT("id", "issuer", "subject", 0);
        decodeJWT(str);

    }

    // The secret key. This should be in a property file NOT under source
    // control and not hard coded in real life. We're putting it here for
    // simplicity.
    private static String SECRET_KEY = "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";

    public static String createJWT(String id, String issuer, String subject, long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis > 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public static Claims decodeJWT(String jwt) {

        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }
}
