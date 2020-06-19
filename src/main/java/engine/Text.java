package engine;

import java.util.Scanner;

/**
 * This class is for displaying text without a decision. It is just prints out the text and then pauses.
 * This is is a subclass of Branch.
 * @see engine.Branch
 *
 * @author Omar Radwan
 * @version 1.0.0
 */
public class Text extends Branch{
    private Branch nextBranch;

    public Text(String desc, StoryPlayer player) {
        super(desc, player);
    }

    public Text(String desc, StoryPlayer player, Branch nextBranch) {
        super(desc, player);
        this.nextBranch = nextBranch;
    }

    /**
     * This method plays the instance and displays text in either terminal or GUI
     * This method over rides the Branch play method
     */
    @Override
    public void play() {
        if (this.nextBranch == null){
            throw new IllegalStateException("Error: can not run text as there is no next branch!");
        }

        if (super.player.getEnableGUI()){
            super.player.getControl().sendText(super.desc);
            super.player.getControl().sendText("");
            ToolBelt.sleep(2);

            super.player.getControl().sendText("Type anything to continue");
            super.player.getControl().getInput();
            super.player.getControl().clearScreen();
        } else {
            Scanner input = new Scanner(System.in);

            ToolBelt.displayText(super.desc, 70);
            System.out.println();
            ToolBelt.sleep(2);

            ToolBelt.slowText("Type anything to continue...");

            input.next();
            ToolBelt.clearScreen();
        }

        this.nextBranch.play();
    }

    /**
     * This method sets the next branch to goto
     * @param nextBranch A branch or any subclass of branch to be set as next.
     */
    public void setNextBranch(Branch nextBranch) {
        this.nextBranch = nextBranch;
    }
}
