package com.bolyartech.forge.server.endpoint;

import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class EndpointRegisterImplTest {
    @Test
    public void testRemoveLastPathSegment() {
        String source = "/presni/chudesni";
        String result = EndpointRegisterImpl.removeLastPathSegment(source);

        assertTrue("Last segment not removed. result: "+ result, result.equals("/presni"));
    }

    @Test
    public void testCountSlashes() {
        String source = "/presni/chudesni";
        int count = EndpointRegisterImpl.countSlashes(source);
        assertTrue("Invalid count: " + count, count == 2);
    }
}
