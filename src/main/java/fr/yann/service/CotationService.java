package fr.yann.service;

import java.util.ArrayList;

import fr.yann.model.enums.EpreuveEnum;
import fr.yann.model.enums.SexeEnum;

public class CotationService {
	
	public int getPoints(EpreuveEnum epreuve, SexeEnum sexe, String perf){
		return 0;
	}
	
	
coef_a = new Array (81.205,25.019,67.92,20.568,57.692,17.573,21.77,6.5713,4.799,1.2685,5.077,1.3216,1.658,0.46566,1.7249,0.512,0.8582,0.2453,0.9121,0.2533,0.53418,0.15851,0.34042,0.11295,0.18778,0.06826,0.199,0.0705,0.09962,0.03885,0.1042,0.04023,0.04066,0.013457,0.04272,0.01382,0.03511,0.01199,0.03622,0.01214,0.02199,0.007019,0.0228,0.007183,0.008189,0.002568,0.008493,0.002637,0.002654,0.0008177,0.0028,0.000868,0.0005567,0.0001742,0.000014682,0.000007296,25.822,13.636,21.622,11.361,18.014,9.466,5.58,3.4273,0.705,0.257,0.4192,0.1761,0.0209,0.01257,0.005165,0.00376,39.382,49.449,39.382,49.449,3.3767,5.9026,3.3767,5.9026,1.821,2.2943,1.821,2.2943,0.473,0.5438,0.473,0.5438,0.04298,0.04631,0.04298,0.04631,0.004233,0.004156,0.0029488,0.004118,0.002454,0.00408,1.2019,0.3954,0.29767,0.08169,0.32277,0.08533,0.05404,0.01562,0.05897,0.01628,0.001558,0.0006822,0.0006648,0.000212,0.0000382,0.0000231,0.000001365)
coef_b = new Array (-9.5,-13.1,-10.3,-14.3,-11.1,-15.4,-17.5,-24.5,-36,-53,-36,-53,-59,-86,-59,-86,-82,-120,-82,-120,-108,-156,-133,-185,-184,-250,-184,-250,-245,-330,-245,-330,-385,-540,-385,-540,-415,-580,-415,-580,-528,-750,-528,-750,-840,-1200,-840,-1200,-1460,-2100,-1460,-2100,-3120,-4500,-16800,-21600,-13.2,-16.1,-14.4,-17.6,-15.7,-19.2,-28,-31.4,-80,-112,-102,-137,-472,-630,-860,-1060,10.2,9.2,10.2,9.2,37,28,37,28,50,45,50,45,97,92,97,92,681,656,681,656,2170,2190,2600,2200,2850,2210,-70,-98,-144,-212,-144,-212,-328,-480,-328,-480,-1385,-2060,-2073,-3470,-8160,-12000,-43200)
coef_c = new Array (0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-5000,-5000,-5000,-5000,-5000,-5000,-5000,-5000,-5000,-5000,-5000,-5000,-5000,-5000,-5000,-5000,-20000,-20000,-20000,-20000,-20000,-20000,-20000,-20000,-20000,-20000,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)
coef_d = new Array (0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0.01,0.01,0.01,0.01,0.01,0.01,0.01,0.01,0.01,0.01,0.01,0.01,0.01,0.01,0.01,0.01,0.01,0.01,0.01,0.01,0.01,0.01,0.01,0.01,0.01,0.01,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)

coef_B = new Array (0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,126.961581,101.1143044,126.961581,101.1143044,1480.73565,847.0843607,1480.73565,847.0843607,2745.74412,2179.316977,2745.74412,2179.313977,10570.8245,9194.556847,10570.8245,9194.556847,465332.713,431872.1659,465332.713,431872.1659,4724781.479,4812319.538,6782419.967,4856726.566,8149959.25,4901960.784,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)
coef_C = new Array (9.5,13.1,10.3,14.3,11.1,15.4,17.5,24.5,36,53,36,53,59,86,59,86,82,120,82,120,108,156,133,185,184,250,184,250,245,330,245,330,385,540,385,540,415,580,415,580,528,750,528,750,840,1200,840,1200,1460,2100,1460,2100,3120,4500,16800,21600,13.2,16.1,14.4,17.6,15.7,19.2,28,31.4,80,112,102,137,472,630,860,1060,-10.205,-9.205,-10.205,-9.205,-37.005,-28.005,-37.005,-28.005,-50.005,-45.005,-50.005,-45.005,-97.005,-92.005,-97.005,-92.005,-681.005,-656.005,-681.005,-656.005,-2170.005,-2190.005,-2600.005,-2200.005,-2850.005,-2210.005,70,98,144,212,144,212,328,480,328,480,1385,2060,2073,3470,8160,12000,43200)


private int calcul_resultat(int pts){ 


heure = new Number(0);
var choix = document.formulaire.epreuve.selectedIndex;
sous_la_racine = pts/coef_a[choix] + coef_B[choix];
if (sous_la_racine < 0) 
{
	return 0;
}
determine_racine = -Math.sqrt(sous_la_racine) + coef_C[choix];
if (determine_racine < 0) 
{
	determine_racine = Math.sqrt(sous_la_racine) + coef_C[choix];
}
resultat = Math.floor(100 * determine_racine) / 100;
if ((choix < 72) || (choix > 97)) 
{
	if (resultat > 60) 
	{
		resultat_text = new String("");
		multiple = Math.floor(resultat /3600);
		if (multiple > 0) 
		{
			resultat_text = multiple + ".";
			heure = multiple;
			resultat -= multiple * 3600;
			reste = resultat;
		}
		multiple = Math.floor(resultat /60);
		if (multiple > 0) 
		{
			if ((heure > 0) && (multiple < 10))
			{
				resultat_text += "0";
			}
			resultat_text += multiple + ".";
			reste = resultat - multiple * 60; 
		}
		else
		{
			if (heure > 0)
			{
				resultat_text += "00.";
			}
		}
		reste = Math.floor(100 * reste) / 100;
		if ((reste < 10)&&(reste != 0))
		{
			resultat_text += "0";
		}
		resultat_text = resultat_text + reste;
		return resultat_text;
	}
}
return resultat;
}
function calcul_points(perf) {
var choix = document.formulaire.epreuve.selectedIndex;
if ((choix < 72) || (choix > 97)) 
{
	perf_en_seconde = new Number(0);
	perf_string = new String(perf);
	if (perf_string == "")
	{
		return 0;
	}
	compte_point = new Number(0);
	pointeur = new Number(0);
	last_pointeur = new Number(0);
	while (perf_string.indexOf(".", pointeur) > -1) 
	{
		pointeur = perf_string.indexOf(".", pointeur);
		if ((pointeur - last_pointeur) > 2)
		{
			return 0;
		}
		compte_point += 1;
		perf_en_seconde += parseInt(perf_string.substring(last_pointeur, pointeur)); 
		pointeur += 1;
		last_pointeur = pointeur;
		perf_en_seconde *= 60;
	}
	if ((choix < 53) || ((choix > 55) && (choix < 112)))
	{
		if (perf_en_seconde > 3600)
		{
			perf_en_seconde /= 60;
		}
		if (perf_en_seconde > 3600)
		{
			perf_en_seconde /= 60;
		}
		resultat = formule_points(perf_en_seconde);
		if ((resultat != 0) && (compte_point == 1))
		{
			perf_en_seconde += parseInt(perf_string.substring(pointeur));
			return formule_points(perf_en_seconde);
		}
		perf_en_seconde += parseFloat("."+perf_string.substring(pointeur));
	}
	else
	{
		if (perf_en_seconde > 216000)
		{
			perf_en_seconde /= 60;
			perf_en_seconde += parseFloat("."+perf_string.substring(pointeur));
		}
		else
		{
			perf_en_seconde += parseInt(perf_string.substring(pointeur));
		}
		if (perf_en_seconde < 3600)
		{
			perf_en_seconde *= 60;
			return formule_points(perf_en_seconde);
		}
	}
	if ((compte_point > 1) && (compte_point < 4)) 
	{
		return formule_points(perf_en_seconde);
	}
	if (compte_point > 3) { return 0 }
}
return formule_points(perf);
}

int formule_points(String perf) {
	// perf = new Number(perf);
	String epreuveSelectioned = "";
	if (perf < 0) {return 0}
	if (coef_c[choix] == 0) {
		if (perf > - coef_b[choix]) {return 0}
	}
	premier_carre =  Math.pow(perf + coef_b[choix],2);
	deuxieme_carre =  Math.pow(perf + coef_b[choix] + coef_d[choix],2);
	resultat = Math.floor(coef_a[choix] * (premier_carre + deuxieme_carre) / 2 + coef_c[choix])
	if ((resultat < 0) || (resultat > 2000)) {return 0}
	if (isNaN(resultat)) {return 0}
	return resultat;
}

List<String> epreuves = new ArrayList<>();

private void init(){
epreuves.add("50m(i)");
epreuves.add("50m(i)");
epreuves.add("55m(i)");
epreuves.add("55m(i)");
epreuves.add("60m(i)");
epreuves.add("60m(i)");
epreuves.add("100m"); 
epreuves.add("100m"); 
epreuves.add("200m");
epreuves.add("200m");
epreuves.add("200m(i)");
epreuves.add("200m(i)");
epreuves.add("300m");
epreuves.add("300m");
epreuves.add("300m(i)");
epreuves.add("300m(i)");
epreuves.add("400m");
epreuves.add("400m");
epreuves.add("400m(i)");
epreuves.add("400m(i)");
epreuves.add("500m(i)");
epreuves.add("500m(i)");
epreuves.add("600m");
epreuves.add("600m");
epreuves.add("800m);
epreuves.add("800m");
epreuves.add("800m(i)");
epreuves.add("800m(i)");
epreuves.add("1000m");
epreuves.add("1000m");
epreuves.add("1000m(i)");
epreuves.add("1000m(i)");
epreuves.add("1500m");
epreuves.add("1500m");
epreuves.add("1500m(i)");
epreuves.add("1500m(i)");
epreuves.add("Mile");
epreuves.add("Mile");
epreuves.add("Mile(i)");
epreuves.add("Mile(i)");
epreuves.add("2000m");
epreuves.add("2000m");
epreuves.add("2000m(i)");
epreuves.add("2000m(i)");
epreuves.add("3000m");
epreuves.add("3000m");
epreuves.add("3000m(i)");
epreuves.add("3000m(i)");
epreuves.add("5000m");
epreuves.add("5000m");
epreuves.add("5000m(i)");
epreuves.add("5000m(i)");
epreuves.add("10000m");
epreuves.add("10000m");
epreuves.add("Marathon");
epreuves.add("Marathon");
epreuves.add("50m h(i) (106)");
epreuves.add("50m h(i) (84)");
epreuves.add("55m h(i) (106)");
epreuves.add("55m h(i) (84)");
epreuves.add("60m h(i) (106)");
epreuves.add("60m h(i) (84)");
epreuves.add("110m h");
epreuves.add("100m h");
epreuves.add("320m h (84)");
epreuves.add("320m h (76)");
epreuves.add("400m h (91)");
epreuves.add("400m h (76)");
epreuves.add("1500m sc");
epreuves.add("2000m sc");
epreuves.add("2000m sc");
epreuves.add("3000m sc);
epreuves.add("Hauteur");
epreuves.add("Hauteur");
epreuves.add("Hauteur(i)");
epreuves.add("Hauteur(i)");
epreuves.add("Perche");
epreuves.add("Perche");
epreuves.add("Perche(i)");
epreuves.add("Perche(i)");
epreuves.add("Longueur");
epreuves.add("Longueur");
epreuves.add("Longueur(i)");
epreuves.add("Longueur(i)");
epreuves.add("Triple saut");
epreuves.add("Triple saut");
epreuves.add("Triple saut(i)");
epreuves.add("Triple saut(i)");
epreuves.add("Poids");
epreuves.add("Poids");
epreuves.add("Poids(i)");
epreuves.add("Poids(i)");
epreuves.add("Disque");
epreuves.add("Disque");
epreuves.add("Marteau");
epreuves.add("Marteau");
epreuves.add("Javelot");
epreuves.add("Javelot");
epreuves.add("4*100m");
epreuves.add("4*100m");
epreuves.add("4*200m");
epreuves.add("4*200m");
epreuves.add("4*200m(i));
epreuves.add("4*200m(i)");
epreuves.add("4*400m");
epreuves.add("4*400m");
epreuves.add("4*400m(i)");
epreuves.add("4*400m(i)");
epreuves.add("2000m Marche");
epreuves.add("3000m Marche");
epreuves.add("3000m Marche");
epreuves.add("5000m Marche");
epreuves.add("10km Marche");
epreuves.add("20km Marche");
epreuves.add("50km Marche");

}
