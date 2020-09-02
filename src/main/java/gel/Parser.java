package gel;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import gel.exception.GelException;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Ensures that user input of date and time is in the correct format.
     * If is in the correct format, translate the string into <code>LocalDateTime</code>.
     *
     * @param dateTime User's input
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
     * @param sc Scanner.
     * @param storage Storage to store date.
     * @param ui Ui to communicate with user.
     * @param taskList List of tasks.
     * @throws IOException If file could not be updated.
     */
    public static void parseUserInput(Scanner sc, Storage storage, Ui ui, TaskList taskList) throws IOException {
        label:
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] inputArr = input.split(" ");
            String keyword = inputArr[0];
            try {
                switch (keyword) {
                case "bye": { //bye
                    storage.updateFile(taskList);
                    ui.farewellMessage();
                    break label;
                }
                case "list": { //list
                    taskList.showListOfTask();
                    break;
                }
                case "done": { //done
                    taskList.doneTask(input);
                    break;
                }
                case "delete": { //delete
                    if (inputArr.length <= 1) {
                        throw new GelException("    Yo tell me what you want to delete la");
                    }
                    try {
                        taskList.deleteTask(inputArr[1]);
                        break;
                    } catch (Exception e) {
                        throw new GelException("    Yoyoyo please input a valid number after delete");
                    }
                }
                case "deadline": { //deadline
                    int dateIndex = input.lastIndexOf("/");
                    if (dateIndex == -1) {
                        throw new GelException("    Bruh you need the /by tag for deadlines");
                    } else {
                        String[] dateDetails = input.substring(dateIndex).split(" ");
                        String checkBy = dateDetails[0];
                        if (!checkBy.equals("/by")) {
                            throw new GelException("    Bruh you need the /by tag for deadlines");
                        } else if (dateDetails.length <= 1) {
                            throw new GelException("    Bruh you left out the deadline");
                        }
                    }
                    taskList.addDeadline(input, dateIndex);
                    break;
                }
                case "event": { //event
                    int dateIndex = input.lastIndexOf("/");
                    if (dateIndex == -1) {
                        throw new GelException("    Bruh you need the /at tag for events");
                    } else {
                        String[] dateDetails = input.substring(dateIndex).split(" ");
                        String checkAt = dateDetails[0];
                        if (!checkAt.equals("/at")) {
                            throw new GelException("    Bruh you need the /at tag for events");
                        } else if (dateDetails.length <= 1) {
                            throw new GelException("    Bruh you left out the event date");
                        }
                    }
                    taskList.addEvent(input, dateIndex);
                    break;
                }
                case "todo": {
                    if (inputArr.length <= 1) {
                        throw new GelException("    Yo tell me what you want todo");
                    }
                    taskList.addTodo(input);
                    break;
                }
                case "find": {
                    String[] findInputArr = input.split(" ", 2);
                    String findKeyword = findInputArr[1];
                    taskList.findDescription(findKeyword);
                    break;
                }
                default: {
                    throw new GelException();
                }
                }
            } catch (GelException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public static String parseUserInputFromGui(Scanner sc, Storage storage, Ui ui, TaskList taskList, String input) throws IOException, GelException {
            String[] inputArr = input.split(" ");
            String keyword = inputArr[0];

            switch (keyword) {
            case "bye": { //bye
                storage.updateFile(taskList);
                return ui.farewellMessage();
                break;
            }
            case "list": { //list
                taskList.showListOfTask();
                break;
            }
            case "done": { //done
                taskList.doneTask(input);
                break;
            }
            case "delete": { //delete
                if (inputArr.length <= 1) {
                    throw new GelException("    Yo tell me what you want to delete la");
                }
                try {
                    taskList.deleteTask(inputArr[1]);
                    break;
                } catch (Exception e) {
                    throw new GelException("    Yoyoyo please input a valid number after delete");
                }
            }
            case "deadline": { //deadline
                int dateIndex = input.lastIndexOf("/");
                if (dateIndex == -1) {
                    throw new GelException("    Bruh you need the /by tag for deadlines");
                } else {
                    String[] dateDetails = input.substring(dateIndex).split(" ");
                    String checkBy = dateDetails[0];
                    if (!checkBy.equals("/by")) {
                        throw new GelException("    Bruh you need the /by tag for deadlines");
                    } else if (dateDetails.length <= 1) {
                        throw new GelException("    Bruh you left out the deadline");
                    }
                }
                taskList.addDeadline(input, dateIndex);
                break;
            }
            case "event": { //event
                int dateIndex = input.lastIndexOf("/");
                if (dateIndex == -1) {
                    throw new GelException("    Bruh you need the /at tag for events");
                } else {
                    String[] dateDetails = input.substring(dateIndex).split(" ");
                    String checkAt = dateDetails[0];
                    if (!checkAt.equals("/at")) {
                        throw new GelException("    Bruh you need the /at tag for events");
                    } else if (dateDetails.length <= 1) {
                        throw new GelException("    Bruh you left out the event date");
                    }
                }
                taskList.addEvent(input, dateIndex);
                break;
            }
            case "todo": {
                if (inputArr.length <= 1) {
                    throw new GelException("    Yo tell me what you want todo");
                }
                taskList.addTodo(input);
                break;
            }
            case "find": {
                String[] findInputArr = input.split(" ", 2);
                String findKeyword = findInputArr[1];
                taskList.findDescription(findKeyword);
                break;
            }
            default: {
                throw new GelException();
            }
            }

        }
    }
}
