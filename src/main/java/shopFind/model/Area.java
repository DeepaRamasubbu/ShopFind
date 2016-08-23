package shopFind.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * Class that has Area details
 */
public class Area {

    @JsonProperty("southwest")
    private Location southWest;

    @JsonProperty("northeast")
    private Location northEast;

    public Location getSouthWest() {
        return southWest;
    }

    public void setSouthWest(Location southWest) {
        this.southWest = southWest;
    }

    public Location getNorthEast() {
        return northEast;
    }

    public void setNorthEast(Location northEast) {
        this.northEast = northEast;
    }
}
