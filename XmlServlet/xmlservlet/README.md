# XMLSERVLET Showcase #

This is a showcase on how to implement a xml web service with Spring. It uses a simple servlet processing a request by retrieving the target service and method from the message.
This is all done by extracting the service and method element from the message and to perform a lookup in the Spring application context.
Application uses an in memory H2 database to store data.

Sample messages can be found in the resources dir.

Atm only create customer method is implemented in BasicCustomerService.

To test this app, run with JDK 11 and use Postman to send message to localhost:8080/xmlservlet/service