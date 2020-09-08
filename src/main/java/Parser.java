import java.time.format.DateTimeParseException;

/**
 * Parser class deals with making sense of user input.
 */
public class Parser {
    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;
    
    Parser(Ui ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }
    
    /**
     * Takes in user input and prints the application's corresponding output.
     * @param input the user input as a String.
     */
    public String parseInput(String input) {
        String result;
        try {
            if (input.equals("bye")) { //exit condition
                result = Ui.showGoodbyeMessage();
            } else if (input.equals("list")) {
                result = ui.showCurrentTasks(taskList);
            } else if (input.startsWith("find")) {
                String word = input.split("find ")[1];
                result = ui.findTask(word, taskList);
            } else if (input.startsWith("done")) {
                String[] number = input.split("done ");
                int index = Integer.parseInt(number[1]);
                taskList.markDone(index);
                result = ui.showTasksAfterMarkDone(index, taskList);
            } else if (input.startsWith("deadline")) {
                //whatever is after deadline
                String[] deadlineInput = input.split("deadline ");
                if(deadlineInput.length < 2) {
                    throw new DukeIllegalArgumentException("", DukeException.DukeExceptionType.DEADLINE);
                }
                if((deadlineInput[1].split(" /by ")).length < 2) {
                    throw new DukeIllegalArgumentException("", DukeException.DukeExceptionType.DEADLINE);
                }
                String deadlineName = (deadlineInput[1].split(" /by "))[0];
                String deadlineTime = (deadlineInput[1].split(" /by "))[1];
                Deadline newDeadline = new Deadline(deadlineName, deadlineTime);
                taskList.addTask(newDeadline);
                result = ui.showAddTaskMessage(newDeadline, taskList);
                
            } else if (input.startsWith("todo")) {
                String[] todoInput = input.split("todo ");
                if(todoInput.length < 2) {
                    throw new DukeIllegalArgumentException("", DukeException.DukeExceptionType.TODO);
                }
                String todoName = todoInput[1];
                Todo newTodo = new Todo(todoName);
                taskList.addTask(newTodo);
                result = ui.showAddTaskMessage(newTodo, taskList);
            } else if (input.startsWith("event")) {
                if(input.split("event ").length < 2) {
                    throw new DukeIllegalArgumentException("", DukeException.DukeExceptionType.EVENT);
                }
                String eventInput = (input.split("event "))[1];
                if(eventInput.split(" /at ").length < 2) {
                    throw new DukeIllegalArgumentException("", DukeException.DukeExceptionType.EVENT);
                }
                String eventName = (eventInput.split(" /at "))[0];
                String eventTime = (eventInput.split(" /at "))[1];
                Event newEvent = new Event(eventName, eventTime);
                taskList.addTask(newEvent);
                result = ui.showAddTaskMessage(newEvent, taskList);
            } else if (input.startsWith("delete")) {
                int deleteNumber = Integer.parseInt((input.split("delete "))[1]);
                result = ui.showDeleteTaskMessage(deleteNumber, taskList);
                taskList.deleteTask(deleteNumber);
            } else {
                throw new DukeUnknownArgumentException("");
            }
        } catch (DukeException e) {
            result = ui.showErrorMessage(e);
        } catch (DateTimeParseException e) {
            result = ui.showErrorMessage("Please key in date and time as follows: DD-MM-YYYY HHMM");
        }
        storage.updateTasksOnSavedFile(taskList, ui);
        return result;
    }
}
