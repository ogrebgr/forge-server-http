package com.bolyartech.forge.server.endpoint;

import javax.servlet.http.Cookie;


public interface ResponseProperties {
    void setHeader(String header, String value);
    void addCookie(Cookie c);
}
