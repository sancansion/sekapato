package com.sekapato.model;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.sekapato.entity.Girl;


@Transactional
public interface GirlDao extends CrudRepository<Girl, Long> {

  /**
   * Return the user having the passed email or null if no user is found.
   * 
   * @param email the user email.
   */
  public Girl findByEmail(String email);
  
  public List<Girl> findAllByOrderByIdAsc();

} 
