
package com.solovev.usersfx.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "lat",
    "lng"
})
public class Geo {

    @JsonProperty("lat")
    private String lat;
    @JsonProperty("lng")
    private String lng;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Geo() {
    }

    /**
     * 
     * @param lng
     * @param lat
     */
    public Geo(String lat, String lng) {
        super();
        this.lat = lat;
        this.lng = lng;
    }

    @JsonProperty("lat")
    public String getLat() {
        return lat;
    }

    @JsonProperty("lat")
    public void setLat(String lat) {
        this.lat = lat;
    }

    @JsonProperty("lng")
    public String getLng() {
        return lng;
    }

    @JsonProperty("lng")
    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Geo geo = (Geo) o;

        if (!Objects.equals(lat, geo.lat)) return false;
        return Objects.equals(lng, geo.lng);
    }

    @Override
    public int hashCode() {
        int result = lat != null ? lat.hashCode() : 0;
        result = 31 * result + (lng != null ? lng.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Geo{" +
                "lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
    }
}
