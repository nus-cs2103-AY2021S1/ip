import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();
    }

    public void run() {
        ui.Greet();
        Parser parser = new Parser(tasks, storage);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            parser.parse(input);
            input = sc.nextLine();
        }
        ui.Bye();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
