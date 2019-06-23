import MG2D.*;
import MG2D.geometrie.*;
import MG2D.audio.*;

import java.awt.Font;

public class Golf {

    public Golf() {

        int largeur = 800;
        int hauteur = 600;

        Fenetre f = new Fenetre("Golfy - Faites le meilleur Score", largeur, hauteur);

        Clavier clavier = f.getClavier();
        Souris souris = f.getSouris();

        // Création de l'animation du sprite "joueur"
        Animation a = new Animation("IMG/joueurb", "1", "5", "png", new Point(100, 95), 10, 10);
        a.setLecture(false);
        a.setNumeroImage("1");
        //Création du Easter Egg (Parceque un Easter Egg c'est fun ;)
        Texture Easter = new Texture("IMG/bob.png", new Point(5000, 102), 100, 100);
        
        //Création du Casque (Pour pas mourir dans l'espace... ça serait bête ;)
        Texture casque = new Texture("IMG/casque.png", new Point(136, 183), 35, 35);

        // Paramètrage de l'aperçu du tir en haut à gauche
        Animation cam = new Animation("IMG/cam/frame-", "3", "49", "gif", new Point(50, 490));
        cam.setLecture(true);
        cam.setNumeroImage("3");
        cam.translater(0, 500);
        Texte texteCam = new Texte(new String(""), new Font("Arial", Font.TYPE1_FONT, 15), new Point(100, 600 - 30));
        Texte camPuissance = new Texte(new String(""), new Font("Arial", Font.TYPE1_FONT, 15), new Point(75, 470));
        Texte camPuissancekh = new Texte(new String(""), new Font("Arial", Font.TYPE1_FONT, 15), new Point(135, 470));
        Texte camAngle = new Texte(new String(""), new Font("Arial", Font.TYPE1_FONT, 15), new Point(71, 450));
        Texte camAngleDegres = new Texte(new String(""), new Font("Arial", Font.TYPE1_FONT, 15), new Point(95, 450));

        // Sons et bruitages
        Musique musiqueBackground = new Musique("sounds/bg.mp3");
        Bruitage gameOver = new Bruitage("sounds/game-over.mp3");
        Bruitage youWin = new Bruitage("sounds/win3.mp3");
        Bruitage bruitBalle = new Bruitage("sounds/ball-hit.mp3");

        // Paramètrage du design de la puissance et de l'angle
        Rectangle puissance_contour = new Rectangle(Couleur.NOIR, new Point(150, 0), new Point(570, 50), false);
        Rectangle puissance = new Rectangle(Couleur.VERT, new Point(150, 12), new Point(150 + 20, 62), true);
        puissance_contour.translater(50, 12);
        puissance.translater(0, -500);
        puissance_contour.translater(0, -500);
        Ligne ligne_angle = new Ligne(Couleur.ROUGE, new Point(150, 100), new Point(200, 150));
        int pointB = 150;
        double x_puissance = 0;

        // Création des background (Terre et lunaire)
        Texture background = new Texture("IMG/background.png", new Point(0, 0));
        Texture background2 = new Texture("IMG/background.png", new Point(1600, 0));
        Texture bgLunaire1 = new Texture("IMG/bg_lunaire.png", new Point(0, 0), 1650, 600);
        Texture bgLunaire2 = new Texture("IMG/bg_lunaire_retourne.png", new Point(1600, 0), 1620, 600);
        Rectangle terreLunaire = new Rectangle(Couleur.NOIR, new Point(0, 0), new Point(800, 98), true);
        int xBackground = 0;
        int xBackground2 = 1600;

        // Création du menu avec un cadre pour le choix
        Texture bgMenu = new Texture("IMG/mode_jeu2.png", new Point(0, 0), 802, 600);
        Rectangle cadreChoix = new Rectangle(Couleur.BLANC, new Point(150, 293), new Point(800 - 150, 337), false);

        // Création de la page d'accueil de crédit
        Texture accueil = new Texture("IMG/accueil.png", new Point(0, 0), 800, 600);

        // Création de la balle (Qui est un oval rond en fait ^^)
        Point centre = new Point(152, 102);
        int rayon = 9;
        Ovale balle = new Ovale(Couleur.NOIR, centre, rayon, rayon, true);

        // Création de tous les textes
        Texte texte_accueil = new Texte(new String("Appuyez sur espace pour choisir l'angle de tir"),
                new Font("Arial", Font.TYPE1_FONT, 20), new Point(400, 350));
        Texte Hauteur_label = new Texte(Couleur.NOIR, new String("Hauteur : "),
                new Font("Calibri", Font.TYPE1_FONT, 25), new Point(310, 550));
        Texte Score_label = new Texte(new String("Score : "), new Font("Calibri", Font.TYPE1_FONT, 25),
                new Point(650, 550));
        Texte hauteur_score = new Texte(new String("0"), new Font("Calibri", Font.TYPE1_FONT, 25), new Point(410, 550));
        Texte score = new Texte(new String("0"), new Font("Calibri", Font.TYPE1_FONT, 25), new Point(720, 550));
        Texte texte_hauteur_max_label = new Texte(new String(""), new Font("Calibri", Font.TYPE1_FONT, 20),
                new Point(370, 300));
        Texte hauteur_max_label = new Texte(new String(""), new Font("Calibri", Font.TYPE1_FONT, 20),
                new Point(520, 300));
        Texte score_final_label = new Texte(new String(""), new Font("Calibri", Font.TYPE1_FONT, 20),
                new Point(410, 250));
        Texte score_final = new Texte(new String(""), new Font("Calibri", Font.TYPE1_FONT, 20), new Point(520, 250));
        Texte texte_jeu_reprendre = new Texte(new String(""), new Font("Arial", Font.TYPE1_FONT, 20),
                new Point(400, 200));
                
        int hauteur_max = 0;


        //Insertion des décors dans la fenêtre dans l'ordre des superpositions des plans
        f.ajouter(background);
        f.ajouter(background2);
        f.ajouter(bgLunaire1);
        f.ajouter(bgLunaire2);
        f.ajouter(a);
        f.ajouter(balle);
        f.ajouter(terreLunaire);
        f.ajouter(puissance);
        f.ajouter(puissance_contour);
        f.ajouter(ligne_angle);
        f.ajouter(Hauteur_label);
        f.ajouter(Score_label);
        f.ajouter(hauteur_score);
        f.ajouter(score);
        f.ajouter(texte_accueil);
        f.ajouter(hauteur_max_label);
        f.ajouter(texte_hauteur_max_label);
        f.ajouter(score_final);
        f.ajouter(score_final_label);
        f.ajouter(texte_jeu_reprendre);
        f.ajouter(bgMenu);
        f.ajouter(cadreChoix);
        f.ajouter(accueil);
        f.ajouter(cam);
        f.ajouter(texteCam);
        f.ajouter(camPuissance);
        f.ajouter(camPuissancekh);
        f.ajouter(camAngle);
        f.ajouter(camAngleDegres);
        
        
        f.rafraichir();
        
        //Création de variables globales (Oui, elles sont toutes utiles ;)
        int repetition = 0;
        int repetitionAnimationJoueur = 3;
        int repetitionAttente = 0;
        int puissance_tir = 0;
        int puissance_gauche = 0;
        int puissance_droite = 1;
        int pointB_gauche = 0;
        int pointB_droite = 1;
        int angle_tir = 0;
        double angle_temp = 0;
        int jouer = 0;
        int menu = 0;
        int nb_bonus = 0;
        int choix = 1;
        int graviteLunaire = 0;
        double puissance0 = 0;
        int OGy = 0;
        double temps = 0;
        int repetitionFinBalle = 250;
        int close = 0;
        int loading = 0;
        
        //Cette boucle sert à afficher l'écran d'accueil 2 secondes
        while (jouer == 0) {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
            musiqueBackground.lecture();
            jouer = 1;
            accueil.translater(0, -2000);
            f.rafraichir();
        }

        /**
         * Celle-ci sert à l'affichage du menu ainsi qu'au choix du mode de jeu
         * TERRESTRE ou bien LUNAIRE
         * Terrestre nous donne un tir avec une gravité de balle de 9.81m.s-1
         * Lunaire nous donne un tir avec une gravite de 3m.s-1,
         * Normalement 1.62m.s-1 mais la balle partait dans les fin fonds de l'univers,
         * donc 3 c'est bien, et réaliste, donc nickel
         */
        
        while (menu == 0) {
            try {
                Thread.sleep(20);
            } catch (Exception e) {
            }
            //Choix 1 c'est terrestre
            if (choix == 1) {
                if (clavier.getBasEnfoncee()) {
                    // cadreChoix.setA(new Point(150, 293));
                    // new Point(650, 337)
                    cadreChoix.setA(new Point(150, 186));
                    cadreChoix.setB(new Point(650, 230));
                    choix = 2;
                }
            } else if (choix == 2) {
                //Et le choix 2 c'est donc lunaire
                if (clavier.getHautEnfoncee()) {
                    // cadreChoix.setA(new Point(150, 293));
                    // new Point(650, 337)
                    cadreChoix.setB(new Point(650, 337));
                    cadreChoix.setA(new Point(150, 293));
                    choix = 1;
                }
            }
            /**
             * Pour valider, on peut faire Entrée, flèche droite, ou Espace
             * On aurait pu cliquer, mais ce n'est pas adapté à une borne arcade
             */
            if (clavier.getEntreeTape() || clavier.getDroiteEnfoncee() || clavier.getEspaceTape()) {
                menu = 1;
                bgMenu.translater(0, -2000);
                cadreChoix.translater(0, -500);
            }
            f.rafraichir();
        }
        
        /**
         * Ici, on modifie le background en fonction de notre choix de mode de jeu,
         * on modifie aussi la couleur des textes, car dans l'espace le fond est noir,
         * et du noir sur du noir, c'est pas ouf...
         */
        if (choix == 1) {
            graviteLunaire = 0;
            bgLunaire1.translater(0, -2000);
            bgLunaire2.translater(0, -2000);
            terreLunaire.translater(0, -500);

        } else {
            graviteLunaire = 1;
            //On ajoute l'Easter Egg caché dans ce niveau et le casque
            f.ajouter(Easter);
            f.ajouter(casque);
            background.translater(0, -2000);
            background2.translater(0, -2000);
            Hauteur_label.setCouleur(Couleur.BLANC);
            hauteur_score.setCouleur(Couleur.BLANC);
            Score_label.setCouleur(Couleur.BLANC);
            score.setCouleur(Couleur.BLANC);
            texte_accueil.setCouleur(Couleur.BLANC);
            balle.setCouleur(Couleur.BLANC);
            texte_hauteur_max_label.setCouleur(Couleur.BLANC);
            hauteur_max_label.setCouleur(Couleur.BLANC);
            score_final.setCouleur(Couleur.BLANC);
            score_final_label.setCouleur(Couleur.BLANC);
            puissance_contour.setCouleur(Couleur.BLANC);
            texte_jeu_reprendre.setCouleur(Couleur.BLANC);
            texteCam.setCouleur(Couleur.BLANC);
            camPuissance.setCouleur(Couleur.BLANC);
            camPuissancekh.setCouleur(Couleur.BLANC);
            camAngle.setCouleur(Couleur.BLANC);
            camAngleDegres.setCouleur(Couleur.BLANC);
        }

        // Ici on choisis notre angle de tir

        while (angle_tir == 0) {
            try {
                Thread.sleep(5);
            } catch (Exception e) {
            }

            // Permet d'animer le 2ème point de la ligne d'angle entre y=100 et y=180
            if (pointB == 180) {
                pointB_gauche = 1;
                pointB_droite = 0;
            } else if (pointB == 100) {
                pointB_gauche = 0;
                pointB_droite = 1;
            }
            if (pointB_gauche == 1)
                pointB--;
            if (pointB_droite == 1)
                pointB++;
            ligne_angle.setB(new Point(200, pointB));

            /**
             * On met le choix de notre angle en pourcentage que l'on ramène à 90 (degré maximum),
             * Le degré maximum est donc 100%, donc 90° etc...
             */

            if (clavier.getEspaceTape()) {
                angle_temp = ((pointB - 100) / 80.0) * 90;
                angle_tir = (int) angle_temp;
                texte_accueil.setTexte(new String("Appuyez sur Espace pour choisir la puissance de tir"));
                puissance.translater(0, 500);
                puissance_contour.translater(0, 500);
            }
            f.rafraichir();
        }

        // Choix de la puissance de tir

        while (puissance_tir == 0) {
            try {
                Thread.sleep(9);
            } catch (Exception e) {
            }
            //Ici c'est pareil que l'angle, le rectangle va translater de 50px à 450px
            if (x_puissance == 450) {
                puissance_gauche = 1;
                puissance_droite = 0;
            } else if (x_puissance == 50) {
                puissance_gauche = 0;
                puissance_droite = 1;
            }
            if (puissance_gauche == 1) {
                puissance.translater(-5, 0);
                x_puissance -= 5;
            } else if (puissance_droite == 1) {
                puissance.translater(+5, 0);
                x_puissance += 5;
            }
            //Changement de la couleur en fonction de l'avancement de la puissance
            if (x_puissance == 400)
                puissance.setCouleur(Couleur.ROUGE);
            if (x_puissance == 200)
                puissance.setCouleur(Couleur.VERT);

            //On met la puissance en pourcentage comme pour l'angle tout à l'heure
            if (clavier.getEspaceTape()) {
                x_puissance = x_puissance - 50;
                // la puissance est désormais entre 50 et 400, remise en pourcentage :
                x_puissance = (x_puissance / 400) * 100;
                /**
                 * puissance0 (sert uniquement pour indiquer une vitesse "réelle à l'utilisateur", elle
                 * ne sert pas aux calculs, c'est x_puissance qui servira à nos calculs)
                 * c'est la puissance en km/h calculée avec :
                 * le pourcentage de puissance * 300km/h qui est la vitesse d'une balle de golf,
                 * 339,56 d'après notre ami Google
                 */
                puissance0 = (x_puissance / 100) * 300;
                puissance_tir = (int) x_puissance;
                // Puissance de tir arrondie en INT
                texte_accueil.setTexte(new String("Appuyez sur Espace pour tirer ! (les bonus sont invisibles)"));
                //On sort de l'écran les éléments de choix (puissance, angle...)
                puissance.translater(0, -500);
                puissance_contour.translater(0, -500);
                ligne_angle.translater(0, -500);
            }
            f.rafraichir();
        }

        // Avant le lancer de la balle

        while (true && repetitionAnimationJoueur != 0) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }
            repetitionAttente++;
            //Ici on fait une petite animation du joueur avant le lancer, histoire de ne pas s'ennuyer
            if (repetitionAttente <= 3) {
                a.setNumeroImage("3");
                a.translater(-1, 0);
                casque.translater(-1,0);
                balle.translater(-1, 0);
            } else if (repetitionAttente <= 6) {
                a.setNumeroImage("1");
                a.translater(1, 0);
                casque.translater(1, 0);
                balle.translater(1, 0);
            } else if (repetitionAttente == 20) {
                repetitionAttente = 0;
            }
            if (souris.getBoutonGaucheEnfonce()) {
                a.setNumeroImage("2");
            }

            if (a.getLecture() == true) {
                repetitionAnimationJoueur--;
            }
            
            //Un petit effet de grossissement de texte
            if (repetitionAttente % 10 == 0) {
                texte_accueil.setPolice(new Font("Arial", Font.TYPE1_FONT, 20));
            } else {
                texte_accueil.setPolice(new Font("Arial", Font.TYPE1_FONT, 21));
            }

            //La balle est lancée avec espace, on joue l'animation de tir et le bruit de la balle
            if (clavier.getEspaceTape()) {
                a.setNumeroImage("1");
                a.setLecture(true);
                bruitBalle.lecture();
                texte_accueil.setTexte(new String(""));
                //On affiche nos stats de tir ainsi qu'un replay du tir
                cam.translater(0, -500);
                camPuissance.setTexte(Double.toString((x_puissance / 100) * 300));
                camPuissancekh.setTexte("km/h");
                camAngle.setTexte(Double.toString((int) angle_tir));
                camAngleDegres.setTexte("°");
                texteCam.setTexte("Replay du tir");
            }
            f.rafraichir();
        }

        /**
         * Durant le défilement de l'arrière plan, Après le lancer de la balle Formule
         * pour obtenir la distance en fonction de la puissance v0 de départ et de
         * l'angle α en radian : Ex : Puissance * cos (10° = 0.174533 rad) * temps soit
         * : 100*Math.cos(0.174533)*repetition*0.01
         */

        /**
         * y_total est la valeur finale du score en fonction de la puissance et de
         * l'angle Grâce à cela, nous avons la vitesse totale, qui va diminuer avec la balle
         */

        int y_total = (int) (-0.5 * 9.81 * 1 * (1) * (1)
                + (x_puissance / 4) * 1 * Math.sin(angle_tir * (Math.PI / 180)) * (1) + 11);

        /**
         * Si la gravité lunaire est activée, nous aurons besoin de plus de temps avant que la balle reviennes,
         * Donc on en rajoute ici
         */

        if (graviteLunaire == 1)
            y_total += 20;

        while (true && y_total > 0) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }
            repetition++;
            temps++;

            //On affiche les stats et le replay jusqu'à 100 répétitions
            if (repetition < 300) {
                cam.setLecture(true);
            } else {
                cam.setLecture(false);
                cam.setA(new Point(0, -100));
                camPuissance.setTexte("");
                camPuissancekh.setTexte("");
                camAngle.setTexte("");
                camAngleDegres.setTexte("");
                texteCam.setTexte("");
            }

            // Toutes les 2 répétitions, on diminie la vitesse des éléments (Background...)
            if (repetition % 50 == 0) {
                y_total -= 1;
            }

            /**
             * Création des bonus invisibles tous les 561 répétitions de boucle, les bonus sont utilisables maximum 3
             * fois Il rajoute 16km/h en puissance de tir et simule le lancer à 90°, Pas mal
             */
            
            if (repetition % 561 == 0 && OGy <= 120 && nb_bonus < 3) {
                y_total += 10;
                temps = 0;
                x_puissance = 16;
                angle_tir = 90;
                nb_bonus++;
            }
            
            //On actualise les textes de score et de hauteur
            score.setTexte(Integer.toString(repetition));
            if (repetition > 999) {
                score.setA(new Point(730, 550));
            }
            hauteur_score.setTexte(Integer.toString(balle.getO().getY() - 101));
            //Si la hauteur actuelle y de la balle est la hauteur maximale, on la met dans la variable hauteur_max
            if (balle.getO().getY() > hauteur_max)
                hauteur_max = balle.getO().getY();
            
            //On déplace les background ici
            xBackground -= y_total;
            xBackground2 -= y_total;
            a.translater(-y_total, -1);
            casque.translater(-y_total, -1);
            background.translater(-y_total, 0);
            background2.translater(-y_total, 0);
            bgLunaire1.translater(-y_total, 0);
            bgLunaire2.translater(-y_total, 0);
            Easter.translater(-y_total, 0);
            if (xBackground <= -1600) {
                background.translater(3200, 0);
                bgLunaire1.translater(3200, 0);
                xBackground = 1600;
            }
            if (xBackground2 <= -1600) {
                background2.translater(3200, 0);
                bgLunaire2.translater(3200, 0);
                xBackground2 = 1600;
            }
            /**
             * Alors,
             * Ici on à l'équation du y de la balle en fonction de notre gravité, si la 
             * gravité lunaire est activé, elle apparaît pourtant que à partir de la 900ème
             * répétition de boucle, sinon fallait attendre trop longtemps la balle partait trop loin,
             * On aura juste l'effet de gravité lunaire vers la fin des rebondissements
             */
            if (graviteLunaire == 1) {
                if (repetition < 900) {
                    OGy = (int) (-0.5 * 9.81 * 1 * (temps * 0.1) * (temps * 0.1)
                            + (x_puissance / 4) * 10 * Math.sin(angle_tir * (Math.PI / 180)) * (temps * 0.1) + 110);
                } else {
                    OGy = (int) (-0.5 * 3 * 1 * (temps * 0.1) * (temps * 0.1)
                            + (x_puissance / 4) * 10 * Math.sin(angle_tir * (Math.PI / 180)) * (temps * 0.1) + 110);
                }
            } else {
                OGy = (int) (-0.5 * 9.81 * 1 * (temps * 0.1) * (temps * 0.1)
                        + (x_puissance / 4) * 10 * Math.sin(angle_tir * (Math.PI / 180)) * (temps * 0.1) + 110);
            }
            balle.setO(new Point(152, OGy));
            //Quand il y a un rebond, on recommence la parabole à x=0 et une puissance à 65% de la précedente puissance
            if (balle.getO().getY() < 102 && x_puissance > 0) {
                temps = 0;
                x_puissance = x_puissance * 0.65;
                balle.setO(new Point(152, 110));
            }
            f.rafraichir();
        }

        hauteur_score.setTexte(new String("0"));
        balle.setO(new Point(152, 102));

        // Fin de l'animation avec la balle qui avance encore un peu, effet de roulement de balle

        while (true && repetitionFinBalle != 0) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }
            repetitionFinBalle--;
            balle.translater(1, 0);
            repetition++;
            score.setTexte(Integer.toString(repetition));
            f.rafraichir();
        }
        //Une partie va reprendre automatiquement dans 5 secondes, donc on avertie l'utilisateur,
        //Et on affiche la hauteur maximale et le score final
        texte_jeu_reprendre.setTexte(new String("Une nouvelle partie va démarrer automatiquement"));
        hauteur_max_label.setTexte(Integer.toString(hauteur_max));
        score_final.setTexte(Integer.toString(repetition));
        texte_hauteur_max_label.setTexte("Hauteur maximale");
        score_final_label.setTexte("Score final");
        
        //On réaffiche nos statistiques avec une photo du replay du tir et on arrête la musique
        cam.setA(new Point(50, 490));
        camPuissance.setTexte(Double.toString(puissance0));
        camPuissancekh.setTexte("km/h");
        camAngle.setTexte(Double.toString((int) angle_tir));
        camAngleDegres.setTexte("°");
        texteCam.setTexte("Photo du tir");
        musiqueBackground.arret();

        //On change la musique de fin en fonction du score,
        //Si le score est inférieur à 1000 c'est une partie "ratée", sinon, c'est pas mal 
        if (repetition < 1000) {
            texte_accueil.setTexte(new String("Jeu Terminé ! Dommage..."));
            gameOver.lecture();
        } else {
            texte_accueil.setTexte(new String("Jeu Terminé ! Bien joué !"));
            youWin.lecture();
        }

        //On attends 4 secondes le temps que l'utilisateur prenne compte de ses statistiques de jeu
        while (close == 0) {
            try {
                Thread.sleep(4000);
            } catch (Exception e) {
            }
            
            close = 1;
        }
        
        //Puis on ferme la fenêtre pour en afficher une nouvelle et c'est repartie
        while (loading == 0) {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
            }
            loading = 1;
            f.fermer();
            musiqueBackground.arret();
        }
    }
}