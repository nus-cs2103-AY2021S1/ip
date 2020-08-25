import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Duke {
//    public static final String pathToFile = ".\\data\\duke.txt";
    public static final Path filepath = Paths.get(".", "data", "duke.txt");
    public SaveLoad saveLoad;
    public Storage storage;

    public Duke() {
        this.storage = new Storage();
        this.saveLoad = new SaveLoad(filepath);
        try {
            this.storage.setList(saveLoad.getListOfTasks());
        } catch (DukeException e) {
            System.out.println("Error in extracting tasks from saved file");
            storage = new Storage();
        }
    }



    public static void main(String[] args) {
        // Initial welcome message
        Salutations welcome = new Salutations(Salutations.type.WELCOME);
        welcome.printMessage(1);

        Duke duke = new Duke();
        Storage listOfItems = duke.storage;
        output(listOfItems, duke);
    }

    public static void output(Storage listOfItems, Duke duke) {
        // Subsequent messages
        Scanner scan = new Scanner(System.in);
        while (true) {
            String userInput = scan.nextLine();
            String[] userInputArr = userInput.split(" ");
            String userInstruction = userInputArr[0];

            try {
                if (!checkInstructionValidity(userInstruction)) {
                    continue;
                }
                // ENUM generation
                EnumUserInstruction.userInstruction userInstructionEnum = EnumUserInstruction.userInstruction.
                        valueOf(userInstruction.toUpperCase());


                switch (userInstructionEnum) {
                    case BYE:
                        byeHandler(userInput, userInstruction);
                        break;
                    case LIST:
                        listHandler(userInput, userInstruction, listOfItems);
                        break;
                    case DONE:
                        doneHandler(userInput, listOfItems, duke);
                        break;
                    case DELETE:
                        deleteHandler(userInput, listOfItems, duke);
                        break;
                    case TODO:
                        todoCreator(userInputArr, userInput, listOfItems, duke);
                        break;
                    case DEADLINE:
                        deadlineCreator(userInput, listOfItems, duke);
                        break;
                    case EVENT:
                        eventCreator(userInput, listOfItems, duke);
                        break;
                    default:
                        throw new DukeException("Sorry, please try again :)");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                output(listOfItems, duke);
                break;
            }
            if (userInstruction.equals("bye")) {
                break;
            }
        }
    }

    private static void byeHandler(String userInput, String userInstruction) throws DukeException {
        if (!userInput.equals(userInstruction)) {
            throw new DukeException("Please do not add anything after the \"bye\" command");
        }

        // Exit message
        Salutations goodbye = new Salutations(Salutations.type.GOODBYE);
        goodbye.printMessage(1);
    }

    private static void listHandler(String userInput, String userInstruction, Storage listOfItems) throws DukeException {
        if (!userInput.equals(userInstruction)) {
            throw new DukeException("Please do not add anything after the \"list\" command");
        }
        listOfItems.print();
    }

    private static void doneHandler(String userInput, Storage listOfItems, Duke duke) throws DukeException {
        try {
            String numberCharacter = userInput.substring(5);
            int index = Integer.parseInt(numberCharacter) - 1;

            Task taskToChange = listOfItems.getItem(index);
            taskToChange.markDone();

            // change data file
            duke.saveLoad.modifyTask(taskToChange, index);

            // printing part
            Salutations markedDone = new Salutations(Salutations.type.TASKDONE);
            markedDone.printMessage(1);
            taskToChange.print(2);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a task number within the range of tasks");
        } catch (NumberFormatException a) {
            throw new DukeException("Please enter a valid task number for me to mark as done");
        }
    }

    private static void deleteHandler(String userInput, Storage listOfItems, Duke duke) throws DukeException {
        try {
            String taskNumberToDelete = userInput.substring(7);
            int index = Integer.parseInt(taskNumberToDelete) - 1;
            listOfItems.deleteItem(index);

            // change data file
            duke.saveLoad.deleteTask(index);

        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a task number within the range of tasks");
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid task number for me to delete");
        }
    }

    private static void todoCreator(String[] inputArr, String input, Storage listOfItems, Duke duke) throws DukeException {
        try {
            if (inputArr[1].isBlank()) {
                throw new DukeException("The description of a todo cannot be empty");
            }
            Task todoTask = new Todo(input.substring(5));
            listOfItems.addItem(todoTask);

            // change data file
            duke.saveLoad.addTask(todoTask);

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("The description of a todo cannot be empty");
        }
    }

    private static void deadlineCreator(String input, Storage listOfItems, Duke duke) throws DukeException {
        try {
            // Splitting string
            String substring = input.substring(9);
            String[] strArr = substring.split("/by");
            String description = strArr[0];
            String date = strArr[1];
            Task deadlineTask = new Deadline(description, date);
            listOfItems.addItem(deadlineTask);

            // change data file
            duke.saveLoad.addTask(deadlineTask);

        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Please give me details for your deadline task");
        } catch (ArrayIndexOutOfBoundsException a) {
            throw new DukeException("Please give date / time after '/by' for this deadline task");
        }
    }

    private static void eventCreator(String input, Storage listOfItems, Duke duke) throws DukeException {
        try {
            // Splitting string
            String substring = input.substring(6);
            String[] strArr = substring.split("/at");
            String description = strArr[0];
            String date = strArr[1];
            Task eventTask = new Event(description, date);
            listOfItems.addItem(eventTask);

            // change data file
            duke.saveLoad.addTask(eventTask);

        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Please give me information about your event!");
        } catch (ArrayIndexOutOfBoundsException a) {
            throw new DukeException("Please give date / time after '/at' for this event");
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
