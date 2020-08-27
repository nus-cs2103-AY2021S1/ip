import java.util.ArrayList;
import java.time.LocalDate;

/**
 * <h1> Duke Parser class </h1>
 * This class is the class that processes the
 * commands to create readable Tasks that will be stored in
 * the arraylist of tasks and arraylist of records
 *
 * @author Lee Penn Han
 * @version 1.0
 * @since 2020-25-08
 */
public class Parser {

    public Parser() {}

    /**
     * Processes the command and filters
     * it the correct private methods to
     * instantiate the Task objects to be recorded
     *
     * @param command This is the user input
     * @param taskList This is the taskList class that stores the current lists of tasks
     * @param storage This is the Storage object that records the tasks
     * @throws DukeException Exception for unidentified commands
     */
    public static void process(String command, TaskList taskList, Storage storage) throws DukeException {
        String[] stringarr = command.split(" ");
        if (stringarr[0].equals("list")) {
            processorList(taskList);
        } else if (stringarr[0].equals("done")) {
            int index = Integer.parseInt(stringarr[1]);
            String record = taskList.updateTask(index);
            storage.updateRecord(record, index);
        } else if (stringarr[0].equals("delete")) {
            int index = Integer.parseInt(stringarr[1]);
            taskList.deleteTask(index);
            storage.deleteRecord(index);
        } else if (stringarr[0].equals("find")) {
            String key = stringarr[1];
            processorFind(taskList, key);
        } else {
            String record = processorAdd(command, taskList);
            storage.saveRecord(record);
        }
    }


    public static void processorFind(TaskList taskList, String key) {
        int counter = 1;
        System.out.println("_________________________________________\n" + "Here are the matching tasks in your list:");
        for (int i = 0; i < taskList.getListSize(); i++) {
            if (taskList.getTask(i).getTask().contains(key)) {
                System.out.println(counter + "." + taskList.getTask(i).toString());
                counter++;
            }
        }
        System.out.println("_________________________________________");
    }


    public static void processorList(TaskList taskList) {
        System.out.println("_________________________________________\n" + "Here are the tasks in your list:");
        for (int i = 0; i < taskList.getListSize(); i++) {
            int index = i+1;
            System.out.println(index + "." + taskList.getTask(i).toString());
        }
        System.out.println("_________________________________________");
    }


    public static String processorAdd(String cmd, TaskList taskList) throws DukeException {
        String[] stringarr = cmd.split(" ", 2);
        if (stringarr[0].equals("todo")) {
            if (stringarr.length <= 1) {
                String message = "OOPS!!! The description of a todo cannot be empty";
                throw new DukeException("_________________________________________\n" + message + "\n_________________________________________");
            } else {
                Todo todo = new Todo(stringarr[1]);
                taskList.addTask(todo);
                return todo.toString();
            }
        } else if (stringarr[0].equals("deadline")) {
            if (stringarr.length <= 1) {
                String message = "OOPS!!! The description of a deadline cannot be empty";
                throw new DukeException("_________________________________________\n" + message + "\n_________________________________________");
            } else {
                String[] secondarr = stringarr[1].split("/by", 2);
                LocalDate date = LocalDate.parse(secondarr[1].trim());
                Deadline deadline = new Deadline(secondarr[0], date);
                taskList.addTask(deadline);
                return deadline.toString();
            }
        } else if (stringarr[0].equals("event")) {
            if (stringarr.length <= 1) {
                String message = "OOPS!!! The description of an event cannot be empty";
                throw new DukeException("_________________________________________\n" + message + "\n_________________________________________");
            } else {
                String[] secondarr = stringarr[1].split("/at", 2);
                Event event = new Event(secondarr[0], secondarr[1]);
                taskList.addTask(event);
                return event.toString();
            }
        } else if (cmd.length() == 0){
                return null;
        } else {
            String message = "OOPS!!! I'm sorry, but I don't know what that means :-(";
            throw new DukeException("_________________________________________\n" + message + "\n_________________________________________");
        }
    }
}
