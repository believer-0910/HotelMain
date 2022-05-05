package com.exadel.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.exadel.demo.dto.TypeDto;
import com.exadel.demo.entity.Type;
import com.exadel.demo.repository.TypeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		when(typeRepository.findById(1L)).thenReturn(Optional.of(type));

	}

	@Test
	void add() {
		TypeDto typeDtoSaved = typeService.add(typeDto);
		assertNotNull(typeDtoSaved);
		assertEquals("type", typeDtoSaved.getType());
	}

	@Test
	void getAll(){
		List<TypeDto> all = typeService.getAll();
		when(typeRepository.findAll()).thenReturn(new ArrayList<>());
		assertNull(all);
	}

	@Test
	void get(){
		when(modelMapper.map(type, TypeDto.class)).thenReturn(typeDto);
		TypeDto typeDto = typeService.get(1L);
		assertNotNull(typeDto);
		assertEquals("type", typeDto.getType());
	}

	@Test
	void update(){
		TypeDto typeDtoSaved = typeService.update(1L, typeDto);
		assertNotNull(typeDtoSaved);
		assertEquals("type", typeDtoSaved.getType());
	}

	@Test
	void delete(){
		typeService.delete(1L);
		verify(typeRepository).deleteById(1L);
	}

	@Test
	void deleteAll(){
		typeService.deleteAll();
		verify(typeRepository).deleteAll();
	}
}
