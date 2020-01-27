package controller;

import model.MainModel;

import java.time.ZonedDateTime;

import static java.lang.Math.*;


public class MainController {

    private MainModel mainModel;

    public MainController(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    public void sunPosition(ZonedDateTime zonedDateTime) {

        double latitude = 50.56 * PI / 180; // TODO: pass as parameter
        double longitude = 6.59 * PI / 180; // TODO: pass as parameter

        int timeZone = 1; // TODO: make dynamic
        double localTime = (double) zonedDateTime.getHour() + 12 + (double) (zonedDateTime.getMinute()) / 60;

        System.out.println("Timezone: " + timeZone);
        System.out.println("Days of year: " + zonedDateTime.toLocalDate().lengthOfYear());
        System.out.println("Hour: " + (zonedDateTime.getHour() + 12));
        System.out.println("Minute: " + zonedDateTime.getMinute());
        System.out.println("Day of year: " + zonedDateTime.getDayOfYear());
        System.out.println("Localtime: " + localTime);

        double dayAngle = 2 * PI * ((double) zonedDateTime.getDayOfYear() / (double) zonedDateTime.toLocalDate().lengthOfYear());
        double solarDeclination = (0.3948 - 23.2559 * cos(dayAngle + 0.1588) - 0.3915 * cos(2 * dayAngle + 0.0942) - 0.1764 * cos(3 * dayAngle + 1.8361)) * PI / 180;
        double eqt = (0.0066 + 7.3525 * cos(dayAngle + 1.4992) + 9.9359 * cos(2 * dayAngle + 1.9007) + 0.3387 * cos(3 * dayAngle + 1.8361)); // [min]
        double mlt = (localTime - timeZone) + 4 * (longitude * 180 / PI) / 60; // * min/°
        double solarTime = mlt + eqt / 60;
        double hourAngle = ((12 - solarTime) * 15) * PI / 180; // [°/h]
        double sunHeight = asin(cos(hourAngle) * cos(latitude) * cos(solarDeclination) + sin(latitude) * sin(solarDeclination));

        double acos = acos((sin(sunHeight) * sin(latitude) - sin(solarDeclination)) / (cos(sunHeight) * cos(latitude)));
        double sunAzimuth;
        if (solarTime <= 720)
            sunAzimuth = PI - acos;
        else
            sunAzimuth = PI + acos;

        sunHeight = sunHeight * 180 / PI;
        sunAzimuth = sunAzimuth * 180 / PI;

        mainModel.solarAltitudeProperty().setValue(sunHeight);
        mainModel.azimuthProperty().setValue(sunAzimuth);
    }
}