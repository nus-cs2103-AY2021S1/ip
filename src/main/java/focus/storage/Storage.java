package focus.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import focus.task.Task;

/** Allows Focus to load and save data in user's files. */
public class Storage {
    /** Represents the current user's directory. */
    private static final String USER_DIRECTORY = "user.dir";
    /** Represents the path for the settings to be saved at. */
    private static String settingsPath;
    /** User's name. */
    private static String userName;
    /** Number of days user has set. */
    private static int numberOfDays;
    /** Represents the path for the task list to be saved at. */
    private final String focusPath;

    /** Creates a storage to allow loading and saving of tasks. */
    public Storage() {
        this.focusPath = System.getProperty(USER_DIRECTORY) + "/data/focus.txt";
        settingsPath = System.getProperty(USER_DIRECTORY) + "/data/settings.txt";
    }

    /**
     * Gets the number of days.
     *
     * @return Number of days.
     */
    public int getNumberOfDays() {
        return numberOfDays;
    }

    /**
     * Gets the username.
     *
     * @return User's name.
     */
    public static String getUserName() {
        return userName;
    }

    /**
     * Sets the user's name.
     *
     * @param userInput User's input.
     */
    public static void setUserName(String userInput) {
        updateName(userInput);
    }

    /** Creates a folder to store text file. If present, it will not create. */
    public static void createFolder() {
        String folderPath = System.getProperty(USER_DIRECTORY) + "/data";
        File folder = new File(folderPath);
        boolean isSuccessful = folder.mkdir();
        if (isSuccessful) {
            System.out.println("Folder created successfully.");
        } else {
            System.out.println("Folder already exists.");
        }
    }

    /**
     * Creates or retrieves the text file from user's files.
     *
     * @return True if user has an existing text file,
     * false if user does not have an existing text file.
     */
    public boolean retrieveFocusTextFile() {
        File data = null;
        boolean hasCreatedTextFile = false;
        boolean hasTextFile = false;
        try {
            data = new File(focusPath);
            hasCreatedTextFile = data.createNewFile();
        } catch (IOException e) { // creation or retrieving data has errors
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        if (hasCreatedTextFile) {
            System.out.println("Text file created: " + data.getName());
        } else {
            hasTextFile = true;
            System.out.println("Text file already exists.");
        }
        return hasTextFile;
    }

    /**
     * Loads the data from user's text file if it already exists.
     *
     * @return Arraylist of tasks in string format.
     */
    public ArrayList<String> loadData() {
        ArrayList<String> taskList = new ArrayList<>();
        try {
            File data = new File(focusPath);
            Scanner sc = new Scanner(data);
            String task;
            while (sc.hasNextLine()) {
                task = sc.nextLine();
                taskList.add(task);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return taskList;
    }

    /**
     * Adds the data to user's text file.
     *
     * @param item Item to be added to text file.
     */
    public void addData(Task item) {
        try {
            FileWriter fileWriter = new FileWriter(focusPath, true);
            String task = item.taskToText() + "\n";
            assert !task.isEmpty() : "Task should not be blank here.";
            fileWriter.write(task);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Updates the data in user's text file.
     *
     * @param tasks Task list to be updated.
     */
    public void updateData(ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(focusPath);
            for (Task item : tasks) {
                String task = item.taskToText() + "\n";
                assert !task.isEmpty() : "Task should not be blank here.";
                fileWriter.write(task);
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Creates or retrieves the settings file from user's files.
     *
     * @return True if user has an existing settings file,
     * false if user does not have an existing settings file.
     */
    public boolean retrieveSettingsTextFile() {
        File data = null;
        boolean hasCreatedTextFile = false;
        boolean hasTextFile = false;
        try {
            data = new File(settingsPath);
            hasCreatedTextFile = data.createNewFile();
        } catch (IOException e) { // creation or retrieving data has errors
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        if (hasCreatedTextFile) {
            System.out.println("Settings file created: " + data.getName());
            try {
                FileWriter fileWriter = new FileWriter(settingsPath);
                fileWriter.write("name|7"); // default name and number of days
                userName = "name";
                numberOfDays = 7;
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else {
            hasTextFile = true;
            System.out.println("Settings file already exists.");
        }
        return hasTextFile;
    }

    /** Loads the data from user's settings file if it already exists. */
    public void loadSettings() {
        try {
            File data = new File(settingsPath);
            Scanner sc = new Scanner(data);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] split = line.split("\\|");
                System.out.println(split[0]);
                System.out.println(split[1]);
                userName = split[0];
                numberOfDays = Integer.parseInt(split[1]);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /** Updates the name in user's settings file. */
    public static void updateName(String username) {
        try {
            FileWriter fileWriter = new FileWriter(settingsPath);
            fileWriter.write(username + "|" + numberOfDays);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        userName = username;
    }

    /** Updates the number of days in user's settings file. */
    public void updateNumberOfDays(String numberOfDays) {
        try {
            FileWriter fileWriter = new FileWriter(settingsPath);
            fileWriter.write(userName + "|" + numberOfDays);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        Storage.numberOfDays = Integer.parseInt(numberOfDays);
    }
}
