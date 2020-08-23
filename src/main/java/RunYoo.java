import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RunYoo {

    public static void run(Scanner sc) throws Exception {

        ArrayList<Task> al = new ArrayList<>();
        String input = sc.nextLine();

        while(!input.equals("bye")) {

            String temp[] = input.split(" ", 2);

            if (input.equals("list")) {
                displayList(al);

            } else {
                try {
                    if (temp[0].equals("done"))
                        markAsDone(al, temp);
                    else if (temp[0].equals("delete"))
                        deleteTask(al, temp);
                    else if (temp[0].equals("todo"))
                        addTodo(al, temp);
                    else if (temp[0].equals("deadline"))
                        addDeadline(al, temp);
                    else if (temp[0].equals("event"))
                        addEvent(al, temp);
                    else
                        throw new YooException("Sorry, I didn't get that (\u3063*\u00B4\u25A1`)\u3063");

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("You're missing a task description (\u3063*\u00B4\u25A1`)\u3063");
                }
            }
            input = sc.nextLine();
        }
        System.out.println("Bye! Come back soon ( ^-^)/");
    }


    public static void displayList(ArrayList<Task> al) {
        System.out.println("Here's your list!");
        for(int i = 1; i <= al.size(); i ++) {
            System.out.println(i + ". "
                    + al.get(i - 1));
        }
    }

    public static void markAsDone(ArrayList<Task> al, String[] temp) throws YooException {
        int index = Integer.parseInt(temp[1]);
        if (index > al.size()) {
            throw new YooException("No such task (>_<)");
        } else {
            System.out.println("Good job completing the task! \u256D( \uFF65\u3142\uFF65)\u0648");
            Task t = al.get(index - 1);
            t.markAsDone();
            System.out.println(index + ". " + t);
        }
    }

    public static void deleteTask(ArrayList<Task> al, String[] temp) throws YooException {
        int index = Integer.parseInt(temp[1]);
        if (index > al.size()) {
            throw new YooException("No such task (>_<)");
        } else {
            Task t = al.remove(index - 1);
            System.out.println("I've deleted the following task! \n" + t);
            System.out.println("Now you have " + al.size() + " tasks in the list (\u00B4\u30FB\u03C9\u30FB\uFF40)");
        }
    }

    public static void addTodo(ArrayList<Task> al, String[] temp) {
        Todo td = new Todo(temp[1]);
        al.add(td);
        System.out.println("I've added the following task! \n" + td);
        System.out.println("Now you have " + al.size() + " tasks in the list (\u00B4\u30FB\u03C9\u30FB\uFF40)");
    }

    private static void addDeadline(ArrayList<Task> al, String[] temp) {
        try {
            String[] a = temp[1].split("/by ", 2);
            LocalDate by = LocalDate.parse(a[1]);
            Deadline dl = new Deadline(a[0], by);
            al.add(dl);
            System.out.println("I've added the following task! \n" + dl);
            System.out.println("Now you have " + al.size() + " tasks in the list (\u00B4\u30FB\u03C9\u30FB\uFF40)");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry, your deadline time is missing (\u3063*\u00B4\u25A1`)\u3063");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date! Please try again (>_<)");
        }
    }

    private static void addEvent(ArrayList<Task> al, String[] temp) {
        try {
            String[] a = temp[1].split("/at ", 2);
            LocalDate at = LocalDate.parse(a[1]);
            Event e = new Event(a[0], at);
            al.add(e);
            System.out.println("I've added the following task! \n" + e);
            System.out.println("Now you have " + al.size() + " tasks in the list (\u00B4\u30FB\u03C9\u30FB\uFF40)");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry, your event time is missing (\u3063*\u00B4\u25A1`)\u3063");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date! Please try again (>_<)");
        }
    }
}