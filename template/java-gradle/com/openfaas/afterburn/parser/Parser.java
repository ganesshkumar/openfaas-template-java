package com.openfaas.afterburn.parser;

import function.Handler;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;

public class Parser {
    private Handler handler = new Handler();

    public void acceptIncoming(DataInputStream inputStream, BufferedWriter outputWriter) throws IOException {
        Request request = new Reader(inputStream).getRequest();
        String responseBody = handler.function(request.getBody(), request.getHeaders().getMethod());
        String response = new Response(200, responseBody, "text/plain").serialize();

        outputWriter.write(response, 0, response.length());
        outputWriter.flush();
    }
}
