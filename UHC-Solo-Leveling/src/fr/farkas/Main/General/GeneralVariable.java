package fr.farkas.Main.General;

public class GeneralVariable {
	
	public static boolean isDebug = true;
	/* -------------- IMPORTANT --------------
	 * 
	 * La variable isDebug va permettre de rapidement passer des valeurs utilisé pour les tests
	 * aux valeurs utilisées durant l'utilisation réel.
	 * Il est donc nécessaire d'initialiser la variable de la minère suivante :
	 * 
	 * public static <type> <nom> = isDebug ? <valeurTest> : <valeurRéel>;
	 * 
	 * */
	
	/* ------------- UTILISATION -------------
	 * 
	 * Afin d'utiliser les variables présentes dans ce document, vous devez :
	 * 
	 * import fr.farkas.Main.General.GeneralVariable;
	 * 
	 * GeneralVariable.<variableName>
	 * 
	 * */
	
	
	public static int h = 360;
	public static int min = 60;
	public static int sec = 1;
	public static int ticToSec = 20;
	
	
	public static int dayDuration = isDebug ? 20*sec : 20*min;
	public static int roleAttribution = isDebug ? (5*sec)*ticToSec : (20*min)*ticToSec;
	
	public static int portalDuration = isDebug ? (30*sec)*ticToSec : (10*min)*ticToSec;
	public static int portalCooldown = isDebug ? (5*sec)*ticToSec : (30*min)*ticToSec;
	public static int portalDefender = isDebug ? 1:5;
	

	
	// ------------------------------------- ChaHaeIn -------------->
	public static int noteveilEffect_ChaHaeIn = isDebug ? (2*min)*ticToSec : (2*min)*ticToSec;
	public static int eveilEffect_ChaHaeIn = isDebug ? (2*min+40*sec)*ticToSec : (2*min+40*sec)*ticToSec;
	
	// ------------------------------------- GoGunHee -------------->
	public static int cooldownTransformation_GoGunHee = isDebug ? (1*min+30*sec)*ticToSec : (1*min+30*sec)*ticToSec;

}
