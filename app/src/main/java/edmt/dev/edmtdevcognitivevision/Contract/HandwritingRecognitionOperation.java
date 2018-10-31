package edmt.dev.edmtdevcognitivevision.Contract;

public class HandwritingRecognitionOperation {
    public HandwritingRecognitionOperation(String url)
    {
        this.url =url;
    }

    public String Url()
    {
        return url;
    } //Url of operation

    private String url;
}
