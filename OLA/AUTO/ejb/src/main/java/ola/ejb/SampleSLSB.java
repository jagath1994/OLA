package ola.ejb;

import javax.ejb.Stateless;

@Stateless
public class SampleSLSB {

    public String getProtectedResource() {
        return "This is a protected resource!";
    }
}
