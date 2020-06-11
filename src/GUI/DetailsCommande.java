/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import APP.CleanModern;
import Entities.Article;
import Entities.Commande;
import static GUI.MesArticlesDetailsForm.ArticleS;
import Services.ServiceArticle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;


/**
 *
 * @author Mega-PC
 */
public class DetailsCommande extends BaseForm{
    final Resources res;
    static Commande CommandeS;
    private Commande commande;

    public DetailsCommande() {
        super("Details Article", new BorderLayout());
        this.res = CleanModern.stheme;
        this.commande = CommandeS;
        System.out.println(commande);

        
        Container north = new Container(new FlowLayout(Component.CENTER));
        north.setUIID("BoutiqueAuto");



        this.addComponent(BorderLayout.NORTH, north);

        Container center = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        center.setUIID("AutoCentre");
        
        Label titre = new Label("Id Commande : "+commande.getId_commande());
        titre.setUIID("AutoInfo");
        center.addComponent(titre);
        
       Label conf = new Label("Prix Total : "+commande.getPrix_total());
        conf.setUIID("AutoInfo");
        center.addComponent(conf);
        
        Label date = new Label("Date : ");
        date.setUIID("AutoInfo");
        center.addComponent(date);
        
        TextArea dates = new TextArea(commande.getDate());
        dates.setUIID("AutoInfo");
        center.addComponent(dates);
        
        
        this.addComponent(BorderLayout.CENTER, center);
                    Button btnAdd = new Button("Retour");
            btnAdd.addActionListener(cliqueEvent -> {
                Form bdf = new AfficherCommande(res);
                  bdf.show();
                });
            north.addComponent(btnAdd);

    }
}
