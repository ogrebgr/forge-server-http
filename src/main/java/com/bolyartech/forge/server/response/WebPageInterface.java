package com.bolyartech.forge.server.response;

import com.bolyartech.forge.server.Session;
import com.bolyartech.forge.server.misc.TemplateEngine;
import com.bolyartech.forge.server.route.RequestContext;


public interface WebPageInterface extends ResponseProducer {
    String produceHtml(RequestContext ctx, Session session, TemplateEngine tple);
}
