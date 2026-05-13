package pl.b2c2.cryptotrading.business;

import org.junit.jupiter.api.Test;
import pl.b2c2.cryptotrading.domain.BlockData;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlockchainStatisticsServiceTest {

    @Test
    void shouldCalculateTotalTransactions() {

        List<BlockData> blocks = List.of(

                new BlockData(
                        BigInteger.ONE,
                        "0x1",
                        10
                ),

                new BlockData(
                        BigInteger.TWO,
                        "0x2",
                        20
                )
        );

        BlockchainStatisticsService service =
                new BlockchainStatisticsService();

        int result =
                service.calculateTotalTransactions(
                        blocks
                );

        assertEquals(30, result);
    }

    @Test
    void shouldCalculateAverageTransactions() {

        List<BlockData> blocks = List.of(

                new BlockData(
                        BigInteger.ONE,
                        "0x1",
                        10
                ),

                new BlockData(
                        BigInteger.TWO,
                        "0x2",
                        20
                )
        );

        BlockchainStatisticsService service =
                new BlockchainStatisticsService();

        double average =
                service.calculateAverageTransactions(
                        blocks
                );

        assertEquals(15.0, average);
    }

    @Test
    void shouldReturnZeroForEmptyList() {

        BlockchainStatisticsService service =
                new BlockchainStatisticsService();

        double average =
                service.calculateAverageTransactions(
                        List.of()
                );

        assertEquals(0.0, average);
    }
}