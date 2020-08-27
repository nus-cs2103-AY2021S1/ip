package duke;
import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.readData());
        parser = new Parser();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        ui.sayHi();
        Scanner myScanner = new Scanner(System.in);
        while(true) {
            String cmd = myScanner.nextLine();
            if(cmd.equals("bye")) {
                ui.sayBye();
                break;
            }
            else if(cmd.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for(int i = 1; i <= tasks.getSize(); ++i) {
                    System.out.println(i + "." + tasks.get(i - 1).getStatus());
                }
            }
            else if(cmd.length() >= 4 && cmd.substring(0, 4).equals("done")) {
                int c = 0;
                for(int i = 5; i < cmd.length(); ++i) {
                    c = c * 10 + cmd.charAt(i) - '0';
                }
                System.out.println("Nice! I've marked this task as done:");
                tasks.get(c - 1).done();
                System.out.println(tasks.get(c - 1).getStatus());
            }
            else if(cmd.length() >= 4 && cmd.substring(0, 4).equals("todo")) {
                try {
                    checkCmd(cmd, 4, "☹ OOPS!!! The description of a todo cannot be empty.");
                    String getName = cmd.substring(5);
                    Todo tmp = new Todo(getName);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println("  " + tmp.getStatus());
                    tasks.add(tmp);
                    System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
                }
                catch (DukeException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            else if(cmd.length() >= 8 &&cmd.substring(0, 8).equals("deadline")) {
                try {
                    checkCmd(cmd, 8, "☹ OOPS!!! The description of a deadline cannot be empty.");
                    String getName = parser.getNameBy(cmd);
                    String getDeadline = parser.getDeadlineBy(cmd);
                    getDeadline = formatDate(getDeadline);
                    Deadline tmp = new Deadline(getName, getDeadline);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println("  " + tmp.getStatus());
                    tasks.add(tmp);
                    System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
                }
                catch (DukeException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            else if(cmd.length() >= 5 &&cmd.substring(0, 5).equals("event")) {
                try {
                    checkCmd(cmd, 5, "☹ OOPS!!! The description of a event cannot be empty.");
                    String getName = parser.getNameAt(cmd);
                    String getTime = parser.getDeadlineAt(cmd);
                    getTime = formatDate(getTime);
                    Event tmp = new Event(getName, getTime);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println("  " + tmp.getStatus());
                    tasks.add(tmp);
                    System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
                    //
                }
                catch (DukeException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            else if(cmd.length() >= 6 && cmd.substring(0, 6).equals("delete")){
                int c = 0;
                for(int i = 7; i < cmd.length(); ++i) {
                    c = c * 10 + cmd.charAt(i) - '0';
                }
                System.out.println("Noted. I've removed this task: ");
                System.out.println(tasks.get(c - 1).getStatus());
                tasks.remove(c - 1);
                System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
            }
            else System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            storage.updateDataFile(tasks.getArrayList());
        }
    }


    public static String formatDate(String str) {
        LocalDate d;
        try {
            d = LocalDate.parse(str);
        } catch (Exception e) {
            return str;
        }
        return d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public static void checkCmd(String cmd, int len, String Ex) throws DukeException {
        if(cmd.length() == len) throw new DukeException(Ex);
    }

}
