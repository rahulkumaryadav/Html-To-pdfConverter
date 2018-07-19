package com.stpl.fileconvertor;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.apache.commons.io.FilenameUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HtmlToPdf {
    private Context context;
    private TemplateEngine templateEngine;
    private List<IActionListener> iActionListenerList;
    private PdfRendererBuilder pdfRendererBuilder;
    private ByteArrayOutputStream byteArrayOutputStream;

    public HtmlToPdf() {
    }

    public HtmlToPdf(TemplateEngine templateEngine, ByteArrayOutputStream byteArrayOutputStream, PdfRendererBuilder pdfRendererBuilder, Context context, ArrayList arrayList) {
        this.templateEngine = templateEngine;
        this.byteArrayOutputStream = byteArrayOutputStream;
        this.pdfRendererBuilder = pdfRendererBuilder;
        this.context = context;
        this.iActionListenerList = arrayList;
    }

    private Context contextConfiguration(Map<String, Object> data) {
        context.setVariables(data);
        return context;
    }

    private String getFileContent(String filePath) {
        if (filePath != null && FilenameUtils.getExtension(filePath).equalsIgnoreCase("html")) {
            try {
                String string = new String(Files.readAllBytes(Paths.get(filePath)));
                return string;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void executeActionListener() {
        for (IActionListener actionListener : iActionListenerList) {
            try {
                actionListener.actionListener(byteArrayOutputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void convert(String filePath, Map<String, Object> data) {
        try {
            pdfRendererBuilder.withHtmlContent(templateEngine.process(getFileContent(filePath), contextConfiguration(data)), "");
            pdfRendererBuilder.toStream(byteArrayOutputStream);
            pdfRendererBuilder.run();
            executeActionListener();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addActionListener(IActionListener iActionListener) {
        if (iActionListener != null) {
            iActionListenerList.add(iActionListener);
        }
    }

    public void removeActionListener(IActionListener iActionListener) {
        if (iActionListener != null) {
            iActionListenerList.remove(iActionListener);
        }
    }

    public void resetActionListener() {
        iActionListenerList.clear();
    }

    public void setiActionListenerList(List<IActionListener> iActionListenerList) {
        if (iActionListenerList != null) {
            this.iActionListenerList = iActionListenerList;
        }
    }


}
