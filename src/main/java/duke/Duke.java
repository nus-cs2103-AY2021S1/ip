package duke;
import duke.task.TaskList;


public class Duke {
    private static final String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Storage.loadFromFile(taskList);
        Ui ui = new Ui();
        ui.greet();
        Parser.setTaskList(taskList);
        boolean isClosed = false;
        while (!isClosed) {
            try {
                String command = ui.readInput();
                if (Parser.stopProgram(command)) {
                    isClosed = true;
                    Storage.writeToFile(taskList);
                    ui.exit();
                } else {
                    ui.printHorizontalLine();
                    Parser.parseCommand(command);
                    ui.printHorizontalLine();
                }
            } catch (DukeException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
    }
}
