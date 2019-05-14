import Clase.Afisare;
import Clase.Serviciu;

import java.util.Scanner;

public class Main {


    public static void AfiseazaMeniu(){
        System.out.println("1. Afisati toate departamentele.");
        System.out.println("2. Adaugati un departament nou.\n");
        System.out.println("3. Afisati toti angajatii.");
        System.out.println("4. Adaugati un angajat nou.");
        System.out.println("5. Concediaza angajat.\n");
        System.out.println("6. Afiseaza programele tuturor angajatilor.");
        System.out.println("7. Verifica disponibilitatea unui angajat la un anumit moment.");
        System.out.println("8. Programeaza activitate noua.\n");
        System.out.println("9. Afiseaza sedintele departamentelor.");
        System.out.println("10. Adauga sedinta de departament noua.\n");
        System.out.println("11. Exit");
        System.out.println();
        System.out.println("Optiunea dumneavoastra:");

    }

    public static void main(String[] args) {
        Afisare afis = new Afisare();

        System.out.println("Bine ati venit!\n\n");
        System.out.println("Aveti urmatoarele optiuni:\n");
        Scanner in = new Scanner(System.in);
        int optiune;
        Serviciu serviciu = new Serviciu();
        while(true){
            AfiseazaMeniu();
            optiune = in.nextInt();
            if(optiune==1) {
                serviciu.AfiseazaDepartamente();
            }
            if(optiune==2){
                serviciu.CitesteDepartamentDeLaTastatura();
            }
            if(optiune==3){
                serviciu.AfiseazaAngajati();
            }
            if(optiune==4){
                serviciu.CitesteAngajatDeLaTastatura();
            }
            if(optiune==5){
                serviciu.StergeAngajat();
            }
            if(optiune==6) {
                serviciu.AfiseazaProgrameleAngajatilor();
            }
            if(optiune==7) {
                String nume_angajat;
                nume_angajat = IntroducereNumeAngajat();
            }
            if(optiune==8) {
                System.out.println("Ce fel de activitate doriti sa programati? (1=activitate individuala/2=sedinta departament)");
                int tip;
                tip = in.nextInt();
                if(tip==1)
                    serviciu.ProgrameazaActivitateNoua();
            }
            if(optiune==9) {

            }
            if(optiune==10) {

            }
            if(optiune==11) {
                System.out.print("O zi buna!");
                return;
            }
            System.out.print("\n\n\n");
        }
    }
}
