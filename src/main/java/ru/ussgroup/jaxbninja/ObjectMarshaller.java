package ru.ussgroup.jaxbninja;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;

public class ObjectMarshaller {
    private Marshaller marshaller;
    
    private JAXBContext ctx;

    public ObjectMarshaller(Schema schema, Class<?> clazz) throws JAXBException {
        ctx = JAXBContext.newInstance(clazz);
        
        marshaller = ctx.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setSchema(schema);
    }
    
    public String marshalToString(Object o) throws JAXBException {
        StringWriter writer = new StringWriter();
        
        marshaller.marshal(o, writer);
        
        return writer.toString();
    }
}

