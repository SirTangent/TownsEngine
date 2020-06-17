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
public class Branch implements Playable{
    private ArrayList<Decision> options = new ArrayList<>();
    protected static Branch currSave = null;
    protected String desc;
    protected StoryPlayer player;

    public Branch(String desc, StoryPlayer player, Decision[] decisions){
        this(desc, player);
        for (Decision temp: decisions) {
            this.addDecision(temp);
        }
    }

    public Branch(String desc, StoryPlayer player, Decision decision){
        this(desc, player);
        this.options.add(decision);
    }

    public Branch(String desc, StoryPlayer player){
        this.player = player;
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
        if (this.player.getEnableGUI()) {
            this.player.getControl().sendText(this.desc);
            this.player.getControl().sendText("");
            ToolBelt.sleep(2);
            this.player.getControl().sendText("What would you like to do?");
        } else {
            ToolBelt.displayText(this.desc, 70);
            System.out.println();
            ToolBelt.sleep(2);
            ToolBelt.slowText("What would you like to do?");
        }

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
            if (this.player.getEnableGUI()){
                this.player.getControl().sendText(count + "|" + temp.getName());
            } else {
                ToolBelt.displayText(count + "|" + temp.getName(), 70);
            }
            count++;
        }

        int number;
        while(true) {
            if (this.player.getEnableGUI()) {
                this.player.getControl().sendText("");

                String input = this.player.getControl().getInput();

                try {
                    number = Integer.parseInt(input);

                    if (number < 1 || number > this.options.size()) {
                        this.player.getControl().sendText("Error: Not a valid option!");
                        ToolBelt.sleep(1);
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    this.player.getControl().sendText("Error: Must be a number!");
                    ToolBelt.sleep(1);
                }
            } else {
                System.out.println();
                System.out.print(">");

                Scanner input = new Scanner(System.in);
                try {
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
        }
        if(this.player.getEnableGUI()) {
            this.player.getControl().clearScreen();
        } else {
            ToolBelt.clearScreen();
        }
        this.options.get(number-1).getNextBranch().play();
    }

    /**
     * This method adds engine.Decision instances to the options array.
     * @param decision a instance of engine.Decision.
     */
    public void addDecision(Decision decision) {
        this.options.add(decision);
    }
}
