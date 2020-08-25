import java.util.Scanner;


public class Duke {
    public static Storage storage;
    public static TaskList taskList;
    public static Ui ui;
    public static boolean running;

    public Duke(String filePath){
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (DukeException e) {
            this.taskList = new TaskList();
        }
    }

    public void run() {
        ui.welcome();
        Scanner scanner = new Scanner(System.in);
        this.running = true;
        while(running) {
            String userInput = scanner.nextLine();
            Parser.parse(userInput);
        }
        scanner.close();
        ui.goodbye();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
