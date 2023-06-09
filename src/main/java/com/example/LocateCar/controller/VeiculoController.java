package com.example.LocateCar.controller;

import com.example.LocateCar.dto.VeiculoDTO;
import com.example.LocateCar.model.TipoVeiculo;
import com.example.LocateCar.model.Veiculo;
import com.example.LocateCar.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping(value = "/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping("")
    public ModelAndView index() {
        List<Veiculo> listaVeiculos = this.veiculoService.listarTodos();
        ModelAndView modelAndView = new ModelAndView("veiculos/index");
        modelAndView.addObject("veiculos", listaVeiculos);
        return modelAndView;
    }

    @GetMapping("/novo")
    public ModelAndView atualizarVeiculos(VeiculoDTO veiculoDTO) {
        ModelAndView modelAndView = new ModelAndView("veiculos/novo");
        modelAndView.addObject("tipoVeiculo", TipoVeiculo.values());
        return modelAndView;
    }

    @PostMapping("")
    public ModelAndView criarVeiculo(@Valid VeiculoDTO veiculoDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("veiculos/novo");
            modelAndView.addObject("tipoVeiculo", TipoVeiculo.values());
            return modelAndView;
        } else {
            Veiculo veiculo = veiculoDTO.paraVeiculo();
            this.veiculoService.createVeiculo(veiculo);

            return new ModelAndView("redirect:/veiculos");
        }
    }

    @GetMapping("/{id}")
    public ModelAndView exibirVeiculo(@PathVariable Long id) {
        Optional<Veiculo> optional = this.veiculoService.buscarVeiculoPorId(id);
        if (optional.isPresent()) {
            Veiculo veiculo = optional.get();
            ModelAndView modelAndView = new ModelAndView("veiculos/veiculo");
            modelAndView.addObject("veiculo", veiculo);
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/veiculos");
        }
    }

    @GetMapping("/{id}/atualizar")
    public ModelAndView atualizarVeiculo(@PathVariable Long id, VeiculoDTO veiculoDTO) {
        Optional<Veiculo> optional = this.veiculoService.buscarVeiculoPorId(id);

        if (optional.isPresent()) {
            Veiculo veiculo = optional.get();
            veiculoDTO.recuperarVeiculo(veiculo);
            ModelAndView modelAndView = new ModelAndView("veiculos/atualizar");
            modelAndView.addObject("tipoVeiculo", TipoVeiculo.values());
            modelAndView.addObject("veiculoId", veiculo.getId());
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/veiculos");
        }
    }

    @PostMapping("/{id}")
    public ModelAndView atualizarVeiculo(@PathVariable Long id, @Valid VeiculoDTO veiculoDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("veiculos/atualizar");
            modelAndView.addObject("veiculoId", id);
            modelAndView.addObject("tipoVeiculo", TipoVeiculo.values());
            return modelAndView;
        } else {
            Optional<Veiculo> optional = this.veiculoService.buscarVeiculoPorId(id);
            if (optional.isPresent()) {
                Veiculo veiculo = veiculoDTO.paraVeiculo(optional.get());
                this.veiculoService.createVeiculo(veiculo);
                return new ModelAndView("redirect:/veiculos/" + veiculo.getId());
            } else {
                return new ModelAndView("redirect:/veiculos");
            }
        }
    }

    @GetMapping("/{id}/deletar")
    public String deletar(@PathVariable Long id) {
        this.veiculoService.removerVeiculoPorId(id);
        return "redirect:/veiculos";
    }
}
