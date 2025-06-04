package com.ashoospringboot.first.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "actor")
public class Actor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "actor_id")
	private Long actorId;
	
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	
	public Actor() {}

    public Actor(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
	
	public Long getActorId() {
		return actorId;
	}
	public void setActorId(Long actorId) {
		this.actorId = actorId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		return "Actor [actirId=" + actorId + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	

}
