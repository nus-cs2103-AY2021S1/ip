public class Duke {
    protected Storage storage;
    protected TaskList taskList;
    protected Ui ui;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.taskList = new TaskList(storage.tasks);
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }

    public void run() {
        // displayed once Duke is run, without input from user
        ui.greetings();
        Parser parser = new Parser();
        boolean isExit = false;

        while (!isExit) {
            try {
                Command command = parser.parse(ui.readCommand(), taskList);
                ui.showLine();
                command.execute();
                isExit = command.isExit;
                if (!isExit) {
                    ui.showLine();
                }
            } catch (DukeException e) {
                e.printStackTrace();
            }
        }

        // Save tasks before terminating program
        storage.saveTasks(taskList.tasks);

        ui.goodbye();

    }

}