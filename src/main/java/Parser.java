public class Parser {
    String[] inputArr;

    Parser(String command) {
        this.inputArr = command.split(" ");
    }

    public String getCommandType() {
        return inputArr[0];
    }

    public int getTaskToModify() {
        return Integer.parseInt(inputArr[1]);
    }

    public String[] getNewTask() throws DukeException {
        String[] newTaskDetails;
        String command = getCommandType();

        switch (command) {
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
