package shopFind.service;

import shopFind.model.ShopDetailsRequest;
import shopFind.model.ShopDetailsResponse;

public interface ShopService {

    public ShopDetailsResponse addShop(ShopDetailsRequest request);

    public ShopDetailsResponse getShopDetails(Double latitude, Double longitude);

}
