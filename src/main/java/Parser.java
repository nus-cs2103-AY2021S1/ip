import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Represents a parser that makes sense of user input.
 *
 * @author Siqi
 * @version 1.0
 * @since 2020-08-25
 */
public class Parser {
    private final static int DONE = 4;
    private final static int DELETE = 6;
    private final static int TODO = 4;
    private final static int DEADLINE = 8;
    private final static int EVENT = 5;
    private final static int FIND = 4;
    /**
     * This method takes in a string and converts it to a
     *      format (YYYY-MM-DD) that can be parsed by LocalDate class.
     * @param dateString This is a string that contains a date
     * @return This returns a string in the format YYYY-MM-DD
     */
    private static String formatDate(String dateString) {
        if (dateString.contains("/")) {
            dateString = dateString.replaceAll("\\/", "-");
        }
        String[] dateStringArr = dateString.split("-");
        dateString = "";
        for (int i = 0; i < dateStringArr.length; i++) {
            if (dateStringArr[i].length() < 2) {
                dateStringArr[i] = "0" + dateStringArr[i];
            }
            if (i > 0) {
                dateString = dateString + "-" + dateStringArr[i];
            } else {
                dateString = dateStringArr[i];
            }
        }
        return dateString;
    }

    /**
     * This is a method that parses user input when it contains a "done" in it.
     * @param input This is the string to be parsed.
     * @param taskList This is the current task list.
     * @throws DukeException When an invalid input is entered.
     */
    public static String handleDoneInput(final String input,
            final TaskList taskList) throws DukeException {
        if (!input.substring(DONE).trim().isEmpty()
                //to make sure the input after "done" is a number
                && input.substring(DONE).trim().matches("[0-9]+")) {
            //convert string to integer
            int index = Integer.parseInt(input.substring(DONE).trim());
            if (index >= 1) { //if input index is valid
                return taskList.markTaskAsDone(index);
            } else {
                throw new DukeException("Please enter a valid task number "
                        + "to mark as done (index is not valid)");
            }
        } else {
            throw new DukeException("Please enter a valid task number to "
                    + "mark as done (substring doesn't match regex)");
        }
    }

    /**
     * This is a method that parses user input when it contains a "delete".
     * @param input This is the string to be parsed.
     * @param taskList This is the current task list.
     * @throws DukeException When an invalid input is entered.
     */
    public static String handleDeleteInput(final String input,
                     final TaskList taskList) throws DukeException {
        if (!input.substring(DELETE).trim().isEmpty()
                //to make sure the input after "done" is a number
                && input.substring(DELETE).trim().matches("[0-9]+")) {
            //convert string to integer
            int index = Integer.parseInt(input.substring(DELETE).trim());
            if (index >= 1) { //if input index is valid
                return taskList.deleteTask(index);
            } else {
                throw new DukeException("Please enter a valid task number "
                        + "to delete (index is not valid)");
            }
        } else {
            throw new DukeException("Please enter a valid task number to"
                    + " delete (substring doesn't match regex)");
        }
    }

    /**
     * This is a method that parses user input when it contains a "todo" in it.
     * @param input This is the string to be parsed.
     * @param taskList This is the current task list.
     * @throws DukeException When an invalid input is entered.
     * @return String to be printed.
     */
    private static String handleTodoInput(final String input,
                    final TaskList taskList) throws DukeException {
        //to make sure to do task is not empty
        if (!input.substring(TODO).trim().isEmpty()) {
           return taskList.addTodo(input);
        } else {
            throw new DukeException("Please enter a valid todo");
        }
    }

    /**
     * This is a method that parses user input when it contains a "deadline".
     * @param input This is the string to be parsed.
     * @param taskList This is the current task list.
     * @throws DukeException When an invalid input is entered.
     * @return String to be printed.
     */
    private static String handleDeadlineInput(final String input,
                        final TaskList taskList) throws DukeException {
        //to make sure deadline is not empty
        if (!input.substring(DEADLINE).trim().isEmpty()
                //to make sure deadline contains /by
                && input.substring(DEADLINE).trim().contains("/by")
                //to make sure deadline contains a task description
                && !input.substring(DEADLINE).trim().startsWith("/by")
                //to make sure deadline contains a deadline
                && !input.substring(DEADLINE).trim().endsWith("/by")) {
            String descriptionAndTime = input.substring(DEADLINE);
            String description = descriptionAndTime.trim().split("/by ")[0];
            String by = descriptionAndTime.trim().split("/by ")[1].trim();
            try {
                if (by.contains(" ")) { //user gave a time input
                    String dateString = by.split(" ")[0].trim();
                    String timeString = by.split(" ")[1].trim();
                    dateString = formatDate(dateString);
                    LocalDate d1 = LocalDate.parse(dateString);
                    if (timeString.length() == 4) {
                        try {
                            //convert string to integer
                            int time = Integer.parseInt(timeString);
                            if (time >= 0000 && time <= 2359) {
                                return taskList.addDeadline(description,
                                        d1, timeString);
                            } else {
                                throw new DukeException("Please enter a "
                                       + "valid time between 0000 and 2359");
                            }
                        } catch (NumberFormatException nfe) {
                            throw new DukeException("Please input the time "
                                   + "in the right format (eg. 1800)");
                        }
                    } else {
                        return taskList.addDeadline(description, d1);
                    }
                } else { //user didn't give a time input
                    by = formatDate(by);
                    LocalDate d1 = LocalDate.parse(by);
                    return taskList.addDeadline(description, d1);
                }
            } catch (DateTimeException dte) {
                throw new DukeException("Please enter your date "
                       + "and time in the format yyyy-mm-dd hhmm "
                       + "(eg. 2020-08-23 1800)");
            }
        } else {
            throw new DukeException("Please enter a valid deadline");
        }
    }

    /**
     * This is a method that parses user input when it contains a "event".
     * @param input This is the string to be parsed.
     * @param taskList This is the current task list.
     * @throws DukeException When an invalid input is entered.
     * @return String to be printed.
     */
    private static String handleEventInput(final String input,
                         final TaskList taskList) throws DukeException {
        //to make sure event is not empty
        if (!input.substring(EVENT).trim().isEmpty()
                //to make sure event contains at
                && input.substring(EVENT).trim().contains("/at")
                //to make sure event description is not empty
                && !input.substring(EVENT).trim().startsWith("/at")
                //to make sure event contains a time/date
                && !input.substring(EVENT).trim().endsWith("/at")) {
            String descriptionAndTime = input.substring(EVENT);
            String description = descriptionAndTime.split("/at ")[0];
            String at = descriptionAndTime.split("/at ")[1].trim();
            try {
                if (at.contains(" ")) { //user gave a time input
                    String dateString = at.split(" ")[0].trim();
                    String timeString = at.split(" ")[1].trim();
                    dateString = formatDate(dateString);
                    LocalDate d2 = LocalDate.parse(dateString);
                    if (timeString.length() == 4) {
                        try {
                            //convert string to integer
                            int time = Integer.parseInt(timeString);
                            if (time >= 0000 && time <= 2359) {
                               return taskList.addEvent(description,
                                       d2, timeString);
                            } else {
                                throw new DukeException("Please enter a "
                                    + "valid time between 0000 and 2359");
                            }
                        } catch (NumberFormatException nfe) {
                            throw new DukeException("Please input the "
                                   + "time in the right format (eg. 1800)");
                        }
                    } else {
                        return taskList.addEvent(description, d2);
                    }
                } else { //user didn't give a time input
                    at = formatDate(at);
                    LocalDate d2 = LocalDate.parse(at);
                    return taskList.addEvent(description, d2);
                }
            } catch (DateTimeException dte) {
                throw new DukeException("Please enter your date "
                       + "and time in the format yyyy-mm-dd hhmm "
                       + "(eg. 2020-08-23 1800)");
            }
        } else {
            throw new DukeException("Please enter a valid event");
        }
    }

    /**
     * This method parses user input when it contains "find".
     * @param input     The user input.
     * @param taskList  The list of tasks.
     * @return          A list of tasks that matches query.
     * @throws DukeException When query is empty.
     */
    public static TaskList handleFindInput(final String input,
                       final TaskList taskList) throws DukeException {
        //to make sure to do task is not empty
        if (!input.substring(FIND).trim().isEmpty()) {
           return taskList.findTasks(input.substring(FIND).trim());
        } else {
            throw new DukeException("Please enter a valid search item");
        }
    }

    /**
     * This is a method that parses list items from the saved list.
     * @param taskString This is the string to be parsed.
     * @param list This is the current task list.
     * @return TaskList This returns a updated task list.
     */
    public static TaskList addTaskFromFile(final String taskString,
                                           final TaskList list) {
        if (Character.toString(taskString.charAt(1)).equals("T")) {
            if (Character.toString(taskString.charAt(4)).equals("0")) {
                list.add(new Todo(taskString.substring(6).trim()));
                return list;
            } else {
                list.add(new Todo(taskString.substring(6).trim(), true));
                return list;
            }
        } else if (Character.toString(taskString.charAt(1)).equals("D")) {
            String description = taskString.substring(6).split("/by")[0].trim();
            String dateString = taskString.substring(6).split("/by")[1].trim();
            //task is marked as not done yet
            if (Character.toString(taskString.charAt(4)).equals("0")) {
                if (dateString.contains(" ")) { //2020-02-03 1800
                    String date = dateString.split(" ")[0];
                    String time = dateString.split(" ")[1].trim();
                    LocalDate dateObj = LocalDate.parse(date);
                    list.add(new Deadline(description, dateObj, time));
                    return list;
                } else { //2020-02-03
                    LocalDate dateObj = LocalDate.parse(dateString);
                    list.add(new Deadline(description, dateObj));
                    return list;
                }
            } else { //task is marked as done
                if (dateString.contains(" ")) { //2020-02-03 1800
                    String date = dateString.split(" ")[0];
                    String time = dateString.split(" ")[1].trim();
                    LocalDate dateObj = LocalDate.parse(date);
                    list.add(new Deadline(description, true, dateObj, time));
                    return list;
                } else { //2020-02-03
                    LocalDate dateObj = LocalDate.parse(dateString);
                    list.add(new Deadline(description, true, dateObj));
                    return list;
                }
            }
        } else {
            String description = taskString.substring(6).split("/at")[0].trim();
            String dateString = taskString.substring(6).split("/at")[1].trim();
            //task is marked as not done yet
            if (Character.toString(taskString.charAt(4)).equals("0")) {
                if (dateString.contains(" ")) { //2020-02-03 1800
                    String date = dateString.split(" ")[0];
                    String time = dateString.split(" ")[1].trim();
                    LocalDate dateObj = LocalDate.parse(date);
                    list.add(new Event(description, dateObj, time));
                    return list;
                } else { //2020-02-03
                    LocalDate dateObj = LocalDate.parse(dateString);
                    list.add(new Event(description, dateObj));
                    return list;
                }
            } else { //task is marked as done
                if (dateString.contains(" ")) { //2020-02-03 1800
                    String date = dateString.split(" ")[0];
                    String time = dateString.split(" ")[1].trim();
                    LocalDate dateObj = LocalDate.parse(date);
                    list.add(new Event(description, true, dateObj, time));
                    return list;
                } else { //2020-02-03
                    LocalDate dateObj = LocalDate.parse(dateString);
                    list.add(new Event(description, true, dateObj));
                    return list;
                }
            }
        }
    }

    /**
     * This method checks the input and calls the respective parsing functions.
     * @param taskList This is the current task list.
     * @param ui This is the system that handles interaction with the user.
     * @param storage This handles the reading and writing from the local list.
     * @param input This is the user input.
     * @return This returns whether the user is done using the chatbot.
     * @throws DukeException When the input is not recognized.
     */
    public static String execute(final TaskList taskList, final Ui ui,
          final Storage storage, final String input) {
        try {
            if (input.equalsIgnoreCase("bye")) {
                ui.printGoodbye();
                storage.write(taskList);
                return "bye then";
            } else if (input.equalsIgnoreCase("list")) {
                return ui.printTasks(taskList);
            } else if (input.toLowerCase().startsWith("done")) {
                return handleDoneInput(input, taskList);
            } else if (input.toLowerCase().startsWith("delete")) {
                return handleDeleteInput(input, taskList);
            } else if (input.toLowerCase().startsWith("find")) {
                return ui.printMatchingTasks(handleFindInput(input, taskList));
            } else if (input.toLowerCase().startsWith("todo")) {
                return handleTodoInput(input, taskList);
            } else if (input.toLowerCase().startsWith("deadline")) {
                return handleDeadlineInput(input, taskList);
            } else if (input.toLowerCase().startsWith("event")) {
                return handleEventInput(input, taskList);
            } else {
                throw new DukeException("Sorry I don't know what that means :(");
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
