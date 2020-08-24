import java.util.Scanner;

public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    Duke() {
        this.storage = new Storage();
        this.ui = new Ui();
        try {
            this.tasks = storage.loadFile();
        } catch (Exception e) {
            System.err.println(e);
            this.tasks = new TaskList();
        }
    }

    public void run() {
        this.ui.showWelcomeMessage();
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;

        while (sc.hasNextLine()) {
            String fullCommand = sc.nextLine();

            try {
                Command command = Parser.parseCommand(fullCommand);
                CommandResult commandResult = command.execute(this.tasks, this.storage);
                this.ui.showResponse(commandResult);
                if (command.isExit()) {
                    break;
                }
            } catch (DukeException e) {
                System.err.println(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}