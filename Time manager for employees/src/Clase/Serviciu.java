package Clase;

import com.sun.scenario.effect.impl.state.LinearConvolveRenderState;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Serviciu {

    //ArrayList<String> cars = new ArrayList<String>();
    ArrayList<Departament> departamente = new ArrayList<Departament>();
    ArrayList<Angajat> angajati = new ArrayList<Angajat>();

    public void CitesteDepartamenteFisier()//din fisier csv
    {
        //----------citesc departamentele din fisier si le adaug in lista-------------------

        String fileName = "D:\\PAO\\Proiect_Oanea_Smit-Andrei_gr235\\Etapa1\\src\\Clase\\Fisiere\\Departamente.csv";
        File file = new File(fileName);
        try{
            Scanner inputStream = new Scanner(file);
            while(inputStream.hasNext()){
                String linie = inputStream.next();
                String cuvinte[] = linie.split(",");

                Ocupatie oc = new Ocupatie(cuvinte[1]);
                Departament dep = new Departament(cuvinte[0], oc);
                departamente.add(dep);//il adaug in lista
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void CitesteDepartamentDeLaTastatura()
    {
        System.out.println("Introduceti numele noului departament: ");
        Scanner input = new Scanner(System.in);
        String nume_departament;
        nume_departament = input.next();
        String nume_ocupatie;
        System.out.println("Introduceti ocupatia noului departament: ");
        nume_ocupatie = input.next();

        Ocupatie oc = new Ocupatie(nume_ocupatie);
        Departament dep = new Departament(nume_departament, oc);
        departamente.add(dep);//il adaug in lista
    }

    public Departament StringToDepartament(String nume_departament)
    {
        boolean am_gasit_departament;
        am_gasit_departament = false;
        Departament dep = new Departament();
        for(int i=0;!am_gasit_departament && i<departamente.size();++i)
            if (departamente.get(i).getNume().equals(nume_departament)){
                dep = departamente.get(i);
                am_gasit_departament = true;
            }
            else{
                //System.out.println("numele este de fapt " + departamente.get(i).getNume() + ", si nu " + cuvinte[1]);
            }
        if(am_gasit_departament) {
            return dep;
        }
        System.out.println("Eroare. Nu exista departamentul " + nume_departament + ".");
        dep.setNume("inexistent");
        return dep;
    }

    public void CitesteAngajatiFisier()
    {
        //----------citesc angajatii din fisier si ii adaug in lista-------------------

        String fileName = "D:\\PAO\\Proiect_Oanea_Smit-Andrei_gr235\\Etapa1\\src\\Clase\\Fisiere\\Angajati.csv";
        File file = new File(fileName);
        try{
            Scanner inputStream = new Scanner(file);
            while(inputStream.hasNext()){
                String linie = inputStream.next();
                String cuvinte[] = linie.split(",");

                //am citit, acum adaug in lista
                //acum verific daca departamentul specificat exista
                Departament dep = new Departament();
                dep = StringToDepartament(cuvinte[1]);
                if(dep.getNume().equals("inexistent"))
                    System.out.println("Eroare. Nu exista departamentul " + cuvinte[1] + ".");
                else {
                    Angajat ang = new Angajat(cuvinte[0], dep);
                    angajati.add(ang);
                }



            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void CitesteAngajatDeLaTastatura()
    {
        String nume_angajat, nume_departament;
        System.out.println("Introduceti numele noului angajat: ");
        Scanner input = new Scanner(System.in);
        nume_angajat = input.next();
        System.out.println("Introduceti departamentul noului angajat: ");
        nume_departament = input.next();


        //am citit, acum adaug in lista
        //acum verific daca departamentul specificat exista
        Departament dep = new Departament();
        dep = StringToDepartament(nume_departament);
        if(dep.getNume().equals("inexistent"))
            System.out.println("Eroare. Nu exista departamentul " + nume_departament + ".");
        else {
            Angajat ang = new Angajat(nume_angajat, dep);
            angajati.add(ang);
        }

    }

    public Angajat StringToAngajat(String nume_angajat)
    {
        boolean am_gasit_angajat;
        am_gasit_angajat = false;
        Angajat ang = new Angajat();
        for(int i=0;!am_gasit_angajat && i<departamente.size();++i)
            if (angajati.get(i).getNume().equals(nume_angajat)){
                ang = angajati.get(i);
                am_gasit_angajat = true;
            }

        if(am_gasit_angajat) {
            return ang;
        }
        System.out.println("Eroare. Nu exista angajatul " + nume_angajat + ".");
        ang.setNume("inexistent");
        return ang;
    }

    public void CitesteActivitatiIndividualeDinFisier()
    {
        String fileName = "D:\\PAO\\Proiect_Oanea_Smit-Andrei_gr235\\Etapa1\\src\\Clase\\Fisiere\\Activitati.csv";
        File file = new File(fileName);
        try{
            Scanner inputStream = new Scanner(file);
            while(inputStream.hasNext()){
                String linie = inputStream.next();
                String cuvinte[] = linie.split(",");

                //am citit, acum adaug in lista
                String nume_angajat = cuvinte[0];
                Angajat ang = new Angajat();
                ang = StringToAngajat(nume_angajat);

                if(ang.getNume().equals("inexistent") == false)//exista acest angajat, ii adaug activitatea in program
                {
                    String nume_activitate = cuvinte[1];
                    int prioritate_activitate = Integer.parseInt(cuvinte[2]);
                    String string_date = cuvinte[3];
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    try{
                        Date data = formatter.parse(string_date);
                        int start, sfarsit;
                        start = Integer.parseInt(cuvinte[4]);
                        sfarsit = Integer.parseInt(cuvinte[5]);
                        IntervalOrar interval = new IntervalOrar();
                        interval.setStart(start);
                        interval.setSfarsit(sfarsit);
                        Activitate activitate = new Activitate(nume_activitate,prioritate_activitate,data,interval);
                        ang.AdaugaActivitate(activitate);

                    } catch (ParseException e){
                        e.printStackTrace();
                    }

                    }
                else
                {
                    System.out.println("intru in else " + cuvinte[1]);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //constructor
    public Serviciu(){
        CitesteDepartamenteFisier();
        CitesteAngajatiFisier();
        CitesteActivitatiIndividualeDinFisier();
    }

    public void AfiseazaDepartamente() {
        System.out.println("Departamentele sunt:");
        int i;
        for(i=0;i<departamente.size();++i)
            System.out.println(departamente.get(i).getNume());
    }

    public void AfiseazaAngajati() {
        System.out.println("Angajatii sunt:");
        for(int i=0;i<angajati.size();++i){
            System.out.println(angajati.get(i).getNume() + ", departament " + angajati.get(i).getDepartament().getNume());
        }
    }

    public void AfiseazaProgramAngajat(Angajat ang)
    {
        ang.getProgram().AfiseazaActivitati();
    }

    public void AfiseazaProgrameleAngajatilor(){
        for(int i=0;i<angajati.size();++i) {
            System.out.println("Angajatul " + angajati.get(i).getNume() + " are programul:");
            AfiseazaProgramAngajat(angajati.get(i));
            System.out.println("\n\n");
        }
    }

    public void StergeAngajat()
    {
        String nume_angajat;
        System.out.println("Introduceti numele angajatului:");
        Scanner input = new Scanner(System.in);
        nume_angajat = input.next();
        boolean am_gasit_angajatul_cautat = false;
        for(int i=0;i<angajati.size();++i)
        {
            if(angajati.get(i).getNume().equals(nume_angajat)){
                angajati.remove(i);
                am_gasit_angajatul_cautat = true;
                break;
            }
        }
        if(!am_gasit_angajatul_cautat)
            System.out.println("Angajatul " + nume_angajat + " nu exista!");
    }

    public boolean VerificaDisponibilitateAngajat(Angajat angajat, Date data, IntervalOrar interval)
    {
            return true;
    }



    public void ProgrameazaActivitateNoua()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Introduceti numele angajatului: ");
        String nume_angajat;
        nume_angajat = input.next();
        Angajat ang = new Angajat();
        ang = StringToAngajat(nume_angajat);

        if(ang.getNume().equals("inexistent") == false)//exista acest angajat, ii adaug activitatea in program
        {
            System.out.println("Introduceti numele activitatii desfasurate:");
            String nume_activitate;
            nume_activitate = input.next();

            System.out.println("Introduceti prioritatea activitatii desfasurate:");
            int prioritate_activitate;
            prioritate_activitate = input.nextInt();

            System.out.println("Introduceti data(dd-MM-yyyy)");
            String string_date;
            string_date = input.next();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            try{
                Date data = formatter.parse(string_date);
                int start, sfarsit;
                System.out.println("Introduceti ora la care incepe activitatea: ");
                start = input.nextInt();
                System.out.println("Introduceti ora la care se termina activitatea: ");
                sfarsit = input.nextInt();
                IntervalOrar interval = new IntervalOrar();
                interval.setStart(start);
                interval.setSfarsit(sfarsit);
                Activitate activitate = new Activitate(nume_activitate,prioritate_activitate,data,interval);
                ang.AdaugaActivitate(activitate);

            } catch (ParseException e){
                e.printStackTrace();
            }

        }
    }
}
