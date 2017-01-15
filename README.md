# WeatherTests
A suite of Cucumber-JVM style tests against a weather application


Details on the solution for testing the weather application.

The test suite has been created using Java and Cucumber-JVM with WebDriver. Maven is the build tool, given this is usually part of a CI build.

Firstly to run the test suite, from the project directory with the pom file, enter: mvn test
[Please ensure Maven is on your path]

Some further observations:

Not convinced the Windspeed / Direction are accurate in summary on the application (e.g. Weds 5km wind actual 1km, Thurs 8km actual 2km). If I understand
correctly, then the 'Dominant' item would be the one with the maximum value. I would question this to understand further because the application looks
to have some bugs.

In each case, the data values need to be validated against either the json file or ultimately the website. I would create some json parsing
utility to take the data directly from the file. Possibly using JSONPath or maybe bind the file to a class using the Jackson libraries to marshall and 
unmarshall - there was no time to do this.

I would have further added some error handling to ensure null pointers or test failures as a side-effect of entering values into the feature file that
have not been catered for.

The data on Weather site for 3 hourly time is one hour different  -> question this? BST perhaps?

I needed to use a Thread sleep rather than webdriverwait where the daily details expand and contract. I would investigate further for a better
solution than a 'hard' wait time.

I would possibly implement Spring framework for passing beans around different steps / features, depending on complexity. Passing a Spring bean
around and across different scenarios can be useful where the complexity merits the benefits.

Conditions icon missing from my summary. Requires more thought on an elegant way to grab this element.

Wind direction is difficult to interpret from one small arrow to the next. (accessibility issue)

The rainfall values seem incorrect (for example, from the JSON file 6:00 on Wed21 the rain data is 0.03 mm. Somehow this become 1mm on the detail row.)
 
Ideally I would add some unit tests to keep my own code clean and to ensure the tests themselves do not break. 
