package engine;
/**
This is the TownsEngine in current form.

 + Decision - Used when you want to add a Decision to the scene
 + StoryPlayer - Used as a basic info keeper and to run the start scene.
 + Scene - The basic it Scene, it will print out the desc and then give you a choose between the Decisions it has.
 + SavePoint - A subclass of Scene, it still print out the desc and then asks you if you want to save and saves the next scene after the save point.
 + DeadEndScene - A subclass of Scene, it is a dead end.

 - ToolBelt - This class has many useful function used thought the engine.

 @author Omar Radwan
 @author Wyatt Phillips
 @version beta
 */