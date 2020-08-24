import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<Task> taskList;
    static String filePath = "data/duke.txt";

    public Duke() {
        this.taskList = new ArrayList<>();
    }

    public void init() {
        System.out.println("Hello! I'm Duke baby :)\n" + "What can I do for you?");
    }
    public void run() {
        Scanner scanner = new Scanner(System.in);
    }


    /** Updates the file in hard disk based on the new taskList. **/
    public static void updateFile(String filePath, ArrayList<Task> taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (int index = 0; index < taskList.size(); index++) {
            Task task = taskList.get(index);
            fileWriter.write(task.fileString() + "\n");
        }
        fileWriter.close();
    }

    public static void main(String[] args) throws IOException {
        Duke duke = new Duke();
        duke.run();
        duke.init();
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        while (true) {
            try {
                String input = scanner.nextLine();
                if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        String stringCount = String.valueOf(i + 1);
                        System.out.println(stringCount + ". " + taskList.get(i));
                    }
                } else if (input.length() >= 6 && input.substring(0, 4).equals("done")) {
                    int index = Integer.valueOf(input.substring(5)) - 1;
                    Task currentTask = taskList.get(index);
                    currentTask.markDone();
                    taskList.set(index, currentTask);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("\t" + currentTask);

                } else if (input.length() >= 8 && input.substring(0, 6).equals("delete")) {
                    int index = Integer.valueOf(input.substring(7)) - 1;
                    Task currentTask = taskList.get(index);
                    taskList.remove(index);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("\t" + currentTask);
                    System.out.println("Now you have " + String.valueOf(taskList.size()) + " tasks in the list.");
                } else if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.length() >=4 && input.substring(0, 4).equals("todo")) {
                    if (input.length() == 4 || (input.length() == 5 && input.substring(4,5).equals(" "))) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    } else if (input.length() == 5 && !input.substring(4,5).equals(" ")) {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    Task newTask = new TodoTask(input);
                    taskList.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + newTask);
                    System.out.println("Now you have " + String.valueOf(taskList.size()) + " tasks in your list.");
                    updateFile(filePath, taskList );

                } else if (input.length() >= 8 && input.substring(0, 8).equals("deadline")) {
                    if (input.length() == 8 || (input.length() == 9 && input.substring(8,9).equals(" "))) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    } else if (input.length() == 9 && !input.substring(8,9).equals(" ")) {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    String[] descDue = input.substring(9).split("/");
                    Task newTask = new DeadlineTask(descDue[0], descDue[1]);
                    taskList.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + newTask);
                    System.out.println("Now you have " + String.valueOf(taskList.size()) + " tasks in your list.");

                } else if (input.length() >= 5 && input.substring(0, 5).equals("event")) {
                    if (input.length() == 5 || (input.length() == 6 && input.substring(5,6).equals(" "))) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    } else if (input.length() == 6 && !input.substring(5,6).equals(" ")) {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    String[] descDue = input.substring(6).split("/");
                    Task newTask = new EventTask(descDue[0], descDue[1]);
                    taskList.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + newTask);
                    System.out.println("Now you have " + String.valueOf(taskList.size()) + " tasks in your list.");
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException err) {
                System.out.println(err.getMessage());
            }
        }
    }
}
