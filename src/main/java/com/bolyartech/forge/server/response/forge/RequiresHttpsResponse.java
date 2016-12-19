package com.bolyartech.forge.server.response.forge;

public class RequiresHttpsResponse extends ForgeResponse {
    public RequiresHttpsResponse() {
        super("", BasicResponseCodes.Errors.REQUIRES_HTTPS.getCode());
    }


    public RequiresHttpsResponse(String string) {
        super(string, BasicResponseCodes.Errors.REQUIRES_HTTPS.getCode());
    }


    public RequiresHttpsResponse(String string, boolean enableGzipSupport) {
        super(string, enableGzipSupport, BasicResponseCodes.Errors.REQUIRES_HTTPS.getCode());
    }
}
