package net.dovale.okta.secure_rest_api;

import com.fasterxml.jackson.annotation.JsonValue;

public class TestJson {
    private String str = "lets see what happens";

    @JsonValue
    public String test()
    {
        return str;
    }
}
