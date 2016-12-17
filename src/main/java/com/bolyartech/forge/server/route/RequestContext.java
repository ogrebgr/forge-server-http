package com.bolyartech.forge.server.route;

import com.bolyartech.forge.server.HttpMethod;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;


public interface RequestContext {
    String getFromGet(String parameterName);
    String getFromPost(String parameterName);

    /**
     *
     * @return List containing path info parameters ordered from left to right
     */
    List<String> getPathInfoParameters();

    /**
     * Alias of {@link #getPathInfoParameters()}
     * @return List containing path info parameters ordered from left to right
     */
    List<String> getPi();

    String getRoutePath();
    String getScheme();
    Part getPart(String partName) throws IOException, ServletException;
    String getPathInfoString();
    Cookie getCookie(String cookieName);
    String getFromServer(String cookieName);
    String optFromGet(String parameterName, String defaultValue);
    String optFromPost(String parameterName, String defaultValue);
    String optFromPathInfo(String parameterName, String defaultValue);
    String optCookie(String cookieName, String defaultValue);
    String optFromServer(String cookieName, String defaultValue);

    String getHeader(String headerName);
    List<String> getHeaderValues(String headerName);

    boolean isMultipart();
    HttpMethod getMethod();
    HttpMethod getHttpMethod();
    boolean isMethod(HttpMethod method);
}
