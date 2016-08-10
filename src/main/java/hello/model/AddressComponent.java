package hello.model;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressComponent {
        
        @JsonProperty("long_name")
        private String longName;
        
        @JsonProperty("short_name")
        private String shortName;
        
        private Collection<String> types = new ArrayList<String>();

        public String getLongName() {
            return longName;
        }

        public void setLongName(String longName) {
            this.longName = longName;
        }

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

        public Collection<String> getTypes() {
            return types;
        }

        public void setTypes(Collection<String> types) {
            this.types = types;
        }
}


