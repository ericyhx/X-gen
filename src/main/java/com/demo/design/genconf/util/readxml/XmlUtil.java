package com.demo.design.genconf.util.readxml;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XmlUtil {
    public static Document getDocument(String fileName) throws Exception {
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(XmlUtil.class.getClassLoader().getResourceAsStream(fileName));
        document.normalize();
        return document;
    }
}
