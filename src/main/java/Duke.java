public class Duke {
    /** Object dealing with loading/saving tasks. */
    private final Storage storage;

    /** Object containing the list of tasks. */
    private TaskList tasks;

    /** Object dealing with interactions with user. */
    private final Ui ui;

    /**
     * Constructs new Duke object.
     *
     * @param filePath Destination path of the duke.txt storage file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the Duke application with the basic logic.
     */
    public void run() {
        ui.opening();
        String command = ui.userInput();

        while (!command.equals("bye")) {
            ui.showLine();

            if (command.equals("list")) {
                tasks.printList();
            } else {
                String action = Parser.getAction(command);
                switch (action) {
                case "done":
                    tasks.markDone(command);
                    break;
                case "todo":
                    tasks.createToDo(command);
                    break;
                case "deadline":
                    tasks.createDeadline(command);
                    break;
                case "event":
                    tasks.createEvent(command);
                    break;
                case "delete":
                    tasks.delete(command);
                    break;
                case "find":
                    tasks.find(command);
                    break;
                default:
                    tasks.getDefaultError();
                }
                storage.save(tasks.getTasks());
            }
            ui.showLine();
            command = ui.userInput();
        }
        ui.closing();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
