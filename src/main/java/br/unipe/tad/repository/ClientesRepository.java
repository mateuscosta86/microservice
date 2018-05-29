package br.unipe.tad.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.unipe.tad.model.ClienteModel;


@Repository
public interface ClientesRepository extends CrudRepository<ClienteModel, Long> {

}
