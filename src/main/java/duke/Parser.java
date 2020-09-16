package duke;

/**
 * Parses the data and processes the command from the users
 */

public class Parser {

    /**
     * Processes the command entered by the user
     *
     * @param command String command entered
     * @param ui Ui created to interact with users
     * @param taskList TaskList to be processed with
     * @param filePath String the path of the data storage
     */
    public static void processCommand(String command, Ui ui, TaskList taskList, String filePath) {

        String[] portions = command.split(" ", 2);
        String commandFront = portions[0];

        switch (commandFront) {
            case "food":
                ui.replyFood();
                break;
            case "allowance":
                ui.replyAllowance();
                break;
            case "tasks":
                processTasksCommand(taskList, ui);
                break;
            case "done":
                processDoneCommand(taskList, ui, filePath, portions);
                break;
            case "delete":
                processDeleteCommand(taskList, ui, filePath, portions);
                break;
            case "find":
                processFindCommand(taskList, ui, portions);
                break;
            case "bye":
                ui.printBye();
                System.exit(0);
            case "todo":
                processToDoCommand(taskList, ui, portions, filePath);
                break;
            case "deadline":
                processDeadlineCommand(taskList, ui, portions, filePath);
                break;
            case "event":
                processEventCommand(taskList, ui, portions, filePath);
                break;
            default:
                ui.failToUnderstand();
                break;
        }
    }


    /**
     * Processes the command "tasks" and prints the task list
     */

    private static void processTasksCommand(TaskList taskList, Ui ui){
        if (taskList.tasks.isEmpty()) {
            ui.printEmptyList();
        } else {
            ui.printTaskList(taskList);
        }
    }

    /**
     * Processes the command "done + number" and marks the relative task done
     */

    private static void processDoneCommand(TaskList taskList, Ui ui, String filePath,  String[] portions){
        if (portions.length == 1) {
            ui.failToMarkDone();
        } else {
            int taskNumber = Integer.parseInt(portions[1]);
            if (taskNumber > taskList.taskCounts) {
                ui.failToFindTask();
            } else {
                Task task = taskList.tasks.get(taskNumber - 1);
                task.markTaskAsDone();
                ui.markTaskDone(task);
            }
        }
        Storage.updateTasks(taskList.getTaskCounts(), taskList.tasks, filePath);
    }

    /**
     * Processes the command "delete + number" and deletes the relative task
     */

    private static void processDeleteCommand(TaskList taskList, Ui ui, String filePath,  String[] portions){
        if (portions.length == 1) {
            ui.failToDelete();
        } else {
            int taskNumber = Integer.parseInt(portions[1]);
            if (taskNumber > taskList.taskCounts) {
                ui.failToFindTask();
            } else {
                Task task = taskList.tasks.get(taskNumber - 1);
                task.markTaskAsDeleted();
                taskList.deleteTask(task);
                ui.deleteTaskSuccessful(task, taskList);
            }
        }
        Storage.updateTasks(taskList.getTaskCounts(), taskList.tasks, filePath);
    }

    /**
     * Processes the command "find + info" and deletes the relative task to the info
     */

    private static void processFindCommand(TaskList taskList, Ui ui, String[] portions){
        if (taskList.tasks.isEmpty()) {
            ui.printEmptyList();
        } else {
            ui.printFound(portions[1], taskList);
        }
    }

    /**
     * Processes the command and adds the task to the task list if task is valid
     * Updates the storage if task is valid
     */

    private static void processToDoCommand(TaskList taskList, Ui ui, String[] portions, String filePath){
        boolean isCorrectCommand = true;

        if (portions.length == 1) {
            ui.failToFindDetails();
            isCorrectCommand = false;
        }

        if(isCorrectCommand){
            ToDo toDo = new ToDo(Task.TASK_TODO, Task.DOING, portions[1]);

            boolean hasDuplicates = false;
            //find duplicates
            for(int i = 0; i < taskList.taskCounts; i++){
                if (taskList.tasks.get(i).toString().equals(toDo.toString())) {
                    hasDuplicates = true;
                    break;
                }
            }
            //add task if no duplicates
            if(hasDuplicates){
                ui.findDuplicates();
            }else {
                taskList.addTask(toDo);
                ui.addTaskSuccessful(toDo, taskList);
            }
            Storage.updateTasks(taskList.getTaskCounts(), taskList.tasks, filePath);
        }
    }

    /**
     * Processes the deadline command and adds the task to the task list if task is valid
     * updates the storage if the task is valid
     */

    private static void processDeadlineCommand(TaskList taskList, Ui ui, String[] portions, String filePath){
        boolean isCorrectCommand = true;
        boolean includesTime = false;
        String detail = "";
        String date = "";
        if (portions.length == 1) {
            ui.failToFindDetails();
            isCorrectCommand = false;
        }

        if(isCorrectCommand){
            String[] deadlineSplitter = portions[1].split("/by ");
            if (deadlineSplitter.length == 1) {
                ui.failToFindTime();

            }else {
                detail = deadlineSplitter[0];
                date = deadlineSplitter[1];
                includesTime = true;
            }
        }

        if(includesTime) {
            Deadline deadline = new Deadline(Task.TASK_DEADLINE, Task.DOING, detail, date);

            boolean hasDuplicates = false;
            //find duplicates
            for(int i = 0; i < taskList.taskCounts; i++){
                if (taskList.tasks.get(i).toString().equals(deadline.toString())) {
                    hasDuplicates = true;
                    break;
                }
            }
            //add task if no duplicates
            if(hasDuplicates){
                ui.findDuplicates();
            }else {
                taskList.addTask(deadline);
                ui.addTaskSuccessful(deadline, taskList);
            }
            Storage.updateTasks(taskList.getTaskCounts(), taskList.tasks, filePath);
        }
    }

    /**
     * Processes the event command and adds the task to the task list if task is valid
     * updates the storage if the task is valid
     */
    private static void processEventCommand(TaskList taskList, Ui ui, String[] portions, String filePath) {
        boolean isCorrectCommand = true;
        boolean includesTime = false;
        String detail = "";
        String date = "";
        if (portions.length == 1) {
            ui.failToFindDetails();
            isCorrectCommand = false;
        }

        if (isCorrectCommand) {
            String[] eventSplitter = portions[1].split("/at ");
            if (eventSplitter.length == 1) {
                ui.failToFindTime();

            } else {
                detail = eventSplitter[0];
                date = eventSplitter[1];
                includesTime = true;
            }
        }

        if (includesTime) {
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
                ui.findDuplicates();
            } else {
                taskList.addTask(event);
                ui.addTaskSuccessful(event, taskList);
            }
            Storage.updateTasks(taskList.getTaskCounts(), taskList.tasks, filePath);
        }
    }

}