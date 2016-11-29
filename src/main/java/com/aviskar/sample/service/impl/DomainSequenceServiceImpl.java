package com.aviskar.sample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aviskar.sample.domain.DomainSequence;
import com.aviskar.sample.repository.DomainSequenceRepository;
import com.aviskar.sample.service.DomainSequenceService;

/**
 * Implementation of {@link DomainSequenceService}.
 * 
 * @author Aviskar
 * @since 1.0
 * @see {@link DomainSequence}
 *
 */
@Service
public class DomainSequenceServiceImpl implements DomainSequenceService {

	@Autowired
	private DomainSequenceRepository domainSequenceRepository;

	@Override
	public Long getNextSequenceValueById(String id) {
		DomainSequence resultDomainSequence = domainSequenceRepository.findOne(id);
		if (resultDomainSequence == null) {
			return null;
		}
		Long currentSequenceValue = resultDomainSequence.getSequenceValue();
		Long nextSequenceValue = ++currentSequenceValue;
		resultDomainSequence.setSequenceValue(nextSequenceValue);
		domainSequenceRepository.save(resultDomainSequence);
		return nextSequenceValue;
	}
}
