package com.bolyartech.forge.server.response;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class StringResponseImpl implements StringResponse {
    private static final String CONTENT_TYPE_TEXT = "text/plain;charset=UTF-8";
    private final String mString;


    public StringResponseImpl(String string) {
        mString = string;
    }


    @Override
    public String getString() {
        return mString;
    }


    @Override
    public void toServletResponse(HttpServletResponse resp) throws ResponseException {
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType(CONTENT_TYPE_TEXT);

        try {
            PrintWriter pw = resp.getWriter();
            pw.print(mString);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            throw new ResponseException(e);
        }
    }
}
