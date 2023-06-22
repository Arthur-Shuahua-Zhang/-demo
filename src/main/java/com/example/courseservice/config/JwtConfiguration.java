package com.example.courseservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

@Configuration
public class JwtConfiguration {

    @Value("${jwt.keystore.path}")
    private String keystorePath;

    @Value("${jwt.keystore.password}")
    private String keystorePassword;

    @Value("${jwt.keystore.key.alias}")
    private String keyAlias;

    @Bean
    public PublicKey jwtPublicKey() throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        KeyStore keyStore = KeyStore.getInstance("JKS");
        InputStream inputStream = new ClassPathResource(keystorePath).getInputStream();
        keyStore.load(inputStream, keystorePassword.toCharArray());
        PrivateKey privateKey = (PrivateKey) keyStore.getKey(keyAlias, keystorePassword.toCharArray());
        PublicKey publicKey = keyStore.getCertificate(keyAlias).getPublicKey();
        return publicKey;
    }
}

