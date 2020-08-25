import java.io.IOException;
import java.util.Scanner;
import java.time.format.DateTimeParseException;

public class Duke {

    private Storage storage;
    private Tasklist taskList;
    private UserInterface ui;

    // constructor for the chat bot
    public Duke() {
        storage = new Storage();
        taskList = new Tasklist(storage);
        ui = new UserInterface();
    }

    public static void main(String[] args) {
        startBot();
    }

    public static void startBot() {
        Duke duke = new Duke();
        duke.initialise();
        duke.userCommand();

        // when bot stops
        duke.exit();
    }

    public void initialise() {
        try {
            ui.welcomeMessage();
            taskList.loadList();
        } catch (IOException e) {
            System.out.print(e.getStackTrace());
        }
    }

    public void userCommand() {

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        try {

            while (!input.equals("bye")) {

                String command = input.split(" ")[0];
                int size = taskList.getTaskSize();

                if (command.equals("list")) {
                    commandList();
                } else if (command.equals("done") || command.equals("delete")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    commandDoneDelete(index, command);
                } else if (commandTaskChecker(command)) {
                    String message = input.substring(command.length());
                    commandTasks(command, message);
                } else {
                    String errorMessage = "Command is wrong. Please start with delete, list, done, deadline, todo or event.";
                    throw new DukeCommandException(errorMessage);
                }
                this.taskList.updateStorage();
                input = sc.nextLine();
            }
        } catch (DukeCommandException | DukeIndexException | DukeTaskException | DukeListException e) {
            ui.printError(e.getMessage());
        } catch (DateTimeParseException e) {
            ui.printError("INPUT DATE TIME FORMAT IS WRONG");
        } catch (IOException e) {
            ui.printError(e.getMessage());
        }
    }

    public void commandList() throws DukeListException {
        if (taskList.getTaskSize() != 0) {
            ui.listTask();
            for (int i = 0; i < taskList.getTaskSize(); i++) {
                ui.printTask(i + 1, taskList.get(i).toString());
            }
        } else {
            throw new DukeListException("Your list is empty.");
        }
    }

    public void commandDoneDelete(int index, String mode) throws DukeIndexException {
        if (index > taskList.getTaskSize() - 1 || index < 0) {
            String errorMessage = "Wrong list number input. " +
                    "Please put a number between 1 and " + taskList.getTaskSize();
            throw new DukeIndexException(errorMessage);
        }

        if (mode.equals("done")) {
            taskList.makeTaskDone(index);
            ui.printDone(taskList.get(index).toString());
        } else {
            ui.printDelete(taskList.get(index).toString(), taskList.getTaskSize());
            taskList.removeTask(index);
        }

    }

    public void commandTasks(String tag, String message) throws DukeTaskException {

        String[] parsedMessage = null;
        Task newTask = null;
        int size = taskList.getTaskSize();

        try {

            if (message.isEmpty()) {
                throw new IndexOutOfBoundsException();
            } else if (tag.equals("todo")) {
                newTask = new Todo(message);
            } else if (tag.equals("deadline")) {
                parsedMessage = message.split("/by ");
                newTask = new Deadline(parsedMessage[0], parsedMessage[1]);
            } else if (tag.equals("event")) {
                parsedMessage = message.split("/at ");
                newTask = new Event(parsedMessage[0], parsedMessage[1]);
            }

            taskList.addTask(newTask);
            ui.printAddTask(newTask.toString(), taskList.getTaskSize());

        } catch (IndexOutOfBoundsException e) {
            String errorMessage = "You might have left your message or duration empty.";
            throw new DukeTaskException(errorMessage);
        }
    }

    public boolean commandTaskChecker(String command) throws DukeCommandException {
        return command.equals("event") || command.equals("deadline") || command.equals("todo");
    }

    public void exit() {
        ui.exitMessage();
    }

}
