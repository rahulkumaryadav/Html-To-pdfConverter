package com.stpl.fileconvertor;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

public interface IActionListener {
    public void actionListener(ByteArrayOutputStream byteArrayOutputStream) throws FileNotFoundException;
}
