package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    File save;
    FileWriter writer;

    public Storage(String filePath) {
        this.save = new File(filePath);
    }

    public void initialiseStorage() throws BobStorageInitilisationException {
        File directory = new File(this.save.getParent());
        directory.mkdirs();
        try {
            save.createNewFile();
        } catch (IOException e) {
            throw new BobStorageInitilisationException();
        }

        try {
            writer = new FileWriter(save, true);
        } catch (IOException e) {
            throw new BobStorageInitilisationException();
        }
    }

    public void loadSave(TaskList tasks) throws BobFileNotFoundException {
        Scanner sc = null;
        try {
            sc = new Scanner(save);
        } catch (FileNotFoundException e) {
            throw new BobFileNotFoundException();
        }
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            if (!str.equals("")) {
                char c = str.charAt(0);
                if (c == 'T') {
                    String bool = str.substring(4, 5);
                    String description = str.substring(8);
                    tasks.add(new Todo(bool.equals("1") ? true : false, description));
                } else if (c == 'D') {
                    String bool = str.substring(4, 5);
                    String description = str.substring(8, str.indexOf("|", 8) - 1);
                    int i = str.indexOf("|", 8);
                    String deadline = str.substring(i + 2);
                    tasks.add(new Deadline(bool.equals("1") ? true : false, description, deadline));
                } else if (c == 'E') {
                    String bool = str.substring(4, 5);
                    String description = str.substring(8, str.indexOf("|", 8) - 1);
                    int i = str.indexOf("|", 8);
                    String period = str.substring(i + 2);
                    tasks.add(new Event(bool.equals("1") ? true : false, description, period));
                }
            }
        }
    }

    public void updateSave(TaskList tasks) throws BobIOException, BobIndexOutOfBoundsException {
        FileWriter deleter = null;
        try {
            deleter = new FileWriter(save);
        } catch (IOException e) {
            throw new BobIOException();
        }

        for (int i = 1; i < tasks.size()+1; i++) {
            Task task = tasks.get(i);
            try {
                deleter.append(task.saveFormat() + System.lineSeparator());
            } catch (IOException e) {
                throw new BobIOException();
            }
        }
        try {
            deleter.close();
        } catch (IOException e) {
            throw new BobIOException();
        }
    }

    public void appendToStorage(String data) throws BobIOException {
        try {
            writer.append(data + System.lineSeparator());
        } catch (IOException e) {
            throw new BobIOException();
        }
    }

    public void flushWriter() throws BobIOException {
        try {
            writer.flush();
        } catch (IOException e) {
            throw new BobIOException();
        }
    }

    public void closeWriter() throws BobIOException {
        try {
            writer.close();
        } catch (IOException e) {
            throw new BobIOException();
        }
    }

}
