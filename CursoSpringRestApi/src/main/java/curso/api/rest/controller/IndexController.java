	package curso.api.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import curso.api.rest.model.Usuario;
import curso.api.rest.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/usuario")
public class IndexController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping(value = "/",produces = "application/json")
	public ResponseEntity init(@RequestParam(value = "nome",defaultValue = "default") String nome, @RequestParam(value = "salario", required = false) Long salario) {
		return new ResponseEntity("Olá Usuário Rest Sprint Boot. Seu nome é: "+nome+"/ Salário = "+salario,HttpStatus.OK);
	}
	
	@GetMapping(value = "/getUsuario",produces = "application/json")
	public ResponseEntity<List<Usuario>> getUsuario () {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setLogin("marcelo.r");
		usuario.setSenha("password");
		usuario.setNome("Marcelo Radulski Nunes");
		
		Usuario usuario2 = new Usuario();
		usuario2.setId(1L);
		usuario2.setLogin("joana.n");
		usuario2.setSenha("password");
		usuario2.setNome("Joana Carraro Nunes");
		
		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(usuario);
		usuarios.add(usuario2);
		
		return new ResponseEntity<List<Usuario>>(usuarios,HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}",produces = "application/json")
	public ResponseEntity<Usuario> getUsuario2 (@PathVariable (value = "id") Long id) {				
		return new ResponseEntity<Usuario>(usuarioRepository.findById(1L).get(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/getUsuario3",produces = "application/json")
	public ResponseEntity<List<Usuario>> getUsuario3 () {
				
		List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
				
		return new ResponseEntity<List<Usuario>>(usuarios,HttpStatus.OK);
	}
	
	@PostMapping(value = "/postUsuario",produces = "application/json")
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario){		
		return new ResponseEntity<Usuario>(usuarioRepository.save(usuario),HttpStatus.OK);	
	}
}
