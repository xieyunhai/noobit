package com.xieyunhai.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.junit.Assert.*;

public class MockServerTest {

    @Test
    public void exactUrlOnly() {
        WireMock.configureFor("wiremock.host", 9999);
        removeAllMappings();

        stubFor(get(urlPathEqualTo("/order/1"))
            .willReturn(aResponse().withBody("{\"id\": 1}").withStatus(200)));
    }
}