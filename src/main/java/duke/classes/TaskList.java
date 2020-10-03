package duke.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import duke.exceptions.BlahException;
import duke.exceptions.DukeExcessException;
import duke.exceptions.DukeInvalidTimeException;
import duke.exceptions.EmptyDukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskType;
import duke.tasks.Todo;

/**
 * Class that carries the data structure for storing tasks and supports operations for the tasklist.
 */
public class TaskList {

    protected List<Task> todoList;

    /**
     * No-arguments constructor for new tasklist.
     */

    public TaskList() {
        todoList = new ArrayList<>();
    }

    /**
     * Class constructor for pre-existing lists that have data already saved onto local hard disk.
     *
     * @param list Currently saved list
     */

    public TaskList(List<Task> list) {
        todoList = list;
    }

    /**
     * Stores a regular task.
     *
     * @param item           Task
     * @throws BlahException Thrown for 'blah' commands
     */

    public void storeTask(String item) throws BlahException {
        int count = todoList.size() + 1;
        if (count > 100) {
            System.out.println("You have far too many pending tasks!");
        } else if (item.equals("blah")) {
            throw new BlahException();
        } else {
            todoList.add(new Task(item, count));
        }
    }

    /**
     * Creates, stores and returns a Todo task.
     *
     * @param                     todo Task
     * @return                    Todo Task object
     * @throws EmptyDukeException For incomplete command
     * @throws DukeExcessException For excess tasks
     */
    public Todo storeTodo(String todo) throws EmptyDukeException, DukeExcessException {
        int count = todoList.size() + 1;
        countCheck(count);
        /*if (count > 100) {
            System.out.println("You have far too many pending tasks!");
            return null;
        } else*/ if (todo.length() <= 4) {
            throw new EmptyDukeException("The description of your todo is empty.");
        } else {
            Todo curr = new Todo(todo.substring(5), count, false, "N");
            todoList.add(curr);
            return curr;
        }
    }

    /**
     * Creates, stores and returns a deadline task.
     *
     * @param deadline                  Deadline task
     * @return                          Deadline task
     * @throws EmptyDukeException       For incomplete command
     * @throws DukeInvalidTimeException For incorrect dates
     * @throws DukeExcessException For excess tasks
     */

    public Deadline storeDeadline(String deadline) throws EmptyDukeException, DukeInvalidTimeException,
            DukeExcessException {
        int count = todoList.size() + 1;
        countCheck(count);
        if (deadline.length() <= 8) {
            throw new EmptyDukeException("The description of your deadline is empty.");
        } else {
            Deadline curr = new Deadline(deadline.substring(9), count, false, "N");
            todoList.add(curr);
            return curr;
        }
    }

    /**
     * Creates, stores and returns an event task.
     *
     * @param event                     Event task
     * @return                          Event task
     * @throws EmptyDukeException       For incomplete command
     * @throws DukeInvalidTimeException For incorrect dates
     * @throws DukeExcessException For excess tasks
     */

    public Event storeEvent(String event) throws EmptyDukeException, DukeInvalidTimeException, DukeExcessException {
        int count = todoList.size() + 1;
        countCheck(count);
        if (event.length() <= 5) {
            throw new EmptyDukeException("The description of your event is empty.");
        } else {
            Event curr = new Event(event.substring(6), count, false, "N");
            todoList.add(curr);
            return curr;
        }
    }

    /**
     * Marks a task as done and returns the task.
     *
     * @param command   Carries the 'done (integer)' command
     * @return          completed Task
     */

    public Task markDone(String command) {
        int taskNo = Integer.parseInt(command.substring(5)) - 1;
        Task task = todoList.get(taskNo);
        task.setDone(true);
        return task;
    }

    /**
     * Takes in the current list and command, before proceeding to
     * remove a task in the list based on number in the command.
     *
     * @param command   Carries the 'delete (integer)' command
     * @return          Task deleted
     */

    public Task delete(String command) {
        int taskNo = Integer.parseInt(command.substring(7)) - 1;
        System.out.println(taskNo);
        Task task = todoList.remove(taskNo);
        todoList.forEach((item) -> {
            if (item.getIndex() > taskNo) {
                item.setIndex(item.getIndex() - 1);
            }
        });
        return task;
    }
    /**
     * Finds the tasks that contain words that match the given word
     * and then returns it in a list format.
     *
     * @param query Key phrase to be found in task
     * @return List of matching tasks
     * @throws DukeInvalidTimeException Error thrown for deadline/event tasks with invalid dates.
     */

    public List<Task> find(String query) throws DukeInvalidTimeException {
        query = query.substring(5);
        List<Task> queriedTasks = new ArrayList<>();
        Function<String, String[]> stringSplit = str -> str.split("\\s");
        for (Task task : todoList) {
            String finalQuery = query;
            String description = task.getDescription();
            String[] keywords;
            switch (task.getType()) {
            case TODO:
                keywords = stringSplit.apply(description);
                Arrays.stream(keywords)
                        .filter((keyword) -> keyword.equals(finalQuery))
                        .forEach((keyword) -> {
                            queriedTasks.add(new Todo(description, queriedTasks.size() + 1,
                                    task.hasDone(), task.getTag()));
                        });
                break;
            case EVENT:
            case DEADLINE:
                // to retrieve just the activity
                keywords = stringSplit.apply(description.substring(0, description.indexOf("/") - 1));
                // add to collection
                for (String keyword : keywords) {
                    if (keyword.equals(query)) {
                        queriedTasks.add(insertTasks(queriedTasks.size() + 1, task, description));
                    }
                }
                break;
            default:
                break;
            }
        }
        return queriedTasks;
    }

    /**
     * Decides & re-indexes tasks of deadline/event type queried by user into collection.
     * @param task Current task
     * @param description Content of task
     * @return Task
     * @throws DukeInvalidTimeException if there is any index timings in task
     */
    private Task insertTasks(int len, Task task, String description) throws DukeInvalidTimeException {
        return task.getType() == TaskType.DEADLINE
                ? new Deadline(description, len, task.hasDone(), task.getTag())
                : new Event(description, len, task.hasDone(), task.getTag());
    }

    /**
     * Checks the size of the current task list.
     * @param size Number of tasks in list
     * @throws DukeExcessException IF there is more than 100 tasks inclusive of the current one
     */
    private void countCheck(int size) throws DukeExcessException {
        if (size > 100) {
            throw new DukeExcessException();
        }
    }

    /**
     * Tags a task.
     * @param command String of command
     * @return Task tagged
     */
    public Task tagTask(String command) {
        Task task = todoList.get(Character.getNumericValue(command.charAt(4)) - 1);
        task.setTag(command.substring(6));
        return task;
    }
}
