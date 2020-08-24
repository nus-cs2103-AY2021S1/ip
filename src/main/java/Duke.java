import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    enum Command {
        EXIT("bye"),
        LIST("list"),
        DONE("done"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DELETE("delete");

        private final String keyword;

        Command(String keyword) {
            this.keyword = keyword;
        }

        public static Command findCommand(String keyword) throws DukeException {
            for (Command c : values()) {
                if (keyword.equals(c.keyword)) {
                    return c;
                }
            }
            throw new DukeException("Wakarimasen~");
        }
    }

    private void run() {
        ui.greet();
        Boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            try {
                String[] inputs = fullCommand.split(" ", 2);
                // By spec, inputs is guaranteed to have at least one element.
                String commandString = inputs[0];
                Command command = Command.findCommand(commandString);
                switch (command) {
                case EXIT:
                    storage.save(this.taskList);
                    isExit = true;
                    ui.exit();
                    break;
                case LIST:
                    Ui.formattedPrint(Ui.prependIndent(taskList.listItems(), 1));
                    break;
                case DONE:
                    try {
                        int index = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
                        Ui.formattedPrint(Ui.prependIndent(taskList.markAsDone(index), 1));
                        break;
                    } catch (NumberFormatException e) {
                        throw new DukeException("This isn't harry potter, please use only integers.");
                    }
                case DEADLINE:
                    try {
                        Ui.formattedPrint(Ui.prependIndent(taskList.add(Deadline.createTask(inputs[1])), 1));
                        break;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("What are you rushing for? To wait?");
                    }
                case TODO:
                    try {
                        ToDo newToDo = new ToDo(inputs[1]);
                        Ui.formattedPrint(Ui.prependIndent(taskList.add(newToDo), 1));
                        break;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("I know your life is empty but your todo can't be empty.");
                    }
                case EVENT:
                    try {
                        Ui.formattedPrint(Ui.prependIndent(taskList.add(Event.createTask(inputs[1])), 1));
                        break;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("Are you going to attend a nameless event?");
                    }
                case DELETE:
                    try {
                        int index = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
                        Ui.formattedPrint(Ui.prependIndent(taskList.deleteTask(index), 1));
                        break;
                    } catch (NumberFormatException e) {
                        throw new DukeException("This isn't harry potter, please use only integers.");
                    }
                }
            } catch (DukeException e) {
                Ui.formattedPrint(Ui.prependIndent(e.getMessage(), 1));
            }
        }
    }

    public static void main(String[] args) {
        new Duke(Storage.FILE_PATH).run();
    }
}
