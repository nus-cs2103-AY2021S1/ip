import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = "\n" +
                "\t███████╗ ██████╗  ██████╗██╗   ██╗███████╗   \n" +
                "\t██╔════╝██╔═══██╗██╔════╝██║   ██║██╔════╝   \n" +
                "\t█████╗  ██║   ██║██║     ██║   ██║███████╗   \n" +
                "\t██╔══╝  ██║   ██║██║     ██║   ██║╚════██║   \n" +
                "\t██║     ╚██████╔╝╚██████╗╚██████╔╝███████║██╗\n" +
                "\t╚═╝      ╚═════╝  ╚═════╝ ╚═════╝ ╚══════╝╚═╝\n";
        System.out.println("Welcome to " + logo);

        String divider = "\t_________________________________________\n";
        String greetings = divider +
                "\tI am Pocus, your personal assistant!" +
                "\n\tMay I know your name?\n" + divider;
        System.out.print(greetings);

        String name = sc.nextLine();
        String enquiry = divider +
                "\tHi there, " + name + "!" +
                "\n\tHow can I help you today?\n" + divider;
        System.out.print(enquiry);

        ArrayList<String> tasks = new ArrayList<>();
        String input = "";
        while (sc.hasNext()) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                String exit = "\tHopefully I helped you today. Bye! :)\n";
                System.out.print(divider + exit + divider);
                break;
            } else if (input.equals("list")) {
                System.out.print(divider);
                for (int i = 0; i < tasks.size(); i++) {
                    int number = i + 1;
                    System.out.print("\t" + number + ": " + tasks.get(i) + "\n");
                }
                System.out.print(divider);
            } else {
                tasks.add(input);
                System.out.print(divider + "\t added: " + input + "\n" + divider);
            }
        }
        sc.close();
    }
}