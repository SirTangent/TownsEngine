//TODO: Comment this class and all methods
package engine.gui;

import engine.StoryPlayer;

import java.util.LinkedList;
import java.util.Queue;

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

    @Override
    public void run() {
        Display.currControl = this;
        this.display.appLaunch();
    }

    public void sendText(String text){
        //System.out.println("Control: added: " + text);
        this.intake.add(text);
    }

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

    public void clearScreen(){
        setClearScreen(true);
    }

    Boolean getClearScreen(){
        return this.screenClear;
    }

    void setClearScreen(Boolean val){
        //System.out.println("Control: set clearScreen value to " + val);
        this.screenClear = val;
    }

    String grabText() {
        if (peekText() == null){
            //System.out.println("Control: removed: null");
            return null;
        }

        //System.out.println("Control: removed: " + peekText());
        return this.intake.remove();
    }

    String peekText() {
        return this.intake.peek();
    }

    void setInput(String text) {
        if (!lock) {
            //System.out.println("Control: output text set to " + text);
            this.outtake = text;
        }
    }

    void setLock(Boolean lock){
        //System.out.println("Control: Lock was set to " + lock);
        this.lock = lock;
    }

    StoryPlayer getPlayer() {
        return this.player;
    }
}
