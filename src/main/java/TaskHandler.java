import java.util.ArrayList;

public class TaskHandler {
    protected ArrayList<Task> taskList;

    public TaskHandler() {
        this.taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public Task doTask(String input, ArrayList<Task> list) throws DukeException {
        try {
            Task currentTask = findTaskByNum(input, list);
            // Mark given task as done
            currentTask.markAsDone();
            return currentTask;
        } catch (Exception e) {
            throw new DukeException("Oops, pls enter a valid task to complete");
        }
    }

    public Task deleteTask(String input, ArrayList<Task> list) throws DukeException {
        Task currentTask = findTaskByNum(input, list);
        try {
            list.remove(currentTask);
            return currentTask;
        } catch (Exception e) {
            throw new DukeException("Oops, pls enter a valid task to delete");
        }
    }

    public Task findTaskByNum(String input, ArrayList<Task> list) throws DukeException {
        try {
            // Checking if only one task is given to complete/valid int
            String[] stringArr = input.split(" ");
            // Finding the actual task
            int indexOfDone = Integer.parseInt(stringArr[1]) - 1;
            return list.get(indexOfDone);
        } catch (Exception e){
            throw new DukeException("Oops, pls enter only one valid task");
        }
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


    public Task sortTask(String input, String tasktype) throws DukeException {
        // Sorts the input into a task with or without time
        if (tasktype.equals("todo")) {
            // Without time
            if (input.substring(4).trim().isEmpty()) {
                // if given empty arguments or space as task
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            String taskDesc = input.substring(5);
            return new Todo(taskDesc);
        }
        if (tasktype.equals("deadline")) {
            // With time
            try {
                return processTaskWithTime(input, tasktype, "/by");
            } catch (Exception e) {
                throw new DukeException("Oops, use add deadline format: deadline [task] /by [time]");
            }
        } else if (tasktype.equals("event")) {
            // With time
            try {
                return processTaskWithTime(input, tasktype, "/at");
            } catch (Exception e) {
                throw new DukeException("Oops, use add event format: event [task] /at [time]");
            }
        } else {
            return new Task("this task should not be created", "todo");
        }
    }

    public static Task processTaskWithTime(String input, String tasktype, String separator) throws DukeException {
        // String processing to find task description and time
        String taskDesc = input.substring(tasktype.length() + 1, input.indexOf(separator));
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

    public static void checkIsFieldEmpty(String nameOfField, String field) throws DukeException {
        // check whether the argument given is empty
        if (field.trim().isEmpty()) {
            throw new DukeException("Oops, " + nameOfField + " cannot be empty");
        }
    }

    public static void indent(int times) {
        for (int i=0; i<times; i++) {
            System.out.print("    ");
        }
    }
}
