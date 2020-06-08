package exampleStorys;
import engine.*;

/**
 * This is my test story to test if my code is working
 *
 * @author Omar Radwan
 * @version 1.0.0
 */
public class programmersDream {
    public static void main (String[] args){
        /*
        WARNING: DONT NOT READ
        jk I dont care
         */
        Decision wakeup = new Decision("Wake up");
        Decision Die = new Decision("Die");
        Scene start = new Scene("This is the start");
        start.addDecision(wakeup);
        start.addDecision(Die);


        Decision good = new Decision("Good");
        Scene _Die = new Scene("you have died");
        _Die.addDecision(good);
        _Die.addDecision(good);

        Die.setNextScene(_Die);

        DeadEndScene deadEnd = new DeadEndScene("You are pleased", _Die);

        good.setNextScene(deadEnd);

        //-------------------------------------------------------------------------

        Scene wake_up = new Scene("You wake up and see a blue sky");
        SavePoint firstSave = new SavePoint("This is the first Save point.", wake_up);
        wakeup.setNextScene(firstSave);

        Decision lookAtSky = new Decision("Look up at the sky");
        Decision lookAtGrass = new Decision("Feel the nice tall grass");
        wake_up.addDecision(lookAtGrass);
        wake_up.addDecision(lookAtSky);

        DeadEndScene skyBurn = new DeadEndScene("You look up at the sky and it blinds you.", wake_up);
        lookAtSky.setNextScene(skyBurn);

        Scene tallGrass = new Scene("You feel the tall grass. Careful, it might feel you back.");
        lookAtGrass.setNextScene(tallGrass);
        Decision stop = new Decision("Stop");
        Decision dont = new Decision("Don't Stop");
        tallGrass.addDecision(stop);
        tallGrass.addDecision(dont);

        Scene tallGrassCon = new Scene("You can't stop. it feels to nice. Suddenly, a black hole opens up.");
        Scene tallGrassConn = new Scene("You don't want to stop. Suddenly, a black hole opens up.");
        stop.setNextScene(tallGrassCon);
        dont.setNextScene(tallGrassConn);

        SavePoint second = new SavePoint("You reached a second save point. I have no idea how you made it this far.");

        Decision walk1 = new Decision("Walk through the black hole", second);
        Decision walk2 = new Decision("Pick number 1", second);
        Decision walk3 = new Decision("No pick me. I'll walk you through the black hole", second);
        Decision walk4 = new Decision("Don't listen to any of them, pick me.", second);
        Decision walk6 = new Decision("it does'nt matter what you pick. we'll all die anyway.", second);

        tallGrassCon.addDecision(walk1);
        tallGrassCon.addDecision(walk2);
        tallGrassCon.addDecision(walk3);
        tallGrassCon.addDecision(walk4);
        tallGrassCon.addDecision(walk6);

        tallGrassConn.addDecision(walk1);
        tallGrassConn.addDecision(walk2);
        tallGrassConn.addDecision(walk3);
        tallGrassConn.addDecision(walk4);
        tallGrassConn.addDecision(walk6);

        Scene black = new Scene("You walk thought the black hole and everything is white");
        second.setNextScene(black);
        Decision makeU = new Decision("Make the universe (your god now)");
        Decision die2 = new Decision("I Still just want to die cus i'm a worthless piece of shit", _Die);
        black.addDecision(makeU);
        black.addDecision(die2);

        DeadEndScene finallys = new DeadEndScene("You tried to play god and god did'nt like it so he edited to code so you die.", black);
        makeU.setNextScene(finallys);

        //-------------------------------------------------------------------------

        StoryPlayer player = new StoryPlayer(start, "Programmers' Masterpiece","Get ready for a ride cus this is the story I used to check my code and make sure it was working. Hold onto your fucking hats cus if this makes any sense your fucking lucky. Maybe grab a drink or teo before starting just to get though it. Good luck","Omar Radwan");
        player.playLoop();
    }
}