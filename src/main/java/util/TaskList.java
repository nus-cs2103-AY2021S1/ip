package util;

import java.time.LocalDate;
import java.util.ArrayList;

import util.task.Deadline;
import util.task.Event;
import util.task.Task;
import util.task.Todo;


/**
 * Contains the task list.
 * e.g., it has operations to add/delete tasks in the list.
 * */
public class TaskList {
    /** Note: limit storage to 100 items **/
    private ArrayList<Task> tasks = new ArrayList<>(100);

    /**
     * Constructors.
     */
    public TaskList() {

    }
    public TaskList(ArrayList<Task> list) {
        this.tasks = list;
    }

    /**
     * Returns list of Tasks Duke knows.
     *
     * @return Task List.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Creates and adds a to-do task into TaskList.
     *
     * @param command The to-do command and its details.
     * @return Message of successful creation
     */
    public String createTodo(String command) {
        String[] instructions = command.split(" ", 2);
        Task t = new Todo(instructions[1]);
        tasks.add(t);
        return String.format("added: %s\n There is now %d tasks in the list!\n",
                t, tasks.size());
    }

    /**
     * Creates and adds a deadline task into TaskList.
     *
     * @param command The deadline command and its details.
     * @return Message of successful creation
     */
    public String createDeadline(String command) {
        String[] instructions = command.split(" ", 2);
        String[] details = instructions[1].split(" /by ", 2);
        Task t = new Deadline(details[0], LocalDate.parse(details[1]));
        tasks.add(t);
        return String.format("added: %s\n There is now %d tasks in the list!\n",
                t, tasks.size());
    }

    /**
     * Creates and adds a Event task into TaskList.
     *
     * @param command The event command and its details.
     * @return Message of successful creation
     */
    public String createEvent(String command) {
        String[] instructions = command.split(" ", 2);
        String[] details = instructions[1].split(" /at ", 2);
        Task t = new Event(details[0], details[1]);
        tasks.add(t);
        return String.format("added: %s\n There is now %d tasks in the list!\n",
                t, tasks.size());
    }

    /**
     * Identifies a specific task and mark it as done.
     *
     * @param command The done command and its details.
     * @return Message of successful change of status
     * @throws DukeException If index noted in command is invalid.
     */
    public String markTaskDone(String command) throws DukeException {
        String[] instructions = command.split(" ", 2);
        int index = Integer.parseInt(instructions[1]) - 1;
        try {
            Task t = tasks.get(index);
            t.setStatus(true);
            return String.format("Congratulations!\nI've helped you mark the task as done:\n -> %s\n",
                    t.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry, I don't think that's a valid index...");
        }
    }

    /**
     * Identifies a specific task and deletes it from TaskList.
     *
     * @param command The delete command and its details.
     * @return Message of successful deletion of task.
     * @throws DukeException If index noted in command is invalid.
     */
    public String deleteTask(String command) throws DukeException {
        String[] instructions = command.split(" ", 2);
        int index = Integer.parseInt(instructions[1]) - 1;
        try {
            Task t = tasks.get(index);
            String m = String.format("Noted!\nI've helped you remove the following task:\n ->%s\n",
                    t.toString());
            tasks.remove(index);
            return m + String.format("Now, there is %d tasks in the list!\n", tasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry, I don't think that's a valid index...");
        }
    }

    /**
     * Returns whether the task list is empty.
     *
     * @return True if list is empty, False otherwise.
     */
    public boolean isListEmpty() {
        if (tasks.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets all of the tasks in the TaskList.
     *
     * @return List of all tasks as a String.
     */
    public String getAllTasks() {
        String output = "";
        // Turn all tasks in Duke's list into a long String
        for (int i = 0; i < tasks.size(); i++) {
            output = output + String.format("%d. %s\n", (i + 1), tasks.get(i));
        }
        return output;
    }

    /**
     * Searches through all of Duke's tasks and
     * print out tasks with given keyword
     *
     * @param command The find command inputted by the user.
     * @return List of findings
     * @throws DukeException If no tasks with keyword can be found.
     */
    public String searchForKeyword(String command) throws DukeException {
        String[] instructions = command.split(" ", 2);
        String keyword = instructions[1];

        // Initialize an arraylist to store found tasks
        ArrayList<Task> findings = new ArrayList<>();

        // Go through Duke's tasks
        for (int i = 0; i < tasks.size(); i++) {
            //Check each task for the keyword
            if (tasks.get(i).getDescription().contains(keyword)) {
                // Add to findings
                findings.add(tasks.get(i));
            }
        }

        //Check if list is empty
        if (findings.isEmpty()) {
            throw new DukeException("Sorry I find can't any tasks related to '" + keyword + "'.");
        } else {
            String m = "Here are the relevant tasks!\n";
            // Prints all tasks in findings
            for (int i = 0; i < findings.size(); i++) {
                m = m + String.format("-> %s\n", findings.get(i));
            }
            return m;
        }
    }

    /**
     * Identifies what task user wants to update and
     * returns current description of task.
     *
     * @param command The update command and its details.
     * @return Current details of task user wants to update
     * @throws DukeException If index noted in command is invalid.
     */
    public String fetchTaskToUpdate(String command) throws DukeException {
        String[] instructions = command.split(" ", 2);
        int index = Integer.parseInt(instructions[1]) - 1;
        try {
            Task t = tasks.get(index);
            return String.format("%s\n -> %s\n%s\n",
                    "Here is the details of the specified task!",
                    t.toString(),
                    "What would you like to change its description to?");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry, I don't think that's a valid index...");
        }
    }

    /**
     * Updates previously mentioned task with provided description.
     *
     * @param newDescription The new description for task.
     * @return String of updated task.
     * @throws DukeException If index noted in command is invalid.
     */
    public String updateTask(String command, String newDescription) {
        String[] instructions = command.split(" ", 2);
        int index = Integer.parseInt(instructions[1]) - 1;
        // Check for index is within range;
        assert (index < tasks.size() && index >= 0);
        Task t = tasks.get(index);
        t.setDescription(newDescription);
        return String.format("%s\n -> %s\n",
                "Here is the details of the updated task!",
                t.toString());
    }
}
