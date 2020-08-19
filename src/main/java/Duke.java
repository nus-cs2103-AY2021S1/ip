import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static String linePrinter() {
        return ("\n--------------------------------------------------------\n");
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

            try {
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
                        if (words.length < 2) { // if nothing written after done
                            throw new DukeDoneException("Nothing's done :?");
                        } else {
                            int number = Integer.parseInt(words[1]);
                            if (number > ls.size()) {
                                throw new DukeNotSureException("This task doesn't seem to exist :?");
                            } else {
                                Task oldTask = ls.get(number - 1);
                                oldTask.checkTask();
                                System.out.println(("Nice! I've marked this task as done:\n" +
                                        oldTask.getStatus()).replaceAll("(?m)^", "\t"));
                            }
                        }
                    } else { // the case where tasks are added
                        if (words[0].equals(toDoCommand)) {
                            if (words.length < 2) {
                                throw new DukeNotSureException("What are you trying to do :?");
                            } else {
                                ToDo newTD = new ToDo(words[1]);
                                ls.add(newTD);
                                String thing = "Got it. I've added this task:\n" + newTD.getStatus().replaceAll("(?m)^", "\t")
                                        + "\nNow you have " + ls.size() + " tasks in the list.";
                                System.out.println(thing.replaceAll("(?m)^", "\t"));
                            }
                        } else if (words[0].equals(deadlineCommand)) {
                            if (words.length < 2) {
                                throw new DukeNotSureException("What are deadlines :?");
                            } else {
                                String[] stuff = words[1].split(" /by ");
                                Deadline newDL = new Deadline(stuff[0], stuff[1]);
                                ls.add(newDL);
                                String thing = "Got it. I've added this task:\n" + newDL.getStatus().replaceAll("(?m)^", "\t")
                                        + "\nNow you have " + ls.size() + " tasks in the list.";
                                System.out.println(thing.replaceAll("(?m)^", "\t"));
                            }
                        } else if (words[0].equals(eventCommand)) {
                            if (words.length < 2) {
                                throw new DukeNotSureException("What event are you making :?");
                            } else {
                                String[] stuff = words[1].split(" /at ");
                                Event newE = new Event(stuff[0], stuff[1]);
                                ls.add(newE);
                                String thing = "Got it. I've added this task:\n" + newE.getStatus().replaceAll("(?m)^", "\t")
                                        + "\nNow you have " + ls.size() + " tasks in the list.";
                                System.out.println(thing.replaceAll("(?m)^", "\t"));
                            }
                        } else {
                            throw new DukeNotSureException("Man I don't know what you want :?");
                        }
                    }
                }
            } catch (DukeDoneException e) {
                System.out.println(e.getMessage());
            } catch (DukeNotSureException e) {
                System.out.println(e.getMessage());
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
