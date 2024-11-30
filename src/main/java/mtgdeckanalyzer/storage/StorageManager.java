package mtgdeckanalyzer.storage;

import com.google.gson.Gson;
import mtgdeckanalyzer.exceptions.StorageException;
import mtgdeckanalyzer.model.Deck;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StorageManager {
    private static final String DECKS_FILE = "saved_decks.json";
    private final Gson gson;

    public StorageManager() {
        gson = new Gson();
    }

    public void saveDeck(Deck deck) throws StorageException {
        List<Deck> decks = loadDecks();
        decks.add(deck);
        
        try (FileWriter writer = new FileWriter(DECKS_FILE)) {
            gson.toJson(decks, writer);
        } catch (IOException e) {
            throw new StorageException("Could not save deck: " + e.getMessage());
        }
    }

    public List<Deck> loadDecks() throws StorageException {
        File file = new File(DECKS_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (FileReader reader = new FileReader(file)) {
            Deck[] decks = gson.fromJson(reader, Deck[].class);
            if (decks != null) {
                return List.of(decks);
            } else {
                return new ArrayList<>();
            }
        } catch (IOException e) {
            throw new StorageException("Could not load decks: " + e.getMessage());
        }
    }
}
