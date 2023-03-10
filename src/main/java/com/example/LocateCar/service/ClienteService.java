package com.example.LocateCar.service;

import com.example.LocateCar.model.Cliente;
import com.example.LocateCar.model.Veiculo;
import com.example.LocateCar.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public void createCliente(Cliente cliente) {
        this.clienteRepository.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return this.clienteRepository.findAll();
    }

    public Optional<Cliente> buscarClientePorId(Long id) {
        return this.clienteRepository.findById(id);
    }

    public Optional<Cliente> buscarClientePeloDocumento(String documento) {
        return this.clienteRepository.findByDocumentoContaining(documento);
    }

    public void removerClientePorId(Long id) {
        this.clienteRepository.deleteById(id);
    }
}
