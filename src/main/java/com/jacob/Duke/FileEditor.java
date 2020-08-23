package com.jacob.Duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileEditor {
    //get current directory
    public static String getCurrentDirectory() {
        return System.getProperty("user.dir");
    }

    //Access the current list, creates the folder and files if they do not exist
    public static String accessTaskListInFileSystem(String current) {
        java.nio.file.Path directoryPath = java.nio.file.Paths.get(current, "data");
        boolean directoryExists = java.nio.file.Files.exists(directoryPath);


        java.nio.file.Path filePath = java.nio.file.Paths.get(current, "data", "duke.txt");
        boolean fileExists = java.nio.file.Files.exists(filePath);
        try {
            if (!directoryExists) {
                Files.createDirectories(directoryPath);
            }
            if (!fileExists) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Printing filepath after accessing it: " + filePath.toString());
        return filePath.toString();
    }

    static StringBuffer stringBufferOfData = new StringBuffer();
    static String filename = null;


    public static List<Task> readFile(String filename, CommandHandlers comHandlers, List<Task> taskList) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(filename))));
            int count = 0;
            for (String line; (line = reader.readLine()) != null; ) {
                //System.out.println(line);

                //parse the line here and add to taskList
                comHandlers.handleFileCommands(line, taskList, count++);

                //add to the buffer
                stringBufferOfData.append(line).append("\r\n");
            }
            System.out.println(taskList);
            reader.close();//this is used to release the scanner from file
            return taskList;
        } catch (IOException e) {
            System.out.println("The file " + filename + " could not be found or opened! " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void writeToFile(String filename) {
        try {
            BufferedWriter bfWriter = new BufferedWriter(new FileWriter(filename));
            bfWriter.write(stringBufferOfData.toString());//writes the edited string buffer to the new file
            bfWriter.close();//closes the file

        } catch (Exception e) {//if an exception occurs
            System.out.println("Error occurred while attempting to write to file: " + e.getMessage());
        }
    }

    public static void replacement(String lineToEdit, String replacementText) {
        //System.out.println(sb);//used for debugging to check that my string buffer has correct contents and spacing


        //Find the original text

        int startIndex = stringBufferOfData.indexOf(lineToEdit);
        int endIndex = startIndex + lineToEdit.length();

        //if exists, then replace

        //else append to the end of the String buffer


        stringBufferOfData.replace(startIndex, endIndex, replacementText);//this is where the actual replacement of the text happens

    }

    public static void appendText(String replacementText) {
        //add a separator for the newline before appending
        String newLine = System.getProperty("line.separator");
        replacementText = replacementText + newLine;
        stringBufferOfData.append(replacementText);
    }

    public static void removeText(String lineToEdit) {
        String newLine = System.getProperty("line.separator");
        lineToEdit = lineToEdit + newLine;
        replacement(lineToEdit, "");
    }

    // test driver
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        accessTaskListInFileSystem(getCurrentDirectory());
        System.out.println(getCurrentDirectory());

        System.out.println("Please enter your files name and path i.e C:\\test.txt: ");//prompt for file name
        String filename = sc.nextLine();//read in the file name

        //create dummy command handler and task list
        CommandHandlers commandHandlers = new CommandHandlers();
        List<Task> taskList = new ArrayList<>();
        readFile(filename,commandHandlers,taskList); //call the method to read the file with the files name

        System.out.println(stringBufferOfData);

        System.out.println("Please enter the contents of a line you would like to edit: ");//prompt for a line in file to edit
        String lineToEdit = sc.nextLine();//read the line to edit

        System.out.println("Please enter the the replacement text: ");//prompt for a line in file to replace
        String replacementText = sc.nextLine();//read the line to replace

        replacement(lineToEdit, replacementText);//call method to get text to replace, replacement text and output replaced String buffer

        //add text
        System.out.println("Please enter the the append text: ");//prompt for a line in file to replace
        String appendText = sc.nextLine();
        appendText(appendText);

        //complete writing to file
        writeToFile(filename);
    }
}
