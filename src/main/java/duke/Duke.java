package duke;

/**
 * Duke main class
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Duke constructor
     *
     * @param
     */
    public Duke() {
        storage = new Storage("src/main/data.txt");
        tasks = new TaskList(storage.getTasks());
        ui = new Ui();
    }


    /**
     * Runs the Duke program
     */
    public void run() {
        ui.showInfo();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public String getResponse(String input) {
        String op;
        try {
            Command c = Parser.parse(input);
            op = c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            op = (e.getMessage());
        }
        return op;
    }

    /**
     * Show welcome message to users
     *
     * @return a welcome message when people start the GUI
     */
    public String welcomeMessage() {
        return "Hello, I'm DukeQ:) \n" + "I am very happy to serve you! \n"
                + "The valid command formats are as follows and the case is non-sensitive. \n"
                + "\n"
                + "todo then your instructions e.g. todo read book\n"
                + "deadline then your instructions e.g. deadline return book /by 2019-10-15\n"
                + "type 'event' followed by the description,\n"
                + "if '/at', the time should be like 2020-09-01\n"
                + "if '/by', the due date should be like 2020-09-01 \n"
                + "done followed by the task number to marked it as done \n"
                + "type list to see the task lists\n"
                + "type find followed by keywords to search for tasks \n"
                + "type help to get a tutorial \n"
                + "type bye to exit DukeQ, hope you have a good day! \n";
    }

}
