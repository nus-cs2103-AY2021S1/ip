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
     * Splits user input into command and instructions.
     * @param input user input into the application.
     * @return String array containing the command in the first index and the
     * instructions to execute in the second index.
     */
    public String[] splitCommandAndInstructions(String input) {
        return input.split(" ", 2);
    }

    /**
     * Splits deadline arguments into description and time.
     * @param input user input containing both description and time.
     * @return String array containing the description in the first index and the 
     * time in the second index.
     */
    public String[] splitDeadlineArguments(String input) {
        return input.split(" /by ", 2);
    }

    /**
     * Splits event arguments into description and time.
     * @param input user input containing both description and time.
     * @return String array containing the description in the first index and the 
     * time in the second index.
     */
    public String[] splitEventArguments(String input) {
        return input.split(" /at ", 2);
    }

    /**
     * Splits time from schedule arguments.
     * @param input user input containing time.
     * @return String array containing schedule time in the second index.
     */
    public String[] splitScheduleArguments(String input) {
        return input.split(" /on ", 2);
    }
    
    
    /**
     * Takes in user input and prints the application's corresponding output.
     * @param input the user input as a String.
     * @return String containing the application's corresponding output.
     */
    public String parseAndProcessInput(String input) {
        String result;
        String[] commandAndArguments = splitCommandAndInstructions(input);
        String command = commandAndArguments[0];
        String arguments = commandAndArguments[1];
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
                String[] deadlineInput = splitCommandAndInstructions(input);
                if(deadlineInput.length < 2) {
                    throw new DukeIllegalArgumentException("", DukeException.DukeExceptionType.DEADLINE);
                }
                String[] deadlineArguments = splitDeadlineArguments(deadlineInput[1]);
                if(deadlineArguments.length < 2) {
                    throw new DukeIllegalArgumentException("", DukeException.DukeExceptionType.DEADLINE);
                }
                String deadlineName = deadlineArguments[0];
                String deadlineTime = deadlineArguments[1];
                Deadline newDeadline = new Deadline(deadlineName, deadlineTime);
                taskList.addTask(newDeadline);
                result = ui.showAddTaskMessage(newDeadline, taskList);
                
            } else if (input.startsWith("todo")) {
                String[] todoInput = splitCommandAndInstructions(input);
                if(todoInput.length < 2) {
                    throw new DukeIllegalArgumentException("", DukeException.DukeExceptionType.TODO);
                }
                String todoName = todoInput[1];
                Todo newTodo = new Todo(todoName);
                taskList.addTask(newTodo);
                result = ui.showAddTaskMessage(newTodo, taskList);
            } else if (input.startsWith("event")) {
                String[] eventInput = splitCommandAndInstructions(input);
                if(eventInput.length < 2) {
                    throw new DukeIllegalArgumentException("", DukeException.DukeExceptionType.EVENT);
                }
                String[] eventDescriptionAndTime = splitEventArguments(eventInput[1]);
                if(eventDescriptionAndTime.length < 2) {
                    throw new DukeIllegalArgumentException("", DukeException.DukeExceptionType.EVENT);
                }
                String eventName = eventDescriptionAndTime[0];
                String eventTime = eventDescriptionAndTime[1];
                Event newEvent = new Event(eventName, eventTime);
                taskList.addTask(newEvent);
                result = ui.showAddTaskMessage(newEvent, taskList);
            } else if (input.startsWith("delete")) {
                int deleteNumber = Integer.parseInt((splitCommandAndInstructions(input))[1]);
                result = ui.showDeleteTaskMessage(deleteNumber, taskList);
                taskList.deleteTask(deleteNumber);
            } else if(input.startsWith("schedule")) {
                String[] viewScheduleInput = splitCommandAndInstructions(input);
                if(viewScheduleInput.length < 2) {
                    throw new DukeIllegalArgumentException("", DukeException.DukeExceptionType.VIEW_SCHEDULE);
                }
                String scheduleArguments = viewScheduleInput[1];
                String scheduleTime = splitScheduleArguments(scheduleArguments)[1];
                TaskList matchingTasksOnDate = taskList.getTasksOnDate(scheduleTime);
                result = ui.showCurrentTasks(matchingTasksOnDate);
            } else {
                throw new DukeUnknownArgumentException("");
            }
        } catch (DukeException e) {
            result = ui.showErrorMessage(e);
        } catch (DateTimeParseException e) {
            result = ui.showErrorMessage("Please key in date and time as follows: DD-MM-YYYY HHMM");
        }
        storage.saveTasksToSavedFile(taskList, ui);
        return result;
    }
}
