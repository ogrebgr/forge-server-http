package com.bolyartech.forge.server.endpoint;

import com.google.common.base.Strings;

import javax.servlet.http.Cookie;
import java.util.*;


public class ResponsePropertiesImpl implements ResponseProperties, HandlerResponseProperties {
    private final Map<String, String> mHeaders = new HashMap<>();
    private final List<Cookie> mCookies = new ArrayList<>();

    @Override
    public void setHeader(String header, String value) {
        if (Strings.isNullOrEmpty(header)) {
            throw new IllegalArgumentException("header is null or empty");
        }

        mHeaders.put(header, value);
    }


    @Override
    public void addCookie(Cookie c) {
        if (!mCookies.contains(c)) {
            mCookies.add(c);
        } else {
            throw new IllegalStateException("Cookie already added " + c);
        }
    }


    @Override
    public Map<String, String> getHeaders() {
        return mHeaders;
    }


    @Override
    public List<Cookie> getCookies() {
        return mCookies;
    }
}
