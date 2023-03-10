package com.example.LocateCar.service;

import com.example.LocateCar.model.Veiculo;
import com.example.LocateCar.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {
    @Autowired
    private VeiculoRepository veiculoRepository;

    public void createVeiculo(Veiculo veiculo) {
        this.veiculoRepository.save(veiculo);
    }

    public List<Veiculo> listarTodos() {
        return this.veiculoRepository.findAll();
    }

    public Optional<Veiculo> buscarVeiculoPorId(Long id) {
        return this.veiculoRepository.findById(id);
    }


    public void removerVeiculoPorId(Long id) {
        this.veiculoRepository.deleteById(id);
    }
}