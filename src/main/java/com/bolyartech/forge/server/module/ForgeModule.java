package com.bolyartech.forge.server.module;

import com.bolyartech.forge.server.endpoint.Endpoint;

import java.util.List;


public interface ForgeModule {
    List<Endpoint> getEndpoints();
    String getSystemName();
    @SuppressWarnings("SameReturnValue")
    String getShortDescription();
    int getVersionCode();
    String getVersionName();
}
