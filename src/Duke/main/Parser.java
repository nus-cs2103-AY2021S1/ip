package Duke.main;

import Duke.exception.DukeException;
import Duke.storage.EditFile;
import Duke.storage.ReadFile;
import Duke.storage.WriteIn;
import Duke.task.*;

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
     * This is to initialize the Parser.
     */
    public Parser() {
        taskList = new TaskList<>();
    }

    /**
     * This method is to refresh the taskList.
     */
    public static void reloadTaskList() {
        taskList = new TaskList<>();
        new ReadFile(Directory.FILEDIRECTORY.toString()).readFile();
    }

    /**
     * Return true if the input from the
     * user is "bye".
     *
     * @param inputFromClient Input from the
     *                        user.
     * @return Return true if input is "bye".
     */
    public boolean isEnd(String inputFromClient) {
        return inputFromClient.equals(Status.BYE.name().toLowerCase());
    }

    /**
     * The method is to run the Parser.
     *
     * @param order The order is the string after
     *              being shortened by Formating.shorten().
     */
    public static void run(String order) {

        extract = extract(order);
        reloadTaskList();

        if (order.equals(Status.LIST.name().toLowerCase())) {
            System.out.println(
                    new Formating<>(Status.LIST.toString() + taskList)
            );
        } else {


            if (extract[command].equals(Status.DONE.name().toLowerCase())) {
                done();
            } else if (extract[command].equals(Status.DELETE.name().toLowerCase())){
                delete();
            } else if (extract[command].equals((Status.FIND.name().toLowerCase()))) {
                find(extract[taskDetail]);
            } else if (order.length() == 0) { }

            else {
                identifier();
            }
        }
    }

<<<<<<< HEAD
    public static void done() {
=======
    /**
     * This method is to set done the corresponding
     * task on both the taskList and Duke.txt.
     *
     * @param order The order is the string after
     *              being shortened by Formating.shorten().
     */
    public static void done(String order) {
>>>>>>> branch-A-JavaDoc
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
                        new Formating<>(new Response(Status.DONE.toString() + task)));
            }
        } catch (NumberFormatException e) {
            DukeException.numberFormatException();
        }
    }

<<<<<<< HEAD
    public static void delete() {
=======
    /**
     * This method is to delete the corresponding
     * task on both the taskList and Duke.txt.
     *
     * @param order The order is the string after
     *              being shortened by Formating.shorten().
     */
    public static void delete(String order) {
>>>>>>> branch-A-JavaDoc
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
                        Status.DELETE.toString() +
                                task + "\n" +
                                String.format
                                        (Status.REPORT.toString(), taskList.getTaskList().size());

                System.out.println(
                        new Formating<>(new Response(response)));
            }
        } catch (NumberFormatException e) {
            DukeException.numberFormatException();
        }
    }

<<<<<<< HEAD
    public static void find(String content) {
        taskList = new TaskList<>();
        ReadFile readFile = new ReadFile(Directory.FILEDIRECTORY.toString());
        readFile.matchContent(content);
        System.out.println(
                new Formating<>(taskList)
        );
    }

    private static String[] extract(String description) {
=======
    /**
     * This method is to identify the type of Tasks
     * from the description passed down from the upper
     * level, which is essentially the input from the
     * user.
     *
     * @param description The description passed down
     *                    from the upper level, which
     *                    is essentially the input from
     *                    the user.
     */
    public static void identifier(String description) {
>>>>>>> branch-A-JavaDoc
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
        extract[taskDetail] = new Formating<>(
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
            extract[taskTime] = new Formating<>(
                    description
                            .substring(separator + 1)
                    )
                    .shorten()
                    .getContent();
        }
        return extract;
    }

    public static void identifier() {
        String identity = extract[command];
        String detail = extract[taskDetail];
        String time = extract[taskTime];
        System.out.println(identity);

        //to check if the input is not a todo or event or deadline
        if (!identity.equals(Status.TODO.toString())
                & !identity.equals(Status.EVENT.toString())
                & !identity.equals(Status.DEADLINE.toString())) {
            DukeException.inputFormatException();
            return;
        }

        //situation that there is no detail of the task, throw error
        if (detail == null) {
            DukeException.emptyTaskException();
            return;
        }

        Task task;
        if (identity.equals(Status.TODO.toString())) {

            task = new Todo(detail);

        } else {
            try {
                Time date = new Time(time);

                if (identity.equals(Status.DEADLINE.toString())) {
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
        Formating<Task> formatedResponse =
                new Formating<>(task);
        System.out.println(formatedResponse);
    }



}
