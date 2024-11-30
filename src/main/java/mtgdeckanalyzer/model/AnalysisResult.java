package mtgdeckanalyzer.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalysisResult {
    private Map<String, Integer> colorDistribution;
    private Map<String, Double> colorPercentages;
    private List<String> dominantColors;
    private Map<Integer, Integer> manaCurve;
    private List<String> validationMessages;
    private boolean isValid;
    private double averageCMC;
    private int maxCMC;
    private int totalCards;
    private int totalLands;

    public AnalysisResult() {
        this.colorDistribution = new HashMap<>();
        this.colorPercentages = new HashMap<>();
        this.dominantColors = new ArrayList<>();
        this.manaCurve = new HashMap<>();
        this.validationMessages = new ArrayList<>();
        this.isValid = false;
        this.averageCMC = 0.0;
        this.maxCMC = 0;
        this.totalCards = 0;
        this.totalLands = 0;
    }

    public Map<String, Integer> getColorDistribution() {
        return colorDistribution;
    }

    public void setColorDistribution(Map<String, Integer> colorDistribution) {
        this.colorDistribution = colorDistribution;
    }

    public Map<String, Double> getColorPercentages() {
        return colorPercentages;
    }

    public void setColorPercentages(Map<String, Double> colorPercentages) {
        this.colorPercentages = colorPercentages;
    }

    public List<String> getDominantColors() {
        return dominantColors;
    }

    public void setDominantColors(List<String> dominantColors) {
        this.dominantColors = dominantColors;
    }

    public Map<Integer, Integer> getManaCurve() {
        return manaCurve;
    }

    public void setManaCurve(Map<Integer, Integer> manaCurve) {
        this.manaCurve = manaCurve;
    }

    public double getAverageCMC() {
        return averageCMC;
    }

    public void setAverageCMC(double averageCMC) {
        this.averageCMC = averageCMC;
    }

    public int getMaxCMC() {
        return maxCMC;
    }

    public void setMaxCMC(int maxCMC) {
        this.maxCMC = maxCMC;
    }

    public int getTotalCards() {
        return totalCards;
    }

    public void setTotalCards(int totalCards) {
        this.totalCards = totalCards;
    }

    public int getTotalLands() {
        return totalLands;
    }

    public void setTotalLands(int totalLands) {
        this.totalLands = totalLands;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        this.isValid = valid;
    }

    public List<String> getValidationMessages() {
        return validationMessages;
    }

    public void setValidationMessages(List<String> validationMessages) {
        this.validationMessages = validationMessages;
    }
}
