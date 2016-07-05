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
@XmlType(name = "CatV2")
public class CatV2 extends Pet {
    @XmlRegistry
    public static class Factory implements ObjectFactory<CatV2> {
        @Override
        @XmlElementDecl(name = "cat", namespace = Namespaces.V2)
        public JAXBElement<CatV2> createElement(CatV2 value) {
            return new JAXBElement<>(new QName("cat", Namespaces.V2), CatV2.class, value);
        }
    }
}
