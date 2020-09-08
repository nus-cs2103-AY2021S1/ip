import com.sun.java.accessibility.util.GUIInitializedListener;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Parser class that deals with loading tasks from the file and
 * saving tasks in the file.
 */
public class Parser {

    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;

    public Parser(Ui ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }
    /**
     * Evaluates the syntax of the input command and executes it,
     * throwing a DukeException if the syntax is correct.
     * @param input the user input
     */
    public String action(String input) {
        String result;
        try {
            if (input.equals("bye")) {
                result = Ui.printBye();
            } else if (input.equals("list")) {
                result = ui.printList(taskList);
            } else if (input.startsWith("done")) {
                String[] number = input.split("done ");
                int num = Integer.parseInt(number[1]);
                Task task = taskList.getTask(num);
                task.markAsDone();
                storage.saveTasks(taskList, ui);
                result = ui.printDone(taskList.getTask(num));
            } else if (input.startsWith("delete")) {
                String[] number = input.split("delete ");
                int num = Integer.parseInt(number[1]);
                Task task = taskList.getTask(num);
                taskList.deleteTask(num);
                storage.saveTasks(taskList, ui);
                result = ui.printDelete(taskList, task);
            } else if (input.startsWith("todo")) {
                String[] array = input.split("todo ");
                if (array.length < 2) {
                    throw new DukeException("The description of a todo cannot be empty!");
                } else {
                    String des = array[1];
                    Task todo = new ToDos(des);
                    taskList.addTask(todo);
                    result = ui.printAdd(taskList, todo);
                    storage.saveTasks(taskList, ui);
                }
            } else if (input.startsWith("deadline")) {
                String[] array = input.split("deadline ");
                if (array.length < 2) {
                    throw new DukeException("The description of a deadline cannot be empty!");
                } else {
                    String[] arr = array[1].split(" /by ");
                    String des = arr[0];
                    if (arr.length == 1) {
                        throw new DukeException("The deadline of the task cannot be empty!");
                    } else {
                        try {
                            String due = arr[1];
                            Task dl = new Deadline(des, due);
                            taskList.addTask(dl);
                            result = ui.printAdd(taskList, dl);
                            storage.saveTasks(taskList, ui);
                        } catch (DateTimeParseException e) {
                            result = ui.printError("Please use this format: \n" +
                                    "dd-MM-yyyy HHmm");
                        }
                    }
                }
            } else if (input.startsWith("event")) {
                String[] array = input.split("event ");
                if (array.length < 2) {
                    throw new DukeException("The description of a event cannot be empty!");
                } else {
                    String[] arr = array[1].split(" /at ");
                    String des = arr[0];
                    if (arr.length == 1) {
                        throw new DukeException("The deadline of the event cannot be empty!");
                    } else {
                        try {
                            String due = arr[1];
                            Task event = new Events(des, due);
                            taskList.addTask(event);
                            result = ui.printAdd(taskList, event);
                            storage.saveTasks(taskList, ui);
                        } catch (DateTimeParseException e) {
                            result = ui.printError("Please use this format: \n" +
                                        "dd-MM-yyyy HHmm");
                        }
                    }
                }
            } else if (input.startsWith("find")) {
                    String word = input.split("find ")[1];
                    result = ui.find(word, taskList);
            } else{
                throw new DukeException("I'm sorry, but I don't know what that means.");
            }
        } catch (DukeException e) {
            result = ui.printDukeError(e);
        }
        return result;
    }



}
