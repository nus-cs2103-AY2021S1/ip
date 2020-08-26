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
        reloadTaskList();
        if (order.equals(Status.LIST.name().toLowerCase())) {
            System.out.println(
                    new Formating<>(Status.LIST.toString() + taskList)
            );
        } else {
            if (order.length() >= 6
                    && order.substring(0, 4).equals(Status.DONE.name().toLowerCase())) {
                done(order);
            } else if (order.length() >= 8
                    && order.substring(0, 6).equals(Status.DELETE.name().toLowerCase())){
                delete(order);
            } else if (order.length() == 0) { }

            else {
                identifier(order);
            }
        }
    }

    /**
     * This method is to set done the corresponding
     * task on both the taskList and Duke.txt.
     *
     * @param order The order is the string after
     *              being shortened by Formating.shorten().
     */
    public static void done(String order) {
        try {
            int num =
                    Integer.parseInt(new Formating<>(order.substring(4)).shorten().getContent());

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

    /**
     * This method is to delete the corresponding
     * task on both the taskList and Duke.txt.
     *
     * @param order The order is the string after
     *              being shortened by Formating.shorten().
     */
    public static void delete(String order) {
        try {
            int num =
                    Integer.parseInt(new Formating<>(order.substring(6)).shorten().getContent());

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
        int len = description.length();
        int pointer = 0;

        while (pointer < len && description.charAt(pointer) != ' ') {
            pointer++;
        }

        //the identity of the task has been found.
        String idetity = description.substring(0, pointer);

        //to check if the input is not a todo or event or deadline
        if (!idetity.equals(Status.TODO.toString())
                & !idetity.equals(Status.EVENT.toString())
                & !idetity.equals(Status.DEADLINE.toString())) {
            DukeException.inputFormatException();
            return;
        }


        //pointer goes on to find details of the task
        int separator = pointer;
        while (separator < len && description.charAt(separator) != '/') {
            separator++;
        }

        //situation that there is no detail of the task, throw error
        if (pointer == separator) {
            DukeException.emptyTaskException();
            return;
        }

        //details of the task is found
        String detail = new Formating<>(
                description
                        .substring(pointer + 1, separator)
        )
                .shorten()
                .getContent();

        Task task;
        if (idetity.equals(Status.TODO.toString())) {

            task = new Todo(detail);

        } else {

            while (separator < len && description.charAt(separator) != ' ') {
                separator++;
            }

            String time;

            if (separator < len - 1) {
                time = new Formating<>(
                        description
                                .substring(separator + 1)
                )
                        .shorten()
                        .getContent();
            } else {
                DukeException.timeMissingException();
                return;

            }

            try {
                Time date = new Time(time);

                if (idetity.equals(Status.DEADLINE.toString())) {
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
