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
                String[] parsedInfo = parser.getDetails(s);
                String command = parsedInfo[0];
                String commandDetail = parsedInfo[1];
                String dateInfo = parsedInfo[2];
                switch (command) {
                    case ("bye"):
                        break;
                    case ("list"):
                        returnListings();
                        break;
                    case ("done"):
                        doneListings(Integer.valueOf(commandDetail));
                        break;
                    case ("todo"):
                        if (commandDetail == null) {
                            throw new NoDescriptionException("todo");
                        } else {
                            addListings(parsedInfo);
                        }
                        break;
                    case ("deadline"):
                        if (commandDetail == null || dateInfo == null) { //can be modified to do description AND time;
                            throw new NoDescriptionException("deadline");
                        } else {
                            addListings(parsedInfo);
                        }
                        break;
                    case ("event"):
                        if (commandDetail == null || dateInfo == null) {
                            throw new NoDescriptionException("event");
                        } else {
                            addListings(parsedInfo);
                        }
                        break;
                    case ("delete"):
                        Integer number = Integer.valueOf(parsedInfo[1]) - 1;
                        deleteMessage(number);
                        list.remove((int) number);
                        break;
                    default:
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
        String taskInfo = details[1];
        String dateInfo = details[2];
        switch (details[0]) {
            case ("todo"):
                ToDo todo = new ToDo(taskInfo);
                list.add(todo);
                s = line + "\n" + gotIt + "\n" + whiteSpaceSeven + todo.toString() +
                        "\n" + "     Now you have " + size + " tasks in the list." + "\n" + line;
                System.out.println(s);
                break;
            case ("deadline"):
                Deadline deadline = new Deadline(taskInfo, dateInfo);
                list.add(deadline);
                s = line + "\n" + gotIt + "\n" + whiteSpaceSeven + deadline.toString() +
                        "\n" + "     Now you have " + size + " tasks in the list." + "\n" + line;
                System.out.println(s);
                break;
            case ("event"):
                Event event = new Event(taskInfo, dateInfo);
                list.add(event);
                s = line + "\n" + gotIt + "\n" + whiteSpaceSeven + event.toString() +
                        "\n" + "     Now you have " + size + " tasks in the list." + "\n" + line;
                System.out.println(s);
                break;
        }
    }


    public void returnListings() {
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
