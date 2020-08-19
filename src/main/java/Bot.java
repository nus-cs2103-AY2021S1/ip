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


    public void serve() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] arr = s.split(" ");
            String firstWord = arr[0];
            if (firstWord.equals("bye")) {
                return;
            } else if (firstWord.equals("list")) {
                returnListings(s);
            } else if (firstWord.equals("done")) {
                Integer value = Integer.valueOf(arr[1]);
                doneListings(value);
            } else if (firstWord.equals("todo")) {
                String detail = getDetails(arr);
                String[] details = new String[1];
                details[0] = detail;
                addListings("todo", details); //and type
            } else if (firstWord.equals("deadline")) {
                String[] details = getDetailsSpecial(arr, "/by");
                addListings("deadline", details);
            } else if (firstWord.equals("event")) {
                String[] details = getDetailsSpecial(arr, "/at");
                addListings("event", details);
            }

        }
    }

    public void addListings(String type, String[] details) {
        int size = list.size() + 1;
        String s = "";
        if (type.equals("todo")) {
            ToDo todo = new ToDo(details[0]);
            list.add(todo);
            s = line + "\n" + gotIt + "\n" + whiteSpaceSeven + todo.toString() +
                    "\n" + "     Now you have " + size + " tasks in the list." + "\n" + line;
            System.out.println(s);
        } else if (type.equals("deadline")) {
            String detail = details[0];
            String timeDetail = details[1];
            Deadline deadline = new Deadline(detail, timeDetail);
            list.add(deadline);
            s = line + "\n" + gotIt + "\n" + whiteSpaceSeven + deadline.toString() +
                    "\n" + "     Now you have " + size + " tasks in the list." + "\n" + line;
            System.out.println(s);
        } else if (type.equals("event")) {
            String detail = details[0];
            String timeDetail = details[1];
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
        String s = line + "\n" + "Nice! I've marked this task as done: \n" + item.doneness()
                + item.toString() + "\n" + line;
        System.out.println(s);
    }

    public String getDetails(String[] detail) {
        String s = ""; //hopefully no entry with just spaces
        for (int i = 1; i < detail.length; i++) {
            s = s + " " + detail[i];
        }
        return s;
    }

    public String[] getDetailsSpecial(String[] details, String type) {
        //check deadline or event
        String[] s = new String[2];
        String s1 = "";
        String s2 = "";
        int counter = 1;
        for (; counter < details.length; counter++) {
            if (details[counter].equals(type)) {
                s[0] = s1;
                counter++;
                break;
            }
            s1 = s1 + " " + details[counter];
        }
        for (; counter < details.length; counter++) {
            s2 = s2 + " " + details[counter];
        }
        s[1] = s2;
        return s;
    }

}
