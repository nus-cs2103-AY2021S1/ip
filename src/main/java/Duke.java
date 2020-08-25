import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.load());
    }

    public void run() {
        try {
            ui.intro();
            Scanner input = new Scanner(System.in);

            // Uses user input text and acts accordingly.
            loop:
            while (input.hasNextLine()) {
                String line = input.nextLine();
                Command command = ui.parseCommand(line);
                ui.lineBreak();

                switch (command.name) {
                case "bye":
                    System.out.println("That's it? That's a shame. Well, see you later then.");
                    ui.lineBreak();
                    storage.saveTasks();
                    break loop;
                case "list":
                    tasks.printList();
                    break;
                case "done":
                    tasks.taskDone(command.index);
                    break;
                case "delete":
                    tasks.removeFromList(command.index);
                    break;
                case "error":
                    System.out.println(command.errorMessage);
                    break;
                default:
                    tasks.addToList(command.task);
                }
                ui.lineBreak();
            }

            input.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
