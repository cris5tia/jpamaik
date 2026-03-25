package com.laganga.usuarios.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laganga.usuarios.dto.MessageResponseDTO;
import com.laganga.usuarios.dto.UsersRequestDTO;
import com.laganga.usuarios.dto.UsersResponseDTO;
import com.laganga.usuarios.services.UsersService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @PostMapping
    public ResponseEntity<MessageResponseDTO> createUser(@RequestBody UsersRequestDTO request) {
        MessageResponseDTO response = new MessageResponseDTO();
        try {
            response = usersService.createUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            response.setMessage("Hubo un error al crear el usuario");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<List<UsersResponseDTO>> getUsers() {
        try {
            List<UsersResponseDTO> response = usersService.getUsers();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /* Flitar/id */
    @GetMapping("/{id}")
    public ResponseEntity<UsersResponseDTO> getUserById(@PathVariable Long id) {
        try {
            UsersResponseDTO response = usersService.getUserById(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    /* delete */
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> deleteUser(@PathVariable Long id) {
        MessageResponseDTO response = new MessageResponseDTO();
        try {
            response = usersService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.setMessage("Hubo un error al eliminar el usuario");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    /* Put */
    @PutMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> updateUser(@PathVariable Long id, @RequestBody UsersRequestDTO request) {
        MessageResponseDTO response = new MessageResponseDTO();
        try {
            response = usersService.updateUser(id, request);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.setMessage("Hubo un error al actualizar el usuario");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    
}