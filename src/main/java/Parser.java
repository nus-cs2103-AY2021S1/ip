import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    private TaskList tasks;

    Parser() {
      this.tasks = new TaskList();
    }

    public void parseCommands(String command, Scanner sc) {
        switch (command) {
        case "bye":
            onByeCommand(this.tasks.getDatabase());
            break;

        case "list":
            this.tasks.list();
            break;

        case "done":
            this.tasks.onDoneCommand(sc);
            break;

        case "delete":
            this.tasks.onDeleteCommand(sc);
            break;

        case "todo":
            this.tasks.onToDoCommand(sc);
            break;

        case "deadline":
            this.tasks.onDeadlineCommand(sc);
            break;

        case "event":
            this.tasks.onEventCommand(sc);
            break;

        default:
            this.tasks.noSuchCommand();
            break;
        }
    }

    public void onByeCommand(ArrayList<Task> database) {
        this.tasks.saveStateToDatabase();
        System.out.println("Goodbye! All the best and see you again soon!");
        System.exit(0);
    }
}
