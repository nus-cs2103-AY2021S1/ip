import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Duke dk = new Duke();
        ArrayList<Task> tasks = new ArrayList<Task>();

        dk.greet();
        Scanner sc = new Scanner( System.in );
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                dk.endConversation();
            } else if (input.equals("list")) {
                dk.showTask(tasks);
            } else if (input.contains("done")){
                String no = input.substring(5);
                int index = Integer.parseInt(no) - 1;
                Task newTask = tasks.get(index).markAsDone();
                tasks.set(index, newTask);
            } else {
                dk.echo(input);
                Task newTask = new Task(input);
                tasks.add(newTask);
            }
        }
    }

    public void greet(){
        System.out.println("Hello! I am Duke Chan.\nWhat can I do for you? ᕕ( ᐛ )ᕗ");
    }

    public void echo(String input){
        System.out.println("added: " + input);
    }

    public void showTask(ArrayList<Task> tasks){
        int no = 1;
        for (Task task : tasks) {
            String state = "[" + task.getStatusIcon() + "] ";
            System.out.println(state + no + ". " + task.description);
            no++;
        }
    }

    public void endConversation(){
        System.out.println("Bye~ Hope to see you again soon! ∠( ᐛ 」∠)＿");
        System.exit(0);
    }


}
