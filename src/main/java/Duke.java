import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private final Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        try {
            this.storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.printGeneralChatWindow("ERROR: Loading error");
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public void run() {
        // Initial greeting, prompt user for commands
        ui.printWelcome();

        // Start scanning for user input
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        // Loop continues echoing input until input == "bye"
        while (!input.equals("bye")) {
            // Tag refers to the command to perform
            String tag = input.split(" ")[0];

            try {
                switch (tag) {
                    case "list":
                        ui.printTasksChatWindow(tasks.getTasks());
                        break;

                    case "done":
                        ui.printDoneTaskChatWindow(tasks.completeTask(input));
                        break;

                    case "delete":
                        ui.printDeleteTaskChatWindow(tasks.deleteTask(input), tasks.getTasks().size());
                        break;

                    case "todo":
                    case "event":
                    case "deadline":
                        Task toAdd = tasks.addTask(tag, input);
                        ui.printAddTaskChatWindow(toAdd, tasks.getTasks().size());
                        break;

                    default:
                        throw new DukeUnknownInputException();
                }

                // Save tasks in a save-file with each command
                storage.save(tasks.getTasks());

            } catch (IOException e) {
                ui.printGeneralChatWindow("ERROR: Loading error!");
            } catch (DukeUnknownInputException e) {
                ui.printGeneralChatWindow(e.toString());
            } catch (IndexOutOfBoundsException e) {
                ui.printGeneralChatWindow("ERROR: Invalid list number input!");
            } catch (DukeInvalidTaskDescriptionException | DukeInvalidTaskTimeException e) {
                ui.printGeneralChatWindow(e.toString());
            } finally {
                input = sc.nextLine();
            }
        }

        // Print goodbye chat window
        ui.printGoodbye();
    }
}
