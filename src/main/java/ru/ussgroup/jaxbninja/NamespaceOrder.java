package ru.ussgroup.jaxbninja;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NamespaceOrder {
    private List<String> namespaces = new ArrayList<>();
    
    private NamespaceOrder(List<String> namespaces) {
        this.namespaces = namespaces;
    }

    public static NamespaceOrder create(String... namespaces) {
        return new NamespaceOrder(Arrays.asList(namespaces));
    }
    
    public int getOrder(String namespace) {
        return namespaces.indexOf(namespace);
    }
    
    public int getLength() {
        return namespaces.size();
    }
}
