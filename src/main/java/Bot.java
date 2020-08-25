import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Bot {
    public Bot() {
    }

    String line = "    ____________________________________________________________";
    String gotIt = "     Got it. I've added this task: ";
    String whiteSpaceSeven = "       ";
    ArrayList<Listing> list = new ArrayList<Listing>();
    Parser parser = new Parser();


    public void serve() {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            try {
                String s = sc.nextLine();
                String[] arr = s.split(" ");
                String[] arr1 = parser.getDetails(s);
                if (arr1[0].equals("bye")) {
                    return;
                } else if (arr1[0].equals("list")) {
                    returnListings(s);
                } else if (arr1[0].equals("done")) {
                    Integer value = Integer.valueOf(arr1[1]);
                    doneListings(value);
                } else if (arr1[0].equals("todo")) {
                    if (arr1[1] == null) {
                        throw new NoDescriptionException("todo");
                    } else {
                        addListings(arr1);
                    }
                } else if (arr1[0].equals("deadline")) {
                    if (arr1[1] == null || arr1[2] == null) { //can be modified to do description AND time;
                        throw new NoDescriptionException("deadline");
                    } else {
                        addListings(arr1);
                    }
                } else if (arr1[0].equals("event")) {
                    if (arr1[0] == null || arr1[1] == null) {
                        throw new NoDescriptionException("event");
                    } else {
                        addListings(arr1);
                    }
                } else if (arr1[0].equals("delete")) {
                    Integer number = Integer.valueOf(arr1[1]) - 1;
                    deleteMessage(number);
                    list.remove((int) number);
                } else {
                    throw new UndefinedException();
                }

            } catch (NoDescriptionException e) {
                noDescriptionMessage(e.s);
            } catch (UndefinedException e) {
                undefinedExceptionMessage();
            }
        }
    }


    public void addListings(String[] details) {
        int size = list.size() + 1;
        String s = "";
        if (details[0].equals("todo")) {
            ToDo todo = new ToDo(details[1]);
            list.add(todo);
            s = line + "\n" + gotIt + "\n" + whiteSpaceSeven + todo.toString() +
                    "\n" + "     Now you have " + size + " tasks in the list." + "\n" + line;
            System.out.println(s);
        } else if (details[0].equals("deadline")) {
            String detail = details[1];
            String timeDetail = details[2];
            Deadline deadline = new Deadline(detail, timeDetail);
            list.add(deadline);
            s = line + "\n" + gotIt + "\n" + whiteSpaceSeven + deadline.toString() +
                    "\n" + "     Now you have " + size + " tasks in the list." + "\n" + line;
            System.out.println(s);
        } else if (details[0].equals("event")) {
            String detail = details[1];
            String timeDetail = details[2];
            Event event = new Event(detail, timeDetail);
            list.add(event);
            s = line + "\n" + gotIt + "\n" + whiteSpaceSeven + event.toString() +
                    "\n" + "     Now you have " + size + " tasks in the list." + "\n" + line;
            System.out.println(s);
        }
    }


    public void returnListings(String listing) {
        System.out.println(line);
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            String s = "     " + (i + 1) + "." + list.get(i).toString();
            System.out.println(s);
        }
        System.out.println(line);
    }

    public void doneListings(Integer value) {
        Listing item = list.get(value - 1);
        item.complete(); //completes the list
        String s = line + "\n" + "     Nice! I've marked this task as done: \n" + "     "
                + item.toString() + "\n" + line;
        System.out.println(s);
    }

    public void undefinedExceptionMessage() {
        System.out.println(line + "\n" + "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-("
                + "\n" + line);
    }

    public void noDescriptionMessage(String s) {
        System.out.println(line + "\n" + "     ☹ OOPS!!! The description of a " + s + " cannot be empty."
                + "\n" + line);
    }

    public void deleteMessage(Integer num) {
        System.out.println(line + "\n" + "     Noted. I've removed this task: \n" + whiteSpaceSeven +
                list.get(num) + "\n" + "     Now you have " + (list.size() - 1) + " tasks in the list.\n" + line);
    }

}
