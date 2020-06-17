package engine;

/**
 * This class is a subclass of Branch. This acts as a dead end in the story.
 * @see Branch
 *
 * @author Omar Radwan
 * @author Wyatt Phillips
 * @version 1.0.1
 */
public class DeadEndBranch extends Branch {
    private boolean startCheck = false;
    private boolean saveCheck = false;
    private Decision backToSave = new Decision("Go back to last Save", Branch.currSave);
    public DeadEndBranch(String desc, Branch prevBranch, StoryPlayer player) {
        super(desc, player);

        if (super.player.getStartBranch() != null && !this.startCheck) {
            Decision backToStart = new Decision("Go back to the start", this.player.getStartBranch());
            super.addDecision(backToStart);
            this.startCheck = true;
        }

        if (Branch.currSave != null && !this.saveCheck) {
            Decision backToSave = new Decision("Go back to last Save", Branch.currSave);
            super.addDecision(backToSave);
            this.saveCheck = true;
        }

        Decision backOne = new Decision("Go to the last scene", prevBranch);
        super.addDecision(backOne);
    }

    /**
     * This method runs the scene and says you hit a dead end and show the options
     * @see Branch
     */
    @Override
    public void play() {
        if (super.player.getStartBranch() != null && this.startCheck == false) {
            Decision backToStart = new Decision("Go back to the start", super.player.getStartBranch());
            super.addDecision(backToStart);
            this.startCheck = true;
        }

        if (Branch.currSave != null && this.saveCheck == false) {
            super.addDecision(this.backToSave);
            this.saveCheck = true;
        }
        if (Branch.currSave != null){
            this.backToSave.setNextBranch(Branch.currSave);
        }

        super.play("You have reached a dead end.");
    }
}
