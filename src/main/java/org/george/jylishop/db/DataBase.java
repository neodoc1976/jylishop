package org.george.jylishop.db;

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


    }


}
