public class Parser {
    public static void respond(String command, Ui ui, TaskList taskList, String filePath) {
        String[] pieces = command.split(" ", 2);
        if (command.equals("bye")) { // terminating command
            ui.bye();
            System.exit(0);
        } else if (command.equals("list")) { // listing command
            if (taskList.list.isEmpty()) {
                ui.emptyList();
            } else {
                ui.printAll(taskList);
            }
        } else if (pieces[0].equals("done")) { // Mark Done task command
            if (pieces.length == 1) { // task number is missing
                ui.markDoneFailure();
            } else {
                int task = Integer.parseInt(pieces[1]); // get the task number
                if (task > taskList.noOfTasks) {
                    ui.uncreatedTask(); // task has not been created
                } else {
                    Task cur = taskList.list.get(task - 1);
                    cur.markAsDone();
                    ui.markDoneSuccessful(cur);
                }
            }
            Storage.updateTasks(taskList.getNoOfTasks(), taskList.list, filePath);
        } else if (pieces[0].equals("delete")) { // delete command
            if (pieces.length == 1) {
                ui.incompleteDeleteCommand();
            } else {
                int num = Integer.valueOf(pieces[1]);
                if (num > taskList.noOfTasks) {
                    ui.uncreatedTask();
                } else {
                    Task removed = taskList.list.get(num - 1);
                    taskList.deleteTask(removed);
                    ui.deleteSuccessful(removed, taskList);
                }
            }
            Storage.updateTasks(taskList.getNoOfTasks(), taskList.list, filePath);
        } else {
            if (pieces.length == 1) {
                switch (pieces[0]) {
                case "todo":
                    ui.missingDescription("todo");
                    break;

                case "deadline":
                    ui.missingDescription("deadline");
                    break;

                case "event":
                    ui.missingDescription("event");
                    break;

                default:
                    ui.unknownCommand();
                    break;
                }
            } else {
                Task t = new Task("");
                String[] array;
                switch (pieces[0]) {
                    case "todo":
                        t = new ToDo(pieces[1]);
                        break;

                    case "deadline":
                        array = pieces[1].split("/by ");
                        if (array.length == 1) {
                            ui.missingDeadline();
                        } else {
                            t = new Deadline(array[0], array[1]);
                        }
                        break;


                    case "event":
                        array = pieces[1].split("/at ");
                        if (array.length == 1) {
                            ui.missingEventTime();
                        } else {
                            t = new Deadline(array[0], array[1]);
                        }
                        break;

                    default:
                        break;
                }
                if (t.description != "") {
                    taskList.addTask(t);
                    ui.addSuccessful(t, taskList);
                    Storage.updateTasks(taskList.getNoOfTasks(), taskList.list, filePath);
                }

            }
        }
    }
}




