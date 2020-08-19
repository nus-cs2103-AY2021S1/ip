import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static String linePrinter() {
        return ("\n--------------------------------------------------------\n").replaceAll("(?m)^", "\t");
    }

    public static String start() {
        String s = ("Hello! I'm Duke\n" +
                "What can I do for you?");
        return linePrinter() +
                s.replaceAll("(?m)^", "\t") +
                linePrinter();
    }

    public static void main(String[] args) {
        String endCommand = "bye";
        String listCommand = "list";
        String doneCommand = "done";
        ArrayList<Task> ls = new ArrayList<Task>();

        System.out.println(start());

        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        while(!s.equals(endCommand)) {
            if (s.equals(listCommand)) { // the case where tasks are listed
                System.out.println(linePrinter() +
                        ("Here are the tasks in your list:").replaceAll("(?m)^", "\t"));

                for (Task t : ls) {
                    System.out.println(
                            ((ls.indexOf(t) + 1) + ". " +
                            t.getStatus()).replaceAll("(?m)^", "\t"));
                }

                System.out.println(linePrinter());
            } else {
                String[] words = s.split(" ");
                if (words.length > 1) { // the case where something is done
                    int number = Integer.parseInt(words[1]);

                    if (number > ls.size()) {
                        System.out.println(linePrinter() +
                                "Can't find this task :(".replaceAll("(?m)^", "\t") +
                                linePrinter());
                    } else {
                        Task newTask = ls.get(number - 1);
                        newTask.checkTask();
                        System.out.println(linePrinter() +
                                ("Nice! I've marked this task as done:\n" +
                                        newTask.getStatus()).replaceAll("(?m)^", "\t") +
                                linePrinter());
                    }
                } else { // the case where tasks are added
                    Task newTask = new Task(s);
                    ls.add(newTask);
                    String thing = "added: " + s;
                    System.out.println(linePrinter() +
                            thing.replaceAll("(?m)^", "\t") +
                            linePrinter());
                }
            }
            s = in.nextLine();
        }

        if (s.equals(endCommand)) {
            String byeText = "Bye. Hope to see you again soon!";
            System.out.println(linePrinter() +
                    byeText.replaceAll("(?m)^", "\t") +
                    linePrinter());
        }
    }
}
