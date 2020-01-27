package view;

import controller.MainController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Location;
import model.MainModel;

import java.time.*;


public class View extends Application {

    private static final String LOCATION = "Bochum";

    MainModel mainModel = new MainModel();
    MainController mainController = new MainController(mainModel);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Solar Altitude");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("Solar Altitude");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label labelDate = new Label("Date:");
        grid.add(labelDate, 0, 1);

        DatePicker datePicker = new DatePicker(LocalDate.of(2020, Month.JANUARY, 1));
        grid.add(datePicker, 1, 1);

        Label labelTime = new Label("Time:");
        grid.add(labelTime, 0, 2);
        Spinner<Integer> hourSpinner = new Spinner<>(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 12));
        hourSpinner.setEditable(true);
        grid.add(hourSpinner, 1, 2);
        Spinner<Integer> minuteSpinner = new Spinner<>(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
        minuteSpinner.setEditable(true);
        grid.add(minuteSpinner, 2, 2);
        Spinner<Integer> secondSpinner = new Spinner<>(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
        secondSpinner.setEditable(true);
        grid.add(secondSpinner, 3, 2);

        Label labelLocation = new Label("Location:");
        // TODO: remove coord fields
        grid.add(labelLocation, 0, 3);
        TextField textFieldLocation1 = new TextField();
        grid.add(textFieldLocation1, 1, 3);
        TextField textFieldLocation2 = new TextField();
        grid.add(textFieldLocation2, 2, 3);
        ChoiceBox<Location> locationChoiceBox = new ChoiceBox<>();
        locationChoiceBox.getItems().addAll(mainModel.getLocations());

        Button button = new Button("Start");
        HBox hbButton = new HBox(10);
        hbButton.setAlignment(Pos.BOTTOM_RIGHT);
        hbButton.getChildren().add(button);
        grid.add(hbButton, 1, 4);

        Text solarAltitudeText = new Text("Solar altitude angle: ");
        Text solarAltitudeResult = new Text();
        solarAltitudeResult.textProperty().bind(mainModel.solarAltitudeProperty().asString());

        Text azimuthText = new Text("Azimuth angle: ");
        Text azimuthResult = new Text();
        azimuthResult.textProperty().bind(mainModel.azimuthProperty().asString());

        grid.add(solarAltitudeText, 0, 6);
        grid.add(solarAltitudeResult, 1, 6);
        grid.add(azimuthText, 0, 7);
        grid.add(azimuthResult, 1, 7);

        button.setOnAction(e -> {
            // Build LocalDate object
            LocalDate localDate = datePicker.getValue();
            LocalTime localTime = LocalTime.of(hourSpinner.getValue(), minuteSpinner.getValue(), secondSpinner.getValue());
            ZonedDateTime zonedDateTime = ZonedDateTime.of(localDate, localTime, ZoneId.of("GMT"));

            // TODO: get Location from locationChoiceBox

            mainController.sunPosition(zonedDateTime);
        });

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}