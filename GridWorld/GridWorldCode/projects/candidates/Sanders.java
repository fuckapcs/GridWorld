import info.gridworld.grid.Grid;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;

public class Sanders extends Actor
{
	boolean feeltheberningtaxes;
	boolean makeamericagreatagain;
	boolean ucsantacruz;
	
	public void act()
    {
        if (getGrid() == null)
            return;
        Grid g = getGrid();
        checkAll();
        ArrayList<Actor> actors = getActors();
        processActors(actors);
        ArrayList<Location> moveLocs = getMoveLocations();
        Location loc = selectMoveLocation(moveLocs);
        makeMove(loc);
    }

	public void checkAll()
	{
		Grid<Actor> gr = getGrid();
		ArrayList<Location> a = gr.getOccupiedLocations();
		for (Location p : a) {
			if (gr.get(p) instanceof Sanders)
				feeltheberningtaxes = true;
			if (gr.get(p) instanceof Trump)
				makeamericagreatagain = true;
			if (gr.get(p) instanceof Cruz)
				ucsantacruz = true;
		}
	}
	
	public ArrayList<Actor> getActors()
    {
        return getGrid().getNeighbors(getLocation());
    }

    
	public void processActors(ArrayList<Actor> actors)
    {
		boolean bernfirst = true;
		boolean trumpfirst = true;
		boolean cruzfirst = true;
		
        for (Actor a : actors)
        {
            if(feeltheberningtaxes && a instanceof Sanders && bernfirst)
            {
				bernfirst = false;
				if(Math.random()<.1)
					a.removeSelfFromGrid();
			}
		}
	    for (Actor a : actors)
        {
			if(makeamericagreatagain && a instanceof Trump && trumpfirst)
            {
				trumpfirst = false;
				if(Math.random()<.1)
					a.removeSelfFromGrid();
			}
		}
		for (Actor a : actors)
        {
            if(ucsantacruz && a instanceof Cruz && cruzfirst)
            {
				cruzfirst = false;
				if(Math.random()<.1)
					a.removeSelfFromGrid();
			}
        }
    }
    
    public ArrayList<Location> getMoveLocations()
    {
        return getGrid().getEmptyAdjacentLocations(getLocation());
    }
    
    public Location selectMoveLocation(ArrayList<Location> locs)
    {
		Grid<Actor> gr = getGrid();
		int onethirdcol = (gr.getNumCols())/3;
        int n = locs.size();
        if (n == 0)
            return getLocation();
        if(feeltheberningtaxes)
        {
			double r = (Math.random());
			if(r<.5)
			{
				return locs.get((int)(Math.random()*locs.size()));
			}
			if(r>=.5)
			{
				if(getLocation().getRow()>onethirdcol)
					if(gr.get(getLocation().getAdjacentLocation(Location.WEST)) == null)
						return getLocation().getAdjacentLocation(Location.WEST);
					else
					{
						return locs.get((int)(Math.random()*locs.size()));
					}
				if(getLocation().getRow()<onethirdcol)
				{
					if(gr.get(getLocation().getAdjacentLocation(Location.EAST)) == null)
						return getLocation().getAdjacentLocation(Location.EAST);
					else
					{
						return locs.get((int)(Math.random()*locs.size()));
					}
				}
				else
					return locs.get((int)(Math.random()*locs.size()));
			}
		}
		else if(makeamericagreatagain || ucsantacruz)
		{
			Location closest = new Location(0,0);
			double distance;
			double nearestdist= 100;
			for(int i = 0; i<locs.size(); i++)
			{
				distance = Math.sqrt(Math.pow(locs.get(i).getRow()-getLocation().getRow(),2) + Math.pow(locs.get(i).getCol()-getLocation().getCol(),2));
				if(distance < nearestdist)
				{
					nearestdist = distance;
					closest = locs.get(i);
				}
			}
			if(gr.get(getLocation().getAdjacentLocation(getLocation().getDirectionToward(closest))) == null)//if location with getDirectionLocation is available
				return getLocation().getAdjacentLocation(getLocation().getDirectionToward(closest));
			else
				return locs.get((int)(Math.random()*locs.size()));
		}
		return locs.get((int)(Math.random()*locs.size()));
    }
    
	public void makeMove(Location loc)
    {
        if (loc == null)
            removeSelfFromGrid();
        else
            moveTo(loc);
    }
	
}