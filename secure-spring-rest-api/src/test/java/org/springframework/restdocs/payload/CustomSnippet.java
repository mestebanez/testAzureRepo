package org.springframework.restdocs.payload;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.operation.Operation;
import org.springframework.restdocs.payload.AbstractFieldsSnippet;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;
import org.springframework.restdocs.snippet.Attributes.Attribute;

public class CustomSnippet extends AbstractFieldsSnippet{
	private Class<?> cls;

	/**
	 * Creates a new {@code RequestFieldsSnippet} that will document the fields in the
	 * request using the given {@code descriptors}. Undocumented fields will trigger a
	 * failure.
	 * @param descriptors the descriptors
	 */
	protected CustomSnippet(List<FieldDescriptor> descriptors) {
		this(descriptors, null, false);
		
	}

	public CustomSnippet(Class cls) {
		this(new ArrayList<FieldDescriptor>());
		
		processAnnotationsAndRequestFields(cls);
	}
	
	/**
	 * Creates a new {@code RequestFieldsSnippet} that will document the fields in the
	 * request using the given {@code descriptors}. If {@code ignoreUndocumentedFields} is
	 * {@code true}, undocumented fields will be ignored and will not trigger a failure.
	 * @param descriptors the descriptors
	 * @param ignoreUndocumentedFields whether undocumented fields should be ignored
	 */
	protected CustomSnippet(List<FieldDescriptor> descriptors, boolean ignoreUndocumentedFields) {
		this(descriptors, null, ignoreUndocumentedFields);
	}

	/**
	 * Creates a new {@code RequestFieldsSnippet} that will document the fields in the
	 * request using the given {@code descriptors}. The given {@code attributes} will be
	 * included in the model during template rendering. Undocumented fields will trigger a
	 * failure.
	 * @param descriptors the descriptors
	 * @param attributes the additional attributes
	 */
	protected CustomSnippet(List<FieldDescriptor> descriptors, Map<String, Object> attributes) {
		this(descriptors, attributes, false);
	}

	/**
	 * Creates a new {@code RequestFieldsSnippet} that will document the fields in the
	 * request using the given {@code descriptors}. The given {@code attributes} will be
	 * included in the model during template rendering. If
	 * {@code ignoreUndocumentedFields} is {@code true}, undocumented fields will be
	 * ignored and will not trigger a failure.
	 * @param descriptors the descriptors
	 * @param attributes the additional attributes
	 * @param ignoreUndocumentedFields whether undocumented fields should be ignored
	 */
	protected CustomSnippet(List<FieldDescriptor> descriptors, Map<String, Object> attributes,
			boolean ignoreUndocumentedFields) {
		this(null, descriptors, attributes, ignoreUndocumentedFields);
	}

	/**
	 * Creates a new {@code RequestFieldsSnippet} that will document the fields in the
	 * subsection of the request extracted by the given {@code subsectionExtractor} using
	 * the given {@code descriptors}. Undocumented fields will trigger a failure.
	 * @param subsectionExtractor the subsection extractor
	 * @param descriptors the descriptors
	 * @since 1.2.0
	 */
	protected CustomSnippet(PayloadSubsectionExtractor<?> subsectionExtractor,
			List<FieldDescriptor> descriptors) {
		this(subsectionExtractor, descriptors, null, false);
	}

	/**
	 * Creates a new {@code RequestFieldsSnippet} that will document the fields in the
	 * subsection of the request extracted by the given {@code subsectionExtractor} using
	 * the given {@code descriptors}. If {@code ignoreUndocumentedFields} is {@code true},
	 * undocumented fields will be ignored and will not trigger a failure.
	 * @param subsectionExtractor the subsection extractor document
	 * @param descriptors the descriptors
	 * @param ignoreUndocumentedFields whether undocumented fields should be ignored
	 * @since 1.2.0
	 */
	protected CustomSnippet(PayloadSubsectionExtractor<?> subsectionExtractor, List<FieldDescriptor> descriptors,
			boolean ignoreUndocumentedFields) {
		this(subsectionExtractor, descriptors, null, ignoreUndocumentedFields);
	}

	/**
	 * Creates a new {@code RequestFieldsSnippet} that will document the fields in the
	 * subsection of the request extracted by the given {@code subsectionExtractor} using
	 * the given {@code descriptors}. The given {@code attributes} will be included in the
	 * model during template rendering. Undocumented fields will trigger a failure.
	 * @param subsectionExtractor the subsection extractor
	 * @param descriptors the descriptors
	 * @param attributes the additional attributes
	 * @since 1.2.0
	 */
	protected CustomSnippet(PayloadSubsectionExtractor<?> subsectionExtractor, List<FieldDescriptor> descriptors,
			Map<String, Object> attributes) {
		this(subsectionExtractor, descriptors, attributes, false);
	}

	/**
	 * Creates a new {@code RequestFieldsSnippet} that will document the fields in the
	 * subsection of the request extracted by the given {@code subsectionExtractor} using
	 * the given {@code descriptors}. The given {@code attributes} will be included in the
	 * model during template rendering. If {@code ignoreUndocumentedFields} is
	 * {@code true}, undocumented fields will be ignored and will not trigger a failure.
	 * @param subsectionExtractor the path identifying the subsection of the payload to
	 * document
	 * @param descriptors the descriptors
	 * @param attributes the additional attributes
	 * @param ignoreUndocumentedFields whether undocumented fields should be ignored
	 * @since 1.2.0
	 */
	protected CustomSnippet(PayloadSubsectionExtractor<?> subsectionExtractor, List<FieldDescriptor> descriptors,
			Map<String, Object> attributes, boolean ignoreUndocumentedFields) {
		super("request1", descriptors, attributes, ignoreUndocumentedFields, subsectionExtractor);
	}

	
	
	@Override
	protected MediaType getContentType(Operation operation) {
		return operation.getRequest().getHeaders().getContentType();
	}

	@Override
	protected byte[] getContent(Operation operation) throws IOException {
		return operation.getRequest().getContent();
	}
	
	
	/**
	 * Automate the capture of annotatons for an oject field and apply constraints to the associated field descriptors
	 */
	private void processAnnotationsAndRequestFields(Class cls)
	{
		ConstraintDescriptions userConstraints = new ConstraintDescriptions(cls);// does this class navigate down the class
		// hierarchy
		
		Method[] meths = cls.getDeclaredMethods();
		for(Method meth: meths)
		{
			if (meth.getName().startsWith("get"))
			{
				String m = meth.getName().replace("get", "");
				String path = m.toLowerCase();
				
				FieldDescriptor descriptor = new FieldDescriptor(path);
				descriptor = descriptor.type(meth.getReturnType());
				
				List<String> constraints = userConstraints.descriptionsForProperty(path);
				
				String consStr = "";
				for(String cons : constraints)
				{
					consStr = consStr + " "  + cons + ",\n";
					
				}
				
				if (consStr!=null)
				{
					Attribute att = new Attribute("constraints", consStr);
					descriptor.attributes(att);
				}
				
				descriptor.description("Get " + path);
				getFieldDescriptors().add(descriptor);
			}
		}
	}
	
	public void applyDescriptionToField(String message)
	{
		for(FieldDescriptor fd : getFieldDescriptors())
		{
			if (fd.getPath().equals(message))
			{
				
			}
		}
	}	
}
