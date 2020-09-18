import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> list = new ArrayList<>();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public TaskList(ArrayList<Task> ls) {
        list.addAll(ls);
    }

    String add(String input, TaskType type) {
        Task task;
        switch(type) {
        case TODO:
            task = new ToDo(input);
            list.add(task);
            break;
        case DEADLINE:
        case EVENT:
            return parseTime(input, type);
        default:
            return "Error adding task";
        }
        return "Poco has added " + task.toString() + " to your list"
                                + "\n" + "Pending Tasks: " + list.size();

    }

    private String parseTime(String input, TaskType type) {
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

    String delete(String input) {
        int index = Integer.parseInt(input) - 1;
        if (index < 0 || index >= list.size()) {
            index++;
            return "Poco cannot find the task: " + index;
        }
        list.remove(index);
        return "Poco has deleted the task";
    }

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

    String displayList(String input) {
        if (list.size() == 0) {
            return "Yay, all done!";
        }
        return this.toString();
    }

    String postpone(String input) {
        String[] arr = input.split("/", 2);
        int index = Integer.parseInt(input) - 1;
        LocalDateTime ldt = LocalDateTime.parse(arr[1].trim(), formatter);
        if (index < 0 || index >= list.size()) {
            index++;
            return "Poco cannot find the task: " + index;
        }
        Task task = list.get(index);
        if (task instanceof ToDo) {
            return "Poco cannot postpone a ToDo task";
        }
        task.postpone(ldt);
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
