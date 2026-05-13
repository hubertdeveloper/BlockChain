package pl.b2c2.cryptotrading.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BlockchainConnectionExceptionTest {

    @Test
    void shouldCreateExceptionObject() {

        BlockchainConnectionException exception =
                new BlockchainConnectionException(
                        "Connection failed"
                );

        assertNotNull(exception);
    }

    @Test
    void shouldStoreCorrectMessage() {

        BlockchainConnectionException exception =
                new BlockchainConnectionException(
                        "API connection error"
                );

        assertEquals(
                "API connection error",
                exception.getMessage()
        );
    }

    @Test
    void shouldHandleEmptyMessage() {

        BlockchainConnectionException exception =
                new BlockchainConnectionException(
                        ""
                );

        assertEquals(
                "",
                exception.getMessage()
        );
    }

    @Test
    void shouldHandleNullMessage() {

        BlockchainConnectionException exception =
                new BlockchainConnectionException(
                        null
                );

        assertEquals(
                null,
                exception.getMessage()
        );
    }
}