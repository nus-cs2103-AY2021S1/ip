import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Duke {

    private static String divider = "______________________________________________________________________________";
    private static String logo = "             *\n"
            + "      o  o  / \\  o  o\n"
            + "      |\\/ \\/   \\/ \\/|\n"
            + "      |             |\n"
            + "      |ooooooooooooo|\n"
            + " __  _  ____  ____    ____      ____    ___   ____  \n"
            + "|  |/ ||    ||    \\  /    |    |    \\  /   \\ |    \\ \n"
            + "|  ' /  |  | |  _  ||   __|    |  o  )|     ||  o  )\n"
            + "|    \\  |  | |  |  ||  |  |    |     ||  O  ||     |\n"
            + "|     | |  | |  |  ||  |_ |    |  O  ||     ||  O  |\n"
            + "|  .  | |  | |  |  ||     |    |     ||     ||     |\n"
            + "|__|\\_||____||__|__||___,_|    |_____| \\___/ |_____|\n";

    private static List<Task> list = new ArrayList<>();


    private static void addList(Task task) {
        list.add(task);
    }

    private static void deleteTask(String input) throws DukeException {
        if (isValidIndex(input)) {
            list.remove(getIndex(input));
        } else {
            throw new DukeException("You don't have such task in your list...");
        }
    }

    private static void showList() {
        System.out.println(divider);
        System.out.println("   Banana! So many tasks?");
        Task task;
        for (int i = 0; i < list.size(); i++) {
            task = list.get(i);
            System.out.println("   " + (i + 1) + ". " + task.toString());
        }
        System.out.println(divider + "\n");
    }

    private static int getListSize() {
        return list.size();
    }

    private static boolean isValidIndex(String input) {
        String[] stringArray = input.split(" ");
        int index;
        try {
            index = Integer.parseInt(stringArray[1]);
        } catch(Exception e) {
            return false;
        }
        int listSize = list.size();
        return index <= listSize && index > 0;
    }

    private static int getIndex(String input) {
        String[] stringArray = input.split(" ");
        return Integer.parseInt(stringArray[1]) - 1;
    }

    private static String getTodoDescription(String input) throws DukeException{
        try {
            return input.substring(5);
        } catch (Exception e){
            throw new DukeException("Todo cannot be empty!");
        }
    }

    private static String[] getDeadlineStrings(String input) {
        String[] stringArray = input.split(" /by ");
        stringArray[0] = stringArray[0].substring(9);
        return stringArray;
    }

    private static String[] getEventTimeStrings(String input) {
        String[] stringArray = input.split(" /at ");
        stringArray[0] = stringArray[0].substring(6);
        return stringArray;
    }

    private static void wrapMessage(String message) {
        System.out.println(divider);
        System.out.println("   " + message);
        System.out.println(divider + "\n");
    }

    private static void byeMessage() {
        wrapMessage("Banana! King Bob is sad to see you go. Farewell my friend!");
    }

    private static void addedMessage(Task task) {
        wrapMessage("Banana! Banana has been added to your list!\n"
                + "      " + task.toString() + "\n"
                + "   Now you have " + getListSize() + " banana(s) in your list! Nom nom..");
    }

    private static void deletedMessage(Task task) {
        wrapMessage("Banana! Banana has been eaten. Burp!\n"
                + "      " + task.toString() + "\n"
                + "   Now you have " + (getListSize() - 1) + " banana(s) in your list! Nom nom..");
    }

    private static void doneMessage(Task task) {
        wrapMessage("Banana! I've marked this task as done:\n"
                + "      " + task.toString());
    }

    private static void convertLineToTasks(String line) {

        String[] stringArray = line.split(" \\| ");
        String taskType = stringArray[0];
        boolean isDone = stringArray[1].equals("1");
        Task task = null;

        switch (taskType) {
        case "T":
            task = new Todo(stringArray[2]);
            break;
        case "D":
            task = new Deadline(stringArray[2], stringArray[3]);
            break;
        case "E":
            task = new Event(stringArray[2], stringArray[3]);
            break;
        default:
            break;
        }

        addList(task);
        if (isDone) {
            task.markAsDone();
        }
    }

    private static void saveListToFile(File file) {

        try {
            FileWriter writer = new FileWriter(file, false);

            for (Task t : list) {
                writer.write(t.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            wrapMessage(new DukeException("Writing error").toString());
        }
    }

    public static void main(String[] args) {

        System.out.println("Bello from the Majestic\n" + logo);
        System.out.println("Banana! What can King Bob do for you?\n" + divider + "\n");

        Path filePath = Paths.get( "data", "duke.txt");
        File dataFile = new File(filePath.toString());
        boolean fileExists = Files.exists(filePath);
        Path directoryPath = Paths.get("data");
        boolean directoryExists = Files.exists(directoryPath);

        if (!directoryExists) {
            File directory = new File(directoryPath.toString());
            directory.mkdir();
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                wrapMessage(new DukeException("WHAT WHATS GOING ON").toString());
            }
        } else if (!fileExists) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                wrapMessage(new DukeException("WHAT WHATS GOING ON").toString());
            }
        } else {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(filePath.toString()));
                String line;
                while ((line = reader.readLine()) != null) {
                    convertLineToTasks(line);
                }
            } catch (IOException e) {
                wrapMessage(new DukeException("WHAT WHATS GOING ON AAAAH").toString());
            }
        }

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            String command = input.split(" ")[0];
            InputCommand inputCommand;

            try {
                inputCommand = InputCommand.valueOf(command.toUpperCase());
            } catch (Exception e) {
                inputCommand = InputCommand.INVALID;
            }

            try {
                switch (inputCommand) {
                case BYE:
                    byeMessage();
                    return;
                case LIST:
                    showList();
                    break;
                case DONE:
                    if (isValidIndex(input)) {
                        Task task = list.get(getIndex(input));
                        task.markAsDone();
                        doneMessage(task);
                        saveListToFile(dataFile);
                    } else {
                        throw new DukeException("You don't have such task in your list...");
                    }
                    break;
                case DELETE:
                    deletedMessage(list.get(getIndex(input)));
                    deleteTask(input);
                    saveListToFile(dataFile);
                    break;
                case TODO:
                    addList(new Todo(getTodoDescription(input)));
                    addedMessage(new Todo(getTodoDescription(input)));
                    saveListToFile(dataFile);
                    break;
                case DEADLINE:
                    addList(new Deadline(getDeadlineStrings(input)[0], getDeadlineStrings(input)[1]));
                    addedMessage(new Deadline(getDeadlineStrings(input)[0], getDeadlineStrings(input)[1]));
                    saveListToFile(dataFile);
                    break;
                case EVENT:
                    addList(new Event(getEventTimeStrings(input)[0], getEventTimeStrings(input)[1]));
                    addedMessage(new Event(getEventTimeStrings(input)[0], getEventTimeStrings(input)[1]));
                    saveListToFile(dataFile);
                    break;
                default:
                    throw new DukeException("Give me a valid banana (input)!");
                }
            } catch (DukeException e){
                wrapMessage(e.toString());
            }
        }
        scanner.close();

    }
}