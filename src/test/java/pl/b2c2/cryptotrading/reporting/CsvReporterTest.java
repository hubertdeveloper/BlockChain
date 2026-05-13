package pl.b2c2.cryptotrading.reporting;

import org.junit.jupiter.api.Test;
import pl.b2c2.cryptotrading.domain.BlockData;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CsvReporterTest {

    @Test
    void shouldCreateCsvReporterObject() {

        CsvReporter reporter =
                new CsvReporter();

        assertNotNull(reporter);
    }

    @Test
    void shouldSaveCsvWithoutException() {

        List<BlockData> blocks = List.of(

                new BlockData(
                        BigInteger.ONE,
                        "0xHASH1",
                        10
                ),

                new BlockData(
                        BigInteger.TWO,
                        "0xHASH2",
                        20
                )
        );

        CsvReporter reporter =
                new CsvReporter();

        assertDoesNotThrow(() ->
                reporter.saveBlocksToCsv(blocks)
        );
    }

    @Test
    void shouldHandleEmptyBlockList() {

        CsvReporter reporter =
                new CsvReporter();

        assertDoesNotThrow(() ->
                reporter.saveBlocksToCsv(
                        List.of()
                )
        );
    }
}