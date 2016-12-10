package com.bolyartech.forge.server.endpoint;

import com.bolyartech.forge.server.Session;
import com.bolyartech.forge.server.response.Response;


public interface ResponseProducer {
    Response produce(RequestContext ctx, Session session);
}
