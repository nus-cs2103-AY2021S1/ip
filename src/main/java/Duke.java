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
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String BY_INDICATOR = "/by";
    private static final String EVENT = "event";
    private static final String AT_INDICATOR = "/at";

    // User Interaction Text
    // Standardise by using only LINE here. NEWLINE to be used for functions
    // Maybe can include Username in introductions
    private static final String INTRODUCTION =
            LINE + "\n" +
                    "Hello! I am Duke, your Personal Assistant!\n" + // START OF INTRODUCTIONS
                    "What can I do for you today?\n" +
                    "Type \"help\" to see the available instructions!\n" +
                    LINE; // END OF INTRODUCTIONS

    private static final String INSTRUCTIONS =
            "How to use Duke:\n" + // Guide to Duke
                    "* Level 4: Specific Tasks. Follow the Task Input Instructions for more\n" +
                    "* Do try to avoid ambiguous inputs, " +
                        "such as [keywords] + [random gibberish] as I cannot recognise them!\n" +
                    "\tI'm not very smart (yet) :( \n\n" +
                    "TASK INPUT INSTRUCTIONS:\n" + // Task Input Instructions
                    " todo [Task Description] - Inputs a TODO DukeTask\n" +
                    " deadline [Task Description] /by [Date] - Inputs a DEADLINE DukeTask, along with INDICATOR /by\n" +
                    " event [Task Description] /at [Date] - Inputs an EVENT DukeTask, along with INDICATOR /at\n\n" +
                    "AVAILABLE INSTRUCTIONS:\n" + // Available Instructions
                    " help - Display Available Instructions\n" +
                    " bye - Terminate Duke\n" +
                    " list - Display current DukeTasks\n" +
                    " done [Task Number] - Complete the specified task number (Specify in numeric format!) Eg: \"done 3\""; // END OF INSTRUCTIONS

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

        // User Instructions
        instructionLoop: while (sc.hasNextLine()) { // labelling of while-loop
            String instruction = sc.nextLine().trim();
            String[] instructionArray = instruction.split(" ");
            int instrLen = instructionArray.length;
            String indicator = instruction.split(" ")[0]; // indicates if instruction or not

            switch (indicator) {
                case BYE:
                    if (instrLen == 1) {
                        break instructionLoop;
                    } else {
                        System.out.println("Please key in a valid instruction! (bye)");
                    }
                    break;
                case HELP:
                    if (instrLen == 1) {
                        System.out.println(INSTRUCTIONS);
                    } else {
                        System.out.println("Please key in a valid instruction! (help)");
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
                            System.out.println(getTaskStatus(inputList, counter));
                        }
                    } else {
                        System.out.println("Please key in a valid instruction! (list)");
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
                            break;
                        }
                    } // if len != 2 or the input is not an integer
                    System.out.println("Please key in a valid instruction! (done)");
                    break;
                case TODO:
                    if (instrLen == 1) {
                        System.out.println("Please enter description!");
                    } else {
                        TodoTask todotask = new TodoTask(mergeArray(instructionArray, 1, instrLen));
                        inputList[counter] = todotask;
                        counter++;
                        System.out.println("Task Added: " + todotask.toString());
                    }
                    break;
                case DEADLINE:
                    int byIndex = findIndex(instructionArray, BY_INDICATOR);
                    if (byIndex == -1) {
                        System.out.println("Please use correct format for deadline tasks! [/by]");
                    } else {
                        String deadlineDesc = mergeArray(instructionArray, 1, byIndex);
                        String deadlineDatetime = mergeArray(instructionArray, byIndex + 1, instrLen);
                        if (deadlineDesc.equals("")) {
                            System.out.println("Please enter description");
                        } else if (deadlineDatetime.equals("")) {
                            System.out.println("Please enter date and time");
                        } else {
                            DeadlineTask deadlinetask = new DeadlineTask(deadlineDesc, deadlineDatetime);
                            inputList[counter] = deadlinetask;
                            counter++;
                            System.out.println("Task Added: " + deadlinetask.toString());
                        }
                    }
                    break;
                case EVENT:
                    int atIndex = findIndex(instructionArray, AT_INDICATOR);
                    if (atIndex == -1) {
                        System.out.println("Please use correct format for deadline tasks! [/at]");
                    } else {
                        String eventDesc = mergeArray(instructionArray, 1, atIndex);
                        String eventDatetime = mergeArray(instructionArray, atIndex + 1, instrLen);
                        if (eventDesc.equals("")) {
                            System.out.println("Please enter description");
                        } else if (eventDatetime.equals("")) {
                            System.out.println("Please enter date and time");
                        } else {
                            EventTask eventTask = new EventTask(eventDesc, eventDatetime);
                            inputList[counter] = eventTask;
                            counter++;
                            System.out.println("Task Added: " + eventTask.toString());
                        }
                    }
                    break;
                default:
                    System.out.println("Please key in a valid instruction! (unknown)");
            }
            System.out.println(LINE);
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

    // Merges the String array into one string, from index 1, until (not including) index end
    private static String mergeArray(String[] array, int start, int end) {
        StringBuilder output = new StringBuilder();
        for (int i = start; i < end; i++) {
            output.append(array[i]).append(" ");
        }
        return output.toString().trim();
    }

    private static int findIndex(String[] array, String regex) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(regex)) {
                return i;
            }
        }
        return -1;
    }

    private static String getTaskStatus(DukeTask[] tasks, int counter) {
        int done = 0;
        int notDone = 0;
        for (int i = 0; i < counter; i++) {
            if (tasks[i].getDoneStatus()) {
                done++;
            } else {
                notDone++;
            }
        }
        return String.format("You now have %d %s done, and %d %s not done", done, done == 1 ? "task" : "tasks", notDone, notDone == 1 ? "task" : "tasks");
    }
}