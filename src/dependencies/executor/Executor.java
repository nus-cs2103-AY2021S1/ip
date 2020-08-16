package dependencies.executor;

import static dependencies.executor.Commands.*;

public class Executor {

    private static final String ADD_COMMAND = "add";
    private static final String LIST_COMMAND = "list";
    private Commands commandState;

    /** Private constructor. */
    private Executor() {}

    /**
     * Initializer for the executor.
     *
     * @return
     */
    public static Executor initExecutor() {
        return new Executor();
    }

    private void setState(Commands c) {
        this.commandState = c;
    }

    private void exec(Commands c) {

    }

    public String receiveAndExec(String command) {
        switch(command) {
            case ADD_COMMAND: {
                setState(ADD);
                break;
            }
            case LIST_COMMAND: {
                setState(LIST);
                break;
            }
            default: {
                setState(INVALID);
                break;
            }
        }
        exec(commandState);
        return finishWith(commandState);
    }

    private String finishWith(Commands commands) {
        return "";
    }



}
