package edu.up.cs301.shapefitter;

/**
 * Solver: finds fit for a shape; completed solution by Vegdahl.
 *
 * @author **** put your name here ****
 * @version **** put completion date here ****
 */
public class MyShapeSolver extends ShapeSolver {

    /**
     * Creates a solver for a particular problem.
     *
     * @param parmShape the shape to fit
     * @param parmWorld the world to fit it into
     * @param acc to send notification messages to
     */
    public MyShapeSolver(boolean[][] parmShape, boolean[][] parmWorld, ShapeSolutionAcceptor acc) {
        // invoke superclass constructor
        super(parmShape, parmWorld, acc);
    }

    /**
     * Solves the problem by finding a fit, if possible. The last call to display tells where
     * the fit is. If there is no fit, no call to display should be made--alternatively, a call to
     * undisplay can be made.
     */
    public void solve() {
        //creates a copy of the world
        boolean[][] worldCopy = new boolean[world.length + shape.length][world[0].length + shape[0].length];
        for(int i = 0; i < world.length + shape.length; i++)
        {
            for(int j = 0; j < world[0].length + shape[0].length; j++)
            {
                //before the world starts
                if( i < shape.length || j < shape.length )
                {
                    worldCopy[i][j] = true;
                }
                //in the world
                else if( i < world.length && j < world[i].length )
                {
                    worldCopy[i][j] = world[i-shape.length][j-shape[0].length];
                }
                //after the world ends
                else
                {
                    worldCopy[i][j] = true;
                }
            }
        }
        // loop through the world in original orientation. Finds where the shape can fit and
        // displays that location once found.
        for( int i = 0 ; i < world.length ; i++ ) {
            for (int j = 0; j < world[i].length; j++) {
                boolean fits = true;
                //loop through the shape, once we find a place for fits to be false we break so we
                // can go to the location in the world
                for( int a = 0; a < shape.length; a++ )
                {
                    for( int b = 0; b < shape[a].length; b++ )
                    {
                        if( shape[a][b] && worldCopy[i+a][j+b] )
                        {
                            fits = false;
                            break;
                        }
                        else
                        {
                            display( i, j, Orientation.ROTATE_NONE);
                        }
                    }
                    if(!fits)
                    {
                        break;
                    }
                }
                if(fits)
                {
                    return;
                }
            }
        }
    }

    /**
     * Checks if the shape is well-formed: has at least one square, and has all squares connected.
     *
     * @return whether the shape is well-formed
     */
    public boolean check() {
        return Math.random() < 0.5;
    }

}
