public class Parser {

//    private TaskList taskList;
//    private static final String end = "bye";

//    public Parser(TaskList taskList) {
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
//            return Command.valueOf(firstWord);
//        } catch (IllegalArgumentException illegalArg) {
//            throw new UnrecognizedTaskException();
//        }
    }

//    public void parse(String fullCommand) {
//
//        while (!fullCommand.equalsIgnoreCase(end)) {
//
//            try {
//
//                Command command = getCommand(fullCommand.trim());
//
//                if (command == Command.list) {
//
//                    taskList.processList(fullCommand);
//
//                } else if (command == Command.done) {
//                    taskList.processDone(fullCommand);
//                } else if (command == Command.delete) {
//                    taskList.processDelete(fullCommand);
//                } else {
//                    taskList.addTask(command, fullCommand.trim());
//                }
//
//            } catch (DukeException error) {
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
