package com.ottplatform.repository;

import com.ottplatform.entities.User;
import com.ottplatform.response.dto.UserResponse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String userName);

    Optional<User> findByMobileNo(String mobileNo);

    Optional<User> findByEmailId(String emailId);

    @Query("select new com.ottplatform.response.dto.UserResponse(u.id,u.name,u.emailId,u.mobileNo,u.status,u.username) from User u where u.id=:id")
    Optional<UserResponse> findByUserId(Long id);

    // @Query("select new
    // com.jwt.response.dto.UserResponse(u.id,u.firstName,u.lastName,u.emailId,u.mobileNo,u.address,u.doj,u.status,u.dob,u.username,u.password,u.createdOn,
    // u.updatedOn) from User u")
    // List<UserResponse> findAllUser();

}
