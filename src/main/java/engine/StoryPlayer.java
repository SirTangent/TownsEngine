package engine;

import java.util.Scanner;
/**
 * This class is for keeping track of basic info like engine info and story info. it also keeps track of the Start scene.
 *
 * @author Omar Radwan
 * @author Wyatt Phillips
 * @version 1.1.0
 * */
public class StoryPlayer {
    private Branch startBranch = null;
    private String title= null;
    private String author= null;
    private String desc = null;

    public StoryPlayer(String title, String desc,String author, Branch startBranch){
        this.startBranch = startBranch;
        this.title = title;
        this.author = author;
        this.desc = desc;
    }

    public StoryPlayer(String title, String desc,String author){
        this.title = title;
        this.author = author;
        this.desc = desc;
    }

    /**
     * This method will start the story and play the startBranch scene.
     */
    public void playLoop() {
        if (this.startBranch == null) {
            throw new IllegalStateException("Error: There is no start scene!");
        }
        this.printInfo();
        this.startBranch.play();
    }

    /**
     * This method will play the branch you give it.
     * @param branch A Branch or a class that extends Branch.
     */
    public void playScene(Branch branch){
        this.printInfo();
        branch.play();
    }

    /**
     * This method gets the StartScene.
     * @return the startBranch scene.
     */
    public Branch getStartBranch(){
        return this.startBranch;
    }
    public void setStartBranch(Branch branch) {this.startBranch = branch;}

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

        ToolBelt.slowText(this.title);
        ToolBelt.slowText("By: " + this.author);
        System.out.println();
        ToolBelt.displayText(this.desc, 70);
        System.out.println();
        ToolBelt.slowText("Type anything to start...");
        input.next();
        ToolBelt.clearScreen();
    }

}
