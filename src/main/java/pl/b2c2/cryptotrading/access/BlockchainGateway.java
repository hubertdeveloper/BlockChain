
package pl.b2c2.cryptotrading.access;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.http.HttpService;
import pl.b2c2.cryptotrading.domain.BlockData;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/*
   Klasa odpowiedzialna za:
   - połączenie z blockchainem
   - pobieranie bloków,
   - mapowanie danych blockchaina
     do modelu BlockData.
 */
public class BlockchainGateway {

    private final Web3j web3j;

    public BlockchainGateway(String url) {

        this.web3j = Web3j.build(
                new HttpService(url)
        );
    }

    public List<BlockData> fetchLatestBlocks(int count)
            throws Exception {

        List<BlockData> blocks =
                Collections.synchronizedList(
                        new ArrayList<>()
                );

        BigInteger latestBlock =
                web3j.ethBlockNumber()
                        .send()
                        .getBlockNumber();

        ExecutorService executor =
                Executors.newFixedThreadPool(10);

        List<Future<?>> futures =
                new ArrayList<>();

        for (int i = 0; i < count; i++) {

            int index = i;

            Future<?> future =
                    executor.submit(() -> {

                        try {

                            BigInteger currentBlock =
                                    latestBlock.subtract(
                                            BigInteger.valueOf(index)
                                    );

                            EthBlock response =
                                    web3j.ethGetBlockByNumber(
                                            new DefaultBlockParameterNumber(
                                                    currentBlock
                                            ),
                                            false
                                    ).send();

                            EthBlock.Block block =
                                    response.getBlock();

                            blocks.add(
                                    new BlockData(
                                            block.getNumber(),
                                            block.getHash(),
                                            block.getTransactions().size(),
                                            block.getGasUsed(),
                                            block.getGasLimit(),
                                            block.getMiner(),
                                            block.getTimestamp()
                                    )
                            );

                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                    });

            futures.add(future);
        }

        for (Future<?> future : futures) {
            future.get();
        }

        executor.shutdown();

        return blocks;
    }
}
