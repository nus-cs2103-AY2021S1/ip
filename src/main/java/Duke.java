import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Duke Object.
     */
    public Duke() {
        ui = new Ui();
        String currentDir = System.getProperty("user.dir");
        String filePath = currentDir + File.separator + "data" + File.separator + "duke.txt";
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Creates a Duke Object.
     *
     * @param filePath The path to the txt file to store the data.
     */
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

    /**
     * Gets a reply from Duke chatbot program.
     *
     * @param input User's input into program
     * @return Response from Duke.
     */
    public String getResponse(String input) {
        ByteArrayOutputStream formattedOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(formattedOutput));

        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }

        String reply = formattedOutput.toString();

        reply = reply.trim();
        System.setOut(System.out);
        return reply;
    }

    /**
     * Gets Duke's Welcome Message.
     *
     * @return Duke's Welcome Message.
     */
    public String getWelcomeMessage() {
        return "Hello! I'm Duke! How can I help you?";
    }

    /**
     * Runs the Duke chatbot programme.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main method to start the Duke chatbot programme.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        String currentDir = System.getProperty("user.dir");
        String path = currentDir + File.separator + "data" + File.separator + "duke.txt";

        new Duke(path).run();
    }
}
