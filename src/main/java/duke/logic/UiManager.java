package duke.logic;

import java.util.Scanner;

import duke.CommonString;
import duke.task.DukeTask;

/**
 * Represents the User interface.
 * Manages the User Interaction Interface.
 * It contains <code>Enums</code> for pre-defined texts to be printed for the program
 * and methods to print feedback to the users for different commands.
 */
public class UiManager {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Reads user command using the <code>Scanner</code>.
     *
     * @return String denoting user command
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints introduction to Duke.
     */
    public void printDukeIntro() {
        System.out.println(UserInteractionText.INTRODUCTION_WITH_LINE);
    }

    /**
     * Returns String representation of Duke intro
     * @return String
     */
    public String getDukeIntro() {
        return UserInteractionText.INTRODUCTION.value;
    }

    /**
     * Prints instructions when user invokes <code>HelpCommand</code>.
     */
    public void printDukeInstructions() {
        System.out.println(UserInteractionText.INSTRUCTIONS);
    }

    /**
     * Returns String representation of Duke instructions
     * @return String
     */
    public String getDukeInstructions() {
        return UserInteractionText.INSTRUCTIONS.value;
    }

    /**
     * Prints outro to Duke.
     */
    public void printDukeOutro() {
        System.out.println(UserInteractionText.OUTRO_WITH_LINE);
    }

    public String getDukeOutro() {
        return UserInteractionText.OUTRO.value;
    }

    /**
     * Prints when users invoke <code>AddCommand</code>.
     */
    public void printAddTask(DukeTask task, int size) {
        System.out.println("Task Added: " + task.toString());
        printTaskStatus(size);
    }

    /**
     * Returns a String representation of add Task response
     * @param task DukeTask
     * @param size number of tasks left
     * @return String
     */
    public String getAddTask(DukeTask task, int size) {
        return "Task Added: " + task.toString() + "\n" + getTaskStatus(size);
    }

    /**
     * Prints when users invoke <code>DeleteCommand</code>.
     */
    public void printDeleteTask(DukeTask task, int size) {
        System.out.println("Alright! I'll delete this task!\n" + "Take note that this is irreversible!");
        System.out.println(task.toString());
        printTaskStatus(size);
    }

    /**
     * Returns a String representation of delete Task response
     * @param task DukeTask
     * @param size number of tasks left
     * @return String
     */
    public String getDeleteTask(DukeTask task, int size) {
        return "Alright! I'll delete this task!\n" + "Take note that this is irreversible!" + "\n"
                + task.toString() + "\n"
                + getTaskStatus(size);
    }

    /**
     * Prints when users invoke <code>ListCommand</code>.
     */
    public void printNumberedTask(DukeTask task, int num) {
        System.out.println((num + 1) + ". " + task);
    }

    public String getNumberedTask(DukeTask task, int num) {
        return (num + 1) + ". " + task;
    }

    /**
     * Prints when users invoke <code>DoneCommand</code>.
     */
    public void printMarkAsDone(DukeTask task, int size) {
        System.out.println("Alright! I'll mark this task as done!");
        System.out.println(task.toString());
        printTaskStatus(size);
    }

    /**
     * Returns a String representation of markAsDone
     * @param task DukeTask
     * @param size Number of tasks in list
     * @return String
     */
    public String getMarkAsDone(DukeTask task, int size) {
        return "Alright! I'll mark this task as done!" + "\n"
                + task.toString() + "\n"
                + getTaskStatus(size);
    }

    /**
     * Prints when FindCommand does not find Task
     */
    public void printFindCannotBeFound(String keyword) {
        System.out.println("There are no tasks containing " + "\"" + keyword + "\"!");
    }

    /**
     * Returns String representation of findCannotBeFound
     * @param keyword String containing keyword
     * @return String
     */
    public String getFindCannotBeFound(String keyword) {
        return "There are no tasks containing " + "\"" + keyword + "\"!";
    }

    /**
     * Prints Tasks found by FindCommand
     */
    public void printFindFilteredList(String keyword, boolean isPlural) {
        System.out.println((isPlural ? "Tasks" : "Task") + " with the keyword: " + "\"" + keyword + "\"");
    }

    /**
     * Returns String representation of findFilteredList
     * @param keyword String containing keyword
     * @return String
     */
    public String getFindFilteredList(String keyword, boolean isPlural) {
        return (isPlural ? "Tasks" : "Task") + " with the keyword: " + "\"" + keyword + "\"";
    }

    /**
     * Prints a line separator.
     */
    public void printLine() {
        System.out.println(UserInteractionText.LINE);
    }

    /**
     * Prints number of tasks users have in their list.
     */
    public void printTaskStatus(int size) {
        String taskSize = size == 1 ? " task" : " tasks";
        System.out.println("You now have " + size + taskSize);
    }

    /**
     * Returns a String representation of number of tasks in user's list.
     * @param size number of tasks remaining
     * @return String
     */
    public String getTaskStatus(int size) {
        return "You now have " + size + (size == 1 ? " task" : " tasks");
    }

    /**
     * Represents text for HelpCommand.
     * It contains different fields holding Strings that are to be printed for the user FAQ.
     * Splitting them up into parts makes it easier to edit according to the specific guide in the future
     */
    private enum InstructionGuide {
        // For formatting purposes, except for the last guide, the guides must end with a guideBreaker
        // It splits them into paragraphs
        Level("* Level 9: Finding Keywords", false),

        DeveloperUpdate("* DEVELOPER UPDATES:\n"
                + "* I can now save data! Just terminate the program with \"bye\" "
                + "and I'll save the data automatically!\n"
                + "* Do take note of the DATE AND TIME notations when inserting DukeTasks!\n"
                + "* Want to find a specific task? I can do it too!", false),

        AvailableInstruction("AVAILABLE INSTRUCTIONS:\n"
                + " help - Display Available Instructions\n"
                + " bye - Terminate Duke\n"
                + " list - Display current DukeTasks\n"
                + " done [Task Number] - Complete the specified task number "
                + "(Specify in numeric format!) Eg: \"done 3\"\n"
                + " delete [Task Number] - Deletes the task number "
                + "(Specify in numeric format!) Eg: \"delete 3\"\n"
                + " find [keyword] - Finds related Tasks containing the keyword\n"
                + "* SEE TASK INPUT INSTRUCTIONS FOR MORE ABOUT TASKS", false),

        TaskInputInstruction("TASK INPUT INSTRUCTIONS:\n"
                + "IMPORTANT: DO AVOID USING THE VERTICAL BAR \"|\" IN YOUR TASK INSTRUCTIONS\n"
                + " todo [Task Description] - Inputs a TODO DukeTask\n"
                + " deadline [Task Description] /by [Date] - Inputs a DEADLINE DukeTask, using INDICATOR /by\n"
                + " event [Task Description] /at [Date] - Inputs an EVENT DukeTask, using INDICATOR /at", false),

        TaskInputSpecification("TO NOTE:\n"
                + "FORMAT FOR DATE: \"DD/MM/YYYY hh/mm/ss\"\n"
                + " \tDD, MM, YYYY are the date, month and year respectively (IN INTEGERS)\n"
                + " \thh, mm, ss are the hour (in 24 HOUR NOTATION), "
                + "minutes and seconds respectively (IN INTEGERS)", true);

        private final String instruction;
        private final boolean last;

        InstructionGuide(String instruction, boolean last) {
            this.instruction = instruction;
            this.last = last;
        }

        @Override
        public String toString() {
            String guideBreaker = "\n\n";
            return instruction + (last ? "" : guideBreaker);
        }
    }

    /**
     * Represents text for User Interaction during Duke's main execution.
     * It contains different fields holding Strings that are to be printed for various parts of the program.
     * Splitting them up into parts makes it easier to edit according to the specific guide in the future
     */
    private enum UserInteractionText {
        LINE("____________________________________________________________"), // Single Line for divider{

        INTRODUCTION_WITH_LINE(LINE + "\n"
                + "Hello! I am Duke, your Personal Assistant!\n"
                + "What can I do for you today?\n"
                + "Type \"help\" to see the available instructions!\n"
                + LINE), // END OF INTRODUCTIONS)

        INTRODUCTION("Hello! I am Duke, your Personal Assistant!\n"
                + "What can I do for you today?\n"
                + "Type \"help\" to see the available instructions!"),

        INSTRUCTIONS("How to use Duke:\n"
                + InstructionGuide.Level
                + InstructionGuide.DeveloperUpdate
                + InstructionGuide.AvailableInstruction
                + InstructionGuide.TaskInputInstruction
                + InstructionGuide.TaskInputSpecification), // END OF INSTRUCTIONS)

        OUTRO_WITH_LINE("Goodbye. Hope to see you soon!\n"
                + CommonString.LOGO + LINE),

        OUTRO("Goodbye. Hope to see you soon!");

        private final String value;

        UserInteractionText(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}

