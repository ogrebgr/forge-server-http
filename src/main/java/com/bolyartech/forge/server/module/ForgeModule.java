package com.bolyartech.forge.server.module;

import com.bolyartech.forge.server.route.Route;

import java.util.List;


public interface ForgeModule {
    List<Route> createRoutes();

    String getSystemName();

    @SuppressWarnings("SameReturnValue")
    String getShortDescription();

    int getVersionCode();

    String getVersionName();
}
