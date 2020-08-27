import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final Storage storage;
    //private final TaskList tasks;
    private final Parser parser;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        //tasks = new TaskList(storage.load());
    }
    
    public void run() {
        Scanner sc = new Scanner(System.in);
        Instruction thisInstruction;
        Deadline thisDeadline;
        ArrayList<Task> list;
        String thisTaskname;
        String thisTime;
        Task thisTask;
        String input;
        int number;
        
        list = storage.load();
        ui.greet();
        input = sc.nextLine();
        
        while(!input.equals("bye")) {
            System.out.println("    -x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
            thisInstruction = parser.load(input);
            try {
                if (thisInstruction == Instruction.LIST) {
                    System.out.println("     Here are the tasks in your list:");
                    for (int i = 1; i <= list.size(); i++) {
                        thisTask = list.get(i - 1);
                        System.out.println("     " + i + "." + thisTask.toString());
                    }
                } else if (thisInstruction == Instruction.DONE) {
                    number = Character.getNumericValue(input.charAt(input.length() - 1));
                    System.out.println("     Nice! I've marked this task as done:");
                    thisTask = list.get(number - 1);
                    thisTask.markAsDone();
                    list.set(number - 1, thisTask);
                    System.out.println("       " + thisTask.toString());
                    storage.updateList(list);
                } else if (thisInstruction == Instruction.DELETE) {
                    number = Character.getNumericValue(input.charAt(input.length() - 1));
                    System.out.println("     Sure! I've removed this task for you:");
                    thisTask = list.get(number - 1);
                    list.remove(number - 1);
                    System.out.println("       " + thisTask.toString());
                    System.out.println("     Now you have " + list.size() + " tasks in the list.");
                    storage.updateList(list);
                } else {
                    if (thisInstruction == Instruction.DEADLINE) {
                        if (input.length() < 10) {
                            throw new DukeException("     The taskname of a deadline cannot be empty.");
                        }
                        System.out.println("     No problem! I've added this task to the list:");
                        thisTaskname = input.substring(9, input.indexOf('/') - 1);
                        thisTime = input.substring(input.indexOf('/') + 4);
                        thisDeadline = new Deadline(thisTaskname, false, thisTime);
                        thisDeadline.updateDateTime();
                        list.add(thisDeadline);
                    } else if (thisInstruction == Instruction.EVENT) {
                        if (input.length() < 7) {
                            throw new DukeException("     The taskname of a event cannot be empty.");
                        }
                        System.out.println("     No problem! I've added this task to the list:");
                        thisTaskname = input.substring(6, input.indexOf('/') - 1);
                        thisTime = input.substring(input.indexOf('/') + 4);
                        list.add(new Event(thisTaskname, false, thisTime));
                    } else if (thisInstruction == Instruction.TODO) {
                        if (input.length() < 6) {
                            throw new DukeException("     The taskname of a todo cannot be empty.");
                        }
                        System.out.println("     No problem! I've added this task to the list:");
                        thisTaskname = input.substring(5);
                        list.add(new Todo(thisTaskname, false));
                    } else {
                        throw new DukeException("     I'm sorry, but I don't know what that means :-(");
                    }
                    storage.updateList(list);
                    System.out.println("       " + list.get(list.size() - 1));
                    System.out.println("     Now you have " + list.size() + " tasks in the list.");
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("    -x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
            input = sc.nextLine();
        }
        ui.bye();
    }
    
    public static void main(String[] args) {
        //String filePath = System.getProperty("user.home")  + "/deskTop/CS2103 iP/data/Duke.txt";
        new Duke("data/Duke.txt").run();
    }
}