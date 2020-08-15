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
                System.out.println("      To do:");
                for (int i = 1; i < listIndex; i++) {
                    System.out.println("      " + i + ". " + list[i]);
                }
                System.out.println(divider);
            } else if (command.contains("done")) {
                String[] temp = command.split(" ");
                int indexDone = Integer.parseInt(temp[1]);
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
