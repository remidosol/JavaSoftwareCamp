package kodlamaio.hrms.demo.tr.gov.nvi.tckimlik.ws;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TCKimlikNoDogrulaResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "tcKimlikNoDogrulaResult"
})
@XmlRootElement(name = "TCKimlikNoDogrulaResponse")
public class TCKimlikNoDogrulaResponse {

    @XmlElement(name = "TCKimlikNoDogrulaResult")
    protected boolean tcKimlikNoDogrulaResult;

    /**
     * Gets the value of the tcKimlikNoDogrulaResult property.
     */
    public boolean isTCKimlikNoDogrulaResult() {
        return tcKimlikNoDogrulaResult;
    }

    /**
     * Sets the value of the tcKimlikNoDogrulaResult property.
     */
    public void setTCKimlikNoDogrulaResult(boolean value) {
        this.tcKimlikNoDogrulaResult = value;
    }

}
