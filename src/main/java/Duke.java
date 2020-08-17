import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Yooo, I'm Deke.\nWhat can I do for you today?"); //Greeting

        ArrayList<String> list = new ArrayList<>();
        String input = sc.nextLine();

        while (!input.isEmpty()) {
            if (input.equals("bye")) { //if user types "bye"
                System.out.println("Bye bye!!! See you again next time :)");
                input = "";
                sc.close();
            } else if (input.equals("list")) { //if user types "list"
                //do a for loop to print out the list
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i + 1 + ". " + list.get(i));
                }
                input = sc.nextLine();
            } else { //if user types anything other than "bye" or "list"
                list.add(input);
                System.out.println("added: " + input);
                input = sc.nextLine();
            }
        }
    }
}
