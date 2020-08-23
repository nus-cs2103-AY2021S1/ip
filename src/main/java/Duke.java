import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Duke {
    private TaskList taskList;
    private final Ui ui;

    Duke() {
        taskList = new TaskList();
        ui = new Ui();
    }

    void run() throws DukeException {
        final String FILE_PATH = "data/data.txt";
        ui.displayLogo();
        try {
            File f = new File(FILE_PATH);
            if (!f.exists()) {
                if (f.getParentFile().mkdirs() && f.createNewFile()) {
                    System.out.println("\tStorage space for you tasks has been initialized successfully.");
                }
            } else {
                taskList.processStorage(f, new Scanner(f));
            }
        } catch (IOException e) {
            System.out.println("Oops! Something went wrong when loading your tasks from storage.");
            e.printStackTrace();
            throw new DukeException(e.getMessage());
        }
        ui.greet();

        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        while (!input.equals("bye")) {
            try {
                taskList.processInput(input);
            } catch (DukeException e) {
                ui.echo(e.getMessage());
            }
            input = s.nextLine();
        }
        try {
            taskList.writeData();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        ui.bye();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        try {
            duke.run();
        } catch (DukeException e) {
            System.out.println("Oops! An unexpected error has occurred.");
        }
    }
}
