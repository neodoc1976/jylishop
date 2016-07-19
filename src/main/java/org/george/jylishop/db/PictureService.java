package org.george.jylishop.db;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yulya on 16.07.2016.
 */
@Service
public class PictureService {

    public static final String ROOT = "..\\static\\";
    public static final String LOGO = ROOT + "logo\\";
    public static final String PICTURES = ROOT + "pictures\\";

    public List<String> getAllPictures() {

        ArrayList<String> pictureList = new ArrayList<>();
        File folder = new File(PICTURES);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {

            pictureList.add(file.getName());
        }

        return pictureList;
    }

    public List<String> getAllLogo() {

        ArrayList<String> logoList = new ArrayList<>();
        File folder = new File(LOGO);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {

            logoList.add(file.getName());
        }

        return logoList;
    }

}
