package org.viaadamo.spring.dto;

import org.viaadamo.spring.entity.util.PersonalType;

public record PersonalDTO(String name, PersonalType type, String eventName) {
}
