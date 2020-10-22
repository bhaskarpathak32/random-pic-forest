package com.bhaskar.service;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import com.bhaskar.beans.FormData;
import com.bhaskar.db.DataRepository;
import com.fasterxml.jackson.core.JsonParser;


public class PicService {

    @Autowired
    DataRepository repo;

    public List<FormData> getPicList() {
        return (List<FormData>) repo.findAll();
    }

    public FormData getFormData(String id) {
        FormData data = new FormData();

        try {
            URL url = new URL("https://ajax.googleapis.com/ajax/services/search/images?"
                    + "v=1.0&q=barack%20obama&userip=INSERT-USER-IP");
            URLConnection connection = url.openConnection();
            connection.addRequestProperty("Referer", "");

            String line;
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            JSONObject json = new JSONObject(builder.toString());
            String imageUrl =
                    json.getJSONObject("responseData").getJSONArray("results").getJSONObject(0).getString("url");


            data.setUrl(imageUrl);
            repo.save(data);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Failure", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return data;
    }
}
