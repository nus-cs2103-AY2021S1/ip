package com.duke.parser;

import com.duke.tasks.Deadlines;
import com.duke.tasks.Events;
import com.duke.tasks.Task;
import com.duke.tasks.ToDos;
import com.duke.exceptions.DukeException;

import java.time.LocalDate;

public class Parser {

    /**
     * Returns boolean on whether format is correct for a 'done' command.
     * If format is wrong, returns false, else returns true.
     *
     * @param input Input command to check format.
     * @return boolean.
     */
    public static boolean isDone(String input) {
        String[] inputArr = input.split(" ");
        return inputArr.length == 2
                && input.substring(0, 4).equals("done")
                && isNumber(input.substring(5,6));
    }

    /**
     * Returns boolean on whether format is correct for a 'delete' command.
     * If format is wrong, returns false, else returns true.
     *
     * @param input Input command to check format.
     * @return boolean.
     */
    public static boolean isDelete(String input) {
        String[] inputArr = input.split(" ");
        return inputArr.length == 2
                && input.substring(0, 6).equals("delete")
                && isNumber(input.substring(7,8));
    }

    private static boolean isNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns boolean on whether format is correct for a create task command.
     * This includes checks for 'todo', 'deadline' or 'event' tasks.
     * If format is wrong, returns false, else returns true.
     *
     * @param input Input command to check format.
     * @return boolean.
     */
    public static boolean correctInputFormat(String input) {
        String[] inputArr = input.split(" ", 2);
        if (inputArr.length == 1) {
            return false;
        }
        //correct todo format
        boolean todoBool = inputArr.length == 2 && inputArr[0].equals("todo");

        String taskWithDate = inputArr[1];
        String[] taskAndDateArr = taskWithDate.split(" /");
        //correct deadline format
        boolean deadlineBool = taskAndDateArr.length == 2 && inputArr[0].equals("deadline");

        //correct event format
        boolean eventBool = taskAndDateArr.length == 2 && inputArr[0].equals("event");

        return todoBool || deadlineBool || eventBool;
    }

    /**
     * Parses a string saved from Storage into a Task Object.
     * This includes 'todo', 'deadline' or 'event' tasks.
     * If format is wrong, throws DukeException.
     *
     * @param taskString Input taskString to check format.
     * @return Task if taskString is in correct format.
     * @throws DukeException if format is wrong.
     */
    public static Task parseTask(String taskString) throws DukeException {
        String[] taskStringArr = taskString.split(" - ");
        String taskCode = taskStringArr[0];
        String isDoneStr = taskStringArr[1];
        boolean isDone = isDoneStr.equals("1") ? true : false;
        String task = taskStringArr[2];

        //if toDo item
        if (taskCode.equals("T")) {
            ToDos todo = new ToDos(task, isDone);
            return todo;
            //if deadline item
        } else if (taskCode.equals("D")) {
            String date = taskStringArr[3];
            date = parseDate(date);
            Deadlines deadline = new Deadlines(task, date, isDone);
            return deadline;
            //if events item
        } else {
            String date = taskStringArr[3];
            date = parseDate(date);
            Events event = new Events(task, date, isDone);
            return event;
        }
    }

    /**
     * Parses a date saved from a Storage file.
     * Parses into LocalDate format and time in 24-hour format (HHMM) concatenated in a string.
     * For example, transforms an input "at 2/12/2019 1800" to "2019-12-02 1800".
     *
     * @param date Input date to be parsed.
     * @return String date in LocalDate format and time in 24-hour format (HHMM).
     * @throws DukeException if input format is incorrect.
     */
    public static String parseDate(String date) throws DukeException {
        //date input could be "at 2/12/2019 1800"
        //returns "2019-12-02 1800"
        try {
            String errMessage = "Sorry! Format of date is wrong. " +
                    "Example input should be " +
                    "deadline return book /by 2/12/2019 1800. " +
                    "Please fix storage file before loading Duke again.";

            String[] strArr = date.split(" ");
            if (strArr.length != 3 && strArr.length != 2) {
                throw new DukeException(errMessage);
            }

            String[] dateArr = new String[0];
            int dateIndex = -1;
            int timeIndex = -1;
            if (strArr.length == 2) {
                dateArr = strArr[0].split("/");
                dateIndex = 0;
                timeIndex = 1;
            } else if (strArr.length == 3) {
                dateArr = strArr[1].split("/");
                dateIndex = 1;
                timeIndex = 2;
            }

            if (dateArr.length != 3) {
                throw new DukeException(errMessage);
            }

            //if day < 10, add 0 in front
            if (Integer.parseInt(dateArr[0]) < 10) {
                dateArr[0] = "0" + dateArr[0];
            }
            //transform 2/12/2019 to 2019-12-02
            date = dateArr[2] + "-" + dateArr[1] + "-" + dateArr[0];
            strArr[dateIndex] = date;

            String res = strArr[dateIndex] + " " + strArr[timeIndex];
            return res;
        } catch (DukeException e) {
            throw e;
        }
    }

    /**
     * Parses a date into a format savable to Storage file.
     * Parses from LocalDate format to Storage file format.
     * For example, transforms an input "2019-12-02" to "2/12/2019".
     *
     * @param date Input LocalDate object to be parsed.
     * @return String date in Storage file format.
     */
    public static String parseDateToSaveFormat(LocalDate date) {
        int day = date.getDayOfMonth();
        int month = date.getMonthValue();
        String monthStr = Integer.toString(month);
        int year = date.getYear();
        if (month < 10) {
            monthStr = "0" + monthStr;
        }
        return day + "/" + monthStr + "/" + year;
    }

    /**
     * Parses a task command into an array containing the task and date.
     * Transforms an input "event halloween party /at 2/12/2019 1800" to "[event halloween party, at 2/12/2019 1800]".
     *
     * @param task Input task to be parsed.
     * @return String[] Array of strings containing task and date.
     * @throws DukeException throws DukeException if format of input command is wrong.
     */
    public static String[] splitTaskAndDate(String task) throws DukeException {
        try {
            // date = "at 2/12/2019 1800"
            String date = task.substring(task.indexOf("/") + 1, task.length());
            // date = 2019-12-02 1800
            date = parseDate(date);

            // task = project meeting
            task = task.substring(0, task.indexOf("/") - 1);
            String[] res = new String[]{task, date};
            return res;
        } catch (DukeException e) {
            throw e;
        }
    }
}
