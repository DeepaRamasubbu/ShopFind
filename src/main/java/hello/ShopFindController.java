package hello;

import hello.model.Greeting;
import hello.model.ShopDetailsRequest;
import hello.model.ShopDetailsResponse;
import hello.service.ShopService;

import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class ShopFindController {
	
	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    @Inject
    private ShopService shopService;
    
	@RequestMapping("/")
    public String index() {
        return "Greetings from Shop Find !";
    }
	

    @RequestMapping(method=RequestMethod.POST,value="/addShop")
    public ResponseEntity<ShopDetailsResponse> addShop(ShopDetailsRequest request){
    	ShopDetailsResponse response = null;
    	response = shopService.addShop(request);
    	return new ResponseEntity<ShopDetailsResponse>(response,HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/{latitude}/{longitude}", method=RequestMethod.GET)
    public ResponseEntity<ShopDetailsResponse> getShop(String latitude,String longitude){
    	return null;
    	
    }
    
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

}
