import java.io.*;

public class PlayGolfClient
{
    public static void main (String[] args) throws IOException
    {
	BufferedReader kbi = new BufferedReader (new InputStreamReader (System.in));

	int club1 = 0;
	int club1Max = 0;
	int club1Min = 0;

	int hitDist = 0;
	int trapDist = 0;
	int trapSize = 0;


	System.out.println ("Welcome to the Golf Game!");

	System.out.println ("Please enter the hole number: "); // Enter first hole details
	int holeNum = Integer.parseInt (kbi.readLine ());

	System.out.println ("Please enter the distance of the first hole: ");
	int holeDist = Integer.parseInt (kbi.readLine ());

	System.out.println ("Please enter the par of the first hole: ");
	int par = Integer.parseInt (kbi.readLine ());


	GolfClub clubs = new GolfClub (club1);


	Hole hole1 = new Hole (holeNum, holeDist, par);
	System.out.println (hole1); // Create first hole


	int strokes = 0;
	int distLeft = holeDist;
	System.out.println (clubs);

	boolean trap = false;

	while (holeNum <= 9) // Play 9 holes
	{
	    boolean ballIn = false;

	    while (ballIn == false)
	    {

		System.out.println ("Which club would you like to use? 1 for Wood, 2 for Iron or 3 for putter.");
		int select = Integer.parseInt (kbi.readLine ());

		GolfClub club = new GolfClub (select); // creates the club Object you play with

		System.out.println ("How much force would you like to use? 1, 2, or 3");
		int force = Integer.parseInt (kbi.readLine ());

		hitDist = club.swing (force);

		if (trap == true)
		{
		    hitDist = hitDist / 2;
		}

		distLeft = distLeft - hitDist;
		
		if (distLeft >= hole1.getTrapSize () && distLeft <= (hole1.getTrapDist () + hole1.getTrapSize ()))
		{
		    System.out.println ("You are in a trap. Your swing distance will be split in half.");
		    trap = true;
		}

		if (distLeft < 0) // If user over hits
		{
		    System.out.println ("You hit the ball too far!");
		    distLeft = 0 - distLeft;
		}
		System.out.println ("Your distance to the hole is now " + distLeft + " yards.");


		System.out.println ("You hit the ball " + hitDist + " yards. \n");


		strokes++;
		if (distLeft <= 5 && distLeft >= 0) // Check if ball is within 5 units of the hole
		{
		    System.out.println ("Well done! You were able to sink the ball after " + strokes + " strokes. \n");
		    if (strokes > par)
		    {
			int strokes1 = strokes - par;
			System.out.println ("You were " + strokes1 + " over par. \n");
		    }

		    if (strokes == par)
		    {
			System.out.println ("You got par. \n");
		    }

		    if (strokes < par)
		    {
			int strokes2 = par - strokes;
			System.out.println ("You were " + strokes2 + " under par. Well done. \n");
		    }

		    strokes = 0;

		    System.out.println ("What hole would you like to play next?");
		    holeNum = Integer.parseInt (kbi.readLine ());
		    
		    Hole newHole = new Hole (holeNum); // Output new hole from second constructor
		    System.out.println (newHole);
		    
		    distLeft = newHole.getHoleDist();
		    System.out.println(distLeft);
		}
		ballIn = true;
	    }
	    holeNum++;
	}
    }
}
