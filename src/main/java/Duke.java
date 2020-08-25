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
        Scanner sc = new Scanner(System.in);
        UI.printGreeting();
        if (sc.hasNext()) {
            String input = sc.nextLine();
            while (!input.equals(TaskElement.BYE.label)) {
                try{
                    if (input.equals(TaskElement.LIST.label)) {
                        UI.printListOfTasks(this.stringStore);
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
                        UI.printTaskAdd(todoTask, stringStore.size());
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
                        UI.printTaskAdd(deadlineTask, stringStore.size());
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
                        UI.printTaskAdd(eventTask, this.stringStore.size());
                    } else if(input.split(" ")[0].equals(TaskElement.DELETE.label)){
                        int deleteTask = Integer.parseInt(input.split(" ")[1]) - 1;
                        if(deleteTask + 1 > stringStore.size() || deleteTask < 0){
                            throw new DukeDeleteException(input);
                        }
                        UI.printDeleteMessage(stringStore.get(deleteTask), stringStore.size() - 1);
                        stringStore.remove(deleteTask);
                    } else {
                        throw new DukeUnknownInputException(input);
                    }
                }  catch (DukeUnknownInputException e){
                    UI.printFormattedMessage("OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (DukeEmptyToDoException e) {
                    UI.printFormattedMessage("OOPS!!! The description of a todo cannot be empty.");
                } catch (DukeEmptyEventException e) {
                    UI.printFormattedMessage("OOPS!!! The description of a event cannot be empty.");
                } catch (DukeEmptyDeadlineException e) {
                    UI.printFormattedMessage("OOPS!!! The description of a deadline cannot be empty.");
                } catch (DukeInvalidDoneNumException e) {
                    UI.printFormattedMessage("OOPS!!! The invalid done number.");
                } catch (DukeEmptyDeadlineTImeException e) {
                    UI.printFormattedMessage("OOPS!!! The description of a deadline time cannot be empty.");
                } catch (DukeEmptyEventTimeException e) {
                    UI.printFormattedMessage("OOPS!!! The description of a event time cannot be empty.");
                } catch (DukeDeleteException e) {
                    UI.printFormattedMessage("OOPS!!! The invalid delete number.");
                } catch (DateTimeParseException e){
                    UI.printFormattedMessage("OOPS!!! The invalid date format has been keyed in. PLease enter in dd-MM-yyyy HH:mm format");
                }
                if (sc.hasNext()) {
                    input = sc.nextLine();
                }
            }
            UI.printByeMessage();
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

    public static void printLine(){
        System.out.println(" ____________________________________________________________");
    }



}
