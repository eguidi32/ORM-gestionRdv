package ism.l3glrs.views;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import ism.l3glrs.entity.DemandeRendezVous;
import ism.l3glrs.entity.Patient;
import ism.l3glrs.entity.Specialite;
import ism.l3glrs.services.DemandeService;
import ism.l3glrs.services.PatientService;

public class PatientView {
    private Scanner sc;
    private PatientService patientService;
    private DemandeService demandeService;

    public PatientView(PatientService patientService, DemandeService demandeService) {
        this.patientService = patientService;
        this.demandeService = demandeService;
    }

    // Injection de dépendance par setter
    public void setScanner(Scanner sc) {
        this.sc = sc;
    }

    public Patient readPatientFromConsole() {
        System.out.println("--- Création d'un patient ---");
        System.out.print("Nom: ");
        String nom = sc.nextLine().trim();
        System.out.print("Prénom: ");
        String prenom = sc.nextLine().trim();
        System.out.print("Téléphone: ");
        String telephone = sc.nextLine().trim();
        System.out.print("Email: ");
        String email = sc.nextLine().trim();
        Patient p = new Patient();
        p.setNom(nom);
        p.setPrenom(prenom);
        p.setTelephone(telephone);
        p.setEmail(email);
        return p;
    }

    public void affichePatients(List<Patient> patients) {
        System.out.println("--- Liste des patients ---");
        patients.forEach(p -> System.out.println(p.getId() + " - " + p.getNom() + " " + p.getPrenom()));
    }

    public DemandeRendezVous readDemandeFromConsole() {
        System.out.println("--- Création d'une demande de rendez-vous ---");
        System.out.print("ID du patient: ");
        Long patientId = Long.parseLong(sc.nextLine().trim());
        Patient patient = patientService.findById(patientId);
        if (patient == null) {
            System.out.println("Patient introuvable");
            return null;
        }
        System.out.print("Date et heure (YYYY-MM-DDTHH:MM), ex: 2026-05-20T14:30 : ");
        String dateStr = sc.nextLine().trim();
        LocalDateTime date = LocalDateTime.parse(dateStr);
        System.out.print("Description: ");
        String desc = sc.nextLine().trim();
        System.out.println("Spécialités disponibles:");
        Specialite[] values = Specialite.values();
        for (int i = 0; i < values.length; i++) {
            System.out.println((i + 1) + " - " + values[i]);
        }
        System.out.print("Choisir une spécialité (numéro): ");
        int idx = Integer.parseInt(sc.nextLine().trim()) - 1;
        if (idx < 0 || idx >= values.length) {
            System.out.println("Spécialité invalide");
            return null;
        }
        Specialite specialite = values[idx];

        DemandeRendezVous d = new DemandeRendezVous();
        d.setDateDemande(date);
        d.setDescription(desc);
        d.setSpecialite(specialite);
        d.setPatient(patient);
        return d;
    }

    public void afficheDemandes(List<DemandeRendezVous> demandes) {
        System.out.println("--- Liste des demandes ---");
        demandes.forEach(d -> System.out.println(d.getId() + " - " + d.getDateDemande() + " - " + d.getSpecialite() + " - patientId=" + (d.getPatient()!=null?d.getPatient().getId():"null")));
    }
}
