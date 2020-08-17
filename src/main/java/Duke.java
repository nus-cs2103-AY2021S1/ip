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
                    + "|____/ \\__,_|_|\\_\\___|\n";

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
    private static final String UNKNOWN = "unknown";
    private static final String DELETE = "delete";

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
                    "* Level 6: Allowing Delete. Follow the Task Input Instructions for more\n" +
                    "* Do try to avoid ambiguous inputs, " +
                        "such as [keywords] + [random gibberish] as I cannot recognise them!\n" +
                    "\tI'm not very smart (yet) :(\n\n" +
                    "TASK INPUT INSTRUCTIONS:\n" + // Task Input Instructions
                    " todo [Task Description] - Inputs a TODO DukeTask\n" +
                    " deadline [Task Description] /by [Date] - Inputs a DEADLINE DukeTask, along with INDICATOR /by\n" +
                    " event [Task Description] /at [Date] - Inputs an EVENT DukeTask, along with INDICATOR /at\n\n" +
                    "AVAILABLE INSTRUCTIONS:\n" + // Available Instructions
                    " help - Display Available Instructions\n" +
                    " bye - Terminate Duke\n" +
                    " list - Display current DukeTasks\n" +
                    " done [Task Number] - Complete the specified task number (Specify in numeric format!) Eg: \"done 3\"\n" +
                    " delete [Task Number] - Deletes the task number (Specify in numeric format!) Eg: \"delete 3\""; // END OF INSTRUCTIONS

    private static final String OUTRO =
            "Goodbye. Hope to see you soon!\n" +
            LOGO + LINE;

    // MAIN FUNCTION
    public static void main(String[] args) {
        // Initialisation of Duke
        System.out.println(INTRODUCTION);

        // Execute Duke
        invokeDuke();

        // OUTRO
        System.out.println(OUTRO);
    }

    // HEAVY LIFTING LOGIC FOR DUKE BOT
    private static void invokeDuke() {

        // Setup
        Scanner sc = new Scanner(System.in);
        ArrayList<DukeTask> inputList = new ArrayList<>();

        // User Instructions with internal error handling
        // With errors, the instructionLoop should still continue to run hence try-catch statements
        // are to be handled the loop
        instructionLoop: while (sc.hasNextLine()) { // labelling of while-loop

            // Instruction Setup
            String instruction = sc.nextLine().trim();
            String[] instructionArray = instruction.split(" ");
            int instrLen = instructionArray.length;
            String indicator = instruction.split(" ")[0]; // indicates if instruction or not

            try {
                switch (indicator) {
                    case BYE:
                        if (instrLen == 1) {
                            break instructionLoop;
                        } else {
                            throw new InvalidInstructionException(BYE);
                        }
                    case HELP:
                        if (instrLen == 1) {
                            System.out.println(INSTRUCTIONS);
                        } else {
                            throw new InvalidInstructionException(HELP);
                        }
                        break;
                    case LIST:
                        if (instrLen == 1) {
                            System.out.println("Your DukeTasks:");
                            if (inputList.size() == 0) {
                                System.out.println("You have no DukeTasks!");
                            } else {
                                for (int i = 0; i < inputList.size(); i++) {
                                    System.out.println((i + 1) + ". " + inputList.get(i));
                                }
                                System.out.println(getTaskStatus(inputList, inputList.size()));
                            }
                        } else {
                            throw new InvalidInstructionException(LIST);
                        }
                        break;
                    case DONE:
                        if (instrLen == 2) {
                            if (isNumeric(instruction.split(" ")[1])) { // second word in instruction is an integer
                                int idx = Integer.parseInt(instruction.split(" ")[1]) - 1; // get index in list

                                if (idx < 0 || idx >= inputList.size()) { // check if loc is an existing DukeTask inside the array inputList
                                    throw new InvalidInstructionException(DONE + ": Invalid Task Number");
                                } else if (inputList.get(idx).getDoneStatus()) { // check if inputList[loc] is already completed
                                    throw new InvalidInstructionException(DONE + ": Task is already done!");
                                } else {
                                    System.out.println("Alright! I'll mark this task as done!");
                                    inputList.get(idx).markAsDone();
                                    System.out.println(inputList.get(idx));
                                    System.out.println(getTaskStatus(inputList, inputList.size()));
                                }
                                break;
                            }
                        } // if len != 2 or the input is not an integer
                        throw new InvalidInstructionException(DONE);
                    case DELETE:
                        if (instrLen == 2) {
                            if (isNumeric(instruction.split(" ")[1])) { // second word in instruction is an integer
                                int idx = Integer.parseInt(instruction.split(" ")[1]) - 1; // get index in list

                                if (idx < 0 || idx >= inputList.size()) { // check if loc is an existing DukeTask inside the array inputList
                                    throw new InvalidInstructionException(DELETE + ": Invalid Task Number");
                                } else {
                                    System.out.println("Alright! I'll delete this task!\n" + "Take note that this is irreversible!");
                                    System.out.println(inputList.remove(idx));
                                    System.out.println(getTaskSize(inputList));
                                }
                                break;
                            }
                        } // if len != 2 or the input is not an integer
                        throw new InvalidInstructionException(DELETE);
                    case TODO:
                        if (instrLen == 1) {
                            throw new MissingFieldException(TODO + ": Description");
                        } else {
                            TodoTask todotask = new TodoTask(mergeArray(instructionArray, 1, instrLen));
                            inputList.add(todotask);
                            System.out.println("Task Added: " + todotask.toString());
                            System.out.println(getTaskSize(inputList));
                        }
                        break;
                    case DEADLINE:
                        int byIndex = findIndex(instructionArray, BY_INDICATOR);
                        if (byIndex == -1) {
                            throw new InvalidFormatException(DEADLINE);
                        } else {
                            String deadlineDesc = mergeArray(instructionArray, 1, byIndex);
                            String deadlineDatetime = mergeArray(instructionArray, byIndex + 1, instrLen);
                            if (deadlineDesc.equals("")) {
                                throw new MissingFieldException(DEADLINE + ": Description");
                            } else if (deadlineDatetime.equals("")) {
                                throw new MissingFieldException(DEADLINE + ": Date and Time");
                            } else {
                                DeadlineTask deadlinetask = new DeadlineTask(deadlineDesc, deadlineDatetime);
                                inputList.add(deadlinetask);
                                System.out.println("Task Added: " + deadlinetask.toString());
                                System.out.println(getTaskSize(inputList));
                            }
                        }
                        break;
                    case EVENT:
                        int atIndex = findIndex(instructionArray, AT_INDICATOR);
                        if (atIndex == -1) {
                            throw new InvalidFormatException(EVENT);
                        } else {
                            String eventDesc = mergeArray(instructionArray, 1, atIndex);
                            String eventDatetime = mergeArray(instructionArray, atIndex + 1, instrLen);
                            if (eventDesc.equals("")) {
                                throw new MissingFieldException(EVENT + ": Description");
                            } else if (eventDatetime.equals("")) {
                                throw new MissingFieldException(EVENT + ": Date and Time");
                            } else {
                                EventTask eventTask = new EventTask(eventDesc, eventDatetime);
                                inputList.add(eventTask);
                                System.out.println("Task Added: " + eventTask.toString());
                                System.out.println(getTaskSize(inputList));
                            }
                        }
                        break;
                    default:
                        throw new InvalidInstructionException(UNKNOWN);
                }
            } catch (InvalidInstructionException | InvalidFormatException | MissingFieldException exception) {
                System.out.println(exception);
            }

            System.out.println(LINE);
        }

        // Cleaning up before terminating invokeDuke()
        sc.close();
    }

    // EXTRA FUNCTIONS FOR ASSISTANCE

    // checks if the input string can be parsed into an Integer or not
    private static boolean isNumeric (String instruction) {
        try {
            Integer.parseInt(instruction);
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

    private static String getTaskStatus(ArrayList<DukeTask> tasks, int counter) {
        int done = 0;
        int notDone = 0;
        for (int i = 0; i < counter; i++) {
            if (tasks.get(i).getDoneStatus()) {
                done++;
            } else {
                notDone++;
            }
        }
        return String.format("You now have %d %s done, and %d %s not done", done, done == 1 ? "task" : "tasks", notDone, notDone == 1 ? "task" : "tasks");
    }

    private static String getTaskSize(ArrayList<DukeTask> tasks) {
        return String.format("You now have %d %s", tasks.size(), tasks.size() == 1 ? "task" : "tasks");
    }
}