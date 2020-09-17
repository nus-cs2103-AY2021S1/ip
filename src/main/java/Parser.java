public class Parser {

    /**
     * Considers the command input and performs actions accordingly.
     * @param inputPrefix The command in string form, decides the instruction to be carried out.
     * @param inputSuffix The parameters of the command in string form, may contain multiple parameters.
     * @param taskList The taskList that might perform instructions based on what the command is.
     * @param mainWindow The mainWindow that will be displaying a response, or that will close, based on what the
     *                   command is.
     */
    public static void handleInput(String inputPrefix, String inputSuffix, TaskList taskList, MainWindow mainWindow)
            throws Exception {
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
        case "bye":
            mainWindow.exit();
            break;
        default:
            throw new DukeNoSuchInputException();
        }
        Storage.createNewSave(taskList.toData());
    }

}
