package com.duke.parser;

import java.time.LocalDate;
import java.util.Arrays;

import com.duke.exceptions.DukeException;
import com.duke.tasks.Deadlines;
import com.duke.tasks.Events;
import com.duke.tasks.RecurringDeadlines;
import com.duke.tasks.RecurringEvents;
import com.duke.tasks.RecurringTask;
import com.duke.tasks.RecurringToDos;
import com.duke.tasks.Task;
import com.duke.tasks.ToDos;


/**
 * Parser which is responsible in parsing all instruction from user.
 */

public class Parser {
    private static final String PARSE_DATE_ERROR_MESSAGE = "Sorry! Format of date is wrong. "
            + "Example input should be "
            + "deadline return book /by 2/12/2019 1800. "
            + "Please fix storage file before loading Duke again.";

    /**
     * Returns boolean on whether format is correct for a 'help command.
     * If format is wrong, returns false, else returns true.
     *
     * @param input Input command to check format.
     * @return boolean.
     */
    public static boolean isHelp(String input) {
        return input.equals("help");
    }

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
                && isNumber(input.substring(5, 6));
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
                && isNumber(input.substring(7, 8));
    }

    /**
     * Returns boolean on whether format is correct for a 'find' command.
     * If format is wrong, returns false, else returns true.
     *
     * @param input Input command to check format.
     * @return boolean.
     */
    public static boolean isFind(String input) {
        String[] inputArr = input.split(" ", 2);
        return inputArr[0].equals("find") && inputArr.length == 2;
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
     * Returns boolean on whether command is to add tasks or not.
     * This includes checks for 'todo', 'deadline' or 'event' tasks.
     * If format is wrong, returns false, else returns true.
     *
     * @param input Input command to check format.
     * @return boolean.
     */
    public static boolean isAddTask(String input) {
        String[] inputArr = input.split(" ", 2);
        if (inputArr.length == 1) {
            return false;
        }
        boolean isTodoBool = isToDoFormat(inputArr);
        boolean isDeadlineBool = isDeadlineFormat(inputArr);
        boolean isEventBool = isEventFormat(inputArr);
        return isTodoBool ^ isDeadlineBool ^ isEventBool;
    }

    private static boolean isToDoFormat(String[] inputArr) {
        boolean doesTaskExists = !inputArr[1].equals("");
        boolean isLengthTwo = inputArr.length == 2;
        boolean isToDo = inputArr[0].equals("todo");
        return isLengthTwo && isToDo && doesTaskExists;
    }

    private static boolean isDeadlineFormat(String[] inputArr) {
        return inputArr[0].equals("deadline");
    }

    private static boolean isEventFormat(String[] inputArr) {
        return inputArr[0].equals("event");
    }

    /**
     * Returns boolean on whether command is to add recurring tasks or not.
     * Example command is 'recurring todo Do Laundry /weekly'
     * Example command is 'recurring deadline Pay Bills /by 9/07/2019 1800 /monthly'
     * Example command is 'recurring event Wedding Anniversary /at 30/10/2020 1800 /yearly'
     * If format is wrong, returns false, else returns true.
     *
     * @param input Input command to check format.
     * @return boolean.
     */
    public static boolean isRecurringTask(String input) {
        String[] inputArr = input.split(" ", 2);
        if (inputArr.length != 2) {
            return false;
        }
        String taskAndInterval = inputArr[1];
        String[] taskAndIntervalArr = taskAndInterval.split(" /");
        System.out.println(Arrays.toString(taskAndIntervalArr));
        boolean isCorrectTaskAndIntervalFormat = taskAndIntervalArr.length == 2 || taskAndIntervalArr.length == 3;
        boolean isRecurring = inputArr[0].equals("recurring");
        if (!isRecurring && !isCorrectTaskAndIntervalFormat) {
            return false;
        }

        String taskType = taskAndIntervalArr[0].split(" ")[0];
        boolean isValidInterval = true;
        if (!taskType.equals("todo")) {
            isValidInterval = checkRecurringInterval(taskAndIntervalArr[2]);
        }
        return isRecurring && isValidInterval;
    }

    private static boolean checkRecurringInterval(String timePeriod) {
        String timePeriodLowerCase = timePeriod.toLowerCase();
        return timePeriodLowerCase.equals("daily")
                || timePeriodLowerCase.equals("weekly")
                || timePeriodLowerCase.equals("monthly")
                || timePeriodLowerCase.equals("yearly");
    }

    public static String getTaskType(String input) {
        return input.substring(0, input.indexOf(" "));
    }

    public static String getTask(String input) {
        return input.substring(input.indexOf(" ") + 1);
    }

    /**
     * Parses a string saved from Storage into a Task Object.
     * This includes 'todo', 'deadline', 'event' and recurring tasks.
     * If format is wrong, throws DukeException.
     *
     * @param taskString Input taskString to check format.
     * @return Task if taskString is in correct format.
     * @throws DukeException if format is wrong.
     */
    public static Task parseTask(String taskString) throws DukeException {
        String[] taskStringArr = taskString.split(" - ");
        String taskCode = taskStringArr[0];
        if (taskCode.equals("R")) {
            return parseRecurringTask(taskString);
        } else if (isNonRecurringTask(taskCode)) {
            return parseNonRecurringTask(taskString);
        } else {
            throw new DukeException("Task is of invalid input format!");
        }
    }

    private static RecurringTask parseRecurringTask(String taskString) throws DukeException {
        String[] taskStringArr = taskString.split(" - ");
        String taskCode = taskStringArr[1];
        String isDoneStr = taskStringArr[2];
        String task = taskStringArr[3];
        String interval = taskStringArr[5];
        boolean isDone = isDoneStr.equals("1");

        String recurringCode = taskStringArr[0];
        assert recurringCode.equals("R");
        assert taskCode.equals("T") ^ taskCode.equals("D") ^ taskCode.equals("E");

        if (taskCode.equals("T")) {
            return new RecurringToDos(task, isDone, interval);
        } else if (taskCode.equals("D")) {
            String date = taskStringArr[4];
            date = parseDate(date);
            return new RecurringDeadlines(task, date, isDone, interval);
        } else if (taskCode.equals("E")) {
            String date = taskStringArr[4];
            date = parseDate(date);
            return new RecurringEvents(task, date, isDone, interval);
        } else {
            throw new DukeException("Task is of invalid input format!");
        }
    }

    private static boolean isNonRecurringTask(String taskCode) {
        return taskCode.equals("T") || taskCode.equals("E") || taskCode.equals("D");
    }

    private static Task parseNonRecurringTask(String taskString) throws DukeException {
        String[] taskStringArr = taskString.split(" - ");
        String taskCode = taskStringArr[0];
        String isDoneStr = taskStringArr[1];
        String task = taskStringArr[2];
        boolean isDone = isDoneStr.equals("1");

        assert taskCode.equals("T") ^ taskCode.equals("D") ^ taskCode.equals("E");

        if (taskCode.equals("T")) {
            return new ToDos(task, isDone);
        } else if (taskCode.equals("D")) {
            String date = taskStringArr[3];
            date = parseDate(date);
            return new Deadlines(task, date, isDone);
        } else if (taskCode.equals("E")) {
            String date = taskStringArr[3];
            date = parseDate(date);
            return new Events(task, date, isDone);
        } else {
            throw new DukeException("Task is of invalid input format!");
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
        String[] strArr = date.split(" ");
        if (strArr.length != 3 && strArr.length != 2) {
            throw new DukeException(PARSE_DATE_ERROR_MESSAGE);
        }

        String[] dateArr = getDateArr(strArr);
        int dateIndex = getDateIndex(strArr);
        int timeIndex = getTimeIndex(strArr);
        if (dateArr.length != 3) {
            throw new DukeException(PARSE_DATE_ERROR_MESSAGE);
        }

        //transform 2/12/2019 to 2019-12-02
        String finalDate = reformatDate(dateArr);
        strArr[dateIndex] = finalDate;

        return strArr[dateIndex] + " " + strArr[timeIndex];
    }

    private static String reformatDate(String[] dateArr) {
        return dateArr[2] + "-" + dateArr[1] + "-" + dateArr[0];
    }

    private static int getDateIndex(String[] strArr) {
        int dateIndex = -1;
        if (strArr.length == 2) {
            dateIndex = 0;
        } else if (strArr.length == 3) {
            dateIndex = 1;
        }
        return dateIndex;
    }

    private static int getTimeIndex(String[] strArr) {
        int timeIndex = -1;
        if (strArr.length == 2) {
            timeIndex = 1;
        } else if (strArr.length == 3) {
            timeIndex = 2;
        }
        return timeIndex;
    }

    private static String[] getDateArr(String[] strArr) throws DukeException {
        try {
            String[] dateArr = new String[0];
            if (strArr.length == 2) {
                dateArr = strArr[0].split("/");
            } else if (strArr.length == 3) {
                dateArr = strArr[1].split("/");
            }

            //first index in dateArr is day of date
            dateArr[0] = parseDay(dateArr[0]);
            return dateArr;
        } catch (NumberFormatException numberFormatException) {
            throw new DukeException(PARSE_DATE_ERROR_MESSAGE);
        }
    }

    private static String parseDay(String day) {
        if (Integer.parseInt(day) < 10) {
            return "0" + day;
        }
        return day;
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
        } catch (DukeException dukeException) {
            throw dukeException;
        }
    }
}
