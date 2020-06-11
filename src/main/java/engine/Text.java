package engine;

import java.util.Scanner;

public class Text extends Branch{
    private Branch nextBranch;

    public Text(String desc) {
        super(desc);
    }

    public Text(String desc, Branch nextBranch) {
        super(desc);
        this.nextBranch = nextBranch;
    }

    @Override
    public void play() {
        if (this.nextBranch == null){
            throw new IllegalStateException("Error: can not run text as there is no next branch!");
        }

        Scanner input = new Scanner(System.in);

        ToolBelt.displayText(this.desc, 70);
        System.out.println();
        ToolBelt.sleep(2);

        ToolBelt.slowText("Type anything to continue...");

        input.next();
        ToolBelt.clearScreen();

        this.nextBranch.play();
    }

    public void setNextBranch(Branch nextBranch) {
        this.nextBranch = nextBranch;
    }
}
