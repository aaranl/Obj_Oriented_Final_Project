package mtgdeckanalyzer.analysis;

import mtgdeckanalyzer.model.Card;
import mtgdeckanalyzer.model.Deck;
import java.util.HashMap;
import java.util.Map;

public class ColorDistributionAnalyzer {
    private static final String[] COLORS = {"W", "U", "B", "R", "G"};
    
    public Map<String, Double> analyzeColorDistribution(Deck deck) {
        Map<String, Integer> colorCounts = new HashMap<>();
        Map<String, Double> colorPercentages = new HashMap<>();
        
        for (String color : COLORS) {
            colorCounts.put(color, 0);
        }
        
        int totalColorPips = 0;
        for (Card card : deck.getCards()) {
            if (card.getType().toLowerCase().contains("land")) {
                continue;
            }
            
            for (String color : card.getColors()) {
                colorCounts.put(color, colorCounts.get(color) + 1);
                totalColorPips++;
            }
        }
        
        if (totalColorPips > 0) {
            for (String color : COLORS) {
                double percentage = (colorCounts.get(color) * 100.0) / totalColorPips;
                colorPercentages.put(color, Math.round(percentage * 10.0) / 10.0);
            }
        }
        
        return colorPercentages;
    }
    
    public String getColorDistributionString(Map<String, Double> colorDistribution) {
        StringBuilder sb = new StringBuilder();
        
        Map<String, String> colorNames = new HashMap<>();
        colorNames.put("W", "White");
        colorNames.put("U", "Blue");
        colorNames.put("B", "Black");
        colorNames.put("R", "Red");
        colorNames.put("G", "Green");
        
        for (String color : COLORS) {
            if (colorDistribution.get(color) > 0) {
                sb.append(colorNames.get(color))
                  .append(": ")
                  .append(colorDistribution.get(color))
                  .append("%\n");
            }
        }
        
        return sb.toString();
    }
}
