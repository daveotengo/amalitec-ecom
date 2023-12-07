package com.amalitec.amalitececom.mapper;




import com.amalitec.amalitececom.blueprint.EntityMapper;
import com.amalitec.amalitececom.request_response.graphql.input.UserInput;
import com.amalitec.amalitececom.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Optional;
import java.util.UUID;


@Mapper(componentModel = "spring", uses = {})
public interface UserMapper extends EntityMapper<UserInput, User> {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Named("toDtoFromOptional")
    default UserInput toDtoFromOptional(Optional<User> optionalUser) {
        return optionalUser.map(this::toDto).orElse(null);
    }

    default User fromId(UUID id) {
        if (id == null) {
            return null;
        }
        User User = new User();
        User.setId(id);
        return User;
    }

    User toEntity(UUID userId);
}
