package engine;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is a sub class of Branch. This class acts as a save point and will save the game.
 * @see Branch
 *
 * @author Omar Radwan
 * @author Wyatt Phillips
 * @version 1.0.0
 */
public class SavePoint extends Branch {
    private Branch nextBranch;
    public SavePoint(String desc, Branch nextBranch) {
        super(desc);
        this.setNextBranch(nextBranch);
    }

    public SavePoint(String desc) {
        super(desc);
    }

    /**
     *  This method sets the nextBranch field.
     * @param branch The Branch or class that extends branch that you want tobe the next branch.
     */
    public void setNextBranch(Branch branch){
        this.nextBranch = branch;
    }

    /**
     * This class runs the scene and will ask if you want to save. If you choose yes, you save.
     * @see Branch
     */
    @Override
    public void play() {
        if (this.nextBranch == null) {
            throw new IllegalStateException("Error: Can not run scene as nextBranch is null!");
        }
        ToolBelt.displayText(super.desc, 70);
        System.out.println();
        ToolBelt.sleep(2);

        ToolBelt.slowText("Would you like to save?");
        ToolBelt.slowText("1|Yes");
        ToolBelt.slowText("2|No");

        int number = 0;
        while(true){
            System.out.println();
            System.out.print(">");

            Scanner input = new Scanner(System.in);
            try{
                number = input.nextInt();

                if (number < 1 || number > 2) {
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

        if (number == 1) {
            Branch.currSave = this.nextBranch;
        }
        this.nextBranch.play();
    }
}
