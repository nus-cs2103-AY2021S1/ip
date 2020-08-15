import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] list = new Task[101];
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
                for (int i = 1; i < listIndex; i++) {
                    System.out.println("      " + i + ". " + list[i]);
                }
                System.out.println(divider);

            } else if (command.contains("deadline")) {
                int indexOfDate = command.indexOf("/");
                String taskDescription = command.substring(9, indexOfDate - 1);
                String by = command.substring(indexOfDate + 3);
                Deadline deadline = new Deadline(taskDescription, by);
                list[listIndex] = deadline;
                listIndex++;
                System.out.println(divider);
                System.out.println("      Mr Camel has added: " + deadline);
                System.out.println("      Number of tasks: " + (listIndex - 1));
                System.out.println(divider);

            } else if (command.contains("todo")) {
                String taskDescription = command.substring(5);
                Todo todo = new Todo(taskDescription);
                list[listIndex] = todo;
                listIndex++;
                System.out.println(divider);
                System.out.println("      Mr Camel has added: " + todo);
                System.out.println("      Number of tasks: " + (listIndex - 1));
                System.out.println(divider);

            } else if (command.contains("event")) {
                int indexOfDate = command.indexOf("/");
                String taskDescription = command.substring(6, indexOfDate - 1);
                String at = command.substring(indexOfDate + 3);
                Event event = new Event(taskDescription, at);
                list[listIndex] = event;
                listIndex++;
                System.out.println(divider);
                System.out.println("      Mr Camel has added: " + event);
                System.out.println("      Number of tasks: " + (listIndex - 1));
                System.out.println(divider);

            } else if (command.contains("done")) {
                int indexDone = Integer.parseInt(command.substring(5));
                list[indexDone].markAsDone();
                System.out.println(divider);
                System.out.println("      Mr Camel will mark this task as done:\n" + "        " + list[indexDone]);
                System.out.println(divider);

            } else if (command.equals("bye")) {
                System.out.println(divider);
                System.out.println("      Bye. Mr Camel will miss you! :(");
                System.out.println(divider);
                break;
            } else {
                list[listIndex] = new Task(command);
                listIndex++;
                System.out.println(divider);
                System.out.println("      Mr Camel has added: " + command);
                System.out.println(divider);
            }
        }
    }
}
