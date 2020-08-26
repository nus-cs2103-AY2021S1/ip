import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Parser class deals with making sense of user input.
 */
public class Parser {
    
    /**
     * Takes in user input and processes it, producing an output.
     * @param ui the Ui currently in use.
     * @param taskList the TaskList currently in use.
     * @param storage the Storage object currently in use.
     */
    public static void echo(Ui ui, TaskList taskList, Storage storage) {
        ui.printHi();
        Scanner sc = new Scanner(System.in);
        storage.load(taskList, ui);
        
        while (true) {
            String input = sc.nextLine();
            try {
                if (input.equals("bye")) { //exit condition
                    ui.printBye();
                    break;
                } else if (input.equals("list")) {
                    ui.showTasks(taskList);
                } else if (input.startsWith("find")) {
                    String word = input.split("find ")[1];
                    ui.find(word, taskList);
                } else if (input.startsWith("done")) {
                    String[] number = input.split("done ");
                    int index = Integer.parseInt(number[1]);
                    taskList.markDone(index);
                    ui.markDone(index, taskList);
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
                    ui.printAdd(newDeadline, taskList);
                } else if (input.startsWith("todo")) {
                    String[] todoInput = input.split("todo ");
                    if(todoInput.length < 2) {
                        throw new DukeIllegalArgumentException("", DukeException.DukeExceptionType.TODO);
                    }
                    String todoName = todoInput[1];
                    Todo newTodo = new Todo(todoName);
                    taskList.addTask(newTodo);
                    ui.printAdd(newTodo, taskList);
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
                    ui.printAdd(newEvent, taskList);
                } else if (input.startsWith("delete")) {
                    int deleteNumber = Integer.parseInt((input.split("delete "))[1]);
                    ui.printDelete(deleteNumber, taskList);
                    taskList.deleteTask(deleteNumber);
                } else {
                    throw new DukeUnknownArgumentException("");
                }
                storage.save(taskList, ui);
            } catch (DukeException e) {
                ui.printError(e);
            } catch (DateTimeParseException e) {
                ui.printError("Please key in date and time as follows: DD-MM-YYYY HHMM");
            }
        }
        sc.close();
    }
    
    
}
