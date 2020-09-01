package duke;
import duke.task.TaskList;

public class Duke {
    private static final String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

    Duke() {
        // initializing plus greeting
        TaskList taskList = new TaskList();
        Storage.loadFromFile(taskList);
        Ui ui = new Ui();
        Parser.setTaskList(taskList);
    }
    /**
     * Generates a response to user input.
     * @param input User input.
     * @return Response string.
     */
    public String getResponse(String input) {
        try {
            return Parser.parseCommand(input);
        } catch (DukeException e) {
            return Ui.getErrorMessage(e.getMessage());
        }
    }

//    /**
//     * Executes the Duke program.
//     * @param args Args.
//     */
//    public static void main(String[] args) {
//        TaskList taskList = new TaskList();
//        Storage.loadFromFile(taskList);
//        Ui ui = new Ui();
//        System.out.print(ui.greet());
//        Parser.setTaskList(taskList);
//        boolean isClosed = false;
//        while (!isClosed) {
//            try {
//                String command = ui.readInput();
//                if (Parser.stopProgram(command)) {
//                    isClosed = true;
//                    Storage.writeToFile(taskList);
//                    System.out.print(ui.exit());
//                } else {
//                    System.out.print(Parser.parseCommand(command));
//                }
//            } catch (DukeException e) {
//                System.out.println(Ui.getErrorMessage(e.getMessage()));
//            }
//        }
//    }
}
