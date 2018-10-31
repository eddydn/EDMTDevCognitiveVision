package edmt.dev.edmtdevcognitivevision.Contract;

import java.util.ArrayList;
import java.util.List;

public class HandwritingTextLine {
    private List<HandwritingTextWord> words; //words in the line

    private String text; //text of text line

    private ArrayList<Integer> boundingBox; //bouding box for whole text line, e.g. "boundingBox": [153,579,1310,550,1313,647,156,678].

    public List<HandwritingTextWord> getWords() {
        return words;
    }

    public void setWords(List<HandwritingTextWord> words) {
        this.words = words;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<Integer> getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(ArrayList<Integer> boundingBox) {
        this.boundingBox = boundingBox;
    }
}
