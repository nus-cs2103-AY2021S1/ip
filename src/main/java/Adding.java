import java.util.List;

public class Adding {

    public static void addTask(String typeOfTask, String taskDetails, List<Task> tasks) throws DukeException {
        if (taskDetails.isBlank()) {
            throw new DukeException("OOPS!!! The description of a " + typeOfTask + " cannot be empty.");
        } else {
            Task newTask = null;
            int startIndex = 1;
            String description = "";
            String by;
            int timeIndex;
            if (typeOfTask.equals("todo")) {
                description = taskDetails.substring(startIndex);
                newTask = new ToDo(description);
            } else if (typeOfTask.equals("deadline")) {
                int indexBy = taskDetails.indexOf(" /by");
                if (indexBy == -1) {
                    throw new DukeException("OOPS!!! Deadline task is poorly formatted.");
                } else if (indexBy == 0) {
                    throw new DukeException("OOPS!!! Description of deadline is empty");
                }
                description = taskDetails.substring(startIndex, indexBy);
                timeIndex = indexBy + 5;
                if (taskDetails.length() <= timeIndex) {
                    throw new DukeException("OOPS!!! Deadline of task is not specified");
                } else {
                    by = taskDetails.substring(timeIndex);
                    newTask = new Deadline(description, by);
                }
            } else if (typeOfTask.equals("event")) {
                int indexBy = taskDetails.indexOf(" /at");
                if (indexBy == -1) {
                    throw new DukeException("OOPS!!! Event task is poorly formatted.");
                } else if (indexBy == 0) {
                    throw new DukeException("OOPS!!! Description of event is empty");
                }
                description = taskDetails.substring(startIndex, indexBy);
                timeIndex = indexBy + 5;
                if (taskDetails.length() <= timeIndex) {
                    throw new DukeException("OOPS!!! Duration of event is not specified");
                } else {
                    by = taskDetails.substring(timeIndex);
                    newTask = new Event(description, by);
                }
            }
            if (description.isBlank()) {
                throw new DukeException("OOPS!!! The description of a " + typeOfTask + " cannot be empty.");
            } else {
                tasks.add(newTask);
                System.out.println("    Got it. I've added this task:");
                System.out.println("      " + newTask);
                System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
            }
        }
    }
}
