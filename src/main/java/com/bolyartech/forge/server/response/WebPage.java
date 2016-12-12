package com.bolyartech.forge.server.response;

import com.bolyartech.forge.server.Session;
import com.bolyartech.forge.server.misc.TemplateEngineFactory;
import com.bolyartech.forge.server.route.RequestContext;


abstract public class WebPage implements WebPageInterface {
    private final TemplateEngineFactory mTemplateEngineFactory;


    public WebPage(TemplateEngineFactory templateEngineFactory) {
        mTemplateEngineFactory = templateEngineFactory;
    }


    @Override
    public Response produce(RequestContext ctx, Session session) {
        String content = produceHtml(ctx, session, mTemplateEngineFactory.createNew());

        return new HtmlResponse(content);
    }
}
