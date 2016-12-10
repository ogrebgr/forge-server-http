package com.bolyartech.forge.server;

import javax.servlet.http.HttpSession;


public class SessionImpl implements Session {
    private final HttpSession mHttpSession;


    public SessionImpl(HttpSession httpSession) {
        mHttpSession = httpSession;
    }


    @Override
    public <T> T getVar(String varName) {
        //noinspection unchecked
        return (T) mHttpSession.getAttribute(varName);
    }


    @Override
    public void setVar(String varName, Object value) {
        mHttpSession.setAttribute(varName, value);
    }
}
