import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Duke dk = new Duke();
        dk.greet();

        Scanner sc = new Scanner( System.in );
        while (sc.hasNext()) {
            String input = sc.next();
            if (input.equals("bye")) {
                dk.end_conversation();
            } else {
                dk.echo(input);
            }
        }
    }

    public void greet(){
        System.out.println("Hello! I am Duke Chan.\nWhat can I do for you? ᕕ( ᐛ )ᕗ");
    }

    public void echo(String input){
        System.out.println(input);
    }

    public void end_conversation(){
        System.out.println("Bye. Hope to see you again soon! ∠( ᐛ 」∠)＿");
        System.exit(0);
    }


}
