package shopFind.model;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeoCode {
    private Collection<String> types = new ArrayList<String>();
    private String formatted_address;
    private Collection<AddressComponent> address_components = new ArrayList<AddressComponent>();
    private Geometry geometry;
    private boolean partialMatch;

    @JsonProperty("place_id")
    private String placeId;

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public Collection<String> getTypes() {
        return types;
    }

    public void setTypes(Collection<String> types) {
        this.types = types;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setAddress_components(Collection<AddressComponent> address_components) {
        this.address_components = address_components;
    }

    public Collection<AddressComponent> getAddress_components() {
        return address_components;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public boolean isPartialMatch() {
        return partialMatch;
    }

    public void setPartialMatch(boolean partialMatch) {
        this.partialMatch = partialMatch;
    }
}
