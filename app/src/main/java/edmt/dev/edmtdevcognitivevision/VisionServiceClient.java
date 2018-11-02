package edmt.dev.edmtdevcognitivevision;

import java.io.IOException;
import java.io.InputStream;

import edmt.dev.edmtdevcognitivevision.Contract.AnalysisInDomainResult;
import edmt.dev.edmtdevcognitivevision.Contract.AnalysisResult;
import edmt.dev.edmtdevcognitivevision.Contract.CelebritiesResult;
import edmt.dev.edmtdevcognitivevision.Contract.HandwritingRecognitionOperation;
import edmt.dev.edmtdevcognitivevision.Contract.HandwritingRecognitionOperationResult;
import edmt.dev.edmtdevcognitivevision.Contract.Model;
import edmt.dev.edmtdevcognitivevision.Contract.ModelResult;
import edmt.dev.edmtdevcognitivevision.Contract.OCR;
import edmt.dev.edmtdevcognitivevision.Rest.VisionServiceException;

public interface VisionServiceClient {

    public CelebritiesResult detectCelebrities(InputStream stream)throws VisionServiceException, IOException;

    public AnalysisResult analyzeImage(String url, String[] visualFeatures, String[] details) throws VisionServiceException;

    public AnalysisResult analyzeImage(InputStream stream, String[] visualFeatures, String[] details) throws VisionServiceException, IOException;

    public AnalysisInDomainResult analyzeImageInDomain(String url, Model model) throws VisionServiceException;

    public AnalysisInDomainResult analyzeImageInDomain(String url, String model) throws VisionServiceException;

    public AnalysisInDomainResult analyzeImageInDomain(InputStream stream, Model model) throws VisionServiceException, IOException;

    public AnalysisInDomainResult analyzeImageInDomain(InputStream stream, String model) throws VisionServiceException, IOException;

    public AnalysisResult describe(String url, int maxCandidates) throws VisionServiceException;

    public AnalysisResult describe(InputStream stream, int maxCandidates) throws VisionServiceException, IOException;

    public ModelResult listModels() throws VisionServiceException;

    public OCR recognizeText(String url, String languageCode, boolean detectOrientation) throws VisionServiceException;

    public OCR recognizeText(InputStream stream, String languageCode, boolean detectOrientation) throws VisionServiceException, IOException;

    public HandwritingRecognitionOperation createHandwritingRecognitionOperationAsync(String url) throws  VisionServiceException;

    public HandwritingRecognitionOperation createHandwritingRecognitionOperationAsync(InputStream stream) throws VisionServiceException, IOException;

    public HandwritingRecognitionOperationResult getHandwritingRecognitionOperationResultAsync(String uri) throws  VisionServiceException;

    public byte[] getThumbnail(int width, int height, boolean smartCropping, String url) throws VisionServiceException, IOException;

    public byte[] getThumbnail(int width, int height, boolean smartCropping, InputStream stream) throws VisionServiceException, IOException;

}
