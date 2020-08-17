import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        boolean on = true;
        String line = "    ____________________________________________________________";
        Scanner sc = new Scanner(System.in);
        System.out.println(line);
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        System.out.println(line);
        ArrayList arr =  new ArrayList<String>();

        while(on) {
            String input = sc.nextLine();
            if(input.compareTo("bye") == 0) {
                on = false;
                System.out.println(line);
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println(line);
            } else if (input.compareTo("list") == 0){
                System.out.println(line);
                for(int i = 0; i < arr.size(); i++) {
                    System.out.println(String.format("     %d. %s", i + 1, arr.get(i)));
                }
                System.out.println(line);
            } else {
                arr.add(input);
                System.out.println(line);
                System.out.println(String.format("     added: %s", input));
                System.out.println(line);
            }
        }
    }
}
