package mtgdeckanalyzer.ui;

import javax.swing.*;
import java.awt.*;

import mtgdeckanalyzer.analysis.ColorDistributionAnalyzer;
import mtgdeckanalyzer.analysis.ManaCurveAnalyzer;
import mtgdeckanalyzer.builder.DeckBuilder;
import mtgdeckanalyzer.exceptions.InvalidInputException;
import mtgdeckanalyzer.model.Deck;
import mtgdeckanalyzer.storage.StorageManager;
import java.util.List;

public class MainUI extends JFrame {
    private final DeckBuilder deckBuilder;
    private JTextField commanderNameField;
    private JTextArea deckListArea;
    private StorageManager storageManager;

    public MainUI() {
        this.deckBuilder = new DeckBuilder();
        this.storageManager = new StorageManager();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("MTG Commander Deck Builder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel commanderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        commanderPanel.add(new JLabel("Commander Name:"));
        commanderNameField = new JTextField(30);
        commanderPanel.add(commanderNameField);
        deckListArea = new JTextArea(20, 30);
        deckListArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(deckListArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Deck List (One card per line, format: 1 Card Name)"));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton analyzeButton = new JButton("Analyze Deck");
        analyzeButton.addActionListener(e -> buildDeck());
        JButton loadDecksButton = new JButton("View Analyses");
        loadDecksButton.addActionListener(e -> loadSavedDecks());
        buttonPanel.add(analyzeButton);
        buttonPanel.add(loadDecksButton);
        mainPanel.add(commanderPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
        setSize(500, 600);
        setLocationRelativeTo(null);
    }

    private void buildDeck() {
        try {
            String commanderName = commanderNameField.getText().trim();
            String deckList = deckListArea.getText().trim();
            
            if (commanderName.isEmpty()) {
                throw new InvalidInputException("Commander name cannot be empty");
            }
            
            if (deckList.isEmpty()) {
                throw new InvalidInputException("Deck list cannot be empty");
            }
            
            Deck deck = deckBuilder.buildDeck("New Deck", commanderName, deckList);
            
            JOptionPane.showMessageDialog(this, "Deck built successfully!\nCommander: " + deck.getCommander().getName() + "\nTotal cards: " + deck.getCards().size(), "Success", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (Exception e) {
            e.printStackTrace();
            
            JOptionPane.showMessageDialog(this, 
                "Error: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadSavedDecks() {
        try {
            List<Deck> decks = storageManager.loadDecks();
            if (decks.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "No saved decks found.",
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            JFrame deckFrame = new JFrame("Saved Decks");
            deckFrame.setSize(600, 400);
            deckFrame.setLocationRelativeTo(this);

            JTextArea deckList = new JTextArea();
            deckList.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(deckList);

            StringBuilder displayText = new StringBuilder();
            displayText.append("=== Saved Decks ===\n\n");

            for (Deck deck : decks) {
                displayText.append("Deck Name: ").append(deck.getName()).append("\n");
                displayText.append("Commander: ").append(deck.getCommander().getName()).append("\n");
                displayText.append("Cards: ").append(deck.getCards().size()).append("\n");
                displayText.append("\nCard List:\n");
                deck.getCards().forEach(card -> 
                    displayText.append("- ").append(card.getName()).append(" (").append(card.getManaCost()).append(") [").append(card.getType().replace("—", "-")).append("]\n"));
                ManaCurveAnalyzer analyzer = new ManaCurveAnalyzer();
                displayText.append("\n=========Mana Curve Analysis=========\n");
                displayText.append("Mana Curve:\n").append(analyzer.getManaCurveString(analyzer.analyzeDeck(deck)));
                ColorDistributionAnalyzer colorAnalyzer = new ColorDistributionAnalyzer();
                displayText.append("\n=========Color Distribution Analysis=========\n");
                displayText.append("Color Distribution:\n").append(colorAnalyzer.getColorDistributionString(colorAnalyzer.analyzeColorDistribution(deck)));
                displayText.append("\n=================\n\n");
            }

            deckList.setText(displayText.toString());

            deckFrame.add(scrollPane);
            deckFrame.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error loading decks: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public void displayUI() {
        setVisible(true);
    }
}
