package dependencies.executor;

import dependencies.storage.Store;
import dependencies.task.Task;

import static dependencies.executor.Commands.*;

public class Executor {

    private static final Store storage = Store.initStorage(100);
    private static final String ADD_COMMAND = "add";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String DELETE_COMMAND = "delete";

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

    /**
     * Executed the given command.
     *
     * @param command
     * @return string specifying what happened/what was done
     */
    public String receiveAndExec(String command) {
        switch(command) {
            case LIST_COMMAND: {
                setState(LIST);
                break;
            }
            default: {
                setState(INVALID);  // Should never reached this stage.
                break;
            }
        }
        return execAndReturn();
    }

    /**
     * Executed the given command on the given task.
     *
     * @param command
     * @return string specifying what happened/what was done
     */
    public String receiveAndExec(String command, Task task) {
        switch(command) {
            case ADD_COMMAND: {
                setState(ADD);
                break;
            }
            case DONE_COMMAND: {
                setState(DONE);
                break;
            }
            default: {
                setState(INVALID);  // Should never reached this stage.
                break;
            }
        }
        return execAndReturn(task);
    }

    private String execAndReturn() {
        switch(commandState) {
            case LIST: {
                String reply = storage.getTodosInList();
                StringBuilder sb = new StringBuilder();
                sb.append("Here are the task in your list:\n")
                        .append(reply)
                        .append("\n")
                        .append("Stop whining and start rolling.");
                return sb.toString();
            }
            default: {
                return "Error";
            }
        }
    }

    private String execAndReturn(Task task) {
        switch(commandState) {
            case ADD: {
                String reply = storage.add(task);
                return String.format("Got it! I have added the task:", task.toString());
            }
            default: {
                return "Error";
            }
        }
    }

    private String execAndReturn(String[] nums) {
        switch(commandState) {
            case DONE: {
                String reply = storage.done(nums);
                return String.format("Congratz! I will marked this task as completed for you!\n%s\n" +
                        "Keep up the good work and continue to stay motivated.", reply);
            }
            default: {
                return "Error";
            }
        }
    }





}
