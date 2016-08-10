package hello.service;

import hello.model.ShopDetailsRequest;
import hello.model.ShopDetailsResponse;

import java.math.BigInteger;

public interface ShopService {

    public ShopDetailsResponse addShop(ShopDetailsRequest request);

    public ShopDetailsResponse getShopDetails(BigInteger latitude, BigInteger longitude);

}
