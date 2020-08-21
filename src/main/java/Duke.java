import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    //    private List<Task> toDoList;
    private final Ui ui;
    private final DukeFileHandler fileHandler;
    private TaskList tasks;

    private Duke() {
        ui = new Ui();
        ui.welcome();

        fileHandler = new DukeFileHandler("data/dukeData.txt");

        try {
            tasks = new TaskList(fileHandler.readFile());
            if (!tasks.isNull()) {
                ui.displayList(tasks.getList());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            tasks = new TaskList(new ArrayList<>());
        }

        run();
    }

    private void run() {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine().trim();

            try {
                Command command = Parser.parse(input);
                command.execute(tasks, ui, fileHandler);

                if (command instanceof ExitCommand) {
                    break;
                }

            } catch (DukeException ex) {
                ui.displayThis(ex.getMessage());
            }

        }
    }


    public static void main(String[] args) {
        new Duke();
    }

}