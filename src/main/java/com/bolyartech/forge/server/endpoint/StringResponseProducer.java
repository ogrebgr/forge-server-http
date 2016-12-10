package com.bolyartech.forge.server.endpoint;


import com.bolyartech.forge.server.Session;
import com.bolyartech.forge.server.response.StringResponse;


public interface StringResponseProducer extends ResponseProducer {
    StringResponse produce(RequestContext ctx, Session session, ResponseProperties responseProperties);
}
