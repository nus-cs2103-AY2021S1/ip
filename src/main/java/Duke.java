import java.util.Scanner;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;
import static java.lang.System.exit;

public class Duke {
    enum Instruction {
        LIST, DONE, DELETE, DEADLINE, EVENT, TODO, OTHERS
    }

    public static void updateList(String filePath, ArrayList<Task> list) throws DukeException {
        try {
            FileWriter fw1 = new FileWriter(filePath);
            FileWriter fw2 = new FileWriter(filePath, true);
            for (int i = 1; i <= list.size(); i++) {
                Task thisTask = list.get(i - 1);
                if (i == 1) {
                    fw1.write("     " + i + "." + thisTask.toString() + System.lineSeparator());
                } else {
                    fw2.write("     " + i + "." + thisTask.toString() + System.lineSeparator());
                }
            }
            fw1.close();
            fw2.close();
        } catch (IOException ex) {
            throw new DukeException(ex.getMessage());
        }
    }
    
    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Instruction thisInstruction;
        String thisTaskname;
        String thisTime;
        Task thisTask;
        String input;
        int number;
        
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        String home = System.getProperty("user.home");
        String folderpath = home + "/deskTop/CS2103 iP/data";
        String filepath = folderpath + "/Duke.txt";

        try {
            if (!(new java.io.File(folderpath).exists())) {
                throw new DukeException("There is not such folder.");
            } else if (!(new java.io.File(filepath).exists())) {
                throw new DukeException("There is not such file.");
            }
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
        }
        
        System.out.println("    -x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
        System.out.println("    Hello from\n" + logo);
        System.out.println("    Hey there! This is Duke here~");
        System.out.println("    How may I help you today?");
        System.out.println("    -x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");

        input = sc.nextLine();
        while(!input.equals("bye")) {
            System.out.println("    -x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
            if (input.equals("list")) {
                thisInstruction = Instruction.LIST;
            } else if (input.startsWith("done")) {
                thisInstruction = Instruction.DONE;
            } else if (input.startsWith("delete")) {
                thisInstruction = Instruction.DELETE;
            } else if (input.startsWith("deadline")) {
                thisInstruction = Instruction.DEADLINE;
            } else if (input.startsWith("event")) {
                thisInstruction = Instruction.EVENT;
            } else if (input.startsWith("todo")) {
                thisInstruction = Instruction.TODO;
            } else {
                thisInstruction = Instruction.OTHERS;
            }
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
                    updateList(filepath,list);
                } else if (thisInstruction == Instruction.DELETE) {
                    number = Character.getNumericValue(input.charAt(input.length() - 1));
                    System.out.println("     Sure! I've removed this task for you:");
                    thisTask = list.get(number - 1);
                    list.remove(number - 1);
                    System.out.println("       " + thisTask.toString());
                    System.out.println("     Now you have " + list.size() + " tasks in the list.");
                    updateList(filepath,list);
                } else {
                    if (thisInstruction == Instruction.DEADLINE) {
                        if (input.length() < 10) {
                            throw new EmptyTaskNameException("     The taskname of a deadline cannot be empty.");
                        }
                        System.out.println("     No problem! I've added this task to the list:");
                        thisTaskname = input.substring(9, input.indexOf('/') - 1);
                        thisTime = input.substring(input.indexOf('/') + 4);
                        list.add(new Deadline(thisTaskname, thisTime));
                    } else if (thisInstruction == Instruction.EVENT) {
                        if (input.length() < 7) {
                            throw new EmptyTaskNameException("     The taskname of a event cannot be empty.");
                        }
                        System.out.println("     No problem! I've added this task to the list:");
                        thisTaskname = input.substring(6, input.indexOf('/') - 1);
                        thisTime = input.substring(input.indexOf('/') + 4);
                        list.add(new Event(thisTaskname, thisTime));
                    } else if (thisInstruction == Instruction.TODO) {
                        if (input.length() < 6) {
                            throw new EmptyTaskNameException("     The taskname of a todo cannot be empty.");
                        }
                        System.out.println("     No problem! I've added this task to the list:");
                        thisTaskname = input.substring(5);
                        list.add(new Todo(thisTaskname));
                    } else {
                        throw new UnknownInstructionException("     I'm sorry, but I don't know what that means :-(");
                    }
                    updateList(filepath,list);
                    System.out.println("       " + list.get(list.size() - 1));
                    System.out.println("     Now you have " + list.size() + " tasks in the list.");
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("    -x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
            input = sc.nextLine();
        }

        System.out.println("    -x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
        System.out.println("    Bye bye~ See ya!");
        System.out.println("    -x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
    }
}