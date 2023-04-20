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
	
	
	public static double dayDuration = isDebug ? 20*sec :20*min;
	public static double roleAttribution = isDebug ? 5*sec :20*min;

}
