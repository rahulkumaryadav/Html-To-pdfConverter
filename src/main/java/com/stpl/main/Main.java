package com.stpl.main;

import com.stpl.fileconvertor.ConverterFactory;
import com.stpl.fileconvertor.HtmlToPdf;
import com.stpl.fileconvertor.IActionListener;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        ConverterFactory converterFactory=new ConverterFactory();
        HtmlToPdf htmlToPdf=converterFactory.getInstance();
        htmlToPdf.addActionListener(new A1());
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("name","Rahul");
        hashMap.put("email","rahulkumar96369@gmail.com");
        htmlToPdf.convert("E:\\WorkingProject\\Html-To-pdfConverter\\src\\main\\java\\com\\stpl\\fileconvertor\\welcome.html",hashMap);
    }

}

class A1 implements IActionListener{

    @Override
    public void actionListener(ByteArrayOutputStream byteArrayOutputStream) throws FileNotFoundException {
        try {
            byteArrayOutputStream.writeTo(new FileOutputStream("E:\\WorkingProject\\Html-To-pdfConverter\\src\\main\\file\\out.pdf"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
