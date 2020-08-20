import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Greet
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        // Creates list
        ArrayList<Task> list = new ArrayList<>();

        // Echo
        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] input_split = input.split(" ");
            if (input.equals("bye")){
                // Stops taking inputs
                break;
            } else if( input.equals("list") ) {
                // returns list of items
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((1 + i) + "." + list.get(i));
                }
            } else if (input_split[0].equals("done") && input_split.length == 2){
                // Marks number as done
                int index;
                // Parse int
                index = Integer.parseInt(input_split[1]) - 1;

                // Mark task as done
                Task task = list.get(index);
                task.markDone();
                System.out.println("Nice! I've marked this task as done:\n" + task);

            } else {
                // Adds item to list
                Task newTask = new Task(input);
                list.add(newTask);
                System.out.println("added: " + input);
            }
        }

        // Exit
        System.out.println("Bye. Hope to see you again soon!");

        sc.close();
    }

}

class Task {
    private String name;
    private boolean done;
    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void markDone(){
        this.done = true;
    }

    public boolean getDone(){
        return this.done;
    }

    public String getName(){
        return this.name;
    }

    public String toString(){
        String symbol = done ? "\u2713" : "\u2718";
        return "[" + symbol + "] " + name;
    }
}