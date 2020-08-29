package duke;

import java.util.ArrayList;

public class TaskList {
    int count;
    ArrayList<Task> list;

    private TaskList() {
        count = 0;
        list = new ArrayList<>();
    }

    public static TaskList startList() {
        return new TaskList();
    }

    public String addToList(Task t) {
        this.count = this.count + 1;
        this.list.add(t);

        return "Got it. I've added this task:\n\t" +
                t.toString() +
                "\n\tNow you have " + count + " tasks in the list.";
    }

    public String markAsDone(int i) throws InvalidTaskNumberException{
        Task t;
        try {
            t = this.list.get(i - 1).taskDone();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        }
        return "Nice! I've marked this task as done:\n\t" +
                "  " + t.toString();
    }

    public String remove(int i) throws InvalidTaskNumberException{
        Task t;
        try {
            t = this.list.get(i - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        }
        this.list.remove(i-1);
        this.count = this.count - 1;
        return "Noted. I've removed this task:\n\t" +
                t.toString() +
                "\n\tNow you have " + count + " tasks in the list.";
    }

    public String listToTxt() {
        String str = "";
        for (Task t : this.list) {
            String type = t.getType();
            boolean isDone = t.isDone();
            String desc = t.getDescription();
            String time = t.getDeadline();
            str = str + type + "@" +
                    (isDone ? "1" : "0") + "@" +
                    desc +
                    ((time == null) ? "" : "@" + time) +
                    System.getProperty("line.separator");
        }
        return str;
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
