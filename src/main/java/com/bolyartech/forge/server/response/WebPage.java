package com.bolyartech.forge.server.response;

import com.bolyartech.forge.server.Session;
import com.bolyartech.forge.server.misc.TemplateEngineFactory;
import com.bolyartech.forge.server.route.RequestContext;


abstract public class WebPage implements WebPageInterface {
    private final TemplateEngineFactory mTemplateEngineFactory;
    private final boolean mEnableGzipSupport;


    public WebPage(TemplateEngineFactory templateEngineFactory) {
        mTemplateEngineFactory = templateEngineFactory;
        mEnableGzipSupport = false;
    }


    public WebPage(TemplateEngineFactory templateEngineFactory, boolean enableGzipSupport) {
        mTemplateEngineFactory = templateEngineFactory;
        mEnableGzipSupport = enableGzipSupport;
    }


    @Override
    public Response produce(RequestContext ctx, Session session) {
        String content = produceHtml(ctx, session, mTemplateEngineFactory.createNew());

        return new HtmlResponse(content, mEnableGzipSupport);
    }
}
