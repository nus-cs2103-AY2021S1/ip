import java.util.Scanner;
import java.util.ArrayList;

/**
 * Command object has a CommandType to determine which specific command to execute
 * and a description which contains the user input String. Command objects to be executed
 * are always valid as input validation is done by the Parser object.
 * Command object also handles manipulation of list of Task objects.
 *
 * @author Hakiem Rasid
 */
public class Command {

    public CommandType type;
    public String description;

    /**
     * Constructor for Command object.
     *
     * @param type Type of command to be executed as CommandType.
     * @param description User input instruction as String.
     */
    public Command(CommandType type, String description) {
        this.type = type;
        this.description = description;
    }

    /**
     * Returns list of Task objects after executing command.
     *
     * @param tasks List of Task objects to be manipulated.
     * @return List of Task objects after changes made from executing command.
     * @throws IndexOutOfBoundsException If index specified in DONE or DELETE command
     * does not lie within range of list of Task objects.
     */
    public ArrayList<Task> executeCommand(ArrayList<Task> tasks) throws
            IndexOutOfBoundsException {

        switch (this.type) {
            case BYE:
                Ui.byeMessage();
                return tasks;
            case CLEAR:
                if (Ui.promptConfirm(new Scanner(System.in))) {
                    // Reference to empty ArrayList
                    Ui.clearedListMessage();
                    return new ArrayList<Task>();
                } else {
                    // do nothing
                    Ui.didNotClearListMessage();
                    return tasks;
                }
            case LIST:
                if (tasks.size() == 0) {
                    // do nothing
                    return new ArrayList<Task>();
                } else {
                    Ui.printList(tasks, "print");
                    return tasks;
                }
            case DONE:
                return markDone(tasks, this.description);
            case DELETE:
                return deleteTask(tasks, this.description);
            case TODO:
            case DEADLINE:
            case EVENT:
                return addTask(tasks, this.description);
            case FIND:
                return findTask(tasks, this.description);
            case UNKNOWN:
                System.out.println(this.description);
                return tasks;
        }
        return tasks;
    }

    /**
     * Returns a list of Task objects with a description that contains the key.
     *
     * @param tasks List of Task objects of which to find matching Tasks.
     * @param key Key used to find matching Task objects.
     * @return List of matching Task objects.
     */
    public ArrayList<Task> findTask(ArrayList<Task> tasks, String key) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getName().toLowerCase().contains(key.toLowerCase())) {
                matchedTasks.add(task);
            }
        }
        if (matchedTasks.size() == 0) {
            Ui.noMatchMessage();
        } else {
            Ui.printList(matchedTasks, "find");
        }
        return tasks;
    }

    /**
     * Returns list of Task objects with specified Task object marked as done.
     *
     * @param tasks List of Task objects.
     * @param input Keyword "done" followed by index of Task object to be marked done.
     * @return List of updated Task objects.
     * @throws IndexOutOfBoundsException If index specified in DONE Command
     * does not lie within range of list of Task objects.
     */
    public ArrayList<Task> markDone(ArrayList<Task> tasks, String input) throws
            IndexOutOfBoundsException {

        // parse int for index of task to be marked as done
        int index = Integer.valueOf(input.split(" ")[1]);

        Task current = tasks.get(index - 1);
        current.completeTask();
        Ui.markDoneMessage(current.printTask());
        return tasks;
    }

    /**
     * Returns updated list of Task objects after deleting Task at specified index.
     *
     * @param tasks List of Task objects to be manipulated.
     * @param input Keyword "delete" followed by index of Task to be deleted.
     * @return Updated list of Task objects.
     * @throws IndexOutOfBoundsException If index specified in DELETE Command
     * does not lie within range of list of Task objects.
     */
    public ArrayList<Task> deleteTask(ArrayList<Task> tasks, String input) throws
            IndexOutOfBoundsException {

        // parse int for index of task to be deleted
        int index = Integer.valueOf(input.split(" ")[1]);

        Task current = tasks.remove(index - 1);
        Ui.deleteTaskMessage(current.printTask(), tasks.size());
        return tasks;
    }

    // Adds Task to list. Checks inputs and throws exceptions for invalid inputs

    /**
     * Returns updated list of Task objects after adding the specified Task.
     *
     * @param tasks List of Task obects to be manipulated.
     * @param input Keyword of specified Task type followed by details of the Task.
     * @return Updated List of Task objects.
     */
    public ArrayList<Task> addTask(ArrayList<Task> tasks, String input) {

        StringBuilder sb = new StringBuilder();
        String[] splitSpace = input.split(" ");
        Task task;

        if (this.type.equals(CommandType.TODO)) {
            // case: todo
            for (String str : splitSpace) {
                if (str.toLowerCase().equals("todo")) {
                    continue;
                } else {
                    sb.append(str + " ");
                }
            } // end for loop
            task = new ToDo(sb.toString().trim());
        } else if (this.type.equals(CommandType.DEADLINE)) {
            // case: deadline

            String name = input.split("/by")[0].trim().substring(9);
            String deadline = input.split("/by")[1].trim();
            task = new Deadline(name, deadline);
        } else {
            // case: event
            String name = input.split("/at")[0].trim().substring(6);
            String time = input.split("/at")[1].trim();
            task = new Event(name, time);
        }

        tasks.add(task);
        Ui.addTaskMessage(task.printTask(), tasks.size());
        return tasks;
    }

    /**
     * Returns the type of this Command object.
     *
     * @return Type of this Command object as CommandType.
     */
    public CommandType getType() {
        return this.type;
    }

    /**
     * Returns description(user input instruction) of this Command object.
     * @return Description of Command object as String.
     */
    public String getDescription() {
        return this.description;
    }

}