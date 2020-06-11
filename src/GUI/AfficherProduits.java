/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Produit;
import Entities.Session;
import Services.ServiceProduit;
import com.codename1.components.InfiniteScrollAdapter;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Mega-PC
 */
public class AfficherProduits extends BaseForm {
     public AfficherProduits(Resources res) {
           super("Newsfeed", BoxLayout.y());
      //  current = this;
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Newsfeed");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("ezezezezezz.jpg"), spacer1);
        addTab(swipe, res.getImage("ezezezezezz.jpg"), spacer2);
       //addTab(swipe, res.getImage("4.png"), spacer2);
                
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
     //   rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("All", barGroup);
        all.setUIID("SelectBar");
        RadioButton MesEvenement = RadioButton.createToggle("Mon Panier", barGroup);
        MesEvenement.setUIID("SelectBar");
        RadioButton AjoutEvenement = RadioButton.createToggle("Ajout", barGroup);
        AjoutEvenement.setUIID("SelectBar");
        RadioButton myFavorite = RadioButton.createToggle("My Favorites", barGroup);
        myFavorite.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, all, MesEvenement,AjoutEvenement),
                FlowLayout.encloseBottom(arrow)
        ));
        
        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(all, arrow);
           
        });
        bindButtonSelection(all, arrow);
        bindButtonSelection(MesEvenement, arrow);
        bindButtonSelection(AjoutEvenement, arrow);
        bindButtonSelection(myFavorite, arrow);
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
         AjoutEvenement.addActionListener(e -> new AfficherCommande(res).show());
     all.addActionListener(e -> new AfficherProduits(res).show());
       MesEvenement.addActionListener(e -> new AfficherPanier(res).show());
      
    
    //  AjoutEvenement.addActionListener(e -> new AjoutEvenement(res).show());
     // All.addActionListener(e -> new ListDesEvenement(res).show());
              setTitle("Panier");
 //user User = Session.getCurrentSession();
        Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
        FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
        EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() * 2, p.getHeight() * 2), false);
        java.util.List<Produit> product = ServiceProduit.getInstance().getAllMesProducts();
        MultiButton[] cmps = new MultiButton[product.size()];
        FontImage e = FontImage.createMaterial(FontImage.MATERIAL_EDIT_ATTRIBUTES, s);
        FontImage d = FontImage.createMaterial(FontImage.MATERIAL_DELETE, s);
        InfiniteScrollAdapter.createInfiniteScroll(this.getContentPane(), () -> {
            int i = 0;
            for (Produit prod : product) {
                cmps[i] = new MultiButton(prod.getNom()+"   "+prod.getPrix_prod());
                
                //cmps[i] = new MultiButton(prod.getPrix_prod());
                cmps[i].setEmblem(FontImage.createMaterial(FontImage.MATERIAL_ADD_CIRCLE, s));
                cmps[i].addActionListener(cliqueEvent -> {
                    //idUser
                    //id
                    int idProd = prod.getId_prod();
          
                    Session session = new Session();
                    int idUser = session.getCurrentSession().getId();
                    System.out.println("Produit " + idProd + " userId " + idUser);
                     boolean b = ServiceProduit.getInstance().Participate(idProd, idUser);
                
                    if (b) { 
                        Dialog.show("Ajout", "Success", "OK", "");
                    } else {
                        Dialog.show("Ajout", "Error", "OK", "");
                    }


                });

                String imgUrl = "http://127.0.0.1:8000/images/" + prod.getUrlImage();

                try {
                    cmps[i].setIcon(URLImage.createToStorage(placeholder, String.valueOf(prod.getId_prod()) + i, imgUrl));
                } catch (IllegalArgumentException ex) {
                    cmps[i].setIcon(FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s));

                }

                i++;
            }
            InfiniteScrollAdapter.addMoreComponents(this.getContentPane(), cmps, false);
        });

    
    }
    
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
    
    private void addTab(Tabs swipe, Image img, Label spacer) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
       // Label likes = new Label(likesStr);
   //     Style heartStyle = new Style(likes.getUnselectedStyle());
    //    heartStyle.setFgColor(0xff2d55);
      //  FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
       // likes.setIcon(heartImage);
       // likes.setTextPosition(RIGHT);

      //  Label comments = new Label(commentsStr);
       // FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
         //                   new SpanLabel(text, "LargeWhiteText"),
           //                 FlowLayout.encloseIn(likes, comments),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
    
   private void addButton(Image img, String title, boolean liked, int likeCount, int commentCount) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(title);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);

       Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
       likes.setTextPosition(RIGHT);
       if(!liked) {
           FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
       } else {
           Style s = new Style(likes.getUnselectedStyle());
           s.setFgColor(0xff2d55);
           FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
           likes.setIcon(heartImage);
       }
       Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
       FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);
       
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       BoxLayout.encloseX(likes, comments)
               ));
       add(cnt);
       image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
   }
    
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
    
}
