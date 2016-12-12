package com.bolyartech.forge.server.route;

import com.bolyartech.forge.server.HttpMethod;
import com.google.common.base.Strings;
import com.google.common.io.CharStreams;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RequestContextImpl implements RequestContext {
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_TYPE_FORM_ENCODED = "application/x-www-form-urlencoded";


    private final HttpServletRequest mHttpReq;
    private final Map<String, String> mGetParams = new HashMap<>();
    private final Map<String, String> mPostParams = new HashMap<>();
    private final Map<String, Cookie> mCookieParams = new HashMap<>();
    private boolean mCookiesInitialized = false;
    private final List<String> mPathInfoParams = new ArrayList<>();


    public RequestContextImpl(HttpServletRequest httpReq) throws IOException {
        mHttpReq = httpReq;
        extractParameters(httpReq.getQueryString(), mGetParams);

        if (httpReq.getMethod().equalsIgnoreCase(HttpMethod.POST.getLiteral())) {
            String contentType = httpReq.getHeader(HEADER_CONTENT_TYPE);
            if (contentType.toLowerCase().contains(CONTENT_TYPE_FORM_ENCODED.toLowerCase())) {
                extractParameters(CharStreams.toString(httpReq.getReader()), mPostParams);
            }
        }

//TODO pi variables
    }


    static void extractParameters(String queryString, Map<String, String> to) {
        if (!Strings.isNullOrEmpty(queryString)) {
            try {
                String decoded = URLDecoder.decode(queryString, "UTF-8");
                String[] split = queryString.split("&");
                for (String aSplit : split) {
                    String[] keyValue = aSplit.split("=");
                    if (keyValue.length == 1) {
                        to.put(URLDecoder.decode(keyValue[0], "UTF-8"), "");
                    } else {
                        to.put(URLDecoder.decode(keyValue[0], "UTF-8"), URLDecoder.decode(keyValue[1], "UTF-8"));
                    }
                }
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public String getFromGet(String parameterName) {
        return mGetParams.get(parameterName);
    }


    @Override
    public String getFromPost(String parameterName) {
        return mPostParams.get(parameterName);
    }


    @Override
    public List<String> getPathInfoParameters() {
        return mPathInfoParams;
    }


    @Override
    public List<String> getPi() {
        return getPathInfoParameters();
    }


    @Override
    public Cookie getCookie(String cookieName) {
        if (!mCookiesInitialized) {
            Cookie[] cs = mHttpReq.getCookies();
            for(Cookie c : cs) {
                mCookieParams.put(c.getName(), c);
            }
            mCookiesInitialized = true;
        }

        return mCookieParams.get(cookieName);
    }


    @Override
    public String getFromServer(String cookieName) {
        return null;
    }


    @Override
    public String optFromGet(String parameterName, String defaultValue) {
        return null;
    }


    @Override
    public String optFromPost(String parameterName, String defaultValue) {
        return null;
    }


    @Override
    public String optFromPathInfo(String parameterName, String defaultValue) {
        return null;
    }


    @Override
    public String optCookie(String cookieName, String defaultValue) {
        return null;
    }


    @Override
    public String optFromServer(String cookieName, String defaultValue) {
        return null;
    }



}
