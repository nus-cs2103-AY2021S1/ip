import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

/**
 * Represents the parser of the Duke application. The parser is responsible for
 * processing the user command so that it can be executed by the application.
 */
public class Parser {

    /**
     * Parses the date or time of a deadline or event.
     * @param dateTime the date or time of a deadline or event
     * @return String representation of the date or time.
     * @throws DateException If an error occurs while parsing the date or time.
     */
    public static String parseDateTime(String dateTime) throws DateException {
        try {
            return processDateTime(dateTime);
        } catch (java.time.format.DateTimeParseException | java.text.ParseException e) {
            throw new DateException("Sorry! I don't understand the date/time. Please specify the date/time "
                    + "in YYYY-MM-DD or YYYY-MM-DD HHMM format.");
        }
    }

    private static String processDateTime(String dateTime) throws
            java.time.format.DateTimeParseException, java.text.ParseException {
        String[] dateTimes = dateTime.split(" ");
        boolean hasOnlyDate = dateTimes.length == 1;
        LocalDate localDate = LocalDate.parse(dateTimes[0]);
        String processedDate = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        if (hasOnlyDate) {
            return processedDate;
        }
        int time = Integer.parseInt(dateTimes[1]);
        String processedTime = processTime(time);
        return processedDate + " " + processedTime;
    }

    private static String processTime(int time) throws java.text.ParseException {
        int militaryTime = time;
        Date date = new SimpleDateFormat("hhmm").parse(String.format("%04d", militaryTime));
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        return dateFormat.format(date);
    }

    /**
     * Parses the user command into a format that can be understood by the command
     * component of the Duke application.
     * @param userCommand User command received by the Parser.
     * @param taskListSize Current size of the task list.
     * @return List of user command details.
     * @throws InvalidDoneException If an error occurs while parsing a command to mark
     * tasks as done.
     * @throws InvalidTaskArgumentException If an error occurs while parsing a command
     * to add tasks.
     * @throws InvalidDeleteException If an error occurs while parsing a command to
     * delete tasks.
     * @throws InvalidCommandException If the user command type is invalid.
     * @throws DateException If an error occurs while parsing the dates of events or deadlines.
     */
    public ArrayList<String> parseUserCommand(String userCommand, int taskListSize)
            throws InvalidDoneException, InvalidTaskArgumentException, InvalidDeleteException,
            InvalidCommandException, DateException {
        ArrayList<String> userCommandDetails = new ArrayList<>();
        if (isListCommand(userCommand)) {
            return addListCommand(userCommandDetails);
        } else if (isDoneCommand(userCommand)) {
            return addDoneCommand(userCommandDetails, userCommand, taskListSize);
        } else if (isTodoCommand(userCommand)) {
            return addTodoCommand(userCommandDetails, userCommand);
        } else if (isDeadlineCommand(userCommand)) {
            return addDeadlineCommand(userCommandDetails, userCommand);
        } else if (isEventCommand(userCommand)) {
            return addEventCommand(userCommandDetails, userCommand);
        } else if (isDeleteCommand(userCommand)) {
            return addDeleteCommand(userCommandDetails, userCommand, taskListSize);
        } else if (isFindCommand(userCommand)) {
            return addFindCommand(userCommandDetails, userCommand);
        } else {
            throw new InvalidCommandException("\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private boolean isNumeric(String taskNumber) {
        try {
            Integer.parseInt(taskNumber);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isListCommand(String userCommand) {
        return userCommand.equals("list");
    }

    private ArrayList<String> addListCommand(ArrayList<String> userCommandDetails) {
        userCommandDetails.add("Show");
        return userCommandDetails;
    }

    private boolean isDoneCommand(String userCommand) {
        return (userCommand.length() >= 4) && (userCommand.substring(0, 4).equals("done"));
    }

    private ArrayList<String> addDoneCommand(ArrayList<String> userCommandDetails,
           String userCommand, int taskListSize) throws InvalidDoneException {
        boolean isTaskNumberNotSpecified = userCommand.length() <= 5;
        if (isTaskNumberNotSpecified) {
            throw new InvalidDoneException("\u2639" + " OOPS!!! The task to be marked as done is not "
                    + "specified.");
        }
        boolean isTaskNumberInvalidInt = !isNumeric(userCommand.substring(5));
        if (isTaskNumberInvalidInt) {
            throw new InvalidDoneException("\u2639" + " OOPS!!! The task to be marked as done is not "
                    + "specified by a valid number.");
        }
        int taskNumber = Integer.parseInt(userCommand.substring(5)) - 1;
        boolean isTaskNumberOutOfBounds = (taskNumber < 0) || (taskNumber >= taskListSize);
        if (isTaskNumberOutOfBounds) {
            throw new InvalidDoneException("\u2639" + " OOPS!!! The number specified does not represent "
                    + "a valid task.");
        }
        userCommandDetails.add("Done");
        userCommandDetails.add(Integer.toString(taskNumber));
        return userCommandDetails;
    }

    private boolean isTodoCommand(String userCommand) {
        return (userCommand.length() >= 4) && (userCommand.substring(0, 4).equals("todo"));
    }

    private ArrayList<String> addTodoCommand(ArrayList<String> userCommandDetails,
           String userCommand) throws InvalidTaskArgumentException {
        boolean isTodoDescriptionMissing = userCommand.length() <= 5;
        if (isTodoDescriptionMissing) {
            throw new InvalidTaskArgumentException("\u2639" + " OOPS!!! The description of a todo cannot "
                    + "be empty.");
        }
        userCommandDetails.add("Add");
        userCommandDetails.add("ToDo");
        userCommandDetails.add(userCommand.substring(5));
        return userCommandDetails;
    }

    private boolean isDeadlineCommand(String userCommand) {
        return (userCommand.length() >= 8) && (userCommand.substring(0, 8).equals("deadline"));
    }

    private ArrayList<String> addDeadlineCommand(ArrayList<String> userCommandDetails,
           String userCommand) throws InvalidTaskArgumentException, DateException {
        boolean isDeadlineDescriptionDateMissing = (userCommand.length() <= 9)
                || (userCommand.substring(9).split(" /by ").length < 2);
        if (isDeadlineDescriptionDateMissing) {
            throw new InvalidTaskArgumentException("\u2639" + " OOPS!!! The deadline is lacking a "
                    + "description/date.");
        }
        userCommandDetails.add("Add");
        userCommandDetails.add("Deadline");
        String[] deadlineDetails = userCommand.substring(9).split(" /by ");
        String deadlineDescription = deadlineDetails[0];
        String deadlineDate = parseDateTime(deadlineDetails[1]);
        userCommandDetails.add(deadlineDescription);
        userCommandDetails.add(deadlineDate);
        return userCommandDetails;
    }

    private boolean isEventCommand(String userCommand) {
        return (userCommand.length() >= 5) && (userCommand.substring(0, 5).equals("event"));
    }

    private ArrayList<String> addEventCommand(ArrayList<String> userCommandDetails,
           String userCommand) throws InvalidTaskArgumentException, DateException {
        boolean isEventDescriptionDateMissing = (userCommand.length() <= 6)
                || (userCommand.substring(6).split(" /at ").length < 2);
        if (isEventDescriptionDateMissing) {
            throw new InvalidTaskArgumentException("\u2639" + " OOPS!!! The event is lacking a "
                    + "description/date.");
        }
        userCommandDetails.add("Add");
        userCommandDetails.add("Event");
        String[] eventDetails = userCommand.substring(6).split(" /at ");
        String eventDescription = eventDetails[0];
        String eventDate = parseDateTime(eventDetails[1]);
        userCommandDetails.add(eventDescription);
        userCommandDetails.add(eventDate);
        return userCommandDetails;
    }

    private boolean isDeleteCommand(String userCommand) {
        return (userCommand.length() >= 6) && (userCommand.substring(0, 6).equals("delete"));
    }

    private ArrayList<String> addDeleteCommand(ArrayList<String> userCommandDetails,
           String userCommand, int taskListSize) throws InvalidDeleteException {
        boolean isTaskNumberNotSpecified = userCommand.length() <= 7;
        if (isTaskNumberNotSpecified) {
            throw new InvalidDeleteException("\u2639" + "OOPS!!! The tasks to be deleted are not "
                    + "specified.");
        }
        userCommandDetails.add("Delete");
        boolean isDeleteAll = userCommand.substring(7).equals("all");
        if (isDeleteAll) {
            userCommandDetails.add("All");
        } else {
            String[] deletedTaskPositions = userCommand.substring(7).split(" ");
            for (int i = 0; i < deletedTaskPositions.length; i++) {
                if (!isNumeric(deletedTaskPositions[i])) {
                    throw new InvalidDeleteException("\u2639" + " OOPS!!! There is a task to be deleted "
                            + " that is not specified by a valid number.");
                }
                int deletedTaskNumber = Integer.parseInt(deletedTaskPositions[i]) - 1;
                boolean isTaskNumberOutOfBounds = (deletedTaskNumber < 0) || (deletedTaskNumber >= taskListSize);
                if (isTaskNumberOutOfBounds) {
                    throw new InvalidDeleteException("\u2639" + " OOPS!!! There is a number specified "
                            + "that does not represent a valid task.");
                }
                userCommandDetails.add(Integer.toString(deletedTaskNumber));
            }
        }
        return userCommandDetails;
    }

    private boolean isFindCommand(String userCommand) {
        return (userCommand.length() >= 4) && (userCommand.substring(0, 4).equals("find"));
    }

    private ArrayList<String> addFindCommand(ArrayList<String> userCommandDetails, String userCommand) {
        userCommandDetails.add("Find");
        String keyword = userCommand.split(" ")[1];
        userCommandDetails.add(keyword);
        return userCommandDetails;
    }
}
