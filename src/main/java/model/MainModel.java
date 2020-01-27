package model;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.time.ZoneId;
import java.util.LinkedList;

public class MainModel {

    private DoubleProperty solarAltitude = new SimpleDoubleProperty(0d);
    private DoubleProperty azimuth = new SimpleDoubleProperty(0d);
    private LinkedList<Location> locations = new LinkedList<>();

    public MainModel() {
        locations.add(new Location("Bochum", new double[]{51d, 7d}, ZoneId.of("GMT")));
        // TODO: add more locations. Maybe move to static method
    }

    public LinkedList<Location> getLocations() {
        return locations;
    }

    public void setLocations(LinkedList<Location> locations) {
        this.locations = locations;
    }

    public double getSolarAltitude() {
        return solarAltitude.get();
    }

    public void setSolarAltitude(double solarAltitude) {
        this.solarAltitude.set(solarAltitude);
    }

    public DoubleProperty solarAltitudeProperty() {
        return solarAltitude;
    }

    public double getAzimuth() {
        return azimuth.get();
    }

    public void setAzimuth(double azimuth) {
        this.azimuth.set(azimuth);
    }

    public DoubleProperty azimuthProperty() {
        return azimuth;
    }
}