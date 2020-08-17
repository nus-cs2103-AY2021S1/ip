import java.util.Scanner;

public class Duke {

    public static void greeting() {
        String greetingMsg = "\n___________________________________________________________"
                + "\n Hello! I'm DukeBT. (:"
                + "\n What can I do for you?"
                + "\n\n **Type 'bye' to exit (: "
                + "\n___________________________________________________________\n";
        System.out.println(greetingMsg);
    }

    public static void echo(String inputMsg) {
        String outputMsg = "\n___________________________________________________________"
                + "\n "+ inputMsg
                + "\n___________________________________________________________\n";

        System.out.println(outputMsg);
    }

    public static void bye() {
        String byeMsg = "\n___________________________________________________________"
                +"\n Bye. Hope to see you again soon!"
                + "\n___________________________________________________________\n";

        System.out.println(byeMsg);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        greeting();
        String inputMsg = sc.nextLine();
        while (!inputMsg.equals("bye")){
            echo(inputMsg);
            inputMsg = sc.nextLine();
        }
        bye();
    }
}
