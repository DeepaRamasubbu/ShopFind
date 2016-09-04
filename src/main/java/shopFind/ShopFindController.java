package shopFind;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import shopFind.model.ShopDetailsRequest;
import shopFind.model.ShopDetailsResponse;

/*
 * Controller for ShopFind , providing the end point for adding shop details and getting the shop details for a particular latitude and longitude
 */
@RestController
@RequestMapping("/")
public interface ShopFindController {

    @RequestMapping("/")
    public String index();

    /*
     * POST method call , that takes a request containing shop details 
     */
    @RequestMapping(method = RequestMethod.POST, value = "/addShop")
    public ResponseEntity<ShopDetailsResponse> addShop(ShopDetailsRequest request) throws IOException;

    /*
     * GET method call, that gives the shop name and details for a given latitude and longitude
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getShop/{latitude:.+}/{longitude:.+}")
    public ResponseEntity<ShopDetailsResponse> getShop(Double latitude, Double longitude) throws IOException;

}
