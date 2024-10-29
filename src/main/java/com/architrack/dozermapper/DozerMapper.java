package com.architrack.dozermapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
@Configuration
public class DozerMapper {

	public static  Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	public static <O, D> D parseObjectForEntity(O origem, Class<D> destination) {
		
		return mapper.map(origem, destination);
	}
	
	public static <O, D> List<D> parseListObjectForEntity(List<O>origem, Class<D>destination){
		
		List<D>list = new ArrayList<>();
		
		for(O o : origem) {
			list.add(mapper.map(o, destination));
		}
		return list;
	}
}
