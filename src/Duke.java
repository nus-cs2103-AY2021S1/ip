import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Duke {
    enum type {
        TODO{
            public String toString() {
                return "todo";
            }
        },
        DEADLINE{
            public String toString() {
                return "deadline";
            }
        },
        EVENT{
            @Override
            public String toString() {
                return "event";
            }
        },
        DELETE{
            @Override
            public String toString() {
                return "delete";
            }
        },
    }

    private static void printBorder() {
        System.out.println("____________________________________________________________\n");
    }

    public static Scanner userInput = new Scanner(System.in);
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static File taskList = new File("tasklist.txt");

    public static void main(String[] args) throws DukeException {
        try{
            if(taskList.exists()){
                FileReader fr = new FileReader(taskList);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while ((line = br.readLine()) != null) {
                    String[] task = line.split(" \\| ", 4);
                    rewriteList(task, tasks);
                }
                if (tasks.size() > 0) {
                    System.out.println("\nYou have a saved list! Here: ");
                    printList(tasks);
                    fr.close();
                }
            } else {
                taskList.createNewFile();
            }
        } catch(IOException e){
            e.printStackTrace();
        }

        printBorder();

        System.out.println("Hello I'm Duke\n");
        System.out.println("What can I do for you?\n");
        printBorder();
        String input = userInput.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printBorder();
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else if (input.contains("done")) {
                try {
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    tasks.get(index).markAsDone();
                    Save();
                    printBorder();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(index));
                } catch (Exception e) {
                    throw new DukeException(" ☹ OOPS!!! What task did you complete?");
                }
            } else {
                printBorder();
                if (input.contains(type.TODO.toString()) || input.contains(type.DEADLINE.toString()) || input.contains(type.EVENT.toString()) || input.contains(type.DELETE.toString())) {
                    if (input.contains(type.TODO.toString())) {
                        try {
                            Todo t = new Todo(input.substring(5));
                            tasks.add(t);
                            System.out.println("Got it. I've added this task:");
                            Save();
                            System.out.println(t);
                        } catch (Exception e) {
                            throw new DukeException(" ☹ Insufficient details! The description of a todo cannot be empty.");
                        }
                    } else if (input.contains(type.DEADLINE.toString())) {
                        try {
                            String[] s = input.split("/by ", 2);
                            String[] s2 = s[1].split("-", 3);
                            LocalDate by = LocalDate.parse(s2[2] + "-" + s2[1] + "-" + s2[0]);
                            String[] desc = s[0].split(" ", 2);
                            if (!desc[1].isEmpty()) {
                                Deadline dl = new Deadline(desc[1], by);
                                System.out.println("Got it. I've added this task:");
                                tasks.add(dl);
                                Save();
                                System.out.println(dl);
                            } else {
                                throw new DukeException(" ☹ Insufficient details! The description of a deadline cannot be empty.");
                            }
                        } catch (DateTimeParseException e) {
                            throw new DukeException(" ☹ Date wrongly entered, please format date in dd-MM-yyyy!");
                        }
                    } else if (input.contains(type.EVENT.toString())) {
                        try {
                            String[] s = input.split("/at ", 2);
                            String[] s2 = s[1].split("-", 3);
                            LocalDate at = LocalDate.parse(s2[2] + "-" + s2[1] + "-" + s2[0]);
                            String[] desc = s[0].split(" ", 2);
                            if (!desc[1].isEmpty()) {
                                Event e = new Event(desc[1], at);
                                System.out.println("Got it. I've added this task:");
                                tasks.add(e);
                                Save();
                                System.out.println(e);
                            } else {
                                throw new DukeException(" ☹ Insufficient details! The description of an event cannot be empty.");
                            }
                        } catch (DateTimeParseException e) {
                            throw new DukeException(" ☹ Date wrongly entered, please format date in dd-MM-yyyy!");
                        }
                    } else if (input.contains(type.DELETE.toString())) {
                        try {
                            int index = Integer.parseInt(input.substring(7)) - 1;
                            Task t = tasks.get(index);
                            tasks.remove(index);
                            Save();
                            System.out.println("Noted. I've removed this task:\n" + t);
                        } catch (Exception e) {
                            throw new DukeException("Please provide the index of the task you would like to remove.");
                        }
                    }
                    try {
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
                    } catch (Exception e) {
                        throw new DukeException("failed to provide task info sufficiently");
                    }
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
            printBorder();
            input = userInput.nextLine();
        }
        printBorder();
        System.out.println("Bye. Hope to see you again soon!");
        printBorder();
    }

    public static void Save() {
        try {
            FileWriter newList = new FileWriter(taskList);
            for (Task t : tasks) {
                newList.write(t.saveText());
            }
            newList.flush();
            newList.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void rewriteList(String[] s, ArrayList<Task> tasks){
        boolean isDone = s[2].equals("\u2713");
        switch (s[0]) {
            case "T":
                Todo t = new Todo(s[2], isDone);
                tasks.add(t);
                break;
            case "D":
                LocalDate by = LocalDate.parse(s[3]);
                Deadline dl = new Deadline(s[2], isDone, by);
                tasks.add(dl);
                break;
            case "E":
                LocalDate at = LocalDate.parse(s[3]);
                Event e = new Event(s[2], isDone, at);
                tasks.add(e);
                break;
        }
    }

    public static void printList(ArrayList<Task> al){
        for(int i = 0; i < al.size(); i++){
            System.out.println((i + 1) + ". " + al.get(i));
        }
    }
}