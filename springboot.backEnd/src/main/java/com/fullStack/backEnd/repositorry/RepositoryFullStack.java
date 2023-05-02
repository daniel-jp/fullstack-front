package com.fullStack.backEnd.repositorry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fullStack.backEnd.model.User;

@Repository
public interface RepositoryFullStack extends  JpaRepository<User, Long>{
	
}
