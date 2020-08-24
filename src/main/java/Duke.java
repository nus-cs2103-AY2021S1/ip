import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private ArrayList<Task> stringStore = new ArrayList<>();
    private Storage storage;

    public Duke(){
        try{
            this.storage = new Storage();
            this.stringStore = storage.load();

        } catch (IOException e){
            System.out.println("ERROR: File Loadding error");

        }
    }


    public static void main(String[] args) {
        Duke dukeMessager = new Duke();
        dukeMessager.run();



    }

    public void run() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();

        if (sc.hasNext()) {
            String input = sc.nextLine();
            while (!input.equals(TaskElement.BYE.label)) {
                try{
                    if (input.equals(TaskElement.LIST.label)) {
                        printLine();
                        System.out.println(" Here are the tasks in your list:");
                        int sizeStore = stringStore.size();
                        for (int i = 1; i < sizeStore + 1; i++) {
                            System.out.println(i + "." + stringStore.get(i - 1));
                        }
                        printLine();
                    } else if (input.split(" ")[0].equals(TaskElement.DONE.label)) {
                        int doneTask = Integer.parseInt(input.split(" ")[1]) - 1;
                        if(doneTask + 1 > stringStore.size() || doneTask < 0){
                            throw new DukeInvalidDoneNumException(input);
                        }
                        stringStore.get(doneTask).markAsDone();
                    } else if (input.split(" ")[0].equals(TaskElement.TODO.label)) {
                        if (input.split(" ").length == 1) {
                            throw new DukeEmptyToDoException(input);
                        }
                        String tasker = stringBuilder(input.split(" "), 1, input.split(" ").length - 1);
                        Todo todoTask = new Todo(tasker);
                        stringStore.add(todoTask);
                        printer(todoTask);
                    } else if (input.split(" ")[0].equals(TaskElement.DEADLINE.label)) {
                        if (input.split(" ").length == 1) {
                            throw new DukeEmptyDeadlineException(input);
                        }
                        String deadliner = stringBuilder(input.split(" "), 1, input.split(" ").length - 1);
                        String[] deadlinerparts = deadliner.split(" /by ");
                        if(deadlinerparts.length == 1){
                            throw new DukeEmptyDeadlineTImeException(input);
                        }
                        Deadline deadlineTask = new Deadline(deadlinerparts[0], deadlinerparts[1]);
                        stringStore.add(deadlineTask);
                        printer(deadlineTask);
                    } else if (input.split(" ")[0].equals(TaskElement.EVENT.label)) {
                        if (input.split(" ").length == 1) {
                            throw new DukeEmptyEventException(input);
                        }
                        String eventer = stringBuilder(input.split(" "), 1, input.split(" ").length - 1);
                        String[] eventParts = eventer.split(" /at ");
                        if (eventParts.length == 1) {
                            throw new DukeEmptyEventTimeException(input);
                        }
                        Event eventTask = new Event(eventParts[0], eventParts[1]);
                        stringStore.add(eventTask);
                        printer(eventTask);
                    } else if(input.split(" ")[0].equals(TaskElement.DELETE.label)){
                        int deleteTask = Integer.parseInt(input.split(" ")[1]) - 1;
                        if(deleteTask + 1 > stringStore.size() || deleteTask < 0){
                            throw new DukeDeleteException(input);
                        }
                        deletePrinter(stringStore.get(deleteTask));
                        stringStore.remove(deleteTask);
                    } else {
                        throw new DukeUnknownInputException(input);
                    }
                }  catch (DukeUnknownInputException e){
                    printLine();
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    printLine();
                } catch (DukeEmptyToDoException e) {
                    printLine();
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                    printLine();
                } catch (DukeEmptyEventException e) {
                    printLine();
                    System.out.println("OOPS!!! The description of a event cannot be empty.");
                    printLine();
                } catch (DukeEmptyDeadlineException e) {
                    printLine();
                    System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                    printLine();
                } catch (DukeInvalidDoneNumException e) {
                    printLine();
                    System.out.println("OOPS!!! The invalid done number.");
                    printLine();
                } catch (DukeEmptyDeadlineTImeException e) {
                    printLine();
                    System.out.println("OOPS!!! The description of a deadline time cannot be empty.");
                    printLine();
                } catch (DukeEmptyEventTimeException e) {
                    printLine();
                    System.out.println("OOPS!!! The description of a event time cannot be empty.");
                    printLine();
                } catch (DukeDeleteException e) {
                    printLine();
                    System.out.println("OOPS!!! The invalid delete number.");
                    printLine();
                } catch (DateTimeParseException e){
                    printLine();
                    System.out.println("OOPS!!! The invalid date format has been keyed in. PLease enter in dd-MM-yyyy HH:mm format");
                    printLine();
                }
                if (sc.hasNext()) {
                    input = sc.nextLine();
                }
            }
            printLine();
            System.out.println("Bye. Hope to see you again soon!");
            printLine();



        }
        storage.save(storage.convertArrayToSaveFormat(this.stringStore));
    }




    public static String stringBuilder(String[] arr, int start, int end){
        String store = "";
        for (int i = start; i <= end; i++) {
            if(i == end){
                store += arr[i];
            } else {
                store += arr[i] + " ";
            }

        }
        return store;
    }

    public void printer(Task task){
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + String.valueOf(this.stringStore.size()) + " tasks in the list.");
        printLine();

    }

    public void deletePrinter(Task task){
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + String.valueOf(this.stringStore.size() - 1) + " tasks in the list.");
        printLine();
    }

    public static void printLine(){
        System.out.println(" ____________________________________________________________");
    }



}
