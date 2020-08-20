import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Yoo ( ﾟ▽ﾟ)/ \nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<String> al = new ArrayList<>();

        String input = sc.nextLine();
        while(!input.equals("bye")) {

            if(input.equals("list")) {
                System.out.println("Here's your list!");
                for(int i = 1; i <= al.size(); i ++) {
                    System.out.println(i + ". " + al.get(i - 1));
                }
            } else {
                System.out.println("I've added [" + input + "] !");
                al.add(input);
            }
            input = sc.nextLine();
        }
        System.out.println("Bye! Come back soon ( ･ω･)ﾉ");
        sc.close();
    }
}