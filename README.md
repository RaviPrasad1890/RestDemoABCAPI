# RestDemoABCAPI

This application will run on port 8080 by default.

Springboot and in memory H2 database has been used internally.

Users:
userId: admin
password: admin

and

userId: user
password: user

About: Application can be used to manage customer of ABC bank and generate token and assign token to customers. 
To execute this application we need to run TokenServiceApplication as a normal Java Application.


Note: By default, three customers are already loaded with id 10001,10002 and 10003 
among which 10001 is a primary customer.

Simillarly, two prime counters and two non prime counters will load at startup. Prime counters will be assigned to 
prime customers only.

>>PATH= "/customer" ,Type= GET
To get all customers details. 

>>PATH= "/customer/{customerId}", Type= GET
To get specific customer details

>>PATH="/customer" Type= POST
To Create a specific customer.
BODY:
{   
    "customerName": "CustomerFour",
    "primaryCustomer": true
}


>>PATH="/customer/token", Type: GET
To get all token details

>>PATH="/customer/{customerId}/token", Type:POST
To generate a token for customer

>>PATH="/customer/{customerId}/token/{tokenId}", Type=GET
To get counter details assigned to token.

>>PATH="/customer/{customerId}/token/{tokenId}/counter", Type=PUT
To assign a specific token to a counter.
Note: Token of primary customer will be assigned to primary counters and for secondary customers will be assigned 
to secondary counters.
If all counters will have active tokens assigned to them, then token will not be assigned to any counter.

>>PATH="/customer/counter" Type: GET
To get all counter details and observe token status currently assigned to those counter.

>>PATH="/customer/token/counter/{counterId}" Type:PUT
To mark a token status complete of a specific counter.
Note: Only admin can mark a token complete.




