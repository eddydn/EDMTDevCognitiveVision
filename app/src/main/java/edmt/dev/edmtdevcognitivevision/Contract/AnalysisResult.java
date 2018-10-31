package edmt.dev.edmtdevcognitivevision.Contract;

import java.util.List;
import java.util.UUID;

public class AnalysisResult {
    public UUID requestId;

    public Metadata metadata;

    public ImageType imageType;

    public Color color;

    public Adult adult;

    public Description description;

    public List<Category> categories;

    public List<Face> faces;

    public List<Tag> tags;
}
