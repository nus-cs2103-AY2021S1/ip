import java.util.Scanner;

public class Dobby {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    public Dobby (String filePath) {
        this.tasks = new TaskList();
        parser = new Parser(this.tasks);
        this.storage = new Storage(filePath, tasks);
        ui = new Ui();
    }

    public void run() {
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        this.storage.readFile();
        while (true) {
            String text = ui.getInput();
            try {
                ui.reply(parser.getMessage(text));
            } catch (DobbyException e) { // prints error message
                ui.reply(e.getMessage());
            }
            if (text.equals("bye")) { // terminates program after bye command
                storage.rewriteFile();
                System.exit(0);
            }
        }
    }

    public static void main (String[] args) {
        new Dobby("../dobbylist.txt").run();
    }
}