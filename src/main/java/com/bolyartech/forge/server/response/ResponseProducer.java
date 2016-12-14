package com.bolyartech.forge.server.response;

import com.bolyartech.forge.server.Session;
import com.bolyartech.forge.server.response.Response;
import com.bolyartech.forge.server.route.RequestContext;


public interface ResponseProducer {
    Response produce(RequestContext ctx, Session session) throws ResponseException;
}
