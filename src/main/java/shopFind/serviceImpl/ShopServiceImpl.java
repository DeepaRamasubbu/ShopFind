package shopFind.serviceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.inject.Named;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import shopFind.model.AddressComponent;
import shopFind.model.GeoCode;
import shopFind.model.GeoCodeResponse;
import shopFind.model.Response;
import shopFind.model.ShopAddress;
import shopFind.model.ShopDetailsRequest;
import shopFind.model.ShopDetailsResponse;
import shopFind.service.ShopService;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Named
public class ShopServiceImpl implements ShopService {

    private static final String keyValue = "AIzaSyCNL-l5qRr1zNaLooMVeeBSf2Vg-N_fJH0";
    private final String USER_AGENT = "Mozilla/5.0";
    private final String baseURL = "https://maps.googleapis.com/maps/api/geocode/json?";
    private final String address = "address=";
    private final String latLang = "latlng=";
    private final String key = "&key=";
    private static final Logger LOGGER = LoggerFactory.getLogger(ShopServiceImpl.class);

    /*
     * This method, gets the shop details and fetches latitude and longitude 
     * @param ShopDetailsRequest - request containing shop details
     * @return ShopDetailsResponse - response containing latitude and longitude
     */
    @Override
    public ShopDetailsResponse addShop(ShopDetailsRequest request) {

        Response response = null;
        String geoCodingRequest = null;
        String geoResponse = null;
        response = validateShopDetails(request);
        if (response == null) {
            try {
                geoCodingRequest = buildRequestForGeocoding(request);
                geoResponse = callGeocoding(geoCodingRequest);
                return buildResponse(geoResponse);
            } catch (Exception e) {
                Throwable th = ExceptionUtils.getRootCause(e);
                LOGGER.error("Exception occured while calling GeoCoding API");
                Response res = new Response();
                res.setErrorCode(Boolean.FALSE);
                res.setErrorMessage("Something happened while calling GeoCoding API");
                ShopDetailsResponse shopResponse = new ShopDetailsResponse();
                shopResponse.setResponse(res);
                return shopResponse;
            }
        } else {
            ShopDetailsResponse shopResponse = new ShopDetailsResponse();
            shopResponse.setResponse(response);
            return shopResponse;
        }

    }

    /*
     * Method that gets the shop details
     * @param latitude.longitude
     * @response ShopDetailsResponse
     */
    @Override
    public ShopDetailsResponse getShopDetails(Double latitude, Double longitude) {
        String reverseGeoCodingRequest = null;
        String reverseGeoCodingResponse = null;
        reverseGeoCodingRequest = buildRequestForReverseGeoCoding(latitude, longitude);
        try {
            reverseGeoCodingResponse = callReverseGeoCoding(reverseGeoCodingRequest);
            return buildReverseResponse(reverseGeoCodingResponse);
        } catch (IOException e) {
            Throwable th = ExceptionUtils.getRootCause(e);
            LOGGER.error("Exception occured while calling GeoCoding API");
            Response res = new Response();
            res.setErrorCode(Boolean.FALSE);
            res.setErrorMessage("Something happened while calling GeoCoding API");
            ShopDetailsResponse shopResponse = new ShopDetailsResponse();
            shopResponse.setResponse(res);
            return shopResponse;
        }

    }

    private Response validateShopDetails(ShopDetailsRequest request) {
        Response response = null;
        if (StringUtils.isEmpty(request.getShopAddress())) {
            response = new Response();
            response.setErrorCode(Boolean.FALSE);
            response.setErrorMessage("Empty shop address");
            return response;
        }
        return response;
    }

    private String buildRequestForGeocoding(ShopDetailsRequest request) {

        StringBuilder url = new StringBuilder();
        url.append(baseURL);
        url.append(address);
        url.append(request.getShopName());
        url.append(",");
        url.append(request.getShopAddress().getShopNumber());
        url.append(",");
        url.append(request.getShopAddress().getShopPostCode());
        url.append(key);
        url.append(keyValue);
        LOGGER.info("url = " + url);
        return url.toString();
    }

    private String buildRequestForReverseGeoCoding(Double latitude, Double longitude) {
        StringBuilder url = new StringBuilder();
        url.append(baseURL);
        url.append(latLang);
        url.append(latitude);
        url.append(",");
        url.append(longitude);
        url.append(key);
        url.append(keyValue);
        LOGGER.info("url = " + url);
        return url.toString();
    }

    private String callGeocoding(String url) throws MalformedURLException, IOException, ProtocolException {
        URL obj = null;
        obj = new URL(url);
        HttpURLConnection con = null;
        con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept", "application/json");

        int responseCode = 0;
        responseCode = con.getResponseCode();

        LOGGER.info("\nSending 'GET' request to URL : " + url);
        LOGGER.info("Response Code : " + responseCode);

        BufferedReader in = null;
        String inputLine;
        StringBuffer response = new StringBuffer();

        if (responseCode == 200) {

            in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        } else {
            in = new BufferedReader(new InputStreamReader(con.getErrorStream()));

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            LOGGER.error("Error = " + con.getResponseMessage());

        }
        in.close();
        //print result
        LOGGER.info(response.toString());
        return response.toString();
    }

    private String callReverseGeoCoding(String url) throws IOException {
        URL obj = null;
        obj = new URL(url);
        HttpURLConnection con = null;
        con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept", "application/json");

        int responseCode = 0;
        responseCode = con.getResponseCode();

        LOGGER.info("\nSending 'GET' request to URL : " + url);
        LOGGER.info("Response Code : " + responseCode);

        BufferedReader in = null;
        String inputLine;
        StringBuffer response = new StringBuffer();

        if (responseCode == 200) {

            in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        } else {
            in = new BufferedReader(new InputStreamReader(con.getErrorStream()));

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            LOGGER.error("Error = " + con.getResponseMessage());

        }
        in.close();
        LOGGER.info(response.toString());
        return response.toString();
    }

    private ShopDetailsResponse buildResponse(String geoResponse) throws JsonParseException, JsonMappingException,
            IOException {
        ShopDetailsResponse shopResponse = new ShopDetailsResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        GeoCodeResponse geoCodeResponse = objectMapper.readValue(geoResponse, GeoCodeResponse.class);
        LOGGER.info("REsponse = " + geoCodeResponse.getStatus());
        if ("OK".equals(geoCodeResponse.getStatus())) {
            List<GeoCode> geoCodeList = new ArrayList<GeoCode>();
            geoCodeList = geoCodeResponse.getResults();
            shopResponse.setLatitiude(geoCodeList.get(0).getGeometry().getLocation().getLat());
            shopResponse.setLongitude(geoCodeList.get(0).getGeometry().getLocation().getLng());
            Response response = new Response();
            response.setErrorCode(Boolean.TRUE);
            shopResponse.setResponse(response);
        } else {
            Response response = new Response();
            response.setErrorCode(Boolean.FALSE);
            response.setErrorMessage(geoCodeResponse.getStatus());
            shopResponse.setResponse(response);
        }

        return shopResponse;
    }

    private ShopDetailsResponse buildReverseResponse(String geoResponse) throws JsonParseException,
            JsonMappingException, IOException {
        ShopDetailsResponse shopResponse = new ShopDetailsResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        GeoCodeResponse geoCodeResponse = objectMapper.readValue(geoResponse, GeoCodeResponse.class);
        LOGGER.info("REsponse = " + geoCodeResponse.getStatus());
        if ("OK".equals(geoCodeResponse.getStatus())) {
            List<GeoCode> geoCodeList = new ArrayList<GeoCode>();
            geoCodeList = geoCodeResponse.getResults();
            shopResponse.setLatitiude(geoCodeList.get(0).getGeometry().getLocation().getLat());
            shopResponse.setLongitude(geoCodeList.get(0).getGeometry().getLocation().getLng());
            Collection<AddressComponent> addressComponents = new ArrayList<AddressComponent>();
            addressComponents = geoCodeList.get(0).getAddress_components();
            Iterator<AddressComponent> itrAddress = addressComponents.iterator();
            ShopAddress shopAddress = new ShopAddress();
            while (itrAddress.hasNext()) {
                AddressComponent address = itrAddress.next();

                Collection<String> addressTypes = new ArrayList<String>();
                addressTypes = address.getTypes();
                Iterator<String> strItr = addressTypes.iterator();
                while (strItr.hasNext()) {
                    String type = strItr.next();
                    if (type.equals("street_number")) {
                        shopAddress.setShopNumber(address.getLongName());
                        break;
                    }
                    if (type.equals("postal_code")) {
                        shopAddress.setShopPostCode(address.getLongName());
                        break;
                    }
                    if (type.equals("route"))
                        shopResponse.setShopName(address.getLongName());
                }

            }
            shopResponse.setShopAddress(shopAddress);
            Response response = new Response();
            response.setErrorCode(Boolean.TRUE);
            shopResponse.setResponse(response);
        } else {
            Response response = new Response();
            response.setErrorCode(Boolean.FALSE);
            response.setErrorMessage(geoCodeResponse.getStatus());
            shopResponse.setResponse(response);
        }

        return shopResponse;
    }
}