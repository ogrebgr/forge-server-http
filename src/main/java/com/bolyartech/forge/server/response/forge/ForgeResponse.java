package com.bolyartech.forge.server.response.forge;

import com.bolyartech.forge.server.response.JsonResponse;
import com.bolyartech.forge.server.response.ResponseException;

import javax.servlet.http.HttpServletResponse;


public class ForgeResponse extends JsonResponse {
    private static final String FORGE_RESULT_CODE_HEADER = "X-Forge-Result-Code";

    private final ForgeResponseCode mResultCode;


    public ForgeResponse(String string, ForgeResponseCode resultCode) {
        super(string);
        mResultCode = resultCode;
    }


    public ForgeResponse(String string, boolean enableGzipSupport, ForgeResponseCode resultCode) {
        super(string, enableGzipSupport);
        mResultCode = resultCode;
    }


    @Override
    public void toServletResponse(HttpServletResponse resp) throws ResponseException {
        resp.setHeader(FORGE_RESULT_CODE_HEADER, Integer.toString(mResultCode.getCode()));
        super.toServletResponse(resp);
    }


    public ForgeResponseCode getResultCode() {
        return mResultCode;
    }


    public String getPayload() {
        return getString();
    }
}
