/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import Entities.Article;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Nour ghidaoui
 */
public class ServiceArticle {
    
    public ArrayList<Article> tasks;
    public static ServiceArticle instance=null;
    public boolean resultOK;
   private ConnectionRequest req;
   double tmestampDate,tmestampDate1;
     public ServiceArticle() {
         req = new ConnectionRequest();
    }
        public static ServiceArticle getInstance() {
        if (instance == null) {
            instance = new ServiceArticle();
        }
        return instance;
    }
        
      public ArrayList<Article>  parseListTaskJson(String json){
        
        ArrayList<Article> listArticle = new ArrayList<>();
        
        try{
        tasks = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
        List<Map<String,Object>> list = (List<Map<String,Object>>)tasks.get("root");
        for(Map<String,Object> obj : list){
        Article a = new Article();
         float id = Float.parseFloat(obj.get("idarticle").toString());
         float     idu=1;
         if (obj.get("id_user")!=null)
               idu = Float.parseFloat(obj.get("id_user").toString());
          Map<String,Object> dateCreationObj1 = (Map<String,Object>) obj.get("date");
                double tmestampDate1 = Double.parseDouble(dateCreationObj1.get("timestamp").toString());
                long timeStampDateF = (long)tmestampDate1 * 1000;
                Date datefin = new Date(timeStampDateF);
                a.setDate(datefin.toString());
          
          
          a.setIdArticle((int)id);
            a.setTitre(obj.get("titre").toString());
            
                 a.setId_user((int)idu);
                a.setDescription(obj.get("description").toString());
                 a.setImage(obj.get("image").toString());
                 
                 System.out.println(a+"\n ***********************");
                 
                 listArticle.add(a);
        }    
            
            
        } catch (IOException ex){
                 }
            System.out.println(listArticle);        
        return listArticle;
      
    }   
        
        
        
  ArrayList<Article> listArticle= new ArrayList<>();
    public ArrayList<Article> RecupererArticles(){
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI/web/app_dev.php/affichermobile");
         con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                listArticle = parseListTaskJson(new String(con.getResponseData()));
                con.removeResponseListener(this);
                System.out.println(listArticle);                      
            }
         });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listArticle;
    }
    
  public ArrayList<Article> GetMyArticles(int user){
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI/web/app_dev.php/GetMyArticles/"+user);
         con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                listArticle = parseListTaskJson(new String(con.getResponseData()));
                con.removeResponseListener(this);
                System.out.println(listArticle);                      
            }
         });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listArticle;
    }
  
      public void DeleteArticle(int id){
       
        String url = "http://localhost/PI/web/app_dev.php/DelArticle/"
                + id;
        ConnectionRequest con = new ConnectionRequest(url, true);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
      
          public void editArticle(Article A) {
       String url = "http://localhost/PI/web/app_dev.php/EditArticle?titre="+A.getTitre()+"&desc="+A.getDescription()+"&id="+A.getIdArticle();
        ConnectionRequest con = new ConnectionRequest(url, true);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con); 
    }

    public void addArticle(Article art) {
        System.out.println(art.getImage());
        String url = "http://localhost/PI/web/app_dev.php/AddArticle?titre="+art.getTitre()+"&userid="+art.getId_user()+"&desc="+art.getDescription()+"&img="+art.getImage();
        ConnectionRequest con = new ConnectionRequest(url, true);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        
    }
        
        
        
        
        
        
        
}
