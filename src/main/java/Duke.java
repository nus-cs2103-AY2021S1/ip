import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke {

    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    FriendList friendList;

    /**
     * Constructor for Duke.
     * @param filePath
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.friendList = new FriendList();
        try {
            taskList = new TaskList(storage.loadData());
        } catch (Exception | IncorrectInputException e) {
            System.out.println("tasklist not loaded");
        }
    }

    /**
     * Constructor for Duke.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("tasklist.txt");
        this.friendList = new FriendList();
        try {
            taskList = new TaskList(storage.loadData());
        } catch (Exception | IncorrectInputException e) {
            e.printStackTrace();
        }
    }

    /**
     * Run the program to interact with users.
     * @throws IOException
     * @throws IncorrectInputException
     */
    public void run() throws IncorrectInputException {
        ui.greeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
//                System.out.println(e.getMessage());
                e.printStackTrace();
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Return a string of response to the user input.
     * @param input
     * @return a response message.
     * @throws IOException
     * @throws IncorrectInputException
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(this.taskList, this.ui, this.storage);
        } catch (IncorrectInputException | EmptyInputException | IOException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) throws IOException, IncorrectInputException {
        new Duke().run();
    }
}
