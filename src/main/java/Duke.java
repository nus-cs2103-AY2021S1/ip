import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DateTimeException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private static final String LINE_BREAK = "   ____________________________" +
            "________________________________";
    private static final String SPACE1 = "    ";
    private static final String SPACE2 = "     ";

    private static boolean isInteger (String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void generateTxt (List<Task> tasks, File data) throws IOException {
        FileWriter fw = new FileWriter(data);
        for (Task task : tasks) {
            fw.write(task.convertTxt() + System.lineSeparator());

        }
        fw.close();
    }

    private static void appendTxt (Task task, File data) throws IOException {
        FileWriter fw = new FileWriter(data, true);
        fw.write(task.convertTxt() + System.lineSeparator());
        fw.close();
    }

    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        File data;

        try {
            java.nio.file.Path dataFolder = java.nio.file.Paths.get(dir,"data");
            if (!java.nio.file.Files.exists(dataFolder)) {
                if (!new File(dataFolder.toString()).mkdir()) {
                    throw new FolderErrorException();
                }
            }

            java.nio.file.Path fileLocation = java.nio.file.Paths
                    .get(dataFolder.toString(),"duke.txt");

            if (!java.nio.file.Files.exists(fileLocation)) {
                if (!fileLocation.toFile().createNewFile()) {
                    throw new FileErrorException();
                }
            }

            data = new File(fileLocation.toString());

        } catch (DukeException | IOException | SecurityException e ) {
            System.out.println(e);
            return;
        }

        List<Task> tasks = new ArrayList<>();

        try {
            Scanner s = new Scanner(data);
            while (s.hasNext()) {
                String event = s.nextLine();
                String[] component = event.split("\\|");
                try{
                    switch(component[0].trim()) {
                    case "T": {
                        Task current = new Task(component[2]);
                        int state = Integer.parseInt(component[1].trim());
                        if (state != 0 && state != 1) {
                            throw new DukeException("Unknown completion type detected!");
                        }
                        if (state == 1) {
                            current.markAsDone();
                        }
                        tasks.add(current);
                        break;
                    }
                    case "D": {
                        Deadline current = new Deadline(component[2], component[3]);
                        int state = Integer.parseInt(component[1].trim());
                        if (state != 0 && state != 1) {
                            throw new DukeException("Unknown completion type detected!");
                        }
                        if (state == 1) {
                            current.markAsDone();
                        }
                        tasks.add(current);
                        break;
                    }

                    case "E": {
                        Event current = new Event(component[2], component[3]);
                        int state = Integer.parseInt(component[1].trim());
                        if (state != 0 && state != 1) {
                            throw new DukeException("Unknown completion type detected!");
                        }
                        if (state == 1) {
                            current.markAsDone();
                        }
                        tasks.add(current);
                        break;
                    }
                    default: {
                        throw new DukeException("Unknown task type detected!");
                    }
                    }
                } catch (DukeException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println(e);
                    System.out.println("Corrupted row detected! Row discarded.");
                    continue;
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("I can't seem to find your file...");
            return;
        }

        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(LINE_BREAK);
        System.out.println(SPACE1 + "Hey, I'm Emilia \u2764 !\n" + SPACE1 +
                "What can I do for you?");
        System.out.println(LINE_BREAK);


        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine().trim().toLowerCase();
            System.out.println(LINE_BREAK);
            if (input.equals("bye")) {
                System.out.println(SPACE1 + "Welcome back \u2764 !\n" + LINE_BREAK);
                break;
            }

            if (input.equals("list")) {
                System.out.println(SPACE1 + "Please take a look at the tasks:");
                int count = 1;
                for (Task task : tasks) {
                    System.out.println(SPACE1 + count + ". " + task);
                    count++;
                }
                System.out.println(LINE_BREAK);
                continue;
            }

            String[] process = input.split(" ", 2);
            try {
                if (process[0].equals("done") && isInteger(process[1])) {
                    int index = Integer.parseInt(process[1]) - 1;
                    Task task = tasks.get(index);
                    task.markAsDone();
                    System.out.println(SPACE1 + "Understood, I've marked this " +
                            "task as done:\n" + SPACE2 + task);
                    generateTxt(tasks, data);
                    continue;
                }

                if (process[0].equals("delete") && isInteger(process[1])) {
                    int index = Integer.parseInt(process[1]) - 1;
                    Task task = tasks.get(index);
                    tasks.remove(index);
                    System.out.println(SPACE1 + "Understood, I've deleted this task\n" +
                             SPACE2 + task + "\n" + SPACE1 + "You have " + tasks.size() +
                            " tasks in your list now!");
                    generateTxt(tasks,data);
                    continue;
                }

                if (process[0].equals("check")) {
                    System.out.println("Hey! I have printed out the tasks that match the date:");
                    String target = process[1];
                    for (Task task : tasks) {
                        if (task.getDate().equals(target)) {
                            System.out.println(task);
                        }
                    }
                    continue;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(SPACE1 + "Sorry, I can't seem " +
                        "to find the task...");
                continue;

            } catch (IOException e) {
                System.out.println("txt file update unsuccessful " + e.getMessage());
                continue;
            }

            String firstWord = process[0];
            Task current;
            try {
                switch (firstWord) {
                case "todo": {
                    current = new ToDo(process[1].trim());
                    break;
                }
                case "deadline": {
                    int byIndex = process[1].indexOf(" /by ");
                    if (byIndex == -1) {
                        throw new DukeException("better description");
                    }
                    String deadline = process[1].substring(byIndex + 4).trim();
                    String description = process[1].substring(0, byIndex).trim();
                    current = new Deadline(description, deadline);
                    break;
                }
                case "event": {
                    int atIndex = process[1].indexOf(" /at ");
                    if (atIndex == -1) {
                        throw new DukeException("better description");
                    }
                    String deadline = process[1].substring(atIndex + 4).trim();
                    String description = process[1].substring(0, atIndex).trim();
                    current = new Event(description, deadline);
                    break;
                }
                default: {
                    throw new DukeException("nothing understood");
                }
                }
            } catch (NumberFormatException | DateTimeException e) {
                System.out.println(SPACE1 + "Invalid date format detected!");
                continue;

            } catch (DukeException | ArrayIndexOutOfBoundsException e) {
                String type = e.getMessage();
                if (type.equals("better description") ||
                        e instanceof ArrayIndexOutOfBoundsException) {
                    System.out.println(SPACE1 + "I need a better " +
                            "description of the task!");
                } else if (type.equals("nothing understood")) {
                    System.out.println(SPACE1 + "I don't understand you at all...");
                }
                continue;
            }

            tasks.add(current);
            try {
                appendTxt(current, data);
            } catch (IOException e) {
                System.out.println("txt file update unsuccessful " + e.getMessage());
            }
            System.out.println(SPACE1 + "Understood! I've added this task:\n" +
                    SPACE2 + current);
            System.out.println(SPACE1 + "You have " +
                    tasks.size() + " tasks in your list now!");
            System.out.println(LINE_BREAK);
        }

        sc.close();
    }
}
