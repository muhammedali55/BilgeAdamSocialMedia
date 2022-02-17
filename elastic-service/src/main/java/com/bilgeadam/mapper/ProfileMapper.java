package com.bilgeadam.mapper;

import com.bilgeadam.rabbitmq.model.ProfileNotification;
import com.bilgeadam.repository.entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProfileMapper {

    Profile toProfile(ProfileNotification profileNotification);
}
