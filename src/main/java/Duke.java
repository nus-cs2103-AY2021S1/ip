import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) throws DukeException {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        String divider = "    _________________________________________\n";
        int listIndex = 1;
        String MrCamel = "                  ,,__\n"
                + "        ..  ..   / o._)\n"
                + "       /--'/--\\  \\-'|| \n"
                + "      /        \\_/ / |\n"
                + "    .'\\  \\__\\  __.'.'\n"
                + "      )\\ |  )\\ |\n"
                + "     // \\\\ // \\\\\n"
                + "    ||_  \\\\|_  \\\\_\n"
                + "    '--' '--'' '--'\n";

        System.out.println("Mr Camel says hello. What can Mr Camel do for you today?\n" + MrCamel);

        while (sc.hasNext()) {
            String command = sc.nextLine();
            if (command.equals("list")) {
                System.out.println(divider);
                System.out.println("      Tasks:");

                for (int i = 1; i < list.size() + 1; i++) {
                    System.out.println("      " + i + ". " + list.get(i-1));
                }
                System.out.println(divider);

            } else if (command.contains("deadline")) {
                try {
                    int indexOfDate = command.indexOf("/");
                    String taskDescription = command.substring(9, indexOfDate - 1);
                    String by = command.substring(indexOfDate + 3);
                    Deadline deadline = new Deadline(taskDescription, by);
                    list.add(deadline);

                    System.out.println(divider);
                    System.out.println("      Mr Camel has added: " + deadline);
                    System.out.println("      Number of tasks: " + list.size());
                    System.out.println(divider);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Please provide both a description and a deadline!");
                }

            } else if (command.contains("todo")) {
                try {
                    String taskDescription = command.substring(5);
                    Todo todo = new Todo(taskDescription);
                    list.add(todo);

                    System.out.println(divider);
                    System.out.println("      Mr Camel has added: " + todo);
                    System.out.println("      Number of tasks: " + list.size());
                    System.out.println(divider);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("Please provide a description!");
                }

            } else if (command.contains("event")) {
                try {
                    int indexOfDate = command.indexOf("/");
                    String taskDescription = command.substring(6, indexOfDate - 1);
                    String at = command.substring(indexOfDate + 3);
                    Event event = new Event(taskDescription, at);
                    list.add(event);

                    System.out.println(divider);
                    System.out.println("      Mr Camel has added: " + event);
                    System.out.println("      Number of tasks: " + list.size());
                    System.out.println(divider);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Please provide both a description and date!");
                }

            } else if (command.contains("done")) {
                try {
                    int indexDone = Integer.parseInt(command.substring(5));
                    list.set(indexDone - 1, list.get(indexDone - 1).markAsDone());

                    System.out.println(divider);
                    System.out.println("      Mr Camel will mark this task as done:\n" + "        " + list.get(indexDone - 1));
                    System.out.println(divider);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Invalid task number!");
                } catch (NullPointerException e) {
                    throw new DukeException("Invalid task number!");
                }

            } else if (command.contains("delete")) {
                try {
                    int indexDone = Integer.parseInt(command.substring(7));

                    System.out.println(divider);
                    System.out.println("      Mr Camel will delete this task:\n" + "        " + list.get(indexDone - 1));
                    list.remove(indexDone - 1);
                    System.out.println("      Number of tasks: " + list.size());
                    System.out.println(divider);

                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Invalid task number!");
                } catch (NullPointerException e) {
                    throw new DukeException("Invalid task number!");
                }

            } else if (command.equals("bye")) {
                System.out.println(divider);
                System.out.println("      Bye. Mr Camel will miss you! :(");
                System.out.println(divider);
                break;
            } else { //invalid input

                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
