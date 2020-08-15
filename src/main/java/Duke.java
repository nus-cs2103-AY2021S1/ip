import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> taskList;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        taskList = new ArrayList<>();


        intro();

        String input = "";
        do {
            input = sc.nextLine();
            process(input);

        } while (!input.equals("bye"));

    }

    /**
     * Prints output enclosed in top and bottom horizontal lines
     * @param message message to be output
     */
    private static void println(String[] message) {
        System.out.println("\t____________________________________________________________");
        for (String s : message) {
            System.out.println("\t" + s);
        }
        System.out.println("\t____________________________________________________________");
    }
    private static void println(String message) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + message);
        System.out.println("\t____________________________________________________________");
    }

    private static void intro() {
        String[] msg = new String[] {"Hello! I'm KING!", "What can I do for you?"} ;
        println(msg);
    }

    private static void exit() {
        println("Bye. Hope to see you again soon!");
    }

    private static void list() {
        String[] output = new String[taskList.size() + 1];
        output[0] = " Here are the tasks in your list:";

        for (int i = 1; i <= taskList.size(); i++) {
            output[i] = i + "." + taskList.get(i-1);
        }

        println(output);
    }

    private static void done(String num) {
        try {
            int selected = Integer.parseInt(num);
            taskList.get(selected-1).setDone();

            String[] output = new String[] { "Nice! I've marked this task as done: ", taskList.get(selected-1).toString() };
            println(output);
        } catch (NumberFormatException nfe) {
            println("Not a number: " + num);
        } catch (IndexOutOfBoundsException iooob) {
            println("Invalid Selection: " + num);
        }
    }

    private static void process(String msg) {
        if (msg.equals("bye"))
            exit();
        else if (msg.equals("list"))
            list();
        else if (msg.startsWith("done "))
            done(msg.substring(5));
        else {
            println(new String[]{"added: " + msg});
            taskList.add(new Task(msg));
        }
    }
}
