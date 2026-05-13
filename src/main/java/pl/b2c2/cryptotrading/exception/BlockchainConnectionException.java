package pl.b2c2.cryptotrading.exception;

public class BlockchainConnectionException
        extends RuntimeException {

    public BlockchainConnectionException(
            String message
    ) {

        super(message);
    }
}