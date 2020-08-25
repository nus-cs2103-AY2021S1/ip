import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Duke {

    static String line = "_________________________________________________________________";
    static List<Task> list = new ArrayList<>();

    public static void greeting() {
        String str = ("\t" + line + "\n"
                + "\tHello! I'm Duke\n"
                + "\tWhat can I do for you?\n"
                + "\t" + line);
        System.out.println(str);
    }

    public static void bye(){
        System.out.println("\t" + line + "\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t" + line);
    }

    public static void addTask(Task task) {
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

    public static String getLocalDate(String time) {
        LocalDate d = LocalDate.parse(time);
        return d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }



    public static void main(String[] args) {
        greeting();
        Scanner sc = new Scanner(System.in);
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
                        String date = getLocalDate(time);
                        Task newTask = new Deadline(description, date);
                        addTask(newTask);
                    } catch (IndexOutOfBoundsException e) {
                        System.err.println("☹ OOPS!!! The description or date of a deadline cannot be empty.");
                    } catch (DateTimeParseException e) {
                        System.err.println("☹ OOPS!!! The date must be valid and in the format of YYYY-MM-DD.");
                    }
                } else if (firstWord(input).equals("event")) {
                    try {
                        String str = input.substring(6);
                        String description = str.split(" /at ")[0]; // split the stirng by "/by ", take first half
                        String time = str.split(" /at ")[1];
                        String date = getLocalDate(time);
                        Task newTask = new Event(description, date);
                        addTask(newTask);
                    } catch (IndexOutOfBoundsException e) {
                        System.err.println("☹ OOPS!!! The description or date of a event cannot be empty.");
                    } catch (DateTimeParseException e) {
                        System.err.println("☹ OOPS!!! The date must be valid and in the format of YYYY-MM-DD.");
                    }
                } else { // when input cannot be recognised
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) { // catch all the exception I manually throwed in the try block
                System.err.println(e);
            }
        }
    }
}
