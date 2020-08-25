import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void print(ArrayList<Task> arr, Task t) {
        String keyword = "";
        String toPrint = "";
        if (t instanceof Deadline) {
            keyword = "by";
            toPrint = " (" + keyword + ": " + t.time + ")";
        } else if (t instanceof Event) {
            keyword = "at";
            toPrint = " (" + keyword + ": " + t.time + ")";
        }
        System.out.println("Got it. I've added this task:" + "\n" +
                t.getIndicator() + t.getIcon() + t.name + toPrint + "\n" +
                "Now you have " + arr.size() + " tasks in the list."
                );
    }

    public static void main(String[] args) throws Exception {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n" + "I can be your friend who manages your task!");
        FileCreator fc = new FileCreator("/Users/juanlie/cs2103t/pw2/ip/data");
        if (!fc.exists()) {
            fc.create();
        }
        boolean exited = false;
        ArrayList<Task> arr = fc.toArrayList();
        try {
            while (!exited) {
                Scanner sc = new Scanner(System.in);
                String command = sc.nextLine();
                int indexOfSlash = command.indexOf('/');

                if (command.equals("bye")) {
                    System.out.println("Bye. Please come back soon :(");
                    exited = true;
                } else if (command.length() >= 4) {
                    if (command.substring(0, 4).equals("list")) {
                        if (arr.size() == 0) {
                            System.out.println("You currently do not have any todos.");
                        } else {
                            System.out.println("Here are the tasks in the list: ");
                            fc.printList();
                        }
                    } else if (command.substring(0, 4).equals("done")) {
                        if (command.length() == 4) {
                            System.out.println("Provide a number of the todo that you want to mark as done!");
                        } else {
                            int taskNumber = Integer.parseInt(command.substring(5));
                            if (taskNumber > fc.lineCounter()) {
                                System.out.println("The specified todo does not exist!");
                            } else {
                                Task t = arr.get(taskNumber - 1);
                                t.taskIsDone();
                                System.out.println("Nice! Duke has marked this task as done: " + "\n");
                                print(arr, t);
                            }
                            fc.listWriter(arr);
                        }
                    } else if (command.substring(0, 4).equals("todo")) {
                        if (command.length() == 4 || indexOfSlash != -1) {
                            TodoException te = new TodoException();
                            System.out.println(te.errorMessage);
                        } else {
                            Todo t = new Todo(command.substring(5));
                            arr.add(t);
                            print(arr, t);
                            fc.listWriter(arr);
                        }
                    } else if (command.substring(0, 5).equals("event")) {
                        if (indexOfSlash == -1 || command.length() == 5 || !command.substring(indexOfSlash + 1, indexOfSlash + 3).equals("at")) {
                            EventException ee = new EventException();
                            System.out.println(ee.errorMessage);
                        } else {
                            String time = command.substring(indexOfSlash + 4);
                            LocalDate parsed = LocalDate.parse(time);
                            Event e = new Event(command.substring(6, indexOfSlash - 1), parsed.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
                            arr.add(e);
                            print(arr, e);
                            fc.listWriter(arr);
                        }

                    } else if (command.substring(0, 6).equals("delete")) {
                        if (command.length() == 6) {
                            System.out.println("Provide the number of the todo that you want to delete!");
                        } else {
                            int taskNumber = Integer.parseInt(command.substring(7));
                            if (taskNumber > arr.size()) {
                                System.out.println("The specified todo does not exist!");
                            } else {
                                Task t = arr.get(taskNumber - 1);
                                arr.remove(t);
                                System.out.println("Nice! Duke has removed this task: " + "\n");
                                print(arr, t);
                                fc.listWriter(arr);
                            }
                        }
                    } else if (command.substring(0, 8).equals("deadline")) {
                        if (indexOfSlash == -1 || command.length() == 8 || !command.substring(indexOfSlash + 1, indexOfSlash + 3).equals("by")) {
                            DeadlineException de = new DeadlineException();
                            System.out.println(de.errorMessage);
                        } else {
                            String time = command.substring(indexOfSlash + 4);
                            LocalDate parsed = LocalDate.parse(time);
                            Deadline d = new Deadline(command.substring(9, indexOfSlash - 1), parsed.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
                            arr.add(d);
                            print(arr, d);
                            fc.listWriter(arr);
                        }

                    } else {
                        System.out.println("Wrong Command" + "\n");
                    }
                } else {
                    System.out.println("Wrong Command" + "\n");
                }
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
