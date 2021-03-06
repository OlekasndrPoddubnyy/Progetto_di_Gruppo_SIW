package com.mylibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mylibrary.model.Film;
import com.mylibrary.model.Gioco;
import com.mylibrary.model.Libro;
import com.mylibrary.model.SerieTv;
import com.mylibrary.model.User;
import com.mylibrary.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The UserService handles logic for Users.
 */
@Service
public class UserService {

    @Autowired
    protected UserRepository userRepository;

    /**
     * This method retrieves a User from the DB based on its ID.
     * @param id the id of the User to retrieve from the DB
     * @return the retrieved User, or null if no User with the passed ID could be found in the DB
     */
    @Transactional
    public User getUser(Long id) {
        Optional<User> result = this.userRepository.findById(id);
        return result.orElse(null);
    }

    /**
     * This method saves a User in the DB.
     * @param user the User to save into the DB
     * @return the saved User
     * @throws DataIntegrityViolationException if a User with the same username
     *                              as the passed User already exists in the DB
     */
    @Transactional
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    /**
     * This method retrieves all Users from the DB.
     * @return a List with all the retrieved Users
     */
    @Transactional
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        Iterable<User> iterable = this.userRepository.findAll();
        for(User user : iterable)
            result.add(user);
        return result;
    }
    
    @Transactional
    public void addGiocoToPreferiti(User user, Gioco gioco) {
    	user.addGiocoToPreferiti(gioco);
    	this.userRepository.save(user);
    }
    
    @Transactional
    public void addFilmToPreferiti(User user, Film film) {
    	user.addFilmToPreferiti(film);
    	this.userRepository.save(user);
    }
    
    @Transactional
    public void addSerieTvToPreferiti(User user, SerieTv serieTv) {
    	user.addSerieTvToPreferiti(serieTv);
    	this.userRepository.save(user);
    }
    
    @Transactional
    public void addLibroToPreferiti(User user, Libro libro) {
    	user.addLibroToPreferiti(libro);
    	this.userRepository.save(user);
    }
    
    @Transactional
    public void deleteGiocoFromPreferiti(User user, Gioco gioco) {
    	user.getGiochiPreferiti().remove(gioco);
    	this.userRepository.save(user);
    }
    
    @Transactional
    public void deleteFilmFromPreferiti(User user, Film film) {
    	user.getFilmPreferiti().remove(film);
    	this.userRepository.save(user);
    }
    
    @Transactional
    public void deleteSerieTvFromPreferiti(User user, SerieTv serieTv) {
    	user.getSerieTvPreferite().remove(serieTv);
    	this.userRepository.save(user);
    }
    
    @Transactional
    public void deleteLibroFromPreferiti(User user, Libro libro) {
    	user.getLibriPreferiti().remove(libro);
    	this.userRepository.save(user);
    }
    
}

