import java.time.LocalDate;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        this.tasks = new TaskList();
        this.storage = new Storage(this.tasks);
        this.ui = new Ui();
    }
    public void run() {
        ui.greet();
        // System.out.println("1");
        storage.readFile();
        // System.out.println("2");
        boolean isExit = false;
        // System.out.println("3");
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                // System.out.println("4");
                ui.showLine();
                // System.out.println("5");
                Command c = Parser.parse(fullCommand);
                // System.out.println("6");
                c.execute(tasks, ui, storage);
                // System.out.println("7");
                isExit = c.isExit();
                // System.out.println("8");
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
                // System.out.println("9");
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
