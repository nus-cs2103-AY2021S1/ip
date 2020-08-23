import java.io.*;
import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RunYoo {

    public static void run(Scanner sc) throws Exception {

        //initialise ArrayList of tasks
        ArrayList<Task> al = new ArrayList<>();


        //check if data & duke.txt exists
        try {
            java.nio.file.Path dataPath = java.nio.file.Paths.get(".", "data");
            java.nio.file.Path dukePath = java.nio.file.Paths.get(".", "data", "duke.txt");

            File dataFile = new File(String.valueOf(dataPath));
            File duke = new File(String.valueOf(dukePath));

            if (dataFile.exists()) {
                if (duke.exists()) {
                    FileReader fr = new FileReader(duke);
                    BufferedReader br = new BufferedReader(fr);
                    StringBuffer sb = new StringBuffer();
                    String line;
                    while ((line = br.readLine()) != null) {
                        String temp[] = line.split(" // ", 4);
                        addTaskToArrayList(temp, al);
                    }
                    if (al.size() != 0) {
                        System.out.println("\n(By the way, here's your saved list!)");
                        displayList(al);
                        fr.close();
                    }
                } else {
                    duke.createNewFile();
                }
            } else {
                //create data file
                dataFile.mkdir();
                duke.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //creating tasks
        String input = sc.nextLine();

        while (!input.equals("bye")) {

            String temp[] = input.split(" ", 2);

            if (input.equals("list")) {
                System.out.println("Here's your list!");
                displayList(al);

            } else {
                try {
                    switch (temp[0]) {
                    case "done":
                        markAsDone(al, temp);
                        break;
                    case "delete":
                        deleteTask(al, temp);
                        break;
                    case "todo":
                        addTodo(al, temp);
                        break;
                    case "deadline":
                        addDeadline(al, temp);
                        break;
                    case "event":
                        addEvent(al, temp);
                        break;
                    default:
                        throw new YooException("Sorry, I didn't get that (\u3063*\u00B4\u25A1`)\u3063");
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("You're missing a task description (\u3063*\u00B4\u25A1`)\u3063");
                }
            }
            input = sc.nextLine();
        }
        //when "bye" is entered
        File newDuke = new File("./data/duke.txt");
        saveTasksToFile(al, newDuke);
        System.out.println("Bye! Come back soon ( ^-^)/");
    }

    private static void saveTasksToFile(ArrayList<Task> al, File duke) throws IOException {
        FileWriter fw = new FileWriter(duke, false);

        for (Task t : al) {
            int isDone = t.isDone ? 1 : 0;

            switch (t.getClass().getName()) {
            case "Todo":
                fw.write("T // " + isDone + " // " + t.description + " // \n");
                break;
            case "Deadline":
                Deadline dl = (Deadline) t;
                fw.write("D // " + isDone + " // " + dl.description + " // " + dl.by + "\n");
                break;
            case "Event":
                Event e = (Event) t;
                fw.write("E // " + isDone + " // " + e.description + " // " + e.at + "\n");
                break;
            }
        }
        fw.close();
    }

    private static void addTaskToArrayList(String[] temp, ArrayList<Task> al) {
        boolean isDone = Integer.parseInt(temp[1]) == 0;
        switch (temp[0]) {
        case "T":
            Todo td = new Todo(temp[2], isDone);
            al.add(td);
            break;
        case "D":
            LocalDate dldate = LocalDate.parse(temp[3]);
            Deadline dl = new Deadline(temp[2], isDone, dldate);
            al.add(dl);
            break;
        case "E":
            LocalDate edate = LocalDate.parse(temp[3]);
            Event e = new Event(temp[2], isDone, edate);
            al.add(e);
            break;
        }
    }


    private static void displayList(ArrayList<Task> al) {
        for (int i = 1; i <= al.size(); i ++) {
            System.out.println(i + ". "
                    + al.get(i - 1));
        }
    }

    private static void markAsDone(ArrayList<Task> al, String[] temp) throws YooException {
        int index = Integer.parseInt(temp[1]);
        if (index > al.size()) {
            throw new YooException("No such task (>_<)");
        } else {
            System.out.println("Good job completing the task! \u256D( \uFF65\u3142\uFF65)\u0648");
            Task t = al.get(index - 1);
            t.markAsDone();
            System.out.println(index + ". " + t);
        }
    }

    private static void deleteTask(ArrayList<Task> al, String[] temp) throws YooException {
        int index = Integer.parseInt(temp[1]);
        if (index > al.size()) {
            throw new YooException("No such task (>_<)");
        } else {
            Task t = al.remove(index - 1);
            System.out.println("I've deleted the following task! \n" + t);
            System.out.println("Now you have " + al.size() + " tasks in the list (\u00B4\u30FB\u03C9\u30FB\uFF40)");
        }
    }

    private static void addTodo(ArrayList<Task> al, String[] temp) {
        Todo td = new Todo(temp[1]);
        al.add(td);
        System.out.println("I've added the following task! \n" + td);
        System.out.println("Now you have " + al.size() + " tasks in the list (\u00B4\u30FB\u03C9\u30FB\uFF40)");
    }

    private static void addDeadline(ArrayList<Task> al, String[] temp) {
        try {
            String[] a = temp[1].split("/by ", 2);
            LocalDate by = LocalDate.parse(a[1]);
            Deadline dl = new Deadline(a[0], by);
            al.add(dl);
            System.out.println("I've added the following task! \n" + dl);
            System.out.println("Now you have " + al.size() + " tasks in the list (\u00B4\u30FB\u03C9\u30FB\uFF40)");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry, your deadline time is missing (\u3063*\u00B4\u25A1`)\u3063");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date! Please try again (>_<)");
        }
    }

    private static void addEvent(ArrayList<Task> al, String[] temp) {
        try {
            String[] a = temp[1].split("/at ", 2);
            LocalDate at = LocalDate.parse(a[1]);
            Event e = new Event(a[0], at);
            al.add(e);
            System.out.println("I've added the following task! \n" + e);
            System.out.println("Now you have " + al.size() + " tasks in the list (\u00B4\u30FB\u03C9\u30FB\uFF40)");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry, your event time is missing (\u3063*\u00B4\u25A1`)\u3063");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date! Please try again (>_<)");
        }
    }
}