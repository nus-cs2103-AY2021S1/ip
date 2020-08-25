import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Bot {
    public Bot() {
    }

    String LINE = "    ____________________________________________________________";
    String GOT_IT = "     Got it. I've added this task: ";
    String WHITE_SPACE_SEVEN = "       ";
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
                        if (commandDetail == null || dateInfo == null) {
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
                s = LINE + "\n" + GOT_IT + "\n" + WHITE_SPACE_SEVEN + todo.toString() +
                        "\n" + "     Now you have " + size + " tasks in the list." + "\n" + LINE;
                System.out.println(s);
                break;
            case ("deadline"):
                Deadline deadline = new Deadline(taskInfo, dateInfo);
                list.add(deadline);
                s = LINE + "\n" + GOT_IT + "\n" + WHITE_SPACE_SEVEN + deadline.toString() +
                        "\n" + "     Now you have " + size + " tasks in the list." + "\n" + LINE;
                System.out.println(s);
                break;
            case ("event"):
                Event event = new Event(taskInfo, dateInfo);
                list.add(event);
                s = LINE + "\n" + GOT_IT + "\n" + WHITE_SPACE_SEVEN + event.toString() +
                        "\n" + "     Now you have " + size + " tasks in the list." + "\n" + LINE;
                System.out.println(s);
                break;
        }
    }


    public void returnListings() {
        System.out.println(LINE);
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            String s = "     " + (i + 1) + "." + list.get(i).toString();
            System.out.println(s);
        }
        System.out.println(LINE);
    }

    public void doneListings(Integer value) {
        Listing item = list.get(value - 1);
        item.complete(); //completes the list
        String s = LINE + "\n" + "     Nice! I've marked this task as done: \n" + "     "
                + item.toString() + "\n" + LINE;
        System.out.println(s);
    }

    public void undefinedExceptionMessage() {
        System.out.println(LINE + "\n" + "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-("
                + "\n" + LINE);
    }

    public void noDescriptionMessage(String s) {
        System.out.println(LINE + "\n" + "     ☹ OOPS!!! The description of a " + s + " cannot be empty."
                + "\n" + LINE);
    }

    public void deleteMessage(Integer num) {
        System.out.println(LINE + "\n" + "     Noted. I've removed this task: \n" + WHITE_SPACE_SEVEN +
                list.get(num) + "\n" + "     Now you have " + (list.size() - 1) + " tasks in the list.\n" + LINE);
    }

}
