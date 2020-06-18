package engine.gui;

import engine.StoryPlayer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * This class is used for talking between the gui and the engine.
 * This class implements Runnable.
 * @see java.lang.Runnable
 *
 * @author Omar Radwan
 * @version 1.0.0
 */
public class Controller implements Runnable{
    private Queue<String> intake = new LinkedList<>();
    private String outtake = null;
    private Boolean lock;
    private Display display;
    private Boolean screenClear = false;
    private StoryPlayer player;

    public Controller(Display display, StoryPlayer player) {
        this.lock = false;
        this.display = display;
        this.player = player;
    }

    /**
     * This overrides the run method from runnable.
     */
    @Override
    public void run() {
        Display.currControl = this;
        this.display.appLaunch();
    }

    /**
     * This will add text the intake queue.
     * @param text the string to be added.
     */
    public void sendText(String text){
        //System.out.println("Control: added: " + text);
        this.intake.add(text);
    }

    /**
     * This method will return the outtake String. Will wait for a input if there none and will not return while the controller is locked.
     * @return the string from outtake.
     */
    public String getInput() {
        while(this.lock){
            System.out.print("");
        }
        while (this.outtake == null){
            System.out.print("");
        }
        //System.out.println("Control: returning text " + this.outtake);

        String temp = this.outtake;
        this.outtake = null;
        return temp;
    }

    /**
     * This method will send a clearScreen to the GUI.
     */
    public void clearScreen(){
        setClearScreen(true);
    }

    /**
     * This method will return if the ClearScreen was requested or not.
     * @return A true if the clear screen was requested and false otherwise.
     */
    Boolean getClearScreen(){
        return this.screenClear;
    }

    /**
     * This will set the clear screen request to the value you give it.
     * @param val a boolean for clear screen request.
     */
    void setClearScreen(Boolean val){
        //System.out.println("Control: set clearScreen value to " + val);
        this.screenClear = val;
    }

    /**
     * This method will pull the first String in the queue and return it or return null if there is nothing in the queue.
     * @return The String from the queue or null if there is none.
     */
    String grabText() {
        if (peekText() == null){
            //System.out.println("Control: removed: null");
            return null;
        }

        //System.out.println("Control: removed: " + peekText());
        return this.intake.remove();
    }

    /**
     * This will return the front of the queue without takign it out.
     * @return the first String in queue or null if there is none.
     */
    String peekText() {
        return this.intake.peek();
    }

    /**
     * This method will set the Input String to the String given.
     * If the controller is locked, the input will not be assigned to the field but it will not wait for the lock to be lifted.
     * @param text The String to assign to the input field.
     */
    void setInput(String text) {
        if (!lock) {
            //System.out.println("Control: output text set to " + text);
            this.outtake = text;
        }
    }

    /**
     * This method will set the lock status of the controller.
     * @param lock The status of the lock you want to set.
     */
    void setLock(Boolean lock){
        //System.out.println("Control: Lock was set to " + lock);
        this.lock = lock;
    }

    /**
     * This method returns the player instance used by this controller.
     * @return the StoryPlayer used by this controller.
     */
    StoryPlayer getPlayer() {
        return this.player;
    }
}
