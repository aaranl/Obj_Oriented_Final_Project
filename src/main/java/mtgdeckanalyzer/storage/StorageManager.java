package mtgdeckanalyzer.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import mtgdeckanalyzer.exceptions.StorageException;
import mtgdeckanalyzer.model.Deck;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StorageManager {
    private static final String STORAGE_DIR = "saved_decks";
    private static final String DECKS_FILE = STORAGE_DIR + "/decks.json";
    private final Gson gson;

    public StorageManager() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        createStorageDirectory();
    }

    private void createStorageDirectory() {
        File directory = new File(STORAGE_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
    public void saveDeck(Deck deck) throws StorageException {
        List<Deck> existingDecks = new ArrayList<>(loadDecks());
        existingDecks.add(deck);
        try (Writer writer = new FileWriter(DECKS_FILE)) {
            gson.toJson(existingDecks, writer);
        } catch (IOException e) {
            throw new StorageException("Could not save deck: " + e.getMessage());
        }
    }

    public List<Deck> loadDecks() throws StorageException {
        File file = new File(DECKS_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (Reader reader = new FileReader(file)) {
            Type deckListType = new TypeToken<ArrayList<Deck>>(){}.getType();
            List<Deck> decks = gson.fromJson(reader, deckListType);
            if (decks != null) {
                return decks;
            } else {
                return new ArrayList<>();
            }
        } catch (IOException e) {
            throw new StorageException("Could not load decks: " + e.getMessage());
        }
    }
}