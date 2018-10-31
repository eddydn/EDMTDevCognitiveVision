package edmt.dev.edmtdevcognitivevision.Rest;

import com.google.gson.Gson;

public class VisionServiceException extends Exception {

    public VisionServiceException(String message) {
        super(message);
    }

    public VisionServiceException(Gson errorObject) {
        super(errorObject.toString());
    }
}