import org.jetbrains.annotations.NotNull;

/**
This class is a general class with useful function that can be used in more then one place.

@author Omar Radwan
@version 1.0.0
 */
public class ToolBelt {
    /**
     * This method clears the console by creating a lot of empty lines.
     */
    public static void clearScreen() {
        for (int i = 0; i < 80; i++)
            System.out.println();
    }

    /**
     * This method will make the program stop of a set number of seconds
     * @param sec The number of second that you would like the program to stop for
     */
    public static void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will print out the line char by char at a slow speed.
     * @param text The String you want to print out this way
     * @param newLine If it is set to true, after the text is done printing it will print a new line.
     */
    public static void slowText(@NotNull String text, Boolean newLine) {
        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i));
            try {
                Thread.sleep(55);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (newLine == true) {
            System.out.println();
        }
    }

    /**
     * This is will call slowText(String text, Boolean newLine). This acts as a default value input for newLine.
     * @param text see slowText(String text, Boolean newLine).
     */
    public static void slowText(String text) {
        slowText(text, true);
    }

    /**
     * This method will print out the line char by char at a fast speed.
     * @param textThe String you want to print out this way
     * @param newLine If it is set to true, after the text is done printing it will print a new line.
     */
    public static void fastText(@NotNull String text, Boolean newLine) {
        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (newLine = true){
            System.out.println();
        }
    }

    /**
     * This is will call fastText(String text, Boolean newLine). This acts as a default value input for newLine.
     * @param text see fastText(String text, Boolean newLine).
     */
    public static void fastText(String text) {
        fastText(text, true);
    }

    /**
     * This will pick a random number between the min and max values
     * @param min The number that is the min
     * @param max The number that is the max
     * @return Returns a random int between the two ranges
     */
    public static int random(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }
}
