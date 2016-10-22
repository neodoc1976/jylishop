package org.george.jylishop.services;

import org.springframework.stereotype.Service;

import java.io.*;

/**
 * Created by Yulya on 29.08.2016.
 */
@Service
public class TextFileService {


    public String readDescription(InputStream is) throws  IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            return stringBuilder.toString();

        } finally {
            reader.close();
        }
    }
}