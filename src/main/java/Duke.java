import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String message = "____________________________________________________________\n" +
                "  Hello! I'm Duke\n" +
                "  What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(message);

        List<Task> lists = new ArrayList<Task>();

        File path = new File("data");
        if (path.exists() && path.isDirectory()) {
            File f = new File("data/duke.txt");
            try {
                Scanner s = new Scanner(f);
                readTask(f, lists);
                while (s.hasNext()) {
                    System.out.println(s.nextLine());
                }
            } catch (FileNotFoundException e) {
                new File("data").mkdir();
                System.out.println("File not found. Created a file.");
            }
        } else {
            new File("data").mkdir();
        }

        Scanner scanner = new Scanner(System.in);
        String string1 = scanner.nextLine();
        String message1;



        while (!string1.equals("bye")) {
            try {
                if (string1.equals("list")) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < lists.size(); i++) {
                        Task task = lists.get(i);
                        System.out.println((i + 1) + "." + task.toString());
                    }
                    System.out.println("____________________________________________________________\n");
                } else if (string1.contains("done ")) {
                    int order = parseInt(string1.substring(string1.length() - 1));
                    Task task = lists.get(order - 1);
                    task.markAsDone();
                    System.out.println("____________________________________________________________\n" +
                            "  Nice! I've marked this task as done:");
                    System.out.println("    [" + task.getStatusIcon() + "] " + task.getDescription());
                    System.out.println("____________________________________________________________\n");
                } else if (string1.contains("delete")) {
                    int order = parseInt(string1.substring(string1.length() - 1));
                    Task task = lists.get(order - 1);
                    lists.remove(order - 1);
                    System.out.println("____________________________________________________________\n" +
                            "Noted. I've removed this task:");
                    System.out.println("  " + task.toString());
                    System.out.println("Now you have " + lists.size() + " tasks in the list.\n" +
                            "____________________________________________________________");
                } else {
                    String type;
                    if (string1.contains(" ")) {
                        type = string1.substring(0, string1.indexOf(' '));
                    } else {
                        String str = "";
                        switch (string1) {
                            case "todo":
                                str = "☹ OOPS!!! The description of a todo cannot be empty.";
                                break;
                            case "deadline":
                                str = "☹ OOPS!!! The description of a deadline cannot be empty.";
                                break;
                            case "event":
                                str = "☹ OOPS!!! The description of an event cannot be empty.";
                                break;
                            default:
                                str = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
                        }
                        throw new DukeException(str);
                    }

                    if (type.equals("deadline")) {
                        String description = string1.substring(string1.indexOf(' ') + 1, string1.indexOf('/') - 1);
                        String by = string1.substring(string1.indexOf("/by") + 4);

                        String s = "";

                        if (by.charAt(0) >= '0' && by.charAt(0) <= '9') {
                            if (by.charAt(1) == '/') {
                                s = s + by.substring(5, 9);
                                s = s + '-';
                                s = s + by.substring(2, 4);
                                s = s + '-';
                                s = s + '0' + by.substring(0,1);
                            } else {
                                s = s + by.substring(6, 10);
                                s = s + '-';
                                s = s + by.substring(3, 5);
                                s = s + '-';
                                s = s + '0' + by.substring(0, 2);
                            }
                            LocalDate date = LocalDate.parse(s);
                            lists.add(new Deadline(description, by, date));
                        } else {
                            lists.add(new Deadline(description, by));
                        }

                    } else if (type.equals("event")) {
                        String time = string1.substring(string1.indexOf("/at") + 4);
                        String description = string1.substring(string1.indexOf(' ') + 1, string1.indexOf('/') - 1);

                        String s = "";

                        if (time.charAt(0) >= '0' && time.charAt(0) <= '9') {
                            if (time.charAt(1) == '/') {
                                s = s + time.substring(5, 9);
                                s = s + '-';
                                s = s + time.substring(2, 4);
                                s = s + '-';
                                s = s + '0' + time.substring(0,1);
                            } else {
                                s = s + time.substring(6, 10);
                                s = s + '-';
                                s = s + time.substring(3, 5);
                                s = s + '-';
                                s = s + '0' + time.substring(0, 2);
                            }
                            LocalDate date = LocalDate.parse(s);
                            lists.add(new Event(description, time, date));
                        } else {
                            lists.add(new Event(description, time));
                        }
                    } else if (type.equals("todo")) {
                        String description = string1.substring(string1.indexOf(' ') + 1);
                        lists.add(new ToDo(description));
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }

                    message1 = "____________________________________________________________\n" +
                            "Got it. I've added this task:\n  "
                            + lists.get(lists.size() - 1).toString() + "\n" +
                            "Now you have " + lists.size() + " tasks in the list.\n" +
                            "____________________________________________________________\n";
                    System.out.println(message1);
                }
            } catch (DukeException e) {
                System.out.println("____________________________________________________________\n");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________\n");
            }

            try {
                writeToFile(lists);
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }

            string1 = scanner.nextLine();
        }

        message1 = "____________________________________________________________\n" +
                "  Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";
        System.out.println(message1);
    }

    public static void writeToFile(List<Task> lists) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        String textToAdd = "";

        for (int i = 0; i < lists.size(); i++) {
            Task task = lists.get(i);
            textToAdd = textToAdd + task.toString() + System.lineSeparator();
        }

        fw.write(textToAdd);
        fw.close();
    }

    public static void readTask(File f, List<Task> lists) {
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String string = s.nextLine();
                if (string.charAt(1) == 'T') {
                    String description = string.substring(string.indexOf(' ') + 1);
                    if (string.charAt(4) == '✓') {
                        lists.add(new ToDo(description, true));
                    } else {
                        lists.add(new ToDo(description));
                    }
                } else if (string.charAt(1) == 'D') {
                    String description = string.substring(string.indexOf(' ') + 1, string.indexOf('('));
                    String by = string.substring(string.indexOf("(by") + 5);
                    if (string.charAt(4) == '✓') {
                        lists.add(new Deadline(description, by, true));
                    } else {
                        lists.add(new Deadline(description, by));
                    }
                } else {
                    String time = string.substring(string.indexOf("(at") + 5);
                    String description = string.substring(string.indexOf(' ') + 1, string.indexOf('('));
                    if (string.charAt(4) == '✓') {
                        lists.add(new Event(description, time, true));
                    } else {
                        lists.add(new Event(description, time));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}
