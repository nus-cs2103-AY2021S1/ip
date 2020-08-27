package duke.logic;

import duke.CommonString;
import duke.task.DukeTask;

import java.util.Scanner;

// Manages the UI interaction with the User for duke.Duke
public class UIManager {
    Scanner scanner = new Scanner(System.in);
    // PRIVATE ENUMS OF THE UI MANAGER -- TO SAVE CONSTANTS TO BE PRINTED

    // Instruction Guides split up the various parts of the "help" function guide
    // Splitting them up into parts makes it easier to edit according to the specific guide in the future
    private enum InstructionGuide {
        // For formatting purposes, except for the last guide, the guides must end with a guideBreaker
        // It splits them into paragraphs
        Level("* Level 8: Formatting Time", false),

        DeveloperUpdate("* DEVELOPER UPDATES:\n" +
                "* I can now save data! Just terminate the program with \"bye\" and I'll save the data automatically!\n" +
                "* Do take note of the DATE AND TIME notations when inserting DukeTasks!", false),

        AvailableInstruction("AVAILABLE INSTRUCTIONS:\n" + // Available Instructions
                " help - Display Available Instructions\n" +
                " bye - Terminate Duke\n" +
                " list - Display current DukeTasks\n" +
                " done [Task Number] - Complete the specified task number (Specify in numeric format!) Eg: \"done 3\"\n" +
                " delete [Task Number] - Deletes the task number (Specify in numeric format!) Eg: \"delete 3\"\n" +
                "* SEE TASK INPUT INSTRUCTIONS FOR MORE ABOUT TASKS", false),

        TaskInputInstruction("TASK INPUT INSTRUCTIONS:\n" +
                "IMPORTANT: DO AVOID USING THE VERTICAL BAR \"|\" IN YOUR TASK INSTRUCTIONS\n" +
                " todo [Task Description] - Inputs a TODO DukeTask\n" +
                " deadline [Task Description] /by [Date] - Inputs a DEADLINE DukeTask, using INDICATOR /by\n" +
                " event [Task Description] /at [Date] - Inputs an EVENT DukeTask, using INDICATOR /at", false),

        TaskInputSpecification("TO NOTE:\n" +
                "FORMAT FOR DATE: \"DD/MM/YYYY hh/mm/ss\"\n" +
                " \tDD, MM, YYYY are the date, month and year respectively (IN INTEGERS)\n" +
                " \thh, mm, ss are the hour (in 24 HOUR NOTATION), minutes and seconds respectively (IN INTEGERS)", true);

        private final static String guideBreaker = "\n\n";
        private final String instruction;
        private final boolean last;

        InstructionGuide(String instruction, boolean last) {
            this.instruction = instruction;
            this.last = last;
        }

        @Override
        public String toString() {
            return instruction + (last ? "" : guideBreaker);
        }
    }

    // User Interaction Text
    private enum UserInteractionText {
        LINE("____________________________________________________________"), // Single Line for divider{

        INTRODUCTION(LINE + "\n" +
                "Hello! I am Duke, your Personal Assistant!\n" + // START OF INTRODUCTIONS
                "What can I do for you today?\n" +
                "Type \"help\" to see the available instructions!\n" +
                LINE), // END OF INTRODUCTIONS)

        INSTRUCTIONS("How to use Duke:\n" + // Guide to duke.Duke
                InstructionGuide.Level +
                InstructionGuide.DeveloperUpdate +
                InstructionGuide.AvailableInstruction +
                InstructionGuide.TaskInputInstruction +
                InstructionGuide.TaskInputSpecification), // END OF INSTRUCTIONS)

        OUTRO("Goodbye. Hope to see you soon!\n" +
                CommonString.LOGO + LINE);

        private final String value;

        UserInteractionText(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    // Reads in user command.
    // Not very efficient because we keep creating and closing scanners
    // later when the while loop is done we can do 1x setup and 1x cleaning up.
    // for now this is safer cause less bugs
    public String readCommand() {
        String output = scanner.nextLine();
        return output;
    }

    // MAIN DUKE FUNCTIONS
    public void printDukeIntro() {
        System.out.println(UserInteractionText.INTRODUCTION);
    }

    public void printDukeInstructions() {
        System.out.println(UserInteractionText.INSTRUCTIONS);
    }

    public void printDukeOutro() {
        System.out.println(UserInteractionText.OUTRO);
    }

    // COMMAND BASED
    public void printAddTask(DukeTask task, int size) {
        System.out.println("Task Added: " + task.toString());
        printTaskStatus(size);
    }

    public void printDeleteTask(DukeTask task, int size) {
        System.out.println("Alright! I'll delete this task!\n" + "Take note that this is irreversible!");
        System.out.println(task.toString());
        printTaskStatus(size);
    }

    public void printNumberedTask(DukeTask task, int num) {
        System.out.println((num + 1) + ". " + task);
    }

    public void printMarkAsDone(DukeTask task, int size) {
        System.out.println("Alright! I'll mark this task as done!");
        System.out.println(task.toString());
        printTaskStatus(size);
    }

    // GENERAL USE
    public void printLine() {
        System.out.println(UserInteractionText.LINE);
    }

    public void printTaskStatus(int size) {
        String taskSize = size == 1 ? " task" : " tasks";
        System.out.println("You now have " + size + taskSize);
    }

}
