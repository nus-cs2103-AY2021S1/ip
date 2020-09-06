package storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import mugexception.MugException;

public class UndoStorage {
    private String mugFilepath;
    private String undoFilepath;
    public UndoStorage(String mugFilepath, String undoFilepath) {
        this.mugFilepath = mugFilepath;
        this.undoFilepath = undoFilepath;
    }
    public void undo() throws MugException {
        try {
            Scanner sc = new Scanner(new File(this.undoFilepath));
            String undoCommand = sc.next();
            switch (undoCommand) {
            case "delete":
                undoDelete();
                break;
            case "add":
                undoAdd();
                break;
            case "done":
                undoDone();
                break;
            default:
                throw new MugException("Nothing to undo");
            }
        } catch (IOException ex) {
            throw new MugException("Error!!! Error!!!");
        }
    }

    public void undoDelete() throws MugException {
        String tempFile = "temp.txt";
        File oldFile = new File(this.mugFilepath);
        File newFile = new File(tempFile);

        try {
            FileWriter mugFw = new FileWriter(tempFile, true);
            BufferedWriter mugBw = new BufferedWriter(mugFw);
            PrintWriter mugPw = new PrintWriter(mugBw);
            // read undo.txt
            Scanner undoSc = new Scanner(new File(this.undoFilepath));
            undoSc.useDelimiter("[\n]");
            undoSc.next();
            int lineIndex = Integer.parseInt(undoSc.next());
            // read mug.txt
            Scanner mugSc = new Scanner(oldFile);
            mugSc.useDelimiter("[\n]");
            // add in task
            for (int i = 1; i < lineIndex; i++) {
                mugPw.println(mugSc.next());
            }
            // add in delete task
            mugPw.println(undoSc.next());
            // continue the rest of the task
            while (mugSc.hasNext()) {
                mugPw.println(mugSc.next());
            }
            //clear undo.txt
            FileWriter undoFw = new FileWriter(this.undoFilepath);
            BufferedWriter undoBw = new BufferedWriter(undoFw);
            PrintWriter undoPw = new PrintWriter(undoBw);
            undoPw.flush();
            undoPw.close();
            // close mug file
            mugPw.flush();
            mugPw.close();
            oldFile.delete();
            File renameFile = new File(this.mugFilepath);
            newFile.renameTo(renameFile);
        } catch (IOException ex) {
            throw new MugException("Something went wrong. Mug fail to add the Tasks.Task :_:");
        }
    }
    public void undoDone() throws MugException {
        String tempFile = "temp.txt";
        File oldFile = new File(this.mugFilepath);
        File newFile = new File(tempFile);

        try {
            FileWriter mugFw = new FileWriter(tempFile, true);
            BufferedWriter mugBw = new BufferedWriter(mugFw);
            PrintWriter mugPw = new PrintWriter(mugBw);
            // read undo.txt
            Scanner undoSc = new Scanner(new File(this.undoFilepath));
            undoSc.useDelimiter("[\n]");
            undoSc.next();
            int lineIndex = Integer.parseInt(undoSc.next());
            // read mug.txt
            Scanner mugSc = new Scanner(oldFile);
            mugSc.useDelimiter("[\n]");
            // add in task
            for (int i = 1; i < lineIndex; i++) {
                mugPw.println(mugSc.next());
            }
            // Change back to original task
            mugPw.println(undoSc.next());
            mugSc.next();
            // continue the rest of the task
            while (mugSc.hasNext()) {
                mugPw.println(mugSc.next());
            }
            //clear undo.txt
            FileWriter undoFw = new FileWriter(this.undoFilepath);
            BufferedWriter undoBw = new BufferedWriter(undoFw);
            PrintWriter undoPw = new PrintWriter(undoBw);
            undoPw.flush();
            undoPw.close();
            // close mug file
            mugPw.flush();
            mugPw.close();
            oldFile.delete();
            File renameFile = new File(this.mugFilepath);
            newFile.renameTo(renameFile);
        } catch (IOException ex) {
            throw new MugException("Something went wrong. Mug fail to add the Tasks.Task :_:");
        }
    }
    public void undoAdd() throws MugException {
        String tempFile = "temp.txt";
        File oldFile = new File(this.mugFilepath);
        File newFile = new File(tempFile);

        try {
            FileWriter mugFw = new FileWriter(tempFile, true);
            BufferedWriter mugBw = new BufferedWriter(mugFw);
            PrintWriter mugPw = new PrintWriter(mugBw);
            // read undo.txt
            Scanner undoSc = new Scanner(new File(this.undoFilepath));
            undoSc.useDelimiter("[\n]");
            undoSc.next();
            int lineIndex = Integer.parseInt(undoSc.next());
            // read mug.txt
            Scanner mugSc = new Scanner(oldFile);
            mugSc.useDelimiter("[\n]");
            // add in task
            for (int i = 1; i < lineIndex; i++) {
                mugPw.println(mugSc.next());
            }
            //clear undo.txt
            FileWriter undoFw = new FileWriter(this.undoFilepath);
            BufferedWriter undoBw = new BufferedWriter(undoFw);
            PrintWriter undoPw = new PrintWriter(undoBw);
            undoPw.flush();
            undoPw.close();
            // close mug file
            mugPw.flush();
            mugPw.close();
            oldFile.delete();
            File renameFile = new File(this.mugFilepath);
            newFile.renameTo(renameFile);
        } catch (IOException ex) {
            throw new MugException("Something went wrong. Mug fail to add the Tasks.Task :_:");
        }
    }
}
