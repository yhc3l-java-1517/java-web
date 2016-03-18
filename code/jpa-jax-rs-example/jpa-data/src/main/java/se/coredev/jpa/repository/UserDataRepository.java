package se.coredev.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import se.coredev.jpa.model.UserData;

public interface UserDataRepository extends JpaRepository<UserData, Long> {

}
