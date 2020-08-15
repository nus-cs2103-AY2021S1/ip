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
                bye();
                break;
            }

            if (input.equals("list")) {
                showList();
            } else {
                add(input);
            }


        }
    }

    void showList() {
        System.out.println(ConsoleColors.YELLOW.getColor()
            + this.kai
            + "Here are your tasks!"
            + ConsoleColors.RESET.getColor());
        for (int i = 0; i < toDoList.size(); i++) {
            System.out.println(ConsoleColors.BLUE_BOLD.getColor()
                + (i+1) + ". "
                + ConsoleColors.BLUE.getColor() + "\t"
                + toDoList.get(i)
                + ConsoleColors.RESET.getColor());
        }
        System.out.println();
    }

    void add(String item) {
        this.toDoList.add(item);
        System.out.println(ConsoleColors.YELLOW.getColor()
                + this.kai
                + "[" + item + "] has been added to your list! \n"
                + ConsoleColors.RESET.getColor());
    }

    void bye() {
        System.out.println(ConsoleColors.YELLOW.getColor()
                + this.kai
                + "Sayonara! See you again my friend!"
                + ConsoleColors.RESET.getColor());
    }
}
