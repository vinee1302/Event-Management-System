package event.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;


@Entity
@Table(name="Event")
public class EventModel {
	
	@Id
	@GeneratedValue
	private long eventId;
	@Column(nullable=false)
	private String eventTitle;
	@Lob
	@Column(nullable = false,columnDefinition="MEDIUMBLOB")
	private String poster;
	@Column(nullable=false)
	private String registrationFee;
	
	@JsonFormat(pattern="dd-MM-yyyy HH:mm")
	@Column(nullable = false)
	private Date startDate;
	@Transient
	private String startDate2;
	
	@Transient
	private String rating; 
	
	@Column(nullable=false)
	private String description;
	@Column(nullable=false)
	private String partispentCriteria;
	@Column(nullable=false)
	private String costPerPerson;
	@Column(nullable=false)
	private String eventType;
	@Column(nullable=false)
	private int totalSeats;
	@Transient
	private int remainingSeats;
	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	public int getRemainingSeats() {
		return remainingSeats;
	}
	public void setRemainingSeats(int remainingSeats) {
		this.remainingSeats = remainingSeats;
	}
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@ManyToOne(fetch =FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="staffId",nullable = true)
	private StaffModel staffModel;
	public long getEventId() {
		return eventId;
	}
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getRegistrationFee() {
		return registrationFee;
	}
	public void setRegistrationFee(String registrationFee) {
		this.registrationFee = registrationFee;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getStartDate2() {
		return startDate2;
	}
	public void setStartDate2(String startDate2) {
		this.startDate2 = startDate2;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPartispentCriteria() {
		return partispentCriteria;
	}
	public void setPartispentCriteria(String partispentCriteria) {
		this.partispentCriteria = partispentCriteria;
	}
	public String getCostPerPerson() {
		return costPerPerson;
	}
	public void setCostPerPerson(String costPerPerson) {
		this.costPerPerson = costPerPerson;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public StaffModel getStaffModel() {
		return staffModel;
	}
	public void setStaffModel(StaffModel staffModel) {
		this.staffModel = staffModel;
	}


}

