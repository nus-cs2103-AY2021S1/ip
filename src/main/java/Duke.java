import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        ui.showWelcome();
        while (true) {
            String input = sc.nextLine();
            ui.showLine();
            try {
                Choice c = parser.parseCommand(input);
                switch (c) {
                    case BYE:
                        System.out.println("Bye. Hope to see you again soon!");
                        storage.writeToFile(tasks.getTaskList());
                        System.exit(0); // End program
                    case DELETE:
                        tasks.deleteTask(input);
                        break;
                    case DONE:
                        tasks.completeTask(input);
                        break;
                    case LIST:
                        tasks.listAllTasks();
                        break;
                    case TODO:
                        tasks.addTodo(input);
                        break;
                    case EVENT:
                        tasks.addEvent(input);
                        break;
                    case DEADLINE:
                        tasks.addDeadline(input);
                        break;
                    default:
                        System.out.println("Error in switch - Default case");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws DukeException, FileNotFoundException {
        new Duke("data.txt").run();
    }
}