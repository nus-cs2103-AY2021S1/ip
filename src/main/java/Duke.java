import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Duke {

    public static String line = "===================================================";
    public static String path = "duke.txt";

    public static void main(String[] args) {
        try {
            Scanner s = new Scanner(System.in);
            int location;
            String description;
            String date;
            LocalDate time;
            String type;
            ArrayList<Task> tasks = new ArrayList<>();
            int number = 0;
            boolean isChanged = false;
            File file = new File(path);

            if (!file.createNewFile()) {
                Scanner reader = new Scanner(file);
                while(reader.hasNextLine()) {
                    String line = reader.nextLine();
                    Task task;
                    number++;

                    switch (line.charAt(0)) {
                        case 'T':
                            task = new Todo(line.substring(4));
                            if(line.charAt(2) == '1') {
                                task.markAsDone();
                            }
                            tasks.add(task);
                            break;

                        case 'D':
                            location = line.indexOf("/");
                            date = line.substring(location + 1);
                            time = LocalDate.parse(date);
                            description = line.substring(4, location);
                            task = new Deadline(description, time);
                            if(line.charAt(2) == '1') {
                                task.markAsDone();
                            }
                            tasks.add(task);
                            break;

                        case 'E':
                            location = line.indexOf("/");
                            date = line.substring(location + 1);
                            time = LocalDate.parse(date);
                            description = line.substring(4, location);
                            task = new Event(description, time);
                            if(line.charAt(2) == '1') {
                                task.markAsDone();
                            }
                            tasks.add(task);
                    }
                }
            }

            printPart("Hello! I'm Duke\n" + "What can I do for you?");
            type = s.next();

            while(!type.equals("bye")) {
                try {
                    switch (type) {
                        case "list":
                            System.out.println(line);
                            System.out.println("Here are the tasks in your list:");
                            for (int i = 0; i < number; i++) {
                                System.out.println(String.format("  %d. ", i + 1) + tasks.get(i).toString());
                            }
                            System.out.println(line + "\n");
                            break;

                        case "todo":
                            description = s.nextLine();
                            if (!(description.length() > 0)) {
                                throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
                            } else {
                                tasks.add(new Todo(description.substring(1)));
                                printPart("Got it. I've added this task:\n"
                                        + "  " + tasks.get(number).toString()
                                        + "\nNow you have " + (number + 1) + " tasks in the list.");
                                number++;
                                isChanged = true;
                            }
                            break;

                        case "deadline":
                            description = s.nextLine();
                            if (!(description.length() > 0)) {
                                throw new DukeException("\u2639 OOPS!!! The description of a deadline cannot be empty.");
                            } else {
                                location = description.indexOf("/");
                                date = description.substring(location + 4);
                                time = LocalDate.parse(date);
                                description = description.substring(1, location - 1);
                                tasks.add(new Deadline(description, time));
                                printPart("Got it. I've added this task:\n"
                                        + "  " + tasks.get(number).toString()
                                        + "\nNow you have " + (number + 1) + " tasks in the list.");
                                number++;
                                isChanged = true;
                            }
                            break;

                        case "event":
                            description = s.nextLine();
                            if (!(description.length() > 0)) {
                                throw new DukeException("\u2639 OOPS!!! The description of a event cannot be empty.");
                            } else {
                                location = description.indexOf("/");
                                date = description.substring(location + 4);
                                time = LocalDate.parse(date);
                                description = description.substring(1, location - 1);
                                tasks.add(new Event(description, time));
                                printPart("Got it. I've added this task:\n"
                                        + "  " + tasks.get(number).toString()
                                        + "\nNow you have " + (number + 1) + " tasks in the list.");
                                number++;
                                isChanged = true;
                            }
                            break;

                        case "done":
                            description = s.nextLine();
                            if (!(description.length() > 0)) {
                                throw new DukeException("\u2639 OOPS!!! The number to be marked done cannot be empty.");
                            } else {
                                int n = Integer.parseInt(description.substring(1));
                                tasks.get(n - 1).markAsDone();
                                printPart("Nice! I've marked this task as done:\n" + "  " + tasks.get(n - 1).toString());
                                isChanged = true;
                            }
                            break;


                        case "delete":
                            description = s.nextLine();
                            if (!(description.length() > 0)) {
                                throw new DukeException("\u2639 OOPS!!! The number to be deleted cannot be empty.");
                            } else {
                                int selected = Integer.parseInt(description.substring(1));
                                printPart("Noted. I've removed this task:\n"
                                        + "  " + tasks.get(selected - 1).toString()
                                        + "\nNow you have " + (number - 1) + " tasks in the list.");
                                tasks.remove(selected - 1);
                                number--;
                                isChanged = true;
                            }
                            break;

                        default:
                            throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } catch (DukeException e) {
                    printPart(e.getMessage());
                }

                type = s.next();
            }

            if(isChanged) {
                PrintWriter pw = new PrintWriter(path);
                pw.close();
                PrintWriter writer = new PrintWriter(path);
                for(int i = 0; i < number; i++) {
                    writer.write(tasks.get(i).toSave() + "\n");
                }
                writer.close();
            }

            printPart("Bye. Hope to see you again soon!");
        } catch (IOException e) {
            System.out.println("Some error occurred.");
            e.printStackTrace();
        }
    }

    public static void printPart(String str) {
        System.out.println(line);
        System.out.println(str);
        System.out.println(line + "\n");
    }
}
