package duke;

import duke.command.*;
import duke.exception.NoIndexException;
import duke.exception.UnrecognizedTaskException;

public class Parser {

//    private duke.TaskList taskList;
//    private static final String end = "bye";

//    public duke.Parser(duke.TaskList taskList) {
//        this.taskList = taskList;
//    }

    public static Command parse(String fullCommand) throws UnrecognizedTaskException, NoIndexException {

        fullCommand = fullCommand.trim();

        String firstWord = fullCommand.contains(" ")
                ? fullCommand.substring(0, fullCommand.indexOf(" ")).toLowerCase()
                : fullCommand.toLowerCase();

        switch (firstWord) {
            case "todo":
                return new ToDoCommand(fullCommand);
                // Fallthrough
            case "event":
                return new EventCommand(fullCommand);
                // Fallthrough
            case "deadline":
                return new DeadlineCommand(fullCommand);
                // Fallthrough
            case "list":
                return new ListCommand(fullCommand);
                // Fallthrough
            case "done":

                if (fullCommand.equalsIgnoreCase("done")) {
                    throw new NoIndexException("done");
                } else {

                    try {
                        int taskNo = Integer.parseInt(fullCommand.substring(5)) - 1;
                        return new DoneCommand(taskNo);

                    } catch (NumberFormatException numError) {
                        throw new NoIndexException("done");
                    }
                }
                // Fallthrough
            case "delete":
                if (fullCommand.equalsIgnoreCase("delete")) {
                    throw new NoIndexException("delete");
                } else {

                    try {
                        int taskNo = Integer.parseInt(fullCommand.substring(7)) - 1;
                        return new DeleteCommand(taskNo);

                    } catch (NumberFormatException numError) {
                        throw new NoIndexException("delete");
                    }

                }
                // Fallthrough
            case "bye":
                return new ExitCommand();
                // Fallthrough
            default:
                throw new UnrecognizedTaskException();
                // Fallthrough
        }

//        try {
//            return duke.command.Command.valueOf(firstWord);
//        } catch (IllegalArgumentException illegalArg) {
//            throw new duke.exception.UnrecognizedTaskException();
//        }
    }

//    public void parse(String fullCommand) {
//
//        while (!fullCommand.equalsIgnoreCase(end)) {
//
//            try {
//
//                duke.command.Command command = getCommand(fullCommand.trim());
//
//                if (command == duke.command.Command.list) {
//
//                    taskList.processList(fullCommand);
//
//                } else if (command == duke.command.Command.done) {
//                    taskList.processDone(fullCommand);
//                } else if (command == duke.command.Command.delete) {
//                    taskList.processDelete(fullCommand);
//                } else {
//                    taskList.addTask(command, fullCommand.trim());
//                }
//
//            } catch (duke.exception.DukeException error) {
//
//                System.out.println(error.getMessage());
//
//            } catch (IndexOutOfBoundsException indexError) {
//
//                System.out.println("Invalid index.");
//                taskList.printListSize();
//            }
//        }
//    }
}
