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
        try {
            FileReader fileToRead = new FileReader("data/duke.txt");
            BufferedReader bufferedReader = new BufferedReader(fileToRead);
            String inputData = bufferedReader.readLine();
            File file = new File("data/duke.txt");
            boolean flagCheck = true;
            boolean fileIsChanged = false;

            while (inputData != null) {
                if (flagCheck) {
                    System.out.println("Loading data from duke.txt..." + "\n");
                    flagCheck = false;
                }

                String taskDate;
                String taskTime;
                String[] dateTimeArray;
                String[] userInputArray = inputData.split("\\|");
                int boolDone = Integer.parseInt(userInputArray[1]);
                String descriptions = userInputArray[2];

                switch (userInputArray[0]) {

                    case "D":
                        dateTimeArray = userInputArray[3].split(" ");
                        taskDate = dateTimeArray[0];
                        taskTime = dateTimeArray[1];
                        Deadline newDeadline = new Deadline(boolDone, descriptions, taskDate, taskTime);
                        STORAGE.add(newDeadline);
                        break;

                    case "E":
                        dateTimeArray = userInputArray[3].split(" ");
                        taskDate = dateTimeArray[0];
                        taskTime = dateTimeArray[1];
                        Event newEvent = new Event(boolDone, descriptions, taskDate, taskTime);
                        STORAGE.add(newEvent);
                        break;

                    case "T":
                        Todo newTodo = new Todo(descriptions, boolDone);
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
                                for (Task task : STORAGE) {
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
                                fileIsChanged = true;
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
                                fileIsChanged = true;
                                printTaskCount();
                            } catch (DukeException error) {
                                System.err.println(error.getMessage());
                            }
                            break;

                        case "deadline":
                            try {
                                if (numOfInput == 1) {
                                    throw new DukeException("☹ Oh no! The description of a deadline task " +
                                            "cannot be empty.");
                                }

                                if (numOfInput < 4) {
                                    throw new DukeException("☹ Oh no! The correct way to log a deadline is: (deadline) "
                                            + "(description) (/by) (date)");
                                }

                                StringBuilder deadlineString = new StringBuilder();
                                StringBuilder dateField = new StringBuilder();
                                StringBuilder timingField = new StringBuilder();
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
                                            if (m == numOfInput - 1) {
                                                timingField.append(userInputArray[m]);
                                            } else {
                                                dateField.append(userInputArray[m]);
                                            }
                                        }
                                    }
                                    m++;
                                }
                                taskToAdd = new Deadline(deadlineString.toString().trim(), dateField.toString().trim(),
                                                         timingField.toString().trim());
                                printGotIt();
                                printTask(taskToAdd);
                                STORAGE.add(taskToAdd);
                                fileIsChanged = true;
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
                                StringBuilder eventDateField = new StringBuilder();
                                StringBuilder eventTimingField = new StringBuilder();
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
                                            if (z == numOfInput - 1) {
                                                eventTimingField.append(userInputArray[z]);
                                            } else {
                                                eventDateField.append(userInputArray[z]);
                                            }
                                        }
                                    }
                                    z++;
                                }
                                taskToAdd = new Event(eventString.toString().trim(), eventDateField.toString().trim(),
                                                      eventTimingField.toString().trim());
                                printGotIt();
                                printTask(taskToAdd);
                                STORAGE.add(taskToAdd);
                                fileIsChanged = true;
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
                                    fileIsChanged = true;
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
            BUFFERED_READER.close();
            if (fileIsChanged) {
                String filePath = file.getAbsolutePath();
                saveToDisk(filePath);
            } else {
                // Do nothing
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Duke.txt file cannot be found. Please create duke.txt in data folder!");
        }
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
                outputLine = "D" + "|" + isDone + "|" + deadlineTask.description + "|"
                        + deadlineTask.deadlineDate.date + " " + deadlineTask.deadlineTime.timing;
            } else if ((task.getClass().equals(Event.class))) {
                Event eventTask = (Event) task;
                outputLine = "E" + "|" + isDone + "|" + eventTask.description + "|"
                        + eventTask.eventDate.date + " " + eventTask.eventTime.timing;
            } else {
                // Do nothing
            }
            String separator = System.lineSeparator();
            writer.write(outputLine + separator);
            index++;
        }
        printBorder();
        writer.close();
        System.out.println("Tasks have been successfully saved to duke.txt!");
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
