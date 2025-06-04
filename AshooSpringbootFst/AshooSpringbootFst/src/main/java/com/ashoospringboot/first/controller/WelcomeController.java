package com.ashoospringboot.first.controller;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashoospringboot.first.entity.Actor;
import com.ashoospringboot.first.model.Person;
import com.ashoospringboot.first.service.WelcomeService;

@RestController
public class WelcomeController {
	
	@Autowired
    private WelcomeService welcomeService;
	
	@GetMapping("/welcome")
	public String welcome()  throws UnknownHostException{
		
		InetAddress ip = InetAddress.getLocalHost();
		return "welcome to ashoo springboot first application" + ip.getHostAddress();
	}

	@GetMapping("/helloUser")
	public String helloUser(
			@RequestParam( value = "name") String name)
	{
		return welcomeService.helloUser(name);
	}
	
	@GetMapping("/helloFriend")
	public Person helloFriend(
			@RequestParam( value = "fstName") String fstname,
			@RequestParam( value = "lstName") String lstname,
			@RequestParam( value = "age") Integer age)
	{
		
		return new Person(fstname, lstname, age);
	}
	
	@GetMapping("/addNumbers")
	public String addNumbers(
			@RequestParam( value = "firstNum") int fstnum, 
			@RequestParam( value = "secondNum") int sndnum)
	{
		return welcomeService.addNumbers(fstnum, sndnum);
	}
	
	@PostMapping("helluFriend")
	public List<Person> helluFriend(@RequestBody Person person) {
		
		return welcomeService.addPeople(person);

	}
	
	@GetMapping("/getActorById")
	public Optional<Actor> getActorById(
			@RequestParam( value = "id") Long id)
	{
		return welcomeService.getActorById(id);
	}
	
	@PostMapping("/saveActor")
	public Long saveActor(@RequestBody Actor actor) {
		
		return welcomeService.saveActor(actor);

	}
}
