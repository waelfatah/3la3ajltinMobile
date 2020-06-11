/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import APP.CleanModern;
import Entities.Article;
import Services.ServiceArticle;
import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author user
 */
public class NewArticleForm extends Form {
    
    
     final Resources res;
    private Article art;
    private Image img;
    private String imgPath;
    
    
    public NewArticleForm() throws IOException {
        super("New Article", new BorderLayout());
        this.res = CleanModern.stheme;

        
        Container north = new Container(new FlowLayout(Component.CENTER));
        north.setUIID("MesDemandes");


        Container center = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        center.setUIID("AutoCentre");
        //center.addComponent;
        
        
        
        //INPUT FIELDS 
        
        Label titre = new Label("Titre: ");
        titre.setUIID("NewDemInfo");
        center.addComponent(titre);
        
        TextField input_titre = new TextField();
        input_titre.setUIID("NewDemInfo");
        center.addComponent(input_titre);
        
       Label desc = new Label("Description : ");
        desc.setUIID("NewDemInfo");
        center.addComponent(desc);
        
        TextField input_desc = new TextField();
        input_desc.setUIID("NewDemInfo");
        center.addComponent(input_desc);
        
        Label pap1 = new Label("Document : ");
        pap1.setUIID("NewDemInfo");
        center.addComponent(pap1);
        
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth()/2, this.getHeight() / 5, 0xFFFFFFFF), true);
        img = URLImage.createToStorage(placeholder, "placeholder.png", "http://localhost/PI/web/Uploads/article/photo/" + "placeholder.png", URLImage.RESIZE_SCALE_TO_FILL);
        ImageViewer iv = new ImageViewer();
        iv.setImage(img);
        center.addComponent(iv);
        iv.addPointerReleasedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    try {
                        imgPath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
                        img = Image.createImage(imgPath);                      
                        iv.setImage(img);
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }
            });
        
        
        
        //END INPUT FIELS

        Button btnModifier = new Button("Ajouter Article");
        center.addComponent(btnModifier);
       btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                art= new Article();
      System.out.print("Test Bouton Ajouter Article ("+imgPath);
      art.setTitre(input_titre.getText());
      art.setDescription(input_desc.getText());
      art.setImage(imgPath);
      art.setId_user(4);
           ServiceArticle SA = new ServiceArticle();
              SA.addArticle(art);
               Form demlist = new MesArticlesForm(res);
                  demlist.show();
               
            }
        }); 
        this.addComponent(BorderLayout.CENTER, center);
            this.addCommand(new Command("Retour") {

            @Override
            public void actionPerformed(ActionEvent evt) {
                Form bdfa = new ArticlesForm(res);
                    bdfa.show();
            }
        });

    }
}
