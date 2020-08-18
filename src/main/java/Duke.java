import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String tab = "  ";
        String lineBreaker = tab + "___________________________________________________________";
        String greet = tab + "Hello! I'm Duke" + "\n" + tab + "What can I do for you?";
        String end = tab + "Bye. Hope to see you again soon!";
        Scanner sc = new Scanner(System.in);
        ArrayList<String> listOfStrings = new ArrayList<>();

        System.out.println(lineBreaker);
        System.out.println(greet);
        System.out.println(lineBreaker);

        while(sc.hasNext()) {
            String input = sc.next();
            System.out.println(lineBreaker);
            if(input.equals("bye")) {
                System.out.println(end);
                System.out.println(lineBreaker);
                break;
            } else if(input.equals("list")) {
                int i = 1;
                for(String item : listOfStrings) {
                    System.out.println(tab + i++ + ". " + item);
                }
                System.out.println(lineBreaker);
            } else {
                listOfStrings.add(input);
                System.out.println(tab + "added: " + input);
                System.out.println(lineBreaker);
            }
        }
    }
}
