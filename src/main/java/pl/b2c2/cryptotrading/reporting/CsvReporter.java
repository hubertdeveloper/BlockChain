package pl.b2c2.cryptotrading.reporting;

import com.opencsv.CSVWriter;
import pl.b2c2.cryptotrading.domain.BlockData;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Klasa odpowiedzialna za
 * zapis raportu blockchain do CSV.
 */
public class CsvReporter {

    public void saveBlocksToCsv(
            List<BlockData> blocks
    ) {

        try {

            CSVWriter writer =
                    new CSVWriter(
                            new FileWriter(
                                    "block-report.csv"
                            )
                    );

            String[] header = {
                    "Block Number",
                    "Hash",
                    "Transactions",
                    "Gas Used",
                    "Gas Limit",
                    "Miner",
                    "Timestamp"
            };

            writer.writeNext(header);

            for (BlockData block : blocks) {

                String[] data = {
                        block.getNumber().toString(),
                        block.getHash(),
                        String.valueOf(block.getTransactionCount()),
                        block.getGasUsed().toString(),
                        block.getGasLimit().toString(),
                        block.getMiner(),
                        block.getTimestamp().toString()
                };

                writer.writeNext(data);
            }

            writer.close();

            System.out.println(
                    "CSV report saved"
            );

        } catch (IOException e) {

            System.out.println(
                    "CSV save error: "
                            + e.getMessage()
            );
        }
    }
}