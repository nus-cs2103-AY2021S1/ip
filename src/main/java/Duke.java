import java.io.*;
import java.util.ArrayList;

public class Duke {

    public static final Reader READER = new InputStreamReader(System.in);
    public static final BufferedReader BUFFERED_READER = new BufferedReader(READER);
    public static final ArrayList<Task> STORAGE = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        executeProgram();
    }

    private static void executeProgram() throws Exception {
        printWelcomeMessage();
        String command = BUFFERED_READER.readLine();
        parseCommands(command);
        printByeMessage();
    }

    private static void printWelcomeMessage() {
        System.out.println("Hello there! My name is Duke." + "\nHow may I assist you today?");
        printBorder();
    }

    private static void parseCommands(String command) throws Exception {
        FileReader fileToRead = new FileReader("data/duke.txt");
        BufferedReader bufferedReader = new BufferedReader(fileToRead);
        String inputData = bufferedReader.readLine();
        File file = new File("data/duke.txt");
        boolean flag = true;

        while (inputData != null) {
            if (flag) {
                System.out.println("Loading data from duke.txt..." + "\n");
                flag = false;
            }

            String taskDate;
            String[] fileTokens = inputData.split("\\|");
            int doneFlag = Integer.valueOf(fileTokens[1]);
            String descriptions = fileTokens[2];

            switch (fileTokens[0]) {

                case "D":
                    taskDate = fileTokens[3];
                    Deadline newDeadline = new Deadline(descriptions, taskDate, doneFlag);
                    STORAGE.add(newDeadline);
                    break;

                case "E":
                    taskDate = fileTokens[3];
                    Event newEvent = new Event(descriptions, taskDate, doneFlag);
                    STORAGE.add(newEvent);
                    break;

                case "T":
                    Todo newTodo = new Todo(descriptions, doneFlag);
                    STORAGE.add(newTodo);
                    break;

                default:
                    break;
            }
            inputData = bufferedReader.readLine();
        }
        bufferedReader.close();

        while (command != null && !command.equals("bye")) {
            String[] userInputArray = command.split(" ");
            String commandCheck = userInputArray[0];
            Task taskToAdd;
            int numOfInput = userInputArray.length;

            try {
                switch (commandCheck) {
                    case "list":
                        if (STORAGE.isEmpty()) {
                            throw new DukeException("There is nothing to list as the list is currently empty.");
                        } else {
                            int index = 1;
                            System.out.println("These are the tasks in your list:");
                            for (Task task: STORAGE) {
                                String str = task.toString();
                                System.out.println(index + ". " + str);
                                index++;
                            }
                        }
                        printBorder();
                        break;

                    case "done":
                        try {
                            if (numOfInput < 2) {
                                throw new DukeException("Please specify which task you have completed. Eg. done 1");
                            }

                            try {
                                Integer.parseInt(userInputArray[1]);
                            } catch (NumberFormatException error) {
                                throw new DukeException("Please input task index as a valid integer.");
                            }

                            String temp = command.split(" ")[1];
                            int indexOfTemp = Integer.parseInt(temp);
                            int currentTaskIndex = indexOfTemp - 1;

                            if (currentTaskIndex < 0 || currentTaskIndex >= STORAGE.size()) {
                                throw new DukeException("The task number" + " (" + (currentTaskIndex + 1) + ") " +
                                                        "that you have input can not be found in the list.");
                            }

                            Task currentTask = STORAGE.get(currentTaskIndex);
                            STORAGE.get(currentTaskIndex).setDone();
                            System.out.println("Great job! This task has been marked as done:");
                            System.out.println(" " + currentTask.toString());
                        } catch (DukeException error) {
                            System.err.println(error.getMessage());
                        }
                        printBorder();
                        break;

                    case "todo":
                        try {
                            if (numOfInput == 1) {
                                throw new DukeException("☹ Oh no! The description of a todo task cannot be empty.");
                            }
                            StringBuilder todoString = new StringBuilder();
                            int j = 1;
                            while (j < numOfInput) {
                                todoString.append(userInputArray[j]);
                                todoString.append(" ");
                                j++;
                            }
                            taskToAdd = new Todo(todoString.toString().trim());
                            printGotIt();
                            printTask(taskToAdd);
                            STORAGE.add(taskToAdd);
                            printTaskCount();
                        } catch (DukeException error) {
                            System.err.println(error.getMessage());
                        }
                        break;

                    case "deadline":
                        try {
                            if (numOfInput == 1) {
                                throw new DukeException("☹ Oh no! The description of a deadline task cannot be empty.");
                            }

                            if (numOfInput < 4) {
                                throw new DukeException("☹ Oh no! The correct way to log a deadline is: (deadline) "
                                                        + "(description) (/by) (date)");
                            }

                            StringBuilder deadlineString = new StringBuilder();
                            StringBuilder deadlineDate = new StringBuilder();
                            boolean checkForDate = false;
                            int m = 1;
                            while (m < numOfInput) {
                                if (userInputArray[m].equals("/by")) {
                                    checkForDate = true;
                                } else {
                                    if (!checkForDate) {
                                        deadlineString.append(userInputArray[m]);
                                        deadlineString.append(" ");
                                    } else {
                                        deadlineDate.append(userInputArray[m]);
                                        deadlineDate.append(" ");
                                    }
                                }
                                m++;
                            }
                            taskToAdd = new Deadline(deadlineString.toString().trim(), deadlineDate.toString().trim());
                            printGotIt();
                            printTask(taskToAdd);
                            STORAGE.add(taskToAdd);
                            printTaskCount();
                        } catch (DukeException error) {
                            System.err.println(error.getMessage());
                        }
                        break;

                    case "event":
                        try {
                            if (numOfInput == 1) {
                                throw new DukeException("☹ Oh no! The description of an event task cannot be empty.");
                            }

                            if (numOfInput < 4) {
                                throw new DukeException("☹ Oh no! The correct way to log an event is: (event) " +
                                                        "(description) (/at) (date)");
                            }

                            StringBuilder eventString = new StringBuilder();
                            StringBuilder eventDate = new StringBuilder(" ");
                            boolean checkForEvent = false;
                            int z = 1;
                            while (z < numOfInput) {
                                if (userInputArray[z].equals("/at")) {
                                    checkForEvent = true;
                                } else {
                                    if (!checkForEvent) {
                                        eventString.append(userInputArray[z]);
                                        eventString.append(" ");
                                    } else {
                                        eventDate.append(userInputArray[z]);
                                        eventDate.append(" ");
                                    }
                                }
                                z++;
                            }
                            taskToAdd = new Event(eventString.toString().trim(), eventDate.toString().trim());
                            printGotIt();
                            printTask(taskToAdd);
                            STORAGE.add(taskToAdd);
                            printTaskCount();
                        } catch (DukeException error) {
                            System.err.println(error.getMessage());
                        }
                        break;

                    case "delete":
                        try {
                            if (numOfInput < 2) {
                                throw new DukeException("Please specify which task you want to delete. Eg. delete 1");
                            }

                            try {
                                Integer.parseInt(userInputArray[1]);
                            } catch (NumberFormatException error) {
                                throw new DukeException("Please input task index as a valid integer.");
                            }

                            String tempStr = command.split(" ")[1];
                            int indexOfTempStr = Integer.parseInt(tempStr);
                            int indexToRemove = indexOfTempStr - 1;
                            int arraySize = STORAGE.size();

                            if (indexToRemove < 0 || indexToRemove >= arraySize) {
                                throw new DukeException("The task number" + " (" + (indexToRemove + 1) + ") " +
                                                        "that you want to delete can not be found in the list.");
                            } else {
                                Task curr = STORAGE.get(indexToRemove);
                                printDelete();
                                System.out.println(" " + curr.toString());
                                STORAGE.remove(indexToRemove);
                                Task.totalTasks = Task.totalTasks - 1;
                                printTaskCount();
                            }
                        } catch (DukeException error) {
                            System.err.println(error.getMessage());
                        }
                        break;

                    default:
                        throw new DukeException("Catastrophe detected! I'm sorry, but '" + command
                                                + "' is not within my realm of knowledge. ☹");
                }
            } catch (DukeException error) {
                System.err.println(error.getMessage());
            }
            command = BUFFERED_READER.readLine();
        }
        READER.close();
        String filePath = file.getAbsolutePath();
        saveToDisk(filePath);
    }

    private static void saveToDisk(String path) throws IOException {
        String outputLine = "";
        FileWriter writer = new FileWriter(path);
        int sizeOfArray = STORAGE.size();
        int index = 0;
        int isDone;

        while (index < sizeOfArray) {
            Task task = STORAGE.get(index);

            if (task.isDone) {
                isDone = 1;
            } else {
                isDone = 0;
            }

            if ((task.getClass().equals(Todo.class))) {
                outputLine = "T" + "|" + isDone + "|" + task.description;
            } else if ((task.getClass().equals(Deadline.class))) {
                Deadline deadlineTask = (Deadline) task;
                outputLine = "D" + "|" + isDone + "|" + deadlineTask.description + "|" + deadlineTask.date;
            } else if ((task.getClass().equals(Event.class))) {
                Event eventTask = (Event) task;
                outputLine = "E" + "|" + isDone + "|" + eventTask.description + "|" + eventTask.timing;
            } else {
                // Do nothing
            }
            String separator = System.lineSeparator();
            writer.write(outputLine + separator);
            index++;
        }
        writer.close();
    }

    private static void printTask(Task taskToAdd) {
        System.out.println(" " + taskToAdd.toString());
    }

    private static void printGotIt() {
        System.out.println("Thank you for your input. The following task has been added to the list:");
    }

    private static void printTaskCount() {
        if (Task.totalTasks > 1) {
            System.out.println("You have a total of " + Task.totalTasks + " tasks in the list.");
        } else {
            System.out.println("You have a total of " + Task.totalTasks + " task in the list.");
        }
        printBorder();
    }

    private static void printBorder() {
        System.out.print("---------------------------\n");
    }

    private static void printDelete() {
        System.out.println("The following task has been successfully removed:");
    }

    private static void printByeMessage() {
        System.out.println("Goodbye. Hope to see you again soon!");
    }
}
