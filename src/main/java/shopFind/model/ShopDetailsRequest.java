package shopFind.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * Class that maps ShopDetailsRequest 
 */
public class ShopDetailsRequest {

    @JsonProperty(value = "shop_name")
    private String shopName;

    @JsonProperty(value = "shop_address")
    private ShopAddress shopAddress;

    @JsonProperty(value = "latitude")
    private int latitiude;

    @JsonProperty(value = "longitude")
    private int longitude;

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

    public int getLatitiude() {
        return latitiude;
    }

    public void setLatitiude(int latitiude) {
        this.latitiude = latitiude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }
}
