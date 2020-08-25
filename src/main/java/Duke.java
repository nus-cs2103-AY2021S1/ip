import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.time.LocalDate;

public class Duke {

    public static String linePrinter() {
        return ("\n-------------------------------------------------------------------------\n");
    }

    public static String start() {
        String s = ("Hello! I'm Duke\n" +
                "What can I do for you?            ");
        return linePrinter() +
                s.replaceAll("(?m)^", "\t") +
                linePrinter();
    }

    public static String[] splitString(String s) {
        return s.split(" ", 2);
    }

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd + System.lineSeparator());
        fw.close();
    }

    public static void readFromFile(String filePath, ArrayList<Task> ls) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] arr = line.split("\\*");
            String task = arr[0];
            if (task.equals("E")) { // case where the task is an event
                boolean status = arr[1].equals("\u2713");
                String todo = arr[2];
                String deadline = arr[3];
                ls.add(new Event(todo, deadline, status));
            } else if (task.equals("T")) {
                boolean status = arr[1].equals("\u2713");
                String todo = arr[2];
                ls.add(new ToDo(todo, status));
            } else {
                boolean status = arr[1].equals("\u2713");
                String todo = arr[2];
                String deadline = arr[3];
                ls.add(new Deadline(todo, deadline, status));
            }
        }
    }

    public static void duker() throws IOException {
            String endCommand = "bye";
            String listCommand = "list";
            String doneCommand = "done";
            String deleteCommand = "delete";
            String toDoCommand = "todo";
            String deadlineCommand = "deadline";
            String eventCommand = "event";

            ArrayList<Task> ls = new ArrayList<Task>();

            readFromFile("duke.txt", ls);

            System.out.println(start());

            Scanner in = new Scanner(System.in);
            String s = in.nextLine();

            while (!s.equals(endCommand)) {
                try {
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
                            if (words.length < 2) { // if nothing written after done
                                throw new DukeDoneException("Nothing's done? :s");
                            } else {
                                int number = Integer.parseInt(words[1]);
                                if (number > ls.size()) {
                                    throw new DukeNotSureException("This task doesn't seem to exist? :s");
                                } else {
                                    Task oldTask = ls.get(number - 1);
                                    oldTask.checkTask();
                                    System.out.println(("Nice! I've marked this task as done:" +
                                            "\n" +
                                            oldTask.getStatus()).replaceAll("(?m)^", "\t"));
                                }
                            }
                        } else if (words[0].equals(deleteCommand)) {
                            if (words.length < 2) { // if nothing written after done
                                throw new DukeDoneException("Nothing's deleted? :s");
                            } else {
                                int number = Integer.parseInt(words[1]);
                                if (number > ls.size()) {
                                    throw new DukeNotSureException("This task doesn't seem to exist? :s");
                                } else {
                                    Task oldTask = ls.get(number - 1);
                                    ls.remove(number - 1);
                                    String thing = "Running away from your responsibilities huh. Deleted:" +
                                            "\n" +
                                            oldTask.getStatus().replaceAll("(?m)^", "\t") +
                                            "\nNow you have " + ls.size() + " tasks in the list.";
                                    System.out.println(thing.replaceAll("(?m)^", "\t"));
                                }
                            }
                        } else { // the case where tasks are added
                            if (words[0].equals(toDoCommand)) {
                                if (words.length < 2) {
                                    throw new DukeNotSureException("What are you trying to do :s");
                                } else {
                                    ToDo newTD = new ToDo(words[1], false);
                                    ls.add(newTD);
                                    String thing = "Alright then, add more things to your ever-growing list of tasks:\n" +
                                            newTD.getStatus().replaceAll("(?m)^", "\t") +
                                            "\nNow you have " + ls.size() + " tasks in the list.";
                                    System.out.println(thing.replaceAll("(?m)^", "\t"));
                                }
                            } else if (words[0].equals(deadlineCommand)) {
                                if (words.length < 2) {
                                    throw new DukeNotSureException("What are deadlines? :s");
                                } else {
                                    String[] stuff = words[1].split(" /by ");
                                    LocalDate day = LocalDate.parse(stuff[1]);
                                    Deadline newDL = new Deadline(stuff[0], day, false);
                                    ls.add(newDL);
                                    String thing = "Alright then, add more things to your ever-growing list of tasks:\n"
                                            + newDL.getStatus().replaceAll("(?m)^", "\t")
                                            + "\nNow you have " + ls.size() + " tasks in the list.";
                                    System.out.println(thing.replaceAll("(?m)^", "\t"));
                                }
                            } else if (words[0].equals(eventCommand)) {
                                if (words.length < 2) {
                                    throw new DukeNotSureException("What event are you making? :s");
                                } else {
                                    String[] stuff = words[1].split(" /by ");
                                    LocalDate day = LocalDate.parse(stuff[1]);
                                    Event newE = new Event(stuff[0], day, false);
                                    ls.add(newE);
                                    String thing = "Alright then, add more things to your ever-growing list of tasks:\n"
                                            + newE.getStatus().replaceAll("(?m)^", "\t")
                                            + "\nNow you have " + ls.size() + " tasks in the list.";
                                    System.out.println(thing.replaceAll("(?m)^", "\t"));
                                }
                            } else {
                                throw new DukeNotSureException("Man I don't know what you want? :s");
                            }
                        }
                    }

                } catch (DukeDoneException | DukeNotSureException e) {
                    System.out.println(e.getMessage());
                }

                System.out.println(linePrinter());
                s = in.nextLine();
            }

            String tasks = "";

            for (Task t : ls) {
                String check = "";
                if (t.isComplete()) {
                    check = "\u2713";
                } else {
                    check = "\u2718";
                }
                String toAdd = t.getType() + "*" + check + "*" + t.toString() + "*" + t.getTime() + "\n";
                tasks = tasks + toAdd;
            }

            writeToFile("duke.txt", tasks);

            String byeText = "Running away huh??";
            System.out.println(linePrinter() +
                    byeText.replaceAll("(?m)^", "\t") +
                    linePrinter());

    }

    public static void main(String[] args) {
        try {
            File f = new File("duke.txt");
            f.createNewFile();
            duker();
        } catch (IOException e) {
            System.out.println("Whoops! Some kind of error :/ see here: " + e.getMessage());
        }
    }
}
