package com.exemploDTO.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemploDTO.dto.UsuarioDTO;
import com.exemploDTO.entities.Usuario;
import com.exemploDTO.repository.UsuarioRepository;

@Service
	public class UsuarioService {
		
	private final UsuarioRepository usuarioRepository;
		
		@Autowired
		public UsuarioService (UsuarioRepository usuarioRepository) {
			this.usuarioRepository = usuarioRepository;
		}
		
		//Metodo DTO
		public UsuarioDTO saveUsuario (UsuarioDTO usuarioDTO) {
			//Converty DTO para entity
			Usuario usuario = new Usuario (usuarioDTO.nome(),usuarioDTO.senha());
			Usuario saveUsuario = usuarioRepository.save(usuario);
			return new UsuarioDTO (saveUsuario.getId(), saveUsuario.getNome(),saveUsuario.getSenha());
		}
		
		public UsuarioDTO updateUsuario (Long id, UsuarioDTO usuarioDTO) {
			Usuario existingUsuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException ("Usuário não encontrado"));
		
			
			existingUsuario.setNome(usuarioDTO.nome());
			existingUsuario.setSenha(usuarioDTO.nome());
			
			Usuario updateUsuario = usuarioRepository.save(existingUsuario);
			return new UsuarioDTO(updateUsuario.getId(), updateUsuario.getNome(), updateUsuario.getSenha());
			
		}
		
		public boolean deleteUsuario(Long id) {
			Optional <Usuario> existingUsuario = usuarioRepository.findById(id);
			if (existingUsuario.isPresent()) {
				usuarioRepository.deleteById(id);
				return true;
			}
			return false;

		}
		
		public List<Usuario> getAllUsuario(){
			return usuarioRepository.findAll();
		}
		
		public Usuario getUsuarioById (Long id) {
			Optional<Usuario> Usuario = usuarioRepository.findById(id);
			return Usuario.orElse(null);
		}
		
		
		
		
}
