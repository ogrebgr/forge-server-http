package com.bolyartech.forge.server;

import com.bolyartech.forge.server.response.ResponseProducer;
import com.bolyartech.forge.server.route.Route;
import com.bolyartech.forge.server.route.RouteImpl;
import com.bolyartech.forge.server.route.RouteRegister;
import com.bolyartech.forge.server.route.RouteRegisterImpl;
import com.bolyartech.forge.server.module.ForgeModule;
import com.bolyartech.forge.server.module.ModuleRegister;
import com.bolyartech.forge.server.module.ModuleRegisterImpl;
import com.bolyartech.forge.server.response.ResponseException;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


abstract public class MainServlet extends HttpServlet {
    private static final String DEFAULT_MODULE_NAME = "default_module";

    private final RouteRegister mRouteRegister = new RouteRegisterImpl();
    private final ModuleRegister mModuleRegister = new ModuleRegisterImpl(mRouteRegister);

    private final org.slf4j.Logger mLogger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Route route = mModuleRegister.match(HttpMethod.GET, req.getPathInfo());
        if (route != null) {
            handle(req, resp, route);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            PrintWriter pw = resp.getWriter();
            pw.print("Not found");
            pw.flush();
            pw.close();
        }
    }


    private void handle(HttpServletRequest req, HttpServletResponse resp, Route route) {
        try {
            route.handle(req, resp);
        } catch (ResponseException e) {
            mLogger.error("Error handling {}, Error: {}", route, e);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try {
                PrintWriter pw = resp.getWriter();
                pw.print("Internal server error");
                pw.flush();
                pw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }


    @Override
    public void init() throws ServletException {
        super.init();

        List<ForgeModule> modules = getModules();

        if (modules != null && modules.size() > 0) {
            for (ForgeModule mod : getModules()) {
                mModuleRegister.registerModule(mod);
            }
        } else {
            mLogger.error("getModules() returned empty list of modules, so no endpoints are registered.");
        }
    }


    public void addRoute(Route route) {
        mRouteRegister.register(DEFAULT_MODULE_NAME, route);
    }


    public void addRoute(HttpMethod httpMethod, String path, ResponseProducer responseProducer) {
        mRouteRegister.register(DEFAULT_MODULE_NAME, new RouteImpl(httpMethod, path, responseProducer));
    }


    protected abstract List<ForgeModule> getModules();


}
