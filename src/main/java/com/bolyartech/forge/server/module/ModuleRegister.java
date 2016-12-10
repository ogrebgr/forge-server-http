package com.bolyartech.forge.server.module;

import com.bolyartech.forge.server.HttpMethod;
import com.bolyartech.forge.server.endpoint.Endpoint;


public interface ModuleRegister {
    void registerModule(ForgeModule mod);
    boolean isModuleRegistered(ForgeModule mod);
    Endpoint match(HttpMethod method, String path);
}
