package com.example.LocateCar.dto;

import com.example.LocateCar.model.TipoVeiculo;
import com.example.LocateCar.model.Veiculo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoDTO {

    @NotBlank
    @NotNull
    private String placa;
    @NotBlank
    @NotNull
    private String marca;
    @NotBlank
    @NotNull
    private String modelo;
    private TipoVeiculo tipoVeiculo;
    private Boolean disponivel;
    public Veiculo paraVeiculo() {
        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca(this.placa);
        veiculo.setMarca(this.marca);
        veiculo.setModelo(this.modelo);
        veiculo.setTipoVeiculo(this.tipoVeiculo);
        veiculo.setDisponivel(true);
        return veiculo;
    }

    public Veiculo paraVeiculo(Veiculo veiculo) {

        veiculo.setPlaca(this.placa);
        veiculo.setMarca(this.marca);
        veiculo.setModelo(this.modelo);
        veiculo.setTipoVeiculo(this.tipoVeiculo);
        veiculo.setDisponivel(true);
        return veiculo;
    }


    public void recuperarVeiculo(Veiculo veiculo) {
        this.placa = veiculo.getPlaca();
        this.marca = veiculo.getMarca();
        this.modelo = veiculo.getModelo();
        this.tipoVeiculo = veiculo.getTipoVeiculo();
        this.disponivel = veiculo.getDisponivel();
    }

}