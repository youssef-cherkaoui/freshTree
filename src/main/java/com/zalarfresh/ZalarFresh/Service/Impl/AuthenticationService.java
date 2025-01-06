package com.zalarfresh.ZalarFresh.Service.Impl;


import com.zalarfresh.ZalarFresh.DTO.request.AuthRequestDTO;
import com.zalarfresh.ZalarFresh.DTO.request.RegisterRequest;
import com.zalarfresh.ZalarFresh.DTO.response.AuthResponseDTO;
import com.zalarfresh.ZalarFresh.Model.Admin;
import com.zalarfresh.ZalarFresh.Repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AdminRepository adminRepository;

    private final JWTService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthResponseDTO registerAdmin(RegisterRequest request){

        if (request == null) {
            throw new IllegalArgumentException("Le formulaire d'inscription ne peut pas être null");
        }


        var user = Admin.builder()
                .nom(request.getNom())
                .email(request.getEmail())
                .telephone(request.getTelephone())
                .motdepasse(passwordEncoder.encode(request.getMotdepasse()))
                .build();
      var admin =  adminRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthResponseDTO.builder()
                .token(jwtToken).user(admin)
                .build();
    }

    public AuthResponseDTO authenticate(AuthRequestDTO request) {
        try {
            System.out.println("Authentification demandée pour : " + request.getEmail());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getMotdepasse()
                    )
            );
            var user = adminRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

            var jwtToken = jwtService.generateToken(user);
            System.out.println("Token généré avec succès : " + jwtToken);

            return AuthResponseDTO.builder()
                    .token(jwtToken)
                    .user(user)
                    .build();
        } catch (Exception e) {
            System.out.println("Erreur lors de l'authentification : " + e.getMessage());
            throw new RuntimeException("Erreur d'authentification", e);
        }
    }



}
