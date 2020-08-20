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

    public String addToList(Task t) {
        this.count = this.count + 1;
        this.list.add(t);

        return "Got it. I've added this task:\n\t" +
                t.toString() +
                "\n\tNow you have " + count + " tasks in the list.";
    }

    public String markAsDone(int i) {
        Task t = this.list.get(i-1).taskDone();
        return "Nice! I've marked this task as done:\n\t" +
                "  " + t.toString();
    }

    public String remove(int i) {
        Task t = this.list.get(i-1);
        this.list.remove(i-1);
        this.count = this.count - 1;
        return "Noted. I've removed this task:\n\t" +
                "  " + t.toString() +
                "\n\tNow you have " + count + " tasks in the list.";
    }

    @Override
    public String toString() {
        if (this.count == 0) {
            return "There are no tasks in your list";
        }

        String msg = "Here are the tasks in your list:\n\t";
        int num = 1;
        for (Task s : this.list) {
            msg = msg + num + ". " + s.toString() + "\n\t";
            num++;
        }
        return msg;
    }
}
