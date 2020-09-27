package duke;

/**
 * Parses the data and processes the command from the users
 * Returns the response as a string
 */

public class ParserGUI {

    /**
     * Processes the command entered by the user
     * Returns the output of response
     *
     * @param command  String command entered
     * @param ui       GUI created to interact with users
     * @param taskList TaskList to be processed with
     * @param filePath String the path of the data storage
     * @return output String
     */
    public static String processCommand(String command, GUI ui, TaskList taskList, String filePath) {

        String[] portions = command.split(" ", 2);
        String commandFront = portions[0];
        String output = "";

        switch (commandFront) {
            case "food":
                output = "Only an apple pie";
                break;
            case "allowance":
                output = "I have checked, it is 2000000 SGD";
                break;
            case "tasks":
                output = processTasksCommand(taskList, ui);
                break;
            case "clear":
                output = processClearCommand(taskList, ui, filePath);
                break;
            case "done":
                output = processDoneCommand(taskList, ui, filePath, portions);
                break;
            case "delete":
                output = processDeleteCommand(taskList, ui, filePath, portions);
                break;
            case "find":
                output = processFindCommand(taskList, ui, portions);
                break;
            case "bye":
                output = ui.printBye();
                break;
            case "exit":
                System.exit(0);
                break;
            case "todo":
                output = processToDoCommand(taskList, ui, portions, filePath);
                break;
            case "deadline":
                output = processDeadlineCommand(taskList, ui, portions, filePath);
                break;
            case "event":
                output = processEventCommand(taskList, ui, portions, filePath);
                break;
            default:
                output = ui.failToUnderstand();
                break;
        }
        return output;
    }

    private static String processClearCommand(TaskList taskList, GUI ui,  String filePath ) {
        if (taskList.tasks.isEmpty()) {
            return ui.printEmptyList();
        } else {
           taskList.tasks.clear();
            Storage.updateTasks(taskList.getTaskCounts(), taskList.tasks, filePath);
            return ui.printClassCleared();
        }
    }

    /**
     * Processes the command "tasks" and prints the task list
     */

    private static String processTasksCommand(TaskList taskList, GUI ui) {
        if (taskList.tasks.isEmpty()) {
            return ui.printEmptyList();
        } else {
            return ui.printTaskList(taskList);
        }
    }

    /**
     * Processes the command "done + number" and marks the relative task done
     */

    private static String processDoneCommand(TaskList taskList, GUI ui, String filePath, String[] portions) {
        if (portions.length == 1) {
            return ui.failToMarkDone();
        } else {
            int taskNumber = Integer.parseInt(portions[1]);
            if (taskNumber < 1 || taskNumber > taskList.taskCounts) {
                return ui.failToFindTask();
            } else {
                Task task = taskList.tasks.get(taskNumber - 1);
                task.markTaskAsDone();
                Storage.updateTasks(taskList.getTaskCounts(), taskList.tasks, filePath);
                return ui.markTaskDone(task);
            }
        }

    }

    /**
     * Processes the command "delete + number" and deletes the relative task
     */

    private static String processDeleteCommand(TaskList taskList, GUI ui, String filePath, String[] portions) {
        if (portions.length == 1) {
            return ui.failToDelete();
        } else {
            int taskNumber = Integer.parseInt(portions[1]);
            if (taskNumber < 1 || taskNumber > taskList.taskCounts) {
                return ui.failToFindTask();
            } else {
                Task task = taskList.tasks.get(taskNumber - 1);
                taskList.deleteTask(task);
                Storage.updateTasks(taskList.getTaskCounts(), taskList.tasks, filePath);
                return ui.deleteTaskSuccessful(task, taskList);
            }
        }

    }

    /**
     * Processes the command "find + info" and deletes the relative task to the info
     */

    private static String processFindCommand(TaskList taskList, GUI ui, String[] portions) {
        if (taskList.tasks.isEmpty()) {
            return ui.printEmptyList();
        } else {
            return ui.printFound(portions[1], taskList);
        }
    }

    /**
     * Processes the command and adds the task to the task list if task is valid
     * Updates the storage if task is valid
     */

    private static String processToDoCommand(TaskList taskList, GUI ui, String[] portions, String filePath) {

        if (portions.length == 1) {
            return ui.failToFindDetails();
        }

        ToDo toDo = new ToDo(Task.TASK_TODO, Task.DOING, portions[1]);
        boolean hasDuplicates = false;

        //find duplicates
        for (int i = 0; i < taskList.taskCounts; i++) {
            if (taskList.tasks.get(i).toString().equals(toDo.toString())) {
                hasDuplicates = true;
                break;
            }
        }
        //add task if no duplicates
        if (hasDuplicates) {
            return ui.findDuplicates();
        } else {
            taskList.addTask(toDo);
            Storage.updateTasks(taskList.getTaskCounts(), taskList.tasks, filePath);
            return ui.addTaskSuccessful(toDo, taskList);
        }
    }

    /**
     * Processes the deadline command and adds the task to the task list if task is valid
     * updates the storage if the task is valid
     */

    private static String processDeadlineCommand(TaskList taskList, GUI ui, String[] portions, String filePath) {

        if (portions.length == 1) {
            return ui.failToFindDetails();
        }

        String[] deadlineSplitter = portions[1].split("/by ");
        if (deadlineSplitter.length == 1) {
            return ui.failToFindTime();
        }

        String detail = deadlineSplitter[0];
        String date = deadlineSplitter[1];
        Deadline deadline = new Deadline(Task.TASK_DEADLINE, Task.DOING, detail, date);
        boolean hasDuplicates = false;

        //find duplicates
        for (int i = 0; i < taskList.taskCounts; i++) {
            if (taskList.tasks.get(i).toString().equals(deadline.toString())) {
                hasDuplicates = true;
                break;
            }
        }
        //add task if no duplicates
        if (hasDuplicates) {
            return ui.findDuplicates();
        } else {
            taskList.addTask(deadline);
            Storage.updateTasks(taskList.getTaskCounts(), taskList.tasks, filePath);
            return ui.addTaskSuccessful(deadline, taskList);
        }
    }

    /**
     * Processes the event command and adds the task to the task list if task is valid
     * updates the storage if the task is valid
     */
    private static String processEventCommand(TaskList taskList, GUI ui, String[] portions, String filePath) {

        if (portions.length == 1) {
            return ui.failToFindDetails();
        }

        String[] eventSplitter = portions[1].split("/at ");
        if (eventSplitter.length == 1) {
            return ui.failToFindTime();
        }

        String detail = eventSplitter[0];
        String date = eventSplitter[1];
        Event event = new Event(Task.TASK_EVENT, Task.DOING, detail, date);
        boolean hasDuplicates = false;

        //find duplicates
        for (int i = 0; i < taskList.taskCounts; i++) {
            if (taskList.tasks.get(i).toString().equals(event.toString())) {
                hasDuplicates = true;
                break;
            }
        }

        //add task if no duplicates
        if (hasDuplicates) {
            return ui.findDuplicates();
        } else {
            taskList.addTask(event);
            Storage.updateTasks(taskList.getTaskCounts(), taskList.tasks, filePath);
            return ui.addTaskSuccessful(event, taskList);
        }

    }
}