package engine;
/**
This is the TownsEngine in current form.

 + Decision - Used when you want to add a Decision to the scene
 + StoryPlayer - Used as a basic info keeper and to run the start scene.
 + Branch - The basic it Branch, it will print out the desc and then give you a choose between the Decisions it has.
 + SavePoint - A subclass of Branch, it still print out the desc and then asks you if you want to save and saves the next scene after the save point.
 + DeadEndBranch - A subclass of Branch, it is a dead end.

 - ToolBelt - This class has many useful function used thought the engine.
 - Playable - This interface must be implemented by all Branch classes.

 @author Omar Radwan
 @author Wyatt Phillips
 @version beta
 */