package engine;

import java.util.Scanner;
/**
 * This class is for keeping track of basic info like engine info and story info. it also keeps track of the Start scene.
 *
 * @author Omar Radwan
 * @author Wyatt Phillips
 * @version 1.0.0
 * */
public class StoryPlayer {
    private static Scene startScene = null;
    private static String title= null;
    private static String author= null;
    private static String desc = null;

    public StoryPlayer(Scene startScene, String title, String desc,String author){
        StoryPlayer.startScene = startScene;
        StoryPlayer.title = title;
        StoryPlayer.author = author;
        StoryPlayer.desc = desc;
    }

    /**
     * This method will start the story and play the startScene scene.
     */
    public void playLoop() {
        this.printInfo();
        StoryPlayer.startScene.run();
    }

    /**
     * This method will play the scene you give it.
     * @param scene A Scene or a class that extends Scene.
     */
    public void playScene(Scene scene){
        this.printInfo();
        scene.run();
    }

    /**
     * This method gets the StartScene.
     * @return the startScene scene.
     */
    public static Scene getStartScene(){
        return StoryPlayer.startScene;
    }

    /**
     * This class prints out Engine info and Story info.
     */
    private void printInfo(){
        Scanner input = new Scanner(System.in);
        ToolBelt.slowText("TownsEngine");
        ToolBelt.slowText("By: Omar Radwan, Wyatt Phillips");
        ToolBelt.slowText("Version: beta");
        System.out.println();
        ToolBelt.slowText("Type anything to continue...");
        input.next();
        ToolBelt.clearScreen();

        ToolBelt.slowText(StoryPlayer.title);
        ToolBelt.slowText("By: " + StoryPlayer.author);
        System.out.println();
        ToolBelt.displayText(StoryPlayer.desc, 70);
        System.out.println();
        ToolBelt.slowText("Type anything to start...");
        input.next();
        ToolBelt.clearScreen();
    }

}
