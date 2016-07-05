package ru.ussgroup.jaxbninja.model.v2;

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
@XmlType(name = "DogV2")
public class DogV2 extends Pet {
    @XmlRegistry
    public static class Factory implements ObjectFactory<DogV2> {
        @Override
        @XmlElementDecl(name = "dog", namespace = Namespaces.V2)
        public JAXBElement<DogV2> createElement(DogV2 value) {
            return new JAXBElement<>(new QName("dog", Namespaces.V2), DogV2.class, value);
        }
    }
}
