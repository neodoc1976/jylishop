package org.george.jylishop.db;

import org.george.jylishop.domain.Contact;
import org.george.jylishop.domain.Hemostatic;
import org.george.jylishop.domain.OpalescenseGel;
import org.george.jylishop.domain.Product;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




import java.util.ArrayList;

/**
 * Created by Yulya on 02.05.2016.
 */
@Component
public class DataBase {

    private Map<Integer,Product> catalogue = new HashMap<>();

    public List<Product> getCatalogue(){
        List<Product> list= new ArrayList<>(catalogue.values());
        return list;
    }



//    public ArrayList<Product> getCatalogue() {
//        return catalogue;


    public DataBase() {
        OpalescenseGel first = new OpalescenseGel();
        first.setTitle("Opalescense Gel PF");
        first.setReactantPercent(10);
        first.setVolume(1.2);
        first.setDescription("Whitening gel for home whitening.");
        first.setPrice(5);
        first.setPicture("first.jpg");
        first.setId(101);
        catalogue.put(first.getId(),first);

        OpalescenseGel second = new OpalescenseGel();
        second.setTitle("Opalescense Gel PF");
        second.setReactantPercent(15);
        second.setVolume(1.2);
        second.setDescription("Whitening gel for home whitening.");
        second.setPrice(6);
        second.setPicture("second.jpg");
        second.setId(202);
        catalogue.put(second.getId(),second);

        OpalescenseGel third = new OpalescenseGel();
        third.setTitle("Opalescense Gel PF");
        third.setReactantPercent(20);
        third.setVolume(1.2);
        third.setDescription("Whitening gel for home whitening.");
        third.setPrice(7);
        third.setPicture("third.jpg");
        third.setId(303);
        catalogue.put(third.getId(),third);

        Hemostatic viscosyringe = new Hemostatic();
        viscosyringe.setTitle("ViscoStat");
        viscosyringe.setHemostaticSubstance("Ferric Sulphate");
        viscosyringe.setDescription("ViscoStat hemostatic is a 20% ferric sulfate equivalent solution with inert binding agents in a viscous, aqueous vehicle. ");
        viscosyringe.setVolume(1.2);
        viscosyringe.setPrice(4.8);
        viscosyringe.setId(404);
        viscosyringe.setPicture("visco.jpg");
        catalogue.put(viscosyringe.getId(),viscosyringe);

        Hemostatic viscoclearsyringe = new Hemostatic();
        viscoclearsyringe.setTitle("ViscoStat Clear");
        viscoclearsyringe.setHemostaticSubstance("Aluminum Chloride");
        viscoclearsyringe.setDescription("ViscoStat Clear is recommended for anterior restorations because it quickly eliminates minor bleeding without leaving any residue.");
        viscoclearsyringe.setVolume(1.2);
        viscoclearsyringe.setPrice(5.04);
        viscoclearsyringe.setId(505);
        viscoclearsyringe.setPicture("viscoclear.jpg");
        catalogue.put(viscoclearsyringe.getId(),viscoclearsyringe);

        Hemostatic viscodispenser = new Hemostatic();
        viscodispenser.setTitle("ViscoStat");
        viscodispenser.setHemostaticSubstance("Ferric Sulphate");
        viscodispenser.setDescription("ViscoStat hemostatic is a 20% ferric sulfate equivalent solution with inert binding agents in a viscous, aqueous vehicle.");
        viscodispenser.setVolume(30);
        viscodispenser.setPrice(43.3);
        viscodispenser.setId(606);
        viscodispenser.setPicture("viscobig.jpg ");
        catalogue.put(viscoclearsyringe.getId(),viscodispenser);

        Hemostatic viscocleardispenser = new Hemostatic();
        viscocleardispenser.setTitle("ViscoStat Clear");
        viscocleardispenser.setHemostaticSubstance("Aluminuim Chloride");
        viscocleardispenser.setDescription("ViscoStat Clear is recommended for anterior restorations because it quickly eliminates minor bleeding without leaving any residue.");
        viscocleardispenser.setVolume(30);
        viscocleardispenser.setPrice(45.4);
        viscocleardispenser.setId(707);
        viscocleardispenser.setPicture("viscoclearbig.jpg");
        catalogue.put(viscocleardispenser.getId(),viscocleardispenser);
    }

}
