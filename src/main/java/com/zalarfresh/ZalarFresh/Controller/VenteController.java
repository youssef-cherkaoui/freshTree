package com.zalarfresh.ZalarFresh.Controller;


import com.zalarfresh.ZalarFresh.DTO.request.VenteRequestDTO;
import com.zalarfresh.ZalarFresh.DTO.response.VenteResponseDTO;
import com.zalarfresh.ZalarFresh.Service.VenteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/ventes")
@Tag(name = "Ventes" , description = "Endpoints gestion des ventes")
@RequiredArgsConstructor
public class VenteController {

    private final VenteService venteService;

    @PostMapping("/CreateVente")
    @Operation(summary = "Créer une nouvelle vente")
    public ResponseEntity<VenteResponseDTO> createVente(@RequestBody @Valid VenteRequestDTO venteDTO) {
        VenteResponseDTO createVente = venteService.creationVente(venteDTO);
        return new ResponseEntity<>(createVente, HttpStatus.CREATED);
    }


    @GetMapping("/GetVentes")
    @Operation(summary = "Get all Ventes" , description = "Récupérer une liste de toutes les ventes")
    public ResponseEntity<List<VenteResponseDTO>> getAllVentes() {
        return ResponseEntity.ok(venteService.GetAllVentes());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Vente" , description = "Mettre à jour la vente avec id spécifié")
    public ResponseEntity<VenteResponseDTO> updateVente(@PathVariable Long id, @RequestBody @Valid VenteRequestDTO venteDTO) {
        VenteResponseDTO updateVente = venteService.updateVente(id, venteDTO);
        return ResponseEntity.ok(updateVente);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer Vente" , description = "Supprimer la vente avec id spécifié")
    public ResponseEntity<Void> deleteVente(@PathVariable Long id) {
        venteService.deleteVente(id);
        return ResponseEntity.noContent().build();
    }
}
