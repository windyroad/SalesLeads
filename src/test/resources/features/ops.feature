Feature: Sales Leads DB Initialisation
    In order to simplify deployments
    As a ops team member
    I want the sales DB to be automatically initialised on engine start up
    

Scenario: New DB
    Given the sales leads DB has no tables
    When the sales leads DB is initialised
    Then the sales leads DB will have a SalesLeads table with the following columns
        | name        | type         |
        | FirstName   | VARCHAR(255) |
        | LastName    | VARCHAR(255) |
        | EMail       | VARCHAR(255) |
        | PhoneNumber | VARCHAR(255) |

Scenario: Existing DB
    Given the sales leads DB exists
    And the following contacts in the sales lead database:
        | First Name | Last Name | Email            | Phone Number |
        | Jane       | Doe       | jane@doe.com.au  | +61255551234 |
    When the sales leads DB is initialised
    Then the sales leads DB will have a SalesLeads table with the following columns
        | name        | type         |
        | FirstName   | VARCHAR(255) |
        | LastName    | VARCHAR(255) |
        | EMail       | VARCHAR(255) |
        | PhoneNumber | VARCHAR(255) |
    And the following records will exist in the sales lead database
        | First Name | Last Name | Email            | Phone Number | Date Added                      |
        | Jane       | Doe       | jane@doe.com.au  | +61255556789 | {{scenario.start}} till {{now}} |
