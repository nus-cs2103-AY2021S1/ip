import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList taskList;

    public Duke(String filePath) {
        try {
            this.storage = new Storage(filePath);
            this.taskList = new TaskList(this.storage.readFromFile());
        } catch (DukeException e) {
            Ui.showMessage(e.getMessage());
        }
    }

    public void run() {
        Ui.showWelcome();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            try {
                Ui.showUpperLine();
                Command next = Parser.parse(input);
                next.execute(this.taskList, this.storage);
            } catch (DukeException e) {
                Ui.showMessage(e.getMessage());
            } finally {
                input = scanner.nextLine();
                Ui.showLowerLine();
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        new Duke("./data/dukeFile.txt").run();
    }
}
