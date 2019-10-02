import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.scene.Group;

public class Arkanoid extends Application {

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        // ----------------------------------------
        // DEBUT création et affichage de l'IG
        Pane root = new Pane();
        Scene scene = new Scene(root, 450, 500, Color.ANTIQUEWHITE);
        Group Jeu = new Group();
        stage.setTitle("Arkanoid");
        stage.setResizable(false);
        
        Rectangle cadreNiveaux = new Rectangle(0, 0, 100, 500);
        cadreNiveaux.setFill(Color.TRANSPARENT);
        cadreNiveaux.setStroke(Color.BLACK);
        cadreNiveaux.setStrokeWidth(3);
        
        // création des bouttons des niveaux
        Group Niveaux = new Group();
        int j = 1;
        while(j < 6) {
        	Button btnNiv = new Button(Integer.toString(j));
        	btnNiv.setLayoutX(37);
        	btnNiv.setLayoutY(j * 40);
        	btnNiv.setOnAction((ActionEvent event) -> {// évenement sur les boutons de niveaux
        		debutJeu(btnNiv.getText(), Jeu);
        	});
        	Niveaux.getChildren().add(btnNiv);
        	j++;
        } 
        j = 0;
        
        Button quit = new Button("Quit");
        quit.setLayoutX(27);
        quit.setLayoutY(450);
        quit.setOnAction((ActionEvent event) -> {// évenement sur bouton "Quit"
        	stage.close();
        });
        
        Button texte = new Button("Levels");//Titre pour les niveaux
        texte.setMinSize(95, 20);
        texte.setLayoutX(3);
        texte.setLayoutY(3);
        
		Niveaux.getChildren().addAll(quit, texte);
 
        root.getChildren().add(cadreNiveaux);
        root.getChildren().add(Niveaux);
        root.getChildren().add(Jeu);
        
        stage.setScene(scene);
        stage.show();

        // FIN création de l'IG  
    }
    
    public void debutJeu(String num, Group Jeu) {

        if(!Jeu.getChildren().isEmpty()) {
            Jeu.getChildren().clear();
        }      

        Rectangle cadreJeu = new Rectangle(125, 50, 300, 400);//cadre du jeu (murs)
        cadreJeu.setFill(Color.TRANSPARENT);
        cadreJeu.setStroke(Color.BLACK);
        cadreJeu.setStrokeWidth(3);
        

        Rectangle raquette = new Rectangle(cadreJeu.getX() + cadreJeu.getWidth() / 2 - 30, cadreJeu.getY() + cadreJeu.getHeight() - 20, 60, 10);
        raquette.setFill(Color.BLACK);

        Circle ball = new Circle(raquette.getX() + raquette.getWidth() / 2, raquette.getY() - 7, 6, Color.BLUE);
        final double speed = 200E-9; // vitesse balle (px per nanosecond)

        // tableaux de rebond sur la raquette (de 135° à 45°)
        // on utilise l'indice et pythagore pour déterminer xSpeed et ySpeed sur chaque pixel
        // le principe est que |speed| (hypoténuse et valeur du vecteur speed) reste constant
        double[] raquetteX = new double[(int)raquette.getWidth()];  
        double[] raquetteY = new double[(int)raquette.getWidth()];
        for(int i = 0; i < raquetteX.length; i++) {
        int x = i - raquetteX.length / 2;
            raquetteX[i] = x * Math.sqrt(Math.pow(speed, 2) / 2) / (raquetteX.length / 2);
            raquetteY[i] = - Math.sqrt(Math.pow(speed, 2) - Math.pow(raquetteX[i], 2));    
        }

        // création des briques
        Group Briques = new Group();
        ArrayList<Color> color = new ArrayList<>();
        color.add(Color.GREEN);
        color.add(Color.PURPLE);
        color.add(Color.ORANGE);
        
        
        //structure des niveaux
        switch(num){
        	case "1"://niveau 1
        		Rectangle brique = new Rectangle(50, 20);
        		brique.setFill(color.get((int)(Math.random()* 3)));
        		brique.setX(5 + cadreJeu.getX());
        		brique.setY(45 + cadreJeu.getY());
        		Rectangle brique2 = new Rectangle(40, 30);
        		brique2.setFill(color.get((int)(Math.random()* 3)));
        		brique2.setX(26 + cadreJeu.getX());
        		brique2.setY(120 + cadreJeu.getY());
        		Rectangle brique3 = new Rectangle(50, 110);
        		brique3.setFill(color.get((int)(Math.random()* 3)));
        		brique3.setX(180 + cadreJeu.getX());
        		brique3.setY(55 + cadreJeu.getY());
        		Briques.getChildren().addAll(brique, brique2, brique3);
        		break;
        	case "2"://niveau 2
        		Rectangle brique4 = new Rectangle(50, 20);
        		brique4.setFill(color.get((int)(Math.random()* 3)));
        		brique4.setX(77 + cadreJeu.getX());
        		brique4.setY(45 + cadreJeu.getY());
        		Rectangle brique5 = new Rectangle(40, 30);
        		brique5.setFill(color.get((int)(Math.random()* 3)));
        		brique5.setX(113 + cadreJeu.getX());
        		brique5.setY(150 + cadreJeu.getY());
        		Rectangle brique6 = new Rectangle(50, 110);
        		brique6.setFill(color.get((int)(Math.random()* 3)));
        		brique6.setX(200 + cadreJeu.getX());
        		brique6.setY(100 + cadreJeu.getY());
        		Rectangle brique7 = new Rectangle(50, 110);
        		brique7.setFill(color.get((int)(Math.random()* 3)));
        		brique7.setX(10 + cadreJeu.getX());
        		brique7.setY(100 + cadreJeu.getY());
        		Briques.getChildren().addAll(brique4, brique5, brique6, brique7);
        		break;
        	case "3"://niveau 3
        		Rectangle brique8 = new Rectangle(50, 20);
        		brique8.setFill(color.get((int)(Math.random()* 3)));
        		brique8.setX(20 + cadreJeu.getX());
        		brique8.setY(45 + cadreJeu.getY());
        		Rectangle brique9 = new Rectangle(40, 30);
        		brique9.setFill(color.get((int)(Math.random()* 3)));
        		brique9.setX(200 + cadreJeu.getX());
        		brique9.setY(200 + cadreJeu.getY());
        		Rectangle brique10 = new Rectangle(50, 110);
        		brique10.setFill(color.get((int)(Math.random()* 3)));
        		brique10.setX(180 + cadreJeu.getX());
        		brique10.setY(55 + cadreJeu.getY());
        		Rectangle brique11 = new Rectangle(120, 30);
        		brique11.setFill(color.get((int)(Math.random()* 3)));
        		brique11.setX(150 + cadreJeu.getX());
        		brique11.setY(300 + cadreJeu.getY());
        		Rectangle brique12 = new Rectangle(50, 110);
        		brique12.setFill(color.get((int)(Math.random()* 3)));
        		brique12.setX(10 + cadreJeu.getX());
        		brique12.setY(200 + cadreJeu.getY());
        		Briques.getChildren().addAll(brique8, brique9, brique10, brique11, brique12);
        		break;
        	case "4"://niveau 4
        		Rectangle brique13 = new Rectangle(80, 20);
        		brique13.setFill(color.get((int)(Math.random()* 3)));
        		brique13.setX(50 + cadreJeu.getX());
        		brique13.setY(45 + cadreJeu.getY());
        		Rectangle brique14 = new Rectangle(20, 30);
        		brique14.setFill(color.get((int)(Math.random()* 3)));
        		brique14.setX(200 + cadreJeu.getX());
        		brique14.setY(300 + cadreJeu.getY());
        		Rectangle brique15 = new Rectangle(100, 45);
        		brique15.setFill(color.get((int)(Math.random()* 3)));
        		brique15.setX(30 + cadreJeu.getX());
        		brique15.setY(100 + cadreJeu.getY());
        		Rectangle brique16 = new Rectangle(85, 60);
        		brique16.setFill(color.get((int)(Math.random()* 3)));
        		brique16.setX(140 + cadreJeu.getX());
        		brique16.setY(55 + cadreJeu.getY());
        		Rectangle brique17 = new Rectangle(50, 90);
        		brique17.setFill(color.get((int)(Math.random()* 3)));
        		brique17.setX(180 + cadreJeu.getX());
        		brique17.setY(55 + cadreJeu.getY());
        		Rectangle brique18 = new Rectangle(45, 55);
        		brique18.setFill(color.get((int)(Math.random()* 3)));
        		brique18.setX(20 + cadreJeu.getX());
        		brique18.setY(200 + cadreJeu.getY());
        		Briques.getChildren().addAll(brique13, brique14, brique15, brique16, brique17, brique18);
        		break;
        	case "5"://niveau 5
        		Rectangle brique19 = new Rectangle(50, 20);
        		brique19.setFill(color.get((int)(Math.random()* 3)));
        		brique19.setX(190 + cadreJeu.getX());
        		brique19.setY(300 + cadreJeu.getY());
        		Rectangle brique20 = new Rectangle(40, 30);
        		brique20.setFill(color.get((int)(Math.random()* 3)));
        		brique20.setX(200 + cadreJeu.getX());
        		brique20.setY(150 + cadreJeu.getY());
        		Rectangle brique21 = new Rectangle(80, 18);
        		brique21.setFill(color.get((int)(Math.random()* 3)));
        		brique21.setX(200 + cadreJeu.getX());
        		brique21.setY(220 + cadreJeu.getY());
        		Rectangle brique22 = new Rectangle(30, 40);
        		brique22.setFill(color.get((int)(Math.random()* 3)));
        		brique22.setX(220 + cadreJeu.getX());
        		brique22.setY(50 + cadreJeu.getY());
        		Rectangle brique23 = new Rectangle(45, 70);
        		brique23.setFill(color.get((int)(Math.random()* 3)));
        		brique23.setX(12 + cadreJeu.getX());
        		brique23.setY(12 + cadreJeu.getY());
        		Rectangle brique24 = new Rectangle(50, 110);
        		brique24.setFill(color.get((int)(Math.random()* 3)));
        		brique24.setX(114 + cadreJeu.getX());
        		brique24.setY(75 + cadreJeu.getY());
        		Rectangle brique25 = new Rectangle(67, 130);
        		brique25.setFill(color.get((int)(Math.random()* 3)));
        		brique25.setX(9 + cadreJeu.getX());
        		brique25.setY(150 + cadreJeu.getY());        		
        		Briques.getChildren().addAll(brique19, brique20, brique21, brique22, brique23, brique24, brique25);
        		break;
        }
        
        //ajouts des éléments à la vue
        Jeu.getChildren().add(cadreJeu);
        Jeu.getChildren().add(raquette);
        Jeu.getChildren().add(ball);
        Jeu.getChildren().add(Briques);

        SimpleIntegerProperty kd = new SimpleIntegerProperty(0); // état deplacement raquette droit
        SimpleIntegerProperty kg = new SimpleIntegerProperty(0); // état deplacement raquette gauche
        SimpleIntegerProperty lancerBalle = new SimpleIntegerProperty(0); // état balle
        
        //évenement appui touches
        Jeu.getParent().setOnKeyPressed(
            event2 -> {
                switch(event2.getCode()){
                    case LEFT:
                        kg.set(1);
                        break;
                    case RIGHT:
                        kd.set(1);
                        break;
                    case ENTER:
                        if(lancerBalle.get() == 0)
                            lancerBalle.set(2);
                        break;
                    case R:
                    	debutJeu(num, Jeu);
                    default:
                        break;
                }
            }
        );        
        
        //évenement relache touches
        Jeu.getParent().setOnKeyReleased(
            event2 -> {
                switch(event2.getCode()){
                    case LEFT:
                        kg.set(0);
                        break;
                    case RIGHT:
                        kd.set(0);
                        break;
                    default:
                        break;
                }
            }
        );

        new AnimationTimer() {
            double xSpeed = 0E-9, ySpeed = 0E-9; // vitesse balle (px per nanosecond)
            double rSpeed = 2 * speed; // vitesse raqette
            long lastTime = System.nanoTime();

            @Override
            public void handle(long time) {
                long dt = time - lastTime;
                double dx = xSpeed * dt, dy = ySpeed * dt;
                double posX = ball.getCenterX(), posY = ball.getCenterY();
                double nPosX = posX + dx, nPosY = posY + dy;

                // test de rebond sur les "murs"
                if (nPosX <= cadreJeu.getX() + ball.getRadius() + cadreJeu.getStrokeWidth() || nPosX >= cadreJeu.getX() + cadreJeu.getWidth() - ball.getRadius() - cadreJeu.getStrokeWidth()) {
                            dx = -dx;
                            xSpeed = -xSpeed;
                        }
                        if (nPosY <= cadreJeu.getY() + ball.getRadius() + cadreJeu.getStrokeWidth()) {
                            dy = -dy;
                            ySpeed = -ySpeed;
                        }
                        //cas de défaite
                        if (nPosY >= cadreJeu.getY() + cadreJeu.getHeight() - ball.getRadius()) {
                            Jeu.getChildren().remove(ball);
                            cadreJeu.setFill(Color.RED);
                            stop();
                        }

                        // test de rebond sur la raquette
                        if (nPosY >= raquette.getY() - ball.getRadius() && nPosX + ball.getRadius() >= raquette.getX() && nPosX - ball.getRadius() <= raquette.getX() + raquette.getWidth()) {
                            // on ajuste aux extrémités de la raquette
                            if(posX < raquette.getX()) { 
                                xSpeed = raquetteX[0];
                                ySpeed = raquetteY[0];
                            } else if(posX > raquette.getX() + raquette.getWidth()) {
                                xSpeed = raquetteX[raquetteX.length - 1];
                                ySpeed = raquetteY[raquetteY.length - 1];
                            } else {
                                xSpeed = raquetteX[(int)(posX - raquette.getX())];
                                ySpeed = raquetteY[(int)(posX - raquette.getX())];
                            }
                            dx = xSpeed * dt;
                            dy = ySpeed * dt;
                        }

                        // test de rebond sur les briques
                        Iterator ite = Briques.getChildren().iterator(); 
                        while (ite.hasNext()) {
                            Rectangle brique = (Rectangle) ite.next(); 
                            if (posX + ball.getRadius() < brique.getX()) { // coté gauche
                                if (nPosX >= brique.getX() - ball.getRadius() && nPosY + ball.getRadius() >= brique.getY() && nPosY - ball.getRadius() <= brique.getY() + brique.getHeight()) {
                                    dx = -dx;
                                    xSpeed = -xSpeed;
                                    ite.remove();
                                }
                            } else if (posX - ball.getRadius() > brique.getX() + brique.getWidth()) { // coté droit
                                if (nPosX <= brique.getX() + brique.getWidth() + ball.getRadius() && nPosY + ball.getRadius() >= brique.getY() && nPosY - ball.getRadius() <= brique.getY() + brique.getHeight()) {
                                    dx = -dx;
                                    xSpeed = -xSpeed;
                                    ite.remove();
                                }
                            }
                            if (posY + ball.getRadius() < brique.getY()) { // au dessus
                                if (nPosY >= brique.getY() - ball.getRadius() && nPosX + ball.getRadius() >= brique.getX() && nPosX - ball.getRadius() <= brique.getX() + brique.getWidth()) {
                                    dy = -dy;
                                    ySpeed = -ySpeed;
                                    ite.remove();
                                }
                            } else if (posY - ball.getRadius() > brique.getY() + brique.getHeight()) { // en dessous
                                if (nPosY <= brique.getY() + brique.getHeight() + ball.getRadius() && nPosX + ball.getRadius() >= brique.getX() && nPosX - ball.getRadius() <= brique.getX() + brique.getWidth()) {
                                    dy = -dy;
                                    ySpeed = -ySpeed;
                                    ite.remove();
                                }
                            }
                            
                            //cas de victoire
                            if(Briques.getChildren().isEmpty()) {
                                Jeu.getChildren().remove(ball);
                                cadreJeu.setFill(Color.BLUE);      
                                stop(); 
                            }
                        }

                        // déplacement de la raquette
                        if(kd.get() == 1 && kg.get() == 0) {
                            if(raquette.getX() + rSpeed * dt + 8 < cadreJeu.getX()+ cadreJeu.getWidth()- raquette.getWidth()) {
                                raquette.setX(raquette.getX() + rSpeed * dt);
                            } else {
                                raquette.setX(cadreJeu.getX()+ cadreJeu.getWidth()- raquette.getWidth() - 8);
                            }
                        } else if(kd.get() == 0 && kg.get() == 1) {
                            if(raquette.getX() - rSpeed * dt - 8 > cadreJeu.getX()){
                                raquette.setX(raquette.getX() - rSpeed * dt);
                            } else {
                                raquette.setX(cadreJeu.getX() + 8);
                            }
                        }

                        // déplacement balle
                        switch (lancerBalle.get()) {
                            case 2:
                                xSpeed = 0;
                                ySpeed = - speed;
                                lancerBalle.set(1);
                                break;
                            case 1:
                                ball.setCenterX(nPosX);
                                ball.setCenterY(nPosY);
                                break;
                            default:
                                ball.setCenterX(raquette.getX() + raquette.getWidth() / 2);
                                break;
                        }
                        lastTime = time;
            }

        }.start();  
    }    
}



