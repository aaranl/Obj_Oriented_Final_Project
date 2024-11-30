package mtgdeckanalyzer.analysis;

import mtgdeckanalyzer.model.Card;
import mtgdeckanalyzer.model.Deck;
import java.util.HashMap;
import java.util.Map;

public class ManaCurveAnalyzer {
    
    public Map<Integer, Integer> analyzeDeck(Deck deck) {
        Map<Integer, Integer> manaCurve = new HashMap<>();
        
        for (int i = 0; i <= 7; i++) {
            manaCurve.put(i, 0);
        }
        
        for (Card card : deck.getCards()) {
            if (card.getType().toLowerCase().contains("land")) {
                continue;
            }
            
            int cmc = card.getCmc();
            
            if (cmc >= 7) {
                manaCurve.put(7, manaCurve.get(cmc) + 1);
            } else {
                manaCurve.put(cmc, manaCurve.get(cmc) + 1);
            }
        }
        
        return manaCurve;
    }
    
    public String getManaCurveString(Map<Integer, Integer> manaCurve) {
        StringBuilder manaCurveString = new StringBuilder();
        
        for (int i = 0; i <= 7; i++) {
            if (i == 7) {
                manaCurveString.append("7+ CMC: ").append(manaCurve.get(i)).append("\n");
            } else {
                manaCurveString.append(i).append(" CMC: ").append(manaCurve.get(i)).append("\n");
            }
        }
        
        return manaCurveString.toString();
    }
}
