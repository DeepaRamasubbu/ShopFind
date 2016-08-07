package hello.service;
import hello.model.ShopDetailsRequest;
import hello.model.ShopDetailsResponse;

public interface ShopService {
	
	public ShopDetailsResponse addShop (ShopDetailsRequest request);
	
	public ShopDetailsResponse getShopDetails(String latitude, String longitude);

}