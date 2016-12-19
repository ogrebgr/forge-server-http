package com.bolyartech.forge.server.response.forge;

public class InvalidParameterValueResponse extends ForgeResponse {
    public InvalidParameterValueResponse() {
        super("", BasicResponseCodes.Errors.INVALID_PARAMETER_VALUE.getCode());
    }


    public InvalidParameterValueResponse(String string) {
        super(string, BasicResponseCodes.Errors.INVALID_PARAMETER_VALUE.getCode());
    }


    public InvalidParameterValueResponse(String string, boolean enableGzipSupport) {
        super(string, enableGzipSupport, BasicResponseCodes.Errors.INVALID_PARAMETER_VALUE.getCode());
    }

}
