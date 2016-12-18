package com.bolyartech.forge.server;

public interface Session {
    <T> T getVar(String varName);

    void setVar(String varName, Object value);
}
