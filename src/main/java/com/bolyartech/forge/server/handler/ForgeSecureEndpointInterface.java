package com.bolyartech.forge.server.handler;

import com.bolyartech.forge.server.session.Session;
import com.bolyartech.forge.server.response.ResponseException;
import com.bolyartech.forge.server.response.forge.ForgeResponse;
import com.bolyartech.forge.server.route.RequestContext;


public interface ForgeSecureEndpointInterface {
    ForgeResponse handleSecure(RequestContext ctx, Session session) throws ResponseException;
}
