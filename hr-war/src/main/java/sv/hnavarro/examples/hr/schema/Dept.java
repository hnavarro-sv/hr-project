
package sv.hnavarro.examples.hr.schema;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dept complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dept">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="departmentId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="departmentName">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="emps" type="{http://hnavarro-sv.blogspot.com}emps"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dept", namespace = "http://hnavarro-sv.blogspot.com", propOrder = {
    "departmentId",
    "departmentName",
    "emps"
})
public class Dept
    implements Serializable
{

    private final static long serialVersionUID = -12345L;
    @XmlElement(namespace = "http://hnavarro-sv.blogspot.com")
    protected int departmentId;
    @XmlElement(namespace = "http://hnavarro-sv.blogspot.com", required = true)
    protected String departmentName;
    @XmlElement(namespace = "http://hnavarro-sv.blogspot.com", required = true)
    protected Emps emps;

    /**
     * Gets the value of the departmentId property.
     * 
     */
    public int getDepartmentId() {
        return departmentId;
    }

    /**
     * Sets the value of the departmentId property.
     * 
     */
    public void setDepartmentId(int value) {
        this.departmentId = value;
    }

    /**
     * Gets the value of the departmentName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * Sets the value of the departmentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartmentName(String value) {
        this.departmentName = value;
    }

    /**
     * Gets the value of the emps property.
     * 
     * @return
     *     possible object is
     *     {@link Emps }
     *     
     */
    public Emps getEmps() {
        return emps;
    }

    /**
     * Sets the value of the emps property.
     * 
     * @param value
     *     allowed object is
     *     {@link Emps }
     *     
     */
    public void setEmps(Emps value) {
        this.emps = value;
    }

}
