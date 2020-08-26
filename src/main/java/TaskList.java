import java.time.LocalDate;
import java.util.ArrayList;

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
     */
    public void createTodo(String command) {
        String[] instructions = command.split(" ", 2);
        Task t = new Todo(instructions[1]);
        tasks.add(t);
        System.out.println("added: " + t);
        System.out.println("There is now " + tasks.size() + " tasks in the list!\n");
    }

    /**
     * Creates and adds a deadline task into TaskList.
     *
     * @param command The deadline command and its details.
     */
    public void createDeadline(String command) {
        String[] instructions = command.split(" ", 2);
        String[] details = instructions[1].split(" /by ", 2);
        Task t = new Deadline(details[0], LocalDate.parse(details[1]));
        tasks.add(t);
        System.out.println("added: " + t);
        System.out.println("There is now " + tasks.size() + " tasks in the list!\n");
    }

    /**
     * Creates and adds a Event task into TaskList.
     *
     * @param command The event command and its details.
     */
    public void createEvent(String command) {
        String[] instructions = command.split(" ", 2);
        String[] details = instructions[1].split(" /at ", 2);
        Task t = new Event(details[0], details[1]);
        tasks.add(t);
        System.out.println("added: " + t);
        System.out.println("There is now " + tasks.size() + " tasks in the list!\n");
    }

    /**
     * Identifies a specific task and mark it as done.
     *
     * @param command The done command and its details.
     * @throws DukeException If index noted in command is invalid.
     */
    public void markTaskDone(String command) throws DukeException {
        String[] instructions = command.split(" ", 2);
        int index = Integer.parseInt(instructions[1]) - 1;
        try {
            Task t = tasks.get(index);
            t.markedDone(true);
            System.out.println("Congratulations! I've helped you mark the task as done:");
            System.out.println("    " + t.toString() + "\n");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry, I don't think that's a valid index...");
        }
    }

    /**
     * Identifies a specific task and deletes it from TaskList.
     *
     * @param command The delete command and its details.
     * @throws DukeException If index noted in command is invalid.
     */
    public void deleteTask(String command) throws DukeException {
        String[] instructions = command.split(" ", 2);
        int index = Integer.parseInt(instructions[1]) - 1;
        try {
            Task t = tasks.get(index);
            System.out.println("Noted! I've helped you remove the following task:");
            System.out.print("    " + t.toString() + "\n");
            tasks.remove(index);
            System.out.println("    Now, there is " + tasks.size() + " tasks in the list!\n");
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
     * Prints out all of the tasks in the TaskList.
     */
    public void printAllTasks() {
        // Prints all tasks in Duke's list
        for (int i = 0; i < tasks.size(); i++) {
            // Enumerator
            System.out.print((i+1) + ".");

            // Actual Task
            System.out.println(tasks.get(i));
        }
    }

    /**
     * Searches through all of Duke's tasks and
     * print out tasks with given keyword
     *
     * @param command The find command inputted by the user.
     * @throws DukeException If no tasks with keyword can be found.
     */
    public void searchForKeyword(String command) throws DukeException {
        String[] instructions = command.split(" ", 2);
        String keyword = instructions[1];

        // Initialize an arraylist to store found tasks
        ArrayList<Task> findings = new ArrayList<>();

        // Go through Duke's tasks
        for (int i = 0; i < list.size(); i++) {
            //Check each task for the keyword
            if (list.get(i).getDescription().contains(keyword)) {
                // Add to findings
                findings.add(list.get(i));
            }
        }

        //Check if list is empty
        if (findings.isEmpty()) {
            throw new DukeException("Sorry I find can't any tasks related to " + keyword + ".");
        } else {
            System.out.println("Here are the relevant tasks!");
            // Prints all tasks in findings
            for (int i = 0; i < findings.size(); i++) {
                // Enumerator
                System.out.print((i+1) + ".");
                // Actual Task
                System.out.println(findings.get(i));
            }
        }
        System.out.println();
    }
}
