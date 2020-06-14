package engine.gui;

import java.util.ArrayList;

public class Controller implements Runnable{
    private ArrayList<String> intake = new ArrayList<String>();
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

    }

    public String getInput() {
        return null;
    }

    String grabText() {
        return null;
    }

    void setInput() {

    }

    void setLock(Boolean lock){

    }
}
