/**
 * This is the Decision class. It holds links to other scenes
 *
 * @author Omar Radwan
 * @version 1.0.0
 */
public class Decision {
    private Scene nextScene;
    private String PrintableName;

    public Decision(Scene nextScene, String name){
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
     * This returns the nextScene field. Will throw a IllegalStateException if the nextScene field is null
     * @return The nextScene field
     */
    public Scene getNextScene() {
        if (this.nextScene == null) {
            throw new IllegalStateException("Error: Can not get next scene because it is null");
        }
        return this.nextScene;
    }

    public void setNextScene(Scene scene){
        this.nextScene = scene;
    }
}