package mtgdeckanalyzer.model;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private String name;
    private Card commander;
    private List<Card> cards;

    public Deck(String name, Card commander) {
        this.name = name;
        this.commander = commander;
        this.cards = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Card getCommander() {
        return commander;
    }

    public void setCommander(Card commander) {
        this.commander = commander;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }
}

