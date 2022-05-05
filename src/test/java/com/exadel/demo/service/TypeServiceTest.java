package com.exadel.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.exadel.demo.dto.TypeDto;
import com.exadel.demo.entity.Type;
import com.exadel.demo.repository.TypeRepository;

public class TypeServiceTest {
	@Mock
	private TypeRepository typeRepository;

	@Mock
	private ModelMapper modelMapper;

	private Type type;

	private Type savedType;

	private TypeService typeService;

	private TypeDto typeDto;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		typeService = new TypeService(typeRepository, modelMapper);
		type = new Type(1L, "type");
		savedType = new Type(1L, "type");
		typeDto = new TypeDto("type");

		when(modelMapper.map(typeDto, Type.class)).thenReturn(type);
		when(modelMapper.map(savedType, TypeDto.class)).thenReturn(typeDto);
		when(typeRepository.save(type)).thenReturn(savedType);
	}

	@Test
	void add() {
		TypeDto typeDtoSaved = typeService.add(typeDto);

		assertNotNull(typeDtoSaved);
		assertEquals("type", typeDtoSaved.getType());
	}

}
