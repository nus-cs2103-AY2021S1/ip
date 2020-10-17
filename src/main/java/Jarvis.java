import java.io.IOException;

public class Jarvis {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Jarvis constructor takes in file path to setup duke
     * @param filePath path of storage file
     * @throws IOException exception throw when invalid file provided
     */
    public Jarvis(String filePath) throws IOException {
        assert filePath.contains(".txt"); //enure that correct file type is passed
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Starts the Jarvis instance
     */
    public void run() {
        ui.welcome();
        boolean exit = false;
        while (!exit) {
            try {
                String command = ui.readCommand();
                ui.showLine();
                Command c = Command.parse(command, tasks, ui, storage);
                c.execute();
                exit = c.isExit();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();;
            }
        }
        ui.goodbye();
    }

    public static void main(String[] args) throws IOException {
        new Jarvis("data/tasks.txt").run();
    }

    /**
     * getResponse takes the user command as a String and returns the response from Jarvis
     * @param input User command String
     * @return Jarvis command
     */
    Command getResponse(String input) {
        String command = input;
        try {
            Command c = Command.parse(command, tasks, ui, storage);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            Command c = new ExceptionCommand(e, tasks, ui, storage);
            return c;
        }
    }
}
