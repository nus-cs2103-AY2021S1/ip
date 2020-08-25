import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Scanner;

public class Duke {

    static String line = "_________________________________________________________________";
    static List<Task> list = new ArrayList<>();
    static File file = new File("data/duke.txt");

    // greet while read all data from duke.txt
    public static void greeting() throws FileNotFoundException{
        String str = ("\t" + line + "\n"
                + "\tHello! I'm Duke\n"
                + "\tWhat can I do for you?\n"
                + "\t" + line);
        System.out.println(str);

        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String task = sc.nextLine();
            String description = task.substring(7);
            // check the 5th char is tick or cross
            boolean isDone = task.charAt(4) == '\u2713';
            Task newTask;

            // check the second character is T/D/E
            if (task.charAt(1) == 'T') {
                newTask = new Todo(description, isDone);
            } else if (task.charAt(1) == 'D') {
                newTask = new Deadline(description, isDone);
            } else {
                newTask = new Event(description, isDone);
            }
            list.add(newTask);
        }
        sc.close();
    }

    // re-write the file after saying bye
    public static void bye() throws IOException{
        System.out.println("\t" + line + "\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t" + line);

        FileWriter fw = new FileWriter(file);
        for (Task task : list) {
            fw.write(task + "\n");
        }
        fw.close();
    }

    public static void addTask(Task task) throws IOException {
        list.add(task);
        System.out.println("\t" + line + "\n\tGot it. I've added this task:\n"
                + "\t  " + task + "\n"
                + "\tNow you have " + list.size() + " tasks in the list.\n"
                + "\t" + line);
    }

    public static void showList(){
        System.out.println("\t" + line + "\n\tHere are the tasks in your list:");
        int index = 1;
        for (Task t : list){
            System.out.println("\t" + index + ". " + t);
            index++;
        }
        System.out.println("\t" + line);
    }

    public static String firstWord(String str) {
        return str.split(" ")[0]; // create array of words and return 0th word
    }

    public static int getTaskNum(String str) {
        return Integer.parseInt(str.split(" ")[1]);
    }

    // num is the number in front of each task
    public static void markDone(int num) {
            list.get(num-1).markDone();
            System.out.println("\t" + line + "\n\tNice! I've marked this task as done:\n\t  "
                    + list.get(num-1)
                    + "\n\t" + line);
    }

    public static void delete(int num) {
        System.out.println("\t" + line + "\n\tNoted. I've removed this task:\n\t  "
                + list.get(num-1)
                + "\n\tNow you have " + (list.size() - 1) + " tasks in the list."
                + "\n\t" + line);
        list.remove(num-1);
    }

    public static void main(String[] args) {
        try {
            greeting();
        } catch (Exception e) {
            System.err.println(e);
        }

        Scanner sc = new Scanner(System.in);
        // inside the loop, must catch the error
        // if not the exception will break the loop, thus system terminates
        while (true) {
            try {
                String input = sc.nextLine().toLowerCase(); // everything in lower case
                // possible actions: type task (todos/deadline/event), list, bye, done 2
                if (input.equals("bye")) {
                    bye();
                    sc.close();
                    break;
                } else if (input.equals("list")) {
                    showList();
                } else if (firstWord(input).equals("done")) {
                    try {
                        int taskNum = getTaskNum(input);
                        markDone(taskNum);
                    } catch (IndexOutOfBoundsException | NumberFormatException e) {
                        System.err.println("☹ OOPS!!! The task number is invalid.");
                    }
                } else if (firstWord(input).equals("delete")) {
                    try {
                        int taskNum = getTaskNum(input);
                        delete(taskNum);
                    } catch (IndexOutOfBoundsException | NumberFormatException e) {
                        System.err.println("☹ OOPS!!! The task number is invalid.");
                    }
                } else if (firstWord(input).equals("todo")) {
                    try {
                        Task newTask = new Todo(input.substring(5)); // cut "todo "
                        addTask(newTask);
                    } catch (IndexOutOfBoundsException e) {
                        System.err.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                } else if (firstWord(input).equals("deadline")) {
                    try {
                        String str = input.substring(9);
                        String description = str.split(" /by ")[0]; // split the stirng by "/by ", take first half
                        String time = str.split(" /by ")[1];
                        Task newTask = new Deadline(description, time);
                        addTask(newTask);
                    } catch (IndexOutOfBoundsException e) {
                        System.err.println("☹ OOPS!!! The description or date of a deadline cannot be empty.");
                    }
                } else if (firstWord(input).equals("event")) {
                    try {
                        String str = input.substring(6);
                        String description = str.split(" /at ")[0]; // split the stirng by "/by ", take first half
                        String time = str.split(" /at ")[1];
                        Task newTask = new Event(description, time);
                        addTask(newTask);
                    } catch (IndexOutOfBoundsException e) {
                        System.err.println("☹ OOPS!!! The description or date of a event cannot be empty.");
                    }
                } else { // when input cannot be recognised
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException | IOException e) { // catch all the exception I manually throwed in the try block
                System.err.println(e);
            }
        }
    }
}
