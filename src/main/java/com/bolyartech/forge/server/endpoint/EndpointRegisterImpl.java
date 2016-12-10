package com.bolyartech.forge.server.endpoint;

import com.bolyartech.forge.server.HttpMethod;
import com.google.common.base.CharMatcher;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class EndpointRegisterImpl implements EndpointRegister {
    private final Map<String, Registration> mEndpointsGet = new ConcurrentHashMap<>();
    private final Map<String, Registration> mEndpointsPost = new ConcurrentHashMap<>();
    private final Map<String, Registration> mEndpointsDelete = new ConcurrentHashMap<>();
    private final Map<String, Registration> mEndpointsPut = new ConcurrentHashMap<>();


    @Override
    public void register(String moduleName, Endpoint endpoint) {
        if (endpoint == null) {
            throw new NullPointerException("endpoint is null");
        }

        switch (endpoint.getHttpMethod()) {
            case GET:
                register(mEndpointsGet, moduleName, endpoint);
                break;
            case POST:
                register(mEndpointsPost, moduleName, endpoint);
                break;
            case PUT:
                register(mEndpointsPut, moduleName, endpoint);
                break;
            case DELETE:
                register(mEndpointsDelete, moduleName, endpoint);
                break;
        }

    }


    private void register(Map<String, Registration> endpoints, String moduleName, Endpoint endpoint) {
        if (!endpoints.containsKey(endpoint.getPath())) {
            endpoints.put(endpoint.getPath(), new Registration(moduleName, endpoint));
        } else {
            throw new IllegalStateException("Registered path already exist: " + endpoint.getPath());
        }
    }


    @Override
    public boolean isRegistered(Endpoint ep) {
        switch (ep.getHttpMethod()) {
            case GET:
                return mEndpointsGet.containsKey(ep.getPath());
            case POST:
                return mEndpointsPost.containsKey(ep.getPath());
            case PUT:
                return mEndpointsPut.containsKey(ep.getPath());
            case DELETE:
                return mEndpointsDelete.containsKey(ep.getPath());
            default:
                return false;
        }
    }


    @Override
    public Registration getRegistration(Endpoint ep) {
        switch (ep.getHttpMethod()) {
            case GET:
                return mEndpointsGet.get(ep.getPath());
            case POST:
                return mEndpointsPost.get(ep.getPath());
            case PUT:
                return mEndpointsPut.get(ep.getPath());
            case DELETE:
                return mEndpointsDelete.get(ep.getPath());
            default:
                return null;
        }
    }


    /**
     * If the path contains more than 15 slashes it will not be matched
     * @param method
     * @param path
     * @return
     */
    @Override
    public Endpoint match(HttpMethod method, String path) {
        path = EndpointImpl.normalizePath(path);

        switch (method) {
            case GET:
                return match(mEndpointsGet, path);
            case POST:
                return match(mEndpointsPost, path);
            case PUT:
                return match(mEndpointsPut, path);
            case DELETE:
                return match(mEndpointsDelete, path);
            default:
                return null;
        }
    }


    private Endpoint match(Map<String, Registration> endpoints, String path) {
        Registration reg = endpoints.get(path);
        if (reg != null) {
            return reg.endpoint;
        } else {
            int count = countSlashes(path);
            // < 15 prevents DDOS attacks with intentionally maliciously composed urls that contain multiple slashes like
            // "/a/a/a/a/b/b/a/d/" in order to slow down the matching (because matching is rather expensive operation)
            if (count > 1 && count <= 15) {
                return match(endpoints, removeLastPathSegment(path));
            } else {
                return null;
            }
        }
    }

    static int countSlashes(String str) {
        return CharMatcher.is('/').countIn(str);
    }


    static String removeLastPathSegment(String path) {
        return path.substring(0, path.lastIndexOf('/'));
    }
}
