package com.bolyartech.forge.server.response.forge;

public class MissingParametersResponse extends ForgeResponse {
    public MissingParametersResponse() {
        super("", BasicResponseCodes.Errors.MISSING_PARAMETERS.getCode());
    }


    public MissingParametersResponse(String string) {
        super(string, BasicResponseCodes.Errors.MISSING_PARAMETERS.getCode());
    }


    public MissingParametersResponse(String string, boolean enableGzipSupport) {
        super(string, enableGzipSupport, BasicResponseCodes.Errors.MISSING_PARAMETERS.getCode());
    }
}
