package duke;

import java.io.File;

public class Duke {
    private TaskList tasks;
    public static final String FILE_PATH = "prevTasks.txt";

    public Duke() {
        this.tasks = new TaskList();
    }

    public TaskList getTasks() {
        return tasks;
    }

    // start() method moved to class MainGUI
    // And also handleUserInput()
    // op() turned into this
    public String getResponse(String input) {
        String outputMsg;
        if (Parser.isBye(input)) {
            outputMsg = "Bye. Hope to see you again soon!\n";
        } else if (Parser.isList(input)) {
            outputMsg = getTasks().summarize();
        } else if (Parser.isDone(input)) {
            outputMsg = getTasks().markDone(Parser.getIndex(input));
        } else if (Parser.isDelete(input)) {
            outputMsg = getTasks().deleteTask(Parser.getIndex(input));
        } else if (Parser.isFind(input)) {
            outputMsg = getTasks().findTasksWith(Parser.getKeyword(input));
        } else {
            try {
                Task taskInput;
                taskInput = Parser.parseTask(input); // catch duke exception from getTask(input)
                outputMsg = getTasks().addTask(taskInput);
            } catch (Exception e) {
                outputMsg = e.getMessage();
            }
        }
        return outputMsg;
    }

    public String getWelcomeMessage() {
        String logo =
                "   __ _____   __  ___  ___  ___  ___\n"
                        + "  / // / _ | / / / _ \\/ _ \\/ _ \\/ _ \\\n"
                        + " / _  / __ |/ /__\\_, / // / // / // /\n"
                        + "/_//_/_/ |_/____/___/\\___/\\___/\\___/\n"
                        + "Hello! I'm Hal9000\nWhat can I do for you?\n";
        // Intro message
        return Formatter.formatResponse(logo);
    }

    public void loadPrevTasks() {
        File prevTasks = FileOpener.openFile(FILE_PATH);
        TaskLoader.loadTasks(prevTasks, this.getTasks());
    }

    public void saveCurrentTasks() {
        File prevTasks = FileOpener.openFile(FILE_PATH);
        TaskStorage.saveTask(prevTasks, this.getTasks());
    }

}
