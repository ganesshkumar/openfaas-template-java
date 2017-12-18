package com.openfaas.afterburn.parser;

public class Response {
    private static final String SEPARATOR = "/r/n";

    private String status;
    private String body;
    private String contentType;

    public Response(String status, String body, String contentType) {
        this.status = status;
        this.body = body;
        this.contentType = contentType;
    }

    public String serialize() {
        return new StringBuffer()
                .append(headers())
                .append(SEPARATOR)
                .append(body)
                .toString();
    }

    private String headers() {
        return new StringBuffer()
                .append(String.format("HTTP/1.1 %s%s", status, SEPARATOR))
                .append(String.format("Content-Length: %s%s", body.length(), SEPARATOR))
                .append(contentType != null ? String.format("Content-Type: %s%s", contentType, SEPARATOR): "")
                .append(String.format("Connection: Close%s", SEPARATOR))
                .toString();
    }
}
