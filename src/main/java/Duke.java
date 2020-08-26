import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private  Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(this.storage.loadFile());
        } catch (DukeException e) {
            this.ui.showDukeError(e);
        } catch (IOException e) {
            this.ui.showLoadError();
        }
    }

    void handleInput(String[] input) {
        try {
            switch (input[0]) {
                case "list":
                    this.ui.list(this.tasks.formattedList());
                    break;
                case "bye":
                    this.storage.saveFile(this.tasks);
                    this.ui.exit();
                    break;
                case "done":
                    this.ui.completeTask(this.tasks.completeTask(Integer.valueOf(input[1])));
                    break;
                case "delete":
                    this.ui.deleteTask(this.tasks.deleteTask(Integer.valueOf(input[1])), this.tasks.getLength());
                    break;
                case "todo":
                case "event":
                case "deadline":
                    if (input.length == 1) {
                        throw new MissingDescriptionException(input[0]);
                    } else {
                        this.ui.addTask(this.tasks.addTask(input[0], input[1]), this.tasks.getLength());
                    }
                    break;
                default:
                    throw new UnknownCommandException();
            }
        } catch (DukeException e) {
            this.ui.showDukeError(e);
        } catch (IOException e) {
            this.ui.showSaveError();
        }
    }

    public void run() {
        this.ui.greet();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String[] input = Parser.parse(sc.nextLine());
            handleInput(input);
        }

        sc.close();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}

