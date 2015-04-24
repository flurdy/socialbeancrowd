
Social Bean Crowd
======
    
* https://github.com/flurdy/socialbeancrowd


A command line social messaging and feed framework.

* Members can post messages
* Follow other friends
* Read a member's messages
* Read a member's 'wall', a feed of messages
* * Wall includes the member's own messages
* * And messages from the friends they follow
* * Messages are sorted by time and date


Prerequisites
-----

* Java 1.7
* Maven

* Or Docker


Build Application
---

 `mvn package` 

Or with Docker
   
 `docker build -t flurdy/socialbeancrowd:0.1 .`


Run Application
---

Start application locally with

   `./socialbeancrowd`

OR

   `mvn exec:java`

Or with Docker

   `docker run -ti --rm flurdy/socialbeancrowd:0.1`


Tests
----

   `mvn test`

   `mvn integration-test`


Examples
-----

Post Messages
-----

   Alice -> Hello world

   Peter -> Hello me


Follow Friends
-----

   Alice follows Peter


Read Alice's Messages
-----

   Alice 

   > Hello World (10 seconds ago)


Read Wall
-----

   Alice wall

   > Peter - Hello (1 minute ago)

   > Alice - Hello World (2 minutes ago)


Assumptions
-----

* Happy sunny day input only
* Upper & lower case irrelevant
* No emoji or odd UTF encoding
* No multiline input
* Names can not have white space characters in them


Improvements
----

* Akka if needs to scale horizontally and reactive
* Java 8 or Akka streams if posts/wall are large 
* Separate apps for input layer, reading and member posts persistance
* Test SocialLauncher and Inputs more
* Dependency injection context like Spring or Guice
* Etc

There is a Scala alternative at https://github.com/flurdy/socialcrowd

