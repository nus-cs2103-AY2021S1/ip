package duke.designpattern.command;

/**
 * ReversibleCommand interface implements execute() and reverse() methods
 */
public interface ReversibleExecutable extends Executable {

    /**
     * All ReversibleExecutables can be reversed
     */
    void reverse();


}
