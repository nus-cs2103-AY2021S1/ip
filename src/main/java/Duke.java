import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String FILE_PATH = "data/tasklist.txt";

    //Remove done string and add in delimeter
    public static String[] editFileInput(String[] input) {
        ArrayList<String> result = new ArrayList<>();
        String taskType = input[0];
        for (int i = 0; i < input.length; i++) {
            if (taskType.equals("deadline") && i == 3) {
                result.add("/by");
            } else if (taskType.equals("event") && i == 3) {
                result.add("/at");
            }
            if (i != 1) {
                result.add(input[i]);
            }
        }
        String returnArray[] = new String[result.size()];
        return result.toArray(returnArray);
    }

    public static void readFile(ArrayList<Task> taskList) throws DukeException {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while (line != null) {
                    String[] splitLine = line.split(" \\| ");
                    boolean isDone = splitLine[1].equals("1") ? true : false;
                    System.out.println(isDone);
                    StringProcessor stringProcessor = new StringProcessor(editFileInput(splitLine), taskList);
                    stringProcessor.process(isDone);
                    line = br.readLine();
                }
                br.close();
                fr.close();
            }
        } catch (IOException e) {
            System.out.println (e.toString());
            System.out.println("Sorry, there were some issues opening the file located at: " + FILE_PATH);
        }
    }

    public static void writeToFile(ArrayList<Task> taskList) {
        try {
            File file = new File(FILE_PATH);
            // add directory if it does not exist
            file.getParentFile().mkdirs();
            FileWriter fw;

            if (file.exists()) {
                fw = new FileWriter(file, false);
            } else {
                fw = new FileWriter(file, true);
            }

            for (Task i : taskList) {
                fw.write(i.appendFile() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println (e.toString());
            System.out.println("Sorry, there were some issues writing to the file located at: " + FILE_PATH);
        }
    }

    public static void main(String[] args) throws DukeException {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ \uD83D\uDD34 \uD83D\uDD34 \\\n"
                + "| |_| | |_| |   <  \\__/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello! I'm\n" + logo);
        System.out.println("\nReading tasks from your task list...");

        ArrayList<Task> inputStore = new ArrayList<>();
        readFile(inputStore);

        System.out.println("\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            String[] splitString = userInput.split(" ");

            if (userInput.replaceAll("\\s", "").equals("")) {
                System.out.println("\u263A Please enter something!");
            } else if (splitString[0].equals("bye")) {
                System.out.println("\u263A Bye. Hope to see you again soon!");
                break;
            } else {
                StringProcessor stringProcessor = new StringProcessor(splitString, inputStore);
                stringProcessor.process(false);
            }
            writeToFile(inputStore);
        }
    }
}
