package duke;

import exception.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

import java.time.DateTimeException;
import java.time.LocalDate;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Duke {
    Scanner sc;
    String input;
    List<Task> taskList;

    public Duke() {
        sc = new Scanner(System.in);
        String input;
        taskList = new ArrayList<>();
    }

    public void start() {
        try {
            this.taskList = Duke.loadStorage();
            Duke.hello();
            handleInteraction();
            Duke.bye();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // file functions
    private static List<Task> loadStorage() throws IOException {
        Path folderPath = Path.of("data");
        Path filePath = folderPath.resolve("duke.txt");

        // create folders containing the file and its parents
        Files.createDirectories(folderPath);

        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }

        // read from file
        BufferedReader br = Files.newBufferedReader(filePath);

        List<Task> tasks = new ArrayList<>();
        br.lines().forEach(line -> {
            try {
                Task task = parseStorageData(line);
                tasks.add(task);
            } catch (StorageException ex) {
                System.out.println(ex.getMessage());
            }
        });
        return tasks;
    }

    private static Task parseStorageData(String line) throws StorageException {
        // split by the pipe `|` token
        String[] tokens = line.split("(\\s)*(\\|)(\\s)*");
        Task task;

        try {
            switch (tokens[0]) {
            case "T":
                task = new Todo(tokens[2]);
                break;
            case "D":
                task = Deadline.create(tokens[2], tokens[3]);
                break;
            case "E":
                task = Event.create(tokens[2], tokens[3]);
                break;
            default:
                throw new StorageException("Unknown task identifier " + tokens[0] + "! Skipping...");
            }

            if (tokens[1].equals("1")) {
                task.markAsDone();
            }

            return task;
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new StorageException("Data" + line + "in wrong format! Skipping...");
        } catch (EventInvalidUsageException | DeadlineInvalidUsageException ex) {
            throw new StorageException(ex.getMessage());
        }
    }

    /**
     * Update the `duke.txt` file that is used for saving tasks
     *
     * @param tasks a list of task to save
     * @return true indicating storage is updated, or false indicating storage fails to update
     */
    private static boolean updateStorage(List<Task> tasks) {
        try {
            BufferedWriter bw = Files.newBufferedWriter(Path.of("data/duke.txt"));
            for (Task task : tasks) {
                String storeFormat = String.format(
                        "%s | %d | %s",
                        task.getType(),
                        task.getStatus() ? 1 : 0,
                        task.getDescription()
                );
                bw.write(storeFormat);
                bw.newLine();
            }
            bw.close();
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // interaction functions
    public void handleInteraction() {
        while (sc.hasNextLine()) {
            input = sc.nextLine();
            // stopping condition
            if (input.equals("bye")) {
                break;
            }

            buildChatFence();

            // handle commands
            try {
                String[] parsed = parseInput(input);
                Commands command;

                try {
                    // modify parsed[0] to uppercase to ensure that comparison with the commands enum is standardized
                    command = Commands.valueOf(parsed[0].toUpperCase());
                } catch (IllegalArgumentException ex) {
                    // if parsed[0] not amongst valid commands, will throw an IllegalArgumentException
                    throw new UnknownCommandException();
                }

                switch (command) {
                case LIST:
                    // we ignore the argument after `list`.
                    System.out.println("     Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.printf("     %d. %s%n", i + 1, taskList.get(i).showTask());
                    }
                    break;
                case DONE:
                    try {
                        int taskNumber = Integer.parseInt(parsed[1]) - 1;
                        // Check that the task number makes sense.
                        if (taskNumber >= 0 && taskNumber < taskList.size()) {
                            taskList.get(taskNumber).markAsDone();
                            Duke.updateStorage(this.taskList);
                            System.out.println("     Good job! I've marked this task as done:");
                            System.out.printf("      %s%n", taskList.get(taskNumber).showTask());
                        } else {
                            System.out.println("     Sorry, I can't find it in your list!");
                        }
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                        throw new InvalidUsageException("Usage: done <task number>");
                    }
                    break;
                case DELETE:
                    try {
                        int taskNumber = Integer.parseInt(parsed[1]) - 1;
                        // Check that the task number makes sense.
                        if (taskNumber >= 0 && taskNumber < taskList.size()) {
                            System.out.println("     Noted. I've removed this task: ");
                            System.out.printf("      %s%n", taskList.remove(taskNumber).showTask());
                            System.out.printf("     Now you have %d %s in the list%n",
                                    taskList.size(), taskList.size() > 1 ? "tasks" : "task");
                            Duke.updateStorage(this.taskList);
                        } else {
                            System.out.println("     Sorry, I can't find it in your list!");
                        }
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                        throw new InvalidUsageException("Usage: delete <task number>");
                    }
                    break;
                case TODO:
                    try {
                        this.addTodo(parsed[1]);
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        throw new TodoInvalidUsageException("Todo description cannot be empty.");
                    }
                    break;
                case DEADLINE:
                    try {
                        this.addDeadline(parsed[1]);
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        throw new DeadlineInvalidUsageException("Deadline description cannot be empty.");
                    }
                    break;
                case EVENT:
                    try {
                        this.addEvent(parsed[1]);
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        throw new EventInvalidUsageException("Event description cannot be empty.");
                    }
                    break;
                case VIEWALL:
                    try {
                        List<Task> filtered = this.viewAll(parsed[1]);
                        System.out.println("     Here are the tasks on given date:");
                        for (int i = 0; i < filtered.size(); i++) {
                            System.out.printf("     %d. %s%n", i + 1, filtered.get(i).showTask());
                        }
                    } catch (DateTimeException | ArrayIndexOutOfBoundsException ex) {
                        throw new ViewallInvalidUsageException("Date should be in yyyy-mm-dd format.");
                    }
                    break;
                default:
                    throw new UnknownCommandException();
                }
            } catch (InvalidUsageException | UnknownCommandException ex) {
                System.out.println(ex.getMessage());
            }
            buildChatFence();
        }
    }

    private static void hello() {
        buildChatFence();
        System.out.println("     Hellowww!! I'm Alexa, your personal todo manager!");
        System.out.println("     How can I help you today?");
        buildChatFence();
    }

    private static void bye() {
        buildChatFence();
        System.out.println("     Bye? I hope it's not forever! Come back soon!");
        buildChatFence();
    }

    private static void buildChatFence() {
        System.out.println("    ----------------------------------------");
    }

    private void printAddConfirmation(int index) {
        int size = taskList.size();
        System.out.println("     Got it. I've added this task:");
        System.out.printf("       %s%n", taskList.get(index).showTask());
        System.out.printf("     Now you have %d %s in the list%n", size, size > 1 ? "tasks" : "task");
    }

    /**
     * Split the input string into first word (command) and others
     *
     * @param input user input
     * @return an array, first element is command, second element is input
     */
    private String[] parseInput(String input) {
        return input.split("\\s+", 2);
    }

    private void addTodo(String todo) {
        taskList.add(new Todo(todo));
        Duke.updateStorage(this.taskList);
        printAddConfirmation(taskList.size() - 1);
    }

    private void addDeadline(String deadline) throws DeadlineInvalidUsageException {
        taskList.add(Deadline.create(deadline));
        printAddConfirmation(taskList.size() - 1);
    }

    private void addEvent(String event) throws EventInvalidUsageException {
        taskList.add(Event.create(event));
        printAddConfirmation(taskList.size() - 1);
    }

    private List<Task> viewAll(String dateStr) throws DateTimeException {
        LocalDate date = LocalDate.parse(dateStr);
        return this.taskList
                .stream()
                .filter(x ->
                        (x instanceof Event && ((Event) x).at.equals(date))
                                || (x instanceof Deadline && ((Deadline) x).by.equals(date)))
                .collect(Collectors.toList());
    }
}
