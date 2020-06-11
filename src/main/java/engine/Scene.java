package engine;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * This class can run a single scene. It will print out the desc and then all the options. You can only have upto 10 options in a given scene.
 *
 * @author Omar Radwan
 * @author Wyatt Phillips
 * @version 1.0.0
 */
public class Scene implements Playable{
    private Decision[] options = new Decision[10];
    private int optionsPointer = 0;
    protected static Scene currSave = null;
    protected String desc;

    public Scene(String desc, Decision[] decisions){
        this(desc);
        if (decisions.length > 10) {
            throw new IllegalStateException("Error: each scene can only support 10 or less options!");
        }

        for (Decision temp: decisions) {
            this.addDecision(temp);
        }
    }

    public Scene(String desc, Decision decision){
        this(desc);
        if (this.optionsPointer > 9) {
            throw new IllegalStateException("Error: can not add another option as each scene can only support 10 or less options!");
        }

        this.options[this.optionsPointer] = decision;
        this.optionsPointer++;
    }

    public Scene(String desc){
        this.desc = desc;
    }

    /**
     * This method runs and will display stuff and ask for user input.
     */
    @Override
    public void play() {
        if (this.optionsPointer == 0) {
            throw new IllegalStateException("Error: Can not run a scene that has no options!");
        }

        ToolBelt.displayText(this.desc, 70);
        System.out.println();
        ToolBelt.sleep(2);
        ToolBelt.slowText("What would you like to do?");

        displayOptions();
    }

    /**
     * This method runs and will display stuff and ask for user input.
     */
    protected void play(String text) {
        if (this.optionsPointer == 0) {
            throw new IllegalStateException("Error: Can not run a scene that has no options!");
        }

        ToolBelt.displayText(this.desc, 70);
        System.out.println();
        ToolBelt.sleep(2);
        ToolBelt.slowText(text);

        displayOptions();
    }

    /**
     * This will display the options and ask for user input.
     */
    private void displayOptions() {
        for (int i = 0; i < this.optionsPointer; i++) {
            int temp = i + 1;
            ToolBelt.displayText(temp + "|" + this.options[i].getName(), 70);
        }

        int number = 0;
        while(true){
            System.out.println();
            System.out.print(">");

            Scanner input = new Scanner(System.in);
            try{
                number = input.nextInt();

                if (number < 1 || number > this.optionsPointer) {
                    System.out.print("Error: Not a valid option!");
                    ToolBelt.sleep(1);
                } else {
                    break;
                }
            } catch (InputMismatchException ex) {
                System.out.print("Error: Must be a number!");
                ToolBelt.sleep(1);
            }
        }
        ToolBelt.clearScreen();
        this.options[number-1].getNextScene().play();
    }

    /**
     * This method adds engine.Decision instances to the options array.
     * @param decision a instance of engine.Decision.
     */
    public void addDecision(Decision decision) {
        if (this.optionsPointer > 9) {
            throw new IllegalStateException("Error: can not add another option as each scene can only support 10 or less options!");
        }

        this.options[this.optionsPointer] = decision;
        this.optionsPointer++;
    }
}
