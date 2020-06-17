package engine;

import engine.gui.Controller;
import engine.gui.Display;

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
    private final String title;
    private final String author;
    private final String desc;
    private Controller control = null;
    private Boolean enableGUI = false;

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

    //TODO: Add comment
    public void enableGUI(){
            this.control = new Controller(new Display(), this);
            Thread gui = new Thread(this.control);
            gui.start();
            this.enableGUI = true;
    }

    public Controller getControl() {
        return this.control;
    }

    public Boolean getEnableGUI(){
        return this.enableGUI;
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

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getDesc() {
        return this.desc;
    }

    /**
     * This class prints out Engine info and Story info.
     */
    private void printInfo(){
        if (this.getEnableGUI()) {
            this.getControl().sendText("Towns Engine");
            this.getControl().sendText("By: Omar Radwan & Wyatt Phillips");
            this.getControl().sendText("Version: beta");
            this.getControl().sendText("");
            this.getControl().sendText("Type anything to continue...");
            this.getControl().getInput();
            this.getControl().clearScreen();

            this.getControl().sendText(this.getTitle());
            this.getControl().sendText("By: " + this.getAuthor());
            this.getControl().sendText("");
            this.getControl().sendText(this.getDesc());
            this.getControl().sendText("");
            this.getControl().sendText("Type anything to start...");
            this.getControl().getInput();
            this.getControl().clearScreen();
        } else {
            Scanner input = new Scanner(System.in);
            ToolBelt.slowText("Towns Engine");
            ToolBelt.slowText("By: Omar Radwan & Wyatt Phillips");
            ToolBelt.slowText("Version: beta");
            System.out.println();
            ToolBelt.slowText("Type anything to continue...");
            input.next();
            ToolBelt.clearScreen();

            ToolBelt.slowText(this.getTitle());
            ToolBelt.slowText("By: " + this.getAuthor());
            System.out.println();
            ToolBelt.displayText(this.getDesc(), 70);
            System.out.println();
            ToolBelt.slowText("Type anything to start...");
            input.next();
            ToolBelt.clearScreen();
        }
    }

}
