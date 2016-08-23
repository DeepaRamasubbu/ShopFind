# ShopFind
ShopFind is the repository which has two apis for getting shop details based on the latitude and longitude provided.

One is POST , which takes "/addShop" , which takes request of type ShopDetailsRequest , containing shopName , shopAddress and latitude and longitude.

Sample JSON request
{  
   "shop_name":"Phoenix+Mall",
   "shop_address":{  
      "shop_number":"Velachery",
      "shop_postcode":"600042"
   }
}

Sample JSON response
{
    "latitiude": 12.9913012,
    "longitude": 80.216486,
    "response": {
        "errorCode": true
    },
    "links": [
        {
            "rel": "self",
            "href": "http://localhost:8080/addShop"
        },
        {
            "rel": "get",
            "href": "http://localhost:8080/getShop/12.9913012/80.216486"
        }
    ]
}



The other API is a GET call , which when given latitude and longitude as requestparam , returns the shopDetails .

Sample JSON request

http://localhost:8080/12.9913012/80.216486

{
    "shopName": "Velachery Road",
    "shopAddress": {
        "shop_number": "142",
        "shop_postcode": "600042"
    },
    "latitiude": 12.9914031,
    "longitude": 80.21649409999999,
    "response": {
        "errorCode": true
    },
    "links": [
        {
            "rel": "self",
            "href": "http://localhost:8080/addShop"
        },
        {
            "rel": "get",
            "href": "http://localhost:8080/getShop/12.9914031/80.21649409999999"
        }
    ]
}

How To Run

This project is MAven project , after importing it to workspace , start the server by

java -jar target/shopFind-0.1.0.jar

and then hit the request from Postman

Things To Do

1) Unit test is not done
2) DB implementation is not done yet.

