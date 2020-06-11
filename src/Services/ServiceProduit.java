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
import static com.codename1.ui.CN.addNetworkErrorListener;
import static com.codename1.ui.CN.updateNetworkThreadCount;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.codename1.io.Log;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
/**
 *
 * @author Mega-PC
 */
public class ServiceProduit {

    public ArrayList<Produit> tasks;
    public String result = "";
public static final String ACCOUNT_SID = "ACdcf7ea577c4b74d120d4136697ff70fd";
    public static final String AUTH_TOKEN = "04bbbccb5248f7d2e759d80b7a9c8978";
    public static ServiceProduit instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    ServiceProduit() {
        req = new ConnectionRequest();
    }

    public static ServiceProduit getInstance() {
        if (instance == null) {
            instance = new ServiceProduit();
        }
        return instance;
    }

    public ArrayList<Produit> parseTasks(String jsonText) {
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
                Produit t = new Produit();
                System.out.println(t);

                // Float test;
                // test=Float.parseFloat();
                //
                float id_prod = Float.parseFloat(obj.get("idProd").toString());
                t.setId_prod((int) id_prod);
                t.setQuantite(((int) Float.parseFloat(obj.get("quantite").toString())));
                t.setQteApresVente(((int) Float.parseFloat(obj.get("quantiteAV").toString())));
                t.setPrix_prod((Float.parseFloat(obj.get("prixProd").toString())));
                t.setNom(obj.get("nom").toString());
                t.setMarque(obj.get("marque").toString());
                t.setUrlImage(obj.get("urlImage").toString());
                t.setType_prod(obj.get("typeProd").toString());
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

    public ArrayList<Produit> getAllMesProducts() {
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

    public ArrayList<Produit> getMesProducts(user u) {
        String url = Statics.BASE_URL + "panier/affichePanierMobile?idUtilisateur=" + u.getId();
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

    public boolean Participate(int idProd, int idUser) {
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

        String url = Statics.BASE_URL + "panier/add/" + idProd + "/" + idUser;

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

    public boolean ProceedWithChekout() throws IOException {


        String url = Statics.BASE_URL + "commande/ajouter";

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);  

        LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("Votre commande a été effectuée avec succès!");
        n.setAlertTitle("Commande");
        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound

        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis(), // fire date/time
                LocalNotification.REPEAT_MINUTE // Whether to repeat and what frequency
        );
                Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21621448606"),
                new com.twilio.type.PhoneNumber("+12055572243"),
                "Votre commande a été effectuée avec succès. Vérifiez votre commande dans la rubrique de vos achats pour le total à payer"
                        + "3la3ajltin.")
            .create();
        return resultOK;
    }

}
