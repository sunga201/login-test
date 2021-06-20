package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Jitendra Singh
 * @since 4.2
 */
public class WebAuthenticationDetailsMixInTest extends AbstractMixinTest {

	@Autowired
	private ObjectMapper objectMapper;
	
	// @formatter:off
	private static final String AUTHENTICATION_DETAILS_JSON = "{"
		+ "\"@class\": \"org.springframework.security.web.authentication.WebAuthenticationDetails\","
		+ "\"sessionId\": \"1\", "
		+ "\"remoteAddress\": "
		+ "\"/localhost\""
	+ "}";
	// @formatter:on
	@Test
	public void buildWebAuthenticationDetailsUsingDifferentConstructors() throws IOException {
		System.out.println("objectMapper : " + objectMapper);
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setRemoteAddr("localhost");
		request.setSession(new MockHttpSession(null, "1"));
		WebAuthenticationDetails details = new WebAuthenticationDetails(request);
		WebAuthenticationDetails authenticationDetails = objectMapper.readValue(AUTHENTICATION_DETAILS_JSON,
				WebAuthenticationDetails.class);
		assertThat(details.equals(authenticationDetails));
	}

	@Test
	public void webAuthenticationDetailsSerializeTest() throws JsonProcessingException, JSONException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setRemoteAddr("/localhost");
		request.setSession(new MockHttpSession(null, "1"));
		WebAuthenticationDetails details = new WebAuthenticationDetails(request);
		String actualJson = this.mapper.writeValueAsString(details);
		JSONAssert.assertEquals(AUTHENTICATION_DETAILS_JSON, actualJson, true);
	}

	@Test
	public void webAuthenticationDetailsDeserializeTest() throws IOException {
		WebAuthenticationDetails details = this.mapper.readValue(AUTHENTICATION_DETAILS_JSON,
				WebAuthenticationDetails.class);
		assertThat(details).isNotNull();
		assertThat(details.getRemoteAddress()).isEqualTo("/localhost");
		assertThat(details.getSessionId()).isEqualTo("1");
	}

}