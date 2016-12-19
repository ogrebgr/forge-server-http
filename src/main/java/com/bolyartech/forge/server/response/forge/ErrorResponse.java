package com.bolyartech.forge.server.response.forge;

public class ErrorResponse extends ForgeResponse {
    public ErrorResponse() {
        super("", BasicResponseCodes.Errors.ERROR.getCode());
    }


    public ErrorResponse(String string) {
        super(string, BasicResponseCodes.Errors.ERROR.getCode());
    }


    public ErrorResponse(String string, boolean enableGzipSupport) {
        super(string, enableGzipSupport, BasicResponseCodes.Errors.ERROR.getCode());
    }
}
