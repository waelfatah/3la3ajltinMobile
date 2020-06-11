/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Commande;
import Entities.Produit;
import Entities.user;
import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mega-PC
 */
public class ServiceCommande {
   public ArrayList<Commande> tasks;
    public String result = "";
    double tmestampDate,tmestampDate1;
    public static ServiceCommande instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceCommande() {
        req = new ConnectionRequest();
    }

    public static ServiceCommande getInstance() {
        if (instance == null) {
            instance = new ServiceCommande();
        }
        return instance;
    }

    public ArrayList<Commande> parseTasks(String jsonText) {
        try {
            tasks = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
             */
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
             */
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {

                //Création des tâches et récupération de leurs données
                Commande t = new Commande();
                System.out.println(t);

                // Float test;
                // test=Float.parseFloat();
                //
                float id_commande = Float.parseFloat(obj.get("idCommande").toString());
                t.setId_commande((int) id_commande);
                
               
                t.setPrix_total((Float.parseFloat(obj.get("prixTotal").toString())));
          Map<String,Object> dateCreationObj1 = (Map<String,Object>) obj.get("Date");
                double tmestampDate1 = Double.parseDouble(dateCreationObj1.get("timestamp").toString());
                long timeStampDateF = (long)tmestampDate1 * 1000;
                Date datefin = new Date(timeStampDateF);
                t.setDate(datefin.toString());
                //    String fileName = sd.format(new Date());

                //Ajouter la tâche extraite de la réponse Json à la liste
                tasks.add(t);
            }

        } catch (IOException ex) {

        }
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        return tasks;
    }

    public ArrayList<Commande> getAllMesProducts() {
        String url = Statics.BASE_URL + "tasks/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }

    public ArrayList<Commande> getMesCommandes(user u) {
        String url = Statics.BASE_URL + "commande/afficheCommandeMobile?idUtilisateur=" + u.getId();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
    /*
      public String supprimerEvenement(Produit t) {
         
       // String url = Statics.BASE_URL + "SupprimerE?idEvenement="+t.getId_Evenement()+ "&idUtilisateur=" + u.getId()+ "&nombreMaxEvenement=" + t.getNombre_max_Evenement();
              
          
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        System.out.println(url);
                   

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                String data = new String(req.getResponseData()); //  ex.getMessage();
                JSONParser j = new JSONParser();
                Map<String, Object> tasksListJson;
                // tasksListJson = j.parseJSON(new CharArrayReader(data.toCharArray()));
                // result=(String) tasksListJson.get("body");
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
     */

    public boolean Participate(int idComm) {
        LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("It's time to take a break and look at me");
        n.setAlertTitle("Break Time!");
        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound

        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE // Whether to repeat and what frequency
        );

        String url = Statics.BASE_URL + "commande/detailsCommande/" + idComm;

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOK;
    }
}
