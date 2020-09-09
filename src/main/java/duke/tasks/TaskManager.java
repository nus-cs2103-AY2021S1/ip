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

    /**
     * Constructs a new <code>TaskManager</code> object
     * and initialises its <code>tasks</code> field as
     * an empty <code>ArrayList</code>.
     */
    public TaskManager() {
        todos = new ArrayList<>();
        deadlines = new ArrayList<>();
        events = new ArrayList<>();
    }

    public void initialiseTodos(List<Todo> todos) {
        this.todos = todos;
    }

    public void initialiseDeadlines(List<Deadline> deadlines) {
        this.deadlines = deadlines;
    }

    public void initialiseEvents(List<Event> events) {
        this.events = events;
    }

    public void addTodo(Todo todo) throws DukeException {
        todos.add(todo);
        save();
    }

    public void addDeadline(Deadline deadline) throws DukeException {
        deadlines.add(deadline);
        deadlines.sort(Deadline::compareTo);
        save();
    }

    public void addEvent(Event event) throws DukeException {
        events.add(event);
        events.sort(Event::compareTo);
        save();
    }

    /**
     * Gets the "save" text represenation of each <code>Task</code>
     * and creates a <code>String</code> of the information to be saved.
     * This string information is then passed on to the <code>Storage</code>
     * class where it will handle the saving of the information.
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
     * Creates a string representation of all the duke.tasks.
     * @return the string representation of all the duke.tasks
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
     * Marks a particular task as done. The task to be marked as done
     * is identified by the <code>taskNum</code>. <code>taskNum - 1</code>
     * is the index position of the task in the <code>ArrayList</code>.
     * @param taskNum the number of the task in the task list
     * @return the <code>Task</code> that is marked done
     * @throws DukeException if the provided task number is out of bounds of the range of the <code>ArrayList</code>
     */
    public Task markDone(int taskNum) throws DukeException {
        int totalNumber = getTotalNumberOfItems();
        if (taskNum < 0 || taskNum >= totalNumber) {
            throw new DukeException("you gave an invalid task number!");
        }
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

    /**
     * Deletes a task from the list.
     * @param taskNum the task number of the task to be deleted
     * @throws DukeException if the provided task number is out of bounds of the range of the <code>ArrayList</code>
     */
    public Task deleteTask(int taskNum) throws DukeException {
        int totalNumber = getTotalNumberOfItems();
        if (taskNum < 0 || taskNum > totalNumber) {
            throw new DukeException("you gave an invalid task number!");
        }
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
        for (Todo todo : todos) {
            if (hasKeyword(keyword, todo)) {
                temp.add(todo);
            }
        }
        for (Deadline deadline : deadlines) {
            if (hasKeyword(keyword, deadline)) {
                temp.add(deadline);
            }
        }
        for (Event event : events) {
            if (hasKeyword(keyword, event)) {
                temp.add(event);
            }
        }
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

    @Override
    public String toString() {
        return "This is an instance of a task manager";
    }
}