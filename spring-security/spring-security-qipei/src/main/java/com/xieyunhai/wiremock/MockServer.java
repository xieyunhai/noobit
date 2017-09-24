package com.xieyunhai.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class MockServer {
    public static void main(String[] args) {

        System.out.println("hello");

        configureFor(9999);
        removeAllMappings();

        stubFor(get(urlPathEqualTo("/order/1"))
            .willReturn(aResponse().withBody("{\"id\": 1}").withStatus(200)));
    }
}
