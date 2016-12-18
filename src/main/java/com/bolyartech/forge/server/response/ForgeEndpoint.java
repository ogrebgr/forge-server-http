package com.bolyartech.forge.server.response;

import javax.servlet.http.HttpServletResponse;


public class ForgeEndpoint extends JsonEndpoint {
    private static final String FORGE_RESULT_CODE_HEADER = "X-Forge-Result-Code";

    private final int mResultCode;


    public ForgeEndpoint(String string, int resultCode) {
        super(string);
        mResultCode = resultCode;
    }


    public ForgeEndpoint(String string, boolean enableGzipSupport, int resultCode) {
        super(string, enableGzipSupport);
        mResultCode = resultCode;
    }


    @Override
    public void toServletResponse(HttpServletResponse resp) throws ResponseException {
        resp.setHeader(FORGE_RESULT_CODE_HEADER, Integer.toString(mResultCode));
        super.toServletResponse(resp);
    }
}
