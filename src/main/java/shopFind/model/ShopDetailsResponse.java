package shopFind.model;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/*
 * Class that maps ShopDetailsResponse
 */
@JsonInclude(Include.NON_NULL)
public class ShopDetailsResponse extends ResourceSupport {
    private String shopName;
    private ShopAddress shopAddress;
    private double latitiude;
    private double longitude;
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public ShopAddress getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(ShopAddress shopAddress) {
        this.shopAddress = shopAddress;
    }

    public double getLatitiude() {
        return latitiude;
    }

    public void setLatitiude(double d) {
        this.latitiude = d;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
