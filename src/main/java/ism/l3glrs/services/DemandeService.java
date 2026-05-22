package ism.l3glrs.services;

import java.util.List;

import ism.l3glrs.entity.DemandeRendezVous;

public interface DemandeService {
    Long creerDemande(DemandeRendezVous demande);
    List<DemandeRendezVous> listerDemandes();
}
