package duke;

public class Duke {
    private TaskList tasks;

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

    // Let's find some way to integrate this
    /*
    public static void main(String[] args) {
        String logo =
            "   __ _____   __  ___  ___  ___  ___\n"
            + "  / // / _ | / / / _ \\/ _ \\/ _ \\/ _ \\\n"
            + " / _  / __ |/ /__\\_, / // / // / // /\n"
            + "/_//_/_/ |_/____/___/\\___/\\___/\\___/\n";

        // Intro message
        System.out.println(logo);
        FormatPrinter.print(
            "Hello! I'm Hal9000\nWhat can I do for you?\n"
        );

        Duke hal9000 = new Duke();
        File prevTasks = FileOpener.openFile("prevTasks.txt");
        TaskLoader.loadTasks(prevTasks, hal9000.getTasks());
        hal9000.op();
        TaskStorage.saveTask(prevTasks, hal9000.getTasks());
    } */

}
