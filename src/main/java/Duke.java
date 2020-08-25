import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    private void start() {
        this.storage = new Storage("Data.txt");
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.parser = new Parser(storage, ui, tasks);
        this.storage.init();
        storage.importSavedDataToList(tasks.get());
        ui.greet();
    }

    private void processInput(String input) throws DukeException {
        this.parser.processInput(input);
    }

    public static void main(String[] args) throws DukeException {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        String input;
        duke.start();
        while (sc.hasNextLine()) {
            input = sc.nextLine();
            try {
                duke.processInput(input);
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
    }
}
