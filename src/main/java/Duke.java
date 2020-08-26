import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {


    static String projectRoot = System.getProperty("user.dir");
    static String filePath = projectRoot + File.separator + "data" + File.separator + "saveData.txt";
    static String dirPath = projectRoot + File.separator + "data";

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Howdy pardner!! I'm\n" + logo);

        System.out.println("What can I get yer for?");

        File saveData = new File(filePath);

        boolean dirExists = new File(dirPath).exists();

        if (!dirExists) new File(dirPath).mkdirs();

        // checking if the save file is already there
        try {
            boolean fileCreated = saveData.createNewFile();
            if (fileCreated) {
                System.out.println("Haven't seen a new face around 'ere for awhile, have a seat!");
            } else {
                System.out.println("A regular! The usual, I presume?");
            }
        } catch (IOException exception) {
            System.out.println(exception);
        }

        while (true) {
            String input = userInput.nextLine();
            String[] inputArray = input.split(" ");
            String firstWord = inputArray[0];
            try {
                if (firstWord.toLowerCase().equals("bye")) { // for termination

                    System.out.println("Well I'll see you around, pardner!!");
                    break;

                } else if (firstWord.toLowerCase().equals("list")) { // to display list of tasks

                    System.out.println("Here's yer current list of thingymajigs");

                    for (int i = 0; i < taskList.size(); i++) {
                        Task task = taskList.get(i);
                        System.out.println(i + 1 + ". " + task.toString());
                    }

                } else if (firstWord.toLowerCase().equals("done")) {
                    int index = Integer.parseInt(inputArray[1]);
                    Task task = taskList.get(index - 1);
                    task.markAsDone();
                    System.out.println("Sure thing baws! This right here is marked as done!\n" + task.toString());

                } else if (firstWord.toLowerCase().equals("delete")) {

                    if (inputArray.length <= 1) throw new DukeException("Sorry, which item did you want me to strike off again?");
                    try {
                        int index = Integer.parseInt(inputArray[1]);
                        if (index > taskList.size()) throw new DukeException("C'mon parder! That task doesn't exist cos you don't go so many!");
                        Task toBeRemoved = taskList.get(index - 1);
                        taskList.remove(toBeRemoved);
                        System.out.println("Alrighty, I'm taking that one out:\n" + toBeRemoved.toString() + "\n" + "You've got a total of " + taskList.size() + " items right now.");
                    } catch (NumberFormatException e) {
                        throw new DukeException("I need a number not a word in this case. Could ya pass that by me one more time?");
                    }

                } else { // to add task to list

                    Task task;
                    int index;
                    switch (firstWord.toLowerCase()) {
                        case "todo":
                            if (inputArray.length <= 1)
                                throw new DukeException("Sorry, but I can't do anything if you don't give me the description of your todo!");
                            String desc = stringCombiner(inputArray, 1, inputArray.length - 1);
                            task = new Todo(desc.trim());
                            break;
                        case "event":
                            String[] eventSplit = input.split("/at");
                            index = indexFinder(inputArray, "/at");
                            if (index == 0) throw new DukeException("Think you forgot the /at keyword, pardner!");
                            if (index == 1)
                                throw new DukeException("I'm gonna need a description for this here event!");
                            if (eventSplit.length == 1)
                                throw new DukeException("I'm gonna need a date or time for this!");
                            task = new Event(stringCombiner(inputArray, 1, index - 1).trim(), eventSplit[1].trim());
                            break;
                        case "deadline":
                            String[] deadlineSplit = input.split("/by");
                            index = indexFinder(inputArray, "/by");
                            if (index == 0) throw new DukeException("Think you forgot the /by keyword, pardner!");
                            if (index == 1)
                                throw new DukeException("I'm gonna need a description for this here deadline!");
                            if (deadlineSplit.length == 1)
                                throw new DukeException("I'm gonna need a date or time for this!");
                            task = new Deadline(stringCombiner(inputArray, 1, index - 1).trim(), deadlineSplit[1].trim());
                            break;
                        default:
                            throw new DukeException("Sorry, I didn't quite catch that!");
                    }
                    taskList.add(task);
                    System.out.println("Alrighty, I'll put it on yer tab:\n" + task.toString() + "\n" + "You've got a total of " + taskList.size() + " items right now.");
                }
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
        BufferedWriter saveWriter = null;
        try {
            saveWriter = new BufferedWriter(new FileWriter(saveData));
            StringBuffer saveContentBuffer = new StringBuffer();
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                saveContentBuffer.append(task.createSaveDataLine() + "\n");
            }

            String saveContent = saveContentBuffer.toString();

            saveWriter.write(saveContent);
        } catch (IOException exception) {
            System.out.println(exception);
        } finally {
            try {
                if (saveWriter != null) {
                    saveWriter.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }

    }

    private static String stringCombiner(String[] arr, int start, int end) {
        StringBuffer str = new StringBuffer();
        for (int i = start; i <= end; i ++) {
            str.append(arr[i] + " ");
        }
        return str.toString();
    }

    private static int indexFinder(String[] arr, String exp) {
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(exp)) index = i;
        }
        return index;
    }
}
