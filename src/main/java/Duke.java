import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    Duke(String filepath) {
        this.storage = new Storage(filepath);
        this.tasks = new TaskList(storage.load());
        this.ui = new Ui(tasks, storage);
    }

    /**
     * Runs the Duke bot to process input from user and output the response.
     */
    public void run() throws DukeException {
        ui.getInput();
    }

    public String getResponse(String input) {
        ByteArrayOutputStream formattedOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(formattedOutput));

        try {
            Command command = Parser.getCommand(input);
            command.execute(tasks);
            storage.saveToFile(tasks.getTasksInfo());
        } catch (DukeException exception) {
            System.out.println(exception.getMessage());
        }
        String reply = formattedOutput.toString();
//        while (reply.contains(Ui.demarcation)) {
//            reply = reply.replace(Ui.demarcation, "");
//        }
        reply = reply.trim();
        System.setOut(System.out);
        return reply;
    }

    public static void main(String[] args) throws DukeException {
        new Duke("ip_data.txt").run();
    }
}
