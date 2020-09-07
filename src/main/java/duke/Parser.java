package duke;

import java.time.format.FormatStyle;

public class Parser {

    /**
     * Constructor of Parser.
     */
    public Parser() {}


    /**
     * Return Formatted datetime of a task.
     *
     * @param dateTime  User input of date and time of a Task object (specifically Deadline/Event).
     * @returns  Processed datetime of a Deadline or Event object.
     */
    public String dateTimeParser(String commandType, String dateTime) {
        try {
            DateTimeConverter dtc = new DateTimeConverter(FormatStyle.MEDIUM, FormatStyle.SHORT);
            return dtc.processTime(commandType, dateTime);
        } catch (Exception e) {
            return "improperDateTime";
        }
    }


    /**
     * Return Specific information of a Task object.
     *
     * @param taskLine  Single line of information of a Task object.
     * @returns  Specific information of a Task contained in an array of Strings.
     */
    public String[] localFileTaskParser(String taskLine) {
        String[] taskTypeAndContent = taskLine.split(SpecialFormat.SPLIT_NOTN, 2);
        String taskType = taskTypeAndContent[0];
        String taskContent = taskTypeAndContent[1];
        String[] taskDetails;

        if (taskType.equals("T")) {
            taskDetails = taskContent.split(SpecialFormat.SPLIT_NOTN, 2);
            return new String[] {taskType, taskDetails[0], taskDetails[1]};
        } else {  // if taskType has value of "E" or "D", representing Event or Deadline object
            taskDetails = taskContent.split(SpecialFormat.SPLIT_NOTN, 3);
            // taskDetails[0] isDone, taskDetails[1] taskAction, taskDetails[2] datetime
            return new String[] {taskType, taskDetails[0], taskDetails[1], taskDetails[2]};
        }
    }



    /**
     * Return Specific information of a Command in Segments.
     *
     * @param input  User input of command.
     * @returns  Specific information of a Command in the form of String array.
     */
    public String[] commandParser(String input) {

        String cleanInput = input.trim();
        if (!cleanInput.contains(" ")) {   // single word command
            switch (cleanInput) {
            case "bye":
            case "hello":
            case "list":
                return new String[]{input};
            default:
                return parseException(cleanInput);
            }
        }

        String[] inputSplitArr = cleanInput.split(" ", 2);
        String commandType = inputSplitArr[0];
        String taskContent = inputSplitArr[1];

        switch (commandType) {
        case "find":
            return parseFind(commandType, taskContent);
        case "done":
        case "delete":
            return parseModifications(commandType, taskContent);
        case "todo":
        case "event":
        case "deadline":
            return parseNewEvent(commandType, taskContent);

        default:
            return new String[] {"exception", "no_meaning"};
        }

    }


    public String[] parseFind(String commandType, String keyword) {
//        if (keyword.isBlank()) {
//            return new String[] {"exception", "find"};
//        }
        return new String[] {commandType, keyword};
    }


    public String[] parseModifications(String commandType, String actionNumber) {
//        if (actionNumber.isBlank()) {
//            return new String[] {"exception", "empty_illegal"};
//        }
        return new String[] {commandType, actionNumber};
    }


    public String[] parseNewEvent(String commandType, String taskContent) {

//        String[] output;
//
//        if (commandType.equals("todo")) {
//            //output = parseTodo(taskContent);
//            output = new String[] {commandType, taskContent};
//        } else {
//            output = parseEventAndDeadline(commandType, taskContent);
//        }
//
//        return output;

        if (commandType.equals("todo")) {
            return parseTodo(taskContent);
        } else {
            return parseEventAndDeadline(commandType, taskContent);
        }

    }


    public String[] parseTodo(String taskContent) {
//        if (taskContent.isBlank()) {
//            return new String[] {"exception", "todo"};
//        }
        return new String[] {"todo", taskContent};
    }


    public String[] parseEventAndDeadline(String commandType, String taskContent) {
        try {
            String[] taskDetails = taskContent.split(
                    commandType.equals("event") ? " /at " : " /by ", 2);

            String taskAction = taskDetails[0];
            String dateTime = taskDetails[1];

            dateTime = dateTimeParser(commandType, dateTime);

            if (dateTime.equals("improperDateTime")) {
                return new String[]{"exception", "improperDateTime"};
            }

            return new String[]{commandType, taskAction, dateTime};

        } catch (Exception ex) {
//            String exceptionType = commandType.equals("event")
//                    ? "event"
//                    : "deadline";
//            return new String[]{"exception", exceptionType};
            return parseException(commandType);
        }
    }

    public String[] parseException(String type) {
        switch (type) {
        case "done":
        case "delete":
            return new String[] {"exception", "empty_illegal"};
        case "find":
        case "todo":
        case "event":
        case "deadline":
            return new String[] {"exception", type};
        default:
            return new String[] {"exception", "no_meaning"};
        }
    }



    //    /**
//     * Return Specific information of a Command in Segments.
//     *
//     * @param input  User input of command.
//     * @returns  Specific information of a Command in the form of String array.
//     */
//    public String[] commandParser(String input) {
//
//        if (input.equals("bye") || input.equals("hello") || input.equals("list")) {
//            return new String[]{input};
//        }
//
//        String commandType;
//        String taskContent;
//        try {
//            String[] inputSplitArr = input.split(" ", 2);
//            commandType = inputSplitArr[0];
//            taskContent = inputSplitArr[1];
//        } catch (Exception e) {
//            return new String[] {"exception", "no_meaning"};
//        }
//
//        switch (commandType) {
//        case "find":
//            return parseFind(commandType, taskContent);
//
//        case "done":
//        case "delete":
//            return parseModifications(commandType, taskContent);
//
//        case "todo":
//        case "event":
//        case "deadline":
//            return parseNewEvent(commandType, taskContent);
//
//        default:
//            return new String[] {"exception", "no_meaning"};
//        }
//
//    }

}