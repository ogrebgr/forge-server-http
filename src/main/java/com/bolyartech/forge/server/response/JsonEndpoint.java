package com.bolyartech.forge.server.response;


public class JsonEndpoint extends AbstractStringResponse {
    private static final String CONTENT_TYPE_JSON = "application/json;charset=UTF-8";


    public JsonEndpoint(String string) {
        super(string);
    }


    public JsonEndpoint(String string, boolean enableGzipSupport) {
        super(string, enableGzipSupport);
    }


    @Override
    protected String getContentType() {
        return CONTENT_TYPE_JSON;
    }
}
