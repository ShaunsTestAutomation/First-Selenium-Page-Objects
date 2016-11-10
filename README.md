# First-Selenium-Page-Objects
Showcase maven POM to build simple java selenium tests in page object design pattern.  
Based upon login example from "the-internet" by Dave Heaffner.

#This project:-<BR>
  1) uses Maven to build and execute.<BR>
  2) is written in Java8.<BR>
  3) depends upon Selenium version 3<BR>
  4) tests http://the-internet.herokuapp.com/login <BR>
  
#Code:<BR>
  i) The code uses the Page Object design pattern.<BR>
  ii)Implements a class specifically for handling Selenium explicit wait statments (SeleniumTimers.java)<BR>

#Usage:
mvn clean test

#To Do list:
1)  extend the Base.java class to allow flexible calling of different browser versions and O/S's.<BR>
    The current project only uses a single hard coded browser at a time (currently Firefox, or IE)<BR>
2)  Add screen shots for test evidence<BR>
3)  Add logging class for standardized test evidencing<BR>
4)  Add data driven test class to take input from external resource file (csv or xls)<BR>
5)  Parallelize the test execution.<BR>
6)  Add more pages to test via more page objects<BR>
