/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author Imen BenAbderrahmen
 */
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import APP.CleanModern;
import Entities.Article;
import com.codename1.ui.TextArea;
import java.io.IOException;
//import tn.esprit.entities.ProduitPanier;

public class ArticlesDetailsForm extends Form {

    final Resources res;
    static Article ArticleS;
    private Article article;

    public ArticlesDetailsForm() {
        super("Details Autorisation", new BorderLayout());
        this.res = CleanModern.stheme;
        this.article = ArticleS;
        System.out.println(article);

        
        Container north = new Container(new FlowLayout(Component.CENTER));
        north.setUIID("BoutiqueAuto");

        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth() / 2, this.getHeight() / 5, 0xFFFFFFFF), true);
        Image img = URLImage.createToStorage(placeholder, article.getImage(), "http://localhost/PI/web/Uploads/article/photo/" + article.getImage(),
                URLImage.RESIZE_SCALE_TO_FILL);
        north.add(img);


        this.addComponent(BorderLayout.NORTH, north);

        Container center = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        center.setUIID("AutoCentre");
        
        Label titre = new Label("Titre : "+article.getTitre());
        titre.setUIID("AutoInfo");
        center.addComponent(titre);
        
        Label date = new Label("Date : ");
        date.setUIID("AutoInfo");
        center.addComponent(date);
        
        TextArea dates = new TextArea(article.getDate());
        dates.setUIID("AutoInfo");
        center.addComponent(dates);
        

        Label iduser = new Label("Auteur ID : "+article.getId_user());
        iduser.setUIID("AutoInfo");
        center.addComponent(iduser);

        Label descr = new Label("Description: ");
        descr.setUIID("AutoInfo");
        center.addComponent(descr);
        
        TextArea desc = new TextArea(article.getDescription());
        desc.setUIID("AutoInfo");
        center.addComponent(desc);
        
/*
        Button btnModifier = new Button("Demander autorisation");
        center.addComponent(btnModifier);
       btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    //System.out.print();
                    //PanierService p = new PanierService();
                    //p.addPanier(produit.getId());
                    // ADD NEW DEMANDE FORM
                    NewDemandeForm.autoS=autorisation;
                    Form bdf = new NewDemandeForm();
                    bdf.show();
                } catch (IOException ex) {
                    System.out.print("Erreur New Demande Form :"+ex.getMessage());
                }
               
            }
        }); 
        */
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



