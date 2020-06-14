package engine.gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Display extends Application {
    private Controller control = null;

    public Display() {

    }

    public void setController(Controller control){
        this.control = control;
    }

    public void appLaunch(){
        launch();
    }

    public void start (Stage primaryStage){
        primaryStage.setTitle("Hello World!");
        TextArea testing = new TextArea("Hello World");

        testing.setEditable(false);

        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        update();
                    }
                }),
                new KeyFrame(Duration.millis(100))
        );
        timeline.setCycleCount(timeline.INDEFINITE);

        StackPane root = new StackPane();
        root.getChildren().add(testing);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();

        timeline.play();

        testing.setText(testing.getText() + "BITCH!");
    }

    private void update() {

    }

}
