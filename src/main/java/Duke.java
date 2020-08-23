import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

public class Duke {

    private final List<Task> tasks;
    private static final String DIVIDER = "----------------------------------------";
    private static final String DIRECTORY = "data";
    private static final String FILE = "data/duke.txt";

    public Duke() {
        this.tasks = new ArrayList<>();
    }

    public static void main(String[] args) {
        Duke session = new Duke(); // start a new session with JonasBot
        session.greet(); // greet the user
        session.readFile();
        session.execute(); // execute the bot to perform intended functions
        session.end(); // end the current session with JonasBot
    }

    public void greet() {
        String text = "  ______  _____  __  ____     __     _______      \n" +
                " |__  __||  __ | | \\ |  |    /_ \\    |   ___|     \n" +
                "    | |  | | | | |  \\|  |   //_\\ \\   |  |___          \n" +
                " _  | |  | | | | | |\\   |  / ____ \\  |____  |   \n" +
                "| |_| |  | |_| | | | \\  | / /    \\ \\ _____| |         \n" +
                "|_____|  |_____| |_|  \\_|/_/      \\_\\|______|       \n" +
                "                  _____   ______  ________                            \n" +
                "                  |  _ \\ |  _  | |__   __|                  \n" +
                "                  | |_| || | | |    | |                      \n" +
                "                  |    / | | | |    | |         \n" +
                "                  |  _ \\ | |_| |    | |       \n" +
                "                  | |_| ||     |    | |         \n" +
                "                  |_____/|_____|    |_|                 \n";
        String greeting = "  Hello! I am JonasBot! Nice to meet you :) \n" +
                text +
                "  \n  I am a task manager bot that will keep track of all your tasks. \n" +
                "  \n  To view a list of all my commands, input '/commands' \n" +
                "  \n  Now that you are familiar with the commands, how may I assist you today?";
        System.out.println(Duke.DIVIDER);
        System.out.println(greeting);
        System.out.println(Duke.DIVIDER);
    }

    public void execute() {
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine().trim();
        String function = message.split(" ")[0];

        while (!message.equals("bye")) {
            try {
                System.out.println(Duke.DIVIDER);
                if (message.equals("/commands")) {
                    commands();
                } else if (message.isEmpty()) {
                    System.out.println("Please enter something!");
                } else if (message.equals("list")) {
                    retrieveList();
                } else if (function.equals("done")) {
                    completeTask(message);
                } else if (function.equals("todo") || function.equals("deadline") || function.equals("event")){
                    createTask(message);
                } else if (function.equals("delete")) {
                    deleteTask(message);
                } else if (function.equals("find_by_date")) {
                    searchByDate(message);
                } else {
                    handleFailedFunction();
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println(Duke.DIVIDER);
            message = sc.nextLine().trim();
            function = message.split(" ")[0];
        }
        sc.close();
    }

    public void handleFailedFunction() throws InvalidFunctionException {
        String err = "Invalid Function! Input '/commands' for a list of all my commands. ";
        throw new InvalidFunctionException(err);
    }

    public void retrieveList() {
        if (this.tasks.isEmpty()) {
            System.out.println("Your list is empty. Add a new task!");
        } else {
            System.out.println("Here is a list of all your tasks:");
            for (int i = 0; i < this.tasks.size(); i++) {
                int index = i + 1;
                System.out.println("\t" + String.format("%d. %s", index, this.tasks.get(i)));
            }
        }
    }

    public void completeTask(String message) throws InvalidTaskException, InvalidFunctionException {
        try {
            int index = Integer.parseInt(message.split(" ")[1]);
            if (index > this.tasks.size()) {
                String err = "Invalid Task! The task does not exist, try again.";
                throw new InvalidTaskException(err);
            } else if (index <= 0) {
                String err = "The task ID you provided is not valid. Input '/commands' to view a list of my commands. ";
                throw new InvalidFunctionException(err);
            } else {
                if (this.tasks.get(index - 1).isDone) {
                    System.out.println("  This task has already been completed:");
                } else {
                    this.tasks.get(index - 1).markAsDone();
                    System.out.println("  Nice! I've marked this task as done:");
                }
                System.out.println("\t" + this.tasks.get(index - 1));
            }
            this.saveFile();
        } catch (ArrayIndexOutOfBoundsException ex) {
            String err = "No Task ID provided! Please input the ID of the task you wish to mark as completed.";
            throw new InvalidFunctionException(err);
        } catch (NumberFormatException ex) {
            String err = "Your input is not a recognised command. You have to provide the ID of " +
                    "the task you wish to mark as done. \n" + "Input '/commands' to view a list of my commands. ";
            throw new InvalidFunctionException(err);
        }
    }

    public void deleteTask(String message) throws InvalidTaskException, InvalidFunctionException {
        try {
            int index = Integer.parseInt(message.split(" ")[1]);
            if (index > this.tasks.size()) {
                String err = "Invalid Task! The task does not exist, try again.";
                throw new InvalidTaskException(err);
            } else if (index <= 0) {
                String err = "The task ID you provided is not valid. Input '/commands' to view a list of my commands. ";
                throw new InvalidFunctionException(err);
            } else {
                Task toRemove = this.tasks.get(index - 1);
                System.out.println("  Found it! This task has been successfully deleted:");
                System.out.println("\t" + toRemove);
                this.tasks.remove(index - 1);
                System.out.println("  You have " + this.tasks.size() + " tasks in your list now.");
            }
            this.saveFile();
        } catch (ArrayIndexOutOfBoundsException ex) {
            String err = "No Task ID provided! Please input the ID of the task you wish to delete.";
            throw new InvalidFunctionException(err);
        } catch (NumberFormatException ex) {
            String err = "Your input is not a recognised command. You have to provide the ID of " +
                    "the task you wish to delete. \n" + "Input '/commands' to view a list of my commands. ";
            throw new InvalidFunctionException(err);
        }
    }

    public void commands() {
        String commands = "  Below is a list of all the commands for my functions: \n" +
                "  1. Create a new task: \n" +
                "\t  1.1 Todo: 'todo' {task description}. For eg, todo eat \n" +
                "\t  1.2 Deadline: 'deadline' {task description} '/by' {deadline date}.\n\t\t" +
                "  Input the date using the format: 'dd/mm/yyyy hh:mm'. " +
                "For eg, deadline return book /by 12/2/2020 13:00 \n" +
                "\t  1.3 Event: 'event' {task description} '/at' {event date}.\n\t\t" +
                "  Input the date using the format: 'dd/mm/yyyy hh:mm'. " + "" +
                "For eg, event project meeting /at 1/3/2020 12:00 \n" +
                "  \n  2. To display all tasks in your list: 'list' \n" +
                "  \n  3. To mark a task as completed: 'done' {task ID}. For eg, 'done 2' \n" +
                "  \n  4. To delete a task: 'delete' {task ID}. For eg, 'delete 2' \n" +
                "  \n  6. To search for a task by date: 'find_by_date' {date}. \n" +
                "     Input the date using the format: 'dd/mm/yyyy'. For eg, 'find_by_date 12/2/2020' \n" +
                "  \n  5. To end this chat: 'bye' \n";
        System.out.println(commands);
    }

    public void searchByDate(String message) throws InvalidFunctionException {
        try {
            String date = message.split("find_by_date")[1].trim();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate dateToSearch = LocalDate.parse(date, dateFormatter);
            int index = 1;
            System.out.println("Search Results:");
            for (Task task : this.tasks) {
                if (task.getDate() != null) {
                    if (task.getDate().isEqual(dateToSearch)) {
                        System.out.println(String.format("%d. %s", index, task));
                        index++;
                    }
                }
            }
            if (index == 1) {
                System.out.println("No tasks found! Please search using a different date!");
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            String err = "No task date provided. Please input a valid date using the format: 'dd/mm/yyyy' \n" +
                    "Type '/commands' to view the correct command for task search by date! ";
            throw new InvalidFunctionException(err);
        } catch (DateTimeParseException ex) {
            String err = "The task date format is incorrect. Please input a valid date using the format: 'dd/mm/yyyy'";
            throw new InvalidFunctionException(err);
        }
    }

    public void createTask(String message) throws InvalidTaskException, InvalidFunctionException {
        String[] taskInfo = retrieveTaskInfo(message);
        Task toAdd = null;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");
        try {
            if (taskInfo[0].equals("todo")) {
                toAdd = new Todo(taskInfo[1]);
            } else {
                String[] timeStamp = taskInfo[2].split(" ");
                if (taskInfo[0].equals("deadline")) {
                    LocalDate deadlineDate = LocalDate.parse(timeStamp[0], dateFormatter);
                    LocalTime deadlineTime = LocalTime.parse(timeStamp[1], timeFormatter);
                    toAdd = new Deadline(taskInfo[1], deadlineDate, deadlineTime);
                } else if (taskInfo[0].equals("event")) {
                    LocalDate deadlineDate = LocalDate.parse(timeStamp[0], dateFormatter);
                    LocalTime deadlineTime = LocalTime.parse(timeStamp[1], timeFormatter);
                    toAdd = new Event(taskInfo[1], deadlineDate, deadlineTime);
                }
            }
            System.out.println("  Success! This " + taskInfo[0] + " task has been added:");
            this.tasks.add(toAdd);
            System.out.println("\t" + toAdd);
            System.out.println("  You have " + this.tasks.size() + " tasks in your list now.");
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException ex) {
            String err = "The task date format is incorrect. \n" +
                    "Please input a valid date using the format: 'dd/mm/yyyy hh:mm'. For eg, 10/8/2020 18:00";
            throw new InvalidFunctionException(err);
        }
        this.saveFile();
    }

    public String[] retrieveTaskInfo(String message) throws InvalidTaskException {
        String[] taskInfo = new String[3];
        String taskType = message.split(" ")[0];
        String description = "";
        String time = "";
        if (taskType.equals("todo")) {
            String[] task = message.split("todo");
            if (task.length == 0) {
                String err = "Your todo task description is empty. The task cannot be created.\n" +
                        "Type '/commands' to view the correct command for task creation! ";
                throw new InvalidTaskException(err);
            } else {
                description = task[1];
            }
        } else if (taskType.equals("deadline")) {
            String[] task = message.split("deadline");
            if (task.length == 0) {
                String err = "Your deadline task has missing arguments and has an incorrect format. " +
                        "The task cannot be created.\n" +
                        "Type '/commands' to view the correct command for task creation!";
                throw new InvalidTaskException(err);
            } else {
                String[] taskInputArray = task[1].split(" /by ");
                if (!task[1].contains(" /by ") && !task[1].endsWith("/by")) {
                    String err = "Your deadline task has an incorrect format. The task cannot be created. \n" +
                            "Type '/commands' to view the correct command for task creation!";
                    throw new InvalidTaskException(err);
                } else if (task[1].trim().equals("/by")) {
                    String err = "Your deadline task is missing a description and time stamp. " +
                            "The task cannot be created. \n" +
                            "Type '/commands' to view the correct command for task creation!";
                    throw new InvalidTaskException(err);
                }
                else if (task[1].trim().endsWith("/by")) {
                    String err = "Your deadline task is missing a time stamp. The task cannot be created. \n" +
                            "Type '/commands' to view the correct command for task creation!";
                    throw new InvalidTaskException(err);
                }
                else if (taskInputArray.length == 1 || taskInputArray[0].isBlank()) {
                    String err = "Your deadline task is missing a description. The task cannot be created. \n" +
                            "Type '/commands' to view the correct command for task creation!";
                    throw new InvalidTaskException(err);
                } else {
                    description = taskInputArray[0];
                    time = taskInputArray[1].trim();
                }
            }
        } else if (taskType.equals("event")) {
            String[] task = message.split("event");
            if (task.length == 0) {
                String err = "Your event task has missing arguments and has an incorrect format. " +
                        "The task cannot be created.\n" +
                        "Type '/commands' to view the correct command for task creation!";
                throw new InvalidTaskException(err);
            } else {
                String[] taskInputArray = task[1].split(" /at ");
                if (!task[1].contains(" /at ") && !task[1].endsWith("/at")) {
                    String err = "Your event task has an incorrect format. The task cannot be created. \n" +
                            "Type '/commands' to view the correct command for task creation!";
                    throw new InvalidTaskException(err);
                } else if (task[1].trim().equals("/at")) {
                    String err = "Your event task is missing a description and time stamp. " +
                            "The task cannot be created. \n" +
                            "Type '/commands' to view the correct command for task creation!";
                    throw new InvalidTaskException(err);
                }
                else if (task[1].trim().endsWith("/at")) {
                    String err = "Your event task is missing a time stamp. The task cannot be created. \n" +
                            "Type '/commands' to view the correct command for task creation!";
                    throw new InvalidTaskException(err);
                }
                else if (taskInputArray.length == 1 || taskInputArray[0].isBlank()) {
                    String err = "Your event task is missing a description. The task cannot be created. \n" +
                            "Type '/commands' to view the correct command for task creation!";
                    throw new InvalidTaskException(err);
                } else {
                    description = taskInputArray[0];
                    time = taskInputArray[1].trim();
                }
            }
        }
        taskInfo[0] = taskType;
        taskInfo[1] = description;
        taskInfo[2] = time;
        return taskInfo;
    }

    public void readFile() {
        try {
            File dataDirectory = new File(Duke.DIRECTORY);
            dataDirectory.mkdir(); // make a data directory if the directory does not exist

            File dataFile = new File(Duke.FILE);
            dataFile.createNewFile(); // create an empty file to store the tasks if the file does not exist

            Scanner sc = new Scanner(dataFile);

            while (sc.hasNextLine()) {
                String[] taskData = sc.nextLine().split(" \\|");
                if (taskData[0].equals("T")) {
                    Task toAdd = new Todo(taskData[2]);
                    if (taskData[1].equals(" 1")) {
                        toAdd.markAsDone();
                    }
                    this.tasks.add(toAdd);
                } else if (taskData[0].equals("D")) {
                    String dateTime = taskData[3].trim();
                    String[] dateTimeArray = dateTime.split(" ");
                    LocalDate deadlineDate = LocalDate.parse(dateTimeArray[0]);
                    LocalTime deadlineTime = LocalTime.parse(dateTimeArray[1]);
                    Task toAdd = new Deadline(taskData[2], deadlineDate, deadlineTime);
                    if (taskData[1].equals(" 1")) {
                        toAdd.markAsDone();
                    }
                    this.tasks.add(toAdd);
                } else if (taskData[0].equals("E")) {
                    String dateTime = taskData[3].trim();
                    String[] dateTimeArray = dateTime.split(" ");
                    LocalDate eventDate = LocalDate.parse(dateTimeArray[0]);
                    LocalTime eventTime = LocalTime.parse(dateTimeArray[1]);
                    Task toAdd = new Event(taskData[2], eventDate, eventTime);
                    if (taskData[1].equals(" 1")) {
                        toAdd.markAsDone();
                    }
                    this.tasks.add(toAdd);
                }
            }
            sc.close();
        } catch (IOException ex) {
            System.out.println("Oh no! An error was encountered, the file data could not be red.");
        }
    }

    public void saveFile() {
        try {
            FileWriter fileWriter = new FileWriter(Duke.FILE);
            for (int i = 0; i < this.tasks.size(); i++) {
                fileWriter.write(this.tasks.get(i).taskFileFormat() + "\n");
            }
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println("Oh no! An error is encountered and the task file could not be updated.");
        }

    }

    public void end() {
        String divider = "----------------------------------------";
        System.out.println(divider);
        System.out.println("  GoodBye and I hope to see you soon! Have a fantastic day! ");
        System.out.println(divider);
    }

}
