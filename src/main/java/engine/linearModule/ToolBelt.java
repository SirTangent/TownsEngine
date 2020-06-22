package engine.linearModule;

/**
This class is a general class with useful function that can be used.

@author Omar Radwan
@version 1.1.1
 */
 class ToolBelt {
    /**
     * This method clears the console by creating a lot of empty lines.
     */
    public static void clearScreen() {
        for (int i = 0; i < 300; i++)
            System.out.println();
    }

    /**
     * This method will make the program stop of a set number of seconds.
     * @param sec The number of second that you would like the program to stop for.
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
     * @param text The String you want to print out this way.
     * @param newLine If it is set to true, after the text is done printing it will print a new line.
     */
    public static void slowText(String text, Boolean newLine) {
        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i));
            try {
                Thread.sleep(55);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (newLine) {
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
     * @param text The String you want to print out this way.
     * @param newLine If it is set to true, after the text is done printing it will print a new line.
     */
    public static void fastText( String text, Boolean newLine) {
        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (newLine){
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
     * This will pick a random number between the min and max values.
     * @param min The number that is the min.
     * @param max The number that is the max.
     * @return Returns a random int between the two ranges.
     */
    public static int random(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    /**
     * This method will print out the text given char by char until it reaches the lineLength and then
     * moves down a line. It will go to the closes space so it doesnt cut of words.
     * @param text The String you want printed out.
     * @param lineLength How many char you want each line.
     */
    public static void displayText(String text, int lineLength){
        while (true) {
            if (text.length() > lineLength) {
                for (int i = lineLength; i > 0; i--) {
                    if (text.charAt(i) == ' ') {
                        ToolBelt.slowText(text.substring(0, i));
                        text = text.substring(i + 1);
                        break;
                    }
                }
            } else {
                ToolBelt.slowText(text);
                return;
            }
        }
    }
}
