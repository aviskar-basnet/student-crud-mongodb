package com.aviskar.sample.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Domain class for storing the sequence value of other domain classes. This
 * domain class helps to maintain the auto increment feature for other domain
 * classes.
 * 
 * @author Aviskar
 * @since 1.0
 *
 */
@Document(collection = "domainSequence")
public class DomainSequence {

	@Id
	private String id;

	private long sequenceValue;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getSequenceValue() {
		return sequenceValue;
	}

	public void setSequenceValue(long sequenceValue) {
		this.sequenceValue = sequenceValue;
	}
}
