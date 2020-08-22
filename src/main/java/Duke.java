import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.File;
import java.io.FileNotFoundException;

public class Duke {

    enum TypeOfTask {
        TODO,
        DEADLINE,
        EVENT
    }

    private static String formatReply(String text) {
        String line = "\t_______________________________________________________________";
        return line + "\n\t\t" + text.replaceAll("\\n", "\n\t\t") + "\n" + line;
    }

    private static String listTasks(ArrayList<Task> tasks) {
        String listOfTasks = "";
        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks = listOfTasks + (i + 1) + ". " + tasks.get(i).toString()
                    + (i == tasks.size() - 1 ? "" : "\n");
        }
        return listOfTasks;
    }

    private static Task getTask(String command, TypeOfTask typeOfTask, Scanner input) throws MissingInfoException {
        String[] commandArray = input.nextLine().split(" ");
        String description = "";
        LocalDateTime timing = null;

        for (int i = 1; i < commandArray.length; i++) {
            if (commandArray[i].equals("/by")) {
                timing = getTiming(command, commandArray, i + 1);
                break;
            } else if (commandArray[i].equals("/at")) {
                timing = getTiming(command, commandArray, i + 1);
                break;
            }
            if (i == 1) {
                description = commandArray[i];
            } else {
                description = description + " " + commandArray[i];
            }
        }

        if (description.isEmpty()) {
            throw new MissingInfoException("OOPS!!! The description of a " + command + " cannot be empty.");
        }

        if ((typeOfTask.equals(TypeOfTask.DEADLINE) || typeOfTask.equals(TypeOfTask.EVENT)) && timing == null) {
            throw new MissingInfoException("OOPS!!! The date/time of a " + command + " cannot be empty.");
        }

        return createTask(typeOfTask, description, timing, false);
    }

    private static LocalDateTime getTiming(String command, String[] commandArray, int index) throws MissingInfoException, DateTimeParseException {
        String timing = "";
        for (int i = index; i < commandArray.length; i++) {
            if (i == index) {
                timing = commandArray[i];
            } else {
                timing = timing + " " + commandArray[i];
            }
        }

        if (timing.isEmpty()) {
            throw new MissingInfoException("OOPS!!! The date/time of a " + command + " cannot be empty.");
        }
        try {
            return LocalDateTime.parse(timing);
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    private static Task createTask(TypeOfTask typeOfTask, String description, LocalDateTime timing, boolean done) {

        switch (typeOfTask) {
        case TODO:
            return new Todo(description, done);
        case DEADLINE:
            return new Deadline(description, timing, done);
        case EVENT:
            return new Event(description, timing, done);
        default:
            return new Task(description, done);
        }
    }

    private static ArrayList<Task> readFile(ArrayList<Task> tasks) {
        try {
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            File f = new File("data/tasks.txt");
            f.createNewFile();
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String[] data;
                data = s.nextLine().split(" \\| ");
                switch (data[0]) {
                case "T":
                    tasks.add(createTask(TypeOfTask.TODO, data[2], null, data[1].equals("1") ? true : false));
                    break;
                case "D":
                    tasks.add(createTask(TypeOfTask.DEADLINE, data[2], LocalDateTime.parse(data[3]), data[1].equals("1") ? true : false));
                    break;
                case "E":
                    tasks.add(createTask(TypeOfTask.EVENT, data[2], LocalDateTime.parse(data[3]), data[1].equals("1") ? true : false));
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(formatReply("OOPS!!! Can't access task data."));
        } catch (IOException e) {
            System.out.println(formatReply("OOPS!!! Something went wrong... Tasks not saved."));
        }
        return tasks;
    }

    private static void writeFile(ArrayList<Task> tasks) {
        String taskString = "";

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String typeOfTask = task.getClass().getName();
            String timing = "";

            switch (typeOfTask) {
                case "Todo":
                    taskString = taskString + "T";
                    break;
                case "Deadline":
                    taskString = taskString + "D";
                    timing = timing + ((Deadline) task).getTiming();
                    break;
                case "Event":
                    taskString = taskString + "E";
                    timing = timing + ((Event) task).getTiming();
                    break;
            }

            taskString = taskString + " | " + (task.getDone() ? "1" : "0") + " | " + task.getTaskDescription()
                    + (typeOfTask.equals("Deadline") || typeOfTask.equals("Event")
                    ? " | " + timing
                    : "");

            taskString = taskString + "\n";
        }

        try {
            FileWriter fw = new FileWriter("data/tasks.txt");
            fw.write(taskString);
            fw.close();
        } catch (IOException e) {
            System.out.println(formatReply("OOPS!!! Something went wrong... Tasks not saved."));
        }
    }

    public static void main(String[] args) {
        System.out.println(formatReply("Hello! I'm Duke\nWhat can I do for you?"));
        ArrayList<Task> taskList = new ArrayList<>();
        taskList = readFile(taskList);
        Scanner input = new Scanner(System.in);
        while (true) {
            String command = input.next();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                System.out.println(formatReply("Here are the tasks in your list:\n" + listTasks(taskList)));
            } else if (command.equals("done")) {
                try {
                    Task task = taskList.get(input.nextInt() - 1);
                    task.completeTask();
                    writeFile(taskList);
                    System.out.println(formatReply("This task has been marked as done:\n" + task.toString()));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(formatReply("OOPS!!! Task number is invalid."));
                } catch (InputMismatchException e) {
                    input.nextLine();
                    System.out.println(formatReply("OOPS!!! Task number must be a number."));
                }
            } else if (command.equals("delete")) {
                try {
                    int taskNumber = input.nextInt();
                    Task task = taskList.get(taskNumber - 1);
                    taskList.remove(taskNumber - 1);
                    writeFile(taskList);
                    System.out.println(formatReply("This task has been removed:\n" + task.toString()
                            + "\nNow you have " + taskList.size() + " in the list."));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(formatReply("OOPS!!! Task number is invalid."));
                } catch (InputMismatchException e) {
                    input.nextLine();
                    System.out.println(formatReply("OOPS!!! Task number must be a number."));
                }
            } else {
                try {
                    TypeOfTask typeOfTask;
                    switch (command) {
                        case "todo":
                            typeOfTask = TypeOfTask.TODO;
                            break;
                        case "deadline":
                            typeOfTask = TypeOfTask.DEADLINE;
                            break;
                        case "event":
                            typeOfTask = TypeOfTask.EVENT;
                            break;
                        default:
                            input.nextLine();
                            throw new InvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    Task newTask = getTask(command, typeOfTask, input);
                    taskList.add(newTask);
                    writeFile(taskList);
                    System.out.println(formatReply("Got it. I've added this task:\n" + newTask.toString()
                            + "\nNow you have " + taskList.size() + " tasks in the list."));
                } catch (InvalidCommandException e) {
                    System.out.println(formatReply(e.getMessage()));
                } catch (MissingInfoException e) {
                    System.out.println(formatReply(e.getMessage()));
                } catch (DateTimeParseException e) {
                    System.out.println(formatReply("OOPS!!! Date format is invalid. Make sure it is yyyy-mm-ddTHH:mm."));
                }
            }
        }
        System.out.println(formatReply("Bye. Hope to see you again soon!"));
    }
}

class InvalidCommandException extends Exception {
    public InvalidCommandException(String message) {
        super(message);
    }
}

class MissingInfoException extends Exception {
    public MissingInfoException(String message) {
        super(message);
    }
}