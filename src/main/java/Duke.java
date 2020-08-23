import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {
    private static final String SAVEPATH = "./src/main/data/SaveData.txt";
    private static enum AcceptedCommands {
        TODO,
        EVENT,
        DEADLINE,
        LIST,
        DONE,
        BYE,
        DELETE,
        CLEAR,
        HELLO,
    }
    public static Scanner scan = new Scanner(System.in);
    private Storage storage;
    private TaskList tasks;

    public Duke(String filepath) {
        storage = new Storage(filepath);
        tasks = TaskList.loadTasks(storage);
    }

    // Driver method to respond to user input
    private void run(String userInput) {
        while (!userInput.equals("bye")) {
            try {
                String command = Parser.getCommand(userInput);
                String details = Parser.getDetails(userInput);

                // Catch illegal commands
                checkIllegalCommand(command);
                // Catch missing content
                checkMissingArgument(command, details);
                // Check indexing out of bounds
                checkExistingTask(command, details, tasks.length());
                // Check illegal argument
                checkIllegalArgument(command, details);

                // Decide the actions
                String[] description;
                Task taskToUpdate;
                switch (command) {
                    case "list":
                        Ui.prettyPrint(tasks);
                        break;
                    case "done":
                        taskToUpdate = tasks.updateTaskStatus(Parser.getIndex(userInput), true);
                        Ui.prettyPrint("Nice! I've marked this task as done: \n" + "\t" + taskToUpdate);
                        break;
                    case "todo":
                        taskToUpdate = tasks.addTask(new ToDo(Parser.getDetails(userInput)));
                        Ui.updateTaskText("added", taskToUpdate, tasks.length());
                        break;
                    case "event":
                        description = Parser.stringSplit(details, " /at ");
                        taskToUpdate = tasks.addTask(new Event(description[0], LocalDate.parse(description[1])));
                        Ui.updateTaskText("added", taskToUpdate, tasks.length());
                        break;
                    case "deadline":
                        description = Parser.stringSplit(details, " /by ");
                        taskToUpdate = tasks.addTask(new Deadline(description[0], LocalDate.parse(description[1])));
                        Ui.updateTaskText("added", taskToUpdate, tasks.length());
                        break;
                    case "delete":
                        taskToUpdate = tasks.removeTask(Parser.getIndex(userInput));
                        Ui.updateTaskText("removed", taskToUpdate, tasks.length());
                        break;
                    case "clear":
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        break;
                    case "hello":
                        Ui.greet();
                        break;
                }
                // ToDo: handle localDate parse error
                // ToDo: stretch goal level-8
            } catch (DukeIllegalCommandException | DukeMissingArgumentException | DukeTaskOutOfBoundsException e) {
                System.out.println(e.toString());
            } catch (DateTimeParseException e) {
                System.out.println("date time wrong");
            } catch (Exception e) {
                System.out.println("Write a number pls");
            }

            try {
                storage.saveTask(tasks);
            } catch (IOException e) {
                System.out.println(e);
            }

            // Gets the new input
            userInput = scan.nextLine();
        }

        Ui.prettyPrint("Bye. Hope to see you again soon!");
    }

    // Check if command user input is valid
    private static void checkIllegalCommand(String command) throws DukeIllegalCommandException {
        for (AcceptedCommands i : AcceptedCommands.values()) {
            if (command.equalsIgnoreCase(i.name())) {
                return;
            }
        }

        throw new DukeIllegalCommandException();
    }

    // Check if command of user missing arguments
    private static void checkMissingArgument(String command, String details) throws DukeMissingArgumentException {
        if (!(command.equalsIgnoreCase(AcceptedCommands.LIST.name())
                || command.equalsIgnoreCase(AcceptedCommands.BYE.name())
                || command.equalsIgnoreCase(AcceptedCommands.CLEAR.name())
                || command.equalsIgnoreCase(AcceptedCommands.HELLO.name()))
                        && (details.isEmpty())) {
            throw new DukeMissingArgumentException(command);
        }
    }

    // Check if trying to access Tasks index out of the list
    private static void checkExistingTask(String command, String details, int max) throws DukeTaskOutOfBoundsException {
        if ((command.equalsIgnoreCase(AcceptedCommands.DONE.name()))
                || command.equalsIgnoreCase(AcceptedCommands.DELETE.name())) {
            if ((Integer.parseInt(details) < 1) || (Integer.parseInt(details) >= (max + 1))) {
                throw new DukeTaskOutOfBoundsException(command);
            }
        }
    }

    private static void checkIllegalArgument(String command, String details) throws Exception {
        if (command.equalsIgnoreCase(AcceptedCommands.DONE.name())
                || command.equalsIgnoreCase(AcceptedCommands.DELETE.name())) {
            Integer.parseInt(details);
        }
    }

    public static void main(String[] args) {
        new Duke(SAVEPATH).run("hello");
    }
}
