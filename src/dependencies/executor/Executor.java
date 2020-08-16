package dependencies.executor;

import dependencies.storage.Store;
import static dependencies.executor.Commands.*;

public class Executor {

    private static final Store storage = Store.initStorage(100);
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

    /**
     * Temporary function to satisfy the Level-2 requirements.
     * @param command
     * @return String indicating the finished command
     */
    public String level2Exec(String command) {
        if (command.equals(LIST_COMMAND)) {

        }
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
