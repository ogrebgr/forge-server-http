package com.bolyartech.forge.server.response;

import javax.servlet.http.HttpServletResponse;


public class JsonResponse extends AbstractStringResponse {
    private static final String CONTENT_TYPE_JSON = "application/json;charset=UTF-8";


    public JsonResponse(String string) {
        super(string);
    }


    @Override
    protected String getContentType() {
        return CONTENT_TYPE_JSON;
    }


    @Override
    public void toServletResponse(HttpServletResponse resp) throws ResponseException {
        resp.setHeader(HttpHeaders.CACHE_CONTROL, HttpHeaders.CACHE_CONTROL_VALUE_NO_CACHE);
        super.toServletResponse(resp);
    }
}
