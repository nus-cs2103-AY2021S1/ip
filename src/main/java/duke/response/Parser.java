package duke.response;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.ListDateCommand;
import duke.command.UnknownCommand;
import duke.response.exception.DukeEmptyAtException;
import duke.response.exception.DukeEmptyByException;
import duke.response.exception.DukeEmptyDescriptionException;
import duke.response.exception.DukeEmptyIndexException;
import duke.response.exception.DukeEmptyKeywordException;
import duke.response.exception.DukeInvalidDateTimeInputException;
import duke.response.exception.DukeUnknownInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * An object that reads and understands user inputs.
 * It then returns the required commands needed.
 */
public class Parser {
    private static final int DEADLINE_LENGTH = 9;
    private static final int EVENT_LENGTH = 6;

    /**
     * Reads and convert the date and time in String format to LocalDateTime.
     * Date is stored in index 0 and Time is stored in index 1.
     * (eg. "9/8/2020 2009" is converted to Local Date and Local Time.)
     *
     * @param dateTimeString String representing date and time.
     * @return List of LocalDateTime where index 0 is Date and index 1 is Time.
     * @throws DukeInvalidDateTimeInputException If Date or Time is invalid.
     */
    public static List<LocalDateTime> getCustomDateTimeList(String dateTimeString)
            throws DukeInvalidDateTimeInputException {
        //dateTimeString should be given in "dd/mm/yyyy hhmm"
        //will use manual parser to check for invalid date time inputs
        List<LocalDateTime> results = new ArrayList<>();
        //results returns index 0 as date, index 1 as time
        String[] tokens = dateTimeString.split(" ");
        String dateString = tokens[0];
        String[] dateTokens = dateString.split("/");
        if (dateTokens.length != 3) {
            throw new DukeInvalidDateTimeInputException("OOPS!!! Invalid date format!");
        } else {
            int year = Integer.parseInt(dateTokens[2]);
            int month = Integer.parseInt(dateTokens[1]);
            int day = Integer.parseInt(dateTokens[0]);
            try {
                results.add(LocalDateTime.of(year, month, day, 0, 0));
            } catch (DateTimeException e) {
                throw new DukeInvalidDateTimeInputException("OOPS!!! Invalid date. Date do not exist!");
            }
        }
        if (tokens.length == 1) {
        } else {
            String timeString = tokens[1];
            if (timeString.length() != 4) {
                throw new DukeInvalidDateTimeInputException("OOPS!!! Invalid time format!");
            }
            try {
                int hr = Integer.parseInt(timeString.substring(0, 2));
                int min = Integer.parseInt(timeString.substring(2));
                results.add(LocalDateTime.of(LocalDate.now(), LocalTime.of(hr, min)));
            } catch (DateTimeException e) {
                throw new DukeInvalidDateTimeInputException(
                    "OOPS!!! Invalid time. You can only input up to 23hr and 59min.");
            }
        }
        return results;
    }

    /**
     * Parses the user input into an AddCommand.
     *
     * @param command The user input.
     * @return AddCommand with specific Task.
     * @throws DukeEmptyDescriptionException     If description of task is empty.
     * @throws DukeEmptyByException              If user did not input deadline date time.
     * @throws DukeEmptyAtException              If user did not input event date time.
     * @throws DukeUnknownInputException         If user input is unknown.
     * @throws DukeInvalidDateTimeInputException If date and time inputted is erroneous.
     */
    private static Command add(String command) throws DukeEmptyDescriptionException,
            DukeEmptyByException, DukeEmptyAtException, DukeUnknownInputException, DukeInvalidDateTimeInputException {
        if (command.startsWith("todo")) {
            try {
                String des = command.substring(5);
                Task toBeAdded = new Todo(des);
                return new AddCommand(toBeAdded);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeEmptyDescriptionException("todo");
            }
        } else if (command.startsWith("deadline")) {
            try {
                return new AddCommand(parseTime("/by", command));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeEmptyDescriptionException("deadline");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeEmptyByException();
            } catch (DukeInvalidDateTimeInputException e) {
                throw e;
            }
        } else if (command.startsWith("event")) {
            try {
                return new AddCommand(parseTime("/at", command));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeEmptyDescriptionException("event");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeEmptyAtException();
            }
        } else {
            throw new DukeUnknownInputException();
        }
    }

    private static Task parseTime(String type, String command) throws DukeInvalidDateTimeInputException {
        assert(type.equals("/by") || type.equals("/at"));
        String[] tokens = command.split(String.format(" %s ", type));
        String des = type.equals("/by")
            ? tokens[0].substring(DEADLINE_LENGTH)
            : tokens[0].substring(EVENT_LENGTH);
        List<LocalDateTime> ldtList = getCustomDateTimeList(tokens[1]);
        return type.equals("/by")
            ? parseDeadlineDateTimeList(des, ldtList)
            : parseEventDateTimeList(des, ldtList);
    }

    private static Deadline parseDeadlineDateTimeList(String description,
            List<LocalDateTime> localDateTimeList) {
        LocalDate date = localDateTimeList.get(0).toLocalDate();
        LocalTime time = null;
        if (localDateTimeList.size() == 2) {
            time = localDateTimeList.get(1).toLocalTime();
        }
        return new Deadline(description, date, time);
    }

    private static Event parseEventDateTimeList(String description,
            List<LocalDateTime> localDateTimeList) {
        LocalDate date = localDateTimeList.get(0).toLocalDate();
        LocalTime time = null;
        if (localDateTimeList.size() == 2) {
            time = localDateTimeList.get(1).toLocalTime();
        }
        return new Event(description, date, time);
    }

    /**
     * Parses the user input into a DoneCommand.
     *
     * @param command The user input.
     * @return DoneCommand with specific index.
     * @throws DukeEmptyIndexException If user did not input index.
     */
    private static Command markAsDone(String command) throws DukeEmptyIndexException {
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
    private static Command delete(String command) throws DukeEmptyIndexException {
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
    private static ListDateCommand listDate(String command) throws DukeInvalidDateTimeInputException {
        try {
            String dateString = command.substring(5);
            String[] dateToken = dateString.split("/");
            int[] date = Arrays.stream(dateToken).mapToInt((str) -> Integer.parseInt(str)).toArray();
            return new ListDateCommand(LocalDate.of(date[2], date[1], date[0]));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeInvalidDateTimeInputException("OOPS!!! Invalid date format!");
        } catch (DateTimeException e) {
            throw new DukeInvalidDateTimeInputException("OOPS!!! Invalid date. Date to not exist!");
        }
    }

    /**
     * Parses the user input into a FindCommand.
     *
     * @param command The user input.
     * @return FindCommand with specific keyword.
     * @throws DukeEmptyKeywordException If no keyword was inputted.
     */
    private static FindCommand find(String command) throws DukeEmptyKeywordException {
        try {
            String[] tokens = command.split("find ");
            String keyword = tokens[1];
            return new FindCommand(keyword);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeEmptyKeywordException();
        }
    }

    /**
     * Parses the user input and checking the type of command.
     * Returns the correct command after calling for correct parser.
     *
     * @param command The user input.
     * @return Command corresponding to user input.
     * @throws DukeEmptyIndexException           If user did not input index.
     * @throws DukeEmptyDescriptionException     If user did not input description.
     * @throws DukeEmptyAtException              If user did not input event date time.
     * @throws DukeEmptyByException              If user did not input deadline date time.
     * @throws DukeInvalidDateTimeInputException If date and time inputted is erroneous.
     */
    public static Command parse(String command) throws DukeEmptyIndexException,
            DukeEmptyDescriptionException, DukeEmptyAtException,
            DukeEmptyByException, DukeInvalidDateTimeInputException,
            DukeEmptyKeywordException {
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
        } else if (command.startsWith("find")) {
            return find(command);
        } else if (command.startsWith("help")) {
            return new HelpCommand();
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
