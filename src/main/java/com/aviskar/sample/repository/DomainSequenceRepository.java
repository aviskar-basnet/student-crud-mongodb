package com.aviskar.sample.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.aviskar.sample.domain.DomainSequence;

@Repository
public interface DomainSequenceRepository extends MongoRepository<DomainSequence, String> {
	
}
