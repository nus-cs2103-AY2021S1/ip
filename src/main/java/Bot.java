import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Bot {
    private Scanner sc = new Scanner(System.in);
    private Tracker tracker;

    private static String instructions = "Instructions on using Duke:\n" +
            "list : key in list to show your list of tasks. key in a date after list to show your list of tasks on that date\n" +
            "date and time : follow yyyy-mm-dd format when keying in dates and hh:mm format when keying in times\n";


    public Bot(Tracker tracker) throws IOException {
        this.tracker = tracker;
    }

    public void start() throws DukeException, IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n");
        System.out.println(instructions);
        System.out.println("What can I do for you?");
        this.listen();
    }

    private void listen() throws DukeException, IOException {
        String next = sc.nextLine();
        if (next.equals("bye")) {
            this.tracker.printSection();
            System.out.println("Bye. Hope to see you again soon!");
        } else {
            try {
                if (next.startsWith("list")) {
                    this.tracker.displayList(next);
                    listen();
                } else if (next.startsWith("done ")) {
                    this.tracker.markDone(next);
                } else if (next.startsWith("delete ")) {
                    this.tracker.delete(next);
                } else if (next.startsWith("todo ")) {
                    this.tracker.addToDo(next);
                } else if (next.startsWith("deadline ")) {
                    this.tracker.addDeadline(next);
                } else if (next.startsWith("event ")) {
                    this.tracker.addEvent(next);
                } else {
                    throw new DukeException("Sorry I don't understand!");
                }
            } catch (DukeException e) {
                this.tracker.printSection();
                System.out.println(e.getMessage());
                this.tracker.printSection();
            } catch (DateTimeParseException e) {
                System.out.println("Please input a valid format e.g. event party /at 2020-08-25 19:00-22:00");
            } catch (NumberFormatException e) {
                this.tracker.printSection();
                System.out.println("Sorry I don't understand");
                this.tracker.printSection();
            } finally {
                this.listen();
            }
        }
    }
}
