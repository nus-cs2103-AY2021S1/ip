package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {
    Parser() {}

    /**
     * Processes all the commands given by the user and triggers functions correspondingly.
     *
     * @param command command given by the user
     * @param tl the TaskList object defined in Duke.
     * @param ui the Ui object defined in Duke.
     * @throws Exception
     */
    public static String processCommand(String command, TaskList tl, Ui ui) throws Exception {
        int indexOfSlash = command.indexOf('/');
        ArrayList<Task> arr = tl.getArr();
        if (command.equals("bye")) {
            return ui.respondToBye();
        } else if (command.length() >= 4) {
            return parseCommand(command, arr, indexOfSlash, ui);
        } else {
            return ui.respondToCommandDoesNotExist();
        }
    }

    /**
     * Parse the command and does actions accordingly
     * @param command command given by user
     * @param arr arraylist of current tasks
     * @param indexOfSlash index of '/' given in command
     * @param ui the ui object used to send replies to the user
     * @return String
     * @throws Exception exception given by listParser
     */
    private static String parseCommand(String command, ArrayList<Task> arr, int indexOfSlash, Ui ui) throws Exception {
        if (command.substring(0, 4).equals("list")) {
            return listParser(arr, ui);
        } else if (command.substring(0, 4).equals("find")) {
            return findParser(command, ui);
        } else if (command.substring(0, 4).equals("done")) {
            return doneParser(command, arr, ui);
        } else if (command.substring(0, 4).equals("todo")) {
            return todoParser(command, ui, indexOfSlash);
        } else if (command.substring(0, 4).equals("undo")) {
            return undoParser(command, ui);
        } else if (command.length() == 4) {
            return ui.respondToCommandDoesNotExist();
        } else if (command.substring(0, 5).equals("event")) {
            return eventParser(command, ui, indexOfSlash);
        } else if (command.length() == 5) {
            return ui.respondToCommandDoesNotExist();
        } else if (command.substring(0, 6).equals("delete")) {
            return deleteParser(command, arr, ui);
        } else if (command.length() == 6 || command.length() == 7) {
            return ui.respondToCommandDoesNotExist();
        } else if (command.substring(0, 8).equals("deadline")) {
            return deadlineParser(command, ui, indexOfSlash);
        } else {
            return ui.respondToCommandDoesNotExist();
        }
    }

    /**
     * Parses the list command and responds accordingly using the ui
     * @param arr arraylist of current tasks
     * @param ui ui used to send replies to user
     * @return String
     * @throws Exception
     */
    private static String listParser(ArrayList<Task> arr, Ui ui) throws Exception {
        if (arr.size() == 0) {
            return ui.respondToEmptyList();
        } else {
            return ui.respondToList(arr);
        }
    }

    /**
     * Parses the find command and responds accordingly using the ui
     * @param command command given by user
     * @param ui ui used to send replies to user
     * @return String
     * @throws Exception
     */
    private static String findParser(String command, Ui ui) throws Exception {
        if (command.length() == 4) {
            return ui.respondToFindWrongSyntax();
        } else {
            String searchWord = command.substring(5);
            return ui.respondToFind(searchWord);
        }
    }

    /**
     * Processes the done command and responds accordingly using the ui
     * @param command command given by user
     * @param arr arraylist of current tasks
     * @param ui ui used to send replies to user
     * @return String
     * @throws Exception
     */
    private static String doneParser(String command, ArrayList<Task> arr, Ui ui) throws Exception {
        if (command.length() == 4) {
            return ui.respondToDoneWrongSyntax();
        } else {
            int taskNumber = Integer.parseInt(command.substring(5));
            if (taskNumber > arr.size()) {
                return ui.respondToDoneFail();
            } else {
                return ui.respondToDone(taskNumber);
            }
        }
    }

    /**
     * Processes the todo command and responds accordingly using the ui
     * @param command command given by user
     * @param ui ui used to send replies to user
     * @param indexOfSlash index of '/' given in command
     * @return String
     * @throws Exception
     */
    private static String todoParser(String command, Ui ui, int indexOfSlash) throws Exception {
        if (command.length() == 4 || indexOfSlash != -1) {
            return ui.respondToTodoWrongSyntax();
        } else {
            return ui.respondToTodo(command.substring(5));
        }
    }

    private static String undoParser(String command, Ui ui) throws Exception {
        if (command.length() > 4) {
            return ui.respondToTodoWrongSyntax();
        } else {
            return ui.respondToUndo();
        }
    }

    /**
     * Processes the event command and responds accordingly using the ui
     * @param command command given by user
     * @param ui ui used to send replies to user
     * @param indexOfSlash index of '/' given in command
     * @return String
     * @throws Exception
     */
    private static String eventParser(String command, Ui ui, int indexOfSlash) throws Exception {
        if (indexOfSlash == -1 || command.length() == 5
                || !command.substring(indexOfSlash + 1, indexOfSlash + 3).equals("at")) {
            return ui.respondToEventFail();
        } else {
            try {
                String time = command.substring(indexOfSlash + 4);
                LocalDate parsed = LocalDate.parse(time);
                return ui.respondToEvent(command.substring(6, indexOfSlash - 1),
                        parsed.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
            } catch (DateTimeParseException d) {
                return ui.respondToEvent(command.substring(6, indexOfSlash - 1),
                        command.substring(indexOfSlash + 4));
            }
        }
    }

    /**
     * Parses the delete command and responds accordingly using the ui
     * @param command command given by user
     * @param arr arraylist of current tasks
     * @param ui ui used to send replies to user
     * @return String
     * @throws Exception
     */
    private static String deleteParser(String command, ArrayList<Task> arr, Ui ui) throws Exception {
        if (command.length() == 6) {
            return ui.respondToDeleteWrongSyntax();
        } else {
            int taskNumber = Integer.parseInt(command.substring(7));
            if (taskNumber > arr.size()) {
                return ui.respondToDeleteFail();
            } else {
                return ui.respondToDelete(taskNumber);
            }
        }
    }

    /**
     * Processes the event command and responds accordingly using the ui
     * @param command command given by user
     * @param ui ui used to send replies to user
     * @param indexOfSlash index of '/' given in command
     * @return String
     * @throws Exception
     */
    private static String deadlineParser(String command, Ui ui, int indexOfSlash) throws Exception {
        if (indexOfSlash == -1 || command.length() == 8
                || !command.substring(indexOfSlash + 1, indexOfSlash + 3).equals("by")) {
            return ui.respondToDeadlineFail();
        } else {
            try {
                String time = command.substring(indexOfSlash + 4);
                LocalDate parsed = LocalDate.parse(time);
                return ui.respondToDeadline(command.substring(9, indexOfSlash - 1),
                        parsed.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
            } catch (DateTimeParseException d) {
                return ui.respondToDeadline(command.substring(9, indexOfSlash - 1),
                        command.substring(indexOfSlash + 4));
            }
        }
    }
}

