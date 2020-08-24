package duke;

public class Duke {
    private TaskList list;
    private Storage storage;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        list = storage.loadFile();
    }

    private void run() {
        Ui.printGreeting();

        String input = ui.getInput();
        Command command = Parser.parse(input);
        while (command.getTaskType() != TaskType.BYE) {
            command.execute(list);

            input = ui.getInput();
            command = Parser.parse(input);
        }

        storage.saveFile(list);
        Ui.printGoodbye();
    }

    public static void main(String[] args) {
        new Duke(".//data//duke.data").run();
    }
}
