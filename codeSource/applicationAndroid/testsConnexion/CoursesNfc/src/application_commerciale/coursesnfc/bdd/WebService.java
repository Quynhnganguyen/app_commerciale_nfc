package application_commerciale.coursesnfc.bdd;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class WebService {
	 
    private final String URL = "http://quiet-wildwood-3463.herokuapp.com/clients/liste_magasins";
 
    Gson gson;
 
    public WebService() {;
        gson = new Gson();
    }
 
    private InputStream sendRequest(URL url) throws Exception {
 
        try {
            // Ouverture de la connexion
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
 
            // Connexion à l'url
            urlConnection.connect();
 
            // Si le serveur nous répond avec un code OK
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return urlConnection.getInputStream();
            }
        } catch (Exception e) {
            throw new Exception("");
        }
 
        return null;
 
    }
 
    public List<Magasins> getMagasins() {
 
        try {
            // Envoie de la requête
            InputStream inputStream = sendRequest(new URL(URL));
 
            // Vérification de l'inputStream
            if(inputStream != null) {
                // Lecture de l'inputStream dans un reader
                InputStreamReader reader = new InputStreamReader(inputStream);
 
                // Return la liste désérialisé par le moteur gson
                return gson.fromJson(reader, new TypeToken<List<Magasins>>(){}.getType());
            }
 
        } catch (Exception e) {
            Log.e("WebService", "Impossible de rapatrier les données");
        }
        return null;
    }
    
}