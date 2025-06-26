package com.example.properly.lease;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
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
class LeaseApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldCreateANewLease() {
        Lease newLease = new Lease();
        ResponseEntity<Void> createResponse = restTemplate.postForEntity("/leases", newLease, Void.class);

        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI locationOfNewLEase = createResponse.getHeaders().getLocation();
        ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewLEase, String.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
