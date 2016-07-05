package ru.ussgroup.jaxbninja;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;

public class ObjectUnmarshaller {
    private Unmarshaller unmarshaller;
    
    private JAXBContext ctx;

    public ObjectUnmarshaller(Schema schema, Class<?> clazz) throws JAXBException {
        ctx = JAXBContext.newInstance(clazz);
        
        unmarshaller = ctx.createUnmarshaller();
        unmarshaller.setSchema(schema);
    }
    
    @SuppressWarnings("unchecked")
    public <T> T unmarshal(String xml) throws JAXBException {
        return (T) unmarshaller.unmarshal(new StringReader(xml));
    }
}

