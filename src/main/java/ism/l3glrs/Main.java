package ism.l3glrs;

import java.util.List;
import java.util.Scanner;

import ism.l3glrs.config.JpaUtil;
import ism.l3glrs.entity.DemandeRendezVous;
import ism.l3glrs.entity.Patient;
import ism.l3glrs.repository.DemandeRepositoryImpl;
import ism.l3glrs.repository.PatientRespositoryImpl;
import ism.l3glrs.services.DemandeService;
import ism.l3glrs.services.DemandeServiceImpl;
import ism.l3glrs.services.PatientService;
import ism.l3glrs.services.PatientServiceImpl;
import ism.l3glrs.views.PatientView;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        var patientRepository = new PatientRespositoryImpl();
        PatientService patientService = new PatientServiceImpl(patientRepository);

        var demandeRepository = new DemandeRepositoryImpl();
        DemandeService demandeService = new DemandeServiceImpl(demandeRepository);

        PatientView patientView = new PatientView(patientService, demandeService);
        patientView.setScanner(sc);

        int choice = 0;
        while (choice != 5) {
            System.out.println("\n--- Menu ---");
            System.out.println("1 - Creer un patient");
            System.out.println("2 - Lister les patients");
            System.out.println("3 - Creer un rendez-vous");
            System.out.println("4 - Lister les rendez-vous");
            System.out.println("5 - Quitter");
            System.out.print("Choix: ");
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Entrée invalide");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    Patient p = patientView.readPatientFromConsole();
                    Long id = patientService.creerPatient(p);
                    System.out.println("Patient créé avec id=" + id);
                }
                case 2 -> {
                    List<Patient> list = patientService.listerPatients();
                    patientView.affichePatients(list);
                }
                case 3 -> {
                    var d = patientView.readDemandeFromConsole();
                    if (d != null) {
                        var id = demandeService.creerDemande(d);
                        System.out.println("Demande créée id=" + id);
                    }
                }
                case 4 -> {
                    List<DemandeRendezVous> l = demandeService.listerDemandes();
                    patientView.afficheDemandes(l);
                }
                case 5 -> System.out.println("Au revoir");
                default -> System.out.println("Choix inconnu");
            }
        }

        sc.close();
        JpaUtil.close();
    }
}