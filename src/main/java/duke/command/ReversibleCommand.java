package duke.command;

/**
 * ReversibleCommand interface implements execute() and reverse() methods
 */
public interface ReversibleCommand extends Command {

    void reverse();

}
