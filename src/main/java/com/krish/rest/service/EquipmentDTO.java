package com.krish.rest.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EquipmentDTO {
	@JsonProperty(value = "EquipmentModelDesc")
	private String equipmentModelDesc;
	
	@JsonProperty(value = "OwnerDesc")
	private String ownerDesc;
}
