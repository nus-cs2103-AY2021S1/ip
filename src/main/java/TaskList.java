import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * TaskList class allows management of tasks in a list
 */
public class TaskList {
    private ArrayList<Task> task_list;

    /**
     * Constructs a TaskList instance.
     *
     * @param task_list The List of tasks.
     */
    public TaskList(ArrayList<Task> task_list) {
        this.task_list = task_list;
    }


    /**
     * Adds the task specified by the client into a list of tasks
     * @param command The string containing the description of the task to be added in the list.
     * @return The updated task list after the addition of the given task.
     * @throws DukeInvalidCommandException If the description of the command entered is invalid.
     * @throws DateTimeParseException If the date provided in the task description is in an invalid format.
     * @throws ArrayIndexOutOfBoundsException If the command is given in a unreadable format

     */
    public ArrayList<Task> addTask(String command) throws DukeInvalidCommandException,  DateTimeParseException, ArrayIndexOutOfBoundsException {
        Task task = null;
        String task_type = command.split(" ")[0];
        command = command.substring(command.indexOf(" "));
        switch (task_type) {
            case "todo":
                task = new ToDo(command.trim());
                break;
            case "event":
                task = new Event(getDescription(command, "/at "), getDate(command, "/at "),
                        getTime(command, "/at "));
                break;
            case "deadline":
                task = new Deadline(getDescription(command, "/by "), getDate(command, "/by "),
                        getTime(command, "/by "));
                break;
            default:
                throw new DukeInvalidCommandException(String.format(
                        "    ____________________________________________________________\n" +
                                "     ☹ OOPS!!! The description of a %s cannot be empty.\n" +
                                "    ____________________________________________________________\n", task_type));
        }

        task_list.add(task);
        String task_added = String.format("    ____________________________________________________________\n" +
                "     Got it. I've added this task: \n" +
                "       %s\n" +
                "     Now you have %d tasks in the list.\n" +
                "    ____________________________________________________________\n", task, task_list.size());
        System.out.println(task_added);
        return task_list;
    }

    /**
     * Deletes the task from the tasks list,
     * then return the updated list.
     *
     * @param command The string containing the index of the task in the list to be deleted.
     * @return The updated task list after the task deletion.
     * @throws DukeInvalidTaskException If the task doesn't exist in the list.
     */
    public ArrayList<Task> deleteTask(String command) throws DukeInvalidTaskException {
        int index = Integer.parseInt(command.split(" ")[1]) - 1;
        if (index >= task_list.size() || index < 0) {
            throw new DukeInvalidTaskException("There is no such task in the list!");
        }

        String task_deleted = String.format("    ____________________________________________________________\n" +
                                            "     Noted. I've removed this task: \n" +
                                            "       %s\n" +
                                            "     Now you have %d tasks in the list.\n" +
                                            "    ____________________________________________________________", task_list.get(index), task_list.size() - 1);
        System.out.println(task_deleted);
        task_list.remove(index);
        return task_list;
    }

    /**
     * Marks the task as done in the tasks list,
     * then return the updated list.
     *
     * @param command The string containing the index of the task in the list to be marked as done.
     * @return The updated task list after marking the task as done.
     * @throws DukeInvalidTaskException If the task doesn't exist in the list.
     */
    public ArrayList<Task> doneTask(String command) throws DukeInvalidTaskException {
        int index = Integer.parseInt(command.split(" ")[1]) - 1;
        if (index >= task_list.size() || index < 0) {
            throw new DukeInvalidTaskException("    ____________________________________________________________\n" +
                                               "     ☹ OOPS!!! The task cannot be found.\n" +
                                               "    ____________________________________________________________\n");
        }

        task_list.get(index).complete();

        String task_done =  "    ____________________________________________________________\n" +
                            "     Nice! I've marked this task as done: \n" +
                            "       " + task_list.get(index) + "\n" +
                            "    ____________________________________________________________";

        System.out.println(task_done);
        return task_list;
    }

    /**
     * Finds the tasks in the task_list matching the entered keyword,
     * then return the list containing those tasks.
     *
     * @param keyword The string containing the description of the task in the list to be found.
     * @return The list of tasks with the matching keyword description.
     * @throws DukeInvalidTaskException If the task doesn't exist in the list.
     */
    public ArrayList<Task> findTask(String keyword) throws DukeInvalidTaskException {
        ArrayList<Task> temp_ls = new ArrayList<>();
        for (Task task : task_list) {
            if (task.task_info.contains(keyword)) {
                temp_ls.add(task);
            }
        }
        if (temp_ls.size() == 0) {
            throw new DukeInvalidTaskException("    ____________________________________________________________\n" +
                                               "     ☹ OOPS!!! There are no matching tasks.\n" +
                                               "    ____________________________________________________________\n");
        }
        return temp_ls;
    }

    private String getDescription(String command, String s) {
        return command.split(s)[0].trim();
    }

    private String getDate_Time(String command, String s) {
        return command.split(s)[1].trim();
    }

    private LocalDate getDate(String command, String s) {
        return LocalDate.parse(getDate_Time(command,s).split(" ")[0]);
    }

    private String getTime(String command, String s) {
        return getDate_Time(command, s).split(" ")[1];
    }





}

