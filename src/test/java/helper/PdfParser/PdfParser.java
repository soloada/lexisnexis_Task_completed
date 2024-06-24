package helper.PdfParser;

import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PdfParser {

    public String getTextFromPdfDocument(File pdfFile) throws IOException {
        PDDocument pdDocument = PDDocument.load(pdfFile);
        String content = new PDFTextStripper().getText(pdDocument);
        pdDocument.close();
        return content;
    }

    public String getTextFromPdf(String pdfFile) throws Throwable {
        byte[] pdfFileBytes = readFileAsBytes(pdfFile);
        PDDocument pdDocument = PDDocument.load(pdfFileBytes);
        String content = new PDFTextStripper().getText(pdDocument);
        pdDocument.close();
        return content;
    }

    private byte[] readFileAsBytes(String filePath) throws Throwable {
        FileInputStream inputStream = new FileInputStream(filePath);
        return IOUtils.toByteArray(inputStream);
    }

}
