import java.util.Scanner;
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
        this.printInfo();
        this.startScene.run();
    }

    public void playScene(Scene scene){
        this.printInfo();
        scene.run();
    }

    public static Scene getStartScene(){
        return startScene;
    }

    private void printInfo(){
        Scanner input = new Scanner(System.in);
        ToolBelt.slowText("TownsEngine");
        ToolBelt.slowText("By: Omar Radwan, Wyatt Phillips");
        ToolBelt.slowText("Version: beta");
        System.out.println();
        ToolBelt.slowText("Type anything to continue...");
        input.next();

        ToolBelt.clearScreen();
    }
}
