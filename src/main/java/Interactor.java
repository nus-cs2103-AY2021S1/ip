import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class Interactor {
    String logo;
    String user;
    String kai;
    List<String> toDoList;

    Interactor() {
        logo = logo = "#    #   ##   # ###### ###### #    #\n"
                + "#   #   #  #  #     #  #      ##   #\n"
                + "####   #    # #    #   #####  # #  #\n"
                + "#  #   ###### #   #    #      #  # #\n"
                + "#   #  #    # #  #     #      #   ##\n"
                + "#    # #    # # ###### ###### #    #\n";
        user = "You: ";
        kai = "Kai: ";
        toDoList = new ArrayList<>();
    }

    void welcome() {
         String welcomeMessage = "Konichiwa! Welcome to Kaizen \n"
                 + "I am Kai, what can I do for you today?\n";

        System.out.println(this.logo + "\n"
                + ConsoleColors.YELLOW.getColor() + welcomeMessage + ConsoleColors.RESET.getColor());
    }

    void getInput() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print(this.user);
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(ConsoleColors.YELLOW.getColor()
                        + kai
                        + "Sayonara! See you again my friend!"
                        + ConsoleColors.RESET.getColor());
                break;
            }
//            System.out.println(ConsoleColors.YELLOW.getColor()
//                    + kai
//                    + input
//                    + ConsoleColors.RESET.getColor()
//                    + "\n");
        }
    }
}
