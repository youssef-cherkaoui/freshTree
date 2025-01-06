package com.zalarfresh.ZalarFresh.Controller;


import com.zalarfresh.ZalarFresh.DTO.request.RecolteRequestDTO;
import com.zalarfresh.ZalarFresh.DTO.response.RecolteResponseDTO;
import com.zalarfresh.ZalarFresh.Service.RecolteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/recolte")
@RequiredArgsConstructor
@Tag(name = "Recoltes" , description = "Endpoints gestion des recoltes")
public class RecolteController {

    private final RecolteService recolteService;

    @PostMapping("/CreationRecolte")
    @Operation(summary = "Creer une nouvelle recolte ", description = "Créer une nouvelle récolte avec les détails spécifiés")
    public ResponseEntity<RecolteResponseDTO> createRecolte(@RequestBody @Valid RecolteRequestDTO recolteDTO) {
        return ResponseEntity.ok(recolteService.creationRecolte(recolteDTO));
    }

    @GetMapping("/GetRecoles")
    @Operation(summary = "Get all recolte", description = "Mettre à jour la récolte avec id spécifié")
        public ResponseEntity<List<RecolteResponseDTO>> getAllRecolte() {
        return ResponseEntity.ok(recolteService.getRecoltes());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Recolte" , description = "Mettre à jour la récolte avec id spécifié")
    public ResponseEntity<RecolteResponseDTO> updateRecolte(@PathVariable Long id, @RequestBody @Valid RecolteRequestDTO recolteDTO) {
        return ResponseEntity.ok(recolteService.updateRecolte(id, recolteDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer Recolte" , description = "Supprimer la récolte avec id spécifié")
    public ResponseEntity<RecolteResponseDTO> SupprimerRecolte(@PathVariable Long id) {
        return ResponseEntity.ok(recolteService.deleteRecolte(id));
    }
}
