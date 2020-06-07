import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * This class can run a single scene. You can only have upto 10 options in a given scene
 *
 * @author Omar Radwan
 * @version 1.0.0
 */
public class Scene implements Runnable{
    private Decision[] options = new Decision[10];
    private int optionsPointer = 0;
    protected static Scene currSave = null;
    private String desc;

    public Scene(String desc, Decision[] decisions){
        this(desc);
        if (decisions.length > 10) {
            throw new IllegalStateException("Error: each scene can only support 10 or less options");
        }

        for (Decision temp: decisions) {
            this.addDecision(temp);
        }
    }

    public Scene(String desc, Decision decision){
        this(desc);
        if (this.optionsPointer > 9) {
            throw new IllegalStateException("Error: can not add another option as each scene can only support 10 or less options");
        }

        this.options[this.optionsPointer] = decision;
        this.optionsPointer++;
    }

    public Scene(String desc){
        this.desc = desc;
    }

    /**
     * This method runs and will display stuff and ask for user input
     */
    @Override
    public void run() {
        if (this.optionsPointer == 0) {
            throw new IllegalStateException("Error: Can not run a scene that has no options.");
        }

        displayText(this.desc, 70);
        System.out.println();
        ToolBelt.sleep(2);
        ToolBelt.slowText("What would you like to do?");

        displayOptions();
    }

    /**
     * This method runs and will display stuff and ask for user input
     */
    protected void run(String text) {
        if (this.optionsPointer == 0) {
            throw new IllegalStateException("Error: Can not run a scene that has no options.");
        }

        displayText(this.desc, 70);
        System.out.println();
        ToolBelt.sleep(2);
        ToolBelt.slowText(text);

        displayOptions();
    }

    private void displayOptions() {
        for (int i = 0; i < this.optionsPointer; i++) {
            int temp = i + 1;
            displayText(temp + "|" + this.options[i].getName(), 70);
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
            } catch (InputMismatchException ex){
                System.out.print("Error: Must be a number!");
                ToolBelt.sleep(1);
            }
            input.close();
        }
        ToolBelt.clearScreen();
        this.options[number].getNextScene().run();
    }

    /**
     * This method adds Decision instances to the options array.
     * @param decision a instance of Decision
     */
    public void addDecision(Decision decision) {
        if (this.optionsPointer > 9) {
            throw new IllegalStateException("Error: can not add another option as each scene can only support 10 or less options");
        }

        this.options[this.optionsPointer] = decision;
        this.optionsPointer++;
    }

    /**
     * This method displays text nicely into the terminal.
     * @param text a string of text you want to display
     * @param lineLength how many char you want per line of the terminal
     */
    protected void displayText(String text, int lineLength){
        while (true) {
            if (text.length() > lineLength) {
                for (int i = lineLength; i > 0; i--) {
                    if (text.charAt(i) == ' ') {
                        ToolBelt.slowText(text.substring(0, i));
                        text = text.substring(i + 1, text.length());
                        break;
                    }
                }
            } else {
                ToolBelt.slowText(text);
                return;
            }
        }
    }
}
