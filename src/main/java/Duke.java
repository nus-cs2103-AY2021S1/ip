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

    public static String[] splitString(String s) {
        return s.split(" ", 2);
    }

    public static void main(String[] args) {
        String endCommand = "bye";
        String listCommand = "list";
        String doneCommand = "done";
        String toDoCommand = "todo";
        String deadlineCommand = "deadline";
        String eventCommand = "event";
        ArrayList<Task> ls = new ArrayList<Task>();

        System.out.println(start());

        Scanner in = new Scanner(System.in);
        String s = in.nextLine();

        while(!s.equals(endCommand)) {
            System.out.println(linePrinter());

            if (s.equals(listCommand)) { // the case where tasks are listed
                System.out.println(("Here are the tasks in your list:").replaceAll("(?m)^", "\t"));

                for (Task t : ls) {
                    System.out.println(
                            ((ls.indexOf(t) + 1) + ". " +
                            t.getStatus()).replaceAll("(?m)^", "\t"));
                }
            } else {
                String[] words = s.split(" ", 2);
                if (words[0].equals(doneCommand)) { // the case where something is done
                    int number = Integer.parseInt(words[1]);

                    if (number > ls.size()) { // if the task number does not exist
                        System.out.println("Can't find this task :(".replaceAll("(?m)^", "\t"));
                    } else {
                        Task oldTask = ls.get(number - 1);
                        oldTask.checkTask();
                        System.out.println(("Nice! I've marked this task as done:\n" +
                                        oldTask.getStatus()).replaceAll("(?m)^", "\t"));
                    }
                } else { // the case where tasks are added
                    if (words[0].equals(toDoCommand)) {
                        ToDo newTD = new ToDo(words[1]);
                        ls.add(newTD);
                        String thing = "Got it. I've added this task:\n" + newTD.getStatus().replaceAll("(?m)^", "\t")
                                + "\nNow you have " + ls.size() + " tasks in the list.";
                        System.out.println(thing.replaceAll("(?m)^", "\t"));
                    } else if (words[0].equals(deadlineCommand)) {
                        String[] stuff = words[1].split(" /by ");
                        Deadline newDL = new Deadline(stuff[0], stuff[1]);
                        ls.add(newDL);
                        String thing = "Got it. I've added this task:\n" + newDL.getStatus().replaceAll("(?m)^", "\t")
                                + "\nNow you have " + ls.size() + " tasks in the list.";
                        System.out.println(thing.replaceAll("(?m)^", "\t"));
                    } else if (words[0].equals(eventCommand)) {
                        String[] stuff = words[1].split(" /at ");
                        Event newE = new Event(stuff[0], stuff[1]);
                        ls.add(newE);
                        String thing = "Got it. I've added this task:\n" + newE.getStatus().replaceAll("(?m)^", "\t")
                                + "\nNow you have " + ls.size() + " tasks in the list.";
                        System.out.println(thing.replaceAll("(?m)^", "\t"));
                    } else {
                        System.out.println("Hm I'm not sure what you mean. :?");
                    }
                }
            }

            System.out.println(linePrinter());
            s = in.nextLine();
        }

        String byeText = "Bye. Hope to see you again soon!";
        System.out.println(linePrinter() +
                byeText.replaceAll("(?m)^", "\t") +
                linePrinter());
    }
}
