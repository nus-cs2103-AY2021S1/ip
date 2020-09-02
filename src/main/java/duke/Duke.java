package duke;

import java.io.IOException;

public class Duke {
    private final Ui ui;
    private final Parser parser;
    private boolean hasExited;
    private final String dest;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor with default directory of database.
     */
    public Duke() {
        dest = "data/duke.txt";
        ui = new Ui();
        parser = new Parser();
        try {
            storage = new Storage(dest);
            taskList = storage.loadFile();
        } catch (IOException e) {
            ui.printNicely(e.getMessage());
        }
    }

    public boolean hasExited() {
        return hasExited;
    }

    private void run() {
        Storage storage;
        TaskList taskList;
        try {
            storage = new Storage(dest);
            taskList = storage.loadFile();
        } catch (IOException e) {
            ui.printNicely(e.getMessage());
            return;
        }
        ui.greet();
        ui.listOut(taskList);
        while (!hasExited) {
            try {
                String input = ui.nextLine();
                switch (parser.parseInput(input)) {
                case ADD:
                    taskList.add(parser.parseInputTask(input));
                    ui.printNicely("Noted, I have added the following task:",
                            parser.parseInputTask(input).toString());
                    break;
                case DONE:
                    taskList.markDone(parser.parseDone(input));
                    ui.printNicely("Noted, I have marked the following task as done:",
                            taskList.get(parser.parseDone(input)).toString());
                    break;
                case DELETE:
                    ui.printNicely("Noted, I have deleted the following task:",
                            taskList.get(parser.parseDelete(input)).toString());
                    taskList.delete(parser.parseDelete(input));
                    ui.listOut(taskList);
                    break;
                case FIND:
                    TaskList newList = taskList.find(parser.parseFind(input));
                    ui.listOut(String.format("I have found the following %d task(s)", newList.size()),
                            newList);
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
                storage.writeFile(taskList);
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
                taskList.add(parser.parseInputTask(input));
                response = new StringBuilder(ui.generateResponse("Noted, I have added the following task:",
                        parser.parseInputTask(input).toString()));
                break;
            case DONE:
                taskList.markDone(parser.parseDone(input));
                response = new StringBuilder(ui.generateResponse("Noted, I have marked the following task as done:",
                        taskList.get(parser.parseDone(input)).toString()));
                break;
            case DELETE:
                response = new StringBuilder(ui.generateResponse("Noted, I have deleted the following task:",
                        taskList.get(parser.parseDelete(input)).toString()));
                taskList.delete(parser.parseDelete(input));
                response.append(ui.generateTaskListString(taskList));
                break;
            case FIND:
                TaskList newList = taskList.find(parser.parseFind(input));
                response = new StringBuilder(ui.generateTaskListString(
                        String.format("I have found the following %d task(s)", newList.size()),
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
            storage.writeFile(taskList);
        } catch (Exception e) {
            response = new StringBuilder(ui.generateResponse(e.getMessage()));
        }
        return response.toString();
    }

}
