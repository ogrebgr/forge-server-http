package com.bolyartech.forge.server.response;

public class HttpHeaders {
    private HttpHeaders() {
        throw new AssertionError("Non-instantiable utility class");
    }

    public static final String LAST_MODIFIED = "Last-Modified";
    public static final String CONTENT_TYPE_OCTET = "application/octet-stream";
    public static final String CACHE_CONTROL = "Cache-control";
    public static final String CACHE_CONTROL_VALUE_NO_CACHE = "no-cache";

    public static final String CONTENT_ENCODING = "Content-Encoding";
    public static final String CONTENT_ENCODING_GZIP = "gzip";

    public static final String ACCEPT_ENCODING = "Accept-Encoding";

    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    public static final String CONTENT_DISPOSITION_ATTACHMENT = "attachment; filename=\"{0}\"";
}
