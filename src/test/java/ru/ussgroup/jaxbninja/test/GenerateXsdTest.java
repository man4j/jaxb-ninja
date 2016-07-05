package ru.ussgroup.jaxbninja.test;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.validation.Schema;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import ru.ussgroup.jaxbninja.NamespaceOrder;
import ru.ussgroup.jaxbninja.ObjectMarshaller;
import ru.ussgroup.jaxbninja.ObjectUnmarshaller;
import ru.ussgroup.jaxbninja.ObjectValidator;
import ru.ussgroup.jaxbninja.SchemaGenerator;
import ru.ussgroup.jaxbninja.constant.Namespaces;
import ru.ussgroup.jaxbninja.model.container.PetContainer;
import ru.ussgroup.jaxbninja.model.v1.CatV1;
import ru.ussgroup.jaxbninja.model.v1.DogV1;
import ru.ussgroup.jaxbninja.model.v2.CatV2;
import ru.ussgroup.jaxbninja.model.v2.DogV2;

public class GenerateXsdTest {
    @Test
    public void shouldGenerateXsdInCorrectOrder() throws JAXBException, SAXException, IOException {
        SchemaGenerator schemaGenerator = new SchemaGenerator(NamespaceOrder.create(Namespaces.ABSTRACT, Namespaces.V1, Namespaces.V2, Namespaces.CONTAINER), PetContainer.class);
        
        Schema schema = schemaGenerator.generateXsd();
        
        Assert.assertNotNull(schema);
        
        for (String stringSchema : schemaGenerator.generateXsdAsStrings()) {
            System.out.println(stringSchema);
            
            Assert.assertNotNull(stringSchema);
        }
        
        CatV1 cat = new CatV1();
        
        PetContainer container = new PetContainer();
        
        container.getPets().add(cat);
        
        ObjectValidator validator = new ObjectValidator(schema, PetContainer.class);
        
        validator.validateObject(container);
        validator.validateObject2(container);
    }
    
    @Test
    public void shouldGenerateForNonRootEntity() throws JAXBException, SAXException, IOException {
        SchemaGenerator schemaGenerator = new SchemaGenerator(NamespaceOrder.create(Namespaces.ABSTRACT, Namespaces.V1), CatV1.class, CatV1.Factory.class);
        
        Schema schema = schemaGenerator.generateXsd();
        
        Assert.assertNotNull(schema);
        
        for (String stringSchema : schemaGenerator.generateXsdAsStrings()) {
            System.out.println(stringSchema);
            
            Assert.assertNotNull(stringSchema);
        }
        
        CatV1 cat = new CatV1();
        
        ObjectValidator validator = new ObjectValidator(schema, CatV1.class);
        validator.validateObject(new CatV1.Factory().createElement(cat));
        validator.validateObject2(new CatV1.Factory().createElement(cat));
    }
    
    @Test
    public void shouldValidateAndUnmarshallPolymorphicXml() throws JAXBException, SAXException, IOException {
        SchemaGenerator schemaGenerator = new SchemaGenerator(NamespaceOrder.create(Namespaces.ABSTRACT, Namespaces.V1, Namespaces.V2, Namespaces.CONTAINER), PetContainer.class);
        
        Schema schema = schemaGenerator.generateXsd();
        
        CatV1 catV1 = new CatV1();
        catV1.setName("Мурзик");
        
        CatV2 catV2 = new CatV2();
        catV2.setName("Пушок");
        
        DogV1 dogV1 = new DogV1();
        dogV1.setName("Тобик");
        
        DogV2 dogV2 = new DogV2();
        dogV2.setName("Барбос");
        
        PetContainer container = new PetContainer();
        
        container.getPets().add(catV1);
        container.getPets().add(catV2);
        container.getPets().add(dogV1);
        container.getPets().add(dogV2);
        
        String xml = new ObjectMarshaller(schema, PetContainer.class).marshalToString(container);
        
        System.out.println(xml);
        
        ObjectValidator validator = new ObjectValidator(schema, PetContainer.class);
        validator.validateObject(container);
        validator.validateStringXml(xml);
        
        container = new ObjectUnmarshaller(schema, PetContainer.class).unmarshal(xml);
        
        Assert.assertEquals(4, container.getPets().size());
    }
}
