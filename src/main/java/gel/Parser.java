package gel;

import java.io.IOException;
import java.time.LocalDateTime;
import gel.exception.GelException;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    private final Storage storage;
    private final Ui ui;
    private final TaskList taskList;

    /**
     * Construct a Parser class to parse user inputs.
     * @param storage handles storage of data
     * @param ui handles replies from gel bot
     * @param taskList contains the list of tasks
     */
    public Parser(Storage storage, Ui ui, TaskList taskList) {
        this.storage = storage;
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Ensures that user input of date and time is in the correct format.
     * If is in the correct format, translate the string into <code>LocalDateTime</code>.
     *
     * @param dateTime User's input.
     * @return <code>LocalDateTime</code> object.
     * @throws GelException If user's input is in the wrong format.
     */
    public static LocalDateTime toDateTime(String dateTime) throws GelException {
        String[] dateTimeSplit = dateTime.split(" ");
        if (dateTimeSplit.length != 2) {
            throw new GelException("    Your datetime has an invalid format... please use"
                    + " the format:YYYY-MM-DD HHMM");
        }
        String[] date = dateTimeSplit[0].split("-");
        if (date.length != 3) {
            throw new GelException("    Your date has an invalid format... please use"
                    + " the format:YYYY-MM-DD");
        }
        String time = dateTimeSplit[1];
        if (time.length() != 4) {
            throw new GelException("    Your time has an invalid format... please use"
                    + " the format:HHMM");
        }
        try {
            int year = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int dayOfMonth = Integer.parseInt(date[2]);
            int hour = Integer.parseInt(time.substring(0, 2));
            int minute = Integer.parseInt(time.substring(2));
            return LocalDateTime.of(year, month, dayOfMonth, hour, minute);
        } catch (Exception e) {
            throw new GelException("    Please make sure that your date and time "
                    + "are numbers within the calendar and 24 hour clock");
        }
    }


    /**
     * Translates user input into something understandable by the task planner.
     *
     * @param input User's input.
     * @return A string to print on the GUI.
     * @throws IOException If file could not be updated.
     * @throws GelException User enters invalid keyword.
     */
    public String parseUserInput(String input) throws IOException, GelException {

        String[] inputArr = input.split(" ");
        String keyword = inputArr[0];

        switch (keyword) {
        case "bye":
            return parseBye();
        case "list":
            return parseList();
        case "done":
            return parseDone(inputArr);
        case "delete":
            return parseDelete(inputArr);
        case "deadline":
            return parseDeadline(input);
        case "event":
            return parseEvent(input);
        case "todo":
            return parseTodo(inputArr.length, input);
        case "find":
            return parseFind(input);
        default:
            throw new GelException();
        }
    }

    private String parseBye() throws IOException {
        storage.updateFile(taskList);
        return ui.farewellMessage();
    }

    private String parseList() {
        return taskList.showListOfTask();
    }

    private String parseDone(String[] inputArr) throws GelException {
        return taskList.markTaskAsDone(inputArr);
    }

    private String parseDelete(String[] inputArr) throws GelException {
        return taskList.deleteTask(inputArr);
    }

    private String parseDeadline(String input) throws GelException {
        int dateIndex = input.lastIndexOf("/");
        if (dateIndex == -1) {
            throw new GelException("    Bruh you need the /by tag for deadlines");
        }
        String[] dateDetails = input.substring(dateIndex).split(" ");
        String checkBy = dateDetails[0];
        if (!checkBy.equals("/by")) {
            throw new GelException("    Bruh you need the /by tag for deadlines");
        } else if (dateDetails.length <= 1) {
            throw new GelException("    Bruh you left out the deadline");
        }
        return taskList.addDeadline(input, dateIndex);
    }

    private String parseEvent(String input) throws GelException {
        int dateIndex = input.lastIndexOf("/");
        if (dateIndex == -1) {
            throw new GelException("    Bruh you need the /at tag for events");
        }
        String[] dateDetails = input.substring(dateIndex).split(" ");
        String checkAt = dateDetails[0];
        if (!checkAt.equals("/at")) {
            throw new GelException("    Bruh you need the /at tag for events");
        } else if (dateDetails.length <= 1) {
            throw new GelException("    Bruh you left out the event date");
        }
        return taskList.addEvent(input, dateIndex);
    }

    private String parseTodo (int arrLength, String input) throws GelException {
        if (arrLength <= 1) {
            throw new GelException("    Yo tell me what you want todo");
        }
        return taskList.addTodo(input);
    }

    private String parseFind(String input) {
        String[] findInputArr = input.split(" ", 2);
        String findKeyword = findInputArr[1];
        return taskList.findDescription(findKeyword);
    }
}
