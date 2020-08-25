import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void start(ArrayList<Task> loadedTasks) {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        if (loadedTasks.size() != 0) {
            tasks = (ArrayList<Task>) loadedTasks.clone();
        }
        try {
            Scanner userInput = new Scanner(System.in);
            while (userInput.hasNext()) {
                String input = userInput.nextLine();
                String[] splitArr = input.split(" ");
                if (input.equals("bye")) {
                    System.out.println("Bye! Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i).recordString());
                    }
                } else if (splitArr.length == 2 && splitArr[0].equals("done") && Integer.parseInt(splitArr[1]) > 0) {
                    int index = Integer.parseInt(splitArr[1]);
                    if (index > tasks.size() || index < 0) {
                        throw new DukeException("That task number does not exist.");
                    }
                    tasks.get(index - 1).setDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(index - 1).recordString());
                } else if (splitArr.length == 2 && splitArr[0].equals("delete") && Integer.parseInt(splitArr[1]) > 0) {
                    int index = Integer.parseInt(splitArr[1]);
                    if (index > tasks.size() || index < 0) {
                        throw new DukeException("That task number does not exist.");
                    }
                    Task deletedTask = tasks.remove(index - 1);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(deletedTask.recordString());
                    System.out.println("Now, you have " + tasks.size() + " tasks in the list");
                } else {
                    switch (splitArr[0]) {
                        case "todo":
                            if (splitArr.length <= 1) {
                                throw new DukeException("The description of a todo cannot be empty.");
                            }
                            System.out.println("Got it. I've added this task:");
                            Task newTask = new ToDo(input.substring(5));
                            tasks.add(newTask);
                            System.out.println(newTask.recordString());
                            break;
                        case "deadline":
                            if (splitArr.length <= 1) {
                                throw new DukeException("The description of a deadline cannot be empty.");
                            }
                            int index = input.indexOf("/");
                            if (index == -1) {
                                throw new DukeException("Please include the date of the deadline!");
                            }
                            String desc = input.substring(9, index - 1);
                            String date = input.substring(index + 4);
                            try {
                                Task newDeadline = new Deadline(desc, date);
                                tasks.add(newDeadline);
                                System.out.println(newDeadline.recordString());
                            } catch (Exception e) {
                                throw new DukeException("Please enter a valid YYYY-MM-DD date format!");
                            }
                            System.out.println("Got it. I've added this task:");
                            break;
                        case "event":
                            if (splitArr.length <= 1) {
                                throw new DukeException("The description of an event cannot be empty.");
                            }
                            int ind = input.indexOf("/");
                            if (ind == -1) {
                                throw new DukeException("Please include the date of the event!");
                            }
                            String des = input.substring(6, ind - 1);
                            String dat = input.substring(ind + 4);
                            try {
                                Task newEvent = new Event(des, dat);
                                tasks.add(newEvent);
                                System.out.println(newEvent.recordString());
                            } catch (Exception e) {
                                throw new DukeException("Please enter a valid YYYY-MM-DD date format!");
                            }
                            System.out.println("Got it. I've added this task:");
                            break;
                        default:
                            throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                    System.out.println("Now, you have " + tasks.size() + " tasks in the list");
                }
                ArrayList<Task> tasksCopy = (ArrayList<Task>) tasks.clone();
                Parser.parse(tasksCopy);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        File f = new File("data/duke.txt");
        if (!f.exists()) {
            final File parentDir = new File("data");
            parentDir.mkdir();
            final String hash = "duke";
            final String fileName = hash + ".txt";
            final File file = new File(parentDir, fileName);
            try {
                file.createNewFile();
                System.out.println("Created path data/duke.txt");
            } catch (IOException e) {
                System.out.println("Could not create file.");
            }
        }
        try {
            ArrayList<Task> loadedTasks = Storage.initializeTasks("data/duke.txt");
            start(loadedTasks);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
