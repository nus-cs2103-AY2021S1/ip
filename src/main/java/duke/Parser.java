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
                System.out.println("Ka To:");
                System.out.println(" ");
                System.out.println("Only an apple pie");
                break;
            case "allowance":
                System.out.println("Ka To:");
                System.out.println(" ");
                System.out.println("I have checked, it is 2000000 SGD");
                break;
            case "tasks":
                if (taskList.tasks.isEmpty()) {
                    ui.printEmptyList();
                } else {
                    ui.printTaskList(taskList);
                }
                break;
            case "done":
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
                break;
            case "delete":
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
                break;
            case "find":
                if (taskList.tasks.isEmpty()) {
                    ui.printEmptyList();
                } else {
                    ui.printFound(portions[1], taskList);
                }
                break;
            case "bye":
                ui.printBye();
                System.exit(0);
            case "todo":
            case "deadline":
            case "event":
                if (portions.length == 1) {
                    ui.failToFindDetails();
                } else if(commandFront.equals("todo")){
                    ToDo toDo = new ToDo(Task.TASK_TODO, Task.DOING, portions[1]);
                    taskList.addTask(toDo);
                    ui.addTaskSuccessful(toDo, taskList);
                } else if(commandFront.equals("deadline")) {
                    String[] deadlineSplitter = portions[1].split("/by ");
                    if (deadlineSplitter.length == 1) {
                        ui.failToFindTime();
                    } else {
                        String detail = deadlineSplitter[0];
                        String date = deadlineSplitter[1];
                        Deadline deadline = new Deadline(Task.TASK_DEADLINE, Task.DOING, detail, date);
                        taskList.addTask(deadline);
                        ui.addTaskSuccessful(deadline, taskList);
                    }
                }else {
                    String[] eventSplitter = portions[1].split("/at ");
                    if (eventSplitter.length == 1) {
                        ui.failToFindTime();
                    } else {
                        String detail = eventSplitter[0];
                        String date = eventSplitter[1];
                        Event event = new Event(Task.TASK_EVENT, Task.DOING, detail, date);
                        taskList.addTask(event);
                        ui.addTaskSuccessful(event, taskList);
                    }
                }
                Storage.updateTasks(taskList.getTaskCounts(), taskList.tasks, filePath);
                break;
            default:
                ui.failToUnderstand();
                break;
        }
    }
}
