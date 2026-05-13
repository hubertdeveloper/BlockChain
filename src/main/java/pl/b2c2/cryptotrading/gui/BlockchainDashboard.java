package pl.b2c2.cryptotrading.gui;

import pl.b2c2.cryptotrading.access.BlockchainGateway;
import pl.b2c2.cryptotrading.config.BlockchainConfig;
import pl.b2c2.cryptotrading.domain.BlockData;
import pl.b2c2.cryptotrading.reporting.CsvReporter;

import java.time.Instant;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BlockchainDashboard extends JFrame {

    private final JTextArea outputArea;

    public BlockchainDashboard() {

        setTitle("Blockchain Monitor");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton loadButton =
                new JButton("Load Blockchain Data");

        outputArea = new JTextArea();

        outputArea.setEditable(false);

        JScrollPane scrollPane =
                new JScrollPane(outputArea);

        loadButton.addActionListener(e -> loadData());

        setLayout(new BorderLayout());

        add(loadButton, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadData() {

        SwingUtilities.invokeLater(() ->
                outputArea.setText("")
        );

        try {

            CsvReporter reporter =
                    new CsvReporter();

            BlockchainGateway gateway =
                    new BlockchainGateway(
                            BlockchainConfig.API_URL
                    );

            List<BlockData> blocks =
                    gateway.fetchLatestBlocks(100);

            reporter.saveBlocksToCsv(blocks);

            for (BlockData block : blocks) {

                SwingUtilities.invokeLater(() -> {

                    outputArea.append(
                            "Block: "
                                    + block.getNumber()
                                    + "\n"
                    );

                    outputArea.append(
                            "Hash: "
                                    + block.getHash()
                                    + "\n"
                    );

                    outputArea.append(
                            "Transactions: "
                                    + block.getTransactionCount()
                                    + "\n"
                    );

                    outputArea.append(
                            "Gas Used: "
                                    + block.getGasUsed()
                                    + "\n"
                    );

                    outputArea.append(
                            "Gas Limit: "
                                    + block.getGasLimit()
                                    + "\n"
                    );

                    outputArea.append(
                            "Miner: "
                                    + block.getMiner()
                                    + "\n"
                    );

                    Instant instant = Instant.ofEpochSecond(block.getTimestamp().longValue());
                    outputArea.append(
                            "Timestamp: "
                                    + instant
                                    + "\n\n"
                    );
                });
            }

        } catch (Exception e) {

            SwingUtilities.invokeLater(() ->

                    outputArea.setText(
                            "Error: " + e.getMessage()
                    )
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            BlockchainDashboard dashboard =
                    new BlockchainDashboard();

            dashboard.setVisible(true);
        });
    }
}