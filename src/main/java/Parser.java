import java.io.IOException;
import java.util.Scanner;

public class Parser {
    Scanner scanner;

    public Task processAddTaskInput(String userInput, TaskList taskList, Ui ui) throws ToDoException, deadlineException, eventException {
        String taskType = userInput.split(" ")[0];
        Task thisTask = null;
        switch (taskType) {
            case "todo":
                if (userInput.equals("todo")) throw new ToDoException();
                thisTask = new Task(userInput.replace("todo ", ""));
                ui.showToDoMessage(thisTask, taskList);
                return thisTask;
            case "deadline":
                if (userInput.equals("deadline")) {
                    throw new deadlineException();
                }
                String[] StringArr = userInput.split(" /by");
                thisTask = new Deadline(StringArr[0].replace("deadline ", ""), StringArr[1]);
                ui.showDeadlineMessage(thisTask, taskList);
                return thisTask;
            case "event":
                if (userInput.equals("event")) throw new eventException();
                StringArr = userInput.split(" /at");
                thisTask = new Event(StringArr[0].replace("event ", ""), StringArr[1]);
                ui.showEventMessage(thisTask, taskList);
        }
        return thisTask;
    }

    public void processOtherActionInput(String userInput, TaskList taskList, Ui ui) throws IOException, DukeException {
        String actionType = userInput.split(" ")[0];
        switch (actionType) {
            case "done":
                System.out.println("ok sure good job i guess\n");
                int doneIndex = Integer.parseInt(userInput.substring(5));
                ui.showDoneMessage(taskList.getList().get(doneIndex));
                taskList.taskCompleted(doneIndex);
                System.out.println("\n");
                break;
            case "delete":
                if (userInput.equals("delete")) throw new deleteException();
                int indexDeleted = Integer.parseInt(userInput.replace("delete ", ""));
                ui.showDeletedMessage(taskList.getList().get(indexDeleted - 1), taskList);
                taskList.deleteTask(indexDeleted);
                break;
            default :
                throw new DukeException();
        }
    }
}
