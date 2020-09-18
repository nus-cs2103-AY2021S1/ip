package duke.exception;

import duke.ImageType;

/**
 * Represents an exception with duke operations.
 */
public class DukeException extends Exception {

    /**
     * Image associated with the exception.
     */
    protected ImageType imageType;

    /**
     * Creates a DukeException object.
     * @param errorMsg Message representing the exception.
     */
    public DukeException(String errorMsg) {
        super(errorMsg);
        this.imageType = ImageType.ALERT;
    }

    /**
     * Creates a DukeException object with a specific image type.
     * @param errorMsg Message representing the exception.
     * @param imageType Type of image.
     */
    public DukeException(String errorMsg, ImageType imageType) {
        super(errorMsg);
        this.imageType = imageType;
    }

    /**
     * Returns the type of image.
     * @return Type of image.
     */
    public ImageType getImageType() {
        return imageType;
    }
}
