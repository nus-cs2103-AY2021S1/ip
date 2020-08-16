import java.util.ArrayList;

public class TaskHandler {
    protected ArrayList<Task> taskList;

    public TaskHandler() {
        this.taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void printList() {
        if (taskList.isEmpty()) {
            // Asks user for tasks when printing empty list
            indent(1);
            System.out.println("Empty list, pls add tasks to list first");
            return;
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

    public Task processTask(String input, String tasktype) throws DukeException {
        // Processes the input into a task
        if (tasktype.equals("todo")) {
            if (input.substring(4).trim().isEmpty()) {
                // if given empty arguments or space as task
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            String taskDesc = input.substring(5);
            return new Todo(taskDesc);
        }
        if (tasktype.equals("deadline")) {
            return processTaskWithTime(input, tasktype, "/by");
        } else if(tasktype.equals("event")) {
            return processTaskWithTime(input, tasktype, "/at");
        } else {
            return new Task("this task should not be created");
        }
    }

    public static Task processTaskWithTime(String input, String tasktype, String separator) throws DukeException {
        // String processing to find task description and time
        String taskDesc = input.substring(tasktype.length(), input.indexOf(separator));
        checkIsFieldEmpty("taskDesc", taskDesc);
        // +4 due to size of /by or /at with a space
        String time = input.substring(input.indexOf(separator) + 4);
        checkIsFieldEmpty("time", time);
        if (tasktype.equals("deadline")) {
            return new Deadline(taskDesc, time);
        } else {
            return new Event(taskDesc, time);
        }
    }

    public static void checkIsFieldEmpty(String name, String string) throws DukeException {
        // check whether the argument given is empty
        if (string.trim().isEmpty()) {
            throw new DukeException("Oops, " + name + " cannot be empty");
        }
    }

    public static void indent(int times) {
        for (int i=0; i<times; i++) {
            System.out.print("    ");
        }
    }
}
