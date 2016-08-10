package hello.impl;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import hello.ShopFindController;
import hello.model.Greeting;
import hello.model.ShopDetailsRequest;
import hello.model.ShopDetailsResponse;
import hello.service.ShopService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Named
public class ShopFindControllerImpl implements ShopFindController {

    @Inject
    private ShopService shopService;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    /*
     * Method that implements the controllder class addShop , calls the service addShop to add teh shop details
     * @param ShopDetailsRequest - incoming request
     * @return instance of ResponseEntity 
     */
    @Override
    public ResponseEntity<ShopDetailsResponse> addShop(@Valid @RequestBody ShopDetailsRequest request) {
        ShopDetailsResponse response = null;
        response = shopService.addShop(request);
        return buildFinalResponse(response);

    }

    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @Override
    public String index() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<ShopDetailsResponse> getShop(@PathVariable("latitude") Double latitude,
            @PathVariable("longitude") Double longitude) {
        System.out.println("latitude = " + latitude.toString());
        System.out.println("longitude = " + longitude.doubleValue());
        ShopDetailsResponse response = null;
        response = shopService.getShopDetails(latitude, longitude);
        return buildFinalResponse(response);
    }

    private ResponseEntity<ShopDetailsResponse> buildFinalResponse(ShopDetailsResponse response) {

        if (response != null) {
            if (response.getResponse().getErrorCode()) {

                response.add(linkTo(ShopFindController.class).slash("addShop").withRel("self"));
                try {
                    StringBuilder tempURL = new StringBuilder();
                    tempURL.append("getShop?latitude=" + response.getLatitiude());
                    tempURL.append(URLEncoder.encode("&", "UTF-8"));
                    tempURL.append("longitude=" + response.getLongitude());
                    System.out.println("tempurl = " + tempURL);
                    response.add(linkTo(ShopFindController.class).slash(tempURL).withRel("get"));
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                return new ResponseEntity<ShopDetailsResponse>(response, HttpStatus.CREATED);
            } else
                return new ResponseEntity<ShopDetailsResponse>(response, HttpStatus.OK);
        }
        return null;
    }
}
