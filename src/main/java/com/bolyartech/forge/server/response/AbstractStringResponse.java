package com.bolyartech.forge.server.response;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


abstract public class AbstractStringResponse implements StringResponse {
    private final String mString;


    public AbstractStringResponse(String string) {
        mString = string;
    }

    abstract protected String getContentType();


    @Override
    public String getString() {
        return mString;
    }


    @Override
    public void toServletResponse(HttpServletResponse resp) throws ResponseException {
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType(getContentType());

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
