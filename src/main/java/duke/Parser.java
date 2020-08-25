package duke;

import duke.command.*;
import duke.exception.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An object that reads and understands user inputs.
 * It then returns the required commands needed.
 */
public class Parser {
    /**
     * Reads and convert the date and time in String format to LocalDateTime.
     * Date is stored in index 0 and Time is stored in index 1.
     * (eg. "9/8/2020 2009" is converted to Local Date and Local Time.)
     *
     * @param dateTimeString String representing date and time.
     * @return List of LocalDateTime where index 0 is Date and index 1 is Time.
     * @throws DukeInvalidDateTimeInputException If Date or Time is invalid.
     */
    protected static List<LocalDateTime> customDateTimeFormatter(String dateTimeString) throws DukeInvalidDateTimeInputException {
        //dateTimeString should be given in "dd/mm/yyyy hhmm"
        //will use manual parser to check for invalid date time inputs
        List<LocalDateTime> results = new ArrayList<>();
        //results returns index 0 as date, index 1 as time
        String[] tokens = dateTimeString.split(" ");
        String dateString = tokens[0];
        String[] dateTokens = dateString.split("/");
        if (dateTokens.length != 3) {
            throw new DukeInvalidDateTimeInputException("☹ OOPS!!! Invalid date format!");
        } else {
            int year = Integer.parseInt(dateTokens[2]);
            int month = Integer.parseInt(dateTokens[1]);
            int day = Integer.parseInt(dateTokens[0]);
            try {
                LocalDateTime date = LocalDateTime.of(year, month, day, 0, 0);
                results.add(date);
            } catch (DateTimeException e) {
                throw new DukeInvalidDateTimeInputException("☹ OOPS!!! Invalid date. Date do not exist!");
            }
        }
        if (tokens.length == 1) {
        } else {
            String timeString = tokens[1];
            if (timeString.length() != 4) {
                throw new DukeInvalidDateTimeInputException("☹ OOPS!!! Invalid time format!");
            }
            try {
                int hr = Integer.parseInt(timeString.substring(0, 2));
                int min = Integer.parseInt(timeString.substring(2));
                results.add(LocalDateTime.of(LocalDate.now(), LocalTime.of(hr, min)));
            } catch (DateTimeException e) {
                throw new DukeInvalidDateTimeInputException("☹ OOPS!!! Invalid time. You can only input up to 23hr and 59min.");
            }
        }
        return results;
    }

    /**
     * Parses the user input into an AddCommand.
     *
     * @param command The user input.
     * @return AddCommand with specific Task.
     * @throws DukeEmptyDescriptionException If description of task is empty.
     * @throws DukeEmptyByException If user did not input deadline date time.
     * @throws DukeEmptyAtException If user did not input event date time.
     * @throws DukeUnknownInputException If user input is unknown.
     * @throws DukeInvalidDateTimeInputException If date and time inputted is erroneous.
     */
    public static Command add(String command) throws DukeEmptyDescriptionException,
            DukeEmptyByException, DukeEmptyAtException, DukeUnknownInputException, DukeInvalidDateTimeInputException {
        Task toBeAdded;
        String des;
        String[] tokens;
        if (command.startsWith("todo")) {
            try {
                des = command.substring(5);
                toBeAdded = new Todo(des);
                return new AddCommand(toBeAdded);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeEmptyDescriptionException("todo");
            }
        } else if (command.startsWith("deadline")) {
            try {
                tokens = command.split(" /by ");
                des = tokens[0].substring(9);
                List<LocalDateTime> ldtList = customDateTimeFormatter(tokens[1]);
                LocalDate date = ldtList.get(0).toLocalDate();
                LocalTime time = null;
                if (ldtList.size() == 2) {
                    time = ldtList.get(1).toLocalTime();
                }
                toBeAdded = new Deadline(des, date, time);
                return new AddCommand(toBeAdded);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeEmptyDescriptionException("deadline");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeEmptyByException();
            } catch (DukeInvalidDateTimeInputException e) {
                throw e;
            }
        } else if (command.startsWith("event")) {
            try {
                tokens = command.split(" /at ");
                des = tokens[0].substring(6);
                List<LocalDateTime> ldtList = customDateTimeFormatter(tokens[1]);
                LocalDate date = ldtList.get(0).toLocalDate();
                LocalTime time = null;
                if (ldtList.size() == 2) {
                    time = ldtList.get(1).toLocalTime();
                }
                toBeAdded = new Event(des, date, time);
                return new AddCommand(toBeAdded);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeEmptyDescriptionException("event");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeEmptyAtException();
            }
        } else {
            throw new DukeUnknownInputException();
        }
    }

    /**
     * Parses the user input into a DoneCommand.
     *
     * @param command The user input.
     * @return DoneCommand with specific index.
     * @throws DukeEmptyIndexException If user did not input index.
     */
    public static Command markAsDone(String command) throws DukeEmptyIndexException {
        try {
            int index;
            String[] tokens = command.split(" ");
            index = Integer.parseInt(tokens[1]);
            return new DoneCommand(index - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeEmptyIndexException("done");
        }
    }

    /**
     * Parses the user input into a DeleteCommand.
     *
     * @param command The user input.
     * @return DeleteCommand with specific index.
     * @throws DukeEmptyIndexException If user did not input index.
     */
    public static Command delete(String command) throws DukeEmptyIndexException {
        int index;
        try {
            String[] tokens = command.split(" ");
            index = Integer.parseInt(tokens[1]);
            return new DeleteCommand(index - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeEmptyIndexException("delete");
        }
    }

    /**
     * Parses the user input into a ListDateCommand.
     *
     * @param command The user input.
     * @return ListDateCommand with specific date.
     * @throws DukeInvalidDateTimeInputException If date and time inputted is erroneous.
     */
    public static ListDateCommand listDate(String command) throws DukeInvalidDateTimeInputException {
        try {
            String dateString = command.substring(5);
            String[] dateToken = dateString.split("/");
            int[] date = Arrays.stream(dateToken).mapToInt((str) -> Integer.parseInt(str)).toArray();
            return new ListDateCommand(LocalDate.of(date[2], date[1], date[0]));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeInvalidDateTimeInputException("☹ OOPS!!! Invalid date format!");
        } catch (DateTimeException e) {
            throw new DukeInvalidDateTimeInputException("☹ OOPS!!! Invalid date. Date to not exist!");
        }
    }

    /**
     * Parses the user input and checking the type of command.
     * Returns the correct command after calling for correct parser.
     *
     * @param command The user input.
     * @return Command corresponding to user input.
     * @throws DukeEmptyIndexException If user did not input index.
     * @throws DukeEmptyDescriptionException If user did not input description.
     * @throws DukeEmptyAtException If user did not input event date time.
     * @throws DukeEmptyByException If user did not input deadline date time.
     * @throws DukeInvalidDateTimeInputException If date and time inputted is erroneous.
     */
    public static Command parse(String command) throws DukeEmptyIndexException,
            DukeEmptyDescriptionException, DukeEmptyAtException,
            DukeEmptyByException, DukeInvalidDateTimeInputException {
        if (command.equals("bye")) {
            return new ByeCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.startsWith("list ")) {
            return listDate(command);
        } else if (command.startsWith("done")) {
            return markAsDone(command);
        } else if (command.startsWith("delete")) {
            return delete(command);
        } else {
            try {
                return add(command);
            } catch (DukeUnknownInputException e) {
                return new UnknownCommand();
            } catch (DukeInvalidDateTimeInputException e) {
                throw e;
            }
        }
    }
}
