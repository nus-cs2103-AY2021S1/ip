package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Optional;

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
            FileReader fr = new FileReader(this.undoFilepath);
            BufferedReader br = new BufferedReader(fr);
            Optional<String> undoCommand = Optional.ofNullable(br.readLine());
            br.close();
            Action parsedCommand = Action.valueOf(undoCommand.get());
            assert(parsedCommand == Action.DELETE
                    || parsedCommand == Action.ADD
                    || parsedCommand == Action.DONE);
            switch (parsedCommand) {
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
            // writer
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            // undo.txt reader
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);
            // remove the first three
            br.readLine();
            br.readLine();
            br.readLine();
            // copy back the rest
            String line = br.readLine();
            while (Optional.ofNullable(line).isPresent()) {
                pw.println(line);
                line = br.readLine();
            }
            br.close();
            pw.flush();
            pw.close();
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
        String tempFile = "writeTemp.txt";
        File oldFile = new File(mugFilepath);
        File newFile = new File(tempFile);
        try {
            // mug.txt writer
            FileWriter mugFw = new FileWriter(tempFile, true);
            BufferedWriter mugBw = new BufferedWriter(mugFw);
            PrintWriter mugPw = new PrintWriter(mugBw);
            // undo.txt reader
            FileReader undoFr = new FileReader(undoFilepath);
            BufferedReader undoBr = new BufferedReader(undoFr);
            undoBr.readLine();
            String taskId = undoBr.readLine();
            int lineIndex = Integer.parseInt(taskId.trim());
            assert(lineIndex > 0);
            // mug.txt reader
            FileReader mugFr = new FileReader(mugFilepath);
            BufferedReader mugBr = new BufferedReader(mugFr);
            // add in task
            for (int i = 1; i < lineIndex; i++) {
                mugPw.println(mugBr.readLine());
            }
            if (isDelete || isDone) {
                // add in changed task
                mugPw.println(undoBr.readLine());
                if (isDone) {
                    mugBr.readLine();
                }
                // continue copy the rest of the task
                String line = mugBr.readLine();
                while (Optional.ofNullable(line).isPresent()) {
                    mugPw.println(line);
                    line = mugBr.readLine();
                }
            }
            // close br
            undoBr.close();
            mugBr.close();
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
