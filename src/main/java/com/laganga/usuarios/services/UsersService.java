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

@Service // Crea un bean (instancia)
@RequiredArgsConstructor
public class UsersService {
    // Inyección de dependencias
    private final UsersRepository usersRepository; // Inyectamos el repository
    
    /**
     * Este metodo es para crear un usuario
     * @param request datos del usuario a crear
     * @return MessageResponseDTO objeto de respuesta que contiene un mensaje
     */
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
        UsersResponseDTO user = new UsersResponseDTO();

        for (Users userFound : usersFound) {
            user.setId(userFound.getId());
            user.setUsername(userFound.getUsername());
            user.setEmail(userFound.getEmail());
            response.add(user);
        }

        return response;
    }
}
