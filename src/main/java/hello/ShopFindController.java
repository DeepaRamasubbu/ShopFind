package hello;

import hello.model.Greeting;
import hello.model.ShopDetailsRequest;
import hello.model.ShopDetailsResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public interface ShopFindController {

    @RequestMapping("/")
    public String index();

    @RequestMapping(method = RequestMethod.POST, value = "/addShop")
    public ResponseEntity<ShopDetailsResponse> addShop(ShopDetailsRequest request);

    @RequestMapping(method = RequestMethod.GET, value = "/getShop/{latitude:.+}/{longitude:.+}")
    public ResponseEntity<ShopDetailsResponse> getShop(Double latitude, Double longitude);

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name);

}
