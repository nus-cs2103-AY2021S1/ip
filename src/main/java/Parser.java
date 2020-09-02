
public class Parser {

    public static void processCommand(String command, Ui ui, TaskList taskList, String filePath) {

        String[] portions = command.split(" ", 2);
        String commandFront = portions[0];

        if (commandFront.equals("food")) {
            System.out.println("Ka To:");
            System.out.println(" ");
            System.out.println("Only an apple pie");
        } else if (commandFront.equals("allowance")) {
            System.out.println("Ka To:");
            System.out.println(" ");
            System.out.println("I have checked, it is 2000000 SGD");
        } else if (commandFront.equals("tasks")) {
            if (taskList.tasks.isEmpty()) {
                ui.printEmptyList();
            } else {
                ui.printTaskList(taskList);
            }
        } else if (commandFront.equals("done")) {
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
        } else if (commandFront.equals("delete")) {
            if (portions.length == 1) {
                ui.failToDelete();
            } else {
                int taskNumber = Integer.parseInt(portions[1]);
                if (taskNumber > taskList.taskCounts) {
                    ui.failToFindTask();
                } else {
                    Task task = taskList.tasks.get(taskNumber - 1);
                    task.markTaskAsDeleted();
                    ui.deleteTaskSuccessful(task, taskList);
                }
            }
            Storage.updateTasks(taskList.getTaskCounts(), taskList.tasks, filePath);
        } else if (commandFront.equals("bye")) {
            ui.printBye();
            System.exit(0);
        } else if (commandFront.equals("todo")
                || commandFront.equals("deadline")
                || commandFront.equals("event")) {
            if (portions.length == 1) {
                ui.failToFindDetails();
            } else {

                switch (commandFront) {
                    case "todo":
                        ToDo toDo = new ToDo(Task.TASK_TODO, Task.DOING, portions[1]);
                        taskList.addTask(toDo);
                        ui.addTaskSuccessful(toDo, taskList);
                        break;
                    case "deadline":
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
                        break;
                    case "event":
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
                        break;
                    default:
                        break;
                }
                Storage.updateTasks(taskList.getTaskCounts(), taskList.tasks, filePath);
            }

        } else {
            ui.failToUnderstand();
        }
    }
}
