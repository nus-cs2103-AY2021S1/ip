package olivia.command;

import olivia.resource.Wrapper;

import java.util.List;

/**
 * ExitCommand class that terminates the current instance of Olivia.
 */

public class ExitCommand implements Command {

    /**
     * Terminates the current instance of Olivia by indicating to the
     * Wrapper that an ExitCommand has been sent.
     * @param wrapper contains Olivia's Storage, TaskList and Ui objects.
     * @param input list that contains the input arguments for the command;
     *              should be empty in this case.
     * @return output String to the user; empty in this case.
     */

    @Override
    public String apply(Wrapper wrapper, List<String> input) {
        wrapper.exit();
        return "";
    }
}
