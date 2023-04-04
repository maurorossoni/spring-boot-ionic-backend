package com.nelioalves.cursomc.services;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityExcepetion;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;




@Service
public class CategoriaService {

   @Autowired
	private CategoriaRepository repo;
	

		 public Optional<Categoria> find(Integer id) {
			 Optional<Categoria> obj = repo.findById(id);
			 if (obj == null) {
				 throw new ObjectNotFoundException("Objeto não encontrado! ID: " + id
						 + ", Tipo: " + Categoria.class.getName());
			 }
              return obj;
			}
	
		 public Categoria insert(Categoria obj) {
			 obj.setId(null);
			 return repo.save(obj);
		 }
		 public Categoria update(Categoria obj) {
			 find(obj.getId());
			 return repo.save(obj);
	 }
		 public void delete(Integer id) {
			 find(id);
			 try {
			 repo.deleteById(id);
					 }
			 catch (DataIntegrityViolationException e) {
				 throw new DataIntegrityExcepetion("Não é possivel excluir uma categoria que possui produtos vinculados");
			 }
		 }

		public List<Categoria> findAll() {
		    return repo.findAll();
		}
		
		public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
			PageRequest pageResquest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			return repo.findAll(pageResquest);
		}		
}
