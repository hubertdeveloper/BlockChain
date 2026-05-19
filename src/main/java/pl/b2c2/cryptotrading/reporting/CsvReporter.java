package pl.b2c2.cryptotrading.reporting;

import com.opencsv.CSVWriter;
import pl.b2c2.cryptotrading.domain.BlockData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/*
   Klasa odpowiedzialna za
   zapis raportu blockchain do CSV.
 */
public class CsvReporter {

    public void saveBlocksToCsv(
            List<BlockData> blocks
    ) {
//tworzenie nowego pliku, jeśli istnieje dopisanie do istniejącego
        try {

            File file =
                    new File("block-report.csv");

            boolean fileExists =
                    file.exists();

            CSVWriter writer =
                    new CSVWriter(
                            new FileWriter(
                                    file,
                                    true
                            )
                    );

            /*
               Header zapisujemy tylko,
               jeśli plik jeszcze nie istnieje.
             */
            if (!fileExists) {

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
            }
//pętla dla każdego bloku
            for (BlockData block : blocks) {

                String[] data = {

                        block.getNumber()
                                .toString(),

                        block.getHash(),

                        String.valueOf(
                                block.getTransactionCount()
                        ),

                        block.getGasUsed()
                                .toString(),

                        block.getGasLimit()
                                .toString(),

                        block.getMiner(),

                        block.getTimestamp()
                                .toString()
                };

                writer.writeNext(data);
            }

            writer.close();
//komunikat o wykonaniu akcjii- zapisywanie
            System.out.println(
                    "CSV report saved"
            );
//łapanie błędu- zapisu
        } catch (IOException e) {

            System.out.println(
                    "CSV save error: "
                            + e.getMessage()
            );
        }
    }
}