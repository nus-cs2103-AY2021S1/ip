import task.*;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Duke {
    public static final String LINE = "_______________________________________\n";

    public Storage storage;
    public static TaskList taskList;
    public static Ui ui;
    public static boolean running;

    public Duke(String filePath) throws DukeException {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (DukeException e) {
            this.taskList = new TaskList();
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        this.running = true;
        while(running) {
            String userInput = scanner.nextLine();
            Parser.parse(userInput);
        }
        scanner.close();
    }

    public static void main(String[] args) throws DukeException, IOException {
        new Duke("data/duke.txt").run();
    }
}
