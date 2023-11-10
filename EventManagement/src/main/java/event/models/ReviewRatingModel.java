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
@Table(name="ReviewRating")
public class ReviewRatingModel {
	@Id
	@GeneratedValue
	private long reviewRatingId;
	@Column(nullable=false)
	private String review;
	@Column(nullable=false)
	private String rating;
	
	@Column(nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	

	@Transient
	private long eventRegistrationId;
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@ManyToOne(fetch =FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="eventRegistrationId")
	private EventRegistrationModel  eventRegistrationModel;
	
	public long getReviewRatingId() {
		return reviewRatingId;
	}
	public void setReviewRatingId(long reviewRatingId) {
		this.reviewRatingId = reviewRatingId;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public long getEventRegistrationId() {
		return eventRegistrationId;
	}
	public void setEventRegistrationId(long eventRegistrationId) {
		this.eventRegistrationId = eventRegistrationId;
	}
	public EventRegistrationModel getEventRegistrationModel() {
		return eventRegistrationModel;
	}
	public void setEventRegistrationModel(EventRegistrationModel eventRegistrationModel) {
		this.eventRegistrationModel = eventRegistrationModel;
	}

	

	}
	

	



