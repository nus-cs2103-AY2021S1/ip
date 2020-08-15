import java.util.Scanner;

class Interactor {
    static String user = "You: ";
    static String kai = "Kai: ";

    static void welcome() {

         String logo = "#    #   ##   # ###### ###### #    #\n"
         + "#   #   #  #  #     #  #      ##   #\n"
         + "####   #    # #    #   #####  # #  #\n"
         + "#  #   ###### #   #    #      #  # #\n"
         + "#   #  #    # #  #     #      #   ##\n"
         + "#    # #    # # ###### ###### #    #\n";

         String welcomeMessage = "Konichiwa! Welcome to Kaizen \n"
                 + "I am Kai, what can I do for you today?\n";

        System.out.println(logo + "\n"
                + ConsoleColors.YELLOW.getColor() + welcomeMessage + ConsoleColors.RESET.getColor());
    }

    static void getInput() {
        Scanner sc = new Scanner(System.in);


        while (true) {
            System.out.print(user);
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(ConsoleColors.YELLOW.getColor()
                        + kai
                        + "Sayonara! See you again my friend!"
                        + ConsoleColors.RESET.getColor());
                break;
            }

            System.out.println(ConsoleColors.YELLOW.getColor()
                    + kai
                    + input
                    + ConsoleColors.RESET.getColor()
                    + "\n");
        }

    }



}
