package net.rest.common.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class DtoConverter<T,K> {

  
  @Autowired
  protected ModelMapper modelMapper;
  
  protected abstract K convertToDto(K source, T destination);
  
  protected abstract T convertToEntity(T source, K destination);
  
}
