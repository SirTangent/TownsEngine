/**
 * This class is for keeping track fo the start scene and can run any scene.
 */
public class StoryPlayer {
    private Scene startScene;

    public StoryPlayer(Scene startScene){
        this.startScene = startScene;
    }

    public void playLoop() {
        this.startScene.run();
    }

    public void playScene(Scene scene){
        scene.run();
    }
}
