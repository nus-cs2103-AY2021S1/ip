package duke.tools;

import duke.exception.DukeException;
import duke.main.Command;
import duke.main.Directory;
import duke.main.Statement;
import duke.storage.EditFile;
import duke.storage.ReadFile;
import duke.storage.WriteIn;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * This class deals with the strings from the client
 * and enable the string to make sense to Duke.
 */
public class Parser {
    private static TaskList<Task> taskList;
    private static String[] extract = new String[3];
    private static final int command = 0;
    private static final int taskDetail = 1;
    private static final int taskTime = 2;

    /**
     * Initializes the Parser.
     */
    public Parser() {
        taskList = new TaskList<>();
    }

    /**
     * Returns the static attribute of taskList.
     *
     * @return the static attribute of taskList.
     */
    public static TaskList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Refreshes the taskList.
     */
    public static void reloadTaskList() {
        taskList = new TaskList<>();
        new ReadFile(Directory.FILEDIRECTORY.toString()).readFile();
    }

    /**
     * Returns true if the input from the
     * user is "bye".
     *
     * @param inputFromClient Input from the
     *                        user.
     * @return Returns true if input is "bye".
     */
    public boolean isEnd(String inputFromClient) {
        return inputFromClient.equals(Command.BYE.toString());
    }

    /**
     * Runs the Parser.
     *
     * @param order The order is the string after
     *              being shortened by Format.shorten().
     */
    public static String run(String order) {

        extract = extract(order);
        reloadTaskList();

        if (order.equals(Command.LIST.toString())) {
            return new Format<>(Statement.LIST.toString() + taskList).toString();
        } else {

            if (order.length() > 0) {
                if (extract[command].equals(Command.DONE.toString())) {
                    return done();
                } else if (extract[command].equals(Command.DELETE.toString())) {
                    return delete();
                } else if (extract[command].equals((Command.FIND.toString()))) {
                    return find(extract[taskDetail]);
                } else if (extract[command].equals((Command.CLEAR.toString()))) {
                    return clear();
                } else {
                    return identifier();
                }
            } else {
                //May cause error.
                return null;
            }
        }
    }

    /**
     * Sets done the corresponding
     * task on both the taskList and Duke.txt.
     */
    public static String done() {
        try {
            int num = Integer.parseInt(extract[taskDetail]);
            if (num > taskList.getTaskList().size()) {
                return DukeException.numberExcessException();
            } else {
                Task task = taskList.getTaskList().get(num - 1);
                task.setDone();
                EditFile editFile = new EditFile(Directory.FILEDIRECTORY.toString());
                editFile.setTaskDone(num);
                return new Format<>(new Response(Statement.DONE.toString() + task)).toString();
            }
        } catch (NumberFormatException e) {
            return DukeException.numberFormatException();
        }
    }

    /**
     * Deletes the corresponding
     * task on both the taskList and Duke.txt.
     */
    public static String delete() {
        try {
            int num = Integer.parseInt(extract[taskDetail]);

            if (num > taskList.getTaskList().size()) {
                return DukeException.numberExcessException();
            } else {
                Task task = taskList.getTaskList().get(num - 1);
                taskList.getTaskList().remove(num - 1);
                EditFile editFile = new EditFile(Directory.FILEDIRECTORY.toString());
                editFile.deleteLine(num);

                String response =
                        Statement.DELETE.toString()
                                + task
                                + FormatString.NEXTLINE.toString()
                                + String.format
                                (Statement.REPORT.toString(), taskList.getTaskList().size());
                return new Format<>(new Response(response)).toString();
            }
        } catch (NumberFormatException e) {
            return DukeException.numberFormatException();
        }
    }

    /**
     * Finds the related content from the task details
     * of the tasks in the taskList.
     *
     * @param content The user input content.
     */
    public static String find(String content) {
        try {
            taskList = new TaskList<>();
            ReadFile readFile = new ReadFile(Directory.FILEDIRECTORY.toString());
            readFile.matchContent(content);

            Response response = new Response(
                    Statement.FIND.toString()
                            + taskList.toString()
            );

            return new Format<>(response).toString();
        } catch (Exception e) {
            return DukeException.findDetailMissingException();
        }

    }

    /**
     * Clears all records in the file from the
     * directory in Directory class.
     */
    public static String clear() {
        EditFile deleteAll = new EditFile(Directory.FILEDIRECTORY.toString());
        deleteAll.clearFile();
        taskList = new TaskList<>();

        Response response = new Response(
                Statement.CLEAR.toString()
        );

        return new Format<>(response).toString();
    }

    /**
     * Extracts out the type of command,
     * and possibly the task detail and
     * task time if the command is to
     * add a new task.
     *
     * @param description The user input.
     * @return Returns a string array whose
     *         length is 3 and first element
     *         is the command string, second
     *         element is the task detail,
     *         and the third element is the
     *         task time.
     */
    private static String[] extract(String description) {
        int len = description.length();
        int pointer = 0;

        while (pointer < len && description.charAt(pointer) != ' ') {
            pointer++;
        }

        //the command of the description has been found.
        extract[command] = description.substring(0, pointer);

        int separator = pointer;
        while (separator < len && description.charAt(separator) != '/') {
            separator++;
        }

        //situation that there is no detail of the task, return
        if (pointer == separator) {
            return extract;
        }

        //details of the description is found
        extract[taskDetail] = new Format<>(
                description
                        .substring(pointer + 1, separator)
        )
                .shorten()
                .getContent();

        while (separator < len && description.charAt(separator) != ' ') {
            separator++;
        }

        //time of the description is found
        if (separator < len - 1) {
            extract[taskTime] = new Format<>(
                    description
                            .substring(separator + 1)
            )
                    .shorten()
                    .getContent();
        }
        return extract;
    }

    /**
     * Identifies the type of Tasks
     * from the description passed down from the upper
     * level, which is essentially the input from the
     * user.
     */
    public static String identifier() {
        String identity = extract[command];
        String detail = extract[taskDetail];
        String time = extract[taskTime];
        System.out.println(identity);

        //to check if the input is not a todo or event or deadline
        if (!identity.equals(Command.TODO.toString())
                & !identity.equals(Command.EVENT.toString())
                & !identity.equals(Command.DEADLINE.toString())) {
            return DukeException.inputFormatException();
        }

        //situation that there is no detail of the task, throw error
        if (detail == null) {
            return DukeException.emptyTaskException();
        }

        Task task;
        if (identity.equals(Command.TODO.toString())) {

            task = new Todo(detail);

        } else {
            try {
                Time date = new Time(time);

                if (identity.equals(Command.DEADLINE.toString())) {
                    task = new Deadline(detail, date.toString());
                } else {
                    task = new Event(detail, date.toString());
                }
            } catch (Exception e) {
                return DukeException.timeFormatException();
            }
        }
        taskList.addMemory(task);
        WriteIn data = new WriteIn(Directory.FILEDIRECTORY.toString(), true);
        data.writeToFile(task.toString());
        Format<Task> responseWithFormat =
                new Format<>(task);
        return responseWithFormat.toString();
    }



}
