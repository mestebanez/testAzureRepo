/*
 * Copyright 2014-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.dovale.okta.secure_rest_api;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.CustomSnippet;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.snippet.Attributes.Attribute;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.dovale.okta.secure_rest_api.payload.HelloPayload;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiDocumentation {

	@Rule
	public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
				.apply(documentationConfiguration(this.restDocumentation)).build();
	}


	@Test
	public void notesCreateExample() throws Exception {

		ConstraintDescriptions userConstraints = new ConstraintDescriptions(HelloPayload.class); 
		List<String> descriptions = userConstraints.descriptionsForProperty("message");
		descriptions.stream().forEach((str)->System.out.println(str));
		String message = "{\r\n" + 
				"\"message\":\"bla\"\r\n" + 
				"}";
		this.mockMvc.perform(
				post("/hello1").contentType(MediaType.APPLICATION_JSON).content(
						message)).andExpect(
				status().is2xxSuccessful())
				.andDo(document("post-test",
						requestFieldsFromClass(HelloPayload.class)));
	
		
	}
	
	/**
	 * Returns a {@code Snippet} that will document the fields of the API operations's
	 * request payload. The fields will be documented using the given {@code descriptors}.
	 * <p>
	 * If a field is present in the request payload, but is not documented by one of the
	 * descriptors, a failure will occur when the snippet is invoked. Similarly, if a
	 * field is documented, is not marked as optional, and is not present in the request,
	 * a failure will also occur. For payloads with a hierarchical structure, documenting
	 * a field with a {@link #subsectionWithPath(String) subsection descriptor} will mean
	 * that all of its descendants are also treated as having been documented.
	 * <p>
	 * If you do not want to document a field or subsection, a descriptor can be
	 * {@link FieldDescriptor#ignored configured to ignore it}. The ignored field or
	 * subsection will not appear in the generated snippet and the failure described above
	 * will not occur.
	 * @param descriptors the descriptions of the request payload's fields
	 * @return the snippet that will document the fields
	 * @see #fieldWithPath(String)
	 * @see #subsectionWithPath(String)
	 */
	public static CustomSnippet requestFieldsFromClass(Class cls) {
		return new CustomSnippet(cls);
	}
	

}
