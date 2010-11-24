package com.example.jaxb_any;

import static org.junit.Assert.*;

import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;
import org.w3c.dom.Element;

import com.example.schema.Container;

public class TestAny {

	@Test
	public void deserialise() throws Exception {
		JAXBContext context = JAXBContext.newInstance("com.example.schema");
		Unmarshaller unmarshaller = context.createUnmarshaller();
		JAXBElement<Container> element = (JAXBElement<Container>) unmarshaller
				.unmarshal(getClass().getResource("/example.xml"));

		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(element, System.out);

		Container container = element.getValue();

		assertEquals(1, container.getRootFiles().getAny2().size());
		assertEquals(1, container.getRootFiles().getAny3().size());

		Element any2_0 = (Element) container.getRootFiles().getAny2().get(0);
		assertEquals("before rootFile", any2_0.getTextContent());

		Element any3_0 = (Element) container.getRootFiles().getAny3().get(0);
		assertEquals("after rootFile", any3_0.getTextContent());
	}

}
