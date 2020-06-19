package engine.gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 *This is the gui for the engine. this can be used to display stuff ina  nice window instead of the terminal.
 *
 * @author Omar Radwan
 * @version 1.0.0
 */
public class Display extends Application {
    public static Controller currControl = null;
    private Controller control;
    public static Update update;


    public Display() {
    }

    /**
     * This launches the Stage.
     */
    public void appLaunch(){
        launch();
    }

    /**
     * This is the actually scene that gets displayed on the screen.
     * @param primaryStage - The default stage.
     */
    public void start (Stage primaryStage){
        TextArea output;
        int xNum;
        int yNum;
        xNum = 500;
        yNum = 700;

        output = new TextArea("");
        output.setEditable(false);
        this.control = Display.currControl;
        Display.update = new Update(this.control, output);
        Display.currControl = null;

        TextArea input = new TextArea();
        input.setMinHeight(yNum/15.0);
        input.setMinWidth((xNum/8.0) * 7);
        input.setMaxHeight(yNum/15.0);
        input.setMaxWidth((xNum/8.0) * 7);
        input.setScrollTop(0);

        input.setStyle("-fx-font-size: 25; -fx-font-family: monospace");

        Button enter = new Button("Enter");
        enter.setMinHeight(yNum/15.0);
        enter.setMinWidth(xNum/8.0);
        enter.setMaxHeight(yNum/15.0);
        enter.setMaxWidth(xNum/8.0);

        enter.setStyle("-fx-font-size: 14; -fx-font-family: monospace");

        HBox inputSys = new HBox();
        inputSys.setAlignment(Pos.CENTER);
        inputSys.getChildren().add(input);
        inputSys.getChildren().add(enter);
        inputSys.setPadding(new Insets(15,0,0,0));

        output.setMinWidth(xNum);
        output.setMinHeight((yNum/15.0) * 14);
        output.setMaxWidth(xNum);
        output.setMaxHeight((yNum/15.0) * 14);

        output.setStyle("-fx-font-size: 18; -fx-font-family: monospace");

        /*
         * This timeline does the update and checks the controller for anything new
         */
        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(100), new EventHandler() {
                    private Thread temp = null;
                    private final Update update = Display.update;
                    private Boolean checkFlag = false;
                    @Override
                    public void handle(Event event) {
                        if(!this.checkFlag) {
                            Display.update = null;
                            this.checkFlag = true;
                        }

                        if (temp == null) {
                            update.getControl().getDebug().printDebug("Timeline: Thread is null; creating new thread");
                            temp = new Thread(update);
                            update.getControl().getDebug().printDebug("Timeline: Starting thread...");
                            temp.start();
                        } else {
                            if (!temp.isAlive()) {
                                update.getControl().getDebug().printDebug("Timeline: Thread is dead;setting temp to null");
                                temp = null;
                            }
                        }
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        enter.setOnAction((ActionEvent e) -> {
            this.control.getDebug().printDebug("GUI: Button was pressed");
            this.control.setInput(input.getText().trim());
            input.setText("");
        });

        input.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER){
                this.control.getDebug().printDebug("GUI: Enter was pressed");
                this.control.setInput(input.getText().trim());
                input.setText("");
            }
        });

        VBox all = new VBox();
        all.setAlignment(Pos.CENTER);
        all.getChildren().add(output);
        all.getChildren().add(inputSys);

        StackPane root = new StackPane();
        root.getChildren().add(all);

        Scene scene = new Scene(root, xNum, yNum);
        primaryStage.setScene(scene);
        primaryStage.setTitle(this.control.getPlayer().getTitle() + " by: " + this.control.getPlayer().getAuthor());
        primaryStage.setResizable(false);
        primaryStage.show();

        primaryStage.setOnCloseRequest((WindowEvent e) -> {
                this.control.getDebug().printDebug("!-!-PROGRAM-!-!: Program has been terminated");
                System.exit(0);
        });
    }

    /**
     * this class will check the controller for anything new and update the screen accordingly and nicely.
     * The display is a variation on how ToolBelt displays and also implements Runnable.
     * @see java.lang.Runnable
     *
     * @author Omar Radwan
     * @version 1.0.0
     */
    private static class Update implements Runnable{
        final private Controller control;
        final private TextArea output;
        private Update(Controller control, TextArea output){
            this.control = control;
            this.output = output;
        }

        /**
         * This method checks the controller for any updates
         */
        private void update() {
            if(this.control.getClearScreen()){
                this.output.setText("");
                this.control.setClearScreen(false);
                return;
            }
            control.getDebug().printDebug("Checking update", this);
            if (this.control.peekText() != null) {
                control.getDebug().printDebug("Found new text! printing...", this);
                this.control.setLock(true);
                String text = this.control.grabText();
                int lineLength = 44;

                displayText(text, lineLength);
            }

            this.control.setLock(false);

            control.getDebug().printDebug("Finished update", this);
        }

        /**
         * This method will display text onto the screen nicely.
         * @param text The String to be displayed.
         * @param lineLength how many char the line will be at most.
         */
        public void displayText(String text, int lineLength) {
            boolean flag = false;
            while (true) {
                if (text.length() > lineLength) {
                    for (int i = lineLength; i > 0; i--) {
                        if (text.charAt(i) == ' ') {
                            slowText(text.substring(0, i));
                            text = text.substring(i + 1);
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        slowText(text.substring(0, lineLength - 1) + "-");
                        text = text.substring(lineLength);
                        flag = false;
                    }
                } else {
                    slowText(text);
                    return;
                }
            }
        }

        /**
         * This method print the sting char by char so it looks nice and then it prints a new line.
         * @param text the text to be printed out char by char.
         */
        public void slowText(String text) {
            for (int i = 0; i < text.length(); i++) {
                this.output.appendText(String.valueOf(text.charAt(i)));
                try {
                    Thread.sleep(55);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.output.appendText( "\n");
        }

        /**
         * This overrides the run method from runnable.
         */
        @Override
        public void run() {
            update();
        }

        public Controller getControl(){
            return this.control;
        }
    }
}
