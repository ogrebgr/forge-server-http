package com.bolyartech.forge.server.endpoint;

import com.bolyartech.forge.server.HttpMethod;
import com.google.common.base.Strings;


public interface EndpointRegister {
    void register(String moduleName, Endpoint ep);
    boolean isRegistered(Endpoint ep);
    Registration getRegistration(Endpoint ep);
    Endpoint match(HttpMethod method, String path);


    class Registration {
        public final String moduleName;
        public final Endpoint endpoint;


        public Registration(String moduleName, Endpoint endpoint) {
            if (Strings.isNullOrEmpty(moduleName)) {
                throw new IllegalArgumentException("moduleName is empty");
            }

            if (endpoint == null) {
                throw new NullPointerException("endpoint is null");
            }

            this.moduleName = moduleName;
            this.endpoint = endpoint;
        }
    }
}
