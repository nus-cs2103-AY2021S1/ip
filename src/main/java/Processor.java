import java.lang.reflect.Array;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 * <h1> Duke Processor class </h1>
 * This class is the class that processes the
 * commands to create readable Tasks that will be stored in
 * the arraylist of tasks and arraylist of records
 *
 * @author Lee Penn Han
 * @version 1.0
 * @since 2020-25-08
 */
public class Processor {

    public Processor() {}

    /**
     * Processes the command and filters
     * it the correct private methods to
     * instantiate the Task objects to be recorded
     *
     * @param command This is the user input
     * @param arraylst This is the arraylist that stores the current lists of tasks
     * @param dukeFile This is the DukeFile object that records the tasks
     * @throws DukeException Exception for unidentified commands
     */
    public void process(String command, ArrayList<Task> arraylst, DukeFile dukeFile) throws DukeException {
        String[] stringarr = command.split(" ");
        if (stringarr[0].equals("list")) {
            processorList(arraylst);
        } else if (stringarr[0].equals("done")) {
            int index = Integer.parseInt(stringarr[1]);
            String record = processorDone(arraylst, index);
            dukeFile.updateRecord(record, index);
        } else if (stringarr[0].equals("delete")) {
            int index = Integer.parseInt(stringarr[1]);
            processorDelete(arraylst, index);
            dukeFile.deleteRecord(index);
        } else if (stringarr[0].equals("find")) {
            String key = stringarr[1];
            processorFind(arraylst, key);
        } else {
            String record = processorAdd(command, arraylst);
            dukeFile.saveRecord(record);
        }
    }

    private void processorFind(ArrayList<Task> arraylst, String key) {
        int counter = 1;
        System.out.println("_________________________________________\n" + "Here are the matching tasks in your list:");
        for (int i = 0; i < arraylst.size(); i++) {
            if (arraylst.get(i).getTask().contains(key.trim().toLowerCase())) {
                System.out.println(counter + "." + arraylst.get(i).toString());
                counter++;
            }
        }
        System.out.println("_________________________________________");
    }

    private void processorList(ArrayList<Task> arraylst) {
        System.out.println("_________________________________________\n" + "Here are the tasks in your list:");
        for (int i = 0; i < arraylst.size(); i++) {
            int index = i+1;
            System.out.println(index + "." + arraylst.get(i).toString());
        }
        System.out.println("_________________________________________");
    }

    private String processorDone(ArrayList<Task> arraylst, int index) {
        int i = index - 1;
        arraylst.get(i).setDone();
        System.out.println("_________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(index + "." + arraylst.get(i).toString());
        System.out.println("_________________________________________");
        return arraylst.get(i).toString();
    }

    private void processorDelete(ArrayList<Task> arraylst, int index) {
        int i = index - 1;
        Task removed_task = arraylst.get(i);
        arraylst.remove(i);
        System.out.println("_________________________________________");
        System.out.println("Noted, I've removed this task:");
        System.out.println(removed_task.toString());
        System.out.println("Now you have " + arraylst.size() + " tasks in the list.");
        System.out.println("_________________________________________");
    }

    private String processorAdd(String cmd, ArrayList<Task> arraylst) throws DukeException {
        String[] stringarr = cmd.split(" ", 2);
        if (stringarr[0].equals("todo")) {
            if (stringarr.length <= 1) {
                String message = "OOPS!!! The description of a todo cannot be empty";
                throw new DukeException("_________________________________________\n" + message + "\n_________________________________________");
            } else {
                Todo todo = new Todo(stringarr[1]);
                arraylst.add(todo);
            }
        } else if (stringarr[0].equals("deadline")) {
            if (stringarr.length <= 1) {
                String message = "OOPS!!! The description of a deadline cannot be empty";
                throw new DukeException("_________________________________________\n" + message + "\n_________________________________________");
            } else {
                String[] secondarr = stringarr[1].split("/by", 2);
                LocalDate date = LocalDate.parse(secondarr[1].trim());
                Deadline deadline = new Deadline(secondarr[0], date);
                arraylst.add(deadline);
            }
        } else if (stringarr[0].equals("event")) {
            if (stringarr.length <= 1) {
                String message = "OOPS!!! The description of an event cannot be empty";
                throw new DukeException("_________________________________________\n" + message + "\n_________________________________________");
            } else {
                String[] secondarr = stringarr[1].split("/at", 2);
                Event event = new Event(secondarr[0], secondarr[1]);
                arraylst.add(event);
            }
        } else {
            String message = "OOPS!!! I'm sorry, but I don't know what that means :-(";
            throw new DukeException("_________________________________________\n" + message + "\n_________________________________________");
        }
        int lastelem = arraylst.size() - 1;
        Task latesttask = arraylst.get(lastelem);
        System.out.println("_________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(latesttask.toString());
        System.out.println("Now you have " + arraylst.size() + " tasks in the list.");
        System.out.println("_________________________________________");
        return latesttask.toString();
    }
}
