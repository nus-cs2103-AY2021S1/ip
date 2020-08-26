import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Contains the task list.
 * e.g., it has operations to add/delete tasks in the list.
 * */
public class TaskList {
    /** Note: limit storage to 100 items **/
    private ArrayList<Task> list;

    /**
     * Constructors.
     */
    public TaskList() {
        list = new ArrayList<>(100);
    }
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Returns list of Tasks Duke knows.
     *
     * @return Task List.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Creates and adds a to-do task into TaskList.
     *
     * @param command The to-do command and its details.
     */
    public void createTodo(String command) {
        String[] instructions = command.split(" ", 2);
        Task t = new Todo(instructions[1]);
        list.add(t);
        System.out.println("added: " + t);
        System.out.println("There is now " + list.size() + " tasks in the list!\n");
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
        list.add(t);
        System.out.println("added: " + t);
        System.out.println("There is now " + list.size() + " tasks in the list!\n");
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
        list.add(t);
        System.out.println("added: " + t);
        System.out.println("There is now " + list.size() + " tasks in the list!\n");
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
            Task t = list.get(index);
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
            Task t = list.get(index);
            System.out.println("Noted! I've helped you remove the following task:");
            System.out.print("    " + t.toString() + "\n");
            list.remove(index);
            System.out.println("    Now, there is " + list.size() + " tasks in the list!\n");
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
        if (list.isEmpty()) {
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
        for (int i = 0; i < list.size(); i++) {
            // Enumerator
            System.out.print((i+1) + ".");
            // Actual Task
            System.out.println(list.get(i));
        }
    }

}
