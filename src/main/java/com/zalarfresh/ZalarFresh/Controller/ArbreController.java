package com.zalarfresh.ZalarFresh.Controller;


import com.zalarfresh.ZalarFresh.DTO.request.ArbreRequestDTO;
import com.zalarfresh.ZalarFresh.DTO.response.ArbreResponseDTO;
import com.zalarfresh.ZalarFresh.Service.ArbreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/Arbres")
@Tag(name = "Arbres" , description = "Endpoints gestion des arbres")
@RequiredArgsConstructor
public class ArbreController {

    private final ArbreService arbreService;

    @PostMapping("/CreateArbre")
    @Operation(summary = "Creer une nouvelle arbre" , description = "Créer un nouvel arbre avec les détails spécifiés")
    public ResponseEntity<ArbreResponseDTO> createArbre(@RequestBody  @Valid ArbreRequestDTO arbreDTO) {
        ArbreResponseDTO createArbre = arbreService.CreateArbre(arbreDTO);
        return new ResponseEntity<>(createArbre, HttpStatus.CREATED);
    }


    @GetMapping("/{id}/productivity")
    @Operation(summary = "Calculer la productivité" , description = "Calculer la productivité de l'arbre avec l'identifiant spécifié")
    public ResponseEntity<Double> calcularProductivity(@PathVariable Long id) {
        double productivity = arbreService.calculateProductivity(id);
        return ResponseEntity.ok(productivity);
    }


    @GetMapping("/GetAllArbres")
    @Operation(summary = "Get all arbres" , description = "Récupérer une liste de tous les arbres")
    public ResponseEntity<List<ArbreResponseDTO>> getAllArbres() {
        List<ArbreResponseDTO> arbre = arbreService.getAllArbres();
        return ResponseEntity.ok(arbre);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Arbre" , description = "Supprimer l'arbre avec l'ID spécifié")
    public ResponseEntity<ArbreResponseDTO> deleteArbre(@PathVariable Long id) {
        return ResponseEntity.ok(arbreService.deleteArbre(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Arbre" , description = "Mettre a jour l'arbre avec l'ID spécifié")
    public ResponseEntity<ArbreResponseDTO> updateArbre(@PathVariable Long id, @RequestBody @Valid ArbreRequestDTO arbreDTO) {
        ArbreResponseDTO updateArbre = arbreService.updateArbre(id, arbreDTO);
        return ResponseEntity.ok(updateArbre);
    }
}
