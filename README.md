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

Simillarly, two primary counters and two non primary counters will load at startup. Primary counters will be assigned to 
prime customers only.

>>PATH= "/customer" ,Type= GET
To get all customers details. 

Eg: [
    {
        "customerId": 10001,
        "customerName": "CustomerOne",
        "primaryCustomer": true
    },
    {
        "customerId": 10002,
        "customerName": "CustomerTwo",
        "primaryCustomer": false
    },
    {
        "customerId": 10003,
        "customerName": "CustomerThree",
        "primaryCustomer": false
    }
]






>>PATH= "/customer/{customerId}", Type= GET
To get specific customer details

Eg:

{
    "customerId": 10001,
    "customerName": "CustomerOne",
    "primaryCustomer": true
}



>>PATH="/customer" Type= POST
To Create a specific customer.

Request Body:
{   
    "customerName": "CustomerFour",
    "primaryCustomer": true
}




>>PATH="/customer/{customerId}/token", Type:POST
To generate a token for customer

{
    "tokenNumber": 1,
    "tokenActive": true,
    "customer": {
        "customerId": 10001,
        "customerName": "CustomerOne",
        "primaryCustomer": true
    }
}


>>PATH="/customer/{customerId}/token/{tokenId}/counter", Type=PUT
To assign a specific token to a counter.
Note: Token of primary customer will be assigned to primary counters and for secondary customers will be assigned 
to secondary counters.
If all counters will have active tokens assigned to them, then token will not be assigned to any counter.

{
    "counterNumber": 1,
    "primaryCounter": true,
    "token": {
        "tokenNumber": 1,
        "tokenActive": true,
        "customer": {
            "customerId": 10001,
            "customerName": "CustomerOne",
            "primaryCustomer": true
        }
    }
}


>>PATH= "/customer/token" ,Type= GET
To get all token details. 

Eg: 

[
    {
        "tokenNumber": 1,
        "tokenActive": true,
        "customer": {
            "customerId": 10001,
            "customerName": "CustomerOne",
            "primaryCustomer": true
        }
    }
]



>>PATH= "/customer/token/counter" ,Type= GET
To get all counter details. 

Eg:

[
    {
        "counterNumber": 1,
        "primaryCounter": true,
        "token": {
            "tokenNumber": 1,
            "tokenActive": true,
            "customer": {
                "customerId": 10001,
                "customerName": "CustomerOne",
                "primaryCustomer": true
            }
        }
    },
    {
        "counterNumber": 2,
        "primaryCounter": true,
        "token": null
    },
    {
        "counterNumber": 3,
        "primaryCounter": false,
        "token": null
    },
    {
        "counterNumber": 4,
        "primaryCounter": false,
        "token": null
    }
]




>>PATH="/customer/token/{tokenId}" Type:PUT
To mark a token status complete of a specific counter.
Note: Only admin can mark a token complete.

Eg:

{
    "tokenNumber": 1,
    "tokenActive": false,
    "customer": {
        "customerId": 10001,
        "customerName": "CustomerOne",
        "primaryCustomer": true
    }
}


