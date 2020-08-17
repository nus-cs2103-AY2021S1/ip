import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bot {
    private List<Task> list = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void start() throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        this.listen();
    }

    private void listen() throws DukeException {
        String next = sc.nextLine();
        if (next.equals("bye")) {
            this.printSection();
            System.out.println("Bye. Hope to see you again soon!");
        } else if (next.equals("list")) {
            this.displayList();
            listen();
        } else if (next.startsWith("done ")){
            try {
                this.markDone(next);
            } catch (NumberFormatException e) {
                this.printSection();
                System.out.println("Please input a number");
                this.printSection();
            } catch (IndexOutOfBoundsException e) {
                this.printSection();
                System.out.println("Please input a valid number");
                this.printSection();
            } finally {
                this.listen();
            }
        } else {
            try {
                if (next.startsWith("todo ")) {
                    this.addToDo(next);
                } else if (next.startsWith("deadline ")) {
                    this.addDeadline(next);
                } else if (next.startsWith("event ")) {
                    this.addEvent(next);
                } else {
                    throw new DukeException("Sorry I don't understand!");
                }
            } catch (DukeException e) {
                this.printSection();
                System.out.println(e.getMessage());
                this.printSection();
            } finally {
                this.listen();
            }
        }
    }

    private void displayList() {
        this.printSection();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + "." + list.get(i));
        }
        this.printSection();
    }

    private void markDone(String num) {
        int i = Integer.valueOf(num.substring(5, num.length()));
        list.get(i - 1).markAsDone();
        this.printSection();
        System.out.println("Nice! I've marked this task as done:\n\t" + list.get(i - 1));
        this.printSection();
    }

    private void addToDo(String next) {
        ToDo todo = new ToDo(next.substring(5, next.length()));
        list.add(todo);
        this.printSection();
        System.out.println("Got it. I've added this task:\n\t" + todo);
        System.out.println("Now you have " + list.size() + " tasks in the list");
        this.printSection();
    }

    private void addDeadline(String next) throws DukeException {
        int cut = next.indexOf(" /by ");
        if (cut != -1) {
            String desc = next.substring(9, cut);
            String by = next.substring(cut + 5, next.length());
            Deadline deadline = new Deadline(desc, by);
            list.add(deadline);
            this.printSection();
            System.out.println("Got it. I've added this task:\n\t" + deadline);
            System.out.println("Now you have " + list.size() + " tasks in the list");
            this.printSection();
        } else {
            throw new DukeException("Please input a deadline");
        }
    }

    private void addEvent(String next) throws DukeException {
        int cut = next.indexOf(" /at ");
        if (cut != -1) {
            String desc = next.substring(6, cut);
            String time = next.substring(cut + 5, next.length());
            Event event = new Event(desc, time);
            list.add(event);
            this.printSection();
            System.out.println("Got it. I've added this task:\n\t" + event);
            System.out.println("Now you have " + list.size() + " tasks in the list");
            this.printSection();
        } else {
            throw new DukeException("Please input a time for the event");
        }
    }

    private void printSection() {
        System.out.println("____________________________________________________________");
    }
}
