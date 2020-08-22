import java.util.Scanner;

public class Duke {
      private Storage storage;
      private TaskList tasks;
      private Ui ui;
      private Parser parser;

    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        parser = new Parser();
    }

    public void run() {
        ui.welcome();
        tasks = new TaskList(storage.load());
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.equals("bye")) {
            ui.line();
            parser.command(str, tasks, storage);
            ui.line();
            str = sc.nextLine();
        }
        sc.close();
        ui.exit();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
