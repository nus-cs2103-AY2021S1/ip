public class Parser {
    String command;
    String[] inputArr;

    Parser(String command) {
        this.command = command;
        this.inputArr = command.split(" ");
    }

    /**
     * Returns the type of the command keyed in by the user.
     * @return The type of command. Eg: done, delete, list, etc.
     */

    public String getCommandType() {
        return inputArr[0];
    }

    /**
     * Returns an integer of the task item the user wishes to delete or mark as done.
     * @return The number of the task item to be modified.
     */

    public int getTaskToModify() {
        return Integer.parseInt(inputArr[1]);
    }

    public String getWord() {
        return inputArr[1];
    }

    /**
     * Returns an array of strings that make up the details of one task. Eg: ["T", "Read Book"].
     * @return Details of a single task.
     * @throws DukeException If the "todo" command keyed by user does not contain a todo task string.
     */

    public String[] getNewTask() throws DukeException {
        String[] newTaskDetails;
        String commandType = getCommandType();

        switch (commandType) {
        case ("todo"):
            // Only has the word todo
            if (inputArr.length == 1) throw new EmptyTodoException();
            newTaskDetails = new String[]{"T", inputArr[1].trim()};
            break;
        case ("deadline"):
            String deadlineContent = command.substring(9);
            String[] deadlineArr = deadlineContent.split("/");
            newTaskDetails = new String[]{"D", deadlineArr[0].trim(), deadlineArr[1].substring(3).trim()};
            break;
        case("event"):
            String eventContent = command.substring(6);
            String[] eventArr = eventContent.split("/");
            newTaskDetails = new String[]{"E", eventArr[0].trim(), eventArr[1].substring(3).trim()};
            break;
        default:
            throw new DukeException();
        }
        return newTaskDetails;
    }
}
