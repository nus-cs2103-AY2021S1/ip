import java.util.Arrays;

/**
 *  Parses user input.
 */
public class Parser {
    /**
     * Parse method to handle all types of commands.
     * @param inputLine String that is input by user on command line.
     * @return Command object that has been specified by input file.
     * @throws DukeException if there is an issue.
     */
    public static Command parse(String inputLine) throws DukeException {
        Command newTaskObject;
        String[] userInputArray = inputLine.split(" ");
        assert(userInputArray.length > 0);
        String commandCheck = userInputArray[0];
        int numOfInput = userInputArray.length;

        switch (commandCheck) {
        case "bye":
            newTaskObject = processByeCommand();
            break;

        case "list":
            newTaskObject = processListCommand();
            break;

        case "done":
            newTaskObject = processDoneCommand(userInputArray, numOfInput);
            break;

        case "todo":
            newTaskObject = processTodoCommand(userInputArray, numOfInput);
            break;

        case "event":
            try {
                newTaskObject = processEventCommand(userInputArray, numOfInput, inputLine);
            } catch (ArrayIndexOutOfBoundsException error) {
                throw new DukeException("Error encountered while parsing event command. Please ensure "
                                        + "that the event command is in the following format: "
                                        + "(event) (description) (/at) (date).");
            }
            break;

        case "deadline":
            try {
                newTaskObject = processDeadlineCommand(userInputArray, numOfInput, inputLine);
            } catch (ArrayIndexOutOfBoundsException error) {
                throw new DukeException("Error encountered while parsing deadline command. Please ensure "
                                        + "that the deadline command is in the following format: "
                                        + "(deadline) (description) (/by) (date).");
            }
            break;

        case "delete":
            newTaskObject = processDeleteCommand(userInputArray, numOfInput, inputLine);
            break;

        case "find":
            newTaskObject = processFindCommand(inputLine);
            break;

        case "help":
            newTaskObject = processHelpCommand();
            break;

        case "sort":
            newTaskObject = processSortCommand(userInputArray, numOfInput);
            break;


        default:
            throw new DukeException("Catastrophe detected! I'm sorry, but '" + commandCheck
                                    + "' is not within my realm of knowledge. " + "\u2620 ");
        }
        return newTaskObject;
    }

    /**
     * Implements bye command.
     * @return ByeCommand object.
     */
    private static ByeCommand processByeCommand() {
        return new ByeCommand();
    }

    /**
     * Implements list command.
     * @return ListCommand object.
     */
    private static ListCommand processListCommand() {
        return new ListCommand();
    }

    /**
     * Implements done command.
     * @param userInputArray Entire string input by user as an array
     * @param numOfInput Number of separate strings in user input
     * @return DoneCommand object.
     * @throws DukeException if there is an issue.
     */
    private static DoneCommand processDoneCommand(String[] userInputArray, Integer numOfInput) throws DukeException {
        if (numOfInput < 2) {
            throw new DukeException("Please specify which task you have completed.");
        }

        try {
            Integer.parseInt(userInputArray[1]);
        } catch (NumberFormatException error) {
            throw new DukeException("Please input task index as a valid integer.");
        }

        int indexOfDoneTask = Integer.parseInt(userInputArray[1]) - 1;
        return new DoneCommand(indexOfDoneTask);
    }

    /**
     * Implements Todo command.
     * @param userInputArray Entire string input by user as an array
     * @param numOfInput Number of separate strings in user input
     * @return TodoCommand object.
     * @throws DukeException if there is an issue.
     */
    private static TodoCommand processTodoCommand(String[] userInputArray, Integer numOfInput) throws DukeException {
        if (numOfInput == 1) {
            throw new DukeException("\u2620 Oh no! The description of a todo task cannot be empty.");
        }
        StringBuilder todoString = new StringBuilder();
        int j = 1;
        while (j < numOfInput) {
            todoString.append(userInputArray[j]);
            todoString.append(" ");
            j++;
        }
        String outputTodoDesc = todoString.toString().trim();
        return new TodoCommand(outputTodoDesc);
    }

    /**
     * Implements Event command.
     * @param userInputArray Entire string input by user as an array
     * @param numOfInput Number of separate strings in user input
     * @param inputLine String that is input by user on command line.
     * @return EventCommand object.
     * @throws DukeException if there is an issue.
     */
    private static EventCommand processEventCommand(String[] userInputArray, Integer numOfInput, String inputLine)
                                                    throws DukeException {
        if (numOfInput == 1) {
            throw new DukeException("\u2620 Oh no! The description of an event task cannot be empty.");
        }

        if (numOfInput < 4) {
            throw new DukeException("\u2620 Oh no! The correct way to log an event is: (event) "
                    + "(description) (/at) (date)");
        }

        boolean checkForEvent = false;
        StringBuilder eventString = new StringBuilder();
        StringBuilder eventDateField = new StringBuilder();
        StringBuilder eventTimingField = new StringBuilder();
        String trimmedEvent = inputLine.split("/at")[1].trim();
        String[] splitElements = trimmedEvent.split(" ");
        int numOfElements = splitElements.length;

        if (numOfElements != 2) {
            throw new DukeException("Date and Timing fields has not been specified correctly. "
                                    + "Please try again.");
        }

        int z = 1;
        while (z < numOfInput) {
            if (userInputArray[z].equals("/at")) {
                checkForEvent = true;
            } else {
                if (!checkForEvent) {
                    eventString.append(userInputArray[z]);
                    eventString.append(" ");
                } else {
                    if (z == numOfInput - 1) {
                        eventTimingField.append(userInputArray[z]);
                    } else {
                        eventDateField.append(userInputArray[z]);
                    }
                }
            }
            z++;
        }
        String outputEventDesc = eventString.toString().trim();
        String outputEventDate = eventDateField.toString().trim();
        String outputEventTime = eventTimingField.toString().trim();
        return new EventCommand(outputEventDesc, outputEventDate, outputEventTime);
    }

    /**
     * Implements Deadline command.
     * @param userInputArray Entire string input by user as an array
     * @param numOfInput Number of separate strings in user input
     * @param inputLine String that is input by user on command line.
     * @return DeadlineCommand object.
     * @throws DukeException if there is an issue.
     */
    private static DeadlineCommand processDeadlineCommand(String[] userInputArray, Integer numOfInput, String inputLine)
                                                          throws DukeException {
        if (numOfInput == 1) {
            throw new DukeException("\u2620 Oh no! The description of a deadline task "
                                    + "cannot be empty.");
        }

        if (numOfInput < 4) {
            throw new DukeException("\u2620 Oh no! The correct way to log a deadline is: (deadline) "
                                    + "(description) (/by) (date)");
        }

        boolean checkForDate = false;
        StringBuilder deadlineString = new StringBuilder();
        StringBuilder dateField = new StringBuilder();
        StringBuilder timingField = new StringBuilder();
        String trimmedDeadline = inputLine.split("/by")[1].trim();
        String[] splitElements1 = trimmedDeadline.split(" ");
        int noOfElements = splitElements1.length;

        if (noOfElements != 2) {
            throw new DukeException("Date and Timing fields has not been specified correctly. "
                                    + "Please try again.");
        }

        int m = 1;
        while (m < numOfInput) {
            if (userInputArray[m].equals("/by")) {
                checkForDate = true;
            } else {
                if (!checkForDate) {
                    deadlineString.append(userInputArray[m]);
                    deadlineString.append(" ");
                } else {
                    if (m == numOfInput - 1) {
                        timingField.append(userInputArray[m]);
                    } else {
                        dateField.append(userInputArray[m]);
                    }
                }
            }
            m++;
        }
        String outputDeadlineDesc = deadlineString.toString().trim();
        String outputDeadlineDate = dateField.toString().trim();
        String outputDeadlineTime = timingField.toString().trim();
        return new DeadlineCommand(outputDeadlineDesc, outputDeadlineDate, outputDeadlineTime);
    }

    /**
     * Implements Delete command.
     * @param userInputArray Entire string input by user as an array
     * @param numOfInput Number of separate strings in user input
     * @param inputLine String that is input by user on command line.
     * @return DeleteCommand object.
     * @throws DukeException if there is an issue.
     */
    private static DeleteCommand processDeleteCommand(String[] userInputArray, Integer numOfInput, String inputLine)
                                                      throws DukeException {
        if (numOfInput < 2) {
            throw new DukeException("Please specify which task you wish to delete.");
        }

        try {
            Integer.parseInt(userInputArray[1]);
        } catch (NumberFormatException error) {
            throw new DukeException("Please input task index as a valid integer.");
        }

        String indexToDelete = inputLine.split(" ")[1];
        int indexOfTemp = Integer.parseInt(indexToDelete);
        int currentTaskIndex = indexOfTemp - 1;

        return new DeleteCommand(currentTaskIndex);
    }

    /**
     * Implements Find command.
     * @param inputLine String that is input by user on command line.
     * @return FindCommand object.
     * @throws DukeException if there is an issue.
     */
    private static FindCommand processFindCommand(String inputLine) throws DukeException {
        String[] arrayOfElements = inputLine.split(" ");
        int numOfTokens = arrayOfElements.length;
        if (numOfTokens < 2) {
            throw new DukeException("Please specific keyword to locate task. Eg. find book");
        }

        String[] copiedArray = Arrays.copyOfRange(arrayOfElements, 1, arrayOfElements.length);
        return new FindCommand(copiedArray);
    }

    /**
     * Implements Help command.
     * @return HelpCommand object.
     */
    private static HelpCommand processHelpCommand() {
        return new HelpCommand();
    }

    /**
     * Implements Sort command.
     * @param userInputArray Entire string input by user as an array
     * @param numOfInput Number of separate strings in user input
     * @return SortCommand object.
     */
    private static SortCommand processSortCommand(String[] userInputArray, int numOfInput) throws DukeException {
        if (numOfInput == 1) {
            throw new DukeException("\u2620 Oh no! The description of a sort command cannot be empty.");
        }
        String typeOfSort = userInputArray[1];
        return new SortCommand(typeOfSort);
    }
}
