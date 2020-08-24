import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Scanner;
import java.util.ArrayList;

import java.nio.file.Paths;
import java.nio.file.Files;

public class Duke {

    private static final String line = "____________________________________________________________";

    private final String name = "Bolot";
    private final String end = "bye";
    private ArrayList<Task> list = new ArrayList<>();

    private Path dukeFile;

    public Duke() {
        createFile();
    }

    public void printLogo() {
        System.out.println("Greetings, human. I am");
        System.out.println(" ______      ___   _____       ___    _________");
        System.out.println("|_   _ \\   .'   `.|_   _|    .'   `. |  _   _  |");
        System.out.println("  | |_) | /  .-.  \\ | |     /  .-.  \\|_/ | | \\_|");
        System.out.println(" _| |__) |\\  `-'  /_| |__/ |\\  `-'  /   _| |_");
        System.out.println("|_______/  `.___.'|________| `.___.'   |_____|");
    }

    public void greet() {
        System.out.println(line);
        System.out.println("Hello! I am " + name + ", your personal chat-bot companion.");
        System.out.println("How may I help you?");
        System.out.println(line);
    }

    public void bye() {
        System.out.println(line);
        System.out.println("Bye! Thank you for chatting with " + name + "!");
        System.out.println("Hope to see you again soon!");
        System.out.println(line);
    }

    public void echo() {
        Scanner sc = new Scanner(System.in);

        String type = sc.nextLine();

        while (!type.equalsIgnoreCase(end)) {
            System.out.println(line);
            System.out.println(type);
            System.out.println(line);

            type = sc.nextLine();
        }

        bye();
    }

    private void printList() {

        if (list.size() == 0) {
            System.out.println("You have nothing on your list!");
        }

        int i = 1;
        for (Task todo : list) {
            System.out.println(i + ". " + todo);
            i++;
        }
    }

    private Command getFirstWord(String input) throws UnrecognizedTaskException {

        String firstWord = input.contains(" ")
                ? input.substring(0, input.indexOf(" ")).toLowerCase()
                : input.toLowerCase();

        try {
            return Command.valueOf(firstWord);
        } catch (IllegalArgumentException illegalArg) {
            throw new UnrecognizedTaskException();
        }
    }

    private void addTask(Command firstWord, String input) throws EmptyTaskException, InvalidDateException {

        String task;
        LocalDateTime date = null;
        LocalDateTime endDate = null;

        try {
            task = input.substring(firstWord.toString().length() + 1);
        } catch (StringIndexOutOfBoundsException indexError) {
            throw new EmptyTaskException();
        }

        if (firstWord == Command.todo) {
            list.add(new ToDo(task));
        } else {

            try {

                task = task.substring(0, task.indexOf('/'));
                date = firstWord == Command.event
                        ? input.contains("to ")
                        ? getDateTime(input.substring(input.indexOf("/at") + 4, input.indexOf("to ")))
                        : getDateTime(input.substring(input.indexOf("/at") + 4))
                        : getDateTime(input.substring(input.indexOf("/by") + 4));

                if (firstWord == Command.event && input.contains("to ")) {
                    String endDateString = input.substring(input.indexOf("to ") + 3);

                    if (endDateString.length() <= 8) {
                        endDate = LocalDateTime.of(date.toLocalDate(), LocalTime.parse(endDateString));
                    } else {
                        endDate = getDateTime(endDateString);
                    }
                }

            } catch (StringIndexOutOfBoundsException | InvalidDateException e) {

                if (firstWord == Command.deadline) {
                    throw new DeadlineInvalidDate();
                } else if (firstWord == Command.event) {
                    throw new EventInvalidDate();
                }
            }

            if (firstWord == Command.deadline) {
                list.add(new Deadline(task, date));
            } else if (firstWord == Command.event) {
                if (endDate != null) {
                    list.add(new Event(task, date, endDate));
                } else {
                    list.add(new Event(task, date));
                }
            }

        }

        System.out.println("Got it. I've added this task:");

        System.out.println(list.get(list.size() - 1).toString());

        String taskText = list.size() == 1 ? " task " : " tasks ";
        System.out.println("Now you have " + list.size() + taskText + "in the list.");
    }

    private void markDone(int taskNo) throws IndexOutOfBoundsException {
        list.set(taskNo, list.get(taskNo).markDone());

        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.get(taskNo));
    }

    private void deleteTask(int taskNo) throws IndexOutOfBoundsException {
        Task deleted = list.get(taskNo);
        list.remove(taskNo);

        System.out.println("Noted. I've removed this task:");
        System.out.println(deleted);
    }

    public void addToList() {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        while (!input.equalsIgnoreCase(end)) {

            System.out.println(line);

            try {

                Command firstWord = getFirstWord(input.trim());

                if (firstWord == Command.list) {

                    if (input.trim().equals(firstWord.name())) {
                        printList();
                    } else {
                        LocalDate date = getDateTime(input.substring(
                                firstWord.name().length()).trim()).toLocalDate();
                        printList(date);
                    }

                } else if (firstWord == Command.done || firstWord == Command.delete) {

                    if (input.equals(firstWord.toString())) {
                        throw new NoIndexException(firstWord.name());
                    } else {

                        try {
                            int taskNo = firstWord == Command.done
                                    ? Integer.parseInt(input.substring(5)) - 1
                                    : Integer.parseInt(input.substring(7)) - 1;

                            if (firstWord == Command.done) {
                                markDone(taskNo);
                            } else {
                                deleteTask(taskNo);
                            }

                        } catch (NumberFormatException numError) {
                            throw new NoIndexException(firstWord.name());
                        }
                    }

                } else {

                    addTask(firstWord, input.trim());
                    saveTasks();

                }

                saveTasks();

            } catch (DukeException error) {
                System.out.println(error.getMessage());
            } catch (IndexOutOfBoundsException indexError) {
                System.out.println("Invalid index.");
                String taskText = list.size() == 1 ? " task " : " tasks ";
                System.out.println("You have " + list.size() + taskText + "on your list.");
            }

            System.out.println(line);
            input = sc.nextLine();
        }

        bye();

    }

    private LocalDateTime getDateTime(String dateString) throws InvalidDateException {

        dateString = dateString.trim();

        try {

            if (dateString.length() == 19 || dateString.length() == 16) {
                return LocalDateTime.parse(dateString);
            } else if (dateString.contains("-")) {
                return LocalDateTime.of(LocalDate.parse(dateString), LocalTime.MAX);
            } else if (dateString.contains(":")) {
                return LocalDateTime.of(LocalDate.now(), LocalTime.parse(dateString));
            } else {
                throw new InvalidDateException();
            }

        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    private void printList(LocalDate date) {

        int i = 0;
        for(Task task: list) {
            if (task.getDate().equals(date)) {
                if (i == 0) {
                    System.out.println("Here's your list on " +
                            date.format(DateTimeFormatter.ofPattern("dd MMM y:")));
                }

                System.out.println((i + 1) + ". " + task);
                i++;
            }
        }

        if (i == 0 || list.size() == 0) {
            System.out.println("You have nothing to do on " +
                    date.format(DateTimeFormatter.ofPattern("dd MMM y.")));
        }
    }

    private void createFile() {
        try {

            if (Files.notExists(Paths.get("data"))) {
                Files.createDirectory(Paths.get("data"));
            }

            Files.deleteIfExists(Paths.get("data/duke.txt"));
            dukeFile = Files.createFile(Paths.get("data/duke.txt"));

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void saveTasks() {

        StringBuilder taskString = new StringBuilder();

        int i = 1;
        for (Task task: list) {
            taskString.append(String.format("%d. ", i));
            taskString.append(task.toString());
            taskString.append("\n");
            i++;
        }

        try {
            Files.writeString(dukeFile, taskString);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.printLogo();
        bot.greet();
//        bot.echo();
        bot.addToList();
    }
}