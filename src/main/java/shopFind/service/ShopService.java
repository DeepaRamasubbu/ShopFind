package shopFind.service;

import shopFind.model.ShopDetailsRequest;
import shopFind.model.ShopDetailsResponse;

/*
 * Interface that provides method for various operation for shop 
 */
public interface ShopService {

    /*
     * Service method that lets add the shop details
     */
    public ShopDetailsResponse addShop(ShopDetailsRequest request);

    /*
     * Service method that gets the shop details
     */
    public ShopDetailsResponse getShopDetails(Double latitude, Double longitude);

}
