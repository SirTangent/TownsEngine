package engine;

import java.util.Scanner;
/**
 * This class is for keeping track of the start scene and can run any scene.
 *
 * @author Omar Radwan
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

    public void playLoop() {
        this.printInfo();
        StoryPlayer.startScene.run();
    }

    public void playScene(Scene scene){
        this.printInfo();
        scene.run();
    }

    public static Scene getStartScene(){
        return StoryPlayer.startScene;
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

        ToolBelt.slowText(StoryPlayer.title);
        ToolBelt.slowText("By: " + StoryPlayer.author);
        System.out.println();
        this.displayText(StoryPlayer.desc, 70);
        System.out.println();
        ToolBelt.slowText("Type anything to start...");
        input.next();
        ToolBelt.clearScreen();
    }

    private void displayText(String text, int lineLength){
        while (true) {
            if (text.length() > lineLength) {
                for (int i = lineLength; i > 0; i--) {
                    if (text.charAt(i) == ' ') {
                        ToolBelt.slowText(text.substring(0, i));
                        text = text.substring(i + 1, text.length());
                        break;
                    }
                }
            } else {
                ToolBelt.slowText(text);
                return;
            }
        }
    }
}
