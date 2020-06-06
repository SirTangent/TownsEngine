public class TEST {
    public static void main (String[] args){
        Scene test = new Scene("This is a test scene. I will be adding options to this scene as I go so I can test if it is working or not. If it is not working, I will try until it works and I will make sure I stop hitting caps lock because it is getting annoying. Is this working.");

        Decision tester3 = new Decision(test, "feed a duck");
        test.addDecision(tester3);

        Decision tester5 = new Decision(test, "Save a cat");
        test.addDecision(tester5);

        Decision tester6 = new Decision(test, "feed a cat");
        test.addDecision(tester6);

        Decision tester7 = new Decision(test, "feed a dog");
        test.addDecision(tester7);

        test.run();
    }
}