public class ToolBelt {
    public static void clearScreen() {
        for (int i = 0; i < 80; i++)
            System.out.println();
    }

    public static void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void slowText(String text, Boolean newLine) {
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

    public static void slowText(String text) {
        slowText(text, true);
    }

    public static void slowTextLine(String text) {
        System.out.println(text);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void fastText(String text, Boolean newLine) {
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

    public static void fastText(String text) {
        fastText(text, true);
    }

    public static int random(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }
}
