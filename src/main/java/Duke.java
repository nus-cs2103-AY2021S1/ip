import java.io.*;
import java.util.Scanner;

public class Duke {
    private static final String PATH = "data/duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            Parser.parse(sc.nextLine(), tasks, ui, storage);
        }
    }

    public static void main(String[] args) {
        new Duke(PATH).run();
    }
}