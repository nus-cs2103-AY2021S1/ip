import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage);
        } catch (Exception e) {
            ui.showLoadingError();
        }
    }

    public void run(Scanner sc) {
        this.ui.showWelcome();
        this.ui.checkCommands(sc, this.tasks);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        new Duke("data/tasks.txt").run(sc);
    }
}
