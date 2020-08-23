/**
 * Encapsulates classes that can be summarised with the getSummary() method to be stored.
 * Classes that implement this interface must also have a <code>reconstruct(String summary)</code> method that recreates the object.
 */
public interface PrintSummary {

    /**
     * Returns a summary of the object.
     * The object must be able to be reconstructed from the summary.
     * @return string representing the details of the object.
     */
    public String getSummary();

}
