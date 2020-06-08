package engine;

/**
 * This is the engine's Decision class. It is used when you want to make a decision in a scene.
 *
 * @author Omar Radwan
 * @author Wyatt Phillips
 * @version 1.0.0
 */
public class Decision {
    private Scene nextScene;
    private String PrintableName;

    public Decision(String name, Scene nextScene){
        this(name);
        this.nextScene = nextScene;
    }

    public Decision(String name){
        this.PrintableName = name;
    }

    public String getName(){
        return this.PrintableName;
    }

    /**
     * This returns the nextScene field. Will throw a IllegalStateException if the nextScene field is null.
     * @return The nextScene field.
     */
    public Scene getNextScene() {
        if (this.nextScene == null) {
            throw new IllegalStateException("Error: Can not get next scene because it is null!");
        }
        return this.nextScene;
    }

    /**
     * This is set the nextScene scene to the scene input.
     * @param scene Scene or class that extends Scene that you want to be the next scene.
     */
    public void setNextScene(Scene scene){
        this.nextScene = scene;
    }
}