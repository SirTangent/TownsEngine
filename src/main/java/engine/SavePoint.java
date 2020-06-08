package engine;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is a sub class of Scene. This class acts as a save point and will save the game.
 * @see Scene
 *
 * @author Omar Radwan
 * @author Wyatt Phillips
 * @version 1.0.0
 */
public class SavePoint extends Scene{
    private Scene nextScene;
    public SavePoint(String desc, Scene nextScene) {
        super(desc);
        this.setNextScene(nextScene);
    }

    public SavePoint(String desc) {
        super(desc);
    }

    /**
     *  This method sets the nextScene field.
     * @param scene The Scene or class that extends scene that you want tobe the next scene.
     */
    public void setNextScene(Scene scene){
        this.nextScene = scene;
    }

    /**
     * This class runs the scene and will ask if you want to save. If you choose yes, you save.
     * @see Scene
     */
    @Override
    public void run() {
        if (this.nextScene == null) {
            throw new IllegalStateException("Error: Can not run scene as nextScene is null!");
        }
        ToolBelt.displayText(super.desc, 70);
        System.out.println();
        ToolBelt.sleep(2);

        ToolBelt.slowText("Would you like to save?");
        ToolBelt.slowText("1|Yes");
        ToolBelt.slowText("2|No");

        int number = 0;
        while(true){
            System.out.println();
            System.out.print(">");

            Scanner input = new Scanner(System.in);
            try{
                number = input.nextInt();

                if (number < 1 || number > 2) {
                    System.out.print("Error: Not a valid option!");
                    ToolBelt.sleep(1);
                } else {
                    break;
                }
            } catch (InputMismatchException ex) {
                System.out.print("Error: Must be a number!");
                ToolBelt.sleep(1);
            }
        }
        ToolBelt.clearScreen();

        if (number == 1) {
            Scene.currSave = this.nextScene;
        }
        this.nextScene.run();
    }
}
