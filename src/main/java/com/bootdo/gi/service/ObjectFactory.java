
package com.bootdo.gi.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.bootdo.gi.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Doit_QNAME = new QName("http://service.gi.bootdo.com/", "doit");
    private final static QName _SayHi_QNAME = new QName("http://service.gi.bootdo.com/", "sayHi");
    private final static QName _SayHiResponse_QNAME = new QName("http://service.gi.bootdo.com/", "sayHiResponse");
    private final static QName _DoitResponse_QNAME = new QName("http://service.gi.bootdo.com/", "doitResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.bootdo.gi.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SayHiResponse }
     * 
     */
    public SayHiResponse createSayHiResponse() {
        return new SayHiResponse();
    }

    /**
     * Create an instance of {@link DoitResponse }
     * 
     */
    public DoitResponse createDoitResponse() {
        return new DoitResponse();
    }

    /**
     * Create an instance of {@link Doit }
     * 
     */
    public Doit createDoit() {
        return new Doit();
    }

    /**
     * Create an instance of {@link SayHi }
     * 
     */
    public SayHi createSayHi() {
        return new SayHi();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Doit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.gi.bootdo.com/", name = "doit")
    public JAXBElement<Doit> createDoit(Doit value) {
        return new JAXBElement<Doit>(_Doit_QNAME, Doit.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHi }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.gi.bootdo.com/", name = "sayHi")
    public JAXBElement<SayHi> createSayHi(SayHi value) {
        return new JAXBElement<SayHi>(_SayHi_QNAME, SayHi.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHiResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.gi.bootdo.com/", name = "sayHiResponse")
    public JAXBElement<SayHiResponse> createSayHiResponse(SayHiResponse value) {
        return new JAXBElement<SayHiResponse>(_SayHiResponse_QNAME, SayHiResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DoitResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.gi.bootdo.com/", name = "doitResponse")
    public JAXBElement<DoitResponse> createDoitResponse(DoitResponse value) {
        return new JAXBElement<DoitResponse>(_DoitResponse_QNAME, DoitResponse.class, null, value);
    }

}
