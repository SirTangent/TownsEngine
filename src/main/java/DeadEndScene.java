public class DeadEndScene extends Scene{
    public DeadEndScene(String desc, Scene prevScene) {
        super(desc);
        Decision goBack = new Decision(prevScene,"Go back to the last scene");
        super.addDecision(goBack);

        if (super.currSave != null) {
            Decision save = new Decision(super.currSave,"Go back to the last save point");
            super.addDecision(save);
        }

        Decision start = new Decision(StoryPlayer.getStartScene(), "Go back to the start");
        super.addDecision(start);

    }

    public void run(){
        super.run("You have hit a dead end.");
    }
}
