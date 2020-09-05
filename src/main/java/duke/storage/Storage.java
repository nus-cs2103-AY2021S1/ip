package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/** Represents an object that deals with loading and saving DukeLists into a text file. */
public abstract class Storage {
    protected final File file;

    /**
     * Constructor method.
     *
     * @param file the actual file path of the text file.
     */
    Storage(File file) {
        this.file = file;
    }

    /**
     * Writes a <code>String</code> to the text file.
     *
     * @param text the <code>String</code> to be written.
     * @throws IOException if the file path does not exist.
     */
    protected void writeToFile(String text) throws IOException {
        FileWriter fw = new FileWriter(this.file.getAbsolutePath());
        fw.write(text);
        fw.close();
    }
}
