import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    ArrayList<Task> tasklist;
    String divider = "\t_________________________________________\n";

    public Duke() {
        this.tasklist = new ArrayList<>();
    }

    public void addToDo(String description) throws DukeException {
        if (description.length() <= 0) {
            throw new DukeException("Please provide a description!");
        }
        Task t = new Todo(description);
        this.tasklist.add(t);
        printOutputAddTask(t);
    }

    public void addDeadline(String details) throws DukeException {
        try {
            String[] temp = details.split("/by");
            String description = temp[0].trim();
            String by = temp[1].trim();

            if (description.length() <= 0 || by.length() <= 0) {
                throw new DukeException("Please provide both a description and date in format 'description /by date'!");
            }
            Task t = new Deadline(description, by);
            this.tasklist.add(t);
            printOutputAddTask(t);

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please provide both a description and date in format 'description /by date'!");
        } catch (DateTimeParseException e) {
            throw new DukeException("Please provide date in format yyyy-mm-dd");
        }
    }

    public void addEvent(String details) throws DukeException {
        try {
            String[] temp2 = details.split("/at");
            String description = temp2[0].trim();
            String at = temp2[1].trim();

            if (description.length() <= 0 || at.length() <= 0) {
                throw new DukeException("Please provide both a description and date in format 'description /at date'!");
            }
            Task t = new Event(description, at);
            this.tasklist.add(t);
            printOutputAddTask(t);

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please provide both a description and date in format 'description /at date'!");
        } catch (DateTimeParseException e) {
            throw new DukeException("Please provide date in format yyyy-mm-dd");
        }
    }

    public void deleteTask(int taskIndex) throws DukeException {
        try {
            Task t = tasklist.get(taskIndex - 1);
            tasklist.remove(taskIndex - 1);

            System.out.println(divider);
            System.out.println("\tMr Camel will delete this task:\n" + "\t" + t);
            System.out.println("\tNumber of tasks: " + tasklist.size());
            System.out.println(divider);

        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number!");
        }
    }

    public void doneTask(int taskIndex) throws DukeException {
        try {
            Task t = tasklist.get(taskIndex - 1);
            t.markAsDone();

            System.out.println(divider);
            System.out.println("\tMr Camel will mark this task as done:\n" + "\t" + t);
            System.out.println(divider);

        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number!");
        }
    }

    public void printOutputAddTask(Task t) {
        System.out.println(divider);
        System.out.println("\tMr Camel has added: " + t);
        System.out.println("\tNumber of tasks: " + tasklist.size());
        System.out.println(divider);
    }


    public void printList() {
        System.out.println(divider);
        System.out.println("\tTasks:");

        for (int i = 1; i < tasklist.size() + 1; i++) {
            System.out.println("\t" + i + ". " + tasklist.get(i-1));
        }
        System.out.println(divider);
    }

    public void initDuke() {
        String MrCamel =
                "                  ,,__\n"
                        + "        ..  ..   / o._)\n"
                        + "       /--'/--\\  \\-'|| \n"
                        + "      /        \\_/ / |\n"
                        + "    .'\\  \\__\\  __.'.'\n"
                        + "      )\\ |  )\\ |\n"
                        + "     // \\\\ // \\\\\n"
                        + "    ||_  \\\\|_  \\\\_\n"
                        + "    '--' '--'' '--'\n";
        System.out.println("Mr Camel says hello. What can Mr Camel do for you today?\n" + MrCamel);
    }

    public void runDuke() {
        Scanner sc = new Scanner(System.in);
        boolean hasNext = true;

        while (hasNext) {
            String command = sc.next();
            try {
                switch (command) {
                    case "bye":
                        System.out.println(divider);
                        System.out.println("\tBye. Mr Camel will miss you! :(");
                        System.out.println(divider);
                        hasNext = false;
                        break;
                    case "list":
                        printList();
                        break;
                    case "todo":
                        String todoDescription = sc.nextLine().trim();
                        addToDo(todoDescription);
                        break;
                    case "deadline":
                        String deadlineDetails = sc.nextLine();
                        addDeadline(deadlineDetails);
                        break;
                    case "event":
                        String eventDetails = sc.nextLine();
                        addEvent(eventDetails);
                        break;
                    case "done":
                        int doneTaskIndex = sc.nextInt();
                        doneTask(doneTaskIndex);
                        break;
                    case "delete":
                        int deleteTaskIndex = sc.nextInt();
                        deleteTask(deleteTaskIndex);
                        break;

                    default:
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(divider);
                System.out.println("\t" + e.getMessage());
                System.out.println(divider);
            }
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke();

        duke.initDuke();
        duke.runDuke();
    }
}
