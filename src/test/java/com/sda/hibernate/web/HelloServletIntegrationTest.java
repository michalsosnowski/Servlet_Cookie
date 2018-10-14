package com.sda.hibernate.web;

import org.apache.catalina.startup.Tomcat;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloServletIntegrationTest {

    private Tomcat tomcat;
    private CloseableHttpClient httpClient;

    @BeforeEach
    public void beforeEach() throws Exception {
        tomcat = EmbeddedTomcatFactory.create(randomPort());
        tomcat.start();
        httpClient = HttpClientBuilder.create().build();

    }

    private int randomPort() {
        return new Random().nextInt(10000) + 8080;
    }

    @AfterEach
    public void afterEach() throws Exception {
        tomcat.stop();
    }

    @DisplayName("should return status 302 when post on /book")
    @Test
    public void test0() throws Exception {
        // given
        String uri = uri("/book");

        // when
        try (CloseableHttpResponse response = HttpClientBuilder.create()
                .build().execute(new HttpPost(uri))) {

            // then
            assertThat(response.getStatusLine().getStatusCode()).isEqualTo
                    (HttpStatus.SC_MOVED_TEMPORARILY);
        }

    }

    @DisplayName("should show form data after post on /book")
    @Test
    public void test01() throws Exception {

        //given
        String uri = uri("/book");

        List<NameValuePair> postParameters = Arrays.asList(
                new BasicNameValuePair("title", "Thinking in Java"),
                new BasicNameValuePair("isbn", "123456789"));
        HttpPost post = new HttpPost(uri);
        post.setEntity(new UrlEncodedFormEntity(postParameters,
                "UTF-8"));

        //when
        httpClient.execute(post);
        //then
        HttpResponse getResponse = httpClient.execute(new HttpGet(uri));
        String htmlResponse = EntityUtils.toString(getResponse.getEntity());
        assertThat(htmlResponse).contains("Thinking in Java", "123456789");
    }


    private String uri(String endpoint) {
        return String.format("http://localhost:%s/%s", tomcat
                .getConnector().getPort(), endpoint);
    }
}
