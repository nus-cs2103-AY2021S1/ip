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

/**
 * Undo Action in storage
 */
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
            Action undoCommand = Action.valueOf(sc.next());
            assert(undoCommand == Action.DELETE
                    || undoCommand == Action.ADD
                    || undoCommand == Action.DONE);
            switch (undoCommand) {
            case DELETE:
                undoDelete();
                break;
            case ADD:
                undoAdd();
                break;
            case DONE:
                undoDone();
                break;
            default:
                throw new MugException("Something went wrong. Mug fail to undo:_:");
            }
        } catch (NoSuchElementException ex) {
            throw new MugException("Mug do not have anything to undo. TT");
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
            clearUndo(this.undoFilepath);
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
            clearUndo(this.undoFilepath);
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
            clearUndo(this.undoFilepath);
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
    private void clearUndo(String filepath) throws IOException {
        String tempFile = "clearTemp.txt";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);

        try {
            FileWriter undoFw = new FileWriter(tempFile, true);
            BufferedWriter undoBw = new BufferedWriter(undoFw);
            PrintWriter undoPw = new PrintWriter(undoBw);
            // read undo.txt
            Scanner undoSc = new Scanner(new File(filepath));
            undoSc.useDelimiter("[\n]");
            // remove the first three
            undoSc.next();
            undoSc.next();
            undoSc.next();
            // copy back the rest
            while (undoSc.hasNext()) {
                undoPw.println(undoSc.next());
            }
            undoSc.close();
            undoPw.flush();
            undoPw.close();
            // rename file
            oldFile.delete();
            File renameFile = new File(filepath);
            newFile.renameTo(renameFile);
        } catch (IOException ex) {
            throw new IOException();
        }
    }


    /**
     * Writes local storage file when undo.
     *
     * @param mugFilepath mug.txt filepath.
     * @param undoFilepath undo.txt filepath.
     * @param isDelete delete Command related.
     * @param isDone done Command related.
     * @throws IOException If fail to write file.
     */
    private void writeFile(String mugFilepath, String undoFilepath,
                           boolean isDelete, boolean isDone) throws IOException, MugException {
        String tempFile = "temp.txt";
        File oldFile = new File(mugFilepath);
        File newFile = new File(tempFile);
        try {
            // write mug file
            FileWriter mugFw = new FileWriter(tempFile, true);
            BufferedWriter mugBw = new BufferedWriter(mugFw);
            PrintWriter mugPw = new PrintWriter(mugBw);
            // read undo.txt
            Scanner undoSc = new Scanner(new File(undoFilepath));
            undoSc.useDelimiter("[\n]");
            undoSc.next();
            int lineIndex = Integer.parseInt(undoSc.next());
            assert(lineIndex >= 0);
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
            // close scanner
            undoSc.close();
            mugSc.close();
            // close mug file
            mugPw.flush();
            mugPw.close();
            oldFile.delete();
            File renameFile = new File(mugFilepath);
            newFile.renameTo(renameFile);
        } catch (NumberFormatException ex) {
            throw new MugException("Something went wrong with your undo.txt file!!!");
        } catch (IOException ex) {
            throw new IOException();
        }
    }
}
