import java.io.IOException;

public class Duke {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Duke() {
        storage = new Storage();

        try {
            // storage.clear();
            taskList = new TaskList(storage.readData());
            ui = new Ui(taskList, storage);

        } catch (IOException e) {
            taskList = new TaskList();
            e.printStackTrace();
        }
    }

    private void run() throws IOException {
        ui.startProgram();
    }

    public static void main(String[] args) {
        try {
            new Duke().run();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
