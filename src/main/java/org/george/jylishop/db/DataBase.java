package org.george.jylishop.db;

import org.george.jylishop.domain.Hemostatic;
import org.george.jylishop.domain.OpalescenseGel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by Yulya on 02.05.2016.
 */
@Component
public class DataBase {
    ArrayList<OpalescenseGel> catalogue=new ArrayList<OpalescenseGel>();

    public ArrayList<OpalescenseGel> getCatalogue() {
        return catalogue;
    }
    ArrayList<Hemostatic> hemolist=new ArrayList<Hemostatic>();

    public ArrayList<Hemostatic> getHemolist() {
        return hemolist;
    }

    public DataBase() {
        OpalescenseGel first=new OpalescenseGel ();
        first.setTitle("Opalescense Gel");
        first.setReactantPercent(10);
        first.setVolume(1.2);
        first.setDescription("Whitening gel for home whitening.");
        first.setPrice(5);
        catalogue.add(first);

        OpalescenseGel second=new OpalescenseGel ();
        second.setTitle("Opalescense Gel");
        second.setReactantPercent(15);
        second.setVolume(1.2);
        second.setDescription("Whitening gel for home whitening.");
        second.setPrice(6);
        catalogue.add (second);

        OpalescenseGel third=new OpalescenseGel ();
        third.setTitle("Opalescense Gel");
        third.setReactantPercent(20);
        third.setVolume(1.2);
        third.setDescription("Whitening gel for home whitening.");
        third.setPrice(7);
        catalogue.add(third);

        Hemostatic viscosyringe=new Hemostatic();
        viscosyringe.setTitle ("ViscoStat");
        viscosyringe.setHemostaticSubstance("Ferric Sulphate");
        viscosyringe.setDescription("ViscoStat hemostatic is a 20% ferric sulfate equivalent solution with inert binding agents in a viscous, aqueous vehicle. ");
        viscosyringe.setVolume(1.2);
        viscosyringe.setPrice(4.8);
        hemolist.add(viscosyringe);

        Hemostatic viscoclearsyringe=new Hemostatic();
        viscoclearsyringe.setTitle("ViscoStat Clear");
        viscoclearsyringe.setHemostaticSubstance("Aluminum Chloride");
        viscoclearsyringe.setDescription("ViscoStat Clear is recommended for anterior restorations because it quickly eliminates minor bleeding without leaving any residue.");
        viscoclearsyringe.setVolume(1.2);
        viscoclearsyringe.setPrice(5.04);
        hemolist.add(viscoclearsyringe);

        Hemostatic viscodispenser=new Hemostatic();
        viscodispenser.setTitle("ViscoStat");
        viscodispenser.setHemostaticSubstance("Ferric Sulphate");
        viscodispenser.setDescription("ViscoStat hemostatic is a 20% ferric sulfate equivalent solution with inert binding agents in a viscous, aqueous vehicle.");
        viscodispenser.setVolume(30);
        viscodispenser.setPrice(43.3);
        hemolist.add(viscodispenser);

        Hemostatic viscocleardispenser=new Hemostatic();
        viscocleardispenser.setTitle("ViscoStat Clear");
        viscocleardispenser.setHemostaticSubstance("Aluminuim Chloride");
        viscocleardispenser.setDescription("ViscoStat Clear is recommended for anterior restorations because it quickly eliminates minor bleeding without leaving any residue.");
        viscocleardispenser.setVolume(30);
        viscocleardispenser.setPrice(45.4);
        hemolist.add(viscocleardispenser);
    }


}
