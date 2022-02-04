package com.bilgeadam.mapper;


import com.bilgeadam.dto.request.DoLoginRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANTS = Mappers.getMapper(UserMapper.class);

    // Birebir aynı olan alanların eşleşmesi durumu var.
    // Eğer alanların yani değişken adlarının aynı olmadığı durumlarda set işlemi nasıl olcak?
    // @Mapping(source = "email",target = "username")
     User toUser(final RegisterRequestDto dto);


}
