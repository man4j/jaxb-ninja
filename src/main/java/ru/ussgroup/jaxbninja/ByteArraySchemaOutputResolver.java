package ru.ussgroup.jaxbninja;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class ByteArraySchemaOutputResolver extends SchemaOutputResolver {
    private ByteArrayOutputStream[] schemaList;
    
    private NamespaceOrder namespaceOrder;
    
    public ByteArraySchemaOutputResolver(NamespaceOrder namespaceOrder) {
        this.namespaceOrder = namespaceOrder;
        schemaList = new ByteArrayOutputStream[namespaceOrder.getLength()];
    }

    @Override
    public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        StreamResult result = new StreamResult(out);
        
        result.setSystemId("");
        
        if (!namespaceUri.isEmpty()) {
            schemaList[namespaceOrder.getOrder(namespaceUri)] = out;
        }
        
        return result;
    }
    
    public List<Source> getSources() {
        try {
            List<Source> sources = new ArrayList<>();
            
            for (ByteArrayOutputStream schema : schemaList) {
                sources.add(new StreamSource(new StringReader(schema.toString("UTF-8"))));
            }
            
            return sources;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public ByteArrayOutputStream[] getStreams() {
        return schemaList;
    }
}
