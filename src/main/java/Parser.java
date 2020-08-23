public class Parser {

    /**
     * Parses all types of commands.
     * @param inputLine Description of the input command.
     * @return Command object that has been specified by input file.
     */
    public static Command parse(String inputLine) throws DukeException {
        Command newTaskObject;
        String[] userInputArray = inputLine.split(" ");
        String commandCheck = userInputArray[0];
        int numOfInput = userInputArray.length;

        switch (commandCheck) {
            case "bye":
                newTaskObject = new ByeCommand();
                break;

            case "list":
                newTaskObject = new ListCommand();
                break;

            case "done":
                if (numOfInput < 2) {
                    throw new DukeException("Please specify which task you have completed.");
                }

                try {
                    Integer.parseInt(userInputArray[1]);
                } catch (NumberFormatException error) {
                    throw new DukeException("Please input task index as a valid integer.");
                }

                int indexOfDoneTask = Integer.parseInt(userInputArray[1]) - 1;
                newTaskObject = new DoneCommand(indexOfDoneTask);
                break;

            case "todo":
                if (numOfInput == 1) {
                    throw new DukeException("☹ Oh no! The description of a todo task cannot be empty.");
                }
                StringBuilder todoString = new StringBuilder();
                int j = 1;
                while (j < numOfInput) {
                    todoString.append(userInputArray[j]);
                    todoString.append(" ");
                    j++;
                }
                String outputTodoDesc = todoString.toString().trim();
                newTaskObject = new TodoCommand(outputTodoDesc);
                break;

            case "event":
                if (numOfInput == 1) {
                    throw new DukeException("☹ Oh no! The description of an event task cannot be empty.");
                }

                if (numOfInput < 4) {
                    throw new DukeException("☹ Oh no! The correct way to log an event is: (event) " +
                                            "(description) (/at) (date)");
                }

                boolean checkForEvent = false;
                StringBuilder eventString = new StringBuilder();
                StringBuilder eventDateField = new StringBuilder();
                StringBuilder eventTimingField = new StringBuilder();
                String trimInput = inputLine.split("/at")[1].trim();
                String[] splitElements = trimInput.split(" ");
                int numOfElements = splitElements.length;

                if (numOfElements != 2) {
                    throw new DukeException("Date and Timing fields has not been specified correctly. Please try again!");
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
                newTaskObject = new EventCommand(outputEventDesc, outputEventDate, outputEventTime);
                break;

            case "deadline":
                if (numOfInput == 1) {
                    throw new DukeException("☹ Oh no! The description of a deadline task " +
                                            "cannot be empty.");
                }

                if (numOfInput < 4) {
                    throw new DukeException("☹ Oh no! The correct way to log a deadline is: (deadline) "
                                            + "(description) (/by) (date)");
                }

                boolean checkForDate = false;
                StringBuilder deadlineString = new StringBuilder();
                StringBuilder dateField = new StringBuilder();
                StringBuilder timingField = new StringBuilder();
                String trimInput1 = inputLine.split("/by")[1].trim();
                String[] splitElements1 = trimInput1.split(" ");
                int noOfElements = splitElements1.length;

                if (noOfElements != 2) {
                    throw new DukeException("Date and Timing fields has not been specified correctly. Please try again!");
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
                newTaskObject = new DeadlineCommand(outputDeadlineDesc, outputDeadlineDate, outputDeadlineTime);
                break;

            case "delete":
                if (numOfInput < 2) {
                    throw new DukeException("Please specify which task you wish to delete.");
                }

                try {
                    Integer.parseInt(userInputArray[1]);
                } catch (NumberFormatException error) {
                    throw new DukeException("Please input task index as a valid integer.");
                }

                String tempStr = inputLine.split(" ")[1];
                int indexOfTemp = Integer.parseInt(tempStr);
                int currentTaskIndex = indexOfTemp - 1;

                newTaskObject = new DeleteCommand(currentTaskIndex);
                break;

            default:
                throw new DukeException("Catastrophe detected! I'm sorry, but '" + commandCheck
                                        + "' is not within my realm of knowledge. ☹");
        }
        return newTaskObject;
    }
}