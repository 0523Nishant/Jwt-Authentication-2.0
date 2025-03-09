package com.example.Jwt_Login.Singup.Api.Repository;

import com.example.Jwt_Login.Singup.Api.Entity.GeneralEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<GeneralEntity,Integer> {

}


