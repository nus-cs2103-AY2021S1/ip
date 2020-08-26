public class Parser {

    /**
     * Considers the command input and performs actions accordingly.
     */
    public static void handleInput(String inputPrefix, String inputSuffix, TaskList taskList) throws DukeException {
        switch (inputPrefix) {
            case "list":
                taskList.printList();
                break;
            case "done":
                taskList.completeTask(Integer.parseInt(inputSuffix) - 1);
                break;
            case "todo":
                taskList.addTask(new Todo(inputSuffix), true);
                break;
            case "deadline":
                Deadline.newDeadline(inputSuffix, taskList, false, true);
                break;
            case "event":
                Event.newEvent(inputSuffix, taskList, false, true);
                break;
            case "delete":
                taskList.deleteTask(Integer.parseInt(inputSuffix) - 1);
                break;
            case "find":
                taskList.lookFor(inputSuffix);
                break;
            default:
                throw new DukeNoSuchInputException();
        }
        Storage.createNewSave(taskList.toData());
    }

}
