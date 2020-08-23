package duke.parser;

import duke.exception.DukeException;
import duke.ui.Ui;
import duke.command.Command;
import duke.command.TodoCommand;
import duke.command.EventCommand;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.CheckCommand;
import duke.command.DoneCommand;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    private Ui ui;

    public Parser(Ui ui) {
        this.ui = ui;
    }

    public boolean isNumeric(String test) {
        try {
            Integer.parseInt(test);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public Command parse(String command) throws DukeException {
        String[] modEcho = command.split(" ", 2);

        if (modEcho.length == 1) {
            return CheckOneWord(command);
        } else {
            return CheckTwoWords(modEcho);
        }
    }

    public Command CheckOneWord(String echo) throws DukeException {
        if (echo.equals("bye")) {
            return new ExitCommand();
        } else if (echo.equals("list")) {
            return new ListCommand();
        } else if (echo.equals("todo") || echo.equals("deadline") || echo.equals("event")) {
            throw new DukeException("Please enter a valid" +
                    " description for your task!");
        } else if (echo.equals("done")) {
            throw new DukeException("Please enter the ID " +
                    "of the task you would like to complete.");
        } else if (echo.equals("delete")) {
            throw new DukeException("Please retry and enter " +
                    "the ID of the task to be deleted.");
        } else if (echo.equals("check")) {
            throw new DukeException("Please enter a date to check!");
        } else {
            throw new DukeException("Please enter a valid " +
                    "command into the console.");
        }
    }

    public Command CheckTwoWords(String[] modEcho) throws DukeException {
        String task = modEcho[0];

        if (task.equals("done")) {
            if (!isNumeric(modEcho[1])) {
                throw new DukeException("Please enter a valid task number to complete!");
            } else {
                int index = Integer.parseInt(modEcho[1]) - 1;
                return new DoneCommand(index);
            }

        } else if (task.equals("delete")) {
            if (!isNumeric(modEcho[1])) {
                throw new DukeException("Please enter a valid task number for deletion!");
            } else {
                int index = Integer.parseInt(modEcho[1]) - 1;
                return new DeleteCommand(index);
            }

        } else if (task.equals("check")) {
            try {
                LocalDate checkedDate = LocalDate.parse(modEcho[1]);
                return new CheckCommand(checkedDate);
            } catch (DateTimeParseException ex) {
                throw new DukeException("Please enter the date in this format: yyyy-mm-dd");
            }

        } else {
            if (task.equals("todo")) {
                return new TodoCommand(modEcho[1]);
            } else if (task.equals("deadline") || task.equals("event")) {
                return CheckValidTime(modEcho);
            } else {
                throw new DukeException("Please enter a valid task name to add into the list!");
            }

        }
    }

    public Command CheckValidTime(String[] modEcho) throws DukeException {
        String task = modEcho[0];
        String[] processTime = modEcho[1].split("/");

        if (processTime.length == 1) {
            throw new DukeException("You need to include '/by' or '/at' for this task to describe the time.");
        } else {
            String[] time = processTime[1].split(" ", 2);

            if (time.length == 1) {
                throw new DukeException("The time description cannot be left blank!");
            } else {

                if (task.equals("deadline")) {
                    return new DeadlineCommand(processTime[0].trim(),
                            time[1].trim());
                } else {
                    return new EventCommand(processTime[0].trim(),
                            time[1].trim());
                }
            }
        }
    }

}
