import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isLoadingSuccess;

    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        this.isLoadingSuccess = true;

        try {
            this.storage.loadFileContents();
            this.tasks = new TaskList(this.storage.getTaskList());
        } catch (FileNotFoundException e) {
            System.out.println(e);
            this.isLoadingSuccess = false;
        }
    }

    public void run() {
        this.ui.displayWelcome();

        if (!isLoadingSuccess) {
            this.ui.displayLoadFileError();

        } else {
            this.ui.displayLoadFileSuccess();

            Parser parser = new Parser(tasks);
            Scanner sc = new Scanner(System.in);

            while (parser.isRunning()) {
                String userInput = sc.nextLine();

                try {
                    parser.checker(userInput);
                } catch (DukeException e) {
                    ui.displayUserInputError(e.getMessage());
                }

                try {
                    storage.writeToFile(parser.getTasks());
                } catch (IOException e) {
                    ui.displayUpdateFileError(e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "Desktop", "CS2103T", "ip", "data", "duke.txt");
        Duke duke = new Duke(path.toString());
        duke.run();
    }
}
