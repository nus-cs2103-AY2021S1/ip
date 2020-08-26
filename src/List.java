import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

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
                "  " + t.toString() +
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

    public void populateList(File f) throws IOException, IndexOutOfBoundsException {
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            String[] arr = line.split("@", 4);
            if (arr[0].equals("T")) {
                String desc = arr[2];
                if (arr[1].equals("1")) {
                    this.addToList(new Todo(desc).taskDone());
                } else {
                    this.addToList(new Todo(desc));
                }
            } else if (arr[0].equals("D")) {
                String desc = arr[2];
                String by = arr[3];
                if (arr[1].equals("1")) {
                    this.addToList(new Deadline(desc, by).taskDone());
                } else {
                    this.addToList(new Deadline(desc, by));
                }
            } else if (arr[0].equals("E")) {
                String desc = arr[2];
                String at = arr[3];
                if (arr[1].equals("1")) {
                    this.addToList(new Event(desc, at).taskDone());
                } else {
                    this.addToList(new Event(desc, at));
                }
            }
        }
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
