package com.openfaas.afterburn.parser;

import function.Handler;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;

public class Parser {
    private Handler handler = new Handler();

    public void acceptIncoming(DataInputStream inputStream, BufferedWriter outputWriter) throws IOException {
        System.err.println("Begin Request");

        Request request = new Reader(inputStream).getRequest();
        String responseBody = handler.function(request.getBody(), request.getHeaders().getMethod());
        String response = new Response("200 OK", responseBody, "text/plain").serialize();

        System.err.println(response);

        outputWriter.write(response);
        outputWriter.flush();

        System.err.println("End Request");
    }
}
