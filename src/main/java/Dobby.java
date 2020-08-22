import javax.imageio.IIOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Dobby {
    // String for output format
    private static final String UNDERSCORE = "_________________________________________" +
            "______________________________________________";

    // ArrayList to store Tasks
    private static ArrayList<Task> tasks = new ArrayList<>();

    private static final String ALL_COMMANDS = "\n    You can use the following commands in this chat bot:"
            + (Commands.TODO).getUsage()
            + (Commands.DEADLINE).getUsage()
            + (Commands.EVENT).getUsage()
            + (Commands.LIST).getUsage()
            + (Commands.DONE).getUsage()
            + (Commands.DELETE).getUsage()
            + (Commands.BYE).getUsage();

    public static void main(String[] args) {

        reply("\n    Hello! I'm Dobby" + ALL_COMMANDS + "\n    How can I help you?\n    ");
        Scanner scanner = new Scanner(System.in);
        readFile();
        while (scanner.hasNext()) {
            String text = scanner.nextLine();
            try {
                reply(getMessage(text));
            } catch (DobbyException e) { // prints error message
                reply(e.getMessage());
            }
            if (text.equals("bye")) { // terminates program after bye command
                rewriteFile();
                System.exit(0);
            }
        }

    }

    private static void readFile () {
        try {
            File file = new File("./data/dobbylist.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String str = scanner.nextLine();
                createList(str);
            }
        } catch (FileNotFoundException e) {
            reply("\n    " + e.getMessage() + "\n    ");
            System.exit(0);
        }
    }

    private static void rewriteFile() {
        try {
            FileWriter fw = new FileWriter("./data/dobbylist.txt");
            fw.flush();
            for (int i = 0; i < tasks.size() - 1; i++) {
                Task task = tasks.get(i);
                fw.write(task.getDescription() + System.lineSeparator());
            }
            fw.write(tasks.get(tasks.size() - 1).getDescription());
            fw.close();
        } catch (IOException e) {
            reply("\n    " + e.getMessage() + "\n    ");
        }
    }

    private static void createList(String str) {
        boolean isDone = str.charAt(4) == '\u2713';
        Task task;
        if (str.charAt(1) == 'T') {
            String decription = str.substring(str.indexOf(' ') + 1);
            task = new Todo(decription);
            tasks.add(task);
        } else if (str.charAt(1) == 'D') {
            String description = str.substring(str.indexOf(' ') + 1, str.indexOf("(by: "));
            String by = str.substring(str.indexOf("(by: ") + 5, str.length() - 1);
            task = new Deadline(description, by);
            tasks.add(task);
        } else {
            String description = str.substring(str.indexOf(' ') + 1, str.indexOf("(at: "));
            String at = str.substring(str.indexOf("(at: ") + 5, str.length() - 1);
            task = new Event(description, at);
            tasks.add(task);
        }

        if (isDone) {
            task.setDone();
        }
    }

    // Returns the chat bot reply or the error message depending on the input
    private static String getMessage(String text) throws DobbyException {
        String message = "";

        if (text.equalsIgnoreCase("bye")) { // bye command
            message = "\n    Goodbye. Hope to see you again soon!\n    ";
        } else if (text.startsWith("todo")) { // todo command
            try {
                text = text.substring(5).trim();
                Todo todo = new Todo(text);
                tasks.add(todo);
                message = "\n    Great! I've added the following task:\n      " + todo.getDescription() +
                        String.format("\n    Now you have %d tasks in the list.\n    ", tasks.size());
            } catch (StringIndexOutOfBoundsException e) {
                throw new DobbyException("\n    Incorrect usage of command. Description cannot be empty. Please try again."
                        + (Commands.TODO).getUsage() + "\n    ");
            }
        } else if (text.startsWith("deadline")) { // deadline command
            String by = "";
            try {
                text = text.substring(9).trim();
                if (text.indexOf("/by") <= 1) { // empty description or /by missing
                    throw new DobbyException("\n    Incorrect usage of command. Description cannot be empty. Please try again."
                            + (Commands.DEADLINE).getUsage()  + "\n    ");
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
                            + (Commands.DEADLINE).getUsage()  + "\n    ");
                } else { // no deadline details specified
                    throw new DobbyException("\n    Incorrect usage of command. Deadline details cannot be empty. Please try again."
                            + (Commands.DEADLINE).getUsage()  + "\n    ");
                }
            } catch (DobbyException e) { // empty description or /by missing
                return e.getMessage();
            }
        } else if (text.startsWith("event")) { // event command
            String at = "";
            try {
                text = text.substring(6).trim();
                if (text.indexOf("/at") <= 1) { // empty description or /at missing
                    throw new DobbyException("\n    Incorrect usage of command. Description cannot be empty. Please try again."
                            + (Commands.EVENT).getUsage()  + "\n    ");
                }
                at = text.substring(text.indexOf("/at") + 4);
                text = text.substring(0, text.indexOf("/at") - 1);
                Event event = new Event(text, at);
                tasks.add(event);
                message = "\n    Great! I've added the following task:\n      " + event.getDescription() +
                        String.format("\n    Now you have %d tasks in the list.\n    ", tasks.size());
            } catch (StringIndexOutOfBoundsException e) {
                if (text.startsWith("event") || text == null) { // description empty
                    throw new DobbyException("\n    Incorrect usage of command. Description cannot be empty. Please try again."
                            + (Commands.EVENT).getUsage()  + "\n    ");
                } else { // no schedule details specified
                    throw new DobbyException("\n    Incorrect usage of command. Schedule details cannot be empty. Please try again."
                            + (Commands.EVENT).getUsage()  + "\n    ");
                }
            } catch (DobbyException e) { // empty description or /at missing
                return e.getMessage();
            }
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
                if (tasks.size() < index) { // if index is out of range throw exception
                    throw new DobbyException("\n    Incorrect usage of command. Task number must be within the correct range."
                            + (Commands.DONE).getUsage() + "\n    ");
                }
                Task task = tasks.get(index - 1);
                task.setDone();
                message = "\n    Great! I've marked this task as done:\n      " + task.getDescription() + "\n    ";
            } catch (DobbyException e) { // if index is out of range return message
                return e.getMessage();
            } catch (Exception e) { // missing number after done
                throw new DobbyException("\n    Incorrect usage of command. Please enter a task number after done."
                        + (Commands.DONE).getUsage() + "\n    ");
            }
        } else if (text.startsWith("delete")) { // delete command
            try {
                text = text.substring(6).trim();
                int index = Integer.parseInt(text);
                if (tasks.size() < index) { // if index is out of range throw exception
                    throw new DobbyException("\n    Incorrect usage of command. Task number must be within the correct range."
                            + (Commands.DELETE).getUsage() + "\n    ");
                }
                Task task = tasks.get(index - 1);
                tasks.remove(index - 1);
                message = "\n    Noted. I've removed this task:\n      " + task.getDescription() + "\n    ";
            } catch (DobbyException e) { // if index is out of range return message
                return e.getMessage();
            } catch (Exception e) { // missing number after done
                throw new DobbyException("\n    Incorrect usage of command. Please enter a task number after delete."
                        + (Commands.DELETE).getUsage() + "\n    ");
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

