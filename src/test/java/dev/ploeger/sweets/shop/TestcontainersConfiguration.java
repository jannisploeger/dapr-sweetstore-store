package dev.ploeger.sweets.shop;

import io.dapr.testcontainers.DaprContainer;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistrar;
import org.testcontainers.containers.Network;
import org.wiremock.integrations.testcontainers.WireMockContainer;

class TestcontainersConfiguration {

    private static final Network network = Network.newNetwork();

    @Bean
    @ServiceConnection
    public DaprContainer daprContainer(WireMockContainer wireMockContainer) {
        // Get the network alias instead of the base URL
        String wireServiceUrl = "http://" + wireMockContainer.getNetworkAliases().getFirst() + ":" + wireMockContainer.getPort();

        DaprContainer dapr = new DaprContainer("daprio/daprd:latest")
                .withAppName("local-dapr-app")
                .withNetwork(network)
//                .withComponent(new Component("warehouse", "http", "v1", Collections.singletonList(new MetadataEntry("url", wireServiceUrl))))
//                .withAppPort(8080)
                .withAppChannelAddress("host.testcontainers.internal");
        return dapr;
    }


    @Bean
    WireMockContainer wireMockContainer() {
        WireMockContainer wireMockContainer = new WireMockContainer("wiremock/wiremock:3.12.0-1")
                .withMappingFromResource("warehouse-service-stubs.json")
                .withNetwork(network);
        wireMockContainer.start();
        return wireMockContainer;
    }

    @Bean
    public DynamicPropertyRegistrar wireMockProperties(WireMockContainer wireMockContainer, DaprContainer dapr) {
        return registry -> {
            registry.add("dapr.grpc.port", () -> dapr.getMappedPort(50001));
            registry.add("dapr.http.port", () -> dapr.getMappedPort(3500));
        };
    }
}
