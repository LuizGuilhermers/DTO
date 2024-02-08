package com.exemploDTO.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.exemploDTO.Service.UsuarioService;
import com.exemploDTO.dto.UsuarioDTO;
import com.exemploDTO.entities.Usuario;

import jakarta.validation.Valid;

@RestController
@RequestMapping ("/usuario")
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	
	@Autowired
	public UsuarioController (UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
    @GetMapping("/{id}")
    public ResponseEntity <Usuario> buscaUsuarioIdControlId(@PathVariable Long id){
        Usuario usuario = usuarioService.getUsuarioById(id);
        if(usuario!= null) {
            return ResponseEntity.ok(usuario);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Usuario>> buscaTodosUsuarioControl() {
        List<Usuario> usuario = usuarioService.getAllUsuario();
 
        return ResponseEntity.ok(usuario);
    }
     
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
        boolean deleted = usuarioService.deleteUsuario(id);
        if (deleted) {
        	 return ResponseEntity.ok().body("O usuário foi excluída com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/")
    public ResponseEntity<UsuarioDTO> save(@RequestBody  @Valid UsuarioDTO usuarioTDO) {
    	UsuarioDTO saveUsuario = usuarioService.saveUsuario(usuarioTDO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveUsuario);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> updateUsuarioDTO(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
    	UsuarioDTO updatedUsuario = usuarioService.updateUsuario(id, usuarioDTO);
        if (updatedUsuario != null) {
            return ResponseEntity.ok(updatedUsuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
