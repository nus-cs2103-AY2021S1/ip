package storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.IntStream;

import mugexception.MugException;

public class UndoStorage {
    /** mug.txt filepath */
    private final String mugFilepath;
    /** undo.txt filepath */
    private final String undoFilepath;

    /**
     * Create Undo Storage Object for undo command.
     *
     * @param mugFilepath mug.txt filepath.
     * @param undoFilepath undo.txt filepath.
     */
    public UndoStorage(String mugFilepath, String undoFilepath) {
        this.mugFilepath = mugFilepath;
        this.undoFilepath = undoFilepath;
    }

    /**
     * Undo action.
     *
     * @throws MugException If do not have anything to undo or fail to read the file.
     */
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
                throw new MugException("Something went wrong. Mug fail to undo:_:");
            }
        } catch (NoSuchElementException ex) {
            String err = "Mug do not have anything to undo. TT\n(Note: Mug can only undo the most recent command.)";
            throw new MugException(err);
        } catch (IOException ex) {
            throw new MugException("Something went wrong. Mug fail to undo:_:");
        }
    }

    /**
     * Undo delete Command
     *
     * @throws MugException If something wrong with file
     */
    private void undoDelete() throws MugException {
        try {
            writeFile(this.mugFilepath, this.undoFilepath, true, false);
            clearFile(this.undoFilepath);
        } catch (IOException ex) {
            throw new MugException("Something went wrong. Mug fail to undo:_:");
        }
    }

    /**
     * Undo done Command
     *
     * @throws MugException If something wrong with file
     */
    private void undoDone() throws MugException {
        try {
            writeFile(this.mugFilepath, this.undoFilepath, false, true);
            clearFile(this.undoFilepath);
        } catch (IOException ex) {
            throw new MugException("Something went wrong. Mug fail to undo:_:");
        }
    }

    /**
     * Undo add task related Command
     *
     * @throws MugException If something wrong with file
     */
    private void undoAdd() throws MugException {
        try {
            writeFile(this.mugFilepath, this.undoFilepath, false, false);
            clearFile(this.undoFilepath);
        } catch (IOException ex) {
            throw new MugException("Something went wrong. Mug fail to undo:_:");
        }
    }

    /**
     * Clears the content of file.
     *
     * @param filepath The filepath of the file to clear.
     * @throws IOException If fail to write file.
     */
    private void clearFile(String filepath) throws IOException {
        try {
            FileWriter undoFw = new FileWriter(filepath);
            BufferedWriter undoBw = new BufferedWriter(undoFw);
            PrintWriter undoPw = new PrintWriter(undoBw);
            undoPw.flush();
            undoPw.close();
        } catch (IOException ex) {
            throw new IOException();
        }
    }

    /**
     * Writes local storage file when undo.
     *
     * @param mugFilepath mug.txt filepath.
     * @param undoFilepath undo.txt filepath.
     * @param isDelete Undo on delete Command.
     * @param isDone Undo on done Command.
     * @throws IOException If fail to write file.
     */
    private void writeFile(String mugFilepath, String undoFilepath,
                           boolean isDelete, boolean isDone) throws IOException {
        String tempFile = "temp.txt";
        File oldFile = new File(mugFilepath);
        File newFile = new File(tempFile);
        try {
            FileWriter mugFw = new FileWriter(tempFile, true);
            BufferedWriter mugBw = new BufferedWriter(mugFw);
            PrintWriter mugPw = new PrintWriter(mugBw);
            // read undo.txt
            Scanner undoSc = new Scanner(new File(undoFilepath));
            undoSc.useDelimiter("[\n]");
            undoSc.next();
            int lineIndex = Integer.parseInt(undoSc.next());
            // read mug.txt
            Scanner mugSc = new Scanner(oldFile);
            mugSc.useDelimiter("[\n]");
            // add in task
            IntStream.range(1, lineIndex)
                    .forEach(line -> mugPw.println(mugSc.next()));
            if (isDelete || isDone) {
                // add in changed task
                mugPw.println(undoSc.next());
                if (isDone) {
                    mugSc.next();
                }
                // continue the rest of the task
                while (mugSc.hasNext()) {
                    mugPw.println(mugSc.next());
                }
            }
            // close mug file
            mugPw.flush();
            mugPw.close();
            oldFile.delete();
            File renameFile = new File(mugFilepath);
            newFile.renameTo(renameFile);
        } catch (IOException ex) {
            throw new IOException();
        }
    }
}
