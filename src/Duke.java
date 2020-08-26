import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.io.File;

public class Duke {
    public static void displayMessage(String s) {
        System.out.println("\t---------------------------\n\t" +
                s +
                "\n\t---------------------------"
        );
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List list1 = List.startList();
        File f = new File("data/duke.txt");
        if (!f.exists()) {
            displayMessage("Creating new data file: duke.txt");
            f.getParentFile().mkdirs();
            try {
                f.createNewFile();
            } catch (IOException e) {
                displayMessage(e.getMessage());
            }
        } else {
            displayMessage("duke.txt file found, loading data");
            try {
                list1.populateList(f);
            } catch (IndexOutOfBoundsException e) {
                displayMessage("Sorry your duke.txt file is corrupt!");
            } catch (Exception e) {
                displayMessage(e.getMessage());
            }
        }


        displayMessage("Hello! I'm Duke\n\tWhat can I do for you?");
        String input;
        boolean cont = true;
        while (cont) {
            input = sc.nextLine();

            String[] arr = input.split("\\s", 2);
            String pref = arr[0];
            String rest;

            String addedMsg;

            try {
                switch (pref) {
                    case "bye":
                        displayMessage("Bye. Hope to see you again soon!");
                        cont = false;
                        break;

                    case "list":
                        String listString = list1.toString();
                        displayMessage(listString);
                        break;

                    case "done":
                        if (arr.length == 1) throw new MissingDescriptionException("done");

                        rest = arr[1];
                        int n;

                        try {
                            n = Integer.parseInt(rest);
                        } catch (Exception e) {
                            throw new InvalidTaskNumberException();
                        }

                        displayMessage(list1.markAsDone(n));
                        writeToFile("data/duke.txt", list1.listToTxt());
                        break;

                    case "delete":
                        if (arr.length == 1) throw new MissingDescriptionException("delete");

                        rest = arr[1];
                        int m;

                        try {
                            m = Integer.parseInt(rest);
                        } catch (Exception e) {
                            throw new InvalidTaskNumberException();
                        }

                        displayMessage(list1.remove(m));
                        writeToFile("data/duke.txt", list1.listToTxt());
                        break;

                    case "todo":
                        if (arr.length == 1) throw new MissingDescriptionException("todo");

                        rest = arr[1];
                        addedMsg = list1.addToList(new Todo(rest));
                        displayMessage(addedMsg);
                        writeToFile("data/duke.txt", list1.listToTxt());
                        break;

                    case "deadline":
                        if (arr.length == 1) throw new MissingDescriptionException("deadline");

                        rest = arr[1];
                        String[] arr1 = rest.split("\\s/by\\s", 2);

                        if (arr1.length == 1) throw new MissingDurationException("deadline");

                        addedMsg = list1.addToList(new Deadline(arr1[0], arr1[1]));
                        displayMessage(addedMsg);
                        writeToFile("data/duke.txt", list1.listToTxt());
                        break;

                    case "event":
                        if (arr.length == 1) throw new MissingDescriptionException("event");

                        rest = arr[1];
                        String[] arr2 = rest.split("\\s/at\\s", 2);

                        if (arr2.length == 1) throw new MissingDurationException("event");

                        addedMsg = list1.addToList(new Event(arr2[0], arr2[1]));
                        displayMessage(addedMsg);
                        writeToFile("data/duke.txt", list1.listToTxt());
                        break;

                    default:
                        throw new InvalidInputException();

                }

            } catch (DateTimeParseException e) {
                displayMessage("Please enter a valid date/time in the format: yyyy-MM-ddTHH:mm");
            } catch (Exception e) {
                displayMessage(e.getMessage());
            }
        }
    }
}
