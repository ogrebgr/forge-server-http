package com.bolyartech.forge.server.endpoint;

import javax.servlet.http.Cookie;
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
    Cookie getCookie(String cookieName);
    String getFromServer(String cookieName);
    String optFromGet(String parameterName, String defaultValue);
    String optFromPost(String parameterName, String defaultValue);
    String optFromPathInfo(String parameterName, String defaultValue);
    String optCookie(String cookieName, String defaultValue);
    String optFromServer(String cookieName, String defaultValue);
}
