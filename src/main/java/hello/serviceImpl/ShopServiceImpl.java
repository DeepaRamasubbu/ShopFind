package hello.serviceImpl;

import hello.model.GeoCode;
import hello.model.GeoCodeResponse;
import hello.model.Response;
import hello.model.ShopDetailsRequest;
import hello.model.ShopDetailsResponse;
import hello.service.ShopService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Named
public class ShopServiceImpl implements ShopService {

    private static final String key = "AIzaSyCNL-l5qRr1zNaLooMVeeBSf2Vg-N_fJH0";
    private final String USER_AGENT = "Mozilla/5.0";

    @Override
    public ShopDetailsResponse addShop(ShopDetailsRequest request) {
        // TODO Auto-generated method stub
        Response response = null;
        String geoCodingRequest = null;
        String geoResponse = null;
        validateShopDetails(request, response);
        if (response == null) {
            geoCodingRequest = buildRequestForGeocoding(request);
            try {
                geoResponse = callGeocoding(geoCodingRequest);
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return buildResponse(geoResponse);
    }

    @Override
    public ShopDetailsResponse getShopDetails(String latitude, String longitude) {
        // TODO Auto-generated method stub
        return null;
    }

    private void validateShopDetails(ShopDetailsRequest request, Response response) {
        if (StringUtils.isEmpty(request.getShopAddress())) {
            response = new Response();
            response.setErrorCode(Boolean.FALSE);
            response.setErrorMessage("Empty shop address");
        }
    }

    private String buildRequestForGeocoding(ShopDetailsRequest request) {
        //
        //https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=YOUR_API_KEY
        StringBuilder url = new StringBuilder();
        url.append("https://maps.googleapis.com/maps/api/geocode/json?address=");
        url.append(request.getShopName());
        url.append(",");
        url.append(request.getShopAddress().getShopNumber());
        url.append(",");
        url.append(request.getShopAddress().getShopPostCode());
        url.append("&key=");
        url.append(key);
        System.out.println("url = " + url);
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

        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

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
            System.out.println("Error = " + con.getResponseMessage());

        }
        in.close();
        //print result
        System.out.println(response.toString());
        return response.toString();
    }

    private ShopDetailsResponse buildResponse(String geoResponse) {
        ShopDetailsResponse shopResponse = new ShopDetailsResponse();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            GeoCodeResponse geoCodeResponse = objectMapper.readValue(geoResponse, GeoCodeResponse.class);
            System.out.println("REsponse = " + geoCodeResponse.getStatus());
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
                response.setErrorMessage("Some error");
                shopResponse.setResponse(response);
            }

        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return shopResponse;
    }
}