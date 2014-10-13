Feature: Store Sales Lead Contact Information
    In order to share sales lead contact information with other staff
    As a slaes person
    I want to record sales leads details
    

Scenario: New Contact
    When the following contact is added to the sales lead database:
        | First Name | Last Name | Email            | Phone Number |
        | John       | Doe       | john@doe.com.au  | +61255556789 |
    Then the following records will exist in the sales lead database
        | First Name | Last Name | Email            | Phone Number | Date Added                 |
        | John       | Doe       | john@doe.com.au  | +61255556789 | {{scenario.start}}-{{now}} |

Scenario: Multiple Contacts
    Given the following contact is in the sales lead database:
        | First Name | Last Name | Email            | Phone Number |
        | Jane       | Doe       | jane@doe.com.au  | +61255551234 |
    When the following contact is added to the sales lead database:
        | First Name | Last Name | Email            | Phone Number |
        | John       | Doe       | john@doe.com.au  | +61255556789 |
    Then the following records will exist in the sales lead database
        | First Name | Last Name | Email            | Phone Number | Date Added                      |
        | Jane       | Doe       | jane@doe.com.au  | +61255556789 | {{scenario.start}} till {{now}} |
        | John       | Doe       | john@doe.com.au  | +61255556789 | {{scenario.start}} till {{now}} |

Scenario: New Contact - No email
    Given the following contact is in the sales lead database:
        | First Name | Last Name | Email            | Phone Number |
        | Jane       | Doe       | jane@doe.com.au  | +61255551234 |
    When the following contact is added to the sales lead database:
        | First Name | Last Name | Email            | Phone Number |
        | John       | Doe       |                  | +61255556789 |
    Then an error will be returned with the message "The email address cannot be blank"
    And the following records will exist in the sales lead database
        | First Name | Last Name | Email            | Phone Number | Date Added                      |
        | Jane       | Doe       | jane@doe.com.au  | +61255556789 | {{scenario.start}} till {{now}} |

Scenario: New Contact - Invalid email
    Given the following contact is in the sales lead database:
        | First Name | Last Name | Email            | Phone Number |
        | Jane       | Doe       | jane@doe.com.au  | +61255551234 |
    When the following contact is added to the sales lead database:
        | First Name | Last Name | Email            | Phone Number |
        | John       | Doe       | johndoe.com.au   | +61255556789 |
    Then an error will be returned with the message "The email address 'johndoe.com.au' is not a valid email address"
    And the following records will exist in the sales lead database
        | First Name | Last Name | Email            | Phone Number | Date Added                      |
        | Jane       | Doe       | jane@doe.com.au  | +61255556789 | {{scenario.start}} till {{now}} |
