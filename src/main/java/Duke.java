import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;

public class Duke {



    public static void main(String[] args) {

        System.out.println("Hello from Bikini Bottom!");
        System.out.println("____________________________________________________________\n"
            + "Hello! I'm Spongebob\n"
            + "What can I do for you?\n"
            + "____________________________________________________________");

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        ArrayList<Task> Tasks = new ArrayList<>();


        while (!str.equals("bye")) {
            System.out.println("____________________________________________________________\n");
            try {

                if (str.equals("list")) {
                    int counter = 1;
                    System.out.println("Here are the tasks in your list: \n");
                    for (int i = 0; i < Tasks.size(); i++) {
                        if (Tasks.get(i) != null) {
                            System.out.println(counter + ". " + Tasks.get(i));
                            counter++;
                        } else {
                            break;
                        }
                    }
                } else if (str.startsWith("delete ")) {
                    int temp = Integer.parseInt(str.substring(7));
                    Task task = Tasks.get(temp - 1);
                    Tasks.remove(temp - 1);
                    System.out.println("Noted. I've removed the task: \n"
                        + task
                        + "\nNow you have " + Tasks.size() + " tasks in the list.");

                } else if (str.startsWith("done ")) {
                    int temp = Integer.parseInt(str.substring(5));
                    Tasks.get(temp-1).doTask();
                } else if (str.startsWith("todo ")) {
                    if (str.length() <= 5) {
                        throw new DescriptionException("todo");
                    }
                    str = str.substring(5);
                    Tasks.add(new ToDo(str));

                    System.out.println("Got it. I've added this task: \n"
                        + Tasks.get(Tasks.size()-1)
                        + "\nNow you have " + Tasks.size() + " task(s) in the list.");

                } else if (str.startsWith("deadline ")) {

                    if (str.length() <= 9) {
                        throw new DescriptionException("deadline");
                    }
                    str = str.substring(9);
                    if (!str.contains(" /by ")) {
                        throw new TrackingException("deadline");
                    }
                    String[] temp = str.split(" /by ");
                    String desc = temp[0];
                    String deadlineString = temp[1];

                    if (desc.length() == 0) {
                        throw new DescriptionException("deadline");
                    }
                    if (deadlineString.length() == 0) {
                        throw new TrackingException("deadline");
                    }
                    String[] dateTime = deadlineString.split("-");
                    if (dateTime.length != 3 ||
                        dateTime[0].length() != 4 && dateTime[1].length() != 2 && dateTime[2].length() != 2) {
                        throw new DateTimeException();
                    } // check if the date and time are in the correct format.
                    LocalDate by = LocalDate.of(Integer.parseInt(dateTime[0]), Integer.parseInt(dateTime[1]),
                        Integer.parseInt(dateTime[2]));

                    Tasks.add(new Deadline(desc, by));

                    System.out.println("Got it. I've added this task: \n"
                        + Tasks.get(Tasks.size() - 1)
                        + "\nNow you have " + Tasks.size() + " task(s) in the list.");

                } else if (str.startsWith("event ")) {
                    if (str.length() <= 6) {
                        throw new DescriptionException("event");
                    }
                    str = str.substring(6);
                    if (!str.contains(" /at ")) {
                        throw new TrackingException("event");
                    }
                    String[] temp = str.split(" /at ");
                    String desc = temp[0];
                    String atString = temp[1];

                    if (desc.length() == 0) {
                        throw new DescriptionException("event");
                    }
                    if (atString.length() == 0) {
                        throw new TrackingException("event");
                    }

                    String[] dateTime = atString.split("-");
                    if (dateTime.length != 3 ||
                        dateTime[0].length() != 4 && dateTime[1].length() != 2 && dateTime[2].length() != 2 ) {
                        throw new DateTimeException();
                    } // check if the date and time are in the correct format.
                    LocalDate at = LocalDate.of(Integer.parseInt(dateTime[0]), Integer.parseInt(dateTime[1]),
                        Integer.parseInt(dateTime[2]));
                    Tasks.add(new Events(desc, at));

                    System.out.println("Got it. I've added this task: \n"
                        + Tasks.get(Tasks.size() - 1)
                        + "\nNow you have " + Tasks.size() + " task(s) in the list.");

                } else if (str.equals("event") || str.equals("deadline") || str.equals("todo") ||
                str.equals("done")) {
                    throw new DescriptionException(str);
                } else {
                    throw new CommandException(str);
                }
            } catch (DukeException ex) {
                System.out.println(ex);
            }

            System.out.println("____________________________________________________________\n");

            str = sc.nextLine();

        }

        System.out.println("____________________________________________________________\n"
            + "Bye. Hope to see you again soon! Bahahahaha!\n"
            + "____________________________________________________________\n");



    }
}
