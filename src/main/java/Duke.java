import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    // Constants
    private static final String LINE = "____________________________________________________________";
    private static final String LOGO =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";;

    // INSTRUCTIONS
    private static final String BYE = "bye";
    private static final String HELP = "help";
    private static final String LIST = "list";
    private static final String DONE = "done";

    // User Interaction Text
    // Standardise by using only LINE here. NEWLINE to be used for functions
    // Maybe can include Username in introductions
    private static final String INTRODUCTION =
            LINE + "\n" +
                    "Hello! I am Duke, your Personal Assistant!\n" + // START OF INTRODUCTIONS
                    "What can I do for you today?\n" +
                    "* Type \"help\" to see the available instructions!\n" +
                    LINE; // END OF INTRODUCTIONS

    private static final String INSTRUCTIONS =
            "How to use Duke:\n" +
                    "* Level 3: Tasking. Except for the available instructions, whatever you type is added as a DukeTask!\n" +
                    "* Do try to avoid ambiguous inputs, such as [keywords] + [random gibberish]\n" +
                    "\tI'm not very smart (yet) :( \n\n" +
                    "AVAILABLE INSTRUCTIONS:\n" +
                    " help - Display Available Instructions\n" +
                    " bye - Terminate Duke\n" +
                    " list - Display current DukeTasks\n" +
                    " done [Task Number] - Complete the specified task number (Specify in numeric format!) Eg: \"done 3\"\n" +
                    LINE; // END OF INSTRUCTIONS

    private static final String OUTRO =
            "Goodbye. Hope to see you soon!\n" +
            LOGO + LINE;

    // MAIN FUNCTION
    public static void main(String[] args) {
        // Initialisation of Duke
        System.out.println(INTRODUCTION);

        Scanner sc = new Scanner(System.in);
        DukeTask[] inputList = new DukeTask[100];
        int counter = 0;

        // Echo User Instructions
        instructionLoop: while (sc.hasNextLine()) { // labelling of while-loop
            String instruction = sc.nextLine().trim();
            int instrLen = instruction.split(" ").length;
            String indicator = instruction.split(" ")[0]; // indicates if instruction or not

            boolean addInstruction = false; // assume it is an instruction by default
            String tag = ""; // used for ambiguous inputs

            switch (indicator) {
                case "": // no inputs
                    System.out.println("Please key in a valid input!");
                    break;
                case BYE:
                    if (instrLen == 1) {
                        break instructionLoop;
                    } else {
                        addInstruction = true;
                        tag = BYE;
                    }
                    break;
                case HELP:
                    if (instrLen == 1) {
                        System.out.println(INSTRUCTIONS);
                    } else {
                        addInstruction = true;
                        tag = HELP;
                    }
                    break;
                case LIST:
                    if (instrLen == 1) {
                        System.out.println("Your DukeTasks:");
                        if (counter == 0) {
                            System.out.println("You have no DukeTasks!");
                        } else {
                            for (int i = 0; i < counter; i++) {
                                System.out.println((i + 1) + ". " + inputList[i]);
                            }
                        }
                        System.out.println(LINE);
                    } else {
                        addInstruction = true;
                        tag = LIST;
                    }
                    break;
                case DONE:
                    if (instrLen == 2) {
                        if (isNumeric(instruction.split(" ")[1])) { // second word in instruction is an integer
                            int idx = Integer.parseInt(instruction.split(" ")[1]) - 1; // get index in list

                            if (idx < 0 || idx >= counter) { // check if loc is an existing DukeTask inside the array inputList
                                System.out.println("Invalid Task Number!");
                            } else if (inputList[idx].getDoneStatus()) { // check if inputList[loc] is already completed
                                System.out.println("Task is already done!");
                            } else {
                                System.out.println("Alright! I'll mark this task as done!");
                                inputList[idx].markAsDone();
                                System.out.println(inputList[idx]);
                            }
                            System.out.println(LINE);
                            break;
                        }
                    } // if len != 2 or the input is not an integer
                    addInstruction = true;
                    tag = DONE;
                    break;
                default:
                    addInstruction = true;
                    tag = "";
            }

            if (addInstruction) {
                if (!tag.equals("")) {
                    System.out.printf("It seems your DukeTask starts with a \"%s\", try not to use it as \"%s\" is a keyword!\n%n", tag, tag);
                }
                inputList[counter] = new DukeTask(instruction);
                counter++;
                System.out.println("Task Added: " + instruction + "\n" + LINE);
            }
        }

        // OUTRO
        System.out.println(OUTRO);
    }

    // EXTRA FUNCTIONS FOR ASSISTANCE

    // checks if the input string can be parsed into an Integer or not
    private static boolean isNumeric (String instruction) {
        try {
            int output = Integer.parseInt(instruction);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}