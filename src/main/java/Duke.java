public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs new Duke object.
     *
     * @param filePath Destination path of duke.txt file.
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
                    tasks.done(command);
                    break;
                case "todo":
                    tasks.newToDo(command);
                    break;
                case "deadline":
                    tasks.newDeadline(command);
                    break;
                case "event":
                    tasks.newEvent(command);
                    break;
                case "delete":
                    tasks.delete(command);
                    break;
                default:
                    tasks.defaultError();
                }
                storage.save(tasks.getTasks());
            }
            ui.showLine();
            command = ui.userInput();
        }
        ui.closing();
    }

    /**
     * Starts the Duke application.
     *
     * @param args Typical String[] argument.
     */
    public static void main(String[] args) {
        new Duke(Storage.getFilePath()).run();
    }
}