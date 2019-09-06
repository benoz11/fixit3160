# fixit3160

IT support ticketing software

Project Implementation for University of Newcastle SENG3160 2019

Benjamin McDonnell, Jordan Maddock, Matthew Rudge, Kundayi Sitole

Project now live on heroku at https://fixit3160.herokuapp.com

Project hosted on github at https://github.com/benoz11/fixit3160

Compile & Run locally with `mvn spring-boot:run` then navigate to http://localhost:8080 to test

### Environment Description

Project is hosted live on the cloud using Heroku, Database is linked into Heroku using Postgresql

Project is written in Java using Spring-boot, Hibernate, and Spring Security

Project is compiled using maven - see pom.xml for dependencies

### Roles
Manager			-	The admin/manager

Caseworker		-	The person who finds and posts solutions to tickets

Regular			-	The user who is using the system to report a problem that they need help with

User			-	An alias for ANY user within the system - Not a real role

### Ticket States / Lifecycle
Open 			- Newly posted, not assigned to anyone

In Progress		- Manager has assigned the ticket to a Caseworkers

Resolved		- Manager or Caseworker has posted a solution to the problem - Users can Reject the solution to change the ticket state back to In Progress
					
Completed		- User has accepted the solution - Ticket cannot be edited at or after this stage

Knowledge Base	- Manager believes this solution might be useful to others, makes it public

Closed			- Manager has locked the ticket as it has been deemed unworthy

### Software Major Functions

Users can log in to the system using one of the given user/pass combinations (see users file - not present on github for security)

Users can create, view, edit their own help tickets describing a problem they want help with

Users can create, view, edit, delete their own comments on tickets

Managers can edit, delete all other users comments

Managers can edit, delete all other users tickets

Managers can assign these tickets to Caseworkers who will find a solution to the problem and post it as a comment under the ticketing

Caseworkers can only see tickets they are assigned to, Regular users can only see tickets they have posted

Users can comment on Tickets

Managers can view statistics relevant to the system

Managers can create, read, edit, delete Users within the system

Tickets and Users can be searched using the search box, or sorted by clicking the table headers

Users can edit their own profiles

Users can view Knowledge Base articles

users can log out

System contains security measures so that only logged in users can see content

System contains security measures so that users can only see content specific to their account and access role

### Git / Local steps

Clone with `git clone https://github.com/benoz11/fixit3160.git`


Create a git remote with `git remote add fixit3160 https://github.com/benoz11/fixit3160.git`


Pull most revent version with `git pull fixit3160 master`


Compile and run locally with `mvn spring-boot:run` then connect to http://localhost:8080 to test


Please test thoroughly before proceeding...


Add your changes to local git with `git add .`


Commit your changes to local git with `git commit -m "A meaningful messsage"`


Push your changes to the git repository with `git push fixit3160 master`


Tada!
