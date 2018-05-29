/**
d * Projeto das trilhas de treinamento de Java b�sico ou avan�ado 
 * com foco nas certifica��es java e em treinamentos corporativos. 
 * Fontes dispon�veis em https://github.com/rodrigofujioka
 * 
 * Professor: Rodrigo da Cruz Fujioka
 * Ano: 2016
 * http://www.rodrigofujioka.com
 * http://www.fujideia.com.br
 * http://lattes.cnpq.br/0843668802633139
 * 
 * Contato: rcf4@cin.ufpe.br 
 */
package br.unipe.tad.controller.api;

import javax.swing.plaf.synth.SynthSpinnerUI;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.unipe.tad.model.ClienteModel;
import br.unipe.tad.repository.ClientesRepository;

/**
 * @author Rodrigo C. Fujioka 
 * @date 16 de mai de 2017 
 * @time 21:31:17
 *
 */
@RestController
@RequestMapping("/api/")
public class ClienteApiController {


	@Autowired
	private ClientesRepository clientes;
		
	@GetMapping("/clientes") 
	public ResponseEntity<Iterable<ClienteModel>> listar(){
			
		Iterable<ClienteModel> clientesList = clientes.findAll();
		
		return new ResponseEntity<Iterable<ClienteModel>>(clientesList, HttpStatus.OK);
	}
		
	@GetMapping("/clientes/{Id}") 
	public ResponseEntity<ClienteModel> depositar(@PathVariable("Id") String Id) {
		ClienteModel cliente = clientes.findOne(Long.parseLong(Id));  
		if(cliente == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<ClienteModel>(cliente, HttpStatus.OK);
	}
	
	@PostMapping("/clientes")
	public ResponseEntity<ClienteModel> salvar(@RequestBody ClienteModel novocliente) {
		
		System.out.println(novocliente.getSobrenome());
		ClienteModel cliente = this.clientes.save(novocliente);
				
		return new ResponseEntity<ClienteModel>(cliente, HttpStatus.CREATED);
	}
	
	@PutMapping("/clientes/{Id}")
	public ResponseEntity<ClienteModel> atualizar(@PathVariable("Id") String Id, @RequestBody ClienteModel novocliente) {
		
		ClienteModel clienteAtualizado = this.clientes.findOne(Long.parseLong(Id));
		if(clienteAtualizado == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		clienteAtualizado.setNome(novocliente.getNome());
		clienteAtualizado.setSobrenome(novocliente.getSobrenome());
		clienteAtualizado.setSaldo(novocliente.getSaldo());
		
		this.clientes.save(clienteAtualizado);
				
		return new ResponseEntity<ClienteModel>(clienteAtualizado, HttpStatus.OK);
	}
	
	@DeleteMapping("/clientes/{Id}")
	public ResponseEntity<String> apagarCliente(@PathVariable("Id") String Id) {
		ClienteModel clienteAtivo = clientes.findOne(Long.parseLong(Id));
		
		if(clienteAtivo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		this.clientes.delete(clienteAtivo);
		return new ResponseEntity<String>("Deleted: " + Id, HttpStatus.OK);		
	}
}
