package pl.b2c2.cryptotrading.reporting;

import org.junit.jupiter.api.Test;
import pl.b2c2.cryptotrading.domain.BlockData;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CsvReporterTest {

    @Test
    void shouldCreateCsvFile() throws Exception {

        File file =
                new File("block-report.csv");

        if (file.exists()) {
            file.delete();
        }

        List<BlockData> blocks = List.of(

                new BlockData(
                        BigInteger.ONE,
                        "0xHASH1",
                        10,
                        BigInteger.valueOf(1000),
                        BigInteger.valueOf(2000),
                        "miner1",
                        BigInteger.valueOf(111)
                )
        );

        CsvReporter reporter =
                new CsvReporter();

        assertDoesNotThrow(() ->
                reporter.saveBlocksToCsv(blocks)
        );

        assertTrue(file.exists());

        List<String> lines =
                Files.readAllLines(
                        file.toPath()
                );

        assertFalse(lines.isEmpty());

        assertTrue(
                lines.get(1)
                        .contains("0xHASH1")
        );
    }

    @Test
    void shouldAppendDataToCsv() throws Exception {

        File file =
                new File("block-report.csv");

        if (file.exists()) {
            file.delete();
        }

        CsvReporter reporter =
                new CsvReporter();

        reporter.saveBlocksToCsv(List.of(

                new BlockData(
                        BigInteger.ONE,
                        "0xHASH1",
                        10,
                        BigInteger.valueOf(1000),
                        BigInteger.valueOf(2000),
                        "miner1",
                        BigInteger.valueOf(111)
                )
        ));

        reporter.saveBlocksToCsv(List.of(

                new BlockData(
                        BigInteger.TWO,
                        "0xHASH2",
                        20,
                        BigInteger.valueOf(3000),
                        BigInteger.valueOf(4000),
                        "miner2",
                        BigInteger.valueOf(222)
                )
        ));

        List<String> lines =
                Files.readAllLines(
                        file.toPath()
                );

        assertEquals(3, lines.size());

        assertTrue(
                lines.get(1)
                        .contains("0xHASH1")
        );

        assertTrue(
                lines.get(2)
                        .contains("0xHASH2")
        );
    }
}