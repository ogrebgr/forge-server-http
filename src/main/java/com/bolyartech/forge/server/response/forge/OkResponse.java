package com.bolyartech.forge.server.response.forge;

public class OkResponse extends ForgeResponse {

    public OkResponse() {
        super("", BasicResponseCodes.Oks.OK);
    }


    public OkResponse(String string) {
        super(string, BasicResponseCodes.Oks.OK);
    }


    public OkResponse(String string, boolean enableGzipSupport) {
        super(string, enableGzipSupport, BasicResponseCodes.Oks.OK);
    }
}
