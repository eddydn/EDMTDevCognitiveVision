package edmt.dev.edmtdevcognitivevision.Contract;

import java.util.List;

public class HandwritingTextResult {
    private List<HandwritingTextLine> lines; //lines in recognition result

    public List<HandwritingTextLine> getLines() {
        return lines;
    }

    public void setLines(List<HandwritingTextLine> lines) {
        this.lines = lines;
    }
}
