package ola.api;

import ola.ejb.SampleSLSB;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/protected/helloworld")
public class ProtectedHelloWorldRS {

    @Inject
    private SampleSLSB sampleSLSB;

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage() {
        // Return some cliched textual content
        return sampleSLSB.getProtectedResource();
    }
}
