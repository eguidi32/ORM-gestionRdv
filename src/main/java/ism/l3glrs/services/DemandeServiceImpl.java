package ism.l3glrs.services;

import java.util.List;

import ism.l3glrs.entity.DemandeRendezVous;
import ism.l3glrs.repository.DemandeRepository;

public class DemandeServiceImpl implements DemandeService {
    private DemandeRepository demandeRepository;

    public DemandeServiceImpl(DemandeRepository demandeRepository) {
        this.demandeRepository = demandeRepository;
    }

    @Override
    public Long creerDemande(DemandeRendezVous demande) {
        return demandeRepository.insert(demande);
    }

    @Override
    public List<DemandeRendezVous> listerDemandes() {
        return demandeRepository.selectAll();
    }

}
