package main.java.Duke;

import main.java.Duke.Commands.*;
import main.java.Duke.DukeException.DukeArrayException;
import main.java.Duke.DukeException.DukeException;
import main.java.Duke.Task.Deadline;
import main.java.Duke.Task.Event;
import main.java.Duke.Task.TaskList;
import main.java.Duke.Task.toDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    Duke duke;

    public Parser(Duke duke) {
        this.duke = duke;
    }

     /**
     * Parses the user input and returns a Command to be executed.
     *
     * @param userInput User input from the user.
     * @param tasklist Current task list.
     * @return Command to be executed.
     * @throws DateTimeParseException if user input for date is not in the correct format.
     * @throws DukeException if user input is invalid.
     */
     public Command parse(String userInput, TaskList tasklist) {

         String commandType;
         String description = null;

        if (userInput.contains(" ")) {
            commandType = userInput.substring(0, userInput.indexOf(' '));
            description = userInput.substring(userInput.indexOf(' ') + 1);
        } else {
            commandType = userInput;
        }
         

        switch (commandType) {
        case "list":
            return new listCommand(tasklist);

        case "todo":
            try {
                if (userInput.length() < 5) {
                    throw new DukeException();
                }
                String todoDescription = userInput.substring(5);
                toDo task = new toDo(todoDescription, false);
                return new addCommand(tasklist, task);

            } catch (DukeException e) {
                duke.ui.showException("Must include description for todo");
            }
            break;

        case "deadline":
            try {
                if (!description.contains("/by")) {
                    throw new DukeException();
                } else {
                    int index = userInput.indexOf("/by");
                    String deadlineDateString = userInput.substring(index + 4);
                    LocalDate deadlineDate = LocalDate.parse(deadlineDateString);
                    Deadline deadline = new Deadline(userInput.substring(9, index), false, deadlineDate);
                    return new addCommand(tasklist, deadline);
                }
            } catch (DateTimeParseException e) {
                duke.ui.showException("Please input date in the format: YYYY-MM-DD");
            } catch (DukeException e) {
                duke.ui.showException("deadline must include '/by'");
            }
            break;

        case "event":
            try {
                if (!userInput.contains("/at")) {
                    throw new DukeException();
                } else {
                    int index = userInput.indexOf("/at");
                    String eventDateString = userInput.substring(index + 4);
                    LocalDate eventDate = LocalDate.parse(eventDateString);
                    Event event = new Event(userInput.substring(6, index), false, eventDate);
                    return new addCommand(tasklist,event);
                }
            } catch (DateTimeParseException e) {
                duke.ui.showException("Please input date in the format: YYYY-MM-DD");
            } catch (DukeException e) {
                duke.ui.showException("event must include '/at'");
            }
            break;

        case "delete":
            try {
                if (userInput.length() <= 6) {
                    throw new DukeException();
                }
                int taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
                if (taskNumber > tasklist.list.size()) {
                    throw new DukeArrayException();
                }
                return new deleteCommand(tasklist, taskNumber);
            } catch (DukeArrayException e) {
                System.out.println("Number cannot be longer than the list.");
            } catch (DukeException e) {
                System.out.println("Must include number after 'delete'");
            } catch (NumberFormatException e) {
                System.out.println("Must include number after 'delete'");
            }
            break;

        case "done":
            try {
                if (userInput.length() < 6) {
                    throw new DukeException();
                }
                int taskNumber = Integer.parseInt(userInput.substring(5)) - 1;
                if (taskNumber >= tasklist.list.size()) {
                    throw new DukeArrayException();
                }
                return new doneCommand(tasklist, taskNumber);
            } catch (DukeArrayException e){
                duke.ui.showException("Number cannot be longer than list.");
            } catch (DukeException e) {
                duke.ui.showException("Must include number after 'done'.");
            }
            break;

        case "bye":
            return new endCommand(tasklist);

        case "find":
            try {
                if (userInput.length() < 6) {
                    throw new DukeException();
                }
                return new findCommand(tasklist, description);
            } catch (DukeException e) {
                duke.ui.showException("Must include name after 'find'.");
            }
            break;


        default:
            duke.ui.badInput();

        }
        return new Command(tasklist);
    }

}
