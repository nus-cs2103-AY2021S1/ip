import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Chatbot {

    /**
     * Method to start the Chatbot and also read/save the history of tasks.
     * Contains all the logic that processes the incoming messages from the user.
     * @throws DukeException
     * @throws IOException
     */
    public void chat() throws DukeException, IOException {
        // Initialize Bot and print Welcome Message
        String start = "Hello! I'm Duke \nWhat can I do for you?";
        System.out.println(start);

        // Get relative directory & init
        String dir = System.getProperty("user.dir");
        String textDir = dir + "/data/duke.txt";
        File data = new File(textDir);
        ArrayList<Task> arr = new ArrayList<>();

        // Check if duke.txt exists, if not create file and retrieve data
        if (!data.exists()) {
            data.createNewFile();
        } else {
            Scanner read = new Scanner(data);
            while (read.hasNext()) {
                String currLine = read.nextLine();
                Scanner cL = new Scanner(currLine);
                String type = cL.next();
                cL.next();
                String isDone = cL.next();
                cL.next();
                if (type.equals("T")) {
                    Todo toAdd = new Todo(cL.nextLine().substring(1));
                    if (isDone.equals("1")) {
                        toAdd.markAsDone();
                    }
                    arr.add(toAdd);
                } else if (type.equals("D")) {
                    String desc = cL.next();
                    String add = cL.next();
                    while (!add.equals("|")) {
                        desc = desc + " " + add;
                        add = cL.next();
                    }
                    String time = cL.nextLine().substring(1);
                    Deadline toAdd = new Deadline(desc, time);
                    if (isDone.equals("1")) {
                        toAdd.markAsDone();
                    }
                    arr.add(toAdd);
                } else if (type.equals("E")) {
                    String desc = cL.next();
                    String add = cL.next();
                    while (!add.equals("|")) {
                        desc = desc + " " + add;
                        add = cL.next();
                    }
                    String time = cL.nextLine().substring(1);
                    Event toAdd = new Event(desc, time);
                    if (isDone.equals("1")) {
                        toAdd.markAsDone();
                    }
                    arr.add(toAdd);
                }
            }
        }

        // Main logic, take in input from scanner and process accordingly
        Scanner sc = new Scanner(System.in);
        while (true) {
            String line = sc.nextLine();
            Scanner scan = new Scanner(line);
            String in = scan.next();
            if (in.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (in.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int j = 1; j <= arr.size(); j++) {
                    String output = j + ". " + arr.get(j - 1);
                    System.out.println(output);
                }
            } else if (in.equals("done")) {
                System.out.println("Nice! I've marked this task as done:");
                int ind = scan.nextInt() - 1;
                arr.get(ind).markAsDone();
                System.out.println(arr.get(ind));
            } else if (in.equals("delete")) {
                System.out.println("Noted. I've removed this task:");
                int ind = scan.nextInt() - 1;
                System.out.println(arr.get(ind));
                arr.remove(ind);
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
            } else if (in.equals("todo")) {
                if (!scan.hasNext()) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                String desc = scan.nextLine().substring(1);
                System.out.println("Got it. I've added this task:");
                Todo curr = new Todo(desc);
                System.out.println(curr);
                arr.add(curr);
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
            } else if (in.equals("deadline")) {
                if (!scan.hasNext()) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                String desc = scan.next();
                System.out.println("Got it. I've added this task:");
                String dead;
                String word = scan.next();
                while (word.indexOf('/') < 0) {
                    desc += " ";
                    desc += word;
                    word = scan.next();
                }
                dead = (scan.nextLine()).substring(1);
                Deadline curr = new Deadline(desc, dead);
                System.out.println(curr);
                arr.add(curr);
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
            } else if (in.equals("event")) {
                if (!scan.hasNext()) {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                }
                String desc = scan.next();
                System.out.println("Got it. I've added this task:");
                String time;
                String word = scan.next();
                while (word.indexOf('/') < 0) {
                    desc += " ";
                    desc += word;
                    word = scan.next();
                }
                time = (scan.nextLine()).substring(1);
                Event curr = new Event(desc, time);
                System.out.println(curr);
                arr.add(curr);
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            saveData(arr, textDir);
        }
    }

    /**
     * Function to write data to txt file. Processes the instances and then converts to String to be written.
     * @param array
     * @param directory
     * @throws IOException
     */
    public static void saveData(ArrayList<Task> array, String directory) throws IOException {
        FileWriter fw = new FileWriter(directory);
        for (Task curr : array) {
            String toWrite = null;
            if (curr instanceof Todo) {
                String state = curr.isDone ? " | 1 | " : " | 0 | ";
                toWrite = "T" + state + curr.description;
            } else if (curr instanceof Deadline) {
                String state = curr.isDone ? " | 1 | " : " | 0 | ";
                toWrite = "D" + state + curr.description + " | " + curr.dateTime;
                ;
            } else if (curr instanceof Event) {
                String state = curr.isDone ? " | 1 | " : " | 0 | ";
                toWrite = "E" + state + curr.description + " | " + curr.dateTime;
            }
            assert toWrite != null;
            fw.write(toWrite);
            fw.write(System.lineSeparator());
        }
        fw.close();
    }
}
