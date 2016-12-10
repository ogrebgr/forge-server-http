package com.bolyartech.forge.server;

import com.bolyartech.forge.server.endpoint.Endpoint;
import com.bolyartech.forge.server.endpoint.EndpointRegisterImpl;
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
    private final ModuleRegister mModuleRegister = new ModuleRegisterImpl(new EndpointRegisterImpl());

    private final org.slf4j.Logger mLogger = LoggerFactory.getLogger(this.getClass());


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Endpoint endpoint = mModuleRegister.match(HttpMethod.GET, req.getPathInfo());
        if (endpoint != null) {
            handle(req, resp, endpoint);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            PrintWriter pw = resp.getWriter();
            pw.print("Not found");
            pw.flush();
            pw.close();
        }
    }


    private void handle(HttpServletRequest req, HttpServletResponse resp, Endpoint endpoint) {
        try {
            endpoint.handle(req, resp);
        } catch (ResponseException e) {
            mLogger.error("Error handling {}, Error: {}", endpoint, e);
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
        if (modules.size() > 0) {
            for (ForgeModule mod : getModules()) {
                mModuleRegister.registerModule(mod);
            }
        } else {
            mLogger.error("getModules() returned empty list of modules, so no endpoints are registered.");
        }
    }


    protected abstract List<ForgeModule> getModules();
}
