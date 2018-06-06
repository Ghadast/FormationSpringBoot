package tn.biat.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.biat.domain.Compte;
import tn.biat.repository.ICompteRepository;

@RestController
public class CompteController {
	//@Autowired
	private ICompteRepository repo;
	
	//@autowired n'est pas nécessaire dans l'injection dans le constructeur
	public CompteController(ICompteRepository repo ) {
		
		this.repo=repo;
	}
	
//@RequestMapping(path="/comptes",method=RequestMethod.GET) ou bien 
@GetMapping(path="/comptes",produces=MediaType.APPLICATION_XML_VALUE)

public List<Compte> getAllComptes(){
	return repo.findAll();
	}

@GetMapping("/comptes/{id}")
public ResponseEntity<Compte> getCompteByID(@PathVariable(value="id") String myid){
	Optional<Compte> cpt = repo.findById(myid);
	if (!cpt.isPresent()) {
		return ResponseEntity.notFound().build();		
	}
	return ResponseEntity.ok().body(cpt.get());	
	}

@PostMapping("/comptes")
public  ResponseEntity<Compte> addCompte(@RequestBody Compte c ){	
	repo.save(c);	
	return new ResponseEntity<Compte>(c,HttpStatus.CREATED); 
}

@PutMapping("/comptes")
public ResponseEntity<Compte> updateCompte(@RequestBody Compte c ){	
	repo.save(c); // save or update	
	return new ResponseEntity<Compte>(c,HttpStatus.ACCEPTED); 
}

@DeleteMapping("/comptes/{id}")
public ResponseEntity<Compte> deleteCompteByID(@PathVariable(value="id") String myid){
	Optional<Compte> cpt = repo.findById(myid);
	if (!cpt.isPresent()) {		
		return ResponseEntity.notFound().build();		
	}
	else {
		repo.deleteById(myid);}		
	return new ResponseEntity<Compte>(HttpStatus.ACCEPTED);
	
	}
	
}
