import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * task list class
 */
public class TaskList {

    private ArrayList<Task> list = new ArrayList<>();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public TaskList(ArrayList<Task> ls) {
        list.addAll(ls);
    }

    /**
     * parses string into
     * corresponding task
     * and adds it to the list
     * @param input user input
     * @param type task type
     * @return output
     */

    String add(String input, TaskType type) {
        if (input.isBlank()) {
            return "Blank task";
        }
        Task task;
        switch(type) {
        case TODO:
            task = new ToDo(input);
            list.add(task);
            break;
        case DEADLINE:
        case EVENT:
            return parseTimedTasks(input, type);
        default:
            return "Error adding task";
        }
        return "Poco has added " + task.toString() + " to your list"
                                + "\n" + "Pending Tasks: " + list.size();

    }

    /**
     * parses event and deadline tasks
     * @param input user input
     * @param type task type
     * @return output
     */
    private String parseTimedTasks(String input, TaskType type) {
        if (!input.contains("/")) {
            return "Error: Specify a time using /";
        }

        try {
            Task task = null;
            String[] arr = input.split("/", 2);
            LocalDateTime ldt = LocalDateTime.parse(arr[1].trim(), formatter);
            switch(type) {
            case DEADLINE:
                task = new Deadline(arr[0], ldt);
                list.add(task);
                break;
            case EVENT:
                task = new Event(arr[0], ldt);
                list.add(task);
                break;
            }
            return task != null ? "Poco has added " + task.toString() + " to your list"
                    + "\n" + "Pending Tasks: " + list.size()
                    : "Error: adding task";
        } catch (Exception e) {
            System.out.println(e);
            return "Error: Invalid time format";
        }
    }

    /**
     * marks task as done
     * @param input index of task
     * @return output
     */
    String done(String input) {
        int index = Integer.parseInt(input) - 1;
        if (index < 0 || index >= list.size()) {
            index++;
            return "Poco cannot find the task: " + index;
        }
        list.get(index).done();
        return "Good job!" + "\n"
                + list.get(index).toString();
    }

    /**
     * deletes task
     * @param input index of task
     * @return output
     */
    String delete(String input) {
        int index = Integer.parseInt(input) - 1;
        if (index < 0 || index >= list.size()) {
            index++;
            return "Poco cannot find the task: " + index;
        }
        list.remove(index);
        return "Poco has deleted the task";
    }

    /**
     * finds tasks with description
     * that matches user input
     * @param match user input
     * @return output
     */
    String find(String match) {
        String s = "";
        for (Task task : list) {
            if (task.toString().contains(match)) {
                s = s.concat(task.toString());
                s = s.concat("\n");
            }
        }
        return s;
    }

    /**
     * displays all tasks in the list
     * @param input user input
     * @return output
     */
    String displayList(String input) {
        if (list.size() == 0) {
            return "Yay, all done!";
        }
        return this.toString();
    }

    /**
     * postpones a task to a new date
     * @param input user input
     * @return output
     */
    String postpone(String input) {
        if (!input.contains("/")) {
            return "Error: specify a time using /";
        }
        String[] arr = input.split("/", 2);
        int index = Integer.parseInt(arr[0]) - 1;
        if (index < 0 || index >= list.size()) {
            index++;
            return "Poco cannot find the task: " + index;
        }
        Task task = list.get(index);
        if (task instanceof ToDo) {
            return "Poco cannot postpone a ToDo task";
        }
        try {
            LocalDateTime ldt = LocalDateTime.parse(arr[1].trim(), formatter);
            task.postpone(ldt);
        } catch (Exception e) {
            System.out.println(e);
            return "Error: Invalid time format";
        }
        return "Poco has postponed the task: " + task.toString();
    }

    @Override
    public String toString() {
        String s = "";
        for (Task task : list) {
            s = s.concat(task.toString());
            s = s.concat("\n");
        }
        return s;
    }
}
