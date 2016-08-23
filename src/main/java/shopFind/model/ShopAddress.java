package shopFind.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * Class that has ShopAddress 
 */
public class ShopAddress {
    @JsonProperty(value = "shop_number")
    private String shopNumber;

    public String getShopNumber() {
        return shopNumber;
    }

    public void setShopNumber(String shopNumber) {
        this.shopNumber = shopNumber;
    }

    public String getShopPostCode() {
        return shopPostCode;
    }

    public void setShopPostCode(String shopPostCode) {
        this.shopPostCode = shopPostCode;
    }

    @JsonProperty(value = "shop_postcode")
    private String shopPostCode;

}
