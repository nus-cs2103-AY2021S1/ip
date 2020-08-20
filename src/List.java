import java.util.ArrayList;

public class List {
    int count;
    ArrayList<Task> list;

    private List() {
        count = 0;
        list = new ArrayList<>();
    }

    public static List startList() {
        return new List();
    }

    public String addToList(String s) {
        this.count = this.count + 1;
        this.list.add(new Task(s));

        return "added: " + s;
    }

    public String markAsDone(int i) {
        Task t = this.list.get(i-1).taskDone();
        return "Nice! I've marked this task as done:\n\t" +
                "  " + t.taskToString();
    }

    public String listToString() {
        if (this.count == 0) {
            return "There are no tasks in your list";
        }

        String msg = "Here are the tasks in your list:\n\t";
        int num = 1;
        for (Task s : this.list) {
            msg = msg + num + ". " + s.taskToString() + "\n\t";
            num++;
        }
        return msg;
    }
}
