import java.util.Scanner;

public class Execute {

    public static void execute(){
        // Initial welcome message
        Salutations welcome = new Salutations(Salutations.type.WELCOME);
        welcome.printMessage(1);

        // creation of List
        Storage listOfItems = new Storage();

        output(listOfItems);
    }

    public static void output(Storage listOfItems) {
        // Subsequent messages
        Scanner scan = new Scanner(System.in);
        label:
        while (true) {
            String userInput = scan.nextLine();
            String[] userInputArr = userInput.split(" ");
            String userInstruction = userInputArr[0];

            // reject bad user instruction
            try {
                if (!checkInstructionValidity(userInstruction)) {
                    continue;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                output(listOfItems);
                break;
            }

            try {

                switch (userInstruction) {
                    case "bye":
                        if (!userInput.equals(userInstruction)) {
                            throw new DukeException("Please do not add anything after the \"bye\" command");
                        }

                        // Exit message
                        Salutations goodbye = new Salutations(Salutations.type.GOODBYE);
                        goodbye.printMessage(1);
                        break label;
                    case "list":
                        if (!userInput.equals(userInstruction)) {
                            throw new DukeException("Please do not add anything after the \"list\" command");
                        }
                        listOfItems.print();
                        break;
                    case "done":
                        try {
                            String numberCharacter = userInput.substring(5);
                            int index = Integer.parseInt(numberCharacter) - 1;

                            Task taskToChange = listOfItems.getItem(index);
                            taskToChange.markDone();

                            // printing part
                            Salutations markedDone = new Salutations(Salutations.type.TASKDONE);
                            markedDone.printMessage(1);
                            taskToChange.print(2);
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("Please enter a task number within the range of tasks");
                        } catch (NumberFormatException a) {
                            throw new DukeException("Please enter a valid task number for me to mark as done");
                        }

                        break;
                    case "delete":
                        try {
                            String taskNumberToDelete = userInput.substring(7);
                            int index = Integer.parseInt(taskNumberToDelete) - 1;
                            listOfItems.deleteItem(index);

                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("Please enter a task number within the range of tasks");
                        } catch(NumberFormatException e) {
                            throw new DukeException("Please enter a valid task number for me to delete");
                        }
                        break;
                    default:
                        // Task creation
                        Task task = taskCreator(userInstruction, userInput);
                        listOfItems.addItem(task);
                        break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                output(listOfItems);
                break;
            }
        }
    }

    private static Task taskCreator(String userInstruction, String input) throws DukeException {
        switch (userInstruction) {
            case "todo":
                try {
                    return new Todo(input.substring(5));
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("The description of a todo cannot be empty");
                }
            case "deadline":

                try {
                    // Splitting string
                    String substring = input.substring(9);
                    String[] strArr = substring.split("/by");
                    String description = strArr[0];
                    String date = strArr[1];
                    return new Deadline(description, date);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("Please give me details for your deadline task");
                } catch (ArrayIndexOutOfBoundsException a) {
                    throw new DukeException("Please give date / time after 'by/' for this deadline task");
                }
            case "event":

                try {
                    // Splitting string
                    String substring = input.substring(6);
                    String[] strArr = substring.split("/at");
                    String description = strArr[0];
                    String date = strArr[1];
                    return new Event(description, date);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("Please give me information about your event!");
                } catch (ArrayIndexOutOfBoundsException a) {
                    throw new DukeException("Please give date / time after 'at/' for this event");
                }
            default:
                // simply a placeholder, this block would not get called under any circumstance
                return null;
        }
    }

    private static boolean checkInstructionValidity(String userInstruction) throws DukeException {
        switch (userInstruction) {
            case "list":
            case "bye":
            case "done":
            case "todo":
            case "deadline":
            case "event":
            case "delete":
                return true;
            default:
                throw new DukeException("Please enter a valid instruction (eg. todo, list, done...)");
        }
    }
}
