package ru.ussgroup.jaxbninja;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ObjectValidator {
    private Marshaller marshaller;
    
    private Validator validator;
    
    private JAXBContext ctx;

    public ObjectValidator(Schema schema, Class<?> clazz) throws JAXBException {
        ctx = JAXBContext.newInstance(clazz);
        
        marshaller = ctx.createMarshaller();
        marshaller.setSchema(schema);
        
        validator = schema.newValidator();
    }
    
    public void validateObject(Object o) throws JAXBException {
        marshaller.marshal(o, new DefaultHandler());
    }
    
    public void validateObject2(Object o) throws SAXException, IOException, JAXBException {
        validator.validate(new JAXBSource(ctx, o));
    }
    
    public void validateStringXml(String xml) throws SAXException, IOException {
        validator.validate(new StreamSource(new StringReader(xml)));
    }
}
