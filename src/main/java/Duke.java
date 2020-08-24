import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    // PRIVATE ENUMS:
    // Contain texts that are for various purposes explained below
    // Abstracted from static constants to make them easy to maintain

    // Instruction Guides split up the various parts of the "help" function guide
    // Splitting them up into parts makes it easier to edit according to the specific guide in the future
    private enum InstructionGuide {
        // For formatting purposes, except for the last guide, the guides must end with a guideBreaker
        // It splits them into paragraphs
        LevelInformation("* Level 7: Saving Data. Follow the Task Input Instructions for more\n" +
                "* Do try to avoid ambiguous inputs, " +
                "such as [keywords] + [random gibberish] as I cannot recognise them!\n" +
                "\tI'm not very smart (yet) :(\n" +
                "* I can now save data! Just terminate the program with " +
                "\"bye\" and I'll save the data automatically!", false),

        TaskInput("IMPORTANT: DO AVOID USING THE VERTICAL BAR \"|\" IN YOUR TASK INSTRUCTIONS\n" +
                " todo [Task Description] - Inputs a TODO DukeTask\n" +
                " deadline [Task Description] /by [Date] - Inputs a DEADLINE DukeTask, along with INDICATOR /by\n" +
                " event [Task Description] /at [Date] - Inputs an EVENT DukeTask, along with INDICATOR /at", false),

        AvailableInstruction(" help - Display Available Instructions\n" +
                " bye - Terminate Duke\n" +
                " list - Display current DukeTasks\n" +
                " done [Task Number] - Complete the specified task number (Specify in numeric format!) Eg: \"done 3\"\n" +
                " delete [Task Number] - Deletes the task number (Specify in numeric format!) Eg: \"delete 3\"", true);

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
        INTRODUCTION(CommonString.LINE + "\n" +
                "Hello! I am Duke, your Personal Assistant!\n" + // START OF INTRODUCTIONS
                "What can I do for you today?\n" +
                "Type \"help\" to see the available instructions!\n" +
                CommonString.LINE), // END OF INTRODUCTIONS)

        INSTRUCTIONS("How to use Duke:\n" + // Guide to Duke
                InstructionGuide.LevelInformation +
                "TASK INPUT INSTRUCTIONS:\n" + // Task Input Instructions
                InstructionGuide.TaskInput +
                "AVAILABLE INSTRUCTIONS:\n" + // Available Instructions
                InstructionGuide.AvailableInstruction), // END OF INSTRUCTIONS)

        OUTRO("Goodbye. Hope to see you soon!\n" +
                CommonString.LOGO + CommonString.LINE);

        private final String value;

        UserInteractionText(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    // INSTRUCTIONS and related CONSTANTS
    // Kept as Strings because of certain constants (indicators) that are related to certain instructions
    // Convenient to use - final keeps them as constant values
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

    // FILE AND DATA STORAGE
    private static final String PATH = "data/dukeData.txt"; // structure: ip/data/dukeData.txt
    private static final String FILE_DATA_SEPARATOR = "|";

    // MAIN FUNCTION
    public static void main(String[] args) {
        // Initialisation of Duke
        System.out.println(UserInteractionText.INTRODUCTION);

        // Execute Duke
        invokeDuke();

        // OUTRO
        System.out.println(UserInteractionText.OUTRO);
    }

    // HEAVY LIFTING LOGIC FOR DUKE BOT
    private static void invokeDuke() {

        // Setup
        Scanner sc = new Scanner(System.in);
        ArrayList<DukeTask> inputList = new ArrayList<>();
        try {
            inputList = loadData();
        } catch (FileNotFoundException e) {
            System.out.println("Data file is not found\n" + e.getMessage());
            System.out.println(CommonString.LINE);
        }

        // User Instructions with internal error handling
        // With errors, the instructionLoop should still continue to run hence try-catch statements
        // are to be handled the loop
        instructionLoop:
        while (sc.hasNextLine()) { // labelling of while-loop

            // Instruction Setup - split by whitespace to check
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
                            System.out.println(UserInteractionText.INSTRUCTIONS);
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
                                System.out.println(getTaskStatus(inputList));
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
                                    System.out.println(getTaskStatus(inputList));
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

            System.out.println(CommonString.LINE);
        }

        // Cleaning up before terminating invokeDuke()
        sc.close(); // close the Scanner
        try {
            saveData(inputList); // Save the data into data file
        } catch (IOException e) {
            System.out.println("Cannot save data:\n" + e.getMessage());
            System.out.println(CommonString.LINE);
        }
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

    // Merges the String array into one string, from index start, until (not including) index end
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

    private static String getTaskStatus(ArrayList<DukeTask> tasks) {
        int done = 0;
        int notDone = 0;
        for (DukeTask task : tasks) {
            if (task.getDoneStatus()) {
                done++;
            } else {
                notDone++;
            }
        }
        return String.format("You now have %d %s done, and %d %s not done",
                done, done == 1 ? "task" : "tasks", notDone, notDone == 1 ? "task" : "tasks");
    }

    private static String getTaskSize(ArrayList<DukeTask> tasks) {
        return String.format("You now have %d %s", tasks.size(), tasks.size() == 1 ? "task" : "tasks");
    }

    private static ArrayList<DukeTask> loadData() throws FileNotFoundException {
        ArrayList<DukeTask> dataList = new ArrayList<>();

        File dataFile = new File(PATH);
        // load parent files
        File directory = new File(dataFile.getParentFile().getAbsolutePath());
        directory.mkdirs();

        // generate data file
        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Problem with creating data file\n" + e.getMessage());
        }

        // read from data file
        if (dataFile.exists()) {
            Scanner fileScanner = new Scanner(dataFile);
            while (fileScanner.hasNextLine()) {
                // regenerate the DukeTasks
                String savedTask = fileScanner.nextLine();
                String[] taskData = savedTask.split("\\|");
                DukeTask task;
                switch (taskData[0]) {
                    case "T":
                        task = new TodoTask(taskData[2]);
                        break;
                    case "E":
                        task = new EventTask(taskData[2], taskData[3]);
                        break;
                    default: // "D"
                        task = new DeadlineTask(taskData[2], taskData[3]);

                }
                if (taskData[1].equals("1")) {
                    task.markAsDone();
                }
                dataList.add(task);
            }
        }

        return dataList;
    }

    // SAVING DATA FOR OUTPUT
    // FORMAT IS GIVEN BY:
    // [TYPE]|[DONE]|[DESCRIPTION]|[DATETIME (if applicable)]
    // TYPE: T,E,D
    // DONE: 1 or 0
    private static void saveData(ArrayList<DukeTask> dataList) throws IOException {
        StringBuilder dataString = new StringBuilder();
        for (DukeTask task : dataList) {
            String addition = "";
            if (task instanceof TodoTask) {
                addition = "T" + FILE_DATA_SEPARATOR
                        + (task.getDoneStatus() ? "1" : "0") + FILE_DATA_SEPARATOR
                        + task.getDescription();
            } else if (task instanceof EventTask) {
                addition = "E" + FILE_DATA_SEPARATOR
                        + (task.getDoneStatus() ? "1" : "0") + FILE_DATA_SEPARATOR
                        + task.getDescription() + FILE_DATA_SEPARATOR
                        + ((EventTask) task).getDatetime();
            } else if (task instanceof DeadlineTask) {
                addition = "D" + FILE_DATA_SEPARATOR
                        + (task.getDoneStatus() ? "1" : "0") + FILE_DATA_SEPARATOR
                        + task.getDescription() + FILE_DATA_SEPARATOR
                        + ((DeadlineTask) task).getDatetime();
            }
            dataString.append(addition).append(System.lineSeparator());
        }

        String output = dataString.toString();

        FileWriter writer = new FileWriter(PATH);
        writer.write(output);
        writer.close();
    }
}