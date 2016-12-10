package com.bolyartech.forge.server.endpoint;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.Map;


public interface HandlerResponseProperties extends ResponseProperties {
    Map<String, String> getHeaders();
    List<Cookie> getCookies();
}
