import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import Parser.InputManager;
import Tasks.TaskManager;
import UI.UserInterface;
import javafx.application.Platform;

/**
 * Represents the main method of the MattBot program. The bot is able to track added todo tasks into the list and it
 * is stored locally in a save txt file called DukeTodoSave.txt located in the root of the project directory.
 * If this file is not present, the program will automatically create this text file in the root.
 */
public class Duke {
    static String getResponse(String input) {
        UserInterface UI = new UserInterface();
        UI.input(input);
        if (input.equals("bye")) {
            Platform.exit();
        }
        return UI.parse2();
    }
    /**
     * Represents the main method of the program. The start of the mattbot program.
     * @param args args.
     */
    public static void main(String[] args) {
        String fileDir = "./DukeTodoSave.txt";
        InputManager.fileDir(fileDir);
        File save = new File(fileDir);
        if (!save.exists()) { // create the text file
            System.out.println("Save file does not exist, creating it now!");
            try {
                save.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating the save file!");
                System.out.println(e);
            }
        }
        TaskManager.load(save);
        Scanner sc = new Scanner(System.in);
        UserInterface UI = new UserInterface();
        while (UI.getStop() == false) {
            UI.input(sc.nextLine());
            UI.parse();
        }
        sc.close();
    }
}
