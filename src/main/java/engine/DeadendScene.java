package engine;

public class DeadendScene extends Scene{
    private boolean startCheck = false;
    private boolean saveCheck = false;
    private Decision backToSave = new Decision("Go back to last Save", Scene.currSave);
    public DeadendScene(String desc, Scene prevScene) {
        super(desc);

        if (StoryPlayer.getStartScene() != null && this.startCheck == false) {
            Decision backToStart = new Decision("Go back to the start", StoryPlayer.getStartScene());
            super.addDecision(backToStart);
            this.startCheck = true;
        }

        if (Scene.currSave != null && this.saveCheck == false) {
            Decision backToSave = new Decision("Go back to last Save", Scene.currSave);
            super.addDecision(backToSave);
            this.saveCheck = true;
        }

        Decision backOne = new Decision("Go to the last scene", prevScene);
        super.addDecision(backOne);
    }

    @Override
    public void run() {
        if (StoryPlayer.getStartScene() != null && this.startCheck == false) {
            Decision backToStart = new Decision("Go back to the start", StoryPlayer.getStartScene());
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

        super.run("You have reached a dead end.");
    }
}
