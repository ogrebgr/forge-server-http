package com.bolyartech.forge.server.module;

import com.bolyartech.forge.server.HttpMethod;
import com.bolyartech.forge.server.endpoint.Endpoint;
import com.bolyartech.forge.server.endpoint.EndpointRegister;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;


public class ModuleRegisterImpl implements ModuleRegister {
    private final EndpointRegister mEndpointRegister;

    private final List<ForgeModule> mModules = new ArrayList<>();


    public ModuleRegisterImpl(EndpointRegister endpointRegister) {
        mEndpointRegister = endpointRegister;
    }


    @Override
    public void registerModule(ForgeModule mod) {
        if (!isModuleRegistered(mod)) {
            mModules.add(mod);
            for (Endpoint ep : mod.getEndpoints()) {
                if (!mEndpointRegister.isRegistered(ep)) {
                    mEndpointRegister.register(mod.getSystemName() + " (" + mod.getVersionName() + ")", ep);
                } else {
                    EndpointRegister.Registration reg = mEndpointRegister.getRegistration(ep);
                    throw new IllegalStateException(
                            MessageFormat.format("Path {0} already registered for module {1}",
                                    reg.endpoint.getPath(),
                                    reg.moduleName));
                }
            }
        } else {
            throw new IllegalStateException(MessageFormat.format("Module '{0}' already registered", mod.getSystemName()));
        }
    }


    @Override
    public boolean isModuleRegistered(ForgeModule mod) {
        boolean ret = false;

        for(ForgeModule m : mModules) {
            if (m.getSystemName().equalsIgnoreCase(mod.getSystemName())) {
                ret = true;
                break;
            }
        }

        return ret;
    }


    @Override
    public Endpoint match(HttpMethod method, String path) {
        return mEndpointRegister.match(method, path);
    }
}
