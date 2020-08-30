import java.io.OutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;

public class Duke {

    public static void main(String[] args){
        // Greet
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        // Creates list
        ArrayList<Task> list = new ArrayList<>();

        //Destination
        String dest = "data/duke.txt";

        // Read file
        File f = new File(dest);
        Scanner sc;
        try {
            sc = new Scanner(f);
            while(sc.hasNextLine()) {
                String input = sc.nextLine();
                String[] inputSplit = input.split("/");

                Task task;
                switch (inputSplit[0]) {
                    case "T":
                        // two inputs
                        task = new ToDo(inputSplit[2]);
                        break;
                    case "D":
                        //three inputs
                        task = new Deadline(inputSplit[2], inputSplit[3]);
                        break;
                    case "E":
                        task = new Event(inputSplit[2], inputSplit[3]);
                        break;
                    default:
                        continue;
                }

                if(Boolean.parseBoolean(inputSplit[1])) {
                    task.markDone();
                }
                list.add(task);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
            return;
        }

        // Take user input
        sc = new Scanner(System.in);
        try{
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                String[] input_split = input.split(" ");
                if (input.equals("bye")) {
                    // Stops taking inputs
                    break;
                } else if (input.equals("list")) {
                    // returns list of items
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((1 + i) + "." + list.get(i));
                    }
                } else if (input_split[0].equals("done") && input_split.length == 2) {
                    // Parse int
                    int index = Integer.parseInt(input_split[1]) - 1;

                    // Mark task as done
                    Task task = list.get(index);
                    task.markDone();

                    //New parsed
                    String parsedData = task.getParsedData();
                    String newData = "";
                    Scanner reader = new Scanner(f);

                    for (int i = 0; i<list.size(); i++){
                        if (i == index) {
                            newData = newData + parsedData + System.lineSeparator();
                        } else {
                            newData = newData + reader.nextLine() + System.lineSeparator();
                        }
                    }


                    saveData(dest, newData, false);

                    //Notify system
                    System.out.println("Nice! I've marked this task as done:\n" + task);

                } else if (input_split[0].equals("delete")) {

                    // Parse int
                    int index = Integer.parseInt(input_split[1]) - 1;

                    //Remove from queue
                    Task removed = list.remove(index);

                    //New text
                    Scanner reader = new Scanner(f);
                    String newData = "";
                    for (int i = 0; i<list.size(); i++){
                        if(i != index){
                            newData = newData + reader.nextLine() + System.lineSeparator();
                        }
                    }

                    saveData(dest, newData, false);

                    System.out.println("Noted. I've removed this task:\n" + removed.toString() +
                            "\nNow you have " + list.size() + " tasks in the list.");
                } else {
                    // Adds item to list
                    String type = input_split[0];
                    String name;
                    Task task;
                    try {
                        if (type.equals("todo")) {

                            if (input_split.length < 2) {
                                // Throw new error message that todo has no desc
                                throw (new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty."));
                            } else {
                                name = input.split("todo ")[1];
                                task = new ToDo(name);
                            }

                        } else if (type.equals("deadline")) {
                            name = input.split(" /by ")[0].split("deadline ")[1];
                            String by = input.split(" /by ")[1];
                            task = new Deadline(name, by);

                        } else if (type.equals("event")) {
                            name = input.split(" /at ")[0].split("event ")[1];
                            String at = input.split(" /at ")[1];
                            task = new Event(name, at);
                        } else {
                            //Throw error message for invalid input
                            throw (new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-("));
                        }
                        //Add task
                        list.add(task);

                        //Append parsedData
                        String toAppend = task.getParsedData() + System.lineSeparator();
                        saveData(dest, toAppend, true);

                        System.out.println("Go it. I've added this task:\n" + list.get(list.size() - 1).toString());
                        System.out.println("Now you have " + list.size() + " tasks in the list.");

                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }

        // Exit
        System.out.println("Bye. Hope to see you again soon!");

        sc.close();
    }

    public static void saveData(String dest, String text, Boolean appendMode) throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(dest, appendMode));
        bw.write(text);
        bw.close();
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

    public String getParsedData(){
        return String.valueOf(this.done) + "/" + this.name;
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
    public String getParsedData(){
        return "T" + "/" + String.valueOf(super.done) + "/" + super.name;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    final static private DateTimeFormatter MYDATEFORMATTER =
            DateTimeFormatter.ofPattern("LLL dd uuuu");
    protected String by;
    protected LocalDate date;


    public Deadline(String name, String by) {
        super(name);
        this.date = LocalDate.parse(by);
        this.by = this.date.format(MYDATEFORMATTER);
    }

    @Override
    public String getParsedData(){
        return "D" + "/" + String.valueOf(super.done) + "/" + super.name + "/" + this.by;
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
    public String getParsedData(){
        return "E" + "/" + String.valueOf(super.done) + "/" + super.name + "/" + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

class DukeException extends Exception{
    public DukeException(String message){
        super(message);
    }
}