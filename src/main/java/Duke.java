import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);

        this.ui.displayWelcome();

        try {
            this.storage.loadFileContents();
            this.tasks = new TaskList(this.storage.getTaskList());
        } catch (FileNotFoundException e) {
            this.ui.displayLoadFileError();
        } finally {
            this.ui.displayLoadFileSuccess();
        }
    }

    public void run() {
        Parser parser = new Parser(tasks);
        Scanner sc = new Scanner(System.in);

        while (parser.isRunning()) {
            String userInput = sc.nextLine();

            try {
                parser.checker(userInput);
            } catch(DukeException e) {
                ui.displayUserInputError(e.getMessage());
            }

            try {
                storage.writeToFile(parser.getTasks());
            } catch (IOException e) {
                ui.displayUpdateFileError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        String FILE_PATH = "data/duke.txt";
        new Duke(FILE_PATH).run();
    }
}
