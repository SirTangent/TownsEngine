import java.util.InputMismatchException;
import java.util.Scanner;

public class SavePoint extends Scene{
    private Scene nextScene;

    public SavePoint(String desc, Scene nextScene) {
        super(desc);
        this.nextScene = nextScene;
    }

    public void run(){
            super.displayText("1|Yes", 70);
            super.displayText("2|No", 70);

        int number = 0;
        while(true){
            System.out.println();
            System.out.print(">");

            Scanner input = new Scanner(System.in);
            try{
                number = input.nextInt();

                if (number < 1 || number > 2) {
                    System.out.print("Error: Not a valid option!");
                    ToolBelt.sleep(1);
                } else {
                    break;
                }
            } catch (InputMismatchException ex){
                System.out.print("Error: Must be a number!");
                ToolBelt.sleep(1);
            }
            input.close();
        }
        ToolBelt.clearScreen();

        if (number == 1){
            super.currSave = this.nextScene;
        }
        this.nextScene.run();
    }
}
