import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Tracker {
    private List<Task> list = new ArrayList<>();
    private String filename;
    private BufferedWriter writer;

    public Tracker(String filename) throws IOException {
        this.filename = filename;
        File file = new File(filename);
        if (Files.exists(Paths.get("./data"))) {
            if (!file.createNewFile()) {
                this.initialize(filename);
            }
        } else {
            new File("./data").mkdir();
            file.createNewFile();
        }
        this.writer = new BufferedWriter(new FileWriter(filename, true));
    }

    private void initialize(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String task = br.readLine();
        while(task != null) {
            if (!task.equals("")) {
                String[] splits = task.split(" ~/~ ");
                Task t = null;
                switch (splits[0]) {
                    case "T":
                        t = new ToDo(splits[2]);
                        break;
                    case "D":
                        t = new Deadline(splits[2], splits[3]);
                        break;
                    case "E":
                        t = new Event(splits[2], splits[3]);
                        break;
                    default:
                        System.out.println("Sorry, there is an error with the hard disk!");
                }
                if (splits[1].equals("1")) t.markAsDone();
                list.add(t);
            }
            task = br.readLine();
        }
    }

    public void displayList() {
        this.printSection();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + "." + list.get(i));
        }
        this.printSection();
    }

    public void markDone(String num) throws IOException {
        int i = Integer.valueOf(num.substring(5, num.length()));
        list.get(i - 1).markAsDone();
        this.refreshTracker();
        this.printSection();
        System.out.println("Nice! I've marked this task as done:\n\t" + list.get(i - 1));
        this.printSection();
    }

    public void addToDo(String next) throws IOException {
        ToDo todo = new ToDo(next.substring(5, next.length()));
        list.add(todo);
        writer.write(todo.txtFileFormat());
        writer.newLine();
        writer.flush();
        this.printSection();
        System.out.println("Got it. I've added this task:\n\t" + todo);
        System.out.println("Now you have " + list.size() + " tasks in the list");
        this.printSection();
    }

    public void addDeadline(String next) throws DukeException, IOException {
        int cut = next.indexOf(" /by ");
        if (cut != -1) {
            String desc = next.substring(9, cut);
            String by = next.substring(cut + 5, next.length());
            Deadline deadline = new Deadline(desc, by);
            list.add(deadline);
            writer.write(deadline.txtFileFormat());
            writer.newLine();
            writer.flush();
            this.printSection();
            System.out.println("Got it. I've added this task:\n\t" + deadline);
            System.out.println("Now you have " + list.size() + " tasks in the list");
            this.printSection();
        } else {
            throw new DukeException("Please input a deadline");
        }
    }

    public void addEvent(String next) throws DukeException, IOException {
        int cut = next.indexOf(" /at ");
        if (cut != -1) {
            String desc = next.substring(6, cut);
            String time = next.substring(cut + 5, next.length());
            Event event = new Event(desc, time);
            list.add(event);
            writer.write(event.txtFileFormat());
            writer.newLine();
            writer.flush();
            this.printSection();
            System.out.println("Got it. I've added this task:\n\t" + event);
            System.out.println("Now you have " + list.size() + " tasks in the list");
            this.printSection();
        } else {
            throw new DukeException("Please input a time for the event");
        }
    }

    public void delete(String next) throws IOException {
        int i = Integer.valueOf(next.substring(7, next.length()));
        this.printSection();
        System.out.println("Noted. I've removed this task:\n\t" + list.get(i - 1));
        list.remove(i - 1);
        this.refreshTracker();
        System.out.println("Now you have " + list.size() + " tasks in the list");
        this.printSection();
    }

    private void refreshTracker() throws IOException {
        this.writer = new BufferedWriter(new FileWriter(this.filename));
        for (Task t : list) {
            writer.write(t.txtFileFormat());
            writer.newLine();
        }
        this.writer.flush();
        this.writer = new BufferedWriter(new FileWriter(this.filename, true));
    }

    public void printSection() {
        System.out.println("____________________________________________________________");
    }
}
