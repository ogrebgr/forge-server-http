package com.bolyartech.forge.server.handler;

import com.bolyartech.forge.server.Session;
import com.bolyartech.forge.server.handler.Handler;
import com.bolyartech.forge.server.misc.TemplateEngine;
import com.bolyartech.forge.server.route.RequestContext;


public interface WebPageInterface extends Handler {
    String produceHtml(RequestContext ctx, Session session, TemplateEngine tple);
}
