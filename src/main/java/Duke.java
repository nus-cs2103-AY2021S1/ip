import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.time.LocalDate;

public class Duke {
    static String[] flavour = {"(Ф Д Ф)", "(=ↀ ω ↀ=)✧", "ฅ( *Φ ω Φ* ) ฅ", "( *Φ ω Φ* )", "(ꐦ°᷄ д °᷅)", "(ꐦ ಠ皿ಠ )", "(ꐦ°᷄ д °᷅)"};
    static Random ran = new Random();
    static int magic = 7;

    public static String linePrinter() {
        return ("\n-------------------------------------------------------------------------\n");
    }

    public static String start() {
        String s = ("Hello! I'm Duke\n" +
                "What can I do for you?            " + flavour[ran.nextInt(7)]);
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
        String deleteCommand = "delete";
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
                    System.out.println(("Here are the tasks in your list:            " + flavour[ran.nextInt(7)]).replaceAll("(?m)^", "\t"));

                    for (Task t : ls) {
                        System.out.println(
                                ((ls.indexOf(t) + 1) + ". " +
                                        t.getStatus()).replaceAll("(?m)^", "\t"));
                    }
                } else {
                    String[] words = s.split(" ", 2);
                    if (words[0].equals(doneCommand)) { // the case where something is done
                        if (words.length < 2) { // if nothing written after done
                            throw new DukeDoneException("Nothing's done :?            " + flavour[ran.nextInt(magic)]);
                        } else {
                            int number = Integer.parseInt(words[1]);
                            if (number > ls.size()) {
                                throw new DukeNotSureException("This task doesn't seem to exist :?            " + flavour[ran.nextInt(magic)]);
                            } else {
                                Task oldTask = ls.get(number - 1);
                                oldTask.checkTask();
                                System.out.println(("Nice! I've marked this task as done:             " + flavour[ran.nextInt(magic)] +
                                        "\n" +
                                        oldTask.getStatus()).replaceAll("(?m)^", "\t"));
                            }
                        }
                    } else if (words[0].equals(deleteCommand)) {
                        if (words.length < 2) { // if nothing written after done
                            throw new DukeDoneException("Nothing's deleted :?             " + flavour[ran.nextInt(magic)]);
                        } else {
                            int number = Integer.parseInt(words[1]);
                            if (number > ls.size()) {
                                throw new DukeNotSureException("This task doesn't seem to exist :?             " + flavour[ran.nextInt(magic)]);
                            } else {
                                Task oldTask = ls.get(number - 1);
                                ls.remove(number - 1);
                                String thing = "Running away from your responsibilities huh. Deleted:             " + flavour[ran.nextInt(magic)] +
                                        "\n" +
                                        oldTask.getStatus().replaceAll("(?m)^", "\t") +
                                        "\nNow you have " + ls.size() + " tasks in the list.";
                                System.out.println(thing.replaceAll("(?m)^", "\t"));
                            }
                        }
                    } else { // the case where tasks are added
                        if (words[0].equals(toDoCommand)) {
                            if (words.length < 2) {
                                throw new DukeNotSureException("What are you trying to do :?             " + flavour[ran.nextInt(magic)]);
                            } else {
                                ToDo newTD = new ToDo(words[1]);
                                ls.add(newTD);
                                String thing = "Got it. I've added this task:\n" +
                                        newTD.getStatus().replaceAll("(?m)^", "\t") +
                                        "\nNow you have " + ls.size() + " tasks in the list.             " + flavour[ran.nextInt(magic)];
                                System.out.println(thing.replaceAll("(?m)^", "\t"));
                            }
                        } else if (words[0].equals(deadlineCommand)) {
                            if (words.length < 2) {
                                throw new DukeNotSureException("What are deadlines :?             " + flavour[ran.nextInt(magic)]);
                            } else {
                                String[] stuff = words[1].split(" /by ");
                                LocalDate day = LocalDate.parse(stuff[1]);
                                Deadline newDL = new Deadline(stuff[0], day);
                                ls.add(newDL);
                                String thing = "Got it. I've added this task:\n" + newDL.getStatus().replaceAll("(?m)^", "\t")
                                        + "\nNow you have " + ls.size() + " tasks in the list.             " + flavour[ran.nextInt(magic)];
                                System.out.println(thing.replaceAll("(?m)^", "\t"));
                            }
                        } else if (words[0].equals(eventCommand)) {
                            if (words.length < 2) {
                                throw new DukeNotSureException("What event are you making :?");
                            } else {
                                String[] stuff = words[1].split(" /at ");
                                LocalDate day = LocalDate.parse(stuff[1]);
                                Event newE = new Event(stuff[0], day);
                                ls.add(newE);
                                String thing = "Got it. I've added this task:\n" + newE.getStatus().replaceAll("(?m)^", "\t")
                                        + "\nNow you have " + ls.size() + " tasks in the list.             " + flavour[ran.nextInt(magic)];
                                System.out.println(thing.replaceAll("(?m)^", "\t"));
                            }
                        } else {
                            throw new DukeNotSureException("Man I don't know what you want :?             " + flavour[ran.nextInt(magic)]);
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

        String byeText = "Running away huh?? " + flavour[ran.nextInt(7)];
        System.out.println(linePrinter() +
                byeText.replaceAll("(?m)^", "\t") +
                linePrinter());
    }
}
