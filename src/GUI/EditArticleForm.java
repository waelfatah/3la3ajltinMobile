/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import APP.CleanModern;
import Entities.Article;
import Services.ServiceArticle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
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

/**
 *
 * @author user
 */
public class EditArticleForm extends Form {
    
    
     final Resources res;
    private Article art;
    static Article articleS;
    private String assistant="";

    public EditArticleForm() {
        super("Edit Article", new BorderLayout());
        this.res = CleanModern.stheme;

        
        Container north = new Container(new FlowLayout(Component.CENTER));
        north.setUIID("EditArticleUID");


        Container center = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        center.setUIID("AutoCentre");
        //center.addComponent;
        
        
        
        //INPUT FIELDS 
        
        
        Label titre = new Label("Nouveau Titre : ");
        titre.setUIID("NewDemInfo");
        center.addComponent(titre);
        
        TextField input_titre = new TextField();
       input_titre.setUIID("NewDemInfo");
        center.addComponent(input_titre);
        
         Label desc = new Label("Nouveau Description : ");
        desc.setUIID("NewDemInfo");
        center.addComponent(desc);
        
        TextField input_desc = new TextField();
       input_desc.setUIID("NewDemInfo");
        center.addComponent(input_desc);
        
        
        //END INPUT FIELS

        Button btnModifier = new Button("Modifier Article");
        center.addComponent(btnModifier);
       btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              art=articleS;
              art.setTitre(input_titre.getText());
              art.setDescription(input_desc.getText());
           ServiceArticle SA = new ServiceArticle();
              SA.editArticle(art);
               Form ff = new MesArticlesForm(res);
                  ff.show();
               
            }
        }); 
        
        this.addComponent(BorderLayout.CENTER, center);
            this.addCommand(new Command("Retour") {

            @Override
            public void actionPerformed(ActionEvent evt) {
                Form bdf = new MesArticlesForm(res);
                    bdf.show();
            }
        });

    }
}
