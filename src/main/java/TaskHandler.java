import java.util.ArrayList;

public class TaskHandler {
    protected ArrayList<Task> taskList;

    enum operationType {
        DONE, DELETE
    }

    public TaskHandler(ArrayList<Task> list) {
        this.taskList = list;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void printList() {
        if (taskList.isEmpty()) {
            // Asks user for tasks when printing empty list
            System.out.println("Empty list, pls add tasks to list first");
        }
        int listPos = 1;
        indent(1);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++,listPos++) {
            indent(2);
            System.out.println(listPos + ". " + taskList.get(i));
        }
        indent(1);
        System.out.println("You have " + taskList.size() + " task(s) in the list");
    }

    public static Task modifyTask(String input, ArrayList<Task> list, TaskHandler.operationType currentOp) throws DukeException {
        String[] stringArr = input.split(" ");
        String lowerCaseOperation = currentOp.toString().toLowerCase();
        if (stringArr.length != 2 ) {
            // if multiple tasks are given as arguments
            throw new DukeException("\u2639 Oops, pls enter only one task number after " + lowerCaseOperation);
        }
        try {
            // Finding the actual task
            int indexOfDone = Integer.parseInt(stringArr[1]) - 1;
            Task currentTask = list.get(indexOfDone);
            if (currentOp == operationType.DELETE) {
                list.remove(currentTask);
            } else if (currentOp == operationType.DONE) {
                currentTask.markAsDone();
            }
            return currentTask;
        } catch (IndexOutOfBoundsException | NumberFormatException e){
            throw new DukeException("\u2639 Oops, pls enter a valid task number after " + lowerCaseOperation);
        }
    }

    public static Task processNewTask(String input, Task.taskType tasktype) throws DukeException {
        // Sorts the input into a task with or without time
        if (tasktype == Task.taskType.TODO) {
            // Without time
            if (input.substring(4).trim().isEmpty()) {
                // if given empty arguments or space as task
                throw new DukeException("\u2639 Oops, the description of a todo cannot be empty.");
            }
            String taskDesc = input.substring(5);
            return new Todo(taskDesc);
        }
        if (tasktype == Task.taskType.DEADLINE) {
            try {
                return processTaskWithTime(input, tasktype, "/by");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("\u2639 Oops, use add deadline format: deadline [task] /by [time (can be 'YYYY-MM-DD HHMM')]");
            }
        } else if (tasktype == Task.taskType.EVENT) {
            try {
                return processTaskWithTime(input, tasktype, "/at");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("\u2639 Oops, use add event format: event [task] /at [time]");
            }
        } else {
            return new Task("this task should not be created", "todo");
        }
    }

    public static Task processTaskWithTime(String input, Task.taskType tasktype, String separator) throws DukeException {
        // Process string to find task description and time
        String taskDesc = input.substring(tasktype.name().length() + 1, input.indexOf(separator) - 1);
        checkIsFieldEmpty("taskDesc", taskDesc);
        // +4 due to size of /by or /at with a space
        String time = input.substring(input.indexOf(separator) + 4);
        checkIsFieldEmpty("time", time);
        if (tasktype ==  Task.taskType.DEADLINE) {
            return new Deadline(taskDesc, time);
        } else {
            return new Event(taskDesc, time);
        }
    }

    public static void checkIsFieldEmpty(String nameOfField, String field) throws DukeException {
        // check whether the argument given is empty
        if (field.trim().isEmpty()) {
            throw new DukeException("\u2639 Oops, " + nameOfField + " cannot be empty");
        }
    }

    public static void indent(int times) {
        for (int i=0; i<times; i++) {
            System.out.print("    ");
        }
    }

    public static void receiveInvalidCommand() throws DukeException {
        throw new DukeException("\u2639 Oops, I'm sorry but I don't know what that means :-(");
    }
}
