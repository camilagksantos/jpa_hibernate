package com.jpa_hibernate.entities.generators;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.security.*;
import java.util.Base64;
import java.util.UUID;

public class UUIDGenerator implements IdentifierGenerator {

    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        return sign(UUID.randomUUID().toString());
    }

    private String sign(String uuid) {
        try {
            KeyPairGenerator kg = KeyPairGenerator.getInstance("RSA");
            kg.initialize(2048);
            kg.generateKeyPair();
            KeyPair kP = kg.generateKeyPair();
            PrivateKey pK = kP.getPrivate();

            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(pK);
            signature.update(uuid.getBytes());
            byte[] result = signature.sign();

            return uuid + "." + Base64.getEncoder().encodeToString(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
