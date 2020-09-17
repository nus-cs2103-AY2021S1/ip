package mattbot.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

import mattbot.DateTime.DateTimeManager;
import mattbot.Errors.ErrorExceptions;
import mattbot.Tasks.TaskManager;



/**
 * Represents a manager that handles all actions carried out by the parser.
 * Decomposes all user input into key terms.
 */
public class InputManager {
    private static String fileDir;

    /**
     * Determines what bridge Parse to call based on the user input.
     * Returns the appropriate message of the action called.
     *
     * @param input user input.
     * @return String action message.
     * @throws ErrorExceptions when selected task do not exists.
     */
    public static String parse2(String input) throws ErrorExceptions {
        assert input.equals("") == false;
        Scanner sc = new Scanner(input);
        String current = sc.next();
        TaskManager.fileDir(fileDir);
        if (current.equals("bye")) {
            return runBye();
        } else if (current.equals("delete")) {
            return runDelete(sc);
        } else if (current.equals("done")) {
            return runDone(sc);
        } else if (current.equals("list")) {
            return runList();
        } else if (current.equals("show")) {
            return runShow();
        } else if (current.equals("filter")) {
            return runFilter(sc);
        } else if (current.equals("find")) {
            return runFind(sc, input);
        } else if (current.equals("view")) {
            return runViewSchedule(sc);
        } else { // add tasks
            return runAdd(current, input);
        }
    }

    public static String runViewSchedule(Scanner sc) {
        try {
            String date = sc.next();
            LocalDate d = DateTimeManager.setDate(date);
            return ParseViewSchedules.execute2(d);
        } catch (NoSuchElementException e) {
//            return ParseViewSchedules.execute3();
            return "Missing date for view command!";
        }

    }
    /**
     * Runs the method for receiving "Bye" command.
     *
     * @return String the String when saying bye.
     */
    public static String runBye() {
        return ParseExit.execute2();
    }

    /**
     * Runs the method when deleting command is received.
     *
     * @param sc scanner of the input.
     * @return String the String when deleting a task.
     * @throws ErrorExceptions when the task entered is not found.
     */
    public static String runDelete(Scanner sc) throws ErrorExceptions {
        try {
            int index = sc.nextInt();
            return ParseDelete.execute2(index);
        } catch (NoSuchElementException e) {
            throw new ErrorExceptions("There is no such tasks!");
        }
    }

    /**
     * Runs the method when receiving a mark as done command.
     *
     * @param sc scanner of the input.
     * @return String the done message.
     * @throws ErrorExceptions when the task cannot be found.
     */
    public static String runDone(Scanner sc) throws ErrorExceptions {
        try {
            int index = sc.nextInt();
            return ParseCompleted.execute2(index);
        } catch (NoSuchElementException e) {
            throw new ErrorExceptions("There is no such tasks!");
        }
    }

    /**
     * Runs the method for showing all tracked tasks.
     *
     * @return String list of all task.
     */
    public static String runList() {
        return ParseList.execute2();
    }

    /**
     * Runs the show all commands method.
     *
     * @return String show all commands.
     */
    public static String runShow() {
        return ParseShow.execute2();
    }
    /**
     * Runs the method when an add task command in received.
     *
     * @param current current command.
     * @param input overall user input.
     * @return String adding of task message.
     * @throws ErrorExceptions
     */
    public static String runAdd(String current, String input) throws ErrorExceptions {
        return ParseAddTask.execute2(current, input);
    }

    /**
     * Runs the filter by date method.
     *
     * @param sc scanner with input.
     * @return String all the task after filtering.
     * @throws ErrorExceptions
     */
    public static String runFilter(Scanner sc) throws ErrorExceptions {
        try {
            String date = sc.next();
            return ParseFilter.execute2(date);
        } catch (NoSuchElementException e) {
            throw new ErrorExceptions("Missing date!");
        }
    }

    /**
     * Runs the find by name method.
     *
     * @param sc scanner with input.
     * @param input user input.
     * @return String all the filtered results.
     */
    public static String runFind(Scanner sc, String input) {
        try {
            return ParseFind.execute2(input);
        } catch (ErrorExceptions e) {
            return e.toString();
        }
    }

    /**
     * Returns the name of the task from the user input.
     *
     * @param input user input.
     * @param type type of task.
     * @return String name of task.
     * @throws ErrorExceptions when the add task command format is wrong
     */
    public static String getName(String input, int type) throws ErrorExceptions {
        Scanner sc = new Scanner(input);
        sc.next(); // skip first commandtype
        String name = "";
        try {
            String current = sc.next();
            if (type == 1) { // todo
                try {
                    while (current.charAt(0) != '/') { // deadline and event
                        name = name + current + " ";
                        current = sc.next();
                    }
                } catch (NoSuchElementException e) {
                    // do nothing
                }
            } else {
                try {
                    while (current.charAt(0) != '/') { // deadline and event
                        name = name + current + " ";
                        current = sc.next();
                    }
                } catch (NoSuchElementException e) {
                    // do nothing
                }
            }
            return name;
        } catch (NoSuchElementException e) {
            throw new ErrorExceptions("Missing item name!");
        }
    }

    /**
     * Returns the LocalDateTime from the user input.
     *
     * @param input user input.
     * @param type type of task.
     * @return LocalDateTime date and time.
     * @throws ErrorExceptions when the date and time format is wrong or missing.
     */
    public static String getDate(String input, int type) throws ErrorExceptions {
        Scanner sc = new Scanner(input);
        String next = sc.next();
        String N = "" + next.charAt(0);
        String date = "";
        try {
            while (!N.equals("/")) {
                next = sc.next();
                N = "" + next.charAt(0);
            }
            String action = "";
            try {
                String day = sc.next();
                action = action + day;
                try {
                    String time = sc.next();
                    action = action + " " + time;
                } catch (NoSuchElementException e) {
                    if (type == 2) {
                    } else {
                        throw new ErrorExceptions("Wrong event command format, missing timeslot");
                    }
                }
                date = action;
            } catch (NoSuchElementException e) {
                throw new ErrorExceptions("Wrong event command format, missing date");
            }
        } catch (NoSuchElementException e) {
            if (type == 1) {
                return date;
            } else {
                throw new ErrorExceptions("Wrong deadline or event command format, missing /action: task");
            }
        }
        return date;
    }

    /**
     * Sets the file directory of the local save.
     *
     * @param d file directory.
     */
    public static void fileDir(String d) {
        fileDir = d;
    }

    /**
     * Returns the file directory of the local save.
     *
     * @return String file directory.
     */
    public static String getFileDir() {
        return fileDir;
    }
}
