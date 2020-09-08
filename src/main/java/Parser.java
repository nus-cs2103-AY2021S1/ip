/**
 * Represents a parser that deals with making sense of the user command.
 */
public class Parser {
    /**
     * How the parser will respond to users' command.
     * @param command Full command by the user.
     * @param ui Ui used in responding.
     * @param taskList Task list referred to in the interaction.
     * @param filePath The relative path to assigned file for reading
     *                 and writing of data.
     * @return String Response to be returned
     */
    public static String respond(String command, Ui ui, TaskList taskList, String filePath) {
        String[] pieces = command.split(" ", 2);
        if (command.equals("bye")) { // terminating command
            return ui.bye();
        } else if (command.equals("list")) { // listing command
            if (taskList.list.isEmpty()) {
                return ui.emptyList();
            } else {
                return ui.returnAll(taskList);
            }
        } else if (pieces[0].equals("find")) { // listing command
            if (taskList.list.isEmpty()) {
                return ui.emptyList();
            } else {
                return ui.returnRelevant(taskList, pieces[1]);
            }
        } else if (pieces[0].equals("done")) { // Mark Done task command
            if (pieces.length == 1) { // task number is missing
                return ui.markDoneFailure();
            } else {
                int task = Integer.parseInt(pieces[1]); // get the task number
                if (task > taskList.noOfTasks) {
                    return ui.uncreatedTask(); // task has not been created
                } else {
                    Task cur = taskList.list.get(task - 1);
                    cur.markAsDone();
                    Storage.updateTasks(taskList.getNoOfTasks(), taskList.list, filePath);
                    return ui.markDoneSuccessful(cur);
                }
            }
        } else if (pieces[0].equals("delete")) { // delete command
            if (pieces.length == 1) {
                return ui.incompleteDeleteCommand();
            } else {
                int num = Integer.valueOf(pieces[1]);
                if (num > taskList.noOfTasks) {
                    return ui.uncreatedTask();
                } else {
                    Task removed = taskList.list.get(num - 1);
                    taskList.deleteTask(removed);
                    Storage.updateTasks(taskList.getNoOfTasks(), taskList.list, filePath); // update storage
                    return ui.deleteSuccessful(removed, taskList);
                }
            }
        } else {
            if (pieces.length == 1) {
                String stringToReturn = "";
                switch (pieces[0]) {
                case "todo":
                    stringToReturn = ui.missingDescription("todo");
                    break;

                case "deadline":
                    stringToReturn = ui.missingDescription("deadline");
                    break;

                case "event":
                    stringToReturn = ui.missingDescription("event");
                    break;

                default:
                    stringToReturn = ui.unknownCommand();
                    break;
                }
                return stringToReturn;
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
                    Storage.updateTasks(taskList.getNoOfTasks(), taskList.list, filePath);
                    return ui.addSuccessful(t, taskList);
                } else {
                    return "";
                }

            }
        }
    }
}




