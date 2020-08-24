public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    Duke(String filePath) {
        taskList = new TaskList();
        storage = new Storage(filePath, taskList);
        parser = new Parser(taskList, storage);
        ui = new Ui(parser);
        try {
            storage.getTodoList();
        } catch (DukeException e) {
            ui.showLoadingError();
        }
    }

    public void run() {
        ui.run();
    }

    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        Duke duke = new Duke("src/data/duke.txt");
        duke.run();
    }
}
