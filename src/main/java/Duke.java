import java.io.IOException;

public class Duke {
    enum Command {
        ADD,
        DONE,
        SAVE,
        DELETE,
        LIST,
        ERROR
    }

    private static Storage storage;
    private static Ui ui;
    private static TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {

        // Introduction
        ui.showWelcome();

        String nextLine = ui.readCommand();

        while (!nextLine.equals("bye")) {
            Command c = Parser.parse(nextLine);

            switch(c) {
                case ADD:
                    try {
                        Task curr = tasks.addItem(nextLine);
                        ui.addedItem(curr, tasks.getListSize());
                    } catch (DukeException ex1) {
                        ui.showError(ex1.getMessage());
                    }
                    nextLine = ui.readCommand();
                    break;
                case DONE:
                    try {
                        Task curr = tasks.doneItem(nextLine);
                        ui.doneItem(curr);
                    } catch (DukeException ex1){
                        ui.showError(ex1.getMessage());
                    }
                    nextLine = ui.readCommand();
                    break;
                case DELETE:
                    try {
                        Task curr = tasks.deleteItem(nextLine);
                        ui.deleteItem(curr);
                    }  catch(DukeException ex1) {
                        ui.showError(ex1.getMessage());
                    }
                    nextLine = ui.readCommand();
                    break;
                case LIST:
                    ui.returnList(tasks.getList());
                    nextLine = ui.readCommand();
                    break;
                case SAVE:
                    try {
                        storage.overwriteData(tasks.getList());
                        ui.save();
                    } catch (IOException ex1) {
                        ui.showError(ex1.getMessage());
                    }
                    nextLine = ui.readCommand();
                    break;
                case ERROR:
                    ui.defaultError();
                    nextLine = ui.readCommand();
                    break;
            }
        }
        // Ending the bot
        ui.bye();
    }

    public static void main(String[] args) {
        new Duke("data/Duke.txt").run();
    }
}

