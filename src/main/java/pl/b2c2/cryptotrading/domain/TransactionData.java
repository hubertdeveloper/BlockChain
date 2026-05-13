package pl.b2c2.cryptotrading.domain;

import java.math.BigInteger;

public class TransactionData {

    private final String hash;
    private final String from;
    private final String to;
    private final String value;
    private final BigInteger gas;

    public TransactionData(
            String hash,
            String from,
            String to,
            String value,
            BigInteger gas
    ) {
        this.hash = hash;
        this.from = from;
        this.to = to;
        this.value = value;
        this.gas = gas;
    }

    public String getHash() {
        return hash;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getValue() {
        return value;
    }

    public BigInteger getGas() {
        return gas;
    }
}