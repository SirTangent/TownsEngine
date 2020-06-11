package engine;

import java.util.ArrayList;
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
    private ArrayList<Decision> options = new ArrayList<Decision>();
    protected static Scene currSave = null;
    protected String desc;

    public Scene(String desc, Decision[] decisions){
        this(desc);

        for (Decision temp: decisions) {
            this.addDecision(temp);
        }
    }

    public Scene(String desc, Decision decision){
        this(desc);

        this.options.add(decision);
    }

    public Scene(String desc){
        this.desc = desc;
    }

    /**
     * This method runs and will display stuff and ask for user input.
     */
    @Override
    public void play() {
        if (this.options.get(0) == null) {
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
     * @param text A description of the scene that will be printed.
     */
    protected void play(String text) {
        if (this.options.size() == 0) {
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
        int count = 1;
        for(Decision temp : this.options){
            ToolBelt.displayText(count + "|" + temp.getName(), 70);
            count++;
        }

        int number;
        while(true){
            System.out.println();
            System.out.print(">");

            Scanner input = new Scanner(System.in);
            try{
                number = input.nextInt();

                if (number < 1 || number > this.options.size()) {
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
        this.options.get(number-1).getNextScene().play();
    }

    /**
     * This method adds engine.Decision instances to the options array.
     * @param decision a instance of engine.Decision.
     */
    public void addDecision(Decision decision) {
        this.options.add(decision);
    }
}
