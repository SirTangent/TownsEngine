package engine.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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

        StackPane root = new StackPane();
        root.getChildren().add(testing);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

}
