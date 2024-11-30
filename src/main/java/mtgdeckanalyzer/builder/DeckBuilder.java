package mtgdeckanalyzer.builder;

import mtgdeckanalyzer.api.ScryfallAPI;
import mtgdeckanalyzer.exceptions.InvalidInputException;
import mtgdeckanalyzer.model.Card;
import mtgdeckanalyzer.model.Deck;
import mtgdeckanalyzer.parser.DeckParser;
import mtgdeckanalyzer.storage.StorageManager;
import mtgdeckanalyzer.exceptions.StorageException;

import java.io.IOException;
import java.util.Map;

public class DeckBuilder {
    private final DeckParser deckParser;
    private final StorageManager storageManager;
    
    public DeckBuilder() {
        this.deckParser = new DeckParser();
        this.storageManager = new StorageManager();
    }
    
    public Deck buildDeck(String deckName, String commanderName, String deckList) throws InvalidInputException, IOException, StorageException {
        Card commander = ScryfallAPI.getCard(commanderName);
        if (!commander.isLegal()) {
            throw new InvalidInputException("Commander is not legal in Commander format: " + commanderName);
        }
        
        Deck deck = new Deck(deckName, commander);
        Map<String, Integer> cardCounts = deckParser.parseDeck(deckList);
        cardCounts.remove(commanderName);
        
        for (Map.Entry<String, Integer> entry : cardCounts.entrySet()) {
            String cardName = entry.getKey();
            int quantity = entry.getValue();
            
            Card card = ScryfallAPI.getCard(cardName);
            if (!card.isLegal()) {
                throw new InvalidInputException("Card is not legal in Commander format: " + cardName);
            }
            
            for (int i = 0; i < quantity; i++) {
                deck.addCard(card);
            }
        }
        
        storageManager.saveDeck(deck);
        
        return deck;
    }
}