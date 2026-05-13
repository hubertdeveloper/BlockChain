package pl.b2c2.cryptotrading.business;

import pl.b2c2.cryptotrading.domain.BlockData;

import java.util.List;

public class BlockchainStatisticsService {

    public int calculateTotalTransactions(
            List<BlockData> blocks
    ) {

        int total = 0;

        for (BlockData block : blocks) {

            total +=
                    block.getTransactionCount();
        }

        return total;
    }

    public double calculateAverageTransactions(
            List<BlockData> blocks
    ) {

        if (blocks.isEmpty()) {
            return 0;
        }

        return (double)
                calculateTotalTransactions(blocks)
                / blocks.size();
    }
}