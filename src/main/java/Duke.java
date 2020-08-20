import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Greet
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        // Creates list
        ArrayList<String> list = new ArrayList<>();

        // Echo
        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")){
                // Stops taking inputs
                break;
            } else if( input.equals("list") ) {
                // returns list of items
                for (int i = 0; i<list.size(); i++){
                    System.out.println( (1 + i) + ". " + list.get(i));
                }
            } else {
                // Adds item to list
                list.add(input);
                System.out.println("added: " + input);
            }
        }

        // Exit
        System.out.println("Bye. Hope to see you again soon!");

        sc.close();
    }

}
