package duke;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/**
 * Represents a user interface and deals with interactions.
 * with the user
 */
public class Ui {
    private static final String INDENT = "\n      ";
    /**
     * Pops initial message and passes to Parser to interact with user.
     * @param lst the list to contain tasks
     */
    public void run(TaskList lst) {
        // set up greetings and old list from file
        System.out.println(sayHello());
        System.out.println(listOfCommands());
        System.out.println(getInitialList(lst));

        // set up scanner for interaction
        Scanner scanner = new Scanner(System.in);
        // hand over to Parser class to handle all commands
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            System.out.println(new Parser(command).getRespond(lst));
            if (command.equals("bye")) {
                scanner.close();
                return;
            }
        }
    }

    /** Returns hello message from duke and also sets up last log in
     *  date and number of done tasks if necessary.
     * @return a string response
     */
    public static String sayHello() {
        LocalDate today = LocalDate.now();
        String messageHello = Parser.format("Hello! I'm Duke"
                + INDENT + "Today is " + today
                .format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "\n      "
                + "Last login was " + TaskList.getLastLoginDate()
                .format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + INDENT + "You have done "
                + TaskList.getNumberOfDoneTasks() + " task(s) in the past week."
                + INDENT + "Keep up the good work!!!");

        // reset done-task-counter if today is SUNDAY or
        // there was a SUNDAY between last login date and today (exclusive)
        LocalDate dateIterator = TaskList.getLastLoginDate()
                .plus(1, ChronoUnit.DAYS);
        while (dateIterator.isBefore(today)) {
            if (today.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                TaskList.resetNumberOfDoneTasks();
                break;
            }
            if (dateIterator.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                TaskList.resetNumberOfDoneTasks();
                break;
            }
            dateIterator = dateIterator.plus(1, ChronoUnit.DAYS);
        }

        return messageHello;
    }

    /** Returns a help list of all commands duke can recognise.
     * @return list of commands
     */
    public static String listOfCommands() {
        String welcomeMessage = "      "
                + "Here are some magic spells I understand:";
        String addCommand = INDENT + "todo <something>"
                + INDENT + "event <something> /at yyyy-mm-dd"
                + INDENT + "deadline <something> /by yyyy-mm-dd";
        String listCommand = INDENT + "list";
        String doneCommand = INDENT + "done <index>";
        String deleteCommand = INDENT + "delete <index>";
        String findCommand = INDENT + "find <keyword>";
        String byeCommand = INDENT + "bye";
        String closeMessage = INDENT + "Try out yourself!!! :)";
        String helpMessage = INDENT + "PS, if you cannot remember, fret not!!"
                + INDENT + "Just type 'help' and I will come to your savor.";
        return welcomeMessage + addCommand + listCommand + doneCommand
                + deleteCommand + findCommand + byeCommand + closeMessage
                + helpMessage;
    }

    /** Returns the saved list content as a string.
     *
     * @param lst the saved task list
     * @return the task list content
     */
    public String getInitialList(TaskList lst) {
        return new Parser("list").getRespond(lst);
    }

}
