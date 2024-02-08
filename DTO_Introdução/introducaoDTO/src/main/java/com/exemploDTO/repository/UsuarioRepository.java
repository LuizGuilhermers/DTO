package com.exemploDTO.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemploDTO.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
