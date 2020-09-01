import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class TaskList {
    private ArrayList<Task> task_list;

    public TaskList(ArrayList<Task> task_list) {
        this.task_list = task_list;
    }

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
}

