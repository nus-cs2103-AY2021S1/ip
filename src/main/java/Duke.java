import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList list;
    private Ui ui;

    public Duke(String filePath) throws IOException, InvalidCommandException {
        ui = new Ui();
        storage = new Storage(filePath);
        list = storage.list;
    }

    public void run() {
        boolean flag = true;
        while (flag) {
            try {
                String input = ui.readInput();
                Input type = Parser.getType(input);
                flag = Parser.dealCommand(type, ui, storage, input, list);
            } catch (InvalidCommandException e) {
                ui.output(e.getMessage());
            }
        }
        ui.close();
    }

    public static void main(String[] args) {
        String path = "../data/tasks.txt";
        new Duke(path).run();
    }
}
