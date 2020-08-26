public class Parser {

    public static void handleInput(String inputPrefix, String inputSuffix, TaskList taskList) throws DukeException {
        switch (inputPrefix) {
            case "list":
                taskList.printList();
                break;
            case "done":
                taskList.completeTask(Integer.parseInt(inputSuffix) - 1);
                break;
            case "todo":
                taskList.addTask(new Todo(inputSuffix));
                break;
            case "deadline":
                String[] deadlineParts = inputSuffix.split("/by",2);
                String deadlineName = deadlineParts[0];
                if (deadlineParts.length == 1) {
                    throw new DukeEmptyDescException(TaskType.EVENT);
                } else {
                    String by = deadlineParts[1];
                    if (Ui.isBlankString(by)) {
                        throw new DukeEmptyDescException(TaskType.EVENT);
                    } else {
                        taskList.addTask(new Deadline(deadlineName, by));
                        break;
                    }
                }
            case "event":
                String[] eventParts = inputSuffix.split("/at",2);
                String eventName = eventParts[0];
                if (eventParts.length == 1) {
                    throw new DukeEmptyDescException(TaskType.EVENT);
                } else {
                    String at = eventParts[1];
                    if (Ui.isBlankString(at)) {
                        throw new DukeEmptyDescException(TaskType.EVENT);
                    } else {
                        taskList.addTask(new Event(eventName, at));
                        break;
                    }
                }
            case "delete":
                taskList.deleteTask(Integer.parseInt(inputSuffix) - 1);
                break;
            default:
                throw new DukeNoSuchInputException();
        }
    }

}
