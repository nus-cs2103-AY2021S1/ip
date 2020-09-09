import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> list = new ArrayList<>();

    public TaskList(ArrayList<Task> ls) {
        list.addAll(ls);
    }

    String add(Task task) {
        list.add(task);
        return "Poco has added " + task.toString() + " to your list"
                + "\n" + "Pending Tasks: " + list.size();
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
