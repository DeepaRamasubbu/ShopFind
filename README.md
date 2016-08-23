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

The other API is a GET call , which when given latitude and longitude as requestparam , returns the shopDetails .

Sample JSON request

http://localhost:8080/12.9913012/80.216486

