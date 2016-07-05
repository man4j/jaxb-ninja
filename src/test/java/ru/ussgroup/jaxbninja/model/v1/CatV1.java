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
@XmlType(name = "CatV1")
public class CatV1 extends Pet {
    @XmlRegistry
    public static class Factory implements ObjectFactory<CatV1> {
        @Override
        @XmlElementDecl(name = "cat", namespace = Namespaces.V1)
        public JAXBElement<CatV1> createElement(CatV1 value) {
            return new JAXBElement<>(new QName(Namespaces.V1, "cat"), CatV1.class, value);
        }
    }
}
