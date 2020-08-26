package duke.dependencies.executor;

import duke.Duke;
import duke.dependencies.dukeexceptions.DukeException;
import duke.dependencies.executable.CommandType;
import duke.dependencies.executable.Executable;

import duke.dependencies.storage.TaskList;

import duke.dependencies.task.Task;

import static duke.dependencies.executable.CommandType.*;

/**
 * Class that enables the logical manipulation of given Executable. Interprets the type of Command and
 * carries out the command on the associated Task and the task list of the user.
 *
 */
public class Executor {

    private static final TaskList storage = TaskList.initStorage();
    private static final String ADD_COMMAND = "add";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";

    /* Half-assed attempt at concurrency lock.
    There should be no need for concurrency
    in this application. */
    private CommandType commandState;

    /** Private constructor. */
    private Executor() {}

    /**
     * Initializer for the executor. Returns the Executor object.
     *
     * @return Executor object.
     */
    public static Executor initExecutor() {
        return new Executor();
    }

    /**
     * Executes the given command and returns with a reply indicating the state of completion of given Executable.
     *
     * @param executable The command to be executed.
     * @return String specifying what happened/what was done (no newline character at end of reply).
     */
    public String receiveAndExec(Executable executable) {
        // TODO: Adding of new commands is to be done here.
        switch(executable.getType()) {
            case LIST:
                setState(LIST);
                break;

            case ADD:
                setState(ADD);
                break;

            case DELETE:
                setState(DELETE);
                break;

            case DONE:
                setState(DONE);
                break;

            case FIND:
                setState(FIND);
                break;

            case GETCOMPLETED:
                setState(GETCOMPLETED);
                break;

            case GETINCOMPLETE:
                setState(GETINCOMPLETE);
                break;

            default:
                setState(INVALID);  // Should never reached this stage.
                break;

        }
        return execAndReturn(executable);
    }

    public int getNumOfCompletedTasks() {
        return storage.getNumOfCompleted();
    }

    public int getNumOfIncompleteTasks() {
        return storage.getNumOfIncomplete();
    }

    public int getListSize() {
        return storage.getListSize();
    }


    /* -------------------------------------------------------------------------------------------------------------- */


    // TODO: Ideally this class should not be returning strings. String should be returned in the Parser
    private String execAndReturn(Executable e) {

        // Block scoped the variable declaration in the cases.
        switch(commandState) {
            case LIST: {
                return storage.getTodosInList();
            }
            case DONE: {
                // Done command would have a task of "1 2 3 4"
                String[] nums = e.getTask().showTaskDescription().split("[\\D]+");
                Integer[] arr = new Integer[nums.length];
                for (int i = 0; i < nums.length; i++) {
                    arr[i] = Integer.valueOf(nums[i]);
                }
                return storage.done(arr);
            }
            case DELETE: {
                String nums = e.getTask().showTaskDescription();
                return storage.deleteTask(Integer.valueOf(nums));
            }
            case FIND: {
                String keyword = e.getTask().showTaskDescription();
                return storage.findMatching(keyword);
            }
            case ADD: {
                Task t = e.getTask();
                return storage.add(t);
            }
            default: {
                return "Error";
            }
        }
    }

    private void setState(CommandType c) {
        this.commandState = c;
    }

}
