package mtgdeckanalyzer.parser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import mtgdeckanalyzer.exceptions.InvalidInputException;

public class DeckParser {
    private static final Set<String> BASIC_LANDS = Set.of("Plains", "Island", "Swamp", "Mountain", "Forest", "Wastes");
    private static final Pattern CARD_LINE_PATTERN = Pattern.compile("(\\d+)\\s+(.*)");

    public Map<String, Integer> parseDeck(String deckList) throws InvalidInputException {
        Map<String, Integer> cardCounts = new HashMap<>();
        Set<String> duplicateCards = new HashSet<>();

        String[] lines = deckList.split("\n");

        for (String line: lines) {
            line = line.trim();
            if (line.isEmpty()) continue;

            Matcher matcher = CARD_LINE_PATTERN.matcher(line);
            if (!matcher.matches()) {
                throw new InvalidInputException("Invalid line format: " + line);
            }

            int quantity = Integer.parseInt(matcher.group(1));
            String cardName = matcher.group(2);

            if (!BASIC_LANDS.contains(matcher.group(2)) && quantity > 1) {
                throw new InvalidInputException("Commander only allows 1 copy of non-basic land cards:" + cardName);
            
            }

            if (cardCounts.containsKey(cardName) && !BASIC_LANDS.contains(cardName)) {
                duplicateCards.add(cardName);
            }

            cardCounts.merge(cardName, quantity, Integer::sum);
        }

        if (!duplicateCards.isEmpty()) {
            throw new InvalidInputException("Duplicate cards: " + String.join(", ", duplicateCards));
        }

        return cardCounts;
    }

}