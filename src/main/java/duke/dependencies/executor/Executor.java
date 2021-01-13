package duke.dependencies.executor;


import duke.dependencies.executable.CommandType;
import duke.dependencies.executable.Executable;
import duke.dependencies.storage.TaskList;
import duke.dependencies.task.Task;

import static duke.dependencies.executable.CommandType.ADD;
import static duke.dependencies.executable.CommandType.CLEAR;
import static duke.dependencies.executable.CommandType.DELETE;
import static duke.dependencies.executable.CommandType.DONE;
import static duke.dependencies.executable.CommandType.FIND;
import static duke.dependencies.executable.CommandType.INVALID;
import static duke.dependencies.executable.CommandType.LIST;

/**
 * Class that enables the logical manipulation of given Executable. Interprets the type of Command and
 * carries out the command on the associated Task and the task list of the user.
 *
 */
public class Executor {

    private static final TaskList TASK_LIST = TaskList.initStorage();

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

            case CLEAR:
                setState(CLEAR);
                break;

            default:
                setState(INVALID);  // Should never reached this stage.
                break;

        }
        return execAndReturn(executable);
    }

    /**
     * Returns number of completed tasks in the list.
     * @return Integer.
     */
    public int getNumOfCompletedTasks() {
        return TASK_LIST.getNumOfCompleted();
    }

    /**
     * Returns number of incomplete tasks in the list.
     * @return Integer.
     */
    public int getNumOfIncompleteTasks() {
        return TASK_LIST.getNumOfIncomplete();
    }

    /**
     * Returns number of items in the list.
     * @return Integer.
     */
    public int getListSize() {
        return TASK_LIST.getListSize();
    }


    /* -------------------------------------------------------------------------------------------------------------- */


    // TODO: Ideally this class should not be returning strings. String should be returned in the Parser
    private String execAndReturn(Executable e) {

        // Block scoped the variable declaration in the cases.
        switch(commandState) {
            case LIST: {
                return TASK_LIST.getTodosInList();
            }
            case DONE: {
                /* C-MassOperations */
                // Done command would have a task of "1 2 3 4"
                String[] nums = e.getTask().showTaskDescription().split("[\\D]+");
                Integer[] arr = new Integer[nums.length];
                for (int i = 0; i < nums.length; i++) {
                    arr[i] = Integer.valueOf(nums[i]);
                }
                return TASK_LIST.done(arr);
            }
            case DELETE: {
                String[] nums = e.getTask().showTaskDescription().split("[\\D]+");
                Integer[] arr = new Integer[nums.length];
                for (int i = 0; i < nums.length; i++) {
                    arr[i] = Integer.valueOf(nums[i]);
                }
                return TASK_LIST.deleteTask(arr);
            }
            case FIND: {
                String keyword = e.getTask().showTaskDescription();
                return TASK_LIST.findMatching(keyword);
            }
            case ADD: {
                Task t = e.getTask();
                return TASK_LIST.add(t);
            }
            case CLEAR: {
                return TASK_LIST.clearList();
            }
            default: {
                return "Error";   // This should not happen.
            }
        }
    }

    private void setState(CommandType c) {
        this.commandState = c;
    }

}
