package model;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class MainModel {

    private DoubleProperty solarAltitude = new SimpleDoubleProperty(0d);
    private DoubleProperty azimuth = new SimpleDoubleProperty(0d);

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