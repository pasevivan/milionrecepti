package bg.softuni.milionrecepti.model.mapper;

import bg.softuni.milionrecepti.model.dto.user.UserRegisterDTO;
import bg.softuni.milionrecepti.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "active", constant = "true")
  UserEntity userDtoToUserEntity(UserRegisterDTO registerDTO);
}
