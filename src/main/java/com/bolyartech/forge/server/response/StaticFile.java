package com.bolyartech.forge.server.response;

import com.bolyartech.forge.server.Session;
import com.bolyartech.forge.server.misc.GzipUtils;
import com.bolyartech.forge.server.misc.MimeTypeResolver;
import com.bolyartech.forge.server.route.RequestContext;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;


public class StaticFile implements ResponseProducer {

    private final ClassLoader mClassLoader;
    private final String mPath;
    private final Response mNotFoundResponse;
    private final MimeTypeResolver mMimeTypeResolver;
    private final boolean mEnableGzip;


    public StaticFile(String path, Response notFoundResponse, MimeTypeResolver mimeTypeResolver, boolean enableGzip) {
        this(path, notFoundResponse, mimeTypeResolver, enableGzip, null);
    }


    public StaticFile(String path,
                      Response notFoundResponse,
                      MimeTypeResolver mimeTypeResolver,
                      boolean enableGzip,
                      ClassLoader classLoader) {

        if (path.startsWith("/")) {
            mPath = path.substring(1);
        } else {
            mPath = path;
        }

        if (classLoader != null) {
            mClassLoader = classLoader;
        } else {
            mClassLoader = this.getClass().getClassLoader();
        }
        mNotFoundResponse = notFoundResponse;
        mEnableGzip = enableGzip;
        mMimeTypeResolver = mimeTypeResolver;
    }


    @Override
    public Response produce(RequestContext ctx, Session session) throws ResponseException {
        String filePath = mPath + ctx.getRoutePath() + ctx.getPathInfoString();

        URL url = mClassLoader.getResource(filePath);
        if (url != null) {
            try {
                File f = new File(url.toURI());
                if (f.isFile()) {
                    boolean actualEnableGzip = mEnableGzip && GzipUtils.supportsGzip(ctx);
                    return new StaticFileResponse(mMimeTypeResolver, f, actualEnableGzip);
                } else {
                    return mNotFoundResponse;
                }
            } catch (URISyntaxException e) {
                throw new ResponseException(e);
            }
        } else {
            return mNotFoundResponse;
        }
    }
}
