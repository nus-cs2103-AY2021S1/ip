package duke;

/**
 * Entry point of the program.
 */
public class Duke {
    private Storage storage;
    private TaskList<Task> tasks;
    private Ui ui;
    private boolean isFirstInteraction;

    /**
     * Instantiates a Duke object.
     * @param filePath the directory to store data
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        isFirstInteraction = true;
        try {
            tasks = new TaskList<>(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
        }
    }

    public Duke() {
        this("data/duke.txt");
    }

//    /**
//     * Runs the entire program with the main logic.
//     */
//    public void run() {
//        Scanner sc = new Scanner(System.in);
//        ui.welcome();
//        boolean exitProgram = false;
//        while (!exitProgram && sc.hasNextLine()) {
//            try {
//                String fullCommand = sc.nextLine(); // no need
//                Command c = Parser.parse(fullCommand);
//                c.execute(tasks, ui, storage);
//                storage.writeToFile(tasks);
//                exitProgram = c.isExitProgram();
//            } catch (DukeException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//        sc.close();
//    }

    public String getResponse(String userInput) {
        try {
            ui.clearMessage();
            Command c = Parser.parse(userInput);
            c.execute(tasks, ui, storage);
            storage.writeToFile(tasks);
            return ui.toString();
        } catch (DukeException e) {
            return ui.showError(e);
        }
    }

//    /**
//     * Starts the Duke program.
//     * @param args user input that's not needed here
//     */
//    public static void main(String[] args) {
//        new Duke("data/duke.txt").run();
//    }

}
