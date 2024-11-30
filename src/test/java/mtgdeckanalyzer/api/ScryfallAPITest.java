package mtgdeckanalyzer.api;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import mtgdeckanalyzer.model.Card;

class ScryfallAPITest {

    @Test
    void testBasicCardFromAPI() throws Exception {
        // Get Lightning Bolt data from API
        Card testCard = ScryfallAPI.getCard("Lightning Bolt");
        
        // Test basic properties
        assertEquals("Lightning Bolt", testCard.getName());
        assertEquals("{R}", testCard.getManaCost());
        assertEquals(1, testCard.getCmc());
    }
}