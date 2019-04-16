
package client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for list complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="list">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="facultyFilter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="courseFilter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "list", propOrder = {
    "facultyFilter",
    "courseFilter"
})
public class List {

    protected String facultyFilter;
    protected String courseFilter;

    /**
     * Gets the value of the facultyFilter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFacultyFilter() {
        return facultyFilter;
    }

    /**
     * Sets the value of the facultyFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFacultyFilter(String value) {
        this.facultyFilter = value;
    }

    /**
     * Gets the value of the courseFilter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCourseFilter() {
        return courseFilter;
    }

    /**
     * Sets the value of the courseFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCourseFilter(String value) {
        this.courseFilter = value;
    }

}
