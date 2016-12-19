package com.bolyartech.forge.server.handler;

import com.bolyartech.forge.server.Session;
import com.bolyartech.forge.server.response.ResponseException;
import com.bolyartech.forge.server.response.forge.ForgeResponse;
import com.bolyartech.forge.server.response.forge.RequiresHttpsResponse;
import com.bolyartech.forge.server.route.RequestContext;


abstract public class ForgeSecureEndpoint implements ForgeHandlerInterface,  ForgeSecureEndpointInterface{
    @Override
    public ForgeResponse handleForge(RequestContext ctx, Session session) throws ResponseException {
        if (ctx.getScheme().equals("https")) {
            return handleSecure(ctx, session);
        } else {
            return new RequiresHttpsResponse();
        }
    }
}
