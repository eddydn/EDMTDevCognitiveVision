package edmt.dev.edmtdevcognitivevision;

import com.google.gson.Gson;




import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import edmt.dev.edmtdevcognitivevision.Contract.AnalysisInDomainResult;
import edmt.dev.edmtdevcognitivevision.Contract.AnalysisResult;
import edmt.dev.edmtdevcognitivevision.Contract.CelebritiesResult;
import edmt.dev.edmtdevcognitivevision.Contract.HandwritingRecognitionOperation;
import edmt.dev.edmtdevcognitivevision.Contract.HandwritingRecognitionOperationResult;
import edmt.dev.edmtdevcognitivevision.Contract.Model;
import edmt.dev.edmtdevcognitivevision.Contract.ModelResult;
import edmt.dev.edmtdevcognitivevision.Contract.OCR;
import edmt.dev.edmtdevcognitivevision.Rest.VisionServiceException;
import edmt.dev.edmtdevcognitivevision.Rest.WebServiceRequest;
import edmt.dev.edmtdevcognitivevision.Utils.Utils;

public class VisionServiceRestClient implements VisionServiceClient  {
    private static final String DEFAULT_REGION = "westus";
    private static final String DEFAULT_API_ROOT = "https://%s.api.cognitive.microsoft.com/vision/v1.0";
    private final String apiRoot;
    private final WebServiceRequest restCall;
    private Gson gson = new Gson();

    public VisionServiceRestClient(String subscriptKey) {
        this(subscriptKey, getApiUrlFromRegion(DEFAULT_REGION));
    }

    public VisionServiceRestClient(String subscriptKey, String apiRoot) {
        this.restCall = new WebServiceRequest(subscriptKey);
        this.apiRoot = apiRoot.replaceAll("/$", "");
    }

    @Override
    public CelebritiesResult detectCelebrities(InputStream stream) throws VisionServiceException, IOException {
        Map<String, Object> params = new HashMap<>();
        AppendParams(params, "visualFeatures", new String[]{"Categories"});
        AppendParams(params, "details", new String[]{"Celebrities"});

        String path = apiRoot + "/analyze";
        String uri = WebServiceRequest.getUrl(path, params);

        params.clear();
        byte[] data = Utils.toByteArray(stream);
        params.put("data", data);

        String json = (String) this.restCall.request(uri, "POST", params, "application/octet-stream", false);
        CelebritiesResult celebritiesResult = this.gson.fromJson(json, CelebritiesResult.class);

        return celebritiesResult;
    }

    @Override
    public AnalysisResult analyzeImage(String url, String[] visualFeatures, String[] details) throws VisionServiceException {
        Map<String, Object> params = new HashMap<>();
        AppendParams(params, "visualFeatures", visualFeatures);
        AppendParams(params, "details", details);

        String path = apiRoot + "/analyze";
        String uri = WebServiceRequest.getUrl(path, params);

        params.clear();
        params.put("url", url);

        String json = (String) this.restCall.request(uri, "POST", params, null, false);
        AnalysisResult visualFeature = this.gson.fromJson(json, AnalysisResult.class);

        return visualFeature;
    }

    @Override
    public AnalysisResult analyzeImage(InputStream stream, String[] visualFeatures, String[] details) throws VisionServiceException, IOException {
        Map<String, Object> params = new HashMap<>();
        AppendParams(params, "visualFeatures", visualFeatures);
        AppendParams(params, "details", details);
        String path = apiRoot + "/analyze";
        String uri = WebServiceRequest.getUrl(path, params);

        params.clear();
        byte[] data = Utils.toByteArray(stream);
        params.put("data", data);

        String json = (String) this.restCall.request(uri, "POST", params, "application/octet-stream", false);
        AnalysisResult visualFeature = this.gson.fromJson(json, AnalysisResult.class);

        return visualFeature;
    }

    @Override
    public AnalysisInDomainResult analyzeImageInDomain(String url, Model model) throws VisionServiceException {
        return  analyzeImageInDomain(url, model.name);
    }



    @Override
    public AnalysisInDomainResult analyzeImageInDomain(String url, String model) throws VisionServiceException {
        Map<String, Object> params = new HashMap<>();
        String path = apiRoot + "/models/" + model + "/analyze";
        String uri = WebServiceRequest.getUrl(path, params);

        params.clear();
        params.put("url", url);

        String json = (String) this.restCall.request(uri, "POST", params, null, false);
        AnalysisInDomainResult visualFeature = this.gson.fromJson(json, AnalysisInDomainResult.class);

        return visualFeature;
    }

    @Override
    public AnalysisInDomainResult analyzeImageInDomain(InputStream stream, Model model) throws VisionServiceException, IOException {
        return analyzeImageInDomain(stream, model.name);
    }

    @Override
    public AnalysisInDomainResult analyzeImageInDomain(InputStream stream, String model) throws VisionServiceException, IOException {
        Map<String, Object> params = new HashMap<>();
        String path = apiRoot + "/models/" + model + "/analyze";
        String uri = WebServiceRequest.getUrl(path, params);

        params.clear();
        byte[] data = Utils.toByteArray(stream);
        params.put("data", data);

        String json = (String) this.restCall.request(uri, "POST", params, "application/octet-stream", false);
        AnalysisInDomainResult visualFeature = this.gson.fromJson(json, AnalysisInDomainResult.class);

        return visualFeature;
    }

    @Override
    public AnalysisResult describe(String url, int maxCandidates) throws VisionServiceException{
        Map<String, Object> params = new HashMap<>();
        params.put("maxCandidates", maxCandidates);
        String path = apiRoot + "/describe";
        String uri = WebServiceRequest.getUrl(path, params);

        params.clear();
        params.put("url", url);

        String json = (String) this.restCall.request(uri, "POST", params, null, false);
        AnalysisResult visualFeature = this.gson.fromJson(json, AnalysisResult.class);

        return visualFeature;
    }

    @Override
    public AnalysisResult describe(InputStream stream, int maxCandidates) throws VisionServiceException, IOException{
        Map<String, Object> params = new HashMap<>();
        params.put("maxCandidates", maxCandidates);
        String path = apiRoot + "/describe";
        String uri = WebServiceRequest.getUrl(path, params);

        params.clear();
        byte[] data = Utils.toByteArray(stream);
        params.put("data", data);

        String json = (String) this.restCall.request(uri, "POST", params, "application/octet-stream", false);
        AnalysisResult visualFeature = this.gson.fromJson(json, AnalysisResult.class);

        return visualFeature;
    }

    @Override
    public ModelResult listModels() throws VisionServiceException{
        Map<String, Object> params = new HashMap<>();
        String path = apiRoot + "/models";
        String uri = WebServiceRequest.getUrl(path, params);

        String json = (String) this.restCall.request(uri, "GET", params, null, false);
        ModelResult models = this.gson.fromJson(json, ModelResult.class);

        return models;
    }

    @Override
    public OCR recognizeText(String url, String languageCode, boolean detectOrientation) throws VisionServiceException {
        Map<String, Object> params = new HashMap<>();
        params.put("language", languageCode);
        params.put("detectOrientation", detectOrientation);
        String path = apiRoot + "/ocr";
        String uri = WebServiceRequest.getUrl(path, params);

        params.clear();
        params.put("url", url);
        String json = (String) this.restCall.request(uri, "POST", params, null, false);
        OCR ocr = this.gson.fromJson(json, OCR.class);

        return ocr;
    }

    @Override
    public OCR recognizeText(InputStream stream, String languageCode, boolean detectOrientation) throws VisionServiceException, IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("language", languageCode);
        params.put("detectOrientation", detectOrientation);
        String path = apiRoot + "/ocr";
        String uri = WebServiceRequest.getUrl(path, params);

        byte[] data = Utils.toByteArray(stream);
        params.put("data", data);
        String json = (String) this.restCall.request(uri, "POST", params, "application/octet-stream", false);
        OCR ocr = this.gson.fromJson(json, OCR.class);

        return ocr;
    }

    @Override
    public HandwritingRecognitionOperation createHandwritingRecognitionOperationAsync(String url) throws VisionServiceException {
        Map<String, Object> params = new HashMap<>();
        String path = apiRoot + "/RecognizeText?handwriting=true";
        String uri = WebServiceRequest.getUrl(path, params);

        params.put("url", url);
        String operationUrl = (String) this.restCall.request(uri, "POST", params, null, false);
        HandwritingRecognitionOperation HandwrittenOCR = new HandwritingRecognitionOperation(operationUrl);

        return HandwrittenOCR;
    }

    @Override
    public HandwritingRecognitionOperation createHandwritingRecognitionOperationAsync(InputStream stream) throws VisionServiceException, IOException {
        Map<String, Object> params = new HashMap<>();
        String path = apiRoot + "/RecognizeText?handwriting=true";
        String uri = WebServiceRequest.getUrl(path, params);

        byte[] data = Utils.toByteArray(stream);
        params.put("data", data);
        String operationUrl = (String) this.restCall.request(uri, "POST", params, "application/octet-stream", false);
        HandwritingRecognitionOperation HandwrittenOCR = new HandwritingRecognitionOperation(operationUrl);

        return HandwrittenOCR;
    }

    @Override
    public HandwritingRecognitionOperationResult getHandwritingRecognitionOperationResultAsync(String uri) throws VisionServiceException {
        String json = (String) this.restCall.request(uri, "GET", null, null, false);
        HandwritingRecognitionOperationResult HandwrittenOCR = this.gson.fromJson(json, HandwritingRecognitionOperationResult.class);

        return HandwrittenOCR;
    }

    @Override
    public byte[] getThumbnail(int width, int height, boolean smartCropping, String url) throws VisionServiceException, IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("width", width);
        params.put("height", height);
        params.put("smartCropping", smartCropping);
        String path = apiRoot + "/generateThumbnail";
        String uri = WebServiceRequest.getUrl(path, params);

        params.clear();
        params.put("url", url);

        InputStream is = (InputStream) this.restCall.request(uri, "POST", params, null, true);
        byte[] image = Utils.toByteArray(is);
        if (is != null) {
            is.close();
        }

        return image;
    }

    @Override
    public byte[] getThumbnail(int width, int height, boolean smartCropping, InputStream stream) throws VisionServiceException, IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("width", width);
        params.put("height", height);
        params.put("smartCropping", smartCropping);
        String path = apiRoot + "/generateThumbnail";
        String uri = WebServiceRequest.getUrl(path, params);

        params.clear();
        byte[] data = Utils.toByteArray(stream);
        params.put("data", data);

        InputStream is = (InputStream) this.restCall.request(uri, "POST", params, "application/octet-stream", true);
        byte[] image = Utils.toByteArray(is);
        if (is != null) {
            is.close();
        }

        return image;
    }

    private void AppendParams(Map<String, Object> params, String name, String[] args) {
        if(args != null && args.length > 0) {
            String features = Utils.join(args, ',');
            params.put(name, features);
        }
    }

    private static String getApiUrlFromRegion(String region) {
        return String.format(DEFAULT_API_ROOT, region);
    }
}
