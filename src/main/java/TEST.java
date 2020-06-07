import java.util.Dictionary;

public class TEST {
    public static void main (String[] args){
        Decision Wakeup = new Decision("Wake up");
        Decision Die = new Decision("Die");
        Scene start = new Scene("This is the start");
        start.addDecision(Wakeup);
        start.addDecision(Die);


        Decision good = new Decision("Good");
        Scene _Die = new Scene("you have died");
        _Die.addDecision(good);
        _Die.addDecision(good);

        Die.setNextScene(_Die);

        StoryPlayer player = new StoryPlayer(start);
        player.playLoop();
    }
}