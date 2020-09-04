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
            if (command.substring(0, 4).equals("list")) {
                if (arr.size() == 0) {
                    return ui.respondToEmptyList();
                } else {
                    return ui.respondToList();
                }
            } else if (command.substring(0, 4).equals("find")) {
                if (command.length() == 4) {
                    return ui.respondToFindWrongSyntax();
                } else {
                    String searchWord = command.substring(5);
                    return ui.respondToFind(searchWord);
                }
            } else if (command.substring(0, 4).equals("done")) {
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
            } else if (command.substring(0, 4).equals("todo")) {
                if (command.length() == 4 || indexOfSlash != -1) {
                    return ui.respondToTodoWrongSyntax();
                } else {
                    return ui.respondToTodo(command.substring(5));
                }
            } else if (command.length() == 4) {
                return ui.respondToCommandDoesNotExist();
            } else if (command.substring(0, 5).equals("event")) {
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

            } else if (command.length() == 5) {
                return ui.respondToCommandDoesNotExist();
            } else if (command.substring(0, 6).equals("delete")) {
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
            } else if (command.length() == 6 || command.length() == 7) {
                return ui.respondToCommandDoesNotExist();
            } else if (command.substring(0, 8).equals("deadline")) {
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
            } else {
                return ui.respondToCommandDoesNotExist();
            }
        } else {
            return ui.respondToCommandDoesNotExist();
        }
    }
}

