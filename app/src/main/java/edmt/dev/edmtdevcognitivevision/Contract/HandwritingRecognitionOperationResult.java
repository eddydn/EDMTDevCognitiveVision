package edmt.dev.edmtdevcognitivevision.Contract;

public class HandwritingRecognitionOperationResult {
    private String status; //status of recogniton result

    private HandwritingTextResult recognitionResult; //content of recognition result

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HandwritingTextResult getRecognitionResult() {
        return recognitionResult;
    }

    public void setRecognitionResult(HandwritingTextResult recognitionResult) {
        this.recognitionResult = recognitionResult;
    }
}
