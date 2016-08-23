package shopFind.impl;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import shopFind.ShopFindController;
import shopFind.model.ShopDetailsRequest;
import shopFind.model.ShopDetailsResponse;
import shopFind.service.ShopService;

/*
 * Implementation class of ShopFindController
 */
@Named
public class ShopFindControllerImpl implements ShopFindController {

    @Inject
    private ShopService shopService;

    private static final String template = "shopFind, %s!";
    private final AtomicLong counter = new AtomicLong();

    /*
     * Method that implements the controller class addShop , calls the service addShop to add teh shop details
     * @param ShopDetailsRequest - incoming request
     * @return instance of ResponseEntity 
     */
    @Override
    public ResponseEntity<ShopDetailsResponse> addShop(@Valid @RequestBody ShopDetailsRequest request) {
        ShopDetailsResponse response = null;
        response = shopService.addShop(request);
        return buildFinalResponse(response);

    }

    /*
     * Method that implements controller class getShop
     * @param Double latitude and longitude
     * 
     */
    @Override
    public ResponseEntity<ShopDetailsResponse> getShop(@PathVariable("latitude") Double latitude,
            @PathVariable("longitude") Double longitude) {
        ShopDetailsResponse response = null;
        response = shopService.getShopDetails(latitude, longitude);
        return buildFinalResponse(response);
    }

    private ResponseEntity<ShopDetailsResponse> buildFinalResponse(ShopDetailsResponse response) {

        if (response != null) {
            if (response.getResponse().getErrorCode()) {

                response.add(linkTo(ShopFindController.class).slash("addShop").withRel("self"));
                response.add(linkTo(ShopFindController.class).slash("getShop").slash(response.getLatitiude())
                        .slash(response.getLongitude()).withRel("get"));

                return new ResponseEntity<ShopDetailsResponse>(response, HttpStatus.CREATED);
            } else
                return new ResponseEntity<ShopDetailsResponse>(response, HttpStatus.OK);
        }
        return null;
    }

    @Override
    public String index() {
        // TODO Auto-generated method stub
        return null;
    }
}
