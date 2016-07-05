package ru.ussgroup.jaxbninja;

import javax.xml.bind.JAXBElement;

public interface ObjectFactory<T> {
    JAXBElement<T> createElement(T value);
}
