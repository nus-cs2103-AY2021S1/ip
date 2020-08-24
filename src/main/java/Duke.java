public class Duke {
    private static TaskList list;
    private static Storage storage;

    public static void main(String[] args) {
        Ui.printGreeting();

        storage = new Storage(".//data//duke.data");
        list = storage.loadFile();

        Ui ui = new Ui();

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
}
