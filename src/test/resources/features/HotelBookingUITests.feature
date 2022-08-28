@FeatureAutomationTest @ui
Feature: Hotel booking reservation for Berns hotel
  As a customer
  I want to use the hotel reservation system
  So that I can book my hotel reservations

  Scenario Outline: User should be able to save the hotel booking with or without deposit amount.
    Given I search hotel for trip :  <hotelName>, <checkin_date>, <checkout_date>
    When I click on the book now button
    Then I should be able to enter details: <firstname>, <surename>, <phonenumber>, <email>, <address>, <city>, <postcode>, <card_number>, <expiry_date>, <cvv>
    Examples:
     | hotelName | firstname | surename | phonenumber | email | checkin_date | checkout_date | address | city   | postcode | card_number | expiry_date | cvv |
     | Berns     | Ganesh    | Ac       | 8850683131  | ganeshac@gmail.com | 25 | 28         | 123 st  | Delhi  | 100002   | 12334478222 | 08/24       | 124 |
