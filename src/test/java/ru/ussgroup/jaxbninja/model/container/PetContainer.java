package ru.ussgroup.jaxbninja.model.container;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ru.ussgroup.jaxbninja.constant.Namespaces;
import ru.ussgroup.jaxbninja.model.abstractmodel.Pet;
import ru.ussgroup.jaxbninja.model.v1.CatV1;
import ru.ussgroup.jaxbninja.model.v1.DogV1;
import ru.ussgroup.jaxbninja.model.v2.CatV2;
import ru.ussgroup.jaxbninja.model.v2.DogV2;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PetContainer")
@XmlRootElement
public class PetContainer {
    @XmlElementWrapper(required = true)
    @XmlElements({
        @XmlElement(name = "cat", required = true, type = CatV1.class, namespace=Namespaces.V1),
        @XmlElement(name = "cat", required = true, type = CatV2.class, namespace=Namespaces.V2),
        @XmlElement(name = "dog", required = true, type = DogV1.class, namespace=Namespaces.V1),
        @XmlElement(name = "dog", required = true, type = DogV2.class, namespace=Namespaces.V2),
    })
    
    private List<Pet> pets = new ArrayList<>();
    
    public List<Pet> getPets() {
        return pets;
    }
}
