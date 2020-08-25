import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
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
                        if (splits.length == 4) {
                            t = new Deadline(splits[2], LocalDate.parse(splits[3]));
                        } else if (splits.length == 5) {
                            t = new Deadline(splits[2], LocalDate.parse(splits[3]), LocalTime.parse(splits[4]));
                        }
                        break;
                    case "E":
                        if (splits.length == 4) {
                            t = new Event(splits[2], LocalDate.parse(splits[3]));
                        } else if (splits.length == 5) {
                            t = new Event(splits[2], LocalDate.parse(splits[3]), LocalTime.parse(splits[4]));
                        } else if (splits.length == 6) {
                            t = new Event(splits[2], LocalDate.parse(splits[3]), LocalTime.parse(splits[4]),
                                    LocalTime.parse(splits[5]));
                        }
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

    public void displayList(String next) throws DukeException {
        String[] splits = next.split(" ");
        if (splits.length == 1) {
            this.printSection();
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(i + 1 + "." + list.get(i));
            }
            this.printSection();
        } else if (splits.length == 2){
            LocalDate date = LocalDate.parse(splits[1]);
            listTasksOnDate(date);
        } else {
            throw new DukeException("Sorry I don't understand");
        }
    }

    public void listTasksOnDate(LocalDate date) {
        int i = 1;
        for (Task t : list) {
            if(t.getDate().equals(date)) {
                System.out.println(i + "." + t);
                i++;
            }
        }
    }

    public void markDone(String num) throws IOException, DukeException {
        int i = Integer.valueOf(num.substring(5, num.length()));
        if (i > list.size()) throw new DukeException("Please input a number");
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
            String[] dateAndTime = by.split(" ");
            LocalDate date = LocalDate.parse(dateAndTime[0]);
            Deadline deadline = null;
            if (dateAndTime.length == 2) {
                deadline = new Deadline(desc, date, LocalTime.parse(dateAndTime[1]));
            } else {
                deadline = new Deadline(desc, date);
            }
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
            String at = next.substring(cut + 5, next.length());
            String[] dateAndTime = at.split(" ");
            LocalDate date = LocalDate.parse(dateAndTime[0]);
            Event event = null;
            if(dateAndTime.length == 2) {
                String[] startAndEndTime = dateAndTime[1].split("-");
                LocalTime startTime = LocalTime.parse(startAndEndTime[0]);
                if (startAndEndTime.length == 2) {
                    event = new Event(desc, date, startTime, LocalTime.parse(startAndEndTime[1]));
                } else {
                    event = new Event(desc, date, startTime);
                }
            } else {
                event = new Event(desc, date);
            }
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
