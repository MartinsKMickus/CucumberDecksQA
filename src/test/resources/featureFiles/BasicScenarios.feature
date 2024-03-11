Feature: Test basic scenarios of the service

  Scenario Outline: Check if card count in deck is correct after drawing X cards from API (give 3 examples)
    Given Such "<url>"
    Then I have "<cardCount>" cards

    Examples:
      | url                         | cardCount |
      | /api/deck/new/draw/?count=0 | 0         |
      | /api/deck/new/draw/?count=1 | 1         |
      | /api/deck/new/draw/?count=6 | 6         |

  Scenario: Create a new deck containing only Aces and validate that player can only get aces from it
    Given Only Aces
    When I draw "1" cards
    Then I have drawn "ACE" card value
    And I have "3" cards remaining
    When I draw "1" cards
    Then I have drawn "ACE" card value
    And I have "2" cards remaining
    When I draw "1" cards
    Then I have drawn "ACE" card value
    And I have "1" cards remaining
    When I draw "1" cards
    Then I have drawn "ACE" card value
    And I have "0" cards remaining

  #    Next scenario is tricky
  #    It seems that there is no possibility to draw cards from a bottom of deck (only piles)
  #    And it seems impossible to draw specific cards from a deck
  #    While not knowing for sure, there will be checked if after drawing 5 cards from new deck they are not there anymore
  Scenario: Draw 5 specific cards from a bottom of the deck and check that card amount changed and drawn cards are no longer in the deck
    Given New deck
    When I draw "5" cards
    Then I have "47" cards remaining
    When I write down card codes
    And I draw "47" cards
    Then I have "0" cards remaining
    And I do not have any written down cards
