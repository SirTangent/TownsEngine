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
    private TextArea output;
    public static Update update;
    private int xNum;
    private int yNum;


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
        this.xNum = 500;
        this.yNum = 700;

        this.output = new TextArea("");
        this.output.setEditable(false);
        this.control = Display.currControl;
        Display.update = new Update(this.control, this.output);
        Display.currControl = null;

        TextArea input = new TextArea();
        input.setMinHeight(this.yNum/15.0);
        input.setMinWidth((this.xNum/8.0) * 7);
        input.setMaxHeight(this.yNum/15.0);
        input.setMaxWidth((this.xNum/8.0) * 7);
        input.setScrollTop(0);

        input.setStyle("-fx-font-size: 25; -fx-font-family: monospace");

        Button enter = new Button("Enter");
        enter.setMinHeight(this.yNum/15.0);
        enter.setMinWidth(this.xNum/8.0);
        enter.setMaxHeight(this.yNum/15.0);
        enter.setMaxWidth(this.xNum/8.0);

        enter.setStyle("-fx-font-size: 14; -fx-font-family: monospace");

        HBox inputSys = new HBox();
        inputSys.setAlignment(Pos.CENTER);
        inputSys.getChildren().add(input);
        inputSys.getChildren().add(enter);
        inputSys.setPadding(new Insets(15,0,0,0));

        this.output.setMinWidth(this.xNum);
        this.output.setMinHeight((this.yNum/15) * 14);
        this.output.setMaxWidth(this.xNum);
        this.output.setMaxHeight((this.yNum/15) * 14);

        this.output.setStyle("-fx-font-size: 18; -fx-font-family: monospace");

        /**
         * This timeline does the update and checks the controller for anything new
         */
        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(100), new EventHandler() {
                    private Boolean lock = false;
                    private Thread temp = null;
                    private Update update = Display.update;
                    private Boolean checkFlag = false;
                    @Override
                    public void handle(Event event) {
                        if(!this.checkFlag) {
                            Display.update = null;
                            this.checkFlag = true;
                        }

                        if (temp == null) {
                            //System.out.println("Timeline: thread is null; creating new thread...");
                            temp = new Thread(update);

                            //System.out.println("Timeline: staring thread..");
                            temp.start();
                        } else {
                            if (temp.isAlive()) {
                                //System.out.println("Timeline: Thread is still alive");
                            } else {
                                //System.out.println("Timeline: Thread is dead; setting temp to null");
                                temp = null;
                            }
                        }
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        enter.setOnAction((ActionEvent e) -> {
            //System.out.println("GUI: Button was pressed");
            this.control.setInput(input.getText().trim());
            input.setText("");
        });

        input.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER){
                //System.out.println("GUI: Enter was pressed");
                this.control.setInput(input.getText().trim());
                input.setText("");
            }
        });

        VBox all = new VBox();
        all.setAlignment(Pos.CENTER);
        all.getChildren().add(this.output);
        all.getChildren().add(inputSys);

        StackPane root = new StackPane();
        root.getChildren().add(all);

        Scene scene = new Scene(root, this.xNum, this.yNum);
        primaryStage.setScene(scene);
        primaryStage.setTitle(this.control.getPlayer().getTitle() + " by: " + this.control.getPlayer().getAuthor());
        primaryStage.setResizable(false);
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                //System.out.print("!-!-PROGRAM-!-!: Program has been terminated");
                System.exit(0);
            }
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
    private class Update implements Runnable{
        private Controller control;
        private TextArea output;
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
            //System.out.println(this.toString() + ": Checking update");
            if (this.control.peekText() != null) {
                //System.out.println(this.toString() + ": Found new text! printing...");
                this.control.setLock(true);
                String text = this.control.grabText();
                int lineLength = 44;

                displayText(text, lineLength);
            }

            this.control.setLock(false);

            //System.out.println(this.toString() + ": Finished update");
        }

        /**
         * This method will display text onto the screen nicely.
         * @param text The String to be displayed.
         * @param lineLength how many char the line will be at most.
         */
        public void displayText(String text, int lineLength) {
            Boolean flag = false;
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
    }
}
