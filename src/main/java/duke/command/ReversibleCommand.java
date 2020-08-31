package duke.command;

/**
 * ReversibleCommand interface implements execute() and reverse() methods
 */
public interface ReversibleCommand extends Command {

    /**
     * A ReversibleCommand can be undone
     */
    void reverse();

}
