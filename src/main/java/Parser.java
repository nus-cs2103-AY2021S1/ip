import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * Represents a parser that deals with making sense of the user command.
 */
public class Parser {

    /**
     * How the parser will respond to users' command.
     *
     * @param command  Full command by the user.
    // * @param ui       Ui used in responding.
     * @param taskList Task list referred to in the interaction.
     * @param filePath The relative path to assigned file for reading
     *                 and writing of data.
     * @return String Response to be returned
     */
    public static String respond(String command, TaskList taskList, String filePath) {
        String[] pieces = command.split(" ", 2);
        assert (pieces[0] != null) : "Incorrect splitting.";
        if (command.equals("bye")) { // terminating command
            return Ui.bye();
        } else if (command.equals("help")) {
            return Ui.getHelp();
        } else if (command.equals("list")) { // listing command
            if (taskList.list.isEmpty()) {
                return Ui.emptyList();
            } else {
                Storage storage = new Storage(filePath);
                TaskList tasks;
                String s = "";
                try {
                    tasks = new TaskList(storage.load());
                    s = Ui.returnAllTasks(tasks);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                return s;

            }
        } else if (pieces[0].equals("find")) { // listing command
            if (taskList.list.isEmpty()) {
                return Ui.emptyList();
            } else {
                return Ui.returnRelevantTasks(taskList, pieces[1]);
            }
        } else if (pieces[0].equals("done")) { // Mark Done task command
            if (pieces.length == 1) { // task number is missing
                return Ui.markDoneFailure();
            } else {
                try {
                    int task = Integer.parseInt(pieces[1]); // get the task number
                    if (task > taskList.noOfTasks) {
                        return Ui.uncreatedTask(); // task has not been created
                    } else {
                        assert (taskList.list.get(task - 1) != null) : "Incorrect index.";
                        Task cur = taskList.list.get(task - 1);
                        if (cur.getStatus()) {
                            return Ui.duplicatedMarkDone();
                        }
                        cur.markAsDone();
                        Storage.updateTasks(taskList.getNoOfTasks(), taskList.list, filePath);
                        return Ui.markDoneSuccessful(cur);
                    }
                } catch (NumberFormatException e) {
                    return Ui.incorrectDoneFormat();
                }
            }
        } else if (pieces[0].equals("delete")) { // delete command
            if (pieces.length == 1) {
                return Ui.incompleteDeleteCommand();
            } else {
                int num = Integer.valueOf(pieces[1]);
                if (num > taskList.noOfTasks) {
                    return Ui.uncreatedTask();
                } else {
                    assert (taskList.list.get(num - 1) != null) : "Incorrect index.";
                    Task removed = taskList.list.get(num - 1);
                    taskList.deleteTask(removed);
                    Storage.updateTasks(taskList.getNoOfTasks(), taskList.list, filePath); // update storage
                    return Ui.deleteSuccessful(removed, taskList);
                }
            }
        } else if (pieces[0].equals("tag")) {
            String[] p = pieces[1].split(" ");
            int task = Integer.parseInt(p[0]); // get the task number
            if (task > taskList.noOfTasks) {
                return Ui.uncreatedTask(); // task has not been created
            } else {
                assert (taskList.list.get(task - 1) != null) : "Incorrect index.";
                Task cur = taskList.list.get(task - 1);
                String tag = p[1];
                cur.setTag(tag);
                Storage.updateTasks(taskList.getNoOfTasks(), taskList.list, filePath);
                return Ui.setTagSuccessful(tag,cur);
            }
        } else {
                if (pieces.length == 1) {
                    String s = Parser.incompleteCommand(pieces[0]);
                    return s;
                } else {
                    return successfulCommand(taskList, pieces[0], pieces[1], filePath);
                }
            }
        }


    private static String successfulCommand ( TaskList taskList, String firstCommandWord,
                                            String secondCommandWord, String filePath){
        Task t = new Task("");
        String[] array;
        String s = "";
        switch (firstCommandWord) {
            case "todo":
                t = new ToDo(secondCommandWord);
                break;

            case "deadline":
                array = secondCommandWord.split("/by ");
                if (array.length == 1) {
                    Ui.missingDeadline();
                } else {
                    t = new Deadline(array[0], array[1]);
                }
                break;

            case "event":
                array = secondCommandWord.split("/at ");
                if (array.length == 1) {
                    Ui.missingEventTime();
                } else {
                    t = new Event(array[0], array[1]);
                }
                break;

            default:
                break;
        }
        if (t.description != "") {
            taskList.addTask(t);
            Storage.updateTasks(taskList.getNoOfTasks() , taskList.list, filePath);
            return Ui.addSuccessful(t, taskList.getNoOfTasks());
        } else {
            return s;
        }
    }

    private static String incompleteCommand(String command) {
        String stringToReturn;
        switch (command) {
            case "todo":
                stringToReturn = Ui.missingDescription("todo");
                break;

            case "deadline":
                stringToReturn = Ui.missingDescription("deadline");
                break;

            case "event":
                stringToReturn = Ui.missingDescription("event");
                break;

            default:
                stringToReturn = Ui.unknownCommand();
                break;
        }
        assert (stringToReturn != "") : "Switch statement error.";
        return stringToReturn;
    }
}




