package com.bolyartech.forge.server.response.forge;

public class ServerErrorResponse extends ForgeResponse {
    public ServerErrorResponse() {
        super("", BasicResponseCodes.Errors.INTERNAL_SERVER_ERROR);
    }


    public ServerErrorResponse(String string) {
        super(string, BasicResponseCodes.Errors.INTERNAL_SERVER_ERROR);
    }


    public ServerErrorResponse(String string, boolean enableGzipSupport) {
        super(string, enableGzipSupport, BasicResponseCodes.Errors.INTERNAL_SERVER_ERROR);
    }
}
