# QA Task for "Deck of Cards API"
This setup implements usage of Cucumber, Java, Rest-Assured and Gradle
to test following scenarios:
- Check if card count in deck is correct after drawing X cards from it (give 3 examples)
- Create a new deck containing only Aces and validate that player can only get aces from it
- Draw 5 specific cards from a bottom of the deck and check that card amount changed and drawn cards are no longer in the deck

After tests Cucumber report is generated and can be used.

## To launch the tests:
1. Ensure that environment is setup (_created using OpenJDK-21.0.2_)
2. Launch either using IDE or command line `./gradlew test`
3. Report is generated in report folder