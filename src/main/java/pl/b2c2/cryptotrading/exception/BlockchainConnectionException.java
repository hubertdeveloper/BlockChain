package pl.b2c2.cryptotrading.exception;
// wyjątek aplikacjii
public class BlockchainConnectionException
        extends RuntimeException {

    /*
       Konstruktor wyjątku.
       message - opis błędu.
     */
    public BlockchainConnectionException(
            String message
    ) {

        super(message);
    }
}