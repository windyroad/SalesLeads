Feature: Proivide Version Information 
    In order to cleary demonstrate no-outage deployments
    As an application manager
    I want the sales leads application to show it's version information
    

Scenario: Display Version
    Given the current binary version is 2.9
    And the current configuration version is 2.9.3
    When the application version is requested
    Then the binary version 2.9 will be reported
    And the configuration version 2.9.3 will be reported

