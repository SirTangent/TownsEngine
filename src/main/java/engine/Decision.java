package engine;

/**
 * This is the engine's Decision class. It is used when you want to make a decision in a scene.
 *
 * @author Omar Radwan
 * @author Wyatt Phillips
 * @version 1.0.0
 */
public class Decision {
    private Branch nextBranch;
    private String PrintableName;

    public Decision(String name, Branch nextBranch){
        this(name);
        this.nextBranch = nextBranch;
    }

    public Decision(String name){
        this.PrintableName = name;
    }

    public String getName(){
        return this.PrintableName;
    }

    /**
     * This returns the nextBranch field. Will throw a IllegalStateException if the nextBranch field is null.
     * @return The nextBranch field.
     */
    public Branch getNextBranch() {
        if (this.nextBranch == null) {
            throw new IllegalStateException("Error: Can not get next scene because it is null!");
        }
        return this.nextBranch;
    }

    /**
     * This is set the nextBranch branch to the branch input.
     * @param branch Branch or class that extends Branch that you want to be the next branch.
     */
    public void setNextBranch(Branch branch){
        this.nextBranch = branch;
    }
}