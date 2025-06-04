package com.ashoospringboot.first.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.ashoospringboot.first.entity.Actor;

@Repository
public interface ActorRepo extends JpaRepository<Actor, Long>{


}
