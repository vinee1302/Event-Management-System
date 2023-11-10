package event.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;


@Entity
@Table(name="EventRegistration")
public class EventRegistrationModel {
	@Id
	@GeneratedValue
	private long eventRegistrationId;
	@Column(nullable=false)
	private long numberOfTickets;
	@Column(nullable=false)
	private double tax;
	@Column(nullable=false)
	private double donations;
	@Column(nullable=false)
	private double totalAmount;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)	
	private Date date;
	
	@Column(nullable=false)
	private String status;
	
	@Transient
	private long studentId;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@ManyToOne(fetch =FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="studentId")
	private StudentModel studentModel;
	
	
	@Transient
	private long eventId;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@ManyToOne(fetch =FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="eventId")
	private EventModel eventModel;
	
	
	public long getEventRegistrationId() {
		return eventRegistrationId;
	}

	public void setEventRegistrationId(long eventRegistrationId) {
		this.eventRegistrationId = eventRegistrationId;
	}

	public long getNumberOfTickets() {
		return numberOfTickets;
	}

	public void setNumberOfTickets(long numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getDonations() {
		return donations;
	}

	public void setDonations(double donations) {
		this.donations = donations;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public StudentModel getStudentModel() {
		return studentModel;
	}

	public void setStudentModel(StudentModel studentModel) {
		this.studentModel = studentModel;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public EventModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(EventModel eventModel) {
		this.eventModel = eventModel;
	}


}

	




