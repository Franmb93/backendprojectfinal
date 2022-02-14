package com.capgemini.controllers;

import java.util.List;

import com.capgemini.entities.Sesion;
import com.capgemini.entities.Usuario;
import com.capgemini.services.ISesionService;
import com.capgemini.services.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequestMapping("/api/sesions")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class SesionController {
    
    @Autowired
    private ISesionService serviceSesion;

    @Autowired
    private IUserService serviceUser;

    @GetMapping
    public List<Sesion> getSesions(){
        return serviceSesion.findAll();
    }

    @GetMapping("/{id}")
    public Sesion getSesion(@PathVariable long id){
        return serviceSesion.findById(id);
    }

    @PostMapping
    public Sesion newSesion(@RequestBody Sesion sesion){
        Usuario user = serviceUser.findByUsername(sesion.getUsername());

        // sesion.setPassword(PasswordEncrypter.encode(sesion.getPassword()));

        if(user.getUsername().equals(sesion.getUsername()) 
            && user.getPassword().equals(sesion.getPassword())){
            sesion.setUser_id(user.getId());
            return serviceSesion.save(sesion);
        } else{
           throw new Error("Name or password wrong");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteSesion(@PathVariable long id){
        serviceSesion.delete(id);
    }
}