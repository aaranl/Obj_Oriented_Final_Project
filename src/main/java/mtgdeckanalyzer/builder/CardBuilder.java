package mtgdeckanalyzer.builder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import mtgdeckanalyzer.model.Card;
import java.util.ArrayList;
import java.util.List;


public class CardBuilder {

    private static List<String> jsonArrayToList(JsonArray jsonArray) {
        List<String> list = new ArrayList<>();
        jsonArray.forEach(element -> list.add(element.getAsString()));
        return list;
    }

    private static boolean isLegalInCommander(JsonObject cardData) {
        JsonObject legalities = cardData.getAsJsonObject("legalities");
        return "legal".equals(legalities.get("commander").getAsString());
    }

    public static Card buildCard(JsonObject cardData) {
        Card card = new Card();
        card.setName(cardData.get("name").getAsString());
        card.setManaCost(cardData.get("mana_cost").getAsString());
        card.setCmc(cardData.get("cmc").getAsInt());
        List<String> colors = jsonArrayToList(cardData.getAsJsonArray("colors"));
        List<String> colorIdentity = jsonArrayToList(cardData.getAsJsonArray("color_identity"));
        card.setColors(colors);
        card.setColorIdentity(colorIdentity);
        card.setType(cardData.get("type_line").getAsString());
        
        if (cardData.has("power") && cardData.has("toughness")) {
            card.setPower(cardData.get("power").getAsString());
            card.setToughness(cardData.get("toughness").getAsString());
        }

        if (cardData.has("oracle_text")) {
            card.setText(cardData.get("oracle_text").getAsString());
        } else {
            card.setText("");
        }

        card.setLegal(isLegalInCommander(cardData));
        JsonObject imageData = cardData.getAsJsonObject("image_uris");
        card.setImageUrlNormal(imageData.get("normal").getAsString());
        card.setImageUrlPNG(imageData.get("png").getAsString());

        return card;
    }
}
