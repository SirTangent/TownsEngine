package engine;

/**
 * This class is a subclass of Scene. This acts as a dead end in the story.
 * @see Scene
 *
 * @author Omar Radwan
 * @author Wyatt Phillips
 * @version 1.0.1
 */
public class DeadEndScene extends Scene{
    private StoryPlayer player = null;
    private boolean startCheck = false;
    private boolean saveCheck = false;
    private Decision backToSave = new Decision("Go back to last Save", Scene.currSave);
    public DeadEndScene(String desc, Scene prevScene, StoryPlayer player) {
        super(desc);
        this.player = player;

        if (this.player.getStartScene() != null && !this.startCheck) {
            Decision backToStart = new Decision("Go back to the start", this.player.getStartScene());
            super.addDecision(backToStart);
            this.startCheck = true;
        }

        if (Scene.currSave != null && !this.saveCheck) {
            Decision backToSave = new Decision("Go back to last Save", Scene.currSave);
            super.addDecision(backToSave);
            this.saveCheck = true;
        }

        Decision backOne = new Decision("Go to the last scene", prevScene);
        super.addDecision(backOne);
    }

    /**
     * This method runs the scene and says you hit a dead end and show the options
     * @see Scene
     */
    @Override
    public void play() {
        if (player.getStartScene() != null && this.startCheck == false) {
            Decision backToStart = new Decision("Go back to the start", this.player.getStartScene());
            super.addDecision(backToStart);
            this.startCheck = true;
        }

        if (Scene.currSave != null && this.saveCheck == false) {
            super.addDecision(this.backToSave);
            this.saveCheck = true;
        }
        if (Scene.currSave != null){
            this.backToSave.setNextScene(Scene.currSave);
        }

        super.play("You have reached a dead end.");
    }
}
