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
public class UiManager implements UserInteractionUi, CommandInteractionUi {
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
     * Prints a line separator.
     */
    public void printLine() {
        System.out.println(UserInteractionText.LINE);
    }

    /**
     * Prints introduction to Duke.
     * Method implemented from UserInteractionUi interface.
     */
    public void printDukeIntro() {
        System.out.println(UserInteractionText.INTRODUCTION_WITH_LINE);
    }

    /**
     * Returns String representation of Duke intro.
     * Method implemented from UserInteractionUi interface.
     *
     * @return String
     */
    public String getDukeIntro() {
        return UserInteractionText.INTRODUCTION.value;
    }

    /**
     * Prints outro to Duke.
     * Method implemented from UserInteractionUi interface.
     */
    public void printDukeOutro() {
        System.out.println(UserInteractionText.OUTRO_WITH_LINE);
    }

    public String getDukeOutro() {
        return UserInteractionText.OUTRO.value;
    }

    /**
     * Prints instructions when user invokes <code>HelpCommand</code>.
     * Method implemented from CommandInteractionUi interface.
     */
    public void printDukeInstructions() {
        System.out.println(UserInteractionText.INSTRUCTIONS);
    }

    /**
     * Returns String representation of Duke instructions.
     * Method implemented from CommandInteractionUi interface.
     *
     * @return String
     */
    public String getDukeInstructions() {
        return UserInteractionText.INSTRUCTIONS.value;
    }

    /**
     * Prints when users invoke <code>AddCommand</code>.
     * Method implemented from CommandInteractionUi interface.
     */
    public void printAddTask(DukeTask task, int size) {
        assert task != null : "printAddTask dukeTask cannot be null";
        System.out.println(getAddTask(task, size));
    }

    /**
     * Returns a String representation of add Task response.
     * Method implemented from CommandInteractionUi interface.
     *
     * @param task DukeTask
     * @param size number of tasks left
     * @return String
     */
    public String getAddTask(DukeTask task, int size) {
        assert task != null : "getAddTask dukeTask cannot be null";
        return "Task Added:\n\n" + task.toString() + "\n\n" + getTaskStatus(size);
    }

    /**
     * Prints when users invoke <code>DeleteCommand</code>.
     * Method implemented from CommandInteractionUi interface.
     */
    public void printDeleteTask(DukeTask task, int size) {
        assert task != null : "printDeleteTask dukeTask cannot be null";
        System.out.println(getDeleteTask(task, size));
    }

    /**
     * Returns a String representation of delete Task response.
     * Method implemented from CommandInteractionUi interface.
     *
     * @param task DukeTask
     * @param size number of tasks left
     * @return String
     */
    public String getDeleteTask(DukeTask task, int size) {
        assert task != null : "getDeleteTask dukeTask cannot be null";
        return "Fine...I'll get rid of that task for you...\n"
                + "...but it's tiring so I'm not gonna bring it back...ever.\n\n"
                + task.toString() + "\n\n"
                + getTaskStatus(size);
    }

    /**
     * Prints when users invoke <code>ListCommand</code>.
     * Method implemented from CommandInteractionUi interface.
     */
    public void printNumberedTask(DukeTask task, int num) {
        assert task != null : "printNumberedTask dukeTask cannot be null";
        System.out.println(getNumberedTask(task, num));
    }

    /**
     * Returns a String representation of list Task response.
     * Method implemented from CommandInteractionUi interface.
     *
     * @param task DukeTask
     * @param num  Index of said task
     * @return String
     */
    public String getNumberedTask(DukeTask task, int num) {
        assert task != null : "getNumberedTask dukeTask cannot be null";

        return (num + 1) + ". " + task;
    }

    /**
     * Prints when users invoke <code>DoneCommand</code>.
     * Method implemented from CommandInteractionUi interface.
     */
    public void printTaskMarkAsDone(DukeTask task, int size) {
        assert task != null : "printMarkAsDone dukeTask cannot be null";

        System.out.println(getTaskMarkAsDone(task, size));
    }

    /**
     * Returns a String representation of markAsDone.
     * Method implemented from CommandInteractionUi interface.
     *
     * @param task DukeTask
     * @param size Number of tasks in list
     * @return String
     */
    public String getTaskMarkAsDone(DukeTask task, int size) {
        assert task != null : "getMarkAsDone dukeTask cannot be null";

        return "Oh...you're done? I'll mark it down for you...\n\n"
                + task.toString() + "\n\n"
                + getTaskStatus(size);
    }

    /**
     * Prints when FindCommand does not find Task.
     * Method implemented from CommandInteractionUi interface.
     */
    public void printKeywordCannotBeFound(String keyword) {
        assert keyword != null : "printFindCannotBeFound keyword cannot be null";

        System.out.println(getKeywordCannotBeFound(keyword));
    }

    /**
     * Returns String representation of find Task response when Task cannot be found.
     * Method implemented from CommandInteractionUi interface.
     *
     * @param keyword String containing keyword
     * @return String
     */
    public String getKeywordCannotBeFound(String keyword) {
        assert keyword != null : "getFindCannotBeFound keyword cannot be null";

        return "...ngghh...I don't know any..." + "\"" + keyword + "\"...zzz...";
    }

    /**
     * Prints Tasks found by FindCommand
     * Method implemented from CommandInteractionUi interface.
     */
    public void printKeywordFoundResult(String keyword, boolean isPlural) {
        assert keyword != null : "printFindFilteredList keyword cannot be null";

        System.out.println(getKeywordFoundResult(keyword, isPlural));
    }

    /**
     * Returns String representation of response from find command.
     * Method implemented from CommandInteractionUi interface.
     *
     * @param keyword String containing keyword
     * @return String
     */
    public String getKeywordFoundResult(String keyword, boolean isPlural) {
        assert keyword != null : "getFindFilteredList keyword cannot be null";

        return "...*yawns*...I found " + (isPlural ? "some tasks" : "a task")
                + " with the keyword: " + "\"" + keyword + "\"\n";
    }

    /**
     * Prints Tasks sorted by SortCommand.
     * Method implemented from CommandInteractionUi interface.
     */
    public void printSortListResult(String tag, boolean isPlural) {
        assert tag != null : "printSortList tag cannot be null";

        System.out.println(getSortListResult(tag, isPlural));
    }

    /**
     * Returns String representation of response from find command.
     * Method implemented from CommandInteractionUi interface.
     *
     * @param tag String containing type of Tasks sorted.
     * @return String
     */
    public String getSortListResult(String tag, boolean isPlural) {
        assert tag != null : "getSortList tag cannot be null";

        return "...*yawns*...I sorted " + (isPlural ? "some tasks" : "a task")
                + " that are of type: " + "\"" + tag + "\"\n";
    }

    /**
     * Prints number of tasks users have in their list.
     * Method implemented from CommandInteractionUi interface.
     */
    public void printTaskStatus(int size) {
        System.out.println(getTaskStatus(size));
    }

    /**
     * Returns a String representation of number of tasks in user's list.
     * Method implemented from CommandInteractionUi interface.
     *
     * @param size number of tasks remaining
     * @return String
     */
    public String getTaskStatus(int size) {
        String taskSize = size == 1 ? " task" : " tasks";
        return ("...*Yawns*... You have " + size + taskSize + "\n"
                + "I'll go back to my nap...please finish them so-...zzz...");
    }

    /**
     * Represents text for HelpCommand.
     * It contains different fields holding Strings that are to be printed for the user FAQ.
     * Splitting them up into parts makes it easier to edit according to the specific guide in the future
     */
    private enum InstructionGuide {
        // For formatting purposes, except for the last guide, the guides must end with a guideBreaker
        // It splits them into paragraphs

        DukeLetter(UserInteractionText.LINE.value + "\n"
                + "Letter from Duke:\n"
                + "Hello! I'm Duke, your Personal Chatbot Assistant. Due to COVID, I'm stuck at overseas :(\n"
                + "\n"
                + "But don't worry! Gude is here to take over me!\n"
                + "I've trained Gude before I left and I've listed below some stuff he can do for you!\n"
                + "\n"
                + "NOTE: He's a lil bit lazy so he'll only understand if you prompt him properly! Have fun!\n"
                + "Cheers, Duke\n"
                + UserInteractionText.LINE.value, false),

        AvailableInstruction("AVAILABLE INSTRUCTIONS:\n"
                + " help - Display Available Instructions\n"
                + " bye - Terminate Duke\n"
                + " list - Display current DukeTasks\n"
                + " done [Task Number] - Complete the specified task number\n"
                + " \t(Specify in numeric format!) Eg: \"done 3\"\n"
                + " delete [Task Number] - Deletes the task number\n"
                + " \t(Specify in numeric format!) Eg: \"delete 3\"\n"
                + " find [keyword] - Finds related Tasks containing the keyword\n"
                + " sort [tag] - Sorts the tasks with the tag", false),

        TaskInputInstruction("TASK INPUT INSTRUCTIONS:\n"
                + "PLEASE AVOID USING THE VERTICAL BAR \"|\" IN YOUR TASK INSTRUCTIONS\n"
                + " todo [Task Description] - Inputs a TODO DukeTask\n"
                + " deadline [Task Description] /by [Date]\n"
                + " \tInputs a DEADLINE DukeTask, using INDICATOR /by\n"
                + " event [Task Description] /at [Date]\n"
                + " \tInputs an EVENT DukeTask, using INDICATOR /at\n"
                + " sort [tag] - TAGS are: \"all\", \"deadline\" or \"event\"", false),

        TaskInputSpecification("TO NOTE:\n"
                + "FORMAT FOR DATE: \"DD/MM/YYYY hh/mm/ss\"\n"
                + " \tDD, MM, YYYY are the date, month and year respectively (IN INTEGERS)\n"
                + " \thh, mm, ss are the hour (in 24 HOUR NOTATION),\n"
                + " \tminutes and seconds respectively (IN INTEGERS)", true);

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

        INTRODUCTION("*Yawns*\n"
                + "...Oh hello...\n"
                + "...*zzz*...\n"
                + "...nggghh...Gude is awake...\n"
                + "Call for \"help\" and I'll...*zzz*...tell you more..."),

        INTRODUCTION_WITH_LINE(LINE + "\n"
                + INTRODUCTION.value
                + LINE), // END OF INTRODUCTIONS)

        INSTRUCTIONS("...ngghh...Here's some stuff Duke told me...\n"
                + InstructionGuide.DukeLetter
                + InstructionGuide.AvailableInstruction
                + InstructionGuide.TaskInputInstruction
                + InstructionGuide.TaskInputSpecification), // END OF INSTRUCTIONS)

        OUTRO("...Oh you're going? Bye then...*yawns*..."),

        OUTRO_WITH_LINE(OUTRO.value + "\n"
                + CommonString.LOGO + LINE);

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

