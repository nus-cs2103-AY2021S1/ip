package Duke.main;

import Duke.exception.DukeException;
import Duke.storage.EditFile;
import Duke.storage.ReadFile;
import Duke.storage.WriteIn;
import Duke.task.Deadline;
import Duke.task.Event;
import Duke.task.Task;
import Duke.task.TaskList;
import Duke.task.Todo;

/**
 * This class deals with the strings from the client
 * and enable the string to make sense to Duke.
 */
public class Parser {
    public static TaskList<Task> taskList;
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
    public static void run(String order) {

        extract = extract(order);
        reloadTaskList();

        if (order.equals(Command.LIST.toString())) {
            System.out.println(
                    new Format<>(Statement.LIST.toString() + taskList)
            );
        } else {

            if (order.length() > 0) {
                if (extract[command].equals(Command.DONE.toString())) {
                    done();
                } else if (extract[command].equals(Command.DELETE.toString())) {
                    delete();
                } else if (extract[command].equals((Command.FIND.toString()))) {
                    find(extract[taskDetail]);
                } else if (extract[command].equals((Command.CLEAR.toString()))) {
                    clear();
                } else {
                    identifier();
                }
            }
        }
    }

    /**
     * Sets done the corresponding
     * task on both the taskList and Duke.txt.
      */
    public static void done() {
        try {
            int num = Integer.parseInt(extract[taskDetail]);
            if (num > taskList.getTaskList().size()) {
                DukeException.numberExcessException();
            } else {
                Task task = taskList.getTaskList().get(num - 1);
                task.setDone();
                EditFile editFile = new EditFile(Directory.FILEDIRECTORY.toString());
                editFile.setTaskDone(num);

                System.out.println(
                        new Format<>(new Response(Statement.DONE.toString() + task)));
            }
        } catch (NumberFormatException e) {
            DukeException.numberFormatException();
        }
    }

    /**
     * Deletes the corresponding
     * task on both the taskList and Duke.txt.
     */
    public static void delete() {
        try {
            int num = Integer.parseInt(extract[taskDetail]);

            if (num > taskList.getTaskList().size()) {
                DukeException.numberExcessException();
            } else {
                Task task = taskList.getTaskList().get(num - 1);
                taskList.getTaskList().remove(num - 1);
                EditFile editFile = new EditFile(Directory.FILEDIRECTORY.toString());
                editFile.deleteLine(num);

                String response =
                        Statement.DELETE.toString() +
                                task +
                                FormatString.NEXTLINE.toString() +
                                String.format
                                        (Statement.REPORT.toString(), taskList.getTaskList().size());

                System.out.println(
                        new Format<>(new Response(response)));
            }
        } catch (NumberFormatException e) {
            DukeException.numberFormatException();
        }
    }

    /**
     * Finds the related content from the task details
     * of the tasks in the taskList.
     *
     * @param content The user input content.
     */
    public static void find(String content) {
        taskList = new TaskList<>();
        ReadFile readFile = new ReadFile(Directory.FILEDIRECTORY.toString());
        readFile.matchContent(content);

        Response response = new Response(
                Statement.FIND.toString() +
                taskList.toString()
        );

        System.out.println(
                new Format<>(response)
        );

    }

    /**
     * Clears all records in the file from the
     * directory in Directory class.
     */
    public static void clear() {
        EditFile deleteAll = new EditFile(Directory.FILEDIRECTORY.toString());
        deleteAll.clearFile();
        taskList = new TaskList<>();

        Response response = new Response(
                Statement.CLEAR.toString()
        );

        System.out.println(
                new Format<>(response)
        );
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
    public static void identifier() {
        String identity = extract[command];
        String detail = extract[taskDetail];
        String time = extract[taskTime];
        System.out.println(identity);

        //to check if the input is not a todo or event or deadline
        if (!identity.equals(Command.TODO.toString())
                & !identity.equals(Command.EVENT.toString())
                & !identity.equals(Command.DEADLINE.toString())) {
            DukeException.inputFormatException();
            return;
        }

        //situation that there is no detail of the task, throw error
        if (detail == null) {
            DukeException.emptyTaskException();
            return;
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
                DukeException.timeFormatException();
                return;
            }
        }
        taskList.addMemory(task);
        WriteIn data = new WriteIn(Directory.FILEDIRECTORY.toString(), true);
        data.writeToFile(task.toString());
        Format<Task> ResponseWithFormat =
                new Format<>(task);
        System.out.println(ResponseWithFormat);
    }



}
