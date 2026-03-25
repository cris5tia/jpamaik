package com.laganga.usuarios.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    /* filtrar/id */
    public UsersResponseDTO getUserById(Long id) {
        Optional<Users> userFound = usersRepository.findById(id);

        if (userFound.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        UsersResponseDTO response = new UsersResponseDTO();
        response.setId(userFound.get().getId());
        response.setUsername(userFound.get().getUsername());
        response.setEmail(userFound.get().getEmail());

        return response;
    }

    /* Put */
    public MessageResponseDTO updateUser(Long id, UsersRequestDTO request) {
        MessageResponseDTO response = new MessageResponseDTO();

        Optional<Users> userFound = usersRepository.findById(id);

        if (userFound.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        Users user = userFound.get();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        usersRepository.save(user);

        response.setMessage("Usuario actualizado correctamente");
        return response;
    }
    /* Delete */
    public MessageResponseDTO deleteUser(Long id) {
        MessageResponseDTO response = new MessageResponseDTO();

        Optional<Users> userFound = usersRepository.findById(id);

        if (userFound.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        usersRepository.deleteById(id);

        response.setMessage("Usuario eliminado correctamente");
        return response;
    }
}