import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class StringProcessor {

//    StringProcessor(String rawInput) {
//        this.rawInput = rawInput;
//    }

    public static Task stringProcessor(String rawInput) throws InvalidInputException{
        String[] splitString = rawInput.split(" ");
        int splitStringLength = splitString.length;


        if (splitString[0].equals("todo")) {

            if (splitString.length == 1) {
                throw new InvalidInputException("Description of To Do cannot be empty");
            } else {
                String[] taskStringArray = Arrays.copyOfRange(splitString, 1, splitStringLength);
                return new Todo(String.join(" ", taskStringArray));
            }

        } else if (splitString[0].equals("deadline")) {

            if (splitString.length == 1) {
                throw new InvalidInputException("Description of Deadline cannot be empty");
            } else {
                String[] taskStringArray = Arrays.copyOfRange(splitString, 1, splitStringLength);
                String toSplit = String.join(" ", taskStringArray);
                int indexOfSlash = toSplit.indexOf("/");
                if (indexOfSlash == -1) {
                    throw new InvalidInputException("Date/time for Deadline cannot be empty/not recognised");
                } else {
                    int indexOfNextWord = toSplit.indexOf(" ", indexOfSlash);
                    return new Deadline(toSplit.substring(0, indexOfSlash - 1),
                            toSplit.substring(indexOfSlash + 1, indexOfNextWord),
                            toSplit.substring(indexOfNextWord + 1));
                }
            }

        } else if (splitString[0].equals("event")) {
            if (splitString.length == 1) {
                throw new InvalidInputException("Description of Event cannot be empty");
            } else {
                String[] taskStringArray = Arrays.copyOfRange(splitString, 1, splitStringLength);
                String toSplit = String.join(" ", taskStringArray);
                int indexOfSlash = toSplit.indexOf("/");
                if (indexOfSlash == -1) {
                    throw new InvalidInputException("Date/time for Event cannot be empty/not recognised");
                } else {
                    int indexOfNextWord = toSplit.indexOf(" ", indexOfSlash);
                    return new Event(toSplit.substring(0, indexOfSlash - 1),
                            toSplit.substring(indexOfSlash + 1, indexOfNextWord),
                            toSplit.substring(indexOfNextWord + 1));
                }
            }
        } else {
            throw new InvalidInputException("Unrecognized task");
        }
    }

    public static Task stringDataProcessor(String rawInput) throws InvalidInputException{
        String[] splitString = rawInput.split("/");

        int splitStringLength = splitString.length;

        if (splitString[0].equals("todo")) {

            if (splitString.length == 2) {
                return new Todo(String.join(" ", splitString[1]));
            } else if (splitString.length == 3) {
                return new Todo(splitString[1], Boolean.parseBoolean(splitString[2]));
            } else {
                // potential error msg here?
                throw new InvalidInputException("ERROR IN DB");
            }

        } else if (splitString[0].equals("deadline")) {

           if (splitString.length == 4) {
                return new Deadline(splitString[1],
                        splitString[2],
                        splitString[3]);
           } else if (splitString.length == 5) {
                return new Deadline(splitString[1],
                        splitString[2],
                        splitString[3],
                        Boolean.parseBoolean(splitString[4]));
           } else {
                throw new InvalidInputException("ERROR IN DB");
           }

        } else if (splitString[0].equals("event")) {

            if (splitString.length < 4) {
                throw new InvalidInputException("Description of event cannot be empty");
            } else if (splitString.length == 4) {
                return new Event(splitString[1],
                        splitString[2],
                        splitString[3]);
            } else if (splitString.length == 5) {
                return new Event(splitString[1],
                        splitString[2],
                        splitString[3],
                        Boolean.parseBoolean(splitString[4]));
            } else {
                throw new InvalidInputException("ERROR IN DB");
            }
        } else {
            throw new InvalidInputException("Unrecognized task");
        }
    }

    public static ArrayList<Task> readFileContents(File f, ArrayList<Task> list) throws FileNotFoundException {
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            try {
                list.add(StringProcessor.stringDataProcessor(s.nextLine()));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ERROR IN DATABASE!!!!!!!!!!!!!!!!!!!!!");
            }
        }
        s.close();
        return list;
    }

    public static void writeToFile(File f, ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(f);
        String stringToWrite = "";
        for(Task t : list) {
            stringToWrite = stringToWrite + t.toDataString() + System.lineSeparator();
        }
        fw.write(stringToWrite);
        fw.close();
    }



}
