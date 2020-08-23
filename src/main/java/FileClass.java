import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class FileClass {
    public static ArrayList<Task> readFileContents(String filePath) throws FileNotFoundException {
        ArrayList<Task> list = new ArrayList<>();
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String taskString = s.nextLine();
            if (!taskString.isEmpty()) {
                if (Character.toString(taskString.charAt(1)).equals("T")) {
                    if (Character.toString(taskString.charAt(4)).equals("0")) {
                        list.add(new Todo(taskString.substring(6).trim()));
                    } else {
                        list.add(new Todo(taskString.substring(6).trim(), true));
                    }
                } else if (Character.toString(taskString.charAt(1)).equals("D")) {
                    String description = taskString.substring(6).split("/by")[0].trim();
                    String dateString = taskString.substring(6).split("/by")[1].trim();
                    if (Character.toString(taskString.charAt(4)).equals("0")) { //task is marked as not done yet
                        if (dateString.contains(" ")) { //2020-02-03 1800
                            String date = dateString.split(" ")[0];
                            String time = dateString.split(" ")[1].trim();
                            LocalDate dateObj = LocalDate.parse(date);
                            list.add(new Deadline(description, dateObj, time));
                        } else { //2020-02-03
                            LocalDate dateObj = LocalDate.parse(dateString);
                            list.add(new Deadline(description, dateObj));
                        }
                    } else { //task is marked as done
                        if (dateString.contains(" ")) { //2020-02-03 1800
                            String date = dateString.split(" ")[0];
                            String time = dateString.split(" ")[1].trim();
                            LocalDate dateObj = LocalDate.parse(date);
                            list.add(new Deadline(description, true, dateObj, time));
                        } else { //2020-02-03
                            LocalDate dateObj = LocalDate.parse(dateString);
                            list.add(new Deadline(description, true, dateObj));
                        }
                    }
                } else {
                    String description = taskString.substring(6).split("/at")[0].trim();
                    String dateString = taskString.substring(6).split("/at")[1].trim();
                    if (Character.toString(taskString.charAt(4)).equals("0")) { //task is marked as not done yet
                        if (dateString.contains(" ")) { //2020-02-03 1800
                            String date = dateString.split(" ")[0];
                            String time = dateString.split(" ")[1].trim();
                            LocalDate dateObj = LocalDate.parse(date);
                            list.add(new Event(description, dateObj, time));
                        } else { //2020-02-03
                            LocalDate dateObj = LocalDate.parse(dateString);
                            list.add(new Event(description, dateObj));
                        }
                    } else { //task is marked as done
                        if (dateString.contains(" ")) { //2020-02-03 1800
                            String date = dateString.split(" ")[0];
                            String time = dateString.split(" ")[1].trim();
                            LocalDate dateObj = LocalDate.parse(date);
                            list.add(new Event(description, true, dateObj, time));
                        } else { //2020-02-03
                            LocalDate dateObj = LocalDate.parse(dateString);
                            list.add(new Event(description, true, dateObj));
                        }
                    }
                }
            } else {
                continue;
            }
        }
        return list;
    }

    public static void writeListToFile(String filePath, ArrayList<Task> listToAdd) throws IOException {
        FileWriter resetfw = new FileWriter(filePath);
        resetfw.write("");
        resetfw.close();

        FileWriter fw = new FileWriter(filePath, true);
        for (int i = 0; i < listToAdd.size(); i++) {
            String task = listToAdd.get(i).toString();
            task = task.replace("(", "/");
            task = task.replace(")", "");
            task = task.replace(":", "");
            fw.write("\n" + task);
        }
        fw.close();
    }
}
