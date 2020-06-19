package engine;

/**
 * This class is used for debug so instead of commenting out the prints to console, you can just run the program with debug enabled.
 *
 * @author Omar Radwan
 * @version 1.0.0
 */
public class Debug {
    private Boolean enabled = false;
    public Debug () {

    }

    /**
     * This method will print whatever text you give it to console but only if debug is enabled.
     * @param text String you want to print to console.
     */
    public void printDebug(String text){
        if (this.enabled) {
            System.out.println(text);
        }
    }

    /**
     * This is a overlaoded version of printDebug. it will print the object you give it as a string and then print the text you give it.
     * This is useful when you are running threads and you want to know what threads are doing.
     * @param text String you want to print to console.
     * @param object The object that you want to print with the text.
     */
    public void printDebug(String text, Object object) {
        this.printDebug(object.toString() + ": " + text);
    }

    /**
     * This method enable debug messages to console.
     */
    public void enableDebug() {
        this.enabled = true;
    }

}
