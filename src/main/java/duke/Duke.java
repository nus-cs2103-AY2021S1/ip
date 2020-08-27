package duke;
import java.util.Scanner;

public class Duke {
    private final Storage storage;
    private final TaskList list;
    private final Parser parser;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        list = new TaskList(storage.load());
    }
    
    public void run() {
        Scanner sc = new Scanner(System.in);
        Instruction thisInstruction;
        Deadline thisDeadline;
        String thisTaskname;
        String thisTime;
        String keyword;
        Task thisTask;
        String input;
        int number;
        
        ui.greet();
        input = sc.nextLine();
        
        while(!input.equals("bye")) {
            System.out.println("    -x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
            thisInstruction = parser.load(input);
            try {
                if (thisInstruction == Instruction.LIST) {
                    ui.printList(list.getList());
                } else if (thisInstruction == Instruction.DONE) {
                    number = Character.getNumericValue(input.charAt(input.length() - 1));
                    thisTask = list.get(number - 1);
                    thisTask.markAsDone();
                    list.set(number - 1, thisTask);
                    ui.markAsDone(thisTask);
                    storage.updateList(list.getList());
                } else if (thisInstruction == Instruction.DELETE) {
                    number = Character.getNumericValue(input.charAt(input.length() - 1));
                    thisTask = list.get(number - 1);
                    list.remove(number - 1);
                    ui.deletedTask(thisTask, list.getList());
                    storage.updateList(list.getList());
                } else if (thisInstruction == Instruction.FIND) {
                    keyword = input.substring(5);
                    ui.printFoundTask(keyword, list.getList());
                } else {
                    if (thisInstruction == Instruction.DEADLINE) {
                        if (input.length() < 10) {
                            throw new DukeException("     The taskname of a deadline cannot be empty.");
                        }
                        ui.noProblem();
                        thisTaskname = input.substring(9, input.indexOf('/') - 1);
                        thisTime = input.substring(input.indexOf('/') + 4);
                        thisDeadline = new Deadline(thisTaskname, false, thisTime);
                        thisDeadline.updateDateTime();
                        list.add(thisDeadline);
                    } else if (thisInstruction == Instruction.EVENT) {
                        if (input.length() < 7) {
                            throw new DukeException("     The taskname of a event cannot be empty.");
                        }
                        ui.noProblem();
                        thisTaskname = input.substring(6, input.indexOf('/') - 1);
                        thisTime = input.substring(input.indexOf('/') + 4);
                        list.add(new Event(thisTaskname, false, thisTime));
                    } else if (thisInstruction == Instruction.TODO) {
                        if (input.length() < 6) {
                            throw new DukeException("     The taskname of a todo cannot be empty.");
                        }
                        ui.noProblem();
                        thisTaskname = input.substring(5);
                        list.add(new Todo(thisTaskname, false));
                    } else {
                        throw new DukeException("     I'm sorry, but I don't know what that means :-(");
                    }
                    storage.updateList(list.getList());
                    ui.updatedTask(list.getList());
                }
            } catch (DukeException ex) {
                ui.printError(ex);
            }
            ui.printLine();
            input = sc.nextLine();
        }
        ui.bye();
    }
    
    public static void main(String[] args) {
        new Duke("data/Duke.txt").run();
    }
}