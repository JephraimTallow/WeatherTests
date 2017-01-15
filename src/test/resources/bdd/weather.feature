
Feature: Testing weather homepage
	A selection of scenarios for some basic homepage validation
	
#Scenario: Ensure the case is handled where the city is not available
#Given The weather homepage has loaded successfully
#And I Change the city name to "SomethingNonExistent"
#Then An appropriate message is displayed to advise the user accordingly

Scenario: Ensure the daily details are shown when the row is selected
Given The weather homepage has loaded successfully
And I Change the city name to "Edinburgh"
When I select summary row "one"
Then The weather intra-day details are shown for that day

Scenario: Ensure the daily details are hidden when the row is selected a second time
Given The weather homepage has loaded successfully
And I Change the city name to "Edinburgh"
And I select summary row "one"
And The weather intra-day details are shown for that day
When I select summary row "one"
Then The weather intra-day details are hidden for that day

Scenario: Verify the daily summary values are correct for Wed21
Given The weather homepage has loaded successfully
And I Change the city name to "Edinburgh"
And The weather service provides the following values:
	| Time | MinTemp | MaxTemp | WindSpeed | Rainfall | Pressure |
	| 0100 | 4.02    | 4.02    | 1.16      | 0        | 1006.89  |
	| 0400 | 5.56    | 5.56    | 1.32      | 0        | 1005.64  |
	| 0700 | 6.08    | 6.08    | 2.36      | 0.03     | 1004.73  |
	| 1000 | 12.62   | 12.62   | 4.76      | 0.045    | 1004.42  |
	| 1300 | 15.54   | 15.54   | 5.47      | 0.01     | 1003.41  |
	| 1600 | 16.44   | 16.44   | 5.51      | 0.01     | 1002.31  |
	| 1900 | 14.79   | 14.79   | 5.01      | 0.02     | 1002.25  |
	| 2200 | 13.2    | 13.2    | 3.58      | 0.03     | 1002.83  |	
When I select summary row "two"
Then The weather daily summary is correct on row "two" for date "Wed21"

Scenario: Verify the daily summary values are correct for Tue20
Given The weather homepage has loaded successfully
And I Change the city name to "Edinburgh"
And The weather service provides the following values:
	| Time | MinTemp | MaxTemp | WindSpeed | Rainfall | Pressure |
	| 1300 | 14.87   | 18.4    | 1.87      | 0        | 1008.47  |
	| 1600 | 15.14   | 17.79   | 1.82      | 0        | 1007.55  |
	| 1900 | 12.33   | 14.1    | 1.16      | 0        | 1007.15  |
	| 2200 | 6.23    | 7.11    | 1.07      | 0        | 1007.32  |
When I select summary row "one"
Then The weather daily summary is correct on row "one" for date "Tue20"

Scenario: Verify the three hour daily detail values are correct
Given The weather homepage has loaded successfully
And I Change the city name to "Edinburgh"
And The weather service provides the following values:
	| Time | MinTemp | MaxTemp | WindSpeed | Rainfall | Pressure |
	| 1300 | 14.87   | 18.4    | 1.87      | 0        | 1008.47  |
	| 1600 | 15.14   | 17.79   | 1.82      | 0        | 1007.55  |
	| 1900 | 12.33   | 14.1    | 1.16      | 0        | 1007.15  |
	| 2200 | 6.23    | 7.11    | 1.07      | 0        | 1007.32  |
When I select summary row "one"
Then The daily details displayed are correct for the provided values
	
