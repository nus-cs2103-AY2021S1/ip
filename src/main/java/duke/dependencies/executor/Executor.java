package duke.dependencies.executor;

import duke.dependencies.storage.TaskList;
import duke.dependencies.task.Task;
import duke.dependencies.executable.*;

import static duke.dependencies.executable.CommandType.*;

public class Executor {

    private static final TaskList storage = TaskList.initStorage();
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
     * @return string specifying what happened/what was done (no newline character at end of reply)
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

    // TODO: Ideally this class should not be returning strings. String should be returned in the Parser
    private String execAndReturn(Executable e) {
        String reply;
        switch(commandState) {
            case LIST: {
                reply = storage.getTodosInList();
                StringBuilder sb = new StringBuilder();
                sb.append("Here are the tasks in your list:\n")
                        .append(reply);
                return sb.toString();
            }
            case ADD: {
                Task t = e.getTask();
                reply = storage.add(t);
                return String.format("Got it! I have added the task:\n%s\n"
                        + "Now you have %d tasks in the list.",
                        reply,
                        storage.getListSize());
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
                        "Keep up the good work and continue to stay motivated.\n"
                        + "You've got %d task left to be completed!",
                        reply,
                        storage.getNumOfIncompleted());
            }
            case DELETE: {
                String nums = e.getTask().showTask();
                reply = storage.deleteTask(Integer.valueOf(nums));
                return String.format("Noted. I've removed this task:\n%s\n"
                        + "Now you have %d tasks left in the list.",
                        reply,
                        storage.getListSize());
            }
            default: {
                return "Error";
            }
        }
    }


}
