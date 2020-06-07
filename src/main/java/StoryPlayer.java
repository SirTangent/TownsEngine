/**
 * This class is for keeping track of the start scene and can run any scene.
 *
 * @author Oamr Radwan
 * @version 1.0.0
 * */
public class StoryPlayer {
    private static Scene startScene = null;

    public StoryPlayer(Scene startScene){
        this.startScene = startScene;
    }

    public void playLoop() {
        this.startScene.run();
    }

    public void playScene(Scene scene){
        scene.run();
    }

    public static Scene getStartScene(){
        return startScene;
    }
}
