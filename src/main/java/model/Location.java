package model;

import java.time.ZoneId;

public class Location {

    // TODO: consider using a third party class instead

    private String name;
    private double[] coordinates;
    private ZoneId zoneId;

    public Location(String name, double[] coordinates, ZoneId zoneId) {
        this.name = name;
        this.coordinates = coordinates;
        this.zoneId = zoneId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public ZoneId getZoneId() {
        return zoneId;
    }

    public void setZoneId(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    @Override
    public String toString() {
        return super.toString();
        // TODO: implement
    }

    @Override
    public int hashCode() {
        return super.hashCode();
        // TODO: implement
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
        // TODO: implement
    }
}
