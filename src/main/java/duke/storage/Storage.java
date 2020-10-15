package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeOperationException;
import duke.exception.DukeParseException;
import duke.list.StorableList;
import duke.parser.StorageParser;

/**
 * Represents an object that deals with loading and saving DukeLists into a text file.
 * @param <T> the object to be stored in the text files.
 */
public abstract class Storage<T extends Storable> {
    protected final File file;
    protected final StorageParser<T> storageParser;

    /**
     * Constructor method.
     * @param file the actual file path of the text file.
     * @param storageParser the required <code>StorageParser</code>.
     */
    Storage(File file, StorageParser<T> storageParser) {
        this.file = file;
        this.storageParser = storageParser;
    }

    /**
     * Writes a <code>String</code> to the text file.
     *
     * @param text the <code>String</code> to be written.
     * @throws IOException if the file path does not exist.
     */
    private void writeToFile(String text) throws IOException {
        FileWriter fw = new FileWriter(file.getAbsolutePath());
        fw.write(text);
        fw.close();
    }

    abstract String getStorableName();

    /**
     * Converts the text file to an array of <code>Strings</code>.
     *
     * @return the converted array of <code>Strings</code>.
     */
    private String[] convertStorageToArray() throws DukeOperationException {
        Scanner sc;
        try {
            sc = new Scanner(file);
        } catch (IOException ignore) {
            String msg = String.format(
                    "I detect no %s storage files. I shall create a fresh list.", getStorableName());
            throw new DukeOperationException(msg);
        }
        ArrayList<String> storageList = new ArrayList<>();
        while (sc.hasNext()) {
            storageList.add(sc.nextLine());
        }
        return storageList.toArray(String[]::new);
    }

    /**
     * Loads an array of storage <code>Strings</code> the given <code>StorableList</code>.
     *
     * @param storageArray the array to be loaded.
     * @param list the <code>StorableList</code> to be loaded into.
     * @return a status <code>String</code> of the outcome of loading.
     */
    private String loadStorageArray(String[] storageArray, StorableList<T> list) {
        StringBuilder sb = new StringBuilder();
        for (String storage : storageArray) {
            try {
                T storable = storageParser.parseStorageString(storage);
                list.add(storable);
            } catch (DukeParseException exception) {
                sb.append(exception.getMessage());
                sb.append("\n");
            }
        }

        if (sb.length() == 0) {
            return String.format("All %s have been loaded.", getStorableName());
        } else {
            sb.deleteCharAt(sb.length() - 1);
            return String.format("The following %ss could not be loaded:\n", getStorableName())
                    + sb.toString();
        }
    }

    /**
     * Loads the <code>StorableList</code> from the text file.
     *
     * @param list the <code>StorableList</code> to be loaded onto.
     * @return a <code>String</code> indicating the status of the loading.
     */
    public String loadList(StorableList<T> list) {
        try {
            String[] storageList = convertStorageToArray();
            return loadStorageArray(storageList, list);
        } catch (DukeOperationException exception) {
            return exception.getMessage();
        }
    }

    /**
     * Saves the <code>StorableList</code> into the text file.
     *
     * @param list the <code>StorableList</code> that is to be saved.
     * @throws DukeOperationException if the text file cannot be written onto.
     */
    public void saveToDisk(StorableList<T> list) throws DukeOperationException {
        StringBuilder sb = new StringBuilder();
        for (T storable : list) {
            String storageTask = storable.convertToStorageString();
            sb.append(storageTask);
        }
        try {
            writeToFile(sb.toString());
        } catch (IOException exception) {
            throw new DukeOperationException("There were some problems when writing to the file. "
                    + exception.getMessage());
        }
    }
}
