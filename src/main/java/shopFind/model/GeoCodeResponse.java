package shopFind.model;

import java.util.ArrayList;
import java.util.List;

public class GeoCodeResponse {
    private String status;
    private List<GeoCode> results = new ArrayList<GeoCode>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResults(List<GeoCode> results) {
        this.results = results;
    }

    public List<GeoCode> getResults() {
        return results;
    }
}
