package pl.b2c2.cryptotrading.business;

import pl.b2c2.cryptotrading.domain.BlockData;

import java.util.List;
/*
Warstwa logiki biznesowej.

Klasa odpowiedzialna za:
        - obliczanie statystyk blockchaina,
        - analizę danych bloków.
 */

public class BlockchainStatisticsService {
    /*
           Oblicza łączną liczbę transakcji
         */
    public int calculateTotalTransactions(
            List<BlockData> blocks
    ) {

        int total = 0;
// Iteracja po wszystkich blokach
        for (BlockData block : blocks) {
            // Dodanie liczby transakcji
            total +=
                    block.getTransactionCount();
        }

        return total;
    }
    /*
        Oblicza średnią liczbę transakcji na blok.
      */
    public double calculateAverageTransactions(
            List<BlockData> blocks
    ) {
//zabezpieczenie
        if (blocks.isEmpty()) {
            return 0;
        }

        return (double)
                calculateTotalTransactions(blocks)
                / blocks.size();
    }
}