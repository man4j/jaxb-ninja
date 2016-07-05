package ru.ussgroup.jaxbninja.model.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;

import ru.ussgroup.jaxbninja.ObjectFactory;
import ru.ussgroup.jaxbninja.constant.Namespaces;
import ru.ussgroup.jaxbninja.model.abstractmodel.Pet;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DogV1")
public class DogV1 extends Pet {
    @XmlRegistry
    public static class Factory implements ObjectFactory<DogV1> {
        @Override
        @XmlElementDecl(name = "dog", namespace = Namespaces.V1)
        public JAXBElement<DogV1> createElement(DogV1 value) {
            return new JAXBElement<>(new QName("dog", Namespaces.V1), DogV1.class, value);
        }
    }
}
