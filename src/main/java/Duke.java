import java.io.IOException;
import java.text.ParseException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException | ParseException e) {
            tasks = new TaskList();
        }
    }

    public void run() throws IOException {
        welcomeMsg();
        ui.start(tasks);
        storage.writeToFile(tasks);
    }

    public void welcomeMsg() {
        String welcomeMsg = "Hello! I'm Duke, some call me a parrot.\n"
                + "What can I do for you?";
        System.out.println(welcomeMsg);
    }

    public static void main(String[] args) {
        try {
            new Duke("./data/duke.txt").run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
