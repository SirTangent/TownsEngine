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
        StoryPlayer player = new StoryPlayer( "Programmers' Masterpiece","Get ready for a ride cus this is the story I used to check my code and make sure it was working. Hold onto your fucking hats cus if this makes any sense your fucking lucky. Maybe grab a drink or teo before starting just to get though it. Good luck","Omar Radwan");

        Text testering = new Text("This is some text without a desigen");

        Decision wakeup = new Decision("Wake up");
        Decision Die = new Decision("Die");
        Branch start = new Branch("This is the start");
        start.addDecision(wakeup);
        start.addDecision(Die);
        player.setStartBranch(start);

        Decision good = new Decision("Good");
        Branch _Die = new Branch("you have died");
        _Die.addDecision(good);
        _Die.addDecision(good);

        Die.setNextBranch(_Die);

        DeadEndBranch deadEnd = new DeadEndBranch("You are pleased", _Die, player);

        good.setNextBranch(deadEnd);

        //-------------------------------------------------------------------------


        Branch wake_up = new Branch("You wake up and see a blue sky");
        SavePoint firstSave = new SavePoint("This is the first Save point.", wake_up);
        wakeup.setNextBranch(testering);
        testering.setNextBranch(firstSave);

        Decision lookAtSky = new Decision("Look up at the sky");
        Decision lookAtGrass = new Decision("Feel the nice tall grass");
        wake_up.addDecision(lookAtGrass);
        wake_up.addDecision(lookAtSky);

        DeadEndBranch skyBurn = new DeadEndBranch("You look up at the sky and it blinds you.", wake_up, player);
        lookAtSky.setNextBranch(skyBurn);

        Branch tallGrass = new Branch("You feel the tall grass. Careful, it might feel you back.");
        lookAtGrass.setNextBranch(tallGrass);
        Decision stop = new Decision("Stop");
        Decision dont = new Decision("Don't Stop");
        tallGrass.addDecision(stop);
        tallGrass.addDecision(dont);

        Branch tallGrassCon = new Branch("You can't stop. it feels to nice. Suddenly, a black hole opens up.");
        Branch tallGrassConn = new Branch("You don't want to stop. Suddenly, a black hole opens up.");
        stop.setNextBranch(tallGrassCon);
        dont.setNextBranch(tallGrassConn);

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

        Branch black = new Branch("You walk thought the black hole and everything is white");
        second.setNextBranch(black);
        Decision makeU = new Decision("Make the universe (your god now)");
        Decision die2 = new Decision("I Still just want to die cus i'm a worthless piece of shit", _Die);
        black.addDecision(makeU);
        black.addDecision(die2);

        DeadEndBranch finallys = new DeadEndBranch("You tried to play god and god did'nt like it so he edited to code so you die.", black, player);
        makeU.setNextBranch(finallys);

        //-------------------------------------------------------------------------

        player.playLoop();
    }
}