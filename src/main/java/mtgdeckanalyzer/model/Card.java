package mtgdeckanalyzer.model;

import java.util.ArrayList;
import java.util.List;

public class Card {
    private String name;
    private String manaCost;
    private int cmc;
    private List<String> colors;
    private List<String> colorIdentity;
    private String type;
    private String power;
    private String toughness;
    private String text;
    private boolean isLegal;
    private String imageUrlNormal;
    private String imageUrlPNG; 

    public Card() {
        this.colors = new ArrayList<>();
        this.colorIdentity = new ArrayList<>();
    }

    public Card(String name, String manaCost, int cmc, 
                List<String> colors, List<String> colorIdentity, 
                String type, 
                String power, String toughness, 
                String text, boolean isLegal, String imageUrlNormal, String imageUrlPNG) {
        
        this.name = name;
        this.manaCost = manaCost;
        this.cmc = cmc;
        
        this.colors = new ArrayList<>(colors);
        this.colorIdentity = new ArrayList<>(colorIdentity);
        
        this.power = power;
        this.toughness = toughness;
        this.text = text;
        this.isLegal = isLegal;
        this.imageUrlNormal = imageUrlNormal;
        this.imageUrlPNG = imageUrlPNG;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManaCost() {
        return manaCost;
    }

    public void setManaCost(String manaCost) {
        this.manaCost = manaCost;
    }

    public int getCmc() {
        return cmc;
    }

    public void setCmc(int cmc) {
        this.cmc = cmc;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public List<String> getColorIdentity() {
        return colorIdentity;
    }

    public void setColorIdentity(List<String> colorIdentity) {
        this.colorIdentity = colorIdentity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getToughness() {
        return toughness;
    }

    public void setToughness(String toughness) {
        this.toughness = toughness;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isLegal() {
        return isLegal;
    }

    public void setLegal(boolean legal) {
        isLegal = legal;
    }

    public String getImageUrlNormal() {
        return imageUrlNormal;
    }

    public void setImageUrlNormal(String imageUrlNormal) {
        this.imageUrlNormal = imageUrlNormal;
    }

    public String getImageUrlPNG() {
        return imageUrlPNG;
    }

    public void setImageUrlPNG(String imageUrlPNG) {
        this.imageUrlPNG = imageUrlPNG;
    }
}