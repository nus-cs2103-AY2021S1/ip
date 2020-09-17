package duke.exception;

import duke.ImageType;

public class DukeException extends Exception {
    protected ImageType imageType;

    public DukeException(String errorMsg) {
        super(errorMsg);
        this.imageType = ImageType.ALERT;
    }

    public DukeException(String errorMsg, ImageType imageType) {
        super(errorMsg);
        this.imageType = imageType;
    }

    public ImageType getImageType() {
        return imageType;
    }
}
