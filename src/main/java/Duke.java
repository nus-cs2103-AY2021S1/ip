import commands.Command;
import exceptions.*;
import tasks.*;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Duke {

    private static ResourceBundle strings;
    private static Scanner scanner;
    private static ArrayList<Task> tasks;

    @SuppressWarnings("unchecked")
    private static void initializeDuke() {
        // read the appropriate string resources
        strings = ResourceBundle.getBundle("resources.StringsBundle", Locale.ENGLISH);
        scanner = new Scanner(System.in);
        tasks = new ArrayList<>();

        //Solution below adapted from https://www.javatpoint.com/serialization-in-java
        try {
            Path dirPath = Paths.get("database");
            File directory = new File(dirPath.normalize().toString());
            if (!directory.exists()) {
                return;
            }

            FileInputStream fileIn = new FileInputStream(dirPath.normalize().toString()+ "/tasks.ser");

            ObjectInputStream in = new ObjectInputStream(fileIn);


            tasks = (ArrayList<Task>) in.readObject();

            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
    }

    private static void printWithDecoration(Object object) {
        String lineDecoration = "\t" + "_".repeat(60);
        System.out.println(lineDecoration);
        System.out.println("\t" + object);
        System.out.println(lineDecoration);
    }

    private static HashMap<String, String> parseParameters(String input) {
        HashMap<String, String> parameters = new HashMap<>();

        if (input.equals("")) {
            return parameters;
        }

        List<String> split = Arrays.asList(input.split("/"));
        parameters.put("argument", split.get(0).strip());

        for (String pair : split.subList(1, split.size())) {
            String key = pair.split(" ")[0];
            parameters.put(key, pair.replace(key, "").strip());
        }

        return parameters;
    }

    public static void main(String[] args) {

        initializeDuke();

        printWithDecoration(strings.getString("output.greeting"));

        String input, inputMainCommand = "", output;
        HashMap<String, String> parameters;

        while (!inputMainCommand.equals(strings.getString("command.bye"))) {
            input = scanner.nextLine();
            inputMainCommand = input.split(" ")[0].strip();
            parameters = parseParameters(input.replace(inputMainCommand, "").strip());

            try {
                output = Command.createCommand(inputMainCommand)
                        .execute(parameters, tasks);
                printWithDecoration(output);
            } catch (DukeException e) {
                printWithDecoration(e.getMessage());
            }

        }

    }

}
