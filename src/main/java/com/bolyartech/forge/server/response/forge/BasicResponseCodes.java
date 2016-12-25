package com.bolyartech.forge.server.response.forge;

import java.util.HashMap;
import java.util.Map;


public class BasicResponseCodes {
    public enum Oks implements ForgeResponseCode {
        OK(1); // used as general code that indicates success

        private final int code;


        Oks(int code) {
            if (code > 0) {
                this.code = code;
            } else {
                throw new IllegalArgumentException("Code must be positive");
            }
        }


        @Override
        public int getCode() {
            return code;
        }
    }


    public enum Errors implements ForgeResponseCode {
        ERROR(-1), // used as general error when we cant/don't want to specify more details
        MISSING_PARAMETERS(-2), // missing required parameters
        REQUIRES_HTTPS(-3), // HTTPS protocol must be used
        INVALID_PARAMETER_VALUE(-4), // parameter value is invalid. For example: string is passed where int is expected
        INTERNAL_SERVER_ERROR(-5), // some serious problem occurred on the server
        UPGRADE_NEEDED(-6); // client version is too old and unsupported


        private static final Map<Integer, Errors> mTypesByValue = new HashMap<>();

        static {
            for (Errors type : Errors.values()) {
                mTypesByValue.put(type.getCode(), type);
            }
        }


        private final int mCode;


        Errors(int code) {
            if (code < 0) {
                this.mCode = code;
            } else {
                throw new IllegalArgumentException("Code must be negative");
            }
        }


        public static Errors fromInt(int code) {
            Errors ret = mTypesByValue.get(code);
            if (ret != null) {
                return ret;
            } else {
                return null;
            }
        }


        @Override
        public int getCode() {
            return mCode;
        }
    }
}


