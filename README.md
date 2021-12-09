## Introduction
There is a new mobile game that starts with consecutively numbered clouds. Some of the clouds are thunderheads and others are cumulus. The player can jump on any cumulus cloud having a number that is equal to the number of the current cloud plus  or . The player must avoid the thunderheads. Determine the minimum number of jumps it will take to jump from the starting postion to the last cloud. It is always possible to win the game.

Given the REST application for jumping on the clouds (JOTC) problem.
The application :
- respond to JOTC requests accepting input and producing the proper output
- store each request being made as well as its result
- Take userName as part of request and store it along with response

Administrators can request information about the JOTC requests
- add endpoints for querying how many requests were processed in total and how many per user
- add an endpoint to remove all requests for a certain user


Clouds String Constraints:
	- request must have only numeric value 0 and 1.
	- request size must be between 2-100.
	- request must start and end with 0.
	- request can not have two consecutive 1.
	
		
** Open api specification can be found in below path after running the application **
		url : http://localhost:8080/swagger-ui.html
 
## Getting Started

###Installation Process
Ready with latest version of GIT, maven, Java 8+.

### Latest release  
0.0.1

### run application on local environment

	- Clone jotc-restapp repository using below url on your command line or Git Bash.
		clone url : https://github.com/shrkntshrivastava/jotc-restapp.git
	- go inside coffeeshop project
		cd jotc-restapp
	- install maven project
		mvn clean install
	- go to local maven repository
		 \.m2\repository\com\assessment\jotc-restapp\0.0.1-SNAPSHOT
	- run built jar file
		java -jar jotc-restapp-0.0.1-SNAPSHOT.jar
	- Check command line console for application started message
		ex : Started JotcRestappApplication  ...
	- Go to any web browser or Rest client(like Postman)
		Access api as mentioned in Introduction tag of README.md

## Contribute
 * API specification can be improved by adding schemas and error scenarios.
 * Authentication can be added for User and Administrator, to give 
 * Feel free to provide any feedback to improve the application.
 