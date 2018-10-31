package edmt.dev.edmtdevcognitivevision.Contract;

import com.google.gson.JsonObject;

import java.util.UUID;

public class AnalysisInDomainResult {
    public UUID requestId;

    public Metadata metadata;

    public JsonObject result;
}
