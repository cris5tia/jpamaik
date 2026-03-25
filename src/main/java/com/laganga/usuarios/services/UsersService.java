package com.laganga.usuarios.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.laganga.usuarios.dto.MessageResponseDTO;
import com.laganga.usuarios.dto.UsersRequestDTO;
import com.laganga.usuarios.dto.UsersResponseDTO;
import com.laganga.usuarios.entity.Users;
import com.laganga.usuarios.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    public MessageResponseDTO createUser(UsersRequestDTO request) {
        MessageResponseDTO response = new MessageResponseDTO();
        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        usersRepository.save(user);
        response.setMessage("Usuario creado correctamente");
        return response;
    }

    public List<UsersResponseDTO> getUsers() {
        List<Users> usersFound = usersRepository.findAll();
        List<UsersResponseDTO> response = new ArrayList<>();
        for (Users userFound : usersFound) {
            UsersResponseDTO user = new UsersResponseDTO();
            user.setId(userFound.getId());
            user.setUsername(userFound.getUsername());
            user.setEmail(userFound.getEmail());
            response.add(user);
        }
        return response;
    }

    public UsersResponseDTO getUserById(Long id) {
        Users userFound = usersRepository.findById(id).orElse(null);
        if (userFound == null) {
            throw new RuntimeException("Usuario no encontrado");
        }
        UsersResponseDTO response = new UsersResponseDTO();
        response.setId(userFound.getId());
        response.setUsername(userFound.getUsername());
        response.setEmail(userFound.getEmail());
        return response;
    }

    public MessageResponseDTO updateUser(Long id, UsersRequestDTO request) {
        MessageResponseDTO response = new MessageResponseDTO();
        Users userFound = usersRepository.findById(id).orElse(null);
        if (userFound == null) {
            throw new RuntimeException("Usuario no encontrado");
        }
        userFound.setUsername(request.getUsername());
        userFound.setEmail(request.getEmail());
        usersRepository.save(userFound);
        response.setMessage("Usuario actualizado correctamente");
        return response;
    }

    public MessageResponseDTO deleteUser(Long id) {
        MessageResponseDTO response = new MessageResponseDTO();
        Users userFound = usersRepository.findById(id).orElse(null);
        if (userFound == null) {
            throw new RuntimeException("Usuario no encontrado");
        }
        usersRepository.delete(userFound);
        response.setMessage("Usuario eliminado correctamente");
        return response;
    }
}