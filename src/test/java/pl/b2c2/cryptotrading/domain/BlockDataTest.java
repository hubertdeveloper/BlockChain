package pl.b2c2.cryptotrading.domain;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BlockDataTest {

    @Test
    void shouldCreateBlockDataObject() {

        BlockData block = new BlockData(
                BigInteger.valueOf(1000),
                "0xABC123",
                15,
                BigInteger.valueOf(123456),
                BigInteger.valueOf(987654),
                "0xMinerAddress",
                BigInteger.valueOf(1710000000L)
        );

        assertNotNull(block);
    }

    @Test
    void shouldReturnCorrectAllFields() {

        BlockData block = new BlockData(
                BigInteger.valueOf(2000),
                "0xHASH",
                25,
                BigInteger.valueOf(1111),
                BigInteger.valueOf(2222),
                "0xMiner",
                BigInteger.valueOf(3333)
        );

        assertEquals(BigInteger.valueOf(2000), block.getNumber());
        assertEquals("0xHASH", block.getHash());
        assertEquals(25, block.getTransactionCount());
        assertEquals(BigInteger.valueOf(1111), block.getGasUsed());
        assertEquals(BigInteger.valueOf(2222), block.getGasLimit());
        assertEquals("0xMiner", block.getMiner());
        assertEquals(BigInteger.valueOf(3333), block.getTimestamp());
    }

    @Test
    void shouldHandleZeroTransactions() {

        BlockData block = new BlockData(
                BigInteger.ONE,
                "0xEMPTY",
                0,
                BigInteger.ZERO,
                BigInteger.valueOf(100),
                "0xMiner",
                BigInteger.valueOf(123)
        );

        assertEquals(0, block.getTransactionCount());
    }
}
