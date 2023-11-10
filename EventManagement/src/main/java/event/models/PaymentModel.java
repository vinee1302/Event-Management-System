package event.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table(name="Payment")
public class PaymentModel {
	@Id
	@GeneratedValue
	private long paymentId;
	@Column(nullable=false)
	private String paymentType;
	@Column(nullable = false ,insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private String date;
	@Column(nullable=false)
	private String status;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@OneToOne(fetch =FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="eventRegistrationId")
	private EventRegistrationModel  eventregistrationModel;
	

	public long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public EventRegistrationModel getEventregistrationModel() {
		return eventregistrationModel;
	}
	public void setEventregistrationModel(EventRegistrationModel eventregistrationModel) {
		this.eventregistrationModel = eventregistrationModel;
	}

}
