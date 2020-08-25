import java.time.DateTimeException;
import java.time.LocalDate;

import java.util.*;
public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\n" +
                "Send me a task in one of the following formats and I'll store it for you.\n" +
                "\tTodo: \"todo <description>\"\n" +
                "\tDeadline: \"deadline <description> /by <YYYY-MM-DD>\"\n" +
                "\tEvent: \"event <description> /at <YYYY-MM-DD>\"\n" +
                "Send \"list\" to see all tasks.\n" +
                "Send \"done <item number>\" to mark an item as done\n" +
                "Send \"delete <item number>\" to delete anj item from the list\n" +
                "Send \"bye\" to end our conversation.");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while(!input.equals("bye")) {
            String[] strings = input.split(" ");
            try {
                switch (convertToEnum(strings[0])) {
                    case LIST:
                        if (tasks.isEmpty()) {
                            System.out.println("Theres currently nothing in your list.");
                        } else {
                            for (int i = 0; i < tasks.size(); i++) {
                                System.out.printf("%d. %s%n", i + 1, tasks.get(i));
                            }
                        }
                        break;
                    case DONE:
                        try {
                            int itemNumber = Integer.parseInt(input.split(" ")[1]);
                            tasks.get(itemNumber - 1).isDone = true;
                            System.out.println("Nice, I've marked this item as done:");
                            System.out.println("\t" + tasks.get(itemNumber - 1));
                        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                            throw new DukeException("Please key in a valid number for \"done\"");
                        }
                        break;
                    case DELETE:
                        try {
                            int itemNumber = Integer.parseInt(input.split(" ")[1]);
                            System.out.println("Noted. I've removed this task:");
                            System.out.println("\t" + tasks.get(itemNumber - 1));
                            tasks.remove(itemNumber - 1);
                            System.out.println("You now have " + tasks.size() + " tasks in the list.");
                        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                            throw new DukeException("Please key in a valid number for \"delete\"");
                        }
                        break;
                    case TODO:
                        String task = String.join(" ", Arrays.copyOfRange(strings, 1, strings.length));
                        if (task.isEmpty()) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                        Task todoTask = new Todo(task);
                        tasks.add(todoTask);
                        System.out.println("Got it. I've added this task:\n\t" + todoTask);
                        System.out.println("You now have " + tasks.size() + " tasks in the list.");
                        break;
                    case DEADLINE:
                        try {
                            task = String.join(" ", Arrays.copyOfRange(strings, 1, strings.length));
                            String description = task.split(" /by ")[0];
                            String by = task.split(" /by ")[1];
                            Task deadlineTask = new Deadline(description, LocalDate.parse(by));
                            tasks.add(deadlineTask);
                            System.out.println("Got it. I've added this task:\n\t" + deadlineTask);
                            System.out.println("You now have " + tasks.size() + " in the list.");
                        } catch (ArrayIndexOutOfBoundsException ex) {
                            throw new DukeException("Please key in a valid deadline command!");
                        } catch (DateTimeException ex) {
                            throw new DukeException(ex.getMessage());
                        }
                        break;
                    case EVENT:
                        try {
                            task = String.join(" ", Arrays.copyOfRange(strings, 1, strings.length));
                            String description = task.split(" /at ")[0];
                            String at = task.split(" /at ")[1];
                            Task eventTask = new Event(description, LocalDate.parse(at));
                            tasks.add(eventTask);
                            System.out.println("Got it. I've added this task:\n\t" + eventTask);
                            System.out.println("You now have " + tasks.size() + " in the list.");
                        } catch (ArrayIndexOutOfBoundsException ex) {
                            throw new DukeException("Please key in a valid event command!");
                        } catch (DateTimeException ex) {
                            throw new DukeException(ex.getMessage());
                        }
                        break;
                    default:
                        throw new DukeException("Please key in a correct command.");
                }
            } catch (DukeException ex) {
                System.err.println(ex.getMessage());
            }
            input = scanner.nextLine();
            
        }
        System.out.println("Bye, hope to chat again soon!");
    }
    
    private static Command convertToEnum(String string) throws DukeException{
        switch (string) {
            case "bye":
                return Command.BYE;
            case "list":
                return Command.LIST;
            case "done":
                return Command.DONE;
            case "delete":
                return Command.DELETE;
            case "todo":
                return Command.TODO;
            case "deadline":
                return Command.DEADLINE;
            case "event":
                return Command.EVENT;
            default:
                throw new DukeException("Please key in a correct command.");
        }
    }
}
