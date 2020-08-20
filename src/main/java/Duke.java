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
            } else if (input_split[0].equals("done") && input_split.length == 2) {
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
                String type = input_split[0];
                String name;
                if (type.equals("todo")){
                    name = input.split("todo ")[1];
                    list.add(new ToDo(name));
                }else if(type.equals("deadline")){
                    name = input.split(" /by ")[0].split("deadline ")[1];
                    String by = input.split(" /by ")[1];
                    list.add(new Deadline(name, by));
                }else if(type.equals("event")){
                    name = input.split(" /at ")[0].split("event ")[1];
                    String at = input.split(" /at ")[1];
                    list.add(new Event(name, at));
                }else{
                    list.add(new Task(input));
                    name = input;
                }

                System.out.println("Go it. I've added this task:\n" + list.get(list.size()-1).toString());
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            }
        }

        // Exit
        System.out.println("Bye. Hope to see you again soon!");

        sc.close();
    }

}

class Task {
    protected String name;
    protected boolean done;

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

class ToDo extends Task {
    public ToDo(String name){
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    protected String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

class Event extends Task {
    protected String at;

    public Event (String name, String at) {
        super(name);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}