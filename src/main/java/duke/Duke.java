package duke;

import java.io.IOException;

public class Duke {
    private static final String DEFAULT_STORAGE_LOCATION = "data/duke.txt";
    private final Ui ui;
    private final Parser parser;
    private boolean hasExited;
    private final String dest;
    private Storage storage;
    private TaskList taskList;

    private enum ResponsePrefix {
        ADD("Noted, I have added the following task(s):"),
        DONE("Noted, I have marked the following task(s) as done:"),
        DELETE("Noted, I have deleted the following task(s):"),
        FIND("Noted, I have found the following %d task(s)");

        private String string;
        ResponsePrefix(String string) {
            this.string = string;
        }
        @Override
        public String toString() {
            return string;
        }
    }

    /**
     * Constructor with default directory of database.
     */
    public Duke() {
        dest = DEFAULT_STORAGE_LOCATION;
        ui = new Ui();
        parser = new Parser();
        try {
            storage = new Storage(dest);
            taskList = storage.loadTaskList();
        } catch (IOException e) {
            ui.printNicely(e.getMessage());
        }
    }

    public boolean hasExited() {
        return hasExited;
    }

    private void run() {
        assert(taskList != null);
        assert(storage != null);
        ui.greet();
        ui.listOut(taskList);
        while (!hasExited) {
            try {
                String input = ui.nextLine();
                switch (parser.parseInput(input)) {
                case ADD:
                    taskList.add(parser.parseAddedTask(input));
                    ui.printNicely(ResponsePrefix.ADD.toString(), parser.parseAddedTask(input).toString());
                    break;
                case DONE:
                    taskList.markDone(parser.parseDoneIndex(input));
                    ui.printNicely(ResponsePrefix.DONE.toString(),
                            taskList.get(parser.parseDoneIndex(input)).toString());
                    break;
                case DELETE:
                    ui.printNicely(ResponsePrefix.DELETE.toString(),
                            taskList.get(parser.parseDeleteIndex(input)).toString());
                    taskList.delete(parser.parseDeleteIndex(input));
                    ui.listOut(taskList);
                    break;
                case FIND:
                    TaskList newList = taskList.find(parser.parseFindKeyword(input));
                    ui.listOut(String.format(ResponsePrefix.FIND.toString(), newList.size()), newList);
                    break;
                case LIST:
                    ui.listOut(taskList);
                    break;
                case BYE:
                    ui.bye();
                    hasExited = true;
                    return;
                default:
                    throw new IOException("Cannot be understood.");
                }
                storage.saveTaskList(taskList);
            } catch (Exception e) {
                ui.printNicely(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Process the input and returns a response in the form of a string.
     *
     * @param input User input
     * @return Duke's response.
     */
    public String processAndGetResponse(String input) {
        StringBuilder response;
        try {
            input = input.trim();
            switch (parser.parseInput(input)) {
            case ADD:
                taskList.add(parser.parseAddedTask(input));
                response = new StringBuilder(ui.generateResponse(ResponsePrefix.ADD.toString(),
                        parser.parseAddedTask(input).toString()));
                break;
            case DONE:
                taskList.markDone(parser.parseDoneIndex(input));
                response = new StringBuilder(ui.generateResponse(ResponsePrefix.DONE.toString(),
                        taskList.get(parser.parseDoneIndex(input)).toString()));
                break;
            case DELETE:
                response = new StringBuilder(ui.generateResponse(ResponsePrefix.DELETE.toString(),
                        taskList.get(parser.parseDeleteIndex(input)).toString()));
                taskList.delete(parser.parseDeleteIndex(input));
                response.append(ui.generateTaskListString(taskList));
                break;
            case FIND:
                TaskList newList = taskList.find(parser.parseFindKeyword(input));
                response = new StringBuilder(ui.generateTaskListString(
                        String.format(ResponsePrefix.FIND.toString(), newList.size()),
                        newList));
                break;
            case LIST:
                response = new StringBuilder(ui.generateTaskListString(taskList));
                break;
            case BYE:
                hasExited = true;
                response = new StringBuilder(ui.generateResponse("Goodbye."));
                break;
            default:
                throw new IOException("Cannot be understood.");
            }
            storage.saveTaskList(taskList);
        } catch (Exception e) {
            response = new StringBuilder(ui.generateResponse(e.getMessage()));
        }
        return response.toString();
    }

}
