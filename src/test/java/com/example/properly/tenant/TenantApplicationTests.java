package com.example.properly.tenant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TenantApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void ShouldCreateANewTenant() {
        Tenant newTenant = new Tenant("Peter", "Griffin", "petergrffin@gmail.com", "123-456-7890");
        ResponseEntity<Void> createResponse = restTemplate.postForEntity("/tenant", newTenant, Void.class);

        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI locationOfNewTenant = createResponse.getHeaders().getLocation();
        ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewTenant, String.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}