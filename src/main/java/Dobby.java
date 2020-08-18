import java.util.Scanner;
import java.util.ArrayList;

public class Dobby {
    // String for output format
    private static final String UNDERSCORE = "_________________________________________" +
            "______________________________________________";

    // ArrayList to store Tasks
    private static ArrayList<Task> tasks = new ArrayList<>();

    // Following are the Strings for all the commands the bot can be used for
    private static final String TODO = "\n      todo _description_";
    private static final String DEADLINE = "\n      deadline _description_ /by _deadline_details_";
    private static final String EVENT = "\n      event _description_ /at _event_scheduled_at_";
    private static final String LIST = "\n      list";
    private static final String DONE = "\n      done _task_number_";
    private static final String BYE = "\n      bye";
    private static final String ALL_COMMANDS = "\n    You can use the following commands in this chat bot:" +
            TODO + DEADLINE + EVENT + LIST + DONE + BYE;

    public static void main(String[] args) {

        reply("\n    Hello! I'm Dobby" + ALL_COMMANDS + "\n    How can I help you?\n    ");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String text = scanner.nextLine();
            try {
                reply(getMessage(text));
            } catch (DobbyException e) { // prints error message
                reply(e.getMessage());
            }
            if (text.equals("bye")) { // terminates program after bye command
                System.exit(0);
            }
        }
    }

    // Returns the chat bot reply or the error message depending on the input
    private static String getMessage(String text) throws DobbyException {
        String message = "";

        if (text.equalsIgnoreCase("bye")) { // bye command

            message = "\n    Goodbye. Hope to see you again soon!\n    ";

        } else if (text.equalsIgnoreCase("list")) { // list command

            int i = 0;
            String all_tasks = "\n    ";
            for (Task task : tasks) {
                i++;
                all_tasks = all_tasks + i + ". " + task.getDescription() + "\n    ";
            }
            message = all_tasks;

        } else if (text.startsWith("done")) { // done command

            try {
                text = text.substring(4).trim();
                int index = Integer.parseInt(text);
                Task task = tasks.get(index - 1);
                task.setDone();
                message = "\n    Great! I've marked this task as done:\n      " + task.getDescription() + "\n    ";
            } catch (Exception e) { // missing number after done
                throw new DobbyException("\n    Incorrect usage of command. Please enter a task number after done."
                        + DONE + "\n    ");
            }

        } else if (text.startsWith("deadline")) { // deadline command

            String by = "";
            try {
                text = text.substring(9).trim();
                if (text.indexOf("/by") <= 1) { // empty description or /by missing
                    throw new DobbyException("\n    Incorrect usage of command. Description cannot be empty. Please try again."
                            + DEADLINE + "\n    ");
                }
                by = text.substring(text.indexOf("/by") + 4).trim();
                text = text.substring(0, text.indexOf("/by") - 1).trim();
                Deadline deadline = new Deadline(text, by);
                tasks.add(deadline);
                message = "\n    Great! I've added the following task:\n      " + deadline.getDescription() +
                        String.format("\n    Now you have %d tasks in the list.\n    ", tasks.size());
            } catch (StringIndexOutOfBoundsException e) {
                if (text.startsWith("deadline") || text == null) { // description empty
                    throw new DobbyException("\n    Incorrect usage of command. Description cannot be empty. Please try again."
                            + DEADLINE + "\n    ");
                } else { // no deadline details specified
                    throw new DobbyException("\n    Incorrect usage of command. Deadline details cannot be empty. Please try again."
                            + DEADLINE + "\n    ");
                }
            } catch (DobbyException e) {
                return e.getMessage();
            }

        } else if (text.startsWith("todo")) { // todo command

            try {
                text = text.substring(5).trim();
                Todo todo = new Todo(text);
                tasks.add(todo);
                message = "\n    Great! I've added the following task:\n      " + todo.getDescription() +
                        String.format("\n    Now you have %d tasks in the list.\n    ", tasks.size());
            } catch (StringIndexOutOfBoundsException e) {
                throw new DobbyException("\n    Incorrect usage of command. Description cannot be empty. Please try again."
                        + TODO + "\n    ");
            }

        } else if (text.startsWith("event")) { // event command

            String at = "";
            try {
                text = text.substring(6).trim();
                if (text.indexOf("/at") <= 1) { // empty description or /at missing
                    throw new DobbyException("\n    Incorrect usage of command. Description cannot be empty. Please try again."
                            + EVENT + "\n    ");
                }
                at = text.substring(text.indexOf("/at") + 4);
                text = text.substring(0, text.indexOf("/at") - 1);
                Event event = new Event(text, at);
                tasks.add(event);
                message = "\n    Great! I've added the following task:\n      " + event.getDescription() +
                        String.format("\n    Now you have %d tasks in the list.\n    ", tasks.size());
            } catch (StringIndexOutOfBoundsException e) {
                if (text.startsWith("event") || text == null) {
                    throw new DobbyException("\n    Incorrect usage of command. Description cannot be empty. Please try again."
                            + EVENT + "\n    ");
                } else {
                    throw new DobbyException("\n    Incorrect usage of command. Schedule details cannot be empty. Please try again."
                            + EVENT + "\n    ");
                }
            } catch (DobbyException e) {
                return e.getMessage();
            }

        } else { // unexpected input

            message =  "\n    Sorry that command is not supported. Please try again." + ALL_COMMANDS + "\n    ";
            throw new DobbyException(message);

        }

        return message;
    }

    private static void reply(String message) {
        System.out.println("    " + UNDERSCORE + message + UNDERSCORE);
    }
}

