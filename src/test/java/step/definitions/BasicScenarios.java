package step.definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class BasicScenarios {
    private final String API_URL;
    private final String ONLY_ACES_ENDPOINT;
    private final String NEW_DECK_ENDPOINT;
    {
        API_URL = "https://deckofcardsapi.com/";
        ONLY_ACES_ENDPOINT = "api/deck/new/shuffle/?cards=AS,AD,AC,AH";
        NEW_DECK_ENDPOINT = "api/deck/new/";
    }
    private final List<String> cardList = new ArrayList<>();

    private Response response;

    @Given("Such {string}")
    public void such(String url) throws URISyntaxException {
        RestAssured.baseURI = API_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.when().get(new URI(url));
    }

    @Then("I have {string} cards")
    public void iHaveCards(String cardCount) {
        List<String> cards = response.then().extract().path("cards");
        Assert.assertEquals(Integer.parseInt(cardCount), cards.size());
    }

    @Given("Only Aces")
    public void onlyAces() throws URISyntaxException {
        such(ONLY_ACES_ENDPOINT);
    }

    @When("I draw {string} cards")
    public void iDrawCards(String cardCount) throws URISyntaxException {
        String deckID = response.then().extract().path("deck_id");
        such("api/deck/" + deckID + "/draw/?count=" + cardCount);
    }

    @Then("I have drawn {string} card value")
    public void iHaveDrawnCardValue(String cardValue) {
        ArrayList<HashMap<String, Object>> cards = response.then().extract().path("cards");
        Assert.assertEquals(cardValue, cards.get(0).get("value"));
        /* This function assumes that there should be only one card drawn*/
        Assert.assertEquals(1, cards.size());
    }

    @And("I have {string} cards remaining")
    public void iHaveCardsRemaining(String cardCount) {
        Assert.assertEquals(cardCount, response.then().extract().path("remaining").toString());
    }

    @Given("New deck")
    public void newDeck() throws URISyntaxException {
        such(NEW_DECK_ENDPOINT);
    }

    @When("I write down card codes")
    public void iWriteDownCardCodes() {
        ArrayList<HashMap<String, Object>> cards = response.then().extract().path("cards");
        for (HashMap<String, Object> card : cards) {
            cardList.add(card.get("code").toString());
        }
    }

    @And("I do not have any written down cards")
    public void iDoNotHaveAnyWrittenDownCards() {
        ArrayList<HashMap<String, Object>> cards = response.then().extract().path("cards");
        for (HashMap<String, Object> card : cards) {
            Assert.assertFalse(cardList.contains(card.get("code").toString()));
        }
    }
}
