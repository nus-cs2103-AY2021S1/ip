import java.time.DateTimeException;
import java.time.LocalDate;

public class Parser {
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

    public static void handleDoneInput(String input, TaskList taskList) throws DukeException {
        if (!input.substring(4).trim().isEmpty()
                && input.substring(4).trim().matches("[0-9]+")) { //to make sure the input after "done" is a number
            int index = Integer.parseInt(input.substring(4).trim()); //convert string to integer
            if (index >= 1) { //if input index is valid
                taskList.setDone(index);
            } else {
                throw new DukeException("Please enter a valid task number to mark as done (index is not valid)");
            }
        } else {
            throw new DukeException("Please enter a valid task number to mark as done " +
                    "(substring doesn't match regex)");
        }
    }

    public static void handleDeleteInput(String input, TaskList taskList) throws DukeException {
        if (!input.substring(6).trim().isEmpty()
                && input.substring(6).trim().matches("[0-9]+")) { //to make sure the input after "done" is a number
            int index = Integer.parseInt(input.substring(6).trim()); //convert string to integer
            if (index >= 1) { //if input index is valid
                taskList.setDelete(index);
            } else {
                throw new DukeException("Please enter a valid task number to delete (index is not valid)");
            }
        } else {
            throw new DukeException("Please enter a valid task number to delete " +
                    "(substring doesn't match regex)");
        }
    }

    public static void handleTodoInput(String input, TaskList taskList) throws DukeException {
        if (!input.substring(4).trim().isEmpty()) { //to make sure to do task is not empty
           taskList.setTodo(input);
        } else {
            throw new DukeException("Please enter a valid todo");
        }
    }

    public static void handleDeadlineInput(String input, TaskList taskList) throws DukeException {
        if (!input.substring(8).trim().isEmpty() //to make sure deadline is not empty
                && input.substring(8).trim().contains("/by") //to make sure deadline contains /by
                && !input.substring(8).trim().startsWith("/by") //to make sure deadline contains a task description
                && !input.substring(8).trim().endsWith("/by")) { //to make sure deadline contains a deadline
            String descriptionAndTime = input.substring(8);
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
                            int time = Integer.parseInt(timeString); //convert string to integer wrap in try catch?
                            if (time >= 0000 && time <= 2359) {
                                taskList.setDeadline(description, d1, timeString);
                            } else {
                                throw new DukeException("Please enter a valid time between 0000 and 2359");
                            }
                        } catch (NumberFormatException nfe) {
                            throw new DukeException("Please input the time in the right format (eg. 1800)");
                        }
                    } else {
                        taskList.setDeadline(description, d1);
                    }
                } else { //user didn't give a time input
                    by = formatDate(by);
                    LocalDate d1 = LocalDate.parse(by);
                    taskList.setDeadline(description, d1);
                }
            } catch (DateTimeException dte) {
                throw new DukeException("Please enter your date and time in the format yyyy-mm-dd hhmm " +
                        "(eg. 2020-08-23 1800)");
            }
        } else {
            throw new DukeException("Please enter a valid deadline");
        }
    }

    public static void handleEventInput(String input, TaskList taskList) throws DukeException {
        if (!input.substring(5).trim().isEmpty() //to make sure event is not empty
                && input.substring(5).trim().contains("/at") //to make sure event contains at
                && !input.substring(5).trim().startsWith("/at") //to make sure event description is not empty
                && !input.substring(5).trim().endsWith("/at")) { //to make sure event contains a time/date
            String descriptionAndTime = input.substring(5);
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
                            int time = Integer.parseInt(timeString); //convert string to integer
                            if (time >= 0000 && time <= 2359) {
                               taskList.setEvent(description, d2, timeString);
                            } else {
                                throw new DukeException("Please enter a valid time between 0000 and 2359");
                            }
                        } catch (NumberFormatException nfe) {
                            throw new DukeException("Please input the time in the right format (eg. 1800)");
                        }
                    } else {
                        taskList.setEvent(description, d2);
                    }
                } else { //user didn't give a time input
                    at = formatDate(at);
                    LocalDate d2 = LocalDate.parse(at);
                    taskList.setEvent(description, d2);
                }
            } catch (DateTimeException dte) {
                throw new DukeException("Please enter your date and time in the format yyyy-mm-dd hhmm " +
                        "(eg. 2020-08-23 1800)");
            }
        } else {
            throw new DukeException("Please enter a valid event");
        }
    }

    public static void handleFindInput(String input, TaskList taskList) throws DukeException {
        if (!input.substring(4).trim().isEmpty()) { //to make sure to do task is not empty
            taskList.setTodo(input);
        } else {
            throw new DukeException("Please enter a valid search item");
        }
    }

    public static TaskList addTaskFromFile(String taskString, TaskList list) {
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
            if (Character.toString(taskString.charAt(4)).equals("0")) { //task is marked as not done yet
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
            if (Character.toString(taskString.charAt(4)).equals("0")) { //task is marked as not done yet
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

    public static boolean execute(TaskList taskList, Ui ui, Storage storage, String input) throws DukeException {
        if (input.equalsIgnoreCase("bye")) { //if user types "bye"
            ui.printGoodbye();
            storage.write(taskList);
            return true;
        } else if (input.equalsIgnoreCase("list")) { //if user types "list"
            ui.printTasks(taskList);
            return false;
        } else if (input.toLowerCase().startsWith("done")) { //if user input starts with "done"
            handleDoneInput(input, taskList);
            return false;
        } else if (input.toLowerCase().startsWith("delete")) {
            handleDeleteInput(input, taskList);
            return false;
        } else if (input.toLowerCase().startsWith("find")) {
            handleFindInput(input, taskList);
            return false;
        } else if (input.toLowerCase().startsWith("todo")) {
            handleTodoInput(input, taskList);
            return false;
        } else if (input.toLowerCase().startsWith("deadline")) {
            handleDeadlineInput(input, taskList);
            return false;
        } else if (input.toLowerCase().startsWith("event")) {
            handleEventInput(input, taskList);
            return false;
        } else {
            throw new DukeException("Please enter a valid deadline");
        }
    }
}
