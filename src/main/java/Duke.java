import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke.
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    /**
     * Run the program to interact with users.
     * @throws IOException
     * @throws IncorrectInputException
     */
    public void run() throws IOException, IncorrectInputException {
        ui.greeting();
        ui.interact();
    }

    public static void main(String[] args) throws IOException, IncorrectInputException {
        new Duke("src/main/java/tasklist.txt").run();
    }
//    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//    }
}
