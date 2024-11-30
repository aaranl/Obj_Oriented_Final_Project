package mtgdeckanalyzer.api;

import java.net.URLEncoder;
import java.io.IOException;
import com.google.gson.*;
import mtgdeckanalyzer.model.Card;
import mtgdeckanalyzer.builder.CardBuilder;

public class ScryfallAPI {
    private static final String API_URL = "https://api.scryfall.com/cards/named?exact=";

    public static Card getCard(String cardName) throws IOException {
        JsonObject cardData = getCardData(cardName);
        return CardBuilder.buildCard(cardData);
    }

    private static JsonObject getCardData(String cardName) throws IOException {
        String url = API_URL + URLEncoder.encode(cardName, "UTF-8");
        String jsonResponse = HttpRequestHelper.sendGet(url);

        @SuppressWarnings("deprecation")
        JsonParser parser = new JsonParser();
        @SuppressWarnings("deprecation")
        JsonObject cardData = parser.parse(jsonResponse).getAsJsonObject();

        return cardData;
    }
}