package com.ashoospringboot.first.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashoospringboot.first.entity.Actor;
import com.ashoospringboot.first.model.Person;
import com.ashoospringboot.first.repository.ActorRepo;



@Service
public class WelcomeService {
	
	@Autowired
	private ActorRepo actorRepo;
	
	
	private List<Person> personSet = new ArrayList<>();
	
	public String helloUser(String name) {
		
		return "Hello " + name.toUpperCase() + " How are you?";
	}
	
	public String addNumbers(int fstnum, int sndnum) {
		
		return "Total is " + (fstnum + sndnum) + "".toUpperCase();
	}
	
	public List<Person> addPeople(Person person){
		
		personSet.add(person);
		
		return personSet;
	}
	
	public Long saveActor(Actor actor) {
		
		System.out.println(actor.getFirstName());
		System.out.println(actor.getLastName());
		
		return actorRepo.save(actor).getActorId();
		
		
	}
	
	public Optional<Actor> getActorById(Long id) {
		
		System.out.print(id);
		
	    return actorRepo.findById(id);
	}

}
