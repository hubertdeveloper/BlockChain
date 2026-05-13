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
                15
        );

        assertNotNull(block);
    }

    @Test
    void shouldReturnCorrectBlockNumber() {

        BlockData block = new BlockData(
                BigInteger.valueOf(2000),
                "0xHASH",
                25
        );

        assertEquals(
                BigInteger.valueOf(2000),
                block.getNumber()
        );
    }

    @Test
    void shouldReturnCorrectHash() {

        BlockData block = new BlockData(
                BigInteger.ONE,
                "0xTESTHASH",
                10
        );

        assertEquals(
                "0xTESTHASH",
                block.getHash()
        );
    }

    @Test
    void shouldReturnCorrectTransactionCount() {

        BlockData block = new BlockData(
                BigInteger.TEN,
                "0xTRANSACTION",
                50
        );

        assertEquals(
                50,
                block.getTransactionCount()
        );
    }

    @Test
    void shouldHandleZeroTransactions() {

        BlockData block = new BlockData(
                BigInteger.ONE,
                "0xEMPTY",
                0
        );

        assertEquals(
                0,
                block.getTransactionCount()
        );
    }
}