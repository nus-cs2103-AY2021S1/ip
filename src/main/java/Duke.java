import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo =
                "     ____        _        \n"
                        + "    |  _ \\ _   _| | _____ \n"
                        + "    | | | | | | | |/ / _ \\\n"
                        + "    | |_| | |_| |   <  __/\n"
                        + "    |____/ \\__,_|_|\\_\\___|";


        System.out.println(logo);

        displayThis("Hello! I'm Duke\n    What can I do for you?");

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        ArrayList<String> myList = new ArrayList<String>();

        while (!input.equals("bye")) {

            if (input.equals("list")) {

                if (myList.size() == 0) { // checks if the user types list to a empty list
                    displayThis("List is empty");
                }else {

                    int temp = 0;
                    System.out.println("\n    ---------------------------------");
                    for (int i = 0; i < myList.size() && myList.get(i) != null; i++) {

                        temp = i + 1;
                        System.out.println("    " + temp + ". " + myList.get(i));

                    }
                    System.out.println("    ---------------------------------");
                }

            } else if(!input.trim().equals("")){
                displayThis("added: " + input);
                myList.add(input);
            }


            input = scanner.nextLine();
        }

        displayThis("Bye. Hope to see you again soon!");

    }

    private static void displayThis(String s) {
        System.out.println("\n    ---------------------------------");

        System.out.println("    " + s);

        System.out.println("    ---------------------------------\n");

    }

}