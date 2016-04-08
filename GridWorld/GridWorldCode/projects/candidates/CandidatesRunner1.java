
import info.gridworld.actor.Bug;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Critter;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

public class CandidatesRunner1
{
	public static void main(String[] args)
	{
		int dimension = 19;
		BoundedGrid<Actor> mygrid = new BoundedGrid<Actor>(dimension,dimension);
		ActorWorld world = new ActorWorld(mygrid);
		
		for(int i = 0; i < dimension/2; i++)
		{
			world.add(new Clinton());
//			world.add(new Sanders());
		
//			world.add(new Trump());
//			world.add(new Cruz());
		}
		
		world.show();
	}
}

