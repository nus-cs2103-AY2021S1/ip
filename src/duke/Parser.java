package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {
    Parser() {}

    /**
     * Processes all the commands given by the user and triggers functions correspondingly.
     * @param command command given by the user
     * @param tl the TaskList object defined in Duke.
     * @param ui the Ui object defined in Duke.
     * @throws Exception
     */
    public static void processCommand(String command, TaskList tl, Ui ui) throws Exception {
        int indexOfSlash = command.indexOf('/');
        ArrayList<Task> arr = tl.getArr();
        if (command.equals("bye")) {
            ui.respondToBye();
        } else if (command.length() >= 4) {
            if (command.substring(0, 4).equals("list")) {
                if (arr.size() == 0) {
                    ui.respondToEmptyList();
                } else {
                    ui.respondToList();
                }
            } else if (command.substring(0, 4).equals("done")) {
                if (command.length() == 4) {
                    ui.respondToDoneWrongSyntax();
                } else {
                    int taskNumber = Integer.parseInt(command.substring(5));
                    if (taskNumber > arr.size()) {
                        ui.respondToDoneFail();
                    } else {
                        ui.respondToDone(taskNumber);
                    }
                }
            } else if (command.substring(0, 4).equals("todo")) {
                if (command.length() == 4 || indexOfSlash != -1) {
                    ui.respondToTodoWrongSyntax();
                } else {
                    ui.respondToTodo(command.substring(5));
                }
            } else if (command.substring(0, 5).equals("event")) {
                if (indexOfSlash == -1 || command.length() == 5 || !command.substring(indexOfSlash + 1, indexOfSlash + 3).equals("at")) {
                    ui.respondToEventFail();
                } else {
                    try {
                        String time = command.substring(indexOfSlash + 4);
                        LocalDate parsed = LocalDate.parse(time);
                        ui.respondToEvent(command.substring(6, indexOfSlash - 1), parsed.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
                    } catch(DateTimeParseException d) {
                        ui.respondToEvent(command.substring(6, indexOfSlash - 1), command.substring(indexOfSlash + 4));
                    }
                }

            } else if (command.substring(0, 6).equals("delete")) {
                if (command.length() == 6) {
                    ui.respondToDeleteWrongSyntax();
                } else {
                    int taskNumber = Integer.parseInt(command.substring(7));
                    if (taskNumber > arr.size()) {
                        ui.respondToDeleteFail();
                    } else {
                        ui.respondToDelete(taskNumber);
                    }
                }
            } else if (command.substring(0, 8).equals("deadline")) {
                if (indexOfSlash == -1 || command.length() == 8 || !command.substring(indexOfSlash + 1, indexOfSlash + 3).equals("by")) {
                    ui.respondToDeadlineFail();
                } else {
                    try {
                        String time = command.substring(indexOfSlash + 4);
                        LocalDate parsed = LocalDate.parse(time);
                        ui.respondToDeadline(command.substring(9, indexOfSlash - 1), parsed.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
                    } catch(DateTimeParseException d) {
                        ui.respondToDeadline(command.substring(9, indexOfSlash - 1), command.substring(indexOfSlash + 4));
                    }
                }
            } else {
                ui.respondToCommandDoesNotExist();
            }
        } else {
            ui.respondToCommandDoesNotExist();
        }
    }
}

