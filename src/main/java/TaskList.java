import java.util.ArrayList;
import java.util.List;

/**
 * Enumeration indicating the type of change to be acted on the TaskList.
 */
enum ListChange {
    ADD, DELETE
}

public class TaskList {
    /** The list of tasks present. */
    protected List<Task> tasks;

    /**
     * Constructs a new TaskList object.
     *
     * @param taskList List of Tasks.
     */
    public TaskList(List<Task> taskList) {
        tasks = taskList;
    }

    /**
     * Gets the list of tasks.
     *
     * @return List of Tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns substring of tasks depending on their amount.
     *
     * @return String of number of tasks.
     */
    public String getNumTasksStr() {
        int numTasks = tasks.size();
        return numTasks == 1 ? "1 task" : numTasks + " tasks";
    }

    /**
     * Returns the correct message to signify adding/deleting a task.
     *
     * @param task Task to be added/deleted.
     * @param change ListChange depending on command.
     * @return Message string.
     */
    public String listChangePrint(Task task, ListChange change) {
        String keyword = change == ListChange.ADD ? "added" : "removed";
        String firstLine = "Noted. I've " + keyword + " this task:\n" + task.toString() + "\n";
        String secondLine = "Now you have " + this.numTasks() + " in the list.";
        return String.join("", firstLine, secondLine);
    }

    /**
     * Compiles an ordered list (in String) of tasks.
     *
     * @return String of task list.
     */
    public String printList() {
        if (tasks.size() == 0) {
            return new DukeException("emptyList").print();
        }

        String tasksStr = "";
        for (int i = 0; i < tasks.size(); i++) {
            String taskToJoin = i + 1 + "." + tasks.get(i).toString() + "\n";
            tasksStr = String.join("", tasksStr, taskToJoin);
        }
        return tasksStr;
    }

    /**
     * Marks a task as done.
     *
     * @param command User command.
     * @return Duke response indicating successful action (or not).
     */
    public String markDone(String command) {
        try {
            int taskId = Parser.getTaskId(command);
            tasks.get(taskId).markAsDone();
            return "Nice! I've marked this task as done:\n" + tasks.get(taskId).toString();
        } catch (Exception e) {
            return new DukeException("invalidMarkingDone").print();
        }
    }

    /**
     * Creates new ToDo task.
     *
     * @param command User command.
     * @return Duke response indicating successful action (or not).
     */
    public String createToDo(String command) {
        if (command.equals("todo")) {
            return new DukeException("invalidTodo").print();
        } else {
            String detail = Parser.getDetail(command);
            if (detail.isBlank()) {
                return new DukeException("invalidTodo").print();
            } else {
                Task newTask = new ToDo(detail);
                tasks.add(newTask);
                return listChangePrint(newTask, ListChange.ADD);
            }
        }
    }

    /**
     * Creates new Deadline task.
     *
     * @param command User command.
     * @return Duke response indicating successful action (or not).
     */
    public String createDeadline(String command) {
        try {
            String desc = Parser.getDeadlineDesc(command);
            String by = Parser.getBy(command);
            Task newTask = new Deadline(desc, by);
            tasks.add(newTask);
            return listChangePrint(newTask, ListChange.ADD);
        } catch (DukeException e) {
            return e.print();
        } catch (Exception e) {
            return new DukeException("invalidDeadlineTask").print();
        }
    }

    /**
     * Creates new Event task.
     *
     * @param command User command.
     * @return Duke response indicating successful action (or not).
     */
    public String createEvent(String command) {
        try {
            String desc = Parser.getEventDesc(command);
            String at = Parser.getAt(command);
            Task newTask = new Event(desc, at);
            tasks.add(newTask);
            return listChangePrint(newTask, ListChange.ADD);
        } catch (DukeException e) {
            return e.print();
        } catch (Exception e) {
            return new DukeException("invalidEvent").print();
        }
    }

    /**
     * Deletes a task.
     *
     * @param command User command.
     * @return Duke response indicating successful action (or not).
     */
    public String delete(String command) {
        try {
            int taskId = Parser.getTaskId(command);
            Task removedTask = tasks.remove(taskId);
            return listChangePrint(removedTask, ListChange.DELETE);
        } catch (Exception e) {
            return new DukeException("invalidDelete").print();
        }
    }

    /**
     * Finds task(s) according to keyword.
     *
     * @param command User command.
     * @return String of tasks matching the keyword.
     */
    public String find(String command) {
        if (command.equals("find")) {
            return new DukeException("invalidFind").print();
        } else {
            String keyword = Parser.getDetail(command);
            if (keyword.isBlank()) {
                return new DukeException("invalidFind").print();
            } else {
                List<Task> tasksFound = new ArrayList<>();

                for (Task task : tasks) {
                    if (task.toString().contains(keyword)) {
                        tasksFound.add(task);
                    }
                }
                TaskList tasksFoundList = new TaskList(tasksFound);
                return tasksFoundList.printList();
            }
        }
    }

    /**
     * Returns the default invalid command error.
     *
     * @return Duke response for default error.
     */
    public String getDefaultError() {
        return new DukeException("invalidCommand").print();
    }
}
