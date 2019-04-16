
package client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for editStudent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="editStudent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="studentCardId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="newName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newStudentCardId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="newFaculty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newSemester" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="newCourses" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="newAvatar" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "editStudent", propOrder = {
    "studentCardId",
    "newName",
    "newStudentCardId",
    "newFaculty",
    "newSemester",
    "newCourses",
    "newAvatar"
})
public class EditStudent {

    protected int studentCardId;
    protected String newName;
    protected Integer newStudentCardId;
    protected String newFaculty;
    protected Integer newSemester;
    protected List<String> newCourses;
    protected byte[] newAvatar;

    /**
     * Gets the value of the studentCardId property.
     * 
     */
    public int getStudentCardId() {
        return studentCardId;
    }

    /**
     * Sets the value of the studentCardId property.
     * 
     */
    public void setStudentCardId(int value) {
        this.studentCardId = value;
    }

    /**
     * Gets the value of the newName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewName() {
        return newName;
    }

    /**
     * Sets the value of the newName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewName(String value) {
        this.newName = value;
    }

    /**
     * Gets the value of the newStudentCardId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNewStudentCardId() {
        return newStudentCardId;
    }

    /**
     * Sets the value of the newStudentCardId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNewStudentCardId(Integer value) {
        this.newStudentCardId = value;
    }

    /**
     * Gets the value of the newFaculty property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewFaculty() {
        return newFaculty;
    }

    /**
     * Sets the value of the newFaculty property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewFaculty(String value) {
        this.newFaculty = value;
    }

    /**
     * Gets the value of the newSemester property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNewSemester() {
        return newSemester;
    }

    /**
     * Sets the value of the newSemester property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNewSemester(Integer value) {
        this.newSemester = value;
    }

    /**
     * Gets the value of the newCourses property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the newCourses property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNewCourses().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getNewCourses() {
        if (newCourses == null) {
            newCourses = new ArrayList<String>();
        }
        return this.newCourses;
    }

    /**
     * Gets the value of the newAvatar property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getNewAvatar() {
        return newAvatar;
    }

    /**
     * Sets the value of the newAvatar property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setNewAvatar(byte[] value) {
        this.newAvatar = value;
    }

}
