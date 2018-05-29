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
package br.unipe.tad.controller;

import javax.swing.plaf.synth.SynthSpinnerUI;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.unipe.tad.model.ClienteModel;
import br.unipe.tad.repository.ClientesRepository;

/**
 * @author Rodrigo C. Fujioka 
 * @date 16 de mai de 2017 
 * @time 21:31:17
 *
 */
@Controller
@RequestMapping("/")
public class ClienteController {


	@Autowired
	private ClientesRepository clientes;
		
	@GetMapping("/") 
	public ModelAndView listar(){
		ModelAndView modelAndView = new ModelAndView("PrincipalView");
		
		modelAndView.addObject("clientes", clientes.findAll());
		
		return modelAndView;
	}
		
	@GetMapping("/novocliente") 
	public ModelAndView adicionar(){
		ModelAndView mv = new ModelAndView("NovoClienteView");
		ClienteModel novoCliente = new ClienteModel();
		novoCliente.setSaldo(0);
		mv.addObject("novocliente", novoCliente);		
		
		return mv;
	}
	
	@PostMapping("/salvar")
	public String salvar(ClienteModel novocliente) {
		
		this.clientes.save(novocliente);
		
		return "redirect:/";
	}
	
	@GetMapping("/depositar/{Id}") 
	public ModelAndView depositar(@PathVariable("Id") String Id) {
		ClienteModel clienteAtivo = clientes.findOne(Long.parseLong(Id));  
		ModelAndView mv = new ModelAndView("DepositoView");
		mv.addObject("clienteAtivo", clienteAtivo);
		
		return mv;
	}
	
	@PostMapping("/salvarDeposito")
	public String salvarDeposito(@RequestParam("id") String id, @RequestParam("valor") String formValor) {
		double valor = Double.parseDouble(formValor);
		ClienteModel novocliente = clientes.findOne(Long.parseLong(id));  
		System.out.println(valor);				
		novocliente.depositar(valor);		
		this.clientes.save(novocliente);
		
		return "redirect:/";
	}
	
	@GetMapping("/sacar/{Id}") 
	public ModelAndView sacar(@PathVariable("Id") String Id) {
		ClienteModel clienteAtivo = clientes.findOne(Long.parseLong(Id));  
		ModelAndView mv = new ModelAndView("SaqueView");
		mv.addObject("clienteAtivo", clienteAtivo);
		
		return mv;
	}
	
	@PostMapping("/salvarSaque")
	public String salvarSaque(@RequestParam("id") String id, @RequestParam("valor") String formValor) {
		double valor = Double.parseDouble(formValor);
		ClienteModel novocliente = clientes.findOne(Long.parseLong(id));  
		System.out.println(valor);				
		novocliente.debitar(valor);		
		this.clientes.save(novocliente);
		
		return "redirect:/";
	}
	
	@GetMapping("/transferir/{Id}") 
	public ModelAndView transferir(@PathVariable("Id") String Id) {
		ClienteModel clienteAtivo = clientes.findOne(Long.parseLong(Id));  
		ModelAndView mv = new ModelAndView("TransferenciaView");
		mv.addObject("clienteAtivo", clienteAtivo);
		
		return mv;
	}
	
	@PostMapping("/salvarTransferencia")
	public String salvarTransferencia(@RequestParam("id") String id, @RequestParam("destinoid") String destinoid, @RequestParam("valor") String formValor) {
		double valor = Double.parseDouble(formValor);
		ClienteModel clienteFonte = clientes.findOne(Long.parseLong(id));
		ClienteModel clienteDestino = clientes.findOne(Long.parseLong(destinoid));
						
		clienteFonte.debitar(valor);
		clienteDestino.depositar(valor);
		this.clientes.save(clienteFonte);
		this.clientes.save(clienteDestino);
		
		return "redirect:/";
	}
	
	@GetMapping("/apagarCliente/{Id}")
	public String apagarCliente(@PathVariable("Id") String Id) {
		ClienteModel clienteAtivo = clientes.findOne(Long.parseLong(Id));
		this.clientes.delete(clienteAtivo);
		return "redirect:/";		
	}
	
	@GetMapping("/atualizarCliente/{Id}") 
	public ModelAndView atualizarCliente(@PathVariable("Id") String Id) {
		ClienteModel clienteAtivo = clientes.findOne(Long.parseLong(Id));
		ModelAndView mv = new ModelAndView("AtualizarView");
		mv.addObject("novocliente", clienteAtivo);		
		
		return mv;
	}

}
