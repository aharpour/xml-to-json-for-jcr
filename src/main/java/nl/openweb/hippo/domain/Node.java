//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.05.15 at 08:03:19 PM CEST 
//


package nl.openweb.hippo.domain;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element ref="{http://www.jcp.org/jcr/sv/1.0}node"/>
 *         &lt;element ref="{http://www.jcp.org/jcr/sv/1.0}property"/>
 *       &lt;/choice>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "nodeOrProperty"
})
@XmlRootElement(name = "node")
public class Node {

    @XmlElements({
        @XmlElement(name = "node", type = Node.class),
        @XmlElement(name = "property", type = Property.class)
    })
    protected List<Object> nodeOrProperty;
    @XmlAttribute(name = "name", namespace = "http://www.jcp.org/jcr/sv/1.0", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String name;

    /**
     * Gets the value of the nodeOrProperty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nodeOrProperty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNodeOrProperty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Node }
     * {@link Property }
     * 
     * 
     */
    public List<Object> getNodeOrProperty() {
        if (nodeOrProperty == null) {
            nodeOrProperty = new ArrayList<Object>();
        }
        return this.nodeOrProperty;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
