package ru.ussgroup.jaxbninja;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.transform.Source;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

public class SchemaGenerator {
    private JAXBContext ctx;
    
    private NamespaceOrder namespaceOrder;
    
    public SchemaGenerator(NamespaceOrder namespaceOrder, Class<?>... clazz) throws JAXBException {
        ctx = JAXBContext.newInstance(clazz);
        this.namespaceOrder = namespaceOrder;
    }

    public Schema generateXsd() {
        try {
            ByteArraySchemaOutputResolver resolver = new ByteArraySchemaOutputResolver(namespaceOrder);
            
            ctx.generateSchema(resolver);
            
            List<Source> sources = resolver.getSources();
            
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            
            return sf.newSchema(sources.toArray(new Source[] {}));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<String> generateXsdAsStrings() {
        try {
            ByteArraySchemaOutputResolver resolver = new ByteArraySchemaOutputResolver(namespaceOrder);
            
            ctx.generateSchema(resolver);
            
            List<String> xsds = new ArrayList<>();

            for (ByteArrayOutputStream out : resolver.getStreams()) {
                xsds.add(out.toString("UTF-8"));
            }
            
            return xsds;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
