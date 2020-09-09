package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;





/**
 * Stores and represents tasks in the application. All the tasks in the program,
 * and its states, are stored in this list.
 */
public class TaskList {
    private static final String TYPE_TODO = "T";
    private static final String TYPE_DEADLINE = "D";
    private static final String TYPE_EVENT = "E";
    private static final String ERROR_MESSAGE = "error loading from file";
    private List<Task> lst;

    /**
     * Creates a new TaskList Object.
     */
    public TaskList() {
        lst = new ArrayList<>();
    }

    /**
     * Creates a new TaskList Object from a String source. If the String is valid, a new
     * TaskList with pre-existing tasks would be constructed. Otherwise, a DukeException will be thrown.
     *
     * @param load String to be parsed and converted to list of tasks(TaskList).
     * @throws DukeException when String cannot be successfully parsed.
     */
    public TaskList(String load) throws DukeException {
        //initialize  list of tasks
        lst = new ArrayList<>();

        try {
            //loop through load to form task list
            String[] tasks = load.split("\\|");
            for (String task : tasks) {
                String[] instruction = task.split(",");
                String firstLetter = instruction[0];
                if (firstLetter.equals(TYPE_TODO)) {
                    Task tsk = new Todo(instruction[1], instruction[2]);
                    lst.add(tsk);
                } else if (firstLetter.equals(TYPE_DEADLINE)) {
                    Task tsk = new Deadline(instruction[1], instruction[2], instruction[3]);
                    lst.add(tsk);
                } else if (firstLetter.equals(TYPE_EVENT)) {
                    Task tsk = new Event(instruction[1], instruction[2], instruction[3]);
                    lst.add(tsk);
                } else {
                    throw new DukeException(ERROR_MESSAGE);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(e.getMessage());
        } catch (Exception e) {
            throw new DukeException(ERROR_MESSAGE);
        }
    }


    /**
     * Returns the list of tasks in the program.
     *
     * @return list of tasks.
     */
    public List<Task> getList() {
        assert this.lst != null : "list attribute should be initialized";

        return this.lst;
    }

    /**
     * Return the task at a specified index of the list.
     *
     * @param index index of task.
     * @return Task Object from the list.
     */
    public Task get(int index) {
        return this.lst.get(index);
    }

    /**
     * Adds a Task into the TaskList Object.
     *
     * @param tsk Task to be added.
     */
    public void add(Task tsk) {
        assert tsk != null : "task should be valid";

        this.lst.add(tsk);
    }


    /**
     * String representation of the TaskList.
     *
     * @return String representation of the TaskList.
     */
    public String toString() {
        //initialize String
        String taskList = "";

        //get list to loop through tasks
        List<Task> tasks = this.getList();

        for (int i = 0; i < tasks.size(); i++) {
            Task targetTask = tasks.get(i);

            //append item index
            taskList += addIndex(i + 1);

            //append item
            taskList += targetTask.toString();


            boolean isNotLastTask = i < tasks.size() - 1;

            //append newLine after each task except for last task
            if (isNotLastTask) {
                taskList += "\n";
            }
        }

        return taskList;
    }

    /**
     * Return size of the task list.
     *
     * @return number of lists.
     */
    public int getSize() {
        return this.getList().size();
    }

    /**
     * Mark an item in the task list as done.
     *
     * @param itemNumber task number in the list(not index number)
     */
    public void markDone(int itemNumber) {
        assert itemNumber > 0 : "itemNumber should be a valid item number in the list";

        Task doneItem = get(itemNumber - 1);
        doneItem.markAsDone();
    }

    /**
     * Deletes a Task in the list at the specified task number.
     *
     * @param itemNumber task number in the list(not index number)
     * @return Task that was deleted from the list.
     */
    public Task delete(int itemNumber) {
        assert itemNumber > 0 : "itemNumber should be a valid item number in the list";

        Task item = get(itemNumber - 1);
        this.lst.remove(item);
        return item;
    }

    private String addIndex(int i) {
        return String.valueOf(i) + ".";
    }

}
