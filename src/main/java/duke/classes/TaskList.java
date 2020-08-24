package duke.classes;

import duke.exceptions.BlahException;
import duke.exceptions.DukeInvalidTimeException;
import duke.exceptions.EmptyDukeException;
import duke.tasks.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that carries the data structure for storing tasks and supports operations for the tasklist.
 */
public class TaskList {

    protected List<Task> todoList;

    /**
     * No-arguments constructor for new tasklist.
     */

    public TaskList() {
        this.todoList = new ArrayList<>();
    }

    /**
     * Class constructor for pre-existing lists that have data already saved onto local hard disk.
     *
     * @param list Currently saved list
     */

    public TaskList(List<Task> list) {
        this.todoList = list;
    }

    /**
     * Method to store a regular task.
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
            this.todoList.add(new Task(item, count));
        }
    }

    /**
     * Method that creates, stores and returns a Todo task.
     *
     * @param                     todo Task
     * @return                    Todo Task object
     * @throws EmptyDukeException For incomplete command
     */

    public Todo storeTodo(String todo) throws EmptyDukeException {
        int count = todoList.size() + 1;
        if (count > 100) {
            System.out.println("You have far too many pending tasks!");
            return null;
        } else if (todo.length() <= 4) {
            throw new EmptyDukeException("The description of your todo is empty.");
        } else {
            Todo curr = new Todo(todo.substring(5), count, false);
            todoList.add(curr);
            return curr;
        }
    }

    /**
     * Method that creates, stores and returns a deadline task.
     *
     * @param deadline                  Deadline task
     * @return                          Deadline task
     * @throws EmptyDukeException       For incomplete command
     * @throws DukeInvalidTimeException For incorrect dates
     */

    public Deadline storeDeadline(String deadline) throws EmptyDukeException, DukeInvalidTimeException {
        int count = todoList.size() + 1;
        if (count > 100) {
            System.out.println("You have far too many pending tasks!");
            return null;
        } else if (deadline.length() <= 8) {
            throw new EmptyDukeException("The description of your deadline is empty.");
        } else {
            Deadline curr = new Deadline(deadline.substring(9), count, false);
            todoList.add(curr);
            return curr;
        }
    }

    /**
     * Method that creates, stores and returns an event task.
     *
     * @param event                     Event task
     * @return                          Event task
     * @throws EmptyDukeException       For incomplete command
     * @throws DukeInvalidTimeException For incorrect dates
     */

    public Event storeEvent(String event) throws EmptyDukeException, DukeInvalidTimeException {
        int count = todoList.size() + 1;
        if (count > 100) {
            System.out.println("You have far too many pending tasks!");
            return null;
        } else if (event.length() <= 5) {
            throw new EmptyDukeException("The description of your event is empty.");
        } else {
            Event curr = new Event(event.substring(6), count, false);
            todoList.add(curr);
            return curr;
        }
    }

    /**
     * Method that marks a task as done and returns the task.
     *
     * @param command   Carries the 'done (integer)' command
     * @return          completed Task
     */

    public Task markDone(String command) {
        int taskNo = Character.getNumericValue(command.charAt(5)) - 1;
        Task task = todoList.get(taskNo);
        task.setDone(true);
        return task;
    }

    /**
     * Method that takes in the current list and command, before proceeding to
     * remove a task in the list based on number in the command.
     *
     * @param command   Carries the 'delete (integer)' command
     * @return          Task deleted
     */

    public Task delete(String command) {
        int taskNo = Character.getNumericValue(command.charAt(7)) - 1;
        Task task = todoList.remove(taskNo);
        for (int i = taskNo; i < todoList.size(); i++) {
            todoList.get(i).setIndex(todoList.get(i).getIndex() - 1);
        }
        return task;
    }

    public List<Task> find(String query) throws DukeInvalidTimeException {

        query = query.substring(5);
        List<Task> queriedList = new ArrayList<>();

        for (Task task : this.todoList) {
            String description = task.getDescription();
            String[] keywords;
            switch (task.getType()) {
                case TODO:
                    keywords = description.split("\\s");
                    for (String keyword : keywords) {
                        if (keyword.equals(query)) {
                            queriedList.add(new Todo(description, queriedList.size() + 1, task.hasDone()));
                        }
                    }
                    break;
                case EVENT:
                case DEADLINE:
                    // to retrieve just the activity
                    keywords = description.substring(0, description.indexOf("/") - 1).split("\\s");
                    for (String keyword : keywords) {
                        if (keyword.equals(query)) {
                            queriedList.add(task.getType() == TaskType.DEADLINE
                                    ? new Deadline(description, queriedList.size() + 1, task.hasDone())
                                    : new Event(description, queriedList.size() + 1, task.hasDone()));
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        return queriedList;
    }


}
