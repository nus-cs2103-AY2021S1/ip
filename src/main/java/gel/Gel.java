package gel;

import java.io.IOException;
import java.util.Scanner;

/**
 * gel.Gel is an task planner. A <code>gel.Gel</code> object is a bot which
 * enables user to plan their tasks.
 */
public class Gel {

    private static final String STORAGE_LOCATION = "data/tasks.txt";
    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;
    private final Parser parser;
    private final Scanner sc = new Scanner(System.in);

    /**
     * Constructor for class gel.Gel.
     *
     * @param filePath takes in the filepath of the storage file.
     */
    public Gel (String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            storage.checkFileExistence();
            taskList = storage.load(ui);
        } catch (IOException e) {
            System.out.println(ui.loadingError());
            taskList = new TaskList(ui);
        }
        this.parser = new Parser(storage, ui, taskList);
    }

    /**
     * Starts the task planner <code>gel.Gel</code> and interact with user.
     */
    private void run() {
        System.out.println(ui.showWelcomeMessage());
        System.out.println();
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            System.out.println();
            System.out.println(getResponse(input));
            System.out.println();
            if (input.equals("bye")) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Gel(STORAGE_LOCATION).run();
    }

    /**
     * Return response fo Gel bot with given input.
     *
     * @param input given by user.
     * @return data acquired by user.
     */
    public String getResponse(String input) {
        try {
            return parser.parseUserInput(input);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String getWelcomeMsg() {
        return ui.showWelcomeMessage();
    }
}
