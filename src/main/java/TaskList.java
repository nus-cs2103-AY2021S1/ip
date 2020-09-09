import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> list = new ArrayList<>();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public TaskList(ArrayList<Task> ls) {
        list.addAll(ls);
    }

    String add(String input, boolean isEvent) {
        Task task;
        if (input.contains("/")) {
            task = parseTime(input, isEvent);
            list.add(task);
        } else {
            task = new ToDo((input));
            list.add(task);
        }
        return task != null ? "Poco has added " + task.toString() + " to your list"
                                + "\n" + "Pending Tasks: " + list.size()
                            : "Error adding task";
    }

    private Task parseTime(String input, boolean isEvent) {
        String[] arr = input.split("/", 2);
        try {
            System.out.println(arr[1]);
            LocalDateTime ldt = LocalDateTime.parse(arr[1].trim(), formatter);
            return isEvent ? new Event(arr[0], ldt)
                            : new Deadline(arr[0], ldt);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
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
