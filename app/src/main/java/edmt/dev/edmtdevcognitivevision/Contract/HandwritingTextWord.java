package edmt.dev.edmtdevcognitivevision.Contract;

import java.util.ArrayList;

public class HandwritingTextWord {
    private String text; //text of the world

    private ArrayList<Integer> boundingBox; //bouding box of the world in origin image, e.g. "boundingBox": [153,579,1310,550,1313,647,156,678].

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
