package duke;

/**
 * Parses the data and processes the command from the users
 */

public class ParserGUI {

    /**
     * Processes the command entered by the user
     *
     * @param command String command entered
     * @param ui Ui created to interact with users
     * @param taskList TaskList to be processed with
     * @param filePath String the path of the data storage
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
                if (taskList.tasks.isEmpty()) {
                    output = ui.printEmptyList();
                } else {
                    output = ui.printTaskList(taskList);
                }
                break;
            case "done":
                if (portions.length == 1) {
                    output = ui.failToMarkDone();
                } else {
                    int taskNumber = Integer.parseInt(portions[1]);
                    if (taskNumber > taskList.taskCounts) {
                        output = ui.failToFindTask();
                    } else {
                        Task task = taskList.tasks.get(taskNumber - 1);
                        task.markTaskAsDone();
                        output = ui.markTaskDone(task);
                    }
                }
                Storage.updateTasks(taskList.getTaskCounts(), taskList.tasks, filePath);
                break;
            case "delete":
                if (portions.length == 1) {
                    output = ui.failToDelete();
                } else {
                    int taskNumber = Integer.parseInt(portions[1]);
                    if (taskNumber > taskList.taskCounts) {
                        output = ui.failToFindTask();
                    } else {
                        Task task = taskList.tasks.get(taskNumber - 1);
                        taskList.deleteTask(task);
                        output = ui.deleteTaskSuccessful(task, taskList);
                    }
                }
                Storage.updateTasks(taskList.getTaskCounts(), taskList.tasks, filePath);
                break;
            case "find":
                if (taskList.tasks.isEmpty()) {
                    output = ui.printEmptyList();
                } else {
                    output = ui.printFound(portions[1], taskList);
                }
                break;
            case "bye": output = ui.printBye();
                break;
            case "exit": System.exit(0);
                break;
            case "todo":
            case "deadline":
            case "event":
                if (portions.length == 1) {
                    ui.failToFindDetails();
                } else {
                    switch (commandFront) {
                        case "todo":
                            ToDo toDo = new ToDo(Task.TASK_TODO, Task.DOING, portions[1]);
                            taskList.addTask(toDo);
                            output = ui.addTaskSuccessful(toDo, taskList);
                            break;
                        case "deadline":
                            String[] deadlineSplitter = portions[1].split("/by ");
                            if (deadlineSplitter.length == 1) {
                                output = ui.failToFindTime();
                            } else {
                                String detail = deadlineSplitter[0];
                                String date = deadlineSplitter[1];
                                Deadline deadline = new Deadline(Task.TASK_DEADLINE, Task.DOING, detail, date);
                                taskList.addTask(deadline);
                                output = ui.addTaskSuccessful(deadline, taskList);
                            }
                            break;
                        case "event":
                            String[] eventSplitter = portions[1].split("/at ");
                            if (eventSplitter.length == 1) {
                                output = ui.failToFindTime();
                            } else {
                                String detail = eventSplitter[0];
                                String date = eventSplitter[1];
                                Event event = new Event(Task.TASK_EVENT, Task.DOING, detail, date);
                                taskList.addTask(event);
                                output = ui.addTaskSuccessful(event, taskList);
                            }
                            break;
                        default:
                            break;
                    }
                    Storage.updateTasks(taskList.getTaskCounts(), taskList.tasks, filePath);
                }

                break;
            default:
                output = ui.failToUnderstand();
                break;
        } return output;
    }
}
