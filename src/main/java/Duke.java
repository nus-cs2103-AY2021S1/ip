import java.util.Scanner;

public class Duke {

    static String line = "—————————————————————————————————————————————————————————————————";

    public static void greeting() {
        String str = ("\t" + line + "\n"
                + "\tHello! I'm Duke\n"
                + "\tWhat can I do for you?\n"
                + "\t" + line);
        System.out.println(str);
    }

    public static void repeat(String input){
        if (input.equals("bye")) {
            System.out.println("\t" + line + "\n"
                    + "\tBye. Hope to see you again soon!\n"
                    + "\t" + line);
        } else {
            System.out.println( "\t" + line + "\n"
                    + "\t" + input + "\n"
                    + "\t" + line);
        }
    }

    public static void main(String[] args) {
        greeting();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                repeat(input);
                sc.close();
                break;
            } else {
                repeat(input);
            }
        }
    }

}
