import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Duke dk = new Duke();
        ArrayList<String> tasks = new ArrayList<String>();

        dk.greet();
        Scanner sc = new Scanner( System.in );
        while (sc.hasNext()) {
            String input = sc.next();
            if (input.equals("bye")) {
                dk.endConversation();
            } else if (input.equals("list")) {
                dk.showTask(tasks);
            } else {
                dk.echo(input);
                tasks.add(input);
            }
        }
    }

    public void greet(){
        System.out.println("Hello! I am Duke Chan.\nWhat can I do for you? ᕕ( ᐛ )ᕗ");
    }

    public void echo(String input){
        System.out.println("added: " + input);
    }

    public void showTask(ArrayList<String> tasks){
        int no = 1;
        for (String task : tasks) {
            System.out.println(no + ". " + task);
            no++;
        }
    }

    public void endConversation(){
        System.out.println("Bye~ Hope to see you again soon! ∠( ᐛ 」∠)＿");
        System.exit(0);
    }


}
