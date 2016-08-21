package org.george.jylishop.services;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public  List<String> getAllPictures() {

        return getPicturesList(PICTURES);
    }

    public List<String> getAllLogo() {

        return getPicturesList(LOGO);
    }

    public void saveLogoPhoto(InputStream is, String name) {
        saveFiles(is,LOGO,name);

    }

    public void saveProductPhoto(InputStream is, String name) {
        saveFiles(is,PICTURES,name);


    }

    private List<String> getPicturesList(String path) {
        ArrayList<String> pictureList = new ArrayList<>();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            pictureList.add(file.getName());
        }
        return pictureList;
    }

     private void saveFiles (InputStream is,String path,String name){

         try {
             Files.copy(is,Paths.get(path,name));

         } catch (IOException e){
             e.printStackTrace();
         }
     }


}
