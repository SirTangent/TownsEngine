package engine.gui;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class Controller implements Runnable{
    private Queue<String> intake = new LinkedList<String>();
    private String outtake = null;
    private Boolean lock = false;
    private Display display = null;

    public Controller(Display display) {
        this.lock = false;
        this.display = display;
    }

    @Override
    public void run() {
        this.display.appLaunch();
    }

    public void sendText(String text){
        this.intake.add(text);
    }

    public String getInput() {
        while (lock){}
        while (outtake == null){}

        String temp = this.outtake;
        this.outtake = null;
        return temp;
    }

    String grabText() {
        if (this.intake.peek() == null){
            return null;
        }
        return this.intake.remove();
    }

    void setInput(String text) {
        if (!lock) {
            this.outtake = text;
        }
    }

    void setLock(Boolean lock){
        this.lock = lock;
    }
}
