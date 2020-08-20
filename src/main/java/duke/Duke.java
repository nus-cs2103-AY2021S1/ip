package duke;

import duke.Command.Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static ArrayList<Task> listArray = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        run();

    }

    private static void run() {
        try {
            importTaksFromFile("/Users/doraheng/Documents/CS 2103T/Duke/ip/data/duke.txt");
        } catch (FileNotFoundException e) {
            System.out.println(Message.BORDERS + "\n" + "File does not exist"
                    + "\n" + Message.BORDERS);
            return;
        } catch (DukeException e) {
            System.out.println(Message.BORDERS + "\n" + e + "\n"
                    + Message.BORDERS);
        }

        print(Message.MESSAGE_WELCOME);

        Scanner scanner = new Scanner(System.in);
        String input;

        while (!(input = scanner.nextLine()).equals("bye")) {
                print(input);
        }

        scanner.close();
        print(input);

        try {
            exportTasksToFile("/Users/doraheng/Documents/CS 2103T/Duke/ip/data/duke.txt");
        } catch (IOException e) {
            System.out.println(Message.BORDERS + "\n" + "Failed to save tasks."
                    + "\n" + Message.BORDERS);
        }
    }

    private static void exportTasksToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : listArray) {
            fw.write(task.serialize());
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    private static void importTaksFromFile(String filePath) throws FileNotFoundException, DukeException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String[] data = scanner.nextLine().split(" \\| ");
            Task task;

            switch (data[0]) {
                case "T":
                    task = new ToDo(data[2]);
                    break;
                case "D":
                    task = new Deadline(data[2], data[3]);
                    break;
                case "E":
                    task = new Event(data[2], data[3]);
                    break;
                default:
                    throw new DukeException("Failed to load tasks.");
            }

            if (data[1].equals("1")) {
                task.markAsDone();
            }

            listArray.add(task);
        }
        scanner.close();
    }

    private static void print(String message) {
        String messageB = Message.BORDERS;
        try {
            if (message.equals(Message.MESSAGE_WELCOME)) {
                System.out.println(messageB + "\n"
                        + Message.MESSAGE_WELCOME + "\n"
                        + messageB);
            } else {
                System.out.println(messageB + "\n"
                        + Command.parse(message) + "\n"
                        + messageB);
            }
        } catch (DukeException e) {
            System.out.println(messageB + "\n" + e + "\n"
                    + messageB);
        }
    }
}
