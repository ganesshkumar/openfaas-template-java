package com.openfaas.afterburn.parser;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class Reader {
    private DataInputStream inputStream;

    Reader(DataInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Request getRequest() throws IOException {
        Headers headers = readHeader();
        return new Request(headers, readBody(headers.contentLength()));
    }

    private Headers readHeader() throws IOException {
        StringBuffer header = new StringBuffer();

        String line;
        while(true)  {
            line = readLine();
            if (line.equals("\n")) break;
            header.append(line);
        }

        return new Headers(header.toString());
    }

    private String readBody(int length) throws IOException {
        byte[] bytes = new byte[length];
        for(int i = 0 ; i < length; i++) {
            bytes[i] = inputStream.readByte();
        }

        return new String(bytes);
    }

    private  String readLine() throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        char character;
        while (true) {
            character = (char)this.inputStream.read();
            if (character == '\r') continue;
            buffer.write(character);
            if (character == '\n') break;
        }

        return buffer.toString();
    }
}
