package duke.tasks;

import duke.DukeException;
import duke.Storage;

import java.util.ArrayList;
import java.util.List;

/**
 * <code>TaskManager</code> handles every operation related to tasks.
 */
public class TaskManager {
    private List<Todo> todos;
    private List<Deadline> deadlines;
    private List<Event> events;

    public TaskManager() {
        todos = new ArrayList<>();
        deadlines = new ArrayList<>();
        events = new ArrayList<>();
    }

    /**
     * Sets the todo list with the given argument.
     * @param todos the todo list to be set.
     */
    public void initialiseTodos(List<Todo> todos) {
        this.todos = todos;
    }

    /**
     * Sets the deadline list with the given argument.
     * @param deadlines the deadline list to be set.
     */
    public void initialiseDeadlines(List<Deadline> deadlines) {
        this.deadlines = deadlines;
    }

    /**
     * Sets the event list with the given argument.
     * @param events the event list to be set.
     */
    public void initialiseEvents(List<Event> events) {
        this.events = events;
    }

    /**
     * Adds a todo.
     * @param todo the todo to be added.
     * @throws DukeException if something went wrong while saving.
     */
    public void addTodo(Todo todo) throws DukeException {
        todos.add(todo);
        save();
    }

    /**
     * Adds a deadline and sorts the deadline list.
     * @param deadline the deadline to be added.
     * @throws DukeException if something went wrong while saving.
     */
    public void addDeadline(Deadline deadline) throws DukeException {
        deadlines.add(deadline);
        deadlines.sort(Deadline::compareTo);
        save();
    }

    /**
     * Adds a event and sorts the events list.
     * @param event the event to be added.
     * @throws DukeException if something went wrong while saving.
     */
    public void addEvent(Event event) throws DukeException {
        events.add(event);
        events.sort(Event::compareTo);
        save();
    }

    /**
     * Gets each task from the three lists and saves them to Task.txt file.
     * @throws DukeException if something went wrong while writing to the file.
     */
    private void save() throws DukeException {
        StringBuilder sb = new StringBuilder();
        for (Todo todo : todos) {
            sb.append(todo.saveText()).append("\n");
        }
        for (Deadline deadline : deadlines) {
            sb.append(deadline.saveText()).append("\n");
        }
        for (Event event : events) {
            sb.append(event.saveText()).append("\n");
        }
        Storage.writeTasksFile(sb.toString());
    }

    /**
     * Creates a string representation of all the tasks.
     * @return the string representation of all the tasks
     */
    public String listTasks() {
        int i = 1;
        StringBuilder sb = new StringBuilder();
        if (!todos.isEmpty()) {
            sb.append("Here are your todos:\n");
            for (Todo todo : todos) {
                sb.append(i).append(". ").append(todo).append("\n");
                i++;
            }
            sb.append("\n");
        } else {
            sb.append("You have no todos.\n");
        }
        if (!deadlines.isEmpty()) {
            sb.append("Here are your deadlines:\n");
            for (Deadline deadline : deadlines) {
                sb.append(i).append(". ").append(deadline).append("\n");
                i++;
            }
            sb.append("\n");
        } else {
            sb.append("You have no deadlines.\n");
        }
        if (!events.isEmpty()) {
            sb.append("Here are your events:\n");
            for (Event event : events) {
                sb.append(i).append(". ").append(event).append("\n");
                i++;
            }
            sb.append("\n");
        } else {
            sb.append("You have no events.\n");
        }
        return sb.toString();
    }

    /**
     * Marks a particular task as done.
     * @param taskNum the number of the task in the task list
     * @return the <code>Task</code> that is marked done
     * @throws DukeException if the provided task number is out of the range of number of tasks.
     */
    public Task markDone(int taskNum) throws DukeException {
        checkValidIndex(taskNum);
        Task task;
        if (taskNum <= todos.size()) {
            task = todos.get(taskNum - 1);
        } else if (taskNum <= todos.size() + deadlines.size()) {
            int ind = taskNum - todos.size() - 1;
            task = deadlines.get(ind);
        } else {
            int ind = taskNum - todos.size() - deadlines.size() - 1;
            task = events.get(ind);
        }
        task.setCompleted();
        save();
        return task;
    }

    private void checkValidIndex(int taskNum) throws DukeException {
        int totalNumber = getTotalNumberOfItems();
        if (taskNum < 0 || taskNum > totalNumber) {
            String errorResponse = String.format("You gave an invalid task number. Give a number from 0 to %d.", totalNumber);
            throw new DukeException(errorResponse);
        }
    }

    /**
     * Deletes a task from the list.
     * @param taskNum the task number of the task to be deleted
     * @throws DukeException if the provided task number is out of bounds of the range of the <code>ArrayList</code>
     */
    public Task deleteTask(int taskNum) throws DukeException {
        checkValidIndex(taskNum);
        Task task;
        if (taskNum <= todos.size()) {
            task = todos.remove(taskNum - 1);
        } else if (taskNum <= todos.size() + deadlines.size()) {
            task = deadlines.remove(taskNum - 1 - todos.size());
        } else {
            task = events.remove(taskNum - 1 - todos.size() - deadlines.size());
        }
        save();
        return task;
    }

    private boolean hasKeyword(String keyword, Task task) {
        return task.getName().toLowerCase().contains((keyword.toLowerCase()));
    }

    /**
     * Finds a task with a given keyword in its name.
     * @param keyword the keyword to search for
     * @return the string represenation of the list of duke.tasks found. If none found, it will
     * return a message indicating that no asks were found.
     */
    public String findTask(String keyword) {
        List<Task> temp = new ArrayList<>();
        todos.stream().filter(todo -> hasKeyword(keyword, todo)).forEach(temp::add);
        deadlines.stream().filter(deadline -> hasKeyword(keyword, deadline)).forEach(temp::add);
        events.stream().filter(event -> hasKeyword(keyword, event)).forEach(temp::add);

        if (temp.isEmpty()) {
            return "Sorry there are no tasks that matches your keyword";
        } else {
            StringBuilder sb = new StringBuilder("Here are the tasks that match your keyword\n");
            for (int i = 0; i < temp.size(); i++) {
                sb.append("\n").append(i + 1).append(". ").append(temp.get(i));
            }
            return sb.toString();
        }
    }

    private int getTotalNumberOfItems() {
        return todos.size() + deadlines.size() + events.size();
    }

    /**
     * Checks if the task manager has any tasks.
     * @return <code>true</code> if there are no tasks.
     */
    public boolean isEmpty() {
        return getTotalNumberOfItems() == 0;
    }

    @Override
    public String toString() {
        return "This is an instance of a task manager";
    }
}