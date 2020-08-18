package dependencies.executor;

import dependencies.storage.Store;
import dependencies.task.Task;
import dependencies.executable.*;

import static dependencies.executable.CommandType.*;

public class Executor {

    private static final Store storage = Store.initStorage();
    private static final String ADD_COMMAND = "add";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String DELETE_COMMAND = "delete";

    private CommandType commandState;

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

    private void setState(CommandType c) {
        this.commandState = c;
    }

    /**
     * Executed the given command.
     *
     * @param executable
     * @return string specifying what happened/what was done
     */
    public String receiveAndExec(Executable executable) {
        // TODO: Adding of new commands is to be done here.
        switch(executable.getType()) {
            case LIST: {
                setState(LIST);
                break;
            }
            case ADD: {
                setState(ADD);
                break;
            }
            case DELETE: {
                setState(DELETE);
                break;
            }
            case DONE: {
                setState(DONE);
                break;
            }
            default: {
                setState(INVALID);  // Should never reached this stage.
                break;
            }
        }
        return execAndReturn(executable);
    }



    private String execAndReturn(Executable e) {
        String reply;
        switch(commandState) {
            case LIST: {
                reply = storage.getTodosInList();
                StringBuilder sb = new StringBuilder();
                sb.append("Here are the task in your list:\n")
                        .append(reply)
                        .append("\n")
                        .append("Stop whining and start rolling.");
                return sb.toString();
            }
            case ADD: {
                Task t = e.getTask();
                reply = storage.add(t);
                return String.format("Got it! I have added the task:\n%s", reply);
            }
            case DONE: {
                // Done command would have a task of "1 2 3 4"
                String[] nums = e.getTask().showTask().split("[\\D]+");
                Integer[] arr = new Integer[nums.length];
                for (int i = 0; i < nums.length; i++) {
                    arr[i] = Integer.valueOf(nums[i]);
                }
                reply = storage.done(arr);
                return String.format("Congratz! I will marked this task as completed for you!\n%s\n" +
                        "Keep up the good work and continue to stay motivated.", reply);
            }
            default: {
                return "Error";
            }
        }
    }


}
