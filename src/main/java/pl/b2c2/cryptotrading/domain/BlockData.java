package pl.b2c2.cryptotrading.domain;

import java.math.BigInteger;

public class BlockData {

    private final BigInteger number;

    private final String hash;

    private final int transactionCount;

    private final BigInteger gasUsed;

    private final BigInteger gasLimit;

    private final String miner;

    private final BigInteger timestamp;

    public BlockData(
            BigInteger number,
            String hash,
            int transactionCount,
            BigInteger gasUsed,
            BigInteger gasLimit,
            String miner,
            BigInteger timestamp
    ) {

        this.number = number;
        this.hash = hash;
        this.transactionCount = transactionCount;
        this.gasUsed = gasUsed;
        this.gasLimit = gasLimit;
        this.miner = miner;
        this.timestamp = timestamp;
    }

    public BigInteger getNumber() {
        return number;
    }

    public String getHash() {
        return hash;
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    public BigInteger getGasUsed() {
        return gasUsed;
    }

    public BigInteger getGasLimit() {
        return gasLimit;
    }

    public String getMiner() {
        return miner;
    }

    public BigInteger getTimestamp() {
        return timestamp;
    }
}